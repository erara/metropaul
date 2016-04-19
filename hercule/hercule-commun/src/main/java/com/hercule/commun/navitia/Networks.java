package com.hercule.commun.navitia;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Antoine Lafuente on 14/07/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Networks {
    private ArrayList<Object> disruptions;
    private Object pagination;
    private ArrayList<Network> networks;
    private ArrayList<Object> links;
    private ArrayList<Object> feed_publishers;




    public Networks() {
    }

    public ArrayList<Object> getDisruptions() {
        return disruptions;
    }

    public void setDisruptions(ArrayList<Object> disruptions) {
        this.disruptions = disruptions;
    }

    public ArrayList<Object> getFeed_publishers() {
        return feed_publishers;
    }

    public void setFeed_publishers(ArrayList<Object> feed_publishers) {
        this.feed_publishers = feed_publishers;
    }

    public ArrayList<Object> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Object> links) {
        this.links = links;
    }

    public ArrayList<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(ArrayList<Network> networks) {
        this.networks = networks;
    }

    public Object getPagination() {
        return pagination;
    }

    public void setPagination(Object pagination) {
        this.pagination = pagination;
    }
}
