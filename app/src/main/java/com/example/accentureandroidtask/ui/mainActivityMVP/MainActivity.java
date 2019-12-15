package com.example.accentureandroidtask.ui.mainActivityMVP;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.accentureandroidtask.R;
import com.example.accentureandroidtask.adapter.RecyclerViewAdapter;
import com.example.accentureandroidtask.daggerNeededFiles.component.ApplicationComponent;
import com.example.accentureandroidtask.daggerNeededFiles.component.DaggerMainActivityComponent;
import com.example.accentureandroidtask.daggerNeededFiles.component.MainActivityComponent;
import com.example.accentureandroidtask.daggerNeededFiles.module.MainActivityContextModule;
import com.example.accentureandroidtask.daggerNeededFiles.module.MainActivityMvpModule;
import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ActivityContext;
import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ApplicationContext;
import com.example.accentureandroidtask.pojo.WeatherDataResponse;
import com.example.accentureandroidtask.roomdatabase.AppDatabase;
import com.example.accentureandroidtask.roomdatabase.Executor;
import com.example.accentureandroidtask.roomdatabase.entity.WeatherDataEntity;
import com.example.accentureandroidtask.root.MyApplication;
import com.example.accentureandroidtask.ui.detailsActivityMVP.DetailsActivity;
import com.example.accentureandroidtask.util.CustomProgressDialog;
import com.example.accentureandroidtask.util.MovableImageButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View, RecyclerViewAdapter.ClickListener {
    private static final String TAG = "MainActivity";
    @Inject
    @ApplicationContext
    public Context context;

    @BindView(R.id.deleteAll_savedData_movableBtn)
    MovableImageButton deleteAllSavedDataMovableBtn;

    private RecyclerView recyclerView;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Inject
    MainActivityPresenterImpl mainActivityPresenter;
    CustomProgressDialog mCustomProgressDialog;

    List<WeatherDataEntity> savedTempList;

    MenuItem item;

    @Inject
    AppDatabase mAppDatabase;

    private Timer autoUpdate;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        MainActivityComponent mainActivityComponent;


        ButterKnife.bind(this);
        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .mainActivityMvpModule(new MainActivityMvpModule(this))
//                .databaseModule(new DatabaseModule(this))
                .applicationComponent(applicationComponent)
                .build();
        mainActivityComponent.injectMainActivity(this);

        if (ActivityCompat.checkSelfPermission((Activity) activityContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) activityContext, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 10);
        }
