package com.example.accentureandroidtask.mainActivityMVP;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.accentureandroidtask.APIInterface;
import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ActivityContext;
import com.example.accentureandroidtask.entity.Response;
import com.example.accentureandroidtask.util.GPSTracker;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class MainActivityPresenterImpl implements MainActivityContract.Presenter  {


    private MainActivityContract.View mView;
    APIInterface apiInterface;
    private ArrayList<Response> users;




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
                .subscribe(new Observer<String>() {
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
                    public void onNext(String data) {
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
    public ArrayList<Response> getUsers() {
        return users;
    }


}
