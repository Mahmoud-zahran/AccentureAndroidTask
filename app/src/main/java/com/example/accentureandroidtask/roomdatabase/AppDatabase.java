package com.example.accentureandroidtask.roomdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.accentureandroidtask.roomdatabase.dao.WeatherDao;
import com.example.accentureandroidtask.roomdatabase.entity.WeatherDataEntity;

@Database(entities = {WeatherDataEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WeatherDao weatherDao();

}