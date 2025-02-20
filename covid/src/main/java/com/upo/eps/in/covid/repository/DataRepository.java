package com.upo.eps.in.covid.repository;

import com.upo.eps.in.covid.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("DataRepository")
public interface DataRepository extends JpaRepository<Data, Serializable> {

    List<Data> findAllByCode(int coderegion);

    Data findFirstByCode(int coderegion);
}
