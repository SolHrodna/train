package com.springapp.mvc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springapp.mvc.DAO.HistoryDAO;
import com.springapp.mvc.domain.History;
import com.springapp.mvc.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService{
	
	@Autowired
	private HistoryDAO historyDAO;
	
	@Override
	public boolean isHistoryExsist(int id) {
		
		return historyDAO.isHistoryExsist(id);
	}

	@Override
	public void save(History history) {

		historyDAO.save(history);
		
	}

}
