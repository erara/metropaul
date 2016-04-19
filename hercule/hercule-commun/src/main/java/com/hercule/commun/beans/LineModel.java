package com.hercule.commun.beans;

import java.util.Date;

public class LineModel {
	private int idLine;
	private String code;
	private String name;
	private int idNetwork;
    private String openingTime;
	private String closingTime;
	private String color;
	private String transport_type;
    private Date lastUpdate;
    
    public LineModel() {
    	
    }

	/**
	 * @param idLine
	 * @param code
	 * @param name
	 * @param idNetwork
	 * @param openingTime
	 * @param closingTime
	 * @param color
	 * @param lastUpdate
	 */
	public LineModel(int idLine, String code, String name, int idNetwork,
			String openingTime, String closingTime, String color, String transport_type,
			Date lastUpdate) {
		this.idLine = idLine;
		this.code = code;
		this.name = name;
		this.idNetwork = idNetwork;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.color = color;
		this.transport_type = transport_type;
		this.lastUpdate = lastUpdate;
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the idNetwork
	 */
	public int getIdNetwork() {
		return idNetwork;
	}

	/**
	 * @param idNetwork the idNetwork to set
	 */
	public void setIdNetwork(int idNetwork) {
		this.idNetwork = idNetwork;
	}

	/**
	 * @return the openingTime
	 */
	public String getOpeningTime() {
		return openingTime;
	}

	/**
	 * @param openingTime the openingTime to set
	 */
	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}

	/**
	 * @return the closingTime
	 */
	public String getClosingTime() {
		return closingTime;
	}

	/**
	 * @param closingTime the closingTime to set
	 */
	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
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

	/**
	 * @return the transport_type
	 */
	public String getTransport_type() {
		return transport_type;
	}

	/**
	 * @param transport_type the transport_type to set
	 */
	public void setTransport_type(String transport_type) {
		this.transport_type = transport_type;
	}
	
	
}
