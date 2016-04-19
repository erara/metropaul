package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Antoine Lafuente on 11/01/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coord {

    private String lat;

    private String lon;

    public Coord( String lon,String lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public Coord(){
        this.lon = null;
        this.lat = null;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
