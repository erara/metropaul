package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Created by Antoine Lafuente on 15/01/2015.
 */
public class Place {
    private String id;
    private String embedded_type;
    private String name;
    private String quality;
    private StopPoint stop_point;
    private StopArea stop_area;
    private NavitiaAddress address;
    private AdminRegion administrative_region;

    public Place() {
    }

    public Place(String embedded_type, String name, String quality, StopPoint stop_point, NavitiaAddress address, AdminRegion administrative_region, String id, StopArea stop_area) {
        this.id = id;
        this.embedded_type = embedded_type;
        this.name = name;
        this.quality = quality;
        this.stop_point = stop_point;
        this.stop_area = stop_area;
        this.address = address;
        this.administrative_region = administrative_region;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmbedded_type() {
        return embedded_type;
    }

    public void setEmbedded_type(String embedded_type) {
        this.embedded_type = embedded_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public StopPoint getStop_point() {
        return stop_point;
    }

    public void setStop_point(StopPoint stop_point) {
        this.stop_point = stop_point;
    }

    public NavitiaAddress getAddress() {
        return address;
    }

    public void setAddress(NavitiaAddress address) {
        this.address = address;
    }

    public StopArea getStop_area() {
        return stop_area;
    }

    public void setStop_area(StopArea stop_area) {
        this.stop_area = stop_area;
    }

    public AdminRegion getAdministrative_region() {
        return administrative_region;
    }

    public void setAdministrative_region(AdminRegion administrative_region) {
        this.administrative_region = administrative_region;
    }
}
