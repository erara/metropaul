package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Antoine Lafuente on 11/01/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StopArea {

    private String id;
    private String name;
    private Coord coord;
    private ArrayList<AdminRegion> administrative_regions;
    private ArrayList<String> equipments;
    private ArrayList<StopPoint> stop_points;
    private String comment;
    private String label;
    private String timezone;

    public StopArea() {
    }

    public StopArea(String id, String name, Coord coord, ArrayList<AdminRegion> administrative_regions, ArrayList<String> equipments, ArrayList<StopPoint> stop_points, String comment, String label, String timezone) {
        this.id = id;
        this.name = name;
        this.coord = coord;
        this.administrative_regions = administrative_regions;
        this.equipments = equipments;
        this.stop_points = stop_points;
        this.comment = comment;
        this.label = label;
        this.timezone = timezone;
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

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public ArrayList<AdminRegion> getAdministrative_regions() {
        return administrative_regions;
    }

    public void setAdministrative_regions(ArrayList<AdminRegion> administrative_regions) {
        this.administrative_regions = administrative_regions;
    }

    public ArrayList<String> getEquipments() {
        return equipments;
    }

    public void setEquipments(ArrayList<String> equipments) {
        this.equipments = equipments;
    }

    public ArrayList<StopPoint> getStop_points() {
        return stop_points;
    }

    public void setStop_points(ArrayList<StopPoint> stop_points) {
        this.stop_points = stop_points;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
