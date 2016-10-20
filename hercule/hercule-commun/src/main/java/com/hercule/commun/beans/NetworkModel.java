package com.hercule.commun.beans;

import java.util.Date;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class NetworkModel {

	private int idNetwork;
	private String type;
    private String generiqueType;
    private Date lastUpdate;
    
	public NetworkModel() {

	}

	/**
	 * @param idNetwork
	 * @param type
	 * @param generiqueType
	 * @param lastUpdate
	 */
	public NetworkModel(int idNetwork, String type, String generiqueType, Date lastUpdate) {
		this.idNetwork = idNetwork;
		this.type = type;
		this.generiqueType = generiqueType;
		this.lastUpdate = lastUpdate;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the generiqueType
	 */
	public String getGeneriqueType() {
		return generiqueType;
	}

	/**
	 * @param generiqueType the generiqueType to set
	 */
	public void setGeneriqueType(String generiqueType) {
		this.generiqueType = generiqueType;
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
    			.add("idNetwork", this.idNetwork)
    			.add("type", this.type);
    	return jsonBuilder.build().toString();
    }
}
