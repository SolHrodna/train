package com.springapp.mvc.service.Impl;

import com.springapp.mvc.DAO.RoutersDAO;
import com.springapp.mvc.domain.Routers;
import com.springapp.mvc.domain.Stations;
import com.springapp.mvc.service.RoutersService;
import com.springapp.mvc.service.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoutersServiceImpl implements RoutersService {

    @Autowired
    private RoutersDAO routersDAO;

    @Autowired
    private StationsService stationsService;

    @Override
    public List<Routers> showAll() {

        return routersDAO.showAll();

    }

    @Override
    public void addRoute(Routers routers) {

        routersDAO.save(routers);
    }

    @Override
    public List<Routers> findRouters(String station, String date) {

        return routersDAO.findRoute(station, date);

    }

    @Override
    public void deleteById(Integer id) {

        routersDAO.deletebyId(id);

    }

    @Override
    public Routers find(Integer id) {

        return routersDAO.findById(id);

    }

    @Override
    public List<Routers> sortRoutes(Set<Routers> routerses) {

        List<Routers> routersList = new ArrayList<Routers>();

        for (Routers routers : routerses) {

            String[] queue = routers.getQueue().split(" ");
            LinkedHashSet<Stations> stationses = new LinkedHashSet<Stations>();

            for (int i = 0; i < queue.length; i++) {


                Stations temp = stationsService.findById(Integer.valueOf(queue[i]));
                stationses.add(temp);

            }

            routers.setStationsSet(stationses);
            routersList.add(routers);

        }
        return routersList;
    }

    @Override
    public Routers sortRoute(Routers router) {

        Routers routers = router;
        String[] queue = router.getQueue().split(" ");
        LinkedHashSet<Stations> stationses = new LinkedHashSet<Stations>();

        for (int i = 0; i < queue.length; i++) {


            Stations temp = stationsService.findById(Integer.valueOf(queue[i]));
            stationses.add(temp);

        }

        routers.setStationsSet(stationses);
        return routers;
    }
}
