package com.springapp.mvc.DAO.Impl;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.springapp.mvc.DAO.ScheduleDAO;
import com.springapp.mvc.domain.Routers;
import com.springapp.mvc.domain.Schedule;

@Component
public class ScheduleDAOImpl extends MainDAOImpl<Schedule> implements ScheduleDAO{
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public ScheduleDAOImpl() {
		super(Schedule.class);
		
	}

	@Override
	@Transactional
	public List<Schedule> showByDay(String day) {
		
		Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Schedule.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	
		return criteria.add(Restrictions.eq("day", day)).list();
	}

	@Override
	@Transactional
	public List<Schedule> findRoutes(String station, String date) {
		
		Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Schedule.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        
        List<Schedule> result = new ArrayList<Schedule>();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dt = LocalDate.parse(date, formatter);
        
        ZoneId zoneId = ZoneId.of("Europe/Minsk");
		LocalTime time = LocalTime.now(zoneId);
		
		LocalDate dateNow = LocalDate.now(zoneId);
		
        if(dateNow.equals(dt)){
        	result = criteria.add(Restrictions.eq("status", "Active")).add(Restrictions.eq("day", dt.getDayOfWeek().toString())).add(Restrictions.gt("startRoute", Time.valueOf(time))).list();
        } else {
        	result = criteria.add(Restrictions.eq("status", "Active")).add(Restrictions.eq("day", dt.getDayOfWeek().toString())).list();
        }

        
        
        
		return result;
	}
}
