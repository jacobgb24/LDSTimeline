package com.jacobgb24.ldstimeline.model;


import java.io.Serializable;
import java.util.List;

/**
 * Created by jacob_000 on 2/17/2018.
 */

public class Event implements Serializable {
    private String name;
    private String date;
    private String location;
    private double latitude;
    private double longitude;
    private String description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

