
package com.example.accentureandroidtask.pojo;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;
    private final static long serialVersionUID = -4718863780124249870L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Weather() {
    }

    /**
     * 
     * @param icon
     * @param description
     * @param main
     * @param id
     */
    public Weather(Integer id, String main, String description, String icon) {
        super();
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

//    @Override
//    public String toString() {
//        return new ToStringBuilder(this).append("id", id).append("main", main).append("description", description).append("icon", icon).toString();
//    }
//
//    @Override
//    public int hashCode() {
//        return new HashCodeBuilder().append(icon).append(description).append(main).append(id).toHashCode();
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//        if ((other instanceof Weather) == false) {
//            return false;
//        }
//        Weather rhs = ((Weather) other);
//        return new EqualsBuilder().append(icon, rhs.icon).append(description, rhs.description).append(main, rhs.main).append(id, rhs.id).isEquals();
//    }

}
