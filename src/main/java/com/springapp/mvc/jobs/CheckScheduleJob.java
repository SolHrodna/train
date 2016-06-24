package com.springapp.mvc.jobs;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springapp.mvc.domain.History;
import com.springapp.mvc.domain.Routers;
import com.springapp.mvc.domain.Schedule;
import com.springapp.mvc.domain.Temp;
import com.springapp.mvc.service.HistoryService;
import com.springapp.mvc.service.RoutersService;
import com.springapp.mvc.service.ScheduleService;
import com.springapp.mvc.service.TempService;

@Component
public class CheckScheduleJob {
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private TempService tempService;


	@Scheduled(fixedRate = 60000)
	public void run() {
		
		
		
		
		
		List<Schedule> scheduleList = scheduleService.showByDay(LocalDate.now().getDayOfWeek().toString());
		List<Temp> tempList = tempService.getThisDate();
		
		for(Temp temp : tempList){
			ZoneId zoneId = ZoneId.of("Europe/Minsk");
			LocalTime time = LocalTime.now(zoneId);
			System.out.println(time);
			
			if(time.isAfter(temp.getSchedule().getStartRouteLoclaTime())){
				History history = new History();
				history.setDate(temp.getDate());
				history.setStartRoute(temp.getSchedule().getStartRoute());
				history.setFinishRoute(temp.getSchedule().getFinishRoute());
				history.setModel(temp.getSchedule().getRouters().getTrain().getModel());
				history.setNumber(temp.getSchedule().getRouters().getTrain().getNumber());
				history.setNumPlaces(temp.getSchedule().getRouters().getTrain().getNumPlaces());
				history.setPlaces(temp.getPlaces());
				history.setPrice(temp.getSchedule().getPrice());
				history.setShortName(temp.getSchedule().getRouters().getShortName());
				history.setScheduleId(temp.getSchedule().getId());
				historyService.save(history);
		
				tempService.delete(temp);
			}
			
		}
		
	

		for(Schedule schedule : scheduleList){
			
			ZoneId zoneId = ZoneId.of("Europe/Minsk");
			LocalTime time = LocalTime.now(zoneId);
			LocalTime timeRoute = schedule.getStartRouteLoclaTime();
			System.out.println(time);
			
			
			
			if(schedule.getStatus().equals("Active") && time.isAfter(timeRoute) && !historyService.isHistoryExsist(schedule.getId())){
				History history = new History();
				history.setDate(Calendar.getInstance().getTime());
				history.setStartRoute(schedule.getStartRoute());
				history.setFinishRoute(schedule.getFinishRoute());
				history.setModel(schedule.getRouters().getTrain().getModel());
				history.setNumber(schedule.getRouters().getTrain().getNumber());
				history.setNumPlaces(schedule.getRouters().getTrain().getNumPlaces());
				history.setPlaces(schedule.getPlacesuse());
				history.setPrice(schedule.getPrice());
				history.setShortName(schedule.getRouters().getShortName());
				history.setScheduleId(schedule.getId());
				historyService.save(history);
	
				scheduleService.save(schedule);
				
			}
		}
		
	}

}
