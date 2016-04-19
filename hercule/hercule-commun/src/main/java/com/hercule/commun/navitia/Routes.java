package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Antoine Lafuente on 14/07/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Routes {
    private ArrayList<Route> routes;
    private ArrayList<Object> disruption;
    private Pagination pagination;
    private ArrayList<Link> links;
    private ArrayList<Object> feed_publishers;

    public Routes() {
    }

    public Routes(ArrayList<Route> routes, ArrayList<Object> disruption, Pagination pagination, ArrayList<Link> links, ArrayList<Object> feed_publishers) {
        this.routes = routes;
        this.disruption = disruption;
        this.pagination = pagination;
        this.links = links;
        this.feed_publishers = feed_publishers;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public ArrayList<Object> getDisruption() {
        return disruption;
    }

    public void setDisruption(ArrayList<Object> disruption) {
        this.disruption = disruption;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    public ArrayList<Object> getFeed_publishers() {
        return feed_publishers;
    }

    public void setFeed_publishers(ArrayList<Object> feed_publishers) {
        this.feed_publishers = feed_publishers;
    }
}
