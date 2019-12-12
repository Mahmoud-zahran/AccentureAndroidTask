package com.example.accentureandroidtask.mainActivityMVP;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.accentureandroidtask.R;
import com.example.accentureandroidtask.daggerNeededFiles.component.ApplicationComponent;
import com.example.accentureandroidtask.daggerNeededFiles.component.DaggerMainActivityComponent;
import com.example.accentureandroidtask.daggerNeededFiles.component.MainActivityComponent;
import com.example.accentureandroidtask.daggerNeededFiles.module.MainActivityContextModule;
import com.example.accentureandroidtask.daggerNeededFiles.module.MainActivityMvpModule;
import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ActivityContext;
import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ApplicationContext;
import com.example.accentureandroidtask.pojo.WeatherDataResponse;
import com.example.accentureandroidtask.root.MyApplication;
import com.example.accentureandroidtask.testCases.TestingActivity;
import com.example.accentureandroidtask.util.CustomProgressDialog;
import com.example.accentureandroidtask.util.GPSTracker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {
    @Inject
    @ApplicationContext
    public Context context;

//    @Inject
//    AppDatabase mAppDatabase;

    @Inject
    @ActivityContext
    public Context activityContext;
    String TAG = "MainActivity";

    @Inject
    MainActivityPresenterImpl mainActivityPresenter;
    CustomProgressDialog mCustomProgressDialog;
    @BindView(R.id.AR_TitleTextView)
    TextView mTemprature;
    @BindView(R.id.AR_DateTextView)
    TextView ARDateTextView;
    @BindView(R.id.AR_SubjectTextView)
    TextView mDate;

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    Date date = new Date();

    private String temp;
    GPSTracker gps;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivityComponent mainActivityComponent;


        ButterKnife.bind(this);
        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .mainActivityMvpModule(new MainActivityMvpModule(this))
                .applicationComponent(applicationComponent)
                .build();
        mainActivityComponent.injectMainActivity(this);
        if (ActivityCompat.checkSelfPermission((Activity) activityContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) activityContext, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 10);
        }
        mainActivityPresenter.loadFeedsData(activityContext);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_SetVehicle:
                Intent intent = new Intent(MainActivity.this, TestingActivity.class);
                startActivity(intent);
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
        temp = mainActivityPresenter.getUsers();
        Log.d(TAG, "showComplete: " + "showComplete message");
    }

    @Override
    public void showWeatherData(WeatherDataResponse mWeatherDataResponse) {
        mTemprature.setText("Temperature: "+mWeatherDataResponse.getMain().getTemp().toString());
        mDate.setText("Date: "+formatter.format(date));


    }

    @Override
    public void updateRecycleView(ArrayList<String> users) {

    }
}
