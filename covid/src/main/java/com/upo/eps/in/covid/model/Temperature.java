package com.upo.eps.in.covid.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Temperature {

    private String nameRegion;

    private List<LocalDate> dateList;

    private List<Double> templList;

    private List<Double> rainList;

    public  Temperature(String nameRegion){
        this.nameRegion = nameRegion;
        this.dateList = new ArrayList<>();
        this.templList = new ArrayList<>();
        this.rainList = new ArrayList<>();
    }

    public String getNameRegion() {
        return nameRegion;
    }

    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }

    public List<LocalDate> getDateList() {
        return dateList;
    }

    public void setDateList(List<LocalDate> dateList) {
        this.dateList = dateList;
    }

    public List<Double> getTemplList() {
        return templList;
    }

    public void setTemplList(List<Double> templList) {
        this.templList = templList;
    }

    public List<Double> getRainList() {
        return rainList;
    }

    public void setRainList(List<Double> rainList) {
        this.rainList = rainList;
    }

    public void addTemp(double temp){
        templList.add(temp);
    }

    public void addRain(double rain){
        templList.add(rain);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature that = (Temperature) o;
        return Objects.equals(nameRegion, that.nameRegion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameRegion);
    }
}
