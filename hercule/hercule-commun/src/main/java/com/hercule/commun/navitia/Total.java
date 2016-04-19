package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Antoine Lafuente on 15/01/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Total {

    private String currency;
    private String value;

    public Total(String currency, String value) {
        this.currency = currency;
        this.value = value;
    }

    public Total(){
        this.currency = null;
        this.value = null;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
