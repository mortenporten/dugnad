package com.mortenporten.dugnad.core.controllers;

import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.persistence.Person;

@Controller
public class PersonController {

	@Autowired
	private PersonBo personBo;
	
	
	 
	    
	 
	    @RequestMapping("/index") 
	    public String listPersons(Map<String, Object> map) {
	 
	        map.put("persons",personBo.findAllPersons());
	        map.put("person", new Person());
	        
	 
	        return "person";
	    }
	    
	    @RequestMapping(value = "/add", method = RequestMethod.POST)
	    public String addPerson(@ModelAttribute("person")
	    Person person, BindingResult result) {
	 
	    	
	        personBo.addPerson(person);
	 
	        return "redirect:/index";
	    }
	    
	    @RequestMapping("/delete/{personId}")
	    public String deleteContact(@PathVariable("personId")
	    String personId) {
	 
	       personBo.deletePerson(personId);
	 
	        return "redirect:/index";
	    }
	    
	
}
