package com.thuyvy.storm.model;

import java.io.Serializable;

public class Storm implements Serializable {

    private String id;
    private String name;
    private float windSpeed;
    private String description;

    public Storm(String id, String name, float windSpeed, String description) {
        this.id = id;
        this.name = name;
        this.windSpeed = windSpeed;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Storm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", windSpeed=" + windSpeed +
                ", description='" + description + '\'' +
                '}';
    }
}
