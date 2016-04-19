package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Antoine Lafuente on 11/01/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminRegion {

    private Coord coord;
    private String id;
    private String label;
    private int level;
    private String zip_code;
    private String name;

    public AdminRegion() {
    }

    public AdminRegion(Coord coord, String id, String label, int level, String zip_code, String name) {
        this.coord = coord;
        this.id = id;
        this.label = label;
        this.level = level;
        this.zip_code = zip_code;
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
