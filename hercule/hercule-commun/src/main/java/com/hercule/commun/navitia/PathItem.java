package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Antoine Lafuente on 11/01/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PathItem {

    private int length;
    private String name;
    private int duration;
    private int direction;

    public PathItem(int length, String name, int duration, int direction) {
        this.length = length;
        this.name = name;
        this.duration = duration;
        this.direction = direction;
    }

    public PathItem() {
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
