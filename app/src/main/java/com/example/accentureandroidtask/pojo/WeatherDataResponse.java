
package com.example.accentureandroidtask.pojo;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherDataResponse implements Serializable
{

    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private Integer cod;
    private final static long serialVersionUID = 6170835411939428595L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WeatherDataResponse() {
    }

    /**
     * 
     * @param dt
     * @param coord
     * @param weather
     * @param name
     * @param cod
     * @param main
     * @param clouds
     * @param id
     * @param sys
     * @param base
     * @param wind
     */
    public WeatherDataResponse(Coord coord, List<Weather> weather, String base, Main main, Wind wind, Clouds clouds, Integer dt, Sys sys, Integer id, String name, Integer cod) {
        super();
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

//    @Override
//    public String toString() {
//        return new ToStringBuilder(this).append("coord", coord).append("weather", weather).append("base", base).append("main", main).append("wind", wind).append("clouds", clouds).append("dt", dt).append("sys", sys).append("id", id).append("name", name).append("cod", cod).toString();
//    }
//
//    @Override
//    public int hashCode() {
//        return new HashCodeBuilder().append(dt).append(coord).append(weather).append(name).append(cod).append(main).append(clouds).append(id).append(sys).append(base).append(wind).toHashCode();
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//        if ((other instanceof WeatherDataResponse) == false) {
//            return false;
//        }
//        WeatherDataResponse rhs = ((WeatherDataResponse) other);
//        return new EqualsBuilder().append(dt, rhs.dt).append(coord, rhs.coord).append(weather, rhs.weather).append(name, rhs.name).append(cod, rhs.cod).append(main, rhs.main).append(clouds, rhs.clouds).append(id, rhs.id).append(sys, rhs.sys).append(base, rhs.base).append(wind, rhs.wind).isEquals();
//    }

}
