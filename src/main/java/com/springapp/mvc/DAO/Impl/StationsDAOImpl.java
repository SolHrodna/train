package com.springapp.mvc.DAO.Impl;


import com.springapp.mvc.DAO.StationsDAO;
import com.springapp.mvc.domain.Stations;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class StationsDAOImpl extends MainDAOImpl<Stations> implements StationsDAO {

    public StationsDAOImpl() {
        super(Stations.class);
    }


    @Override
    @Transactional
    public void addNewStation(Stations station) {

     
            this.save(station);

        }

    
}
