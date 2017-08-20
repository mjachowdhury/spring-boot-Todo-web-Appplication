package com.alomsoftware.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alomsoftware.springboot.web.service.LoginService;

@Controller 
//Controller will pick up the class
public class LoginController {

	//Injected Automatically
	@Autowired
	LoginService loginServcie;
 
	@RequestMapping(value = "/login", method = RequestMethod.GET)	 
	public String loginMessage(ModelMap model){
		//model.put("name", name);
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)	 
	public String welcomeMessage(ModelMap model, @RequestParam String name, @RequestParam String password){
		
		boolean isvalidUser = loginServcie.validateUser(name, password);
		if(!isvalidUser){
			model.put("errorMessage", "Invalid Credentials.");
			return "login";
		}
			
		
		model.put("name", name);
		model.put("password", password);
		return "opi";
	}
	
	
	//With @requestParam  and ModelMap tag i can pass the message through controller
	//eg-http://localhost:8080/opi?n=opi
	@RequestMapping("/opi")
	public String anotherMessage(@RequestParam String n, ModelMap m){
		m.put("name", n);
		return "opi";
	}

}
