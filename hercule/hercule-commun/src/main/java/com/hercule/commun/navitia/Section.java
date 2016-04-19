package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Antoine Lafuente on 15/01/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class Section {
    private String id;
    private Place from;
    private Place to;
    private ArrayList<String> additional_informations;
    private String departure_date_time;
    private String arrival_date_time;
    private int duration;
    private String mode;
    private Object geojson;
    private DisplayInformations display_informations;
    private String type;
    private ArrayList<LinkSection> links;
    private ArrayList<PathItem> path;
    private String transfer_type;
    private ArrayList<StopDateTime> stop_date_times;

    public Section() {
    }

    public Section(String id, Place from, Place to, ArrayList<String> additional_informations, String departure_date_time, String arrival_date_time, int duration, String mode, Object geojson, DisplayInformations display_informations, String type, ArrayList<LinkSection> links, ArrayList<PathItem> path, String transfer_type, ArrayList<StopDateTime> stop_date_times) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.additional_informations = additional_informations;
        this.departure_date_time = departure_date_time;
        this.arrival_date_time = arrival_date_time;
        this.duration = duration;
        this.mode = mode;
        this.geojson = geojson;
        this.display_informations = display_informations;
        this.type = type;
        this.links = links;
        this.path = path;
        this.transfer_type = transfer_type;
        this.stop_date_times = stop_date_times;
    }

    // ************************** GETTERS AND SETTERS ************************** //

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Place getFrom() {
        return from;
    }

    public void setFrom(Place from) {
        this.from = from;
    }

    public Place getTo() {
        return to;
    }

    public void setTo(Place to) {
        this.to = to;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Object getGeojson() {
        return geojson;
    }

    public void setGeojson(Object geojson) {
        this.geojson = geojson;
    }

    public DisplayInformations getDisplay_informations() {
        return display_informations;
    }

    public void setDisplay_informations(DisplayInformations display_informations) {
        this.display_informations = display_informations;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<LinkSection> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<LinkSection> links) {
        this.links = links;
    }

    public ArrayList<PathItem> getPath() {
        return path;
    }

    public void setPath(ArrayList<PathItem> path) {
        this.path = path;
    }

    public String getTransfer_type() {
        return transfer_type;
    }

    public void setTransfer_type(String transfer_type) {
        this.transfer_type = transfer_type;
    }

    public ArrayList<StopDateTime> getStop_date_times() {
        return stop_date_times;
    }

    public void setStop_date_times(ArrayList<StopDateTime> stop_date_times) {
        this.stop_date_times = stop_date_times;
    }
}
