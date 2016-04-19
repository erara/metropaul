package com.hercule.commun.navitia;

import java.util.ArrayList;

public class CommercialMode {

	private String id;
	private String name;
	private ArrayList<Object> physical_modes;
	
	public CommercialMode(){
	}
	
	public CommercialMode(String id, String name, ArrayList<Object> physical_modes) {
		this.id = id;
		this.name = name;
		this.physical_modes = physical_modes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Object> getPhysical_modes() {
		return physical_modes;
	}
	public void setPhysical_modes(ArrayList<Object> physical_modes) {
		this.physical_modes = physical_modes;
	}
	
	
}
