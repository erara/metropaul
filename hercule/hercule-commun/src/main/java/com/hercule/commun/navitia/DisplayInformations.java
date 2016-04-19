package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Antoine Lafuente on 11/01/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DisplayInformations {

    private String network;
    private String direction;
    private String commercial_mode;
    private String physical_mode;
    private String label;
    private String color;
    private String code;
    private String description;
    private ArrayList<String> equipments;
    private String headsign;

    public DisplayInformations(String network, String direction, String commercial_mode, String physical_mode, String label, String color, String code, String description, ArrayList<String> equipments, String headsign) {
        this.network = network;
        this.direction = direction;
        this.commercial_mode = commercial_mode;
        this.physical_mode = physical_mode;
        this.label = label;
        this.color = color;
        this.code = code;
        this.description = description;
        this.equipments = equipments;
        this.headsign = headsign;
    }

    public DisplayInformations() {
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCommercial_mode() {
        return commercial_mode;
    }

    public void setCommercial_mode(String commercial_mode) {
        this.commercial_mode = commercial_mode;
    }

    public String getPhysical_mode() {
        return physical_mode;
    }

    public void setPhysical_mode(String physical_mode) {
        this.physical_mode = physical_mode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getEquipments() {
        return equipments;
    }

    public void setEquipments(ArrayList<String> equipments) {
        this.equipments = equipments;
    }

    public String getHeadsign() {
        return headsign;
    }

    public void setHeadsign(String headsign) {
        this.headsign = headsign;
    }
}
