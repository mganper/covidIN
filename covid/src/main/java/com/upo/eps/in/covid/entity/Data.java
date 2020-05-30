package com.upo.eps.in.covid.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "data")
public class Data implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "region")
    private String region;

    @Column(name = "code")
    private int code;

    @Column(name = "date")
    private String date;

    @Column(name = "temp")
    private double temp;

    @Column(name = "rain")
    private double rain;

    @Column(name = "cases")
    private int cases;

    @Column(name = "deaths")
    private int deaths;

    @Column(name = "hospitalized")
    private int hospitalized;

    @Column(name = "discharged")
    private int discharged;

    public Data() {
    }

    public Data(int id, String region, int code, String date, double temp, double rain, int cases, int deaths, int hospitalized, int discharged) {
        this.id = id;
        this.region = region;
        this.code = code;
        this.date = date;
        this.temp = temp;
        this.rain = rain;
        this.cases = cases;
        this.deaths = deaths;
        this.hospitalized = hospitalized;
        this.discharged = discharged;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int codeRegion) {
        this.code = codeRegion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
