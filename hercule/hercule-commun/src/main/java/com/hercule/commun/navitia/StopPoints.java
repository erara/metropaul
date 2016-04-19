package com.hercule.commun.navitia;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPoints {
	private ArrayList<StopPoint> stop_points;
	private Pagination pagination;
	private ArrayList<Object> feed_publishers;
	private ArrayList<Object> links;
	private ArrayList<Object> disruptions;
	public ArrayList<StopPoint> getStop_points() {
		return stop_points;
	}
	public void setStop_points(ArrayList<StopPoint> stop_points) {
		this.stop_points = stop_points;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
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
	public ArrayList<Object> getDisruptions() {
		return disruptions;
	}
	public void setDisruptions(ArrayList<Object> disruptions) {
		this.disruptions = disruptions;
	}
	
	

	
}
