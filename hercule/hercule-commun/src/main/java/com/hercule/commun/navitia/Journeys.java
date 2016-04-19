package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Created by Antoine Lafuente on 11/01/2015.
 */

public class Journeys {

    private ArrayList<Journey> journeys;
    private ArrayList<Object> links;
    private ArrayList<Object> tickets;
    private ArrayList<Object> exceptions;
    private ArrayList<Object> notes;

    public Journeys(ArrayList<Object> notes, ArrayList<Journey> journeys, ArrayList<Object> links, ArrayList<Object> tickets, ArrayList<Object> exceptions) {
        this.notes = notes;
        this.journeys = journeys;
        this.links = links;
        this.tickets = tickets;
        this.exceptions = exceptions;
    }

    public Journeys() {
    }


    public ArrayList<Object> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Object> tickets) {
        this.tickets = tickets;
    }

    public ArrayList<Object> getExceptions() {
        return exceptions;
    }

    public void setExceptions(ArrayList<Object> exceptions) {
        this.exceptions = exceptions;
    }

    public ArrayList<Object> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Object> notes) {
        this.notes = notes;
    }

    public ArrayList<Journey> getJourneys() {
        return journeys;
    }

    public void setJourneys(ArrayList<Journey> journeys) {
        this.journeys = journeys;
    }


    public ArrayList<Object> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Object> links) {
        this.links = links;
    }
}
