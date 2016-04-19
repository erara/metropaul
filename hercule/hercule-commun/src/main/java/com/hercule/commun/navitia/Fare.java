package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Antoine Lafuente on 15/01/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fare {

    private boolean found;
    private Total total;
    private ArrayList<Object> links;

    public Fare(boolean found, Total total, ArrayList<Object> links) {
        this.found = found;
        this.total = total;
        this.links = links;
    }

    public Fare() {
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public ArrayList<Object> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Object> links) {
        this.links = links;
    }
}
