package com.example.accentureandroidtask.ui.mainActivityMVP;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;


import com.example.accentureandroidtask.APIInterface;
import com.example.accentureandroidtask.ApiUrls;
import com.example.accentureandroidtask.pojo.WeatherDataResponse;
import com.example.accentureandroidtask.roomdatabase.entity.WeatherDataEntity;
import com.example.accentureandroidtask.util.GPSTracker;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivityPresenterImpl implements MainActivityContract.Presenter  {


    private MainActivityContract.View mView;
    APIInterface apiInterface;
    GPSTracker gps;
    WeatherDataEntity mWeatherDataEntity;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    Date date = new Date();




    @Inject
    public MainActivityPresenterImpl( APIInterface apiInterface, MainActivityContract.View mView){
       // this.feedsApi = feedsApi;//DownloadDataTypeServiceProvider.getInstance();
        this.apiInterface = apiInterface;
        this.mView = mView;
        mWeatherDataEntity= new WeatherDataEntity();
//mAppDatabase= Room.databaseBuilder(, AppDatabase.class, "database-name").build();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void loadFeedsData(Context context,boolean save) {
        //mView.showProgress();
       // mView.getLatLong();
        gps = new GPSTracker(context);


        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

      //  mView.hideProgress();
        apiInterface.getApiDataByLatLong(latitude+"",longitude+"", ApiUrls.appkey).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherDataResponse>() {
                    @Override
                    public void onComplete() {
                        mView.showComplete();
                      //  mView.hideProgress();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showProgress();
                    }

                    @Override
                    public void onNext(WeatherDataResponse data) {
                        Log.d("weather data", "onNext: " + data.getMain().getTemp().toString());
                        mView.showWeatherData(data);
                        mWeatherDataEntity.setCity(data.getName());
                        mWeatherDataEntity.setLatitude(data.getCoord().getLat());
                        mWeatherDataEntity.setLongitude(data.getCoord().getLon());
                        mWeatherDataEntity.setTemperature(data.getMain().getTemp());
                        mWeatherDataEntity.setDate(formatter.format(date));
                        mWeatherDataEntity.setWeatherId(data.getWeather().get(0).getId());
                        //     Executor.IOThread(() -> BaseDao.insert((WeatherDataEntity)mainActivityPresenter.mWeatherDataEntity);

                        if (save) {
                            mView.saveCurrentTemp(context);
                            mView.showWeatherData(data);
                        }else{
                            mView.showWeatherData(data);
                            Log.d("presenter", "save buttonClicked: " + "DataBase message " + data.getName());
                            Toast.makeText(context, "Your Location is " + data.getName(), Toast.LENGTH_LONG).show();

                        }
                        mView.hideProgress();

                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError("weather call Api","Error occurred"+e.getMessage());
                        mView.hideProgress();
                    }
                });
        }else{
            gps.showSettingsAlert();
        }


    }




}
