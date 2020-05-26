package com.upo.eps.in.covid.service;

import com.upo.eps.in.covid.repository.DataRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    private final DataRepository dataRepository;

    public DataService(@Qualifier("DataRepository") DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }


}
