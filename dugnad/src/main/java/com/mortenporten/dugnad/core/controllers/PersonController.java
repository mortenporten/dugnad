package com.mortenporten.dugnad.core.controllers;

import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.persistence.Person;

@Controller
public class PersonController {

	@Autowired
	private PersonBo personBo;
	
	
	 
	    
	 
	    @RequestMapping("/index") 
	    public String listContacts(Map<String, Object> map) {
	 
	        map.put("persons",personBo.findAllPersons());
	        map.put("person", new Person());
	        
	 
	        return "person";
	    }
	    
	
}
