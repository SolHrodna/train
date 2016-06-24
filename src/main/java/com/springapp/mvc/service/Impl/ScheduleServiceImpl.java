package com.springapp.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springapp.mvc.DAO.ScheduleDAO;
import com.springapp.mvc.DAO.TempDAO;
import com.springapp.mvc.domain.Schedule;
import com.springapp.mvc.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService{
	
	@Autowired
	private ScheduleDAO scheduleDAO;
	
	@Override
	public List<Schedule> showAll() {
		
		return scheduleDAO.showAll();
	}

	@Override
	public List<Schedule> showByDay(String day) {
		
		return scheduleDAO.showByDay(day);
	}

	@Override
	public void save(Schedule schedule) {
		scheduleDAO.save(schedule);
		
	}

	@Override
	public List<Schedule> findRoutes(String station, String date) {
		
		return scheduleDAO.findRoutes(station, date);
	}

	@Override
	public Schedule findById(Integer id) {
		
		return scheduleDAO.findById(id);
	}

}
