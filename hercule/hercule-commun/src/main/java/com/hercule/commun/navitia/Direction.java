package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Direction {
	private String embedded_type;
	private int quality;
	private StopArea stop_area;
	private StopPoint stop_point;
	private String name;
	private String id;
	
	public Direction() {
		super();
	}

	public String getEmbedded_type() {
		return embedded_type;
	}

	public void setEmbedded_type(String embedded_type) {
		this.embedded_type = embedded_type;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public StopArea getStop_area() {
		return stop_area;
	}

	public void setStop_area(StopArea stop_area) {
		this.stop_area = stop_area;
	}

	public StopPoint getStop_point() {
		return stop_point;
	}

	public void setStop_point(StopPoint stop_point) {
		this.stop_point = stop_point;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
		
}
