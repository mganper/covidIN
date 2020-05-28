package com.upo.eps.in.covid.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataDto {

    private String nameRegion;

    private List<LocalDate> dateList;

    private List<Double> templList;

    private List<Double> rainList;

    private List<Integer> casesList;

    private List<Integer> deathsList;

    private List<Integer> hospitalizListed;

    private List<Integer> dischargedList;

    public DataDto(String nameRegion) {
        this.nameRegion = nameRegion;
        this.dateList = new ArrayList<>();
        this.templList = new ArrayList<>();
        this.rainList = new ArrayList<>();
        this.casesList = new ArrayList<>();
        this.deathsList = new ArrayList<>();
        this.hospitalizListed = new ArrayList<>();
        this.dischargedList = new ArrayList<>();
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

    public List<Integer> getCasesList() {
        return casesList;
    }

    public void setCasesList(List<Integer> casesList) {
        this.casesList = casesList;
    }

    public List<Integer> getDeathsList() {
        return deathsList;
    }

    public void setDeathsList(List<Integer> deathsList) {
        this.deathsList = deathsList;
    }

    public List<Integer> getHospitalizListed() {
        return hospitalizListed;
    }

    public void setHospitalizListed(List<Integer> hospitalizListed) {
        this.hospitalizListed = hospitalizListed;
    }

    public List<Integer> getDischargedList() {
        return dischargedList;
    }

    public void setDischargedList(List<Integer> dischargedList) {
        this.dischargedList = dischargedList;
    }

    public void add(com.upo.eps.in.covid.entity.Data data){
        dateList.add(LocalDate.parse(data.getDate()));
        templList.add(data.getTemp());
        rainList.add(data.getRain());
        casesList.add(data.getCases());
        deathsList.add(data.getDeaths());
        hospitalizListed.add(data.getHospitalized());
        dischargedList.add(data.getDischarged());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataDto that = (DataDto) o;
        return Objects.equals(nameRegion, that.nameRegion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameRegion);
    }
}
