package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoine Lafuente on 10/01/2015.
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Journey {
    private String type;
    private int duration;
    private String requested_date_time;
    private String departure_date_time;
    private String arrival_date_time;
    private ArrayList<String> tags;
    private int nb_transfers;
    private ArrayList<Link> links;
    private ArrayList<Section> sections;
    private DisplayInformations display_informations;
    private Fare fare;
    private int price;
    private String libellePrice;
    private List<StopDateTime> stop_date_times;


    // -------------- Constructors -------------- //

    public Journey() {
    }

    public Journey(String type, int duration, String requested_date_time, String departure_date_time, String arrival_date_time, ArrayList<String> tags, int nb_transfers, ArrayList<Link> links, ArrayList<Section> sections, Fare fare) {
        this.type = type;
        this.duration = duration;
        this.requested_date_time = requested_date_time;
        this.departure_date_time = departure_date_time;
        this.arrival_date_time = arrival_date_time;
        this.tags = tags;
        this.nb_transfers = nb_transfers;
        this.links = links;
        this.sections = sections;
        this.fare = fare;
    }

    // *******************GETTERS AND SETTERS******************* //

    public Fare getFare() {
        return fare;
    }

    /**
	 * @return the stop_date_times
	 */
	public List<StopDateTime> getStop_date_times() {
		return stop_date_times;
	}

	/**
	 * @param stop_date_times the stop_date_times to set
	 */
	public void setStop_date_times(List<StopDateTime> stop_date_times) {
		this.stop_date_times = stop_date_times;
	}

	/**
	 * @return the display_informations
	 */
	public DisplayInformations getDisplay_informations() {
		return display_informations;
	}

	/**
	 * @param display_informations the display_informations to set
	 */
	public void setDisplay_informations(DisplayInformations display_informations) {
		this.display_informations = display_informations;
	}

	public void setFare(Fare fare) {
        this.fare = fare;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRequested_date_time() {
        return requested_date_time;
    }

    public void setRequested_date_time(String requested_date_time) {
        this.requested_date_time = requested_date_time;
    }

    public String getDeparture_date_time() {
        return departure_date_time;
    }

    public void setDeparture_date_time(String departure_date_time) {
        this.departure_date_time = departure_date_time;
    }

    public String getArrival_date_time() {
        return arrival_date_time;
    }

    public void setArrival_date_time(String arrival_date_time) {
        this.arrival_date_time = arrival_date_time;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public int getNb_transfers() {
        return nb_transfers;
    }

    public void setNb_transfers(int nb_transfers) {
        this.nb_transfers = nb_transfers;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLibellePrice() {
        return libellePrice;
    }

    public void setLibellePrice(String libellePrice) {
        this.libellePrice = libellePrice;
    }
}