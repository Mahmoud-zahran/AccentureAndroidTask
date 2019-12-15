package com.example.accentureandroidtask.ui;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;

import com.example.accentureandroidtask.APIInterface;
import com.example.accentureandroidtask.ApiUrls;
import com.example.accentureandroidtask.R;
import com.example.accentureandroidtask.pojo.WeatherDataResponse;
import com.example.accentureandroidtask.roomdatabase.AppDatabase;
import com.example.accentureandroidtask.roomdatabase.Executor;
import com.example.accentureandroidtask.util.GPSTracker;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Implementation of App Widget functionality.
 */
public class WeatherLoggerWidget extends AppWidgetProvider {
//    @Inject
//    APIInterface apiInterface;
    static RemoteViews views;
    @Inject
    AppDatabase mAppDatabase;


    static GPSTracker gps;
    @RequiresApi(api = Build.VERSION_CODES.M)
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
         views = new RemoteViews(context.getPackageName(), R.layout.weather_logger_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        gps = new GPSTracker(context);


        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            views.setTextViewText(R.id.appwidget_text,"latitude: "+ latitude+" longitude: "+ longitude);



            //  mView.hideProgress();
         /*   apiInterface.getApiDataByLatLong(latitude+"",longitude+"", ApiUrls.appkey).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<WeatherDataResponse>() {
                        @Override
                        public void onComplete() {

                            //  mView.hideProgress();
                        }

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(WeatherDataResponse data) {
                            Log.d("weather data", "onNext: " + data.getMain().getTemp().toString());

                            views.setTextViewText(R.id.appwidget_text,"Temperature: "+ data.getMain().getTemp().toString());


                            //     Executor.IOThread(() -> BaseDao.insert((WeatherDataEntity)mainActivityPresenter.mWeatherDataEntity);





                        }
                        @Override
                        public void onError(Throwable e) {

                        }
                    });*/
        }else{
            gps.showSettingsAlert();
        }



        // Construct the RemoteViews object

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
//        Executor.IOThread(()->{
//            views.setTextViewText(R.id.appwidget_text,"City: "+ mAppDatabase.weatherDao().getAll().get(0).getCity());
//
//            mAppDatabase.weatherDao().getAll().get(0).getCity();
//        });
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

