package com.springapp.mvc;

import com.springapp.mvc.domain.Stations;
import com.springapp.mvc.domain.Train;
import com.springapp.mvc.domain.User;
import com.springapp.mvc.service.RoutersService;
import com.springapp.mvc.service.Impl.RoutersServiceImpl;
import com.springapp.mvc.service.Impl.StationsServiceImpl;
import com.springapp.mvc.service.Impl.TrainServiceImpl;
import com.springapp.mvc.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@EnableScheduling
public class SignInController {
	
	@Autowired
	RoutersService routersService;
	
	@Autowired
	UserServiceImpl userService;

	@Autowired
	StationsServiceImpl stationsService;
	
	@Autowired
	TrainServiceImpl trainService;
	
	
	@ModelAttribute()
	public void addAttributes(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    

		model.addAttribute("user", new User());
		model.addAttribute("stationsList", stationsService.showAll());
		model.addAttribute("station",new Stations());
		model.addAttribute("currentLoginUser", userService.initializeUser(name));
		model.addAttribute("train", new Train());
		model.addAttribute("trainList", trainService.showAll());
		model.addAttribute("routeList", routersService.showAll());
		
	}
	
	public String getLoginName() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		return name;
	}
	
	

	@RequestMapping(value = { "/", "/signin" }, method = RequestMethod.GET)
	public String startApplication(Model model) {

		return "signin";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String startAdmin(Model model) {

		return "admin";
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String startMain(Model model) {
		
		String page;
		
		if (getLoginName().equals("admin")) {
			
			 page = "admin";
			
		} else {
			
			page = "main";
		}

		return page;
	}

	@RequestMapping(value = "/login?error=true", method = RequestMethod.GET)
	public String loginError(HttpServletRequest request, HttpServletResponse response) {

		return "signin";
	}

	@RequestMapping(value = "/registrationUser", method = RequestMethod.POST)
	public String registrationUser(@ModelAttribute("user") User user) {
		
		userService.save(user);
		
		return "signin";
	}

}