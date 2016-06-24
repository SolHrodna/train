package com.springapp.mvc.service;

import java.util.List;

import com.springapp.mvc.domain.Temp;

public interface TempService {

	List<Temp> getThisDate();

	void delete(Temp temp);

	

}
