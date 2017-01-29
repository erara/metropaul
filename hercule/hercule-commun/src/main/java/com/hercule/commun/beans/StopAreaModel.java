package com.hercule.commun.beans;

import java.util.Date;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class StopAreaModel {
	
	private int idStopArea;
	private String name;
    private String idNavitia;
    private String longitude;
    private String latitude;
    private int zone;
    private int ignoreItineraire;
    private Date lastUpdate;
    
    public StopAreaModel() {
    	
    }

	/**
	 * @param idStopArea
	 * @param name
	 * @param type
	 * @param lastUpdate
	 */
	public StopAreaModel(int idStopArea, String name, String idNavitia,
			Date lastUpdate) {
		this.idStopArea = idStopArea;
		this.name = name;
		this.idNavitia = idNavitia;
		this.lastUpdate = lastUpdate;
	}
	
	/**
	 * @return the zone
	 */
	public int getZone() {
		return zone;
	}

	/**
	 * @param zone the zone to set
	 */
	public void setZone(int zone) {
		this.zone = zone;
	}

	/**
	 * @return the ignoreItineraire
	 */
	public int getIgnoreItineraire() {
		return ignoreItineraire;
	}

	/**
	 * @param ignoreItineraire the ignoreItineraire to set
	 */
	public void setIgnoreItineraire(int ignoreItineraire) {
		this.ignoreItineraire = ignoreItineraire;
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
	 * @return the idNavitia
	 */
	public String getIdNavitia() {
		return idNavitia;
	}

	/**
	 * @param idNavitia the idNavitia to set
	 */
	public void setIdNavitia(String idNavitia) {
		this.idNavitia = idNavitia;
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

    public String transformToJson() {
    	JsonObjectBuilder jsonBuilder = Json.createObjectBuilder()
    			.add("id_stop_area", this.idStopArea)
    			.add("name", this.name)
    			.add("longitude", this.longitude)
    			.add("latitude", this.latitude)
    			.add("zone", this.zone)
    			.add("ignore_itineraire", this.ignoreItineraire);
    	return jsonBuilder.build().toString();
    }
}
