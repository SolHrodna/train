package com.springapp.mvc.DAO.Impl;

import com.springapp.mvc.DAO.RoutersDAO;
import com.springapp.mvc.domain.Routers;
import com.springapp.mvc.domain.Schedule;
import com.springapp.mvc.domain.Stations;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoutresDAOImpl extends MainDAOImpl<Routers> implements RoutersDAO {
	
	@Autowired
    private SessionFactory sessionFactory;
	
    public RoutresDAOImpl() {
        super(Routers.class);
    }


    @Override
    @Transactional
    public List<Routers> findRoute(String station, String date) {
    	
    	Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Routers.class);
    	
        List<Routers> result = criteria.createAlias("stationsSet", "stations").add(Restrictions.eq("stations.station", station)).list();
        
        /*List<Routers> inRoutes = this.showAll();
        List<Routers> routeIndex = new ArrayList<Routers>();

        for (Routers routers : inRoutes) {

            for (Stations stations : routers.getStationsSet()) {

                if (stations.getStation().equals(station)) {


                    routeIndex.add(routers);
                    break;


                }

            }

        }*/


        return result;
    }

}
