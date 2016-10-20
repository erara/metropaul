package com.hercule.commun.beans;

import java.util.Date;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class StopAreaLine {
	private int idStopArea;
    private int idLine;
    private Date lastUpdate;


	public StopAreaLine() {
	}
	
	public StopAreaLine(int idStopArea, int idLine, Date lastUpdate) {
		super();
		this.idStopArea = idStopArea;
		this.idLine = idLine;
		this.lastUpdate = lastUpdate;
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
    			.add("idStopArea", this.idStopArea)
    			.add("idLine", this.idLine);
    	return jsonBuilder.build().toString();
    }
    
    
}
