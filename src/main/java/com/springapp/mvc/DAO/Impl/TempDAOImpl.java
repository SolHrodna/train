package com.springapp.mvc.DAO.Impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.springapp.mvc.DAO.TempDAO;
import com.springapp.mvc.domain.Temp;

@Component
public class TempDAOImpl extends MainDAOImpl<Temp> implements TempDAO {

	public TempDAOImpl(){
		super(Temp.class);
	}
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Temp> getThisDate() {
		
		Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Temp.class);
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date lo = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date hi = calendar.getTime();
		
		return criteria.add(Restrictions.between("date", lo, hi)).list();
	}

	@Override
	@Transactional
	public List<Temp> getAllTemp(String date) {
		Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Temp.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        
       
		
        return criteria.add(Restrictions.eq("date", java.sql.Date.valueOf(date))).list();
	}
	
}
