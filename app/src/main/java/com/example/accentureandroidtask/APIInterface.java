package com.example.accentureandroidtask;

import com.example.accentureandroidtask.pojo.WeatherDataResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Mahmoud Zahran on 10/12/2019.
 */
public interface APIInterface {
    @GET(ApiUrls.API_City_URL)
    Observable<WeatherDataResponse> getApiDataByCityName(@Query(value="q", encoded=true)  String cityName, @Query(value="appid", encoded=true) String appId);

    //    lat=35&lon=139&appid=68afa9ddaa8361f41357435507e5d916
    @GET(ApiUrls.API_City_URL)
    Observable<WeatherDataResponse> getApiDataByLatLong(@Query(value="lat", encoded=true)  String lat, @Query(value="lon", encoded=true)  String lon, @Query(value="appid", encoded=true) String appId);

    @POST(".")
    Observable<String> getApiDataPost();
}
