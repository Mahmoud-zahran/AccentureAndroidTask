package com.example.accentureandroidtask.mainActivityMVP;

import com.example.accentureandroidtask.entity.Response;

import java.util.ArrayList;

import javax.inject.Inject;


public class MainActivityPresenterImpl implements MainActivityContract.Presenter  {


    private MainActivityContract.View mView;
    private ArrayList<Response> users;



    @Inject
    public MainActivityPresenterImpl( MainActivityContract.View mView){
       // this.feedsApi = feedsApi;//DownloadDataTypeServiceProvider.getInstance();
        this.mView = mView;


    }

    @Override
    public void loadFeedsData() {
        mView.showProgress();

    }

    @Override
    public ArrayList<Response> getUsers() {
        return users;
    }


}
