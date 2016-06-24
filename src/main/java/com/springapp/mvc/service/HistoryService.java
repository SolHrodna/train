package com.springapp.mvc.service;



import com.springapp.mvc.domain.History;

public interface HistoryService {
	
	public boolean isHistoryExsist(int id);
	
	public void save(History history);

}
