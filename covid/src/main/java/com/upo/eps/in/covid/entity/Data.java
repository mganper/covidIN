package com.upo.eps.in.covid.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

public class Data {

    @Id
    public String id;

    private String region;

    private LocalDate date;

    private double temp;

    private double rain;

    private int cases;

    private int deaths;

    private int hospitalized;

    private int discharged;

    public Data() {
    }

    public Data(String region, LocalDate date, double temp, double rain, int cases, int deaths, int hospitalized, int discharged) {
        this.region = region;
        this.date = date;
        this.temp = temp;
        this.rain = rain;
        this.cases = cases;
        this.deaths = deaths;
        this.hospitalized = hospitalized;
        this.discharged = discharged;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getHospitalized() {
        return hospitalized;
    }

    public void setHospitalized(int hospitalized) {
        this.hospitalized = hospitalized;
    }

    public int getDischarged() {
        return discharged;
    }

    public void setDischarged(int discharged) {
        this.discharged = discharged;
    }
}
