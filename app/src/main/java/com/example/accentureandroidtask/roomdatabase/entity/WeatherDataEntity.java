package com.example.accentureandroidtask.roomdatabase.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TB_Weather")
public class WeatherDataEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "temp_id")
    public int temp_id;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "longitude")
    public double longitude;

    @ColumnInfo(name = "latitude")
    public double latitude;

}