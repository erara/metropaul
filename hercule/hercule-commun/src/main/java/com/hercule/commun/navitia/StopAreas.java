package com.hercule.commun.navitia;

import java.util.ArrayList;

public class StopAreas {

	private ArrayList<StopArea> stop_areas;
	private Pagination pagination;
	private ArrayList<Object> feed_publishers;
	private ArrayList<Link> links;
	private ArrayList<Object> disruptions;
	public ArrayList<StopArea> getStop_areas() {
		return stop_areas;
	}
	public void setStop_areas(ArrayList<StopArea> stop_areas) {
		this.stop_areas = stop_areas;
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
	public ArrayList<Link> getLinks() {
		return links;	
	}
	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}
	public ArrayList<Object> getDisruptions() {
		return disruptions;
	}
	public void setDisruptions(ArrayList<Object> disruptions) {
		this.disruptions = disruptions;
	}
	
	
}
