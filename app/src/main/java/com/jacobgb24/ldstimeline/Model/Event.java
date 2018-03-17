package com.jacobgb24.ldstimeline.Model;

import java.util.List;
import java.util.Map;

/**
 * Created by jacob_000 on 2/17/2018.
 */

public class Event {
    private String name;
    private String date;
    private String location;
    private double latitude;
    private double longitude;
    private String fullDesc;
    private List<Pair> images;
    private List<Pair> sources;

    public Event() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getFullDesc() {
        return fullDesc;
    }

    public void setFullDesc(String fullDesc) {
        this.fullDesc = fullDesc;
    }

    public List<Pair> getImages() {
        return images;
    }

    public void setImages(List<Pair> images) {
        this.images = images;
    }

    public List<Pair> getSources() {
        return sources;
    }

    public void setSources(List<Pair> sources) {
        this.sources = sources;
    }
}
