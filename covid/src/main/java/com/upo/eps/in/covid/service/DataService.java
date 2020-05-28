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

    public List<DataDto> readTemperatures(){
        List<com.upo.eps.in.covid.entity.Data> dataList = dataRepository.findAll();
        return generateDataDtoList(dataList);
    }

    private List<DataDto> generateDataDtoList(List<com.upo.eps.in.covid.entity.Data> dataList) {
        List<DataDto> dataDtoList = new ArrayList<>();

        dataList.forEach(data -> {
            DataDto dat = new DataDto(data.getRegion());

            if (dataDtoList.contains(dat)){
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
