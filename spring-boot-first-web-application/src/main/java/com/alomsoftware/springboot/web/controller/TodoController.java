package com.alomsoftware.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alomsoftware.springboot.web.service.LoginService;
import com.alomsoftware.springboot.web.service.TodoService;

@Controller 
//Controller will pick up the class
@SessionAttributes("name") //with this tag during login time user name will be available through out the controller
//and i have to use this tag where ever i need for user name.
public class TodoController {

	//Injected Automatically
	@Autowired
	TodoService todoService;
 
	/**
	 * This method will get the name of the user will put the name in the model
	 * @param model
	 * @return will return the user to the list-todo page
	 */
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)	 
	public String showTodos(ModelMap model){
		String name = (String) model.get("name");
		model.put("todos", todoService.retrieveTodos(name)); 
		return "list-todos";
	}
	
	/**
	 * This method will work for adding todo stuffs.
	 * @param model
	 * @return to the add-todo page
	 */
	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)	 
	public String addTodo(ModelMap model){	 
		return "add-todo";
	}
	
	
}
