package com.springapp.mvc.DAO;


import java.util.List;



import com.springapp.mvc.domain.Schedule;

public interface ScheduleDAO extends MainDAO<Schedule>{
	
	
	List<Schedule> showByDay(String day);

	List<Schedule> findRoutes(String station, String date);
	
}
