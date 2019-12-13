package com.example.accentureandroidtask.mainActivityMVP;

//This is the blueprint of View-Model-Presenter.


import android.content.Context;

import com.example.accentureandroidtask.pojo.WeatherDataResponse;
import com.example.accentureandroidtask.roomdatabase.entity.WeatherDataEntity;

import java.util.ArrayList;
import java.util.List;

public interface MainActivityContract {
    interface View{

        void showError(String call, String statusMessage);
        void showProgress();
        //String getLatLong();
        void hideProgress();
        void showComplete();
        void runExecuterToGetData();
        void saveCurrentTemp(Context context);
        void showWeatherData(WeatherDataResponse mWeatherDataResponse);
        void updateRecycleView(ArrayList<String> users);
        List<WeatherDataEntity> getSavedTempList();
    }

    interface Presenter{
        void loadFeedsData(Context context);




    }
}
