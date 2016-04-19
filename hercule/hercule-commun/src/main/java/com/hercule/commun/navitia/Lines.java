package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Antoine Lafuente on 14/07/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lines {
    ArrayList<Object> disruptions;
    Pagination pagination;
    ArrayList<Line> lines;
    ArrayList<Link> links;
    ArrayList<Object> feed_publishers;

    public Lines() {
    }

    public Lines(ArrayList<Object> disruptions, Pagination pagination, ArrayList<Line> lines, ArrayList<Link> links, ArrayList<Object> feed_publishers) {
        this.disruptions = disruptions;
        this.pagination = pagination;
        this.lines = lines;
        this.links = links;
        this.feed_publishers = feed_publishers;
    }

    public ArrayList<Object> getDisruptions() {
        return disruptions;
    }

    public void setDisruptions(ArrayList<Object> disruptions) {
        this.disruptions = disruptions;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
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
