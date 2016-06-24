package com.springapp.mvc.service.Impl;

import com.springapp.mvc.DAO.StationsDAO;
import com.springapp.mvc.domain.Stations;
import com.springapp.mvc.service.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationsServiceImpl implements StationsService {

    @Autowired
    private StationsDAO stationsDAO;

    @Override
    public List<Stations> showAll() {
        return stationsDAO.showAll();
    }

    @Override
    public Stations findById(Integer id) {

        return stationsDAO.findById(id);
    }

    @Override
    public void addStation(Stations station) {

        stationsDAO.addNewStation(station);

    }

    @Override
    public void delete(Integer id) {

        stationsDAO.deletebyId(id);

    }
}
