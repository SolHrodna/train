package com.springapp.mvc.service;

import java.util.List;

import com.springapp.mvc.domain.Schedule;


public interface ScheduleService {
	
	public List<Schedule> showAll();
	public List<Schedule> showByDay(String day);
	public void save(Schedule schedule);
	public List<Schedule> findRoutes(String station, String date);
	public Schedule findById(Integer id);
	
}
