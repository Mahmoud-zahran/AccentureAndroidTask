package com.example.accentureandroidtask.mainActivityMVP;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.accentureandroidtask.APIInterface;
import com.example.accentureandroidtask.pojo.WeatherDataResponse;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivityPresenterImpl implements MainActivityContract.Presenter  {


    private MainActivityContract.View mView;
    APIInterface apiInterface;





    @Inject
    public MainActivityPresenterImpl( APIInterface apiInterface, MainActivityContract.View mView){
       // this.feedsApi = feedsApi;//DownloadDataTypeServiceProvider.getInstance();
        this.apiInterface = apiInterface;
        this.mView = mView;


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void loadFeedsData() {
        //mView.showProgress();
        mView.getLatLong();
      //  mView.hideProgress();
        apiInterface.getApiData().subscribeOn(Schedulers.io())
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
                        Log.d("weather data", "onNext: "+ data.getMain().getTemp().toString());
                        mView.hideProgress();
                    }
                    @Override
                    public void onError(Throwable e) {
                        mView.showError("weather call Api","Error occurred"+e.getMessage());
                        mView.hideProgress();
                    }
                });


    }

    @Override
    public String getUsers() {
        return "jj";
    }


}
