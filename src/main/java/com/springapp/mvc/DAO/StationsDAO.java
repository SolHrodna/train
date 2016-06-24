package com.springapp.mvc.DAO;

import com.springapp.mvc.domain.Stations;
import org.springframework.transaction.annotation.Transactional;


public interface StationsDAO extends MainDAO<Stations> {


    @Transactional
    void addNewStation(Stations station);
}
