package com.upo.eps.in.covid.model;

import com.upo.eps.in.covid.entity.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataDto {

    private String nameRegion;

    private int codeRegion;

    private List<LocalDate> dateList;

    private List<Double> tempList;

    private List<Double> rainList;

    private List<Integer> casesList;

    private List<Integer> deathsList;

    private List<Integer> hospitalizedList;

    private List<Integer> dischargedList;

    public DataDto(String nameRegion, int codeRegion) {
        this.nameRegion = nameRegion;
        this.codeRegion = codeRegion;
        this.dateList = new ArrayList<>();
        this.tempList = new ArrayList<>();
        this.rainList = new ArrayList<>();
        this.casesList = new ArrayList<>();
        this.deathsList = new ArrayList<>();
        this.hospitalizedList = new ArrayList<>();
        this.dischargedList = new ArrayList<>();
    }

    public String getNameRegion() {
        return nameRegion;
    }

    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }

    public int getCodeRegion() {
        return codeRegion;
    }

    public void setCodeRegion(int codeRegion) {
        this.codeRegion = codeRegion;
    }

    public List<LocalDate> getDateList() {
        return dateList;
    }

    public void setDateList(List<LocalDate> dateList) {
        this.dateList = dateList;
    }

    public List<Double> getTempList() {
        return tempList;
    }

    public void setTempList(List<Double> tempList) {
        this.tempList = tempList;
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

    public List<Integer> getHospitalizedList() {
        return hospitalizedList;
    }

    public void setHospitalizedList(List<Integer> hospitalizedList) {
        this.hospitalizedList = hospitalizedList;
    }

    public List<Integer> getDischargedList() {
        return dischargedList;
    }

    public void setDischargedList(List<Integer> dischargedList) {
        this.dischargedList = dischargedList;
    }

    public void add(Data data){
        dateList.add(LocalDate.parse(data.getDate()));
        tempList.add(data.getTemp());
        rainList.add(data.getRain());
        casesList.add(data.getCases());
        deathsList.add(data.getDeaths());
        hospitalizedList.add(data.getHospitalized());
        dischargedList.add(data.getDischarged());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataDto that = (DataDto) o;
        return Objects.equals(nameRegion, that.nameRegion) && codeRegion == that.codeRegion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameRegion);
    }
}
