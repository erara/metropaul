package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Antoine Lafuente on 14/07/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Line {
    private String code;
    private String name;
    private ArrayList<Object> links;
    private ArrayList<Object> physical_modes;
    private String opening_time;
    private Object geojson;
    private CommercialMode commercial_mode;
    private String color;
    private String closing_time;
    private ArrayList<Route> routes;
    private String id;
    private Network network;

    public Line() {
    }

    public Line(String code, String name, ArrayList<Object> links, ArrayList<Object> physical_modes, String opening_time, Object geojson, CommercialMode commercial_mode, String color, String closing_time, ArrayList<Route> routes, String id, Network network) {
        this.code = code;
        this.name = name;
        this.links = links;
        this.physical_modes = physical_modes;
        this.opening_time = opening_time;
        this.geojson = geojson;
        this.commercial_mode = commercial_mode;
        this.color = color;
        this.closing_time = closing_time;
        this.routes = routes;
        this.id = id;
        this.network = network;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Object> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Object> links) {
        this.links = links;
    }

    public ArrayList<Object> getPhysical_modes() {
        return physical_modes;
    }

    public void setPhysical_modes(ArrayList<Object> physical_modes) {
        this.physical_modes = physical_modes;
    }

    public String getOpening_time() {
        return opening_time;
    }

    public void setOpening_time(String opening_time) {
        this.opening_time = opening_time;
    }

    public Object getGeojson() {
        return geojson;
    }

    public void setGeojson(Object geojson) {
        this.geojson = geojson;
    }

    public CommercialMode getCommercial_mode() {
        return commercial_mode;
    }

    public void setCommercial_mode(CommercialMode commercial_mode) {
        this.commercial_mode = commercial_mode;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getClosing_time() {
        return closing_time;
    }

    public void setClosing_time(String closing_time) {
        this.closing_time = closing_time;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }
}
