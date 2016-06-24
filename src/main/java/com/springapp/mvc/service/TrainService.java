package com.springapp.mvc.service;


import com.springapp.mvc.domain.Train;

import java.util.List;

public interface TrainService {

    public List<Train> showAll();

    void addTrain(Train train);

    void deleteById(Integer id);

    Train find(Integer id);
}
