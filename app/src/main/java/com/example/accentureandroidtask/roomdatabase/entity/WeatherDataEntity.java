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

    @ColumnInfo(name = "temperature")
    public double temperature;

    @ColumnInfo(name = "date")
    public String date;



    public int getTemp_id() {
        return temp_id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}