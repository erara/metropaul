package com.hercule.commun.beans;

import javax.json.Json;
import javax.json.JsonObjectBuilder;


public class LineDirectionModel {
	private int idDirection;
	private String code;
	private String libelle;
    
    public LineDirectionModel() {
    	
    }

	public int getIdDirection() {
		return idDirection;
	}

	public void setIdDirection(int idDirection) {
		this.idDirection = idDirection;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

    public String transformToJson() {
    	JsonObjectBuilder jsonBuilder = Json.createObjectBuilder()
    			.add("idDirection", this.idDirection)
    			.add("code", this.code)
    			.add("libelle", this.libelle);
    	return jsonBuilder.build().toString();
    }
}
