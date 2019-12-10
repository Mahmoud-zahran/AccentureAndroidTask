
package com.example.accentureandroidtask.pojo;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind implements Serializable
{

    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Integer deg;
    private final static long serialVersionUID = -6877083303190627832L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Wind() {
    }

    /**
     * 
     * @param deg
     * @param speed
     */
    public Wind(Double speed, Integer deg) {
        super();
        this.speed = speed;
        this.deg = deg;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

//    @Override
//    public String toString() {
//        return new ToStringBuilder(this).append("speed", speed).append("deg", deg).toString();
//    }
//
//    @Override
//    public int hashCode() {
//        return new HashCodeBuilder().append(speed).append(deg).toHashCode();
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//        if ((other instanceof Wind) == false) {
//            return false;
//        }
//        Wind rhs = ((Wind) other);
//        return new EqualsBuilder().append(speed, rhs.speed).append(deg, rhs.deg).isEquals();
//    }

}