//        Executor.IOThread(() ->mAppDatabase.weatherDao().deleteAll());
        runExecuterToGetData();
        recyclerView.setAdapter(recyclerViewAdapter);

        // mainActivityPresenter.loadFeedsData(activityContext);

    }

    @Override
    protected void onResume() {
        super.onResume();
        autoUpdate = new Timer();
        autoUpdate.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    public void run() {
                        updateWeatherData();
                    }
                });
            }
        }, 0, 60000); // updates each 60 secs
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void updateWeatherData(){
        // your logic here
        mainActivityPresenter.loadFeedsData(getApplicationContext(),false);
        populateRecyclerView(savedTempList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    @Override
    public void populateRecyclerView(List<WeatherDataEntity> response) {
        recyclerViewAdapter.setData(response);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        item =menu.findItem(R.id.action_refresh);

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save:
                mainActivityPresenter.loadFeedsData(getApplicationContext(),true);
                // saveCurrentTemp(getApplicationContext());
//                Log.d(TAG, "save buttonClicked: " + "DataBase message "+  savedTempList.get(0).getCity());
//
//                 Log.d(TAG, "save buttonClicked: " + "DataBase message"+  city);
//                mAppDatabase.weatherDao().getAll().get(0);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void showError(String call, String statusMessage) {
        if (call.equals("network error")) {
            Log.d(TAG, "showError:network error " + statusMessage);
            Toast.makeText(context, statusMessage, Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "showError: " + "error message");
            Toast.makeText(context, "error message", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgress() {
        mCustomProgressDialog = new CustomProgressDialog(this);
        mCustomProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mCustomProgressDialog.show();
        Log.d(TAG, "showProgress: " + "showProgress message");
    }

//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Override
//    public String getLatLong() {
//        gps = new GPSTracker(activityContext);
//
//
//        if (gps.canGetLocation()) {
//
//            double latitude = gps.getLatitude();
//            double longitude = gps.getLongitude();
//            String cityName="null";
//            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
//            try {
//
//                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
//                 cityName = addresses.get(0).getAddressLine(0).split(",")[1];
//                 return  cityName;
//            }catch (Exception e){
//                Log.e(TAG, "getLatLong: "+e.getMessage(),e );
//            }
//            Toast.makeText(getApplicationContext(), "Your Location is "+cityName+" - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
//        } else {
//
//
//            gps.showSettingsAlert();
//            return "empty";
//        }
//        return "empty";
//    }

    @Override
    public void hideProgress() {
        mCustomProgressDialog.dismiss();
        mCustomProgressDialog = null;
        Log.d(TAG, "hideProgress: " + "hideProgress message");
    }

    @Override
    public void showComplete() {
        //    temp = mainActivityPresenter.getSavedTempList();
        Log.d(TAG, "showComplete: " + "showComplete message");
    }

    @Override
    public void showWeatherData(WeatherDataResponse mWeatherDataResponse) {
//        mTemprature.setText("Temperature: "+mWeatherDataResponse.getMain().getTemp().toString());
//        mDate.setText("Date: "+formatter.format(date));
        double  temp= Math.round(mWeatherDataResponse.getMain().getTemp())-273.15;
        String x= String.format(Locale.getDefault(), "%.0f°", temp);

        item.setTitle(x+"C");


    }

    @Override
    public void updateRecycleView(ArrayList<String> users) {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void saveCurrentTemp(Context context) {

        Executor.IOThread(() -> {
            mAppDatabase.weatherDao().insert(mainActivityPresenter.mWeatherDataEntity);
            savedTempList = mAppDatabase.weatherDao().getAll();
        });
        Log.d("presenter", "save buttonClicked: " + "DataBase message " + mainActivityPresenter.mWeatherDataEntity.getCity());
        Toast.makeText(context, "Your Location is " + mainActivityPresenter.mWeatherDataEntity.getCity(), Toast.LENGTH_LONG).show();
        populateRecyclerView(savedTempList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void runExecuterToGetData() {
        savedTempList = new ArrayList<>();
        Executor.IOThread(() -> {
            savedTempList = mAppDatabase.weatherDao().getAll();
            populateRecyclerView(savedTempList);
//            Log.d(TAG, "save buttonClicked: " + "DataBase message "+  savedTempList.get(0).getCity());
            // mAppDatabase.weatherDao().getAll().get(0).getCity();


//        Toast.makeText(getApplicationContext(), "Your Location is "+mainActivityPresenter.mWeatherDataEntity.getCity(), Toast.LENGTH_LONG).show();


        });
    }

    @Override
    public List<WeatherDataEntity> getSavedTempList() {
        runExecuterToGetData();
        return savedTempList;
    }

    @Override
    public void launchIntent(WeatherDataEntity url) {
//        Toast.makeText(activityContext, "RecyclerView Row selected " + url.getCity(), Toast.LENGTH_SHORT).show();
        Intent mIntent=new Intent(activityContext, DetailsActivity.class);
        mIntent.putExtra("city", url.getCity());
        mIntent.putExtra("temp", (double)url.getTemperature());
        mIntent.putExtra("weather", (int) url.getWeatherId());

        mIntent.putExtra("date", url.getDate());
        startActivity(mIntent);
        Animatoo.animateZoom(activityContext);
    }

    @OnClick(R.id.deleteAll_savedData_movableBtn)
    public void onViewClicked() {

                Executor.IOThread(() ->{

                    mAppDatabase.weatherDao().deleteAll();
                    savedTempList = mAppDatabase.weatherDao().getAll();

                });

        recyclerViewAdapter.setData(new ArrayList<WeatherDataEntity>());
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(recyclerViewAdapter);
        Toast.makeText(getApplicationContext(), "All weather data Deleted.", Toast.LENGTH_LONG).show();



    }


}
