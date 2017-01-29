package com.hercule.commun.beans;

import java.util.Date;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class StopAreaRoute {
	private int idStopArea;
    private int idRoute;
    private Date lastUpdate;
    
    public StopAreaRoute() {
    	
    }

	/**
	 * @return the idStopArea
	 */
	public int getIdStopArea() {
		return idStopArea;
	}

	/**
	 * @param idStopArea the idStopArea to set
	 */
	public void setIdStopArea(int idStopArea) {
		this.idStopArea = idStopArea;
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
    			.add("id_stop_area", this.idStopArea)
    			.add("id_route", this.idRoute);
    	return jsonBuilder.build().toString();
    }
    
    
}
