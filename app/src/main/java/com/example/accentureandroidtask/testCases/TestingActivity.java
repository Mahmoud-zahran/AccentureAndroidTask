package com.example.accentureandroidtask.testCases;

import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.accentureandroidtask.APIInterface;
import com.example.accentureandroidtask.ApiUrls;
import com.example.accentureandroidtask.R;
import com.example.accentureandroidtask.daggerNeededFiles.component.ApplicationComponent;
import com.example.accentureandroidtask.daggerNeededFiles.component.DaggerTestingActivityComponent;
import com.example.accentureandroidtask.daggerNeededFiles.component.TestingActivityComponent;
import com.example.accentureandroidtask.daggerNeededFiles.module.DatabaseModule;
import com.example.accentureandroidtask.daggerNeededFiles.module.DetailsActivityContextModule;
import com.example.accentureandroidtask.daggerNeededFiles.module.RetrofitModule;
import com.example.accentureandroidtask.daggerNeededFiles.module.TestingActivityContextModule;
import com.example.accentureandroidtask.pojo.WeatherDataResponse;
import com.example.accentureandroidtask.root.MyApplication;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TestingActivity extends AppCompatActivity {
    private EditText txtResult;
    private EditText txtSummery;

    @Inject
    APIInterface apiInterface;

    private int lineIndex = 0;
    int i = 0;

    private int countsuccess = 0;
    private int countCanceled = 0;
    private int countFailure = 0;

    private Handler mHandler = new Handler();
    private String[] latitudelist =
            {
                    "35.37","10.6666","51.11111","19.5679","66.77777","0","","10.1010"
                   };
    private String [] longituidelist =
            {
                    "-33.2344","19.18181","22.333","-13.19991","10.022777","0","10.00100",""
            };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        txtResult = (EditText) findViewById(R.id.txtResult);
        txtSummery = (EditText) findViewById(R.id.txtSummery);
        TestingActivityComponent testActivityComponent;
        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        testActivityComponent = DaggerTestingActivityComponent.builder()
                .testingActivityContextModule(new TestingActivityContextModule(this))
            //    .retrofitModule(new RetrofitModule(this))
                .applicationComponent(applicationComponent)
                .build();
       testActivityComponent.injectTestingActivity(this);
        runTestCode();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    private void runTestCode() {
        txtResult.setText("test data static latitude and longitude\n");
        for (i=0; i<latitudelist.length;i++) {
            apiInterface.getApiDataByLatLong(latitudelist[i]+ "", longituidelist[i] + "", ApiUrls.appkey).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<WeatherDataResponse>() {
                        @Override
                        public void onComplete() {
                            txtResult.append("********************\n OnStart Call\n"+
                                    "\nlatitude: "  + latitudelist[i-1]+"\nlongitude: "+ longituidelist[i-1]+"\n");

                        }

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(WeatherDataResponse data) {

                            double  temp= Math.round(data.getMain().getTemp())-273.15;
                            String x= String.format(Locale.getDefault(), "%.0f°", temp);
                            txtResult.append("city: "+data.getName()+"\ntemperature: "+ x+"C\n");
                            Log.d("weather data", "onNext: " + data.getMain().getTemp().toString());

                            countsuccess++;

                        }

                        @Override
                        public void onError(Throwable e) {
                            txtResult.setText(e.getMessage());
                            countFailure++;
                        }
                    });
            logInEditText("test\n");
        }




    }

    private void logInEditText(final String msg) {

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (lineIndex == 0)
                    txtResult.append(lineIndex++ + ": " + msg);
                else
                    txtResult.append("\n" + lineIndex++ + ": " + msg);

                txtSummery.setText("\nsuccess: " + countsuccess);
                txtSummery.append("\nCanceled: " + countCanceled);
                txtSummery.append("\nFailure: " + countFailure);
            }
        }, 10000);

    }


}
