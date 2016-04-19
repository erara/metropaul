package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Created by cyril on 27/01/2015.
 */
public class Places {

    private ArrayList<Place> places;
    private ArrayList<Object> links;

    public Places(ArrayList<Object> links, ArrayList<Place> places) {
        this.links = links;
        this.places = places;
    }

    public Places() {
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    public ArrayList<Object> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Object> links) {
        this.links = links;
    }
}
