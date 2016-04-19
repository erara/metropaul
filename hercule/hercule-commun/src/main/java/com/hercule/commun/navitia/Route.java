package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Antoine Lafuente on 14/07/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {
    private Direction direction;
    private String name;
    private ArrayList<Link> links;
    private ArrayList<Object> physical_modes;
    private String is_frequence;
    private Object geojson;
    private Line line;
    private Object disruptions;
    private ArrayList<StopPoint> stop_points;
    private String id;

    public Route() {
    }

    
    public Route(Direction direction, String name, ArrayList<Link> links,
			ArrayList<Object> physical_modes, String is_frequence,
			Object geojson, Line line, Object disruptions,
			ArrayList<StopPoint> stop_points, String id) {
		super();
		this.direction = direction;
		this.name = name;
		this.links = links;
		this.physical_modes = physical_modes;
		this.is_frequence = is_frequence;
		this.geojson = geojson;
		this.line = line;
		this.disruptions = disruptions;
		this.stop_points = stop_points;
		this.id = id;
	} 



	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    public ArrayList<Object> getPhysical_modes() {
        return physical_modes;
    }

    public void setPhysical_modes(ArrayList<Object> physical_modes) {
        this.physical_modes = physical_modes;
    }

    public String getIs_frequence() {
        return is_frequence;
    }

    public void setIs_frequence(String is_frequence) {
        this.is_frequence = is_frequence;
    }

    public Object getGeojson() {
        return geojson;
    }

    public void setGeojson(Object geojson) {
        this.geojson = geojson;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Object getDisruptions() {
        return disruptions;
    }

    public void setDisruptions(Object disruptions) {
        this.disruptions = disruptions;
    }

    public ArrayList<StopPoint> getStop_points() {
        return stop_points;
    }

    public void setStop_points(ArrayList<StopPoint> stop_points) {
        this.stop_points = stop_points;
    }
}
