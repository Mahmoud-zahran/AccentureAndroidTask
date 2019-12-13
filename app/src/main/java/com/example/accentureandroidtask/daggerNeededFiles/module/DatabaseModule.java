package com.example.accentureandroidtask.daggerNeededFiles.module;

import android.content.Context;

import androidx.room.Room;

import com.example.accentureandroidtask.daggerNeededFiles.qualifer.ApplicationContext;
import com.example.accentureandroidtask.daggerNeededFiles.qualifer.DatabaseInfo;


import com.example.accentureandroidtask.roomdatabase.AppDatabase;
import com.example.accentureandroidtask.roomdatabase.dao.WeatherDao;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    @ApplicationContext
    private final Context mContext;

    @DatabaseInfo
    private final String mDBName = "accenture_database.db";

    public DatabaseModule (@ApplicationContext Context context) {
        mContext = context;
    }

//    @Singleton
    @Provides
    AppDatabase provideDatabase () {
        return Room.databaseBuilder(
                mContext,
                AppDatabase.class,
                mDBName
        ).fallbackToDestructiveMigration().build();

    }

//    @Singleton
//    @Provides
//    public AppDatabase provideMyDatabase(Context context){
//        return Room.databaseBuilder(context, AppDatabase.class, mDBName).build();
//    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() { return mDBName; }



//    @Singleton
    @Provides
    WeatherDao provideWeatherDao(AppDatabase db) { return db.weatherDao(); }

}