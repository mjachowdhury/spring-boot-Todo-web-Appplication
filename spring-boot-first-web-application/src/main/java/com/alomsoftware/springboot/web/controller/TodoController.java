package com.alomsoftware.springboot.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.alomsoftware.springboot.web.model.Todo;
import com.alomsoftware.springboot.web.service.TodoService;

@Controller 
//Controller will pick up the class
//@SessionAttributes("name") //with this tag during login time user name will be available through out the controller
//and i have to use this tag where ever i need for user name.
public class TodoController {

	//Injected Automatically
	@Autowired
	TodoService todoService;
	
	/**
	 * This method will work for date field
	 * InitBinder tag is need for date 
	 * WebDataBinder was used to bind for mapping date
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder){
		//Date -dd/MM/yyyy date format
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
 
	/**
	 * This method will get the name of the user will put the name in the model
	 * @param model
	 * @return will return the user to the list-todo page
	 */
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)	 
	public String showTodos(ModelMap model){
		String name = getLoggedinUserName(model);
		model.put("todos", todoService.retrieveTodos(name)); 
		return "list-todos";
	}


	private String getLoggedinUserName(ModelMap model) {
		Object principal = 
				SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails){
			return ((UserDetails)principal).getUsername();
		}
		return principal.toString();
		//return (String) model.get("name");
	}
	
	/**
	 * This method will work for adding todo stuffs.
	 * @param model
	 * @return to the add-todo page
	 */
	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)	 
	public String showAddTodo(ModelMap model){	
		model.addAttribute("todo",new Todo(0, getLoggedinUserName(model), "Add Desc." , new Date(), false));
		return "todo";
	}
	
	/**
	 * This method will connect directly with Todo class
	 * don't have to use tag requestparam string desc 
	 * added command bean todo
	 * @param model
	 * @param todo
	 * @return
	 */
	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)	 
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result){	
		if(result.hasErrors()){
			return "todo";
		}
		todoService.addTodo(getLoggedinUserName(model), todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:/list-todos";
	}
	
	
	/**
	 * This method is mapping for delete todos and redirect to list-todos
	 * and added tag requrestParam with id and called deleteTodo method
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)	 
	public String deleteTodo(@RequestParam int id){	 
		todoService.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)	 
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model){	 
		Todo todo= todoService.retrieveTodos(id);
		model.addAttribute("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)	 
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result){			
		
		if(result.hasErrors()){
			return "todo";
		}
		
		todo.setUser(getLoggedinUserName(model));
		todoService.updateTodo(todo);
		 
		return "redirect:/list-todos";
	}
	
	/* 
	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)	 
	public String addTodo(ModelMap model, @RequestParam String desc){	
		todoService.addTodo((String) model.get("name"), desc, new Date(), false);
		return "redirect:/list-todos";
	}*/
	
	
}
