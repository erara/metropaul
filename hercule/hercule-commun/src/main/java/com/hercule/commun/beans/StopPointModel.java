package com.hercule.commun.beans;

import java.util.Date;

public class StopPointModel {
	private int idStopPoint;
	private String name;
    private int idStopArea;
    private int idRoute;
    private String longitude;
    private String latitude;
    private Date lastUpdate;
    
    public StopPointModel() {
    	
    }
    
	/**
	 * @param idStopPoint
	 * @param name
	 * @param idStopArea
	 * @param idRoute
	 * @param longitude
	 * @param latitude
	 */
	public StopPointModel(int idStopPoint, String name, int idStopArea, int idRoute, String longitude, String latitude) {
		this.idStopPoint = idStopPoint;
		this.name = name;
		this.idStopArea = idStopArea;
		this.idRoute = idRoute;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	/**
	 * @return the idStopPoint
	 */
	public int getIdStopPoint() {
		return idStopPoint;
	}

	/**
	 * @param idStopPoint the idStopPoint to set
	 */
	public void setIdStopPoint(int idStopPoint) {
		this.idStopPoint = idStopPoint;
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
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
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
}
