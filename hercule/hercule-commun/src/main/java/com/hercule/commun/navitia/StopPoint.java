package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Antoine Lafuente on 11/01/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPoint {

    private String id;

    private String name;

    private Coord coord;

    private ArrayList<AdminRegion> administrative_regions;

    private ArrayList<String> equipments;

    private StopArea stopArea;

    private String comment;

    private String label;

    private NavitiaAddress address;

    private StopArea stop_area;


    public StopPoint() {
    }

    public StopPoint(String id, String name, Coord coord, ArrayList<AdminRegion> administrative_regions, ArrayList<String> equipments, StopArea stopArea, String comment, String label, NavitiaAddress address, StopArea stop_area) {
        this.id = id;
        this.name = name;
        this.coord = coord;
        this.administrative_regions = administrative_regions;
        this.equipments = equipments;
        this.stopArea = stopArea;
        this.comment = comment;
        this.label = label;
        this.address = address;
        this.stop_area = stop_area;
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

    public StopArea getStopArea() {
        return stopArea;
    }

    public void setStopArea(StopArea stopArea) {
        this.stopArea = stopArea;
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

    public NavitiaAddress getAddress() {
        return address;
    }

    public void setAddress(NavitiaAddress address) {
        this.address = address;
    }

    public StopArea getStop_area() {
        return stop_area;
    }

    public void setStop_area(StopArea stop_area) {
        this.stop_area = stop_area;
    }
}
