package com.springapp.mvc;

import com.springapp.mvc.domain.Order;
import com.springapp.mvc.domain.Routers;
import com.springapp.mvc.domain.Schedule;
import com.springapp.mvc.domain.Stations;
import com.springapp.mvc.domain.Temp;
import com.springapp.mvc.domain.User;
import com.springapp.mvc.service.ScheduleService;
import com.springapp.mvc.service.TempService;
import com.springapp.mvc.service.Impl.OrderServiceImpl;
import com.springapp.mvc.service.Impl.RoutersServiceImpl;
import com.springapp.mvc.service.Impl.StationsServiceImpl;
import com.springapp.mvc.service.Impl.TempServiceImpl;
import com.springapp.mvc.service.Impl.TrainServiceImpl;
import com.springapp.mvc.service.Impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;

@Controller
public class MainController {
	
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	UserServiceImpl userService;

	@Autowired
	RoutersServiceImpl routersService;

	@Autowired
	StationsServiceImpl stationsService;

	@Autowired
	OrderServiceImpl orderService;
	
	@Autowired
	TrainServiceImpl trainService;
	
	@Autowired
	TempServiceImpl tempService;

	String stationFrom, stationTo, selectDate;
	ArrayList<Routers> routerOrder = new ArrayList<Routers>();

	@ModelAttribute()
	public void addLoginUser(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		model.addAttribute("currentLoginUser", userService.initializeUser(name));
		model.addAttribute("stationsList", stationsService.showAll());
		model.addAttribute("trainList", trainService.showAll());
		model.addAttribute("routeList", routersService.showAll());

	}

	public String getLoginName() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		return name;
	}

	@RequestMapping(value = "/payOrder/{id}", method = RequestMethod.GET)
	public String payOrder(@PathVariable Integer id, Model model) {

		Order orderPay = orderService.find(id);

		orderPay.setStatus("Paid");

		orderService.addOrder(orderPay);

		return startOrder(model);
	}

	@RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.GET)
	public String deleteOrder(@PathVariable Integer id, Model model) {

		Order orderPay = orderService.find(id);

		orderPay.setStatus("Deleted");

		orderService.addOrder(orderPay);

		return startOrder(model);
	}

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String startHistory(Model model) {

		User user = userService.initializeUser(getLoginName());

		LinkedHashSet<Order> orders = new LinkedHashSet<Order>();

		for (Order order : user.getOrderSet()) {

			

				Routers routers = routersService.sortRoute(routersService.find(order.getRouters().getRouteId()));
				order.setRouters(routers);
				orders.add(order);

		

		}

		model.addAttribute("ordersResult", orders);

		return "history";
	}

	@RequestMapping(value = "/orderPage", method = RequestMethod.GET)
	public String startOrder(Model model) {

		User user = userService.initializeUser(getLoginName());

		LinkedHashSet<Order> orders = new LinkedHashSet<Order>();

		for (Order order : user.getOrderSet()) {

			if (order.getStatus().equals("Not Paid")) {

				Routers routers = routersService.sortRoute(routersService.find(order.getRouters().getRouteId()));
				order.setRouters(routers);
				orders.add(order);

			}

		}

		model.addAttribute("ordersResult", orders);

		return "order";
	}

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public String findRouters(@ModelAttribute("selectTo") String nameStationTo,
			@ModelAttribute("selectFrom") String nameStationFrom, @ModelAttribute("selectDate") String date, Model model) {

		stationFrom = nameStationFrom;
		stationTo = nameStationTo;
		

		List<Schedule> list = scheduleService.findRoutes(nameStationFrom, date);
		List<Schedule> result = new ArrayList<Schedule>();
		List<Temp> temps = tempService.getAllTemp(date);
		
		
		for(Schedule schedule : list){
				for(Temp temp : temps){
				if(temp.getSchedule().getId() == schedule.getId()){
					result.remove(schedule);
				}
			}
		}
		
		
		
		for (Schedule schedule : list) {
			
			
			
			

			int first = -1;
			int last = -1;
			List<Stations> stations = schedule.getRouters().getStationByOrder();

			for (int i = 0; i < stations.size(); i++) {


				if (stations.get(i).getStation().equals(nameStationFrom)) {

					first = i;

				} else if (stations.get(i).getStation().equals(nameStationTo)) {

					last= i;

				}

			}
			
			
				
	
				if ((first <= last) && (first != -1) && (last != -1) ) {
					
					
					
					result.add(schedule);
					
				
				
				}
				
				
			
				
			
			
				

			}
		
		
		

		

		model.addAttribute("result", result);
		model.addAttribute("date", date);
		model.addAttribute("tempResult", temps);
		return "router";
	}

	@RequestMapping(value = "/orderSchedule/{id}", method = RequestMethod.GET)
	public String makeOrder(@PathVariable String id, Model model) {
		
		String[] requset = id.split(":");
		
		Temp temp = new Temp();
		
		User user = userService.initializeUser(getLoginName());
		
		temp.setSchedule(scheduleService.findById(Integer.parseInt(requset[0])));
		
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		temp.setDate(java.sql.Date.valueOf(requset[1]));
		temp.setPlaces(1);
		
		temp.setUser(user);

		tempService.save(temp);
		return "main";
	}
	
	@RequestMapping(value = "/orderTemp/{id}", method = RequestMethod.GET)
	public String makeReadyOrde(@PathVariable String id, Model model) {
		
		User user = userService.initializeUser(getLoginName());
		
		String[] requset = id.split(":");
		
		Temp temp = tempService.findById(Integer.valueOf(requset[0]));
		
		temp.setPlaces(temp.getPlaces() + 1);
		
		temp.setUser(user);
		
		tempService.save(temp);
		
		return "main";
	}
	
	@RequestMapping(value = "/schedulePage", method = RequestMethod.GET)
	public String schedulePage(){
		
	
		return "schedule";
		
	}
	
	@RequestMapping(value = "/showSchedule", method = RequestMethod.POST)
	public String showSchedule(@ModelAttribute("selectDate") String date, Model model){
		
		if (date != "" || date != null){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate dt = LocalDate.parse(date, formatter);
	        
	        List<Schedule> result = scheduleService.showByDay(dt.getDayOfWeek().toString());
			
			model.addAttribute("result", result);
		}
		
        
		
		return "schedule";
	}

}
