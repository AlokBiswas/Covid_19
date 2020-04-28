package com.alokbiswas.covid_19.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mundial {

    @SerializedName("cases")
    @Expose
    private String cases;

    @SerializedName("deaths")
    @Expose
    private String deaths;

    @SerializedName("recovered")
    @Expose
    private String recovered;

    public String getCases() {
        return cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getRecovered() {
        return recovered;
    }
}
