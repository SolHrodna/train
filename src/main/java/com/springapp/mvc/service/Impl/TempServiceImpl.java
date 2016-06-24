package com.springapp.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.springapp.mvc.DAO.TempDAO;
import com.springapp.mvc.domain.Temp;
import com.springapp.mvc.service.TempService;

@Service
public class TempServiceImpl implements TempService{
	
	 @Autowired
	    private TempDAO tempDAO;
	
	
	@Override
	public List<Temp> getThisDate() {
		
		return tempDAO.getThisDate();
	}

	@Override
	public void delete(Temp temp) {
		tempDAO.delete(temp);
		
	}

	public List<Temp> getAllTemp(String date) {
		
		return tempDAO.getAllTemp(date);
	}

	public void save(Temp temp) {
		
		tempDAO.save(temp);
	}

	public Temp findById(Integer id) {
		
		return tempDAO.findById(id);
	}

}
