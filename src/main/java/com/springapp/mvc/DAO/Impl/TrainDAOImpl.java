package com.springapp.mvc.DAO.Impl;


        import com.springapp.mvc.DAO.TrainDAO;
        import com.springapp.mvc.domain.Train;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class TrainDAOImpl extends MainDAOImpl<Train> implements TrainDAO {

    public TrainDAOImpl() {
        super(Train.class);
    }


}
