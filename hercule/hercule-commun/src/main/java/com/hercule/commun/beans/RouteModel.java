package com.hercule.commun.beans;

import java.util.Date;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class RouteModel {
	private int idRoute;
	private int idLine;
	private String name;
    private String destination;
    private String openingTime;
    private String closingTime;
    private Date lastUpdate;
    
    public RouteModel() {
    	
    }

	/**
	 * @param idRoute
	 * @param idLine
	 * @param name
	 * @param destination
	 * @param lastUpdate
	 */
	public RouteModel(int idRoute, int idLine, String name, String destination, Date lastUpdate, String openingTime, String closingTime) {
		this.idRoute = idRoute;
		this.idLine = idLine;
		this.name = name;
		this.destination = destination;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.lastUpdate = lastUpdate;
	}

	public String getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}

	public String getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}

	/**
	 * @return the idRoute
	 */
	public int getIdRoute() {
		return idRoute;
	}

	/**
	 * @param idRoute the idRoute to set
	 */
	public void setIdRoute(int idRoute) {
		this.idRoute = idRoute;
	}

	/**
	 * @return the idLine
	 */
	public int getIdLine() {
		return idLine;
	}

	/**
	 * @param idLine the idLine to set
	 */
	public void setIdLine(int idLine) {
		this.idLine = idLine;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @return the lastUpdate
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

    public String transformToJson() {
    	JsonObjectBuilder jsonBuilder = Json.createObjectBuilder()
    			.add("id_route", this.idRoute)
    			.add("id_line", this.idLine)
    			.add("name", this.name)
    			.add("destination", this.destination)
    			.add("opening_time", this.openingTime)
    			.add("closing_time", this.closingTime);
    	return jsonBuilder.build().toString();
    }
    
}
