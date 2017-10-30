package com.prakash.final_project;

import java.io.Serializable;

/**
 * Created by prakash on 4/10/2017.
 */

public class Data implements Serializable {
    private String name1,rating1,add1,lat,lng;

    public Data(String name1, String rating1, String add1, String lat, String lng) {
        this.name1 = name1;
        this.rating1 = rating1;
        this.add1 = add1;
        this.lat = lat;
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getRating1() {
        return rating1;
    }

    public void setRating1(String rating1) {
        this.rating1 = rating1;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }
}
