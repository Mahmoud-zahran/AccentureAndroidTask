package com.example.accentureandroidtask.roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.accentureandroidtask.roomdatabase.entity.WeatherDataEntity;

import java.util.List;

@Dao
public interface WeatherDao extends BaseDao<WeatherDataEntity>{
    @Query("SELECT * FROM TB_Weather")
    List<WeatherDataEntity> getAll();

    @Query("SELECT * FROM TB_Weather WHERE city LIKE :city")
    List<WeatherDataEntity> findPersonsByName(String city);

    @Query("DELETE  FROM TB_Weather")
    public void deleteAll();
}