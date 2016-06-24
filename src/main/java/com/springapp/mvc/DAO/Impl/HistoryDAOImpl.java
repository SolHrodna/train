package com.springapp.mvc.DAO.Impl;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.springapp.mvc.DAO.HistoryDAO;
import com.springapp.mvc.domain.History;
import com.springapp.mvc.domain.Schedule;

@Component
public class HistoryDAOImpl extends MainDAOImpl<History> implements HistoryDAO{

	public HistoryDAOImpl(){
		super(History.class);
	}
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	@Transactional
	public boolean isHistoryExsist(int id) {
		
		Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(History.class);
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date lo = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date hi = calendar.getTime();
		
        
        
		return criteria.add(Restrictions.eq("scheduleId", id)).add(Restrictions.between("date", lo, hi)).list().size()>0;
	}
	
}
