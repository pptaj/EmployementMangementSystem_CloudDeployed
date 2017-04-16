package com.ems.controllers;


import com.ems.pojo.Employee;
import com.ems.pojo.Manager;
import com.ems.pojo.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@ModelAttribute("person") Person person,HttpServletRequest request) {
		return "/index.jsp";
	}
	
	@RequestMapping(value = "/createUser.htm", method = RequestMethod.GET)
	public String adminCreateUserPage(@ModelAttribute("employee") Employee employee) {
		return "registerUser";
	}
	
	@RequestMapping(value = "/registerManager.htm", method = RequestMethod.GET)
	public String adminCreateManagePage(@ModelAttribute("manager") Manager manager) {
		return "registerManager";
	}
}