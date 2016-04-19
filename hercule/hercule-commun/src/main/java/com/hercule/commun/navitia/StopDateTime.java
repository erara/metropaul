package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Antoine Lafuente on 11/01/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StopDateTime {

    private ArrayList<String> additional_informations;

    private String departure_date_time;

    private String arrival_date_time;

    private StopPoint stop_point;

    private ArrayList<Object> links;

    public StopDateTime(ArrayList<String> additional_informations, String departure_date_time, String arrival_date_time, StopPoint stop_point, ArrayList<Object> links) {
        this.additional_informations = additional_informations;
        this.departure_date_time = departure_date_time;
        this.arrival_date_time = arrival_date_time;
        this.stop_point = stop_point;
        this.links = links;
    }

    public StopDateTime() {
    }

    public ArrayList<String> getAdditional_informations() {
        return additional_informations;
    }

    public void setAdditional_informations(ArrayList<String> additional_informations) {
        this.additional_informations = additional_informations;
    }

    public String getDeparture_date_time() {
        return departure_date_time;
    }

    public void setDeparture_date_time(String departure_date_time) {
        this.departure_date_time = departure_date_time;
    }

    public String getArrival_date_time() {
        return arrival_date_time;
    }

    public void setArrival_date_time(String arrival_date_time) {
        this.arrival_date_time = arrival_date_time;
    }

    public StopPoint getStop_point() {
        return stop_point;
    }

    public void setStop_point(StopPoint stop_point) {
        this.stop_point = stop_point;
    }

    public ArrayList<Object> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Object> links) {
        this.links = links;
    }
}

