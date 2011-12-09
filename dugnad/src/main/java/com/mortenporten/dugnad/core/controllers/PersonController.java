package com.mortenporten.dugnad.core.controllers;

import java.util.Map;

import javax.validation.Valid;



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
@RequestMapping("/person/*")
public class PersonController {

	@Autowired
	private PersonBo personBo;
	
	
	 
	    
	 
	    @RequestMapping("/persons") 
	    public String listPersons(Map<String, Object> map) {
	 
	        map.put("persons",personBo.findAllPersons());
	        map.put("person", new Person());
	        
	 
	        return "persons";
	    }
	    
	    @RequestMapping(value = "/add", method = RequestMethod.POST)
	    public String addPerson(@Valid @ModelAttribute("person")
	    Person person, BindingResult result) {
	    	
	    	 if(result.hasErrors())  
	    	    {  
	    	        return "persons";  
	    	    } 
	    	
	        personBo.addPerson(person);
	 
	        return "redirect:persons";
	    }
	    
	    @RequestMapping("/delete/{personId}")
	    public String deleteContact(@PathVariable("personId")
	    String personId) {
	 
	       personBo.deleteAllPersonDutiesAndPerson(personId, 
	    		   personBo.getPersonDutiesId(personId));
	 
	        return "redirect:/person/persons";
	    }
	    
	
}
