
package com.example.accentureandroidtask.pojo;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Clouds implements Serializable
{

    @SerializedName("all")
    @Expose
    private Integer all;
    private final static long serialVersionUID = -1296533392698988300L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Clouds() {
    }

    /**
     * 
     * @param all
     */
    public Clouds(Integer all) {
        super();
        this.all = all;
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

//    @Override
//    public String toString() {
//        return new ToStringBuilder(this).append("all", all).toString();
//    }
//
//    @Override
//    public int hashCode() {
//        return new HashCodeBuilder().append(all).toHashCode();
//    }

//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//        if ((other instanceof Clouds) == false) {
//            return false;
//        }
//        Clouds rhs = ((Clouds) other);
//        return new EqualsBuilder().append(all, rhs.all).isEquals();
//    }

}
