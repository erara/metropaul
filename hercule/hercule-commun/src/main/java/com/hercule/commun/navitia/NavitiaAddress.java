package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Antoine Lafuente on 15/01/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NavitiaAddress {

    private ArrayList<AdminRegion> administrative_regions;
    private Coord coord;
    private String house_number;
    private String id;
    private String label;
    private String name;

    public NavitiaAddress() {
    }

    public NavitiaAddress(ArrayList<AdminRegion> administrative_region, Coord coord, String house_number, String id, String label, String name) {
        this.administrative_regions = administrative_region;
        this.coord = coord;
        this.house_number = house_number;
        this.id = id;
        this.label = label;
        this.name = name;
    }

    public ArrayList<AdminRegion> getAdministrative_regions() {
        return administrative_regions;
    }

    public void setAdministrative_regions(ArrayList<AdminRegion> administrative_region) {
        this.administrative_regions = administrative_region;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
