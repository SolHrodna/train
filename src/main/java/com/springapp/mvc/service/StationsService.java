package com.springapp.mvc.service;

import com.springapp.mvc.domain.Stations;

import java.util.List;

public interface StationsService {

    public List<Stations> showAll();


    Stations findById(Integer id);

    void addStation(Stations station);

    void delete(Integer id);
}
