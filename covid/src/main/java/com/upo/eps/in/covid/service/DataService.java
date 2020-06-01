package com.upo.eps.in.covid.service;

import com.upo.eps.in.covid.entity.Data;
import com.upo.eps.in.covid.model.DataDto;
import com.upo.eps.in.covid.repository.DataRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service("DataService")
public class DataService {

    private final DataRepository dataRepository;

    public DataService(@Qualifier("DataRepository") DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public List<DataDto> readData() {
        List<Data> dataList = dataRepository.findAll();
        return generateDataDtoList(dataList);
    }

    public DataDto readDataByCodeRegion(int codeRegion) {
        List<Data> dataList = dataRepository.findAllByCode(codeRegion);
        List<DataDto> dataDtoList = generateDataDtoList(dataList);
        return dataDtoList.get(0);
    }

    public DataDto getListForChartRegion(List<DataDto> dataDtoList, int codeRegion) {
        String region = dataRepository.findFirstByCode(codeRegion).getRegion();

        DataDto dataDto = new DataDto(region, codeRegion);
        List<LocalDate> dateList = new ArrayList<>();
        List<Double> templList = new ArrayList<>();
        List<Double> rainList = new ArrayList<>();
        List<Integer> casesList = new ArrayList<>();
        List<Integer> deathsList = new ArrayList<>();
        List<Integer> hospitalizedList = new ArrayList<>();
        List<Integer> dischargedList = new ArrayList<>();

        dataDto = dataDtoList.get(dataDtoList.indexOf(dataDto));

        int lastCases, lastDeath, lastHospitalized, lastDischarged;

        for (int i = 0; i < dataDto.getCasesList().size(); i += 2) {
            dateList.add(dataDto.getDateList().get(i));
            templList.add(dataDto.getTempList().get(i));
            rainList.add(dataDto.getRainList().get(i));

            if (i == 0) {
                casesList.add(dataDto.getCasesList().get(0));
                deathsList.add(dataDto.getDeathsList().get(0));
                hospitalizedList.add(dataDto.getHospitalizedList().get(0));
                dischargedList.add(dataDto.getDischargedList().get(0));
            } else {
                lastCases = dataDto.getCasesList().get(i) - dataDto.getCasesList().get(i - 2);
                lastDeath = dataDto.getDeathsList().get(i) - dataDto.getDeathsList().get(i - 2);
                lastHospitalized = dataDto.getHospitalizedList().get(i) - dataDto.getHospitalizedList().get(i - 2);
                lastDischarged = dataDto.getDischargedList().get(i) - dataDto.getDischargedList().get(i - 2);

                casesList.add(lastCases);
                deathsList.add(lastDeath);
                hospitalizedList.add(lastHospitalized);
                dischargedList.add(lastDischarged);
            }

        }

        dataDto = new DataDto(region, codeRegion);
        dataDto.setDateList(dateList);
        dataDto.setTempList(templList);
        dataDto.setRainList(rainList);
        dataDto.setCasesList(casesList);
        dataDto.setDeathsList(deathsList);
        dataDto.setHospitalizedList(hospitalizedList);
        dataDto.setDischargedList(dischargedList);

        return dataDto;
    }

    public DataDto getListForChartCountry(List<DataDto> dataDtoList) {
        DataDto dataDto = new DataDto("España", 0), aux;

        for (int i = 0; i < dataDtoList.size(); i++) {
            aux = getListForChartRegion(dataDtoList, dataDtoList.get(i).getCodeRegion());

            if (dataDto.getCasesList().isEmpty()) {
                dataDto.setCasesList(aux.getCasesList());
                dataDto.setDeathsList(aux.getDeathsList());
                dataDto.setHospitalizedList(aux.getHospitalizedList());
                dataDto.setDischargedList(aux.getDischargedList());
            } else {
                for (int j = 0; j < aux.getDateList().size(); j++) {
                    dataDto.getCasesList().set(j, aux.getCasesList().get(j) + dataDto.getCasesList().get(j));
                    dataDto.getDeathsList().set(j, aux.getDeathsList().get(j) + dataDto.getDeathsList().get(j));
                    dataDto.getHospitalizedList().set(j, aux.getHospitalizedList().get(j) + dataDto.getHospitalizedList().get(j));
                    dataDto.getDischargedList().set(j, aux.getDischargedList().get(j) + dataDto.getDischargedList().get(j));
                }
            }
        }

        return dataDto;
    }

    private List<DataDto> generateDataDtoList(List<Data> dataList) {
        List<DataDto> dataDtoList = new ArrayList<>();

        dataList.forEach(data -> {
            DataDto dat = new DataDto(data.getRegion(), data.getCode());

            if (dataDtoList.contains(dat)) {
                int i = dataDtoList.indexOf(dat);
                dat = dataDtoList.get(i);
            } else {
                dataDtoList.add(dat);
            }

            dat.add(data);
        });

        return dataDtoList;
    }

}
