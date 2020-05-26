package com.upo.eps.in.covid.service;

import com.upo.eps.in.covid.entity.Data;
import com.upo.eps.in.covid.model.Temperature;
import com.upo.eps.in.covid.repository.DataRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("DataService")
public class DataService {

    private final DataRepository dataRepository;

    public DataService(@Qualifier("DataRepository") DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public List<Temperature> readTemperatures(){
        List<Data> dataList = dataRepository.findAll();
        return generateTemperatureList(dataList);
    }

    private List<Temperature> generateTemperatureList(List<Data> dataList) {
        List<Temperature> temperatureList = new ArrayList<>();

        dataList.forEach(data -> {
            Temperature temp = new Temperature(data.getRegion());

            if (temperatureList.contains(temp)){
                 int i = temperatureList.indexOf(temp);
                 temp = temperatureList.get(i);
            }

            temp.add(data);
        });

        return temperatureList;
    }
}
