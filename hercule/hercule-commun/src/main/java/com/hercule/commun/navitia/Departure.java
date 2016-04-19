package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = false)
public class Departure {
    private DisplayInformations display_informations;
    private StopPoint stop_point;
    private Route route;
    private StopDateTime stop_date_time;
	
    
    
    public Departure() {
		super();
	}


	public Departure(DisplayInformations display_informations, StopPoint stop_point, Route route,
			StopDateTime stop_date_time) {
		super();
		this.display_informations = display_informations;
		this.stop_point = stop_point;
		this.route = route;
		this.stop_date_time = stop_date_time;
	}


	public DisplayInformations getDisplay_informations() {
		return display_informations;
	}


	public void setDisplay_informations(DisplayInformations display_informations) {
		this.display_informations = display_informations;
	}


	public StopPoint getStop_point() {
		return stop_point;
	}


	public void setStop_point(StopPoint stop_point) {
		this.stop_point = stop_point;
	}


	public Route getRoute() {
		return route;
	}


	public void setRoute(Route route) {
		this.route = route;
	}


	public StopDateTime getStop_date_time() {
		return stop_date_time;
	}


	public void setStop_date_time(StopDateTime stop_date_time) {
		this.stop_date_time = stop_date_time;
	}
    
    
    
}
