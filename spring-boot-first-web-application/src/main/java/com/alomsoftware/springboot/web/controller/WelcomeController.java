package com.alomsoftware.springboot.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

//i can use sessionAttributes or spring security eith way i can do 
@Controller 
//Controller will pick up the class
//@SessionAttributes("name")
//with this tag during login time user name will be available through out the controller
//and i have to use this tag where ever i need for user name.
public class WelcomeController {

	//Injected Automatically
	 
 
	@RequestMapping(value = "/", method = RequestMethod.GET)	 
	public String showWelcomePage(ModelMap model){
		model.put("name", getLoggedinUserName());
		return "welcome";
	}
	
	/**
	 * when this method will be called the user name and password from the bean and whoever 
	 * logged in
	 * @return
	 */
	private String getLoggedinUserName(){
		
		Object principal = 
				SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails){
			return ((UserDetails)principal).getUsername();
		}
		return principal.toString();
	}
	
	/*
	@RequestMapping(value = "/login", method = RequestMethod.POST)	 
	public String welcomeMessage(ModelMap model, @RequestParam String name, @RequestParam String password){
		
		boolean isvalidUser = loginServcie.validateUser(name, password);
		if(!isvalidUser){
			model.put("errorMessage", "Invalid Credentials.");
			return "login";
		}
			
		
		model.put("name", name);
		model.put("password", password);
		return "welcome";
	}*/
	
	
	//With @requestParam  and ModelMap tag i can pass the message through controller
	//eg-http://localhost:8080/opi?n=opi
	//it's an example
	/*@RequestMapping("/welcome")
	public String anotherMessage(@RequestParam String n, ModelMap m){
		m.put("name", n);
		return "welcome";
	}*/

}
