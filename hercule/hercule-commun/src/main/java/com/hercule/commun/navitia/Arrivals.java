package com.hercule.commun.navitia;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = false)
public class Arrivals {
    private Pagination pagination;
    private ArrayList<Object> links;
    private ArrayList<Object> disruptions;
    private ArrayList<Object> notes;
    private ArrayList<Object> feed_publishers;
    private ArrayList<Arrival> arrivals;
    private ArrayList<Object> exceptions;
	
    
    
    public Arrivals() {
		super();
	}



	public Arrivals(Pagination pagination, ArrayList<Object> links, ArrayList<Object> disruptions,
			ArrayList<Object> notes, ArrayList<Object> feed_publishers, ArrayList<Arrival> arrivals,
			ArrayList<Object> exceptions) {
		super();
		this.pagination = pagination;
		this.links = links;
		this.disruptions = disruptions;
		this.notes = notes;
		this.feed_publishers = feed_publishers;
		this.arrivals = arrivals;
		this.exceptions = exceptions;
	}
    
    
    
    public Pagination getPagination() {
		return pagination;
	}



	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
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



	public ArrayList<Object> getNotes() {
		return notes;
	}



	public void setNotes(ArrayList<Object> notes) {
		this.notes = notes;
	}



	public ArrayList<Object> getFeed_publishers() {
		return feed_publishers;
	}



	public void setFeed_publishers(ArrayList<Object> feed_publishers) {
		this.feed_publishers = feed_publishers;
	}



	public ArrayList<Arrival> getArrivals() {
		return arrivals;
	}



	public void setArrivals(ArrayList<Arrival> arrivals) {
		this.arrivals = arrivals;
	}



	public ArrayList<Object> getExceptions() {
		return exceptions;
	}



	public void setExceptions(ArrayList<Object> exceptions) {
		this.exceptions = exceptions;
	}



	
    
    
    
}

