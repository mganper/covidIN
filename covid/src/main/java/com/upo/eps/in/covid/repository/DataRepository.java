package com.upo.eps.in.covid.repository;

import com.upo.eps.in.covid.entity.Data;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("DataRepository")
public interface DataRepository extends MongoRepository<Data, String> {
}
