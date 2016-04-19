package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Antoine Lafuente on 11/01/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JourneysRequest {

    private String from;
    private String to;
    private String datetime;
    private String datetime_represents;
    private ArrayList<String> forbidden_uris[];
    private ArrayList<String> first_section_mode[];
    private ArrayList<String> last_section_mode[];
    private String max_duration_to_pt;
    private String walking_speed;
    private String bike_speed;
    private String bss_speed;
    private String car_speed;
    private String min_nb_journeys;
    private String max_nb_journeys;
    private String count;
    private String max_nb_tranfers;
    private String disruption_active;
    private String max_duration;
    private String wheelchair;
    private String show_codes;
    private String debug;

    public JourneysRequest(String from, String to, String datetime, String datetime_represents, ArrayList<String>[] forbidden_uris, ArrayList<String>[] first_section_mode, ArrayList<String>[] last_section_mode, String max_duration_to_pt, String walking_speed, String bike_speed, String bss_speed, String car_speed, String min_nb_journeys, String max_nb_journeys, String count, String max_nb_tranfers, String disruption_active, String max_duration, String wheelchair, String show_codes, String debug) {
        this.from = from;
        this.to = to;
        this.datetime = datetime;
        this.datetime_represents = datetime_represents;
        this.forbidden_uris = forbidden_uris;
        this.first_section_mode = first_section_mode;
        this.last_section_mode = last_section_mode;
        this.max_duration_to_pt = max_duration_to_pt;
        this.walking_speed = walking_speed;
        this.bike_speed = bike_speed;
        this.bss_speed = bss_speed;
        this.car_speed = car_speed;
        this.min_nb_journeys = min_nb_journeys;
        this.max_nb_journeys = max_nb_journeys;
        this.count = count;
        this.max_nb_tranfers = max_nb_tranfers;
        this.disruption_active = disruption_active;
        this.max_duration = max_duration;
        this.wheelchair = wheelchair;
        this.show_codes = show_codes;
        this.debug = debug;
    }

    public HashMap<String, Object> toMap(){
        HashMap <String, Object> map = new HashMap<String,Object>();
        map.put("from",from);
        map.put("to",to);
        map.put("datetime",datetime);
        map.put("datetime_represents",datetime_represents);
        map.put("forbidden_uris[]",forbidden_uris);
        map.put("first_section_mode[]",first_section_mode);
        map.put("last_section_mode[]",last_section_mode);
        map.put("max_duration_to_pt",max_duration_to_pt);
        map.put("walking_speed",walking_speed);
        map.put("bike_speed",bike_speed);
        map.put("bss_speed",bss_speed);
        map.put("car_speed",car_speed);
        map.put("min_nb_journeys",min_nb_journeys);
        map.put("max_nb_journeys",max_nb_journeys);
        map.put("count",count);
        map.put("max_nb_tranfers",max_nb_tranfers);
        map.put("disruption_active",disruption_active);
        map.put("max_duration",max_duration);
        map.put("wheelchair",wheelchair);
        map.put("show_codes",show_codes);
        map.put("debug",debug);
        return map;
    }


    public JourneysRequest() {
        from=null;
        to=null;
        datetime=null;
        datetime_represents=null;
        forbidden_uris=null;
        first_section_mode=null;
        last_section_mode=null;
        max_duration_to_pt=null;
        walking_speed=null;
        bike_speed=null;
        bss_speed=null;
        car_speed=null;
        min_nb_journeys=null;
        max_nb_journeys=null;
        count=null;
        max_nb_tranfers=null;
        disruption_active=null;//perturbations
        max_duration=null;
        wheelchair=null;
        show_codes=null;
        debug=null;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDatetime_represents() {
        return datetime_represents;
    }

    public void setDatetime_represents(String datetime_represents) {
        this.datetime_represents = datetime_represents;
    }

    public ArrayList<String>[] getForbidden_uris() {
        return forbidden_uris;
    }

    public void setForbidden_uris(ArrayList<String>[] forbidden_uris) {
        this.forbidden_uris = forbidden_uris;
    }

    public ArrayList<String>[] getFirst_section_mode() {
        return first_section_mode;
    }

    public void setFirst_section_mode(ArrayList<String>[] first_section_mode) {
        this.first_section_mode = first_section_mode;
    }

    public ArrayList<String>[] getLast_section_mode() {
        return last_section_mode;
    }

    public void setLast_section_mode(ArrayList<String>[] last_section_mode) {
        this.last_section_mode = last_section_mode;
    }

    public String getMax_duration_to_pt() {
        return max_duration_to_pt;
    }

    public void setMax_duration_to_pt(String max_duration_to_pt) {
        this.max_duration_to_pt = max_duration_to_pt;
    }

    public String getWalking_speed() {
        return walking_speed;
    }

    public void setWalking_speed(String walking_speed) {
        this.walking_speed = walking_speed;
    }

    public String getBike_speed() {
        return bike_speed;
    }

    public void setBike_speed(String bike_speed) {
        this.bike_speed = bike_speed;
    }

    public String getBss_speed() {
        return bss_speed;
    }

    public void setBss_speed(String bss_speed) {
        this.bss_speed = bss_speed;
    }

    public String getCar_speed() {
        return car_speed;
    }

    public void setCar_speed(String car_speed) {
        this.car_speed = car_speed;
    }

    public String getMin_nb_journeys() {
        return min_nb_journeys;
    }

    public void setMin_nb_journeys(String min_nb_journeys) {
        this.min_nb_journeys = min_nb_journeys;
    }

    public String getMax_nb_journeys() {
        return max_nb_journeys;
    }

    public void setMax_nb_journeys(String max_nb_journeys) {
        this.max_nb_journeys = max_nb_journeys;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getMax_nb_tranfers() {
        return max_nb_tranfers;
    }

    public void setMax_nb_tranfers(String max_nb_tranfers) {
        this.max_nb_tranfers = max_nb_tranfers;
    }

    public String isDisruption_active() {
        return disruption_active;
    }

    public void setDisruption_active(String disruption_active) {
        this.disruption_active = disruption_active;
    }

    public String getMax_duration() {
        return max_duration;
    }

    public void setMax_duration(String max_duration) {
        this.max_duration = max_duration;
    }

    public String isWheelchair() {
        return wheelchair;
    }

    public void setWheelchair(String wheelchair) {
        this.wheelchair = wheelchair;
    }

    public String isShow_codes() {
        return show_codes;
    }

    public void setShow_codes(String show_codes) {
        this.show_codes = show_codes;
    }

    public String isDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }
}
