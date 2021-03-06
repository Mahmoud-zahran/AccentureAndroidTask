package com.example.accentureandroidtask.roomdatabase.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "TB_Weather")
public class WeatherDataEntity implements Serializable {

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

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "weatherId")
    private int weatherId;

    @ColumnInfo(name = "windSpeed")
    private Double windSpeed;

    @ColumnInfo(name = "windDeg")
    private Integer windDeg;
    @ColumnInfo(name = "humidity")
    private Integer humidity;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(Integer windDeg) {
        this.windDeg = windDeg;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

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