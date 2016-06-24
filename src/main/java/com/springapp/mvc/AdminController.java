package com.springapp.mvc;

import com.springapp.mvc.consts.ErrorTypes;
import com.springapp.mvc.domain.*;
import com.springapp.mvc.service.ScheduleService;
import com.springapp.mvc.service.Impl.*;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;



@Controller
public class AdminController {

	@Autowired
	UserServiceImpl userService;

	@Autowired
	RoutersServiceImpl routersService;

	@Autowired
	StationsServiceImpl stationsService;

	@Autowired
	TrainServiceImpl trainService;

	@Autowired
	OrderServiceImpl orderService;
	
	@Autowired
	ScheduleService scheduleService;

	List<Stations> slist = new ArrayList<Stations>();
	

	@ModelAttribute()
	public void addAttributes(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		model.addAttribute("currentLoginUser", userService.initializeUser(name));
		model.addAttribute("station", new Stations());
		model.addAttribute("train", new Train());
		model.addAttribute("user", new User());
		model.addAttribute("trainList", trainService.showAll());
		model.addAttribute("stationsList", stationsService.showAll());
		model.addAttribute("routeList", routersService.showAll());
	}
	
	

	@RequestMapping(value = "/addStation", method = RequestMethod.POST)
	public String addStation(@ModelAttribute("station") Stations station, Model model) {
		
		int typeError = 0;

		try {
			stationsService.addStation(station);
		} catch (Exception e) {
			typeError = ErrorTypes.SAVE_ERROR;
		}

		String title = "Station";
		model.addAttribute("typeError", typeError);
		model.addAttribute("title", title);

		model.addAttribute("stationsList", stationsService.showAll());

		return "admin";

	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user, Model model) {
		
		int typeError = 0;
		
		try {
			userService.save(user);
		} catch (Exception e) {
			typeError = ErrorTypes.SAVE_ERROR;
		} 
		
		String title = "User";		
		model.addAttribute("typeError", typeError);
		model.addAttribute("title", title);

		return "admin";
	}

	@RequestMapping(value = "/addTrain", method = RequestMethod.POST)
	public String addTrain(@ModelAttribute("train") Train train, Model model) {
		
		int typeError = 0;
		
		try {
			trainService.addTrain(train);
		} catch (Exception e) {
			typeError = ErrorTypes.SAVE_ERROR;
		}
		
		String title = "Train";		
		
		
		model.addAttribute("trainList", trainService.showAll());
		model.addAttribute("typeError", typeError);
		model.addAttribute("title", title);
	

		return "admin";
	}

	@RequestMapping(value = "/addRoute", method = RequestMethod.POST)
	public String addNewRoute(@ModelAttribute("queue") String queueStation,
			@ModelAttribute("selectTrain") String selectTrain, Model model) {

		Routers router = new Routers();

		if (queueStation.equals("")) {

		}

		else {
			LinkedHashSet<Stations> stationsSet = new LinkedHashSet<Stations>();

			String[] queue = queueStation.split(" ");

			for (int i = 0; i < queue.length; i++) {

				Stations temp = stationsService.findById(Integer.valueOf(queue[i]));
				stationsSet.add(temp);

			}

			router.setTrain(trainService.find(Integer.valueOf(selectTrain)));
			router.setQueue(queueStation);
			router.setStationsSet(stationsSet);
			

			int typeError = 0;
			
			try {
				routersService.addRoute(router);
			} catch (Exception e) {
				typeError = ErrorTypes.SAVE_ERROR;
			}
			
			String title = "Route";		
			
			
			
			model.addAttribute("typeError", typeError);
			model.addAttribute("title", title);
		

			
		}

		return "admin";
	}
	
	@RequestMapping(value = "/addSchedule", method = RequestMethod.POST)
	public String addNewSchedule(@ModelAttribute("selectRoute") String selectRoute,@ModelAttribute("cost") String cost, @ModelAttribute("startTime") String startTime,
			@ModelAttribute("finishTime") String finishTime, @ModelAttribute("selectDay") String day, Model model){
		Schedule schedule = new Schedule();
		
	
		
		schedule.setRouters(routersService.find(Integer.valueOf(selectRoute)));
		schedule.setDay(day);
		schedule.setPrice(Float.parseFloat(cost));
		schedule.setStartRoute(java.sql.Time.valueOf(startTime+":00"));
		schedule.setFinishRoute(java.sql.Time.valueOf(finishTime+":00"));
		schedule.setPlacesuse(0);
		schedule.setStatus("Active");
		
		scheduleService.save(schedule);
		return "admin";
	}

}
