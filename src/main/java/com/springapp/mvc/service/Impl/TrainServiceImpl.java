package com.springapp.mvc.service.Impl;

import com.springapp.mvc.DAO.TrainDAO;
import com.springapp.mvc.domain.Train;
import com.springapp.mvc.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainDAO trainDAO;

    @Override
    public List<Train> showAll() {
        return trainDAO.showAll();
    }

    @Override
    public void addTrain(Train train) {
        trainDAO.save(train);
    }

    @Override
    public void deleteById(Integer id) {
        trainDAO.deletebyId(id);
    }

    @Override
    public Train find(Integer id) {

        return trainDAO.findById(id);
    }
}
