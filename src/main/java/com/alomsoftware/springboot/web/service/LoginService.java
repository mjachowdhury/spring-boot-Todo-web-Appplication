package com.alomsoftware.springboot.web.service;

import org.springframework.stereotype.Component;

//I have to tell Spring that its a Bean class
//Spring Bean
@Component
public class LoginService {

	public boolean validateUser(String userId, String password){
		
		//eg-( Moahmmed and Alom is the userId and password)
		//if this userId and password is only match then it will be true otherwise it be false
		return userId.equalsIgnoreCase("mohammed") 
				&& password.equalsIgnoreCase("alom");
	}
}
