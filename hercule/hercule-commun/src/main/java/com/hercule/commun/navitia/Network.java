package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Antoine Lafuente on 14/07/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Network {

    private String id;
    private String name;
    private ArrayList<Link> links;

    public Network() {
    }

    public Network(String id, String name, ArrayList<Link> links) {
        this.id = id;
        this.name = name;
        this.links = links;
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

    public ArrayList<Link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

}
