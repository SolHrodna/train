package com.springapp.mvc.DAO;

import java.text.ParseException;
import java.util.List;

import com.springapp.mvc.domain.Temp;

public interface TempDAO extends MainDAO<Temp> {

	List<Temp> getThisDate();

	List<Temp> getAllTemp(String date);

}
