package com.mortenporten.dugnad.core.controllers;

import java.util.Map;

import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mortenporten.dugnad.core.bo.AssociationBo;
import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.persistence.Association;
import com.mortenporten.dugnad.core.persistence.Person;

@Controller
@RequestMapping("/person/*")
@SessionAttributes({"person","persons", "associationsMap"})
public class PersonController {

	@Autowired
	private PersonBo personBo;
	@Autowired
	private AssociationBo associationBo;
	 
	    
	 
	    @RequestMapping("/persons") 
	    public String listPersons( ModelMap map) {
	 
	        map.put("persons",personBo.findAllPersons());
	        map.put("person", new Person());
	        map.put("associationsMap", associationBo.getAllAssociationsMap());
	        
	        return "persons";
	    }
	    
	    @RequestMapping(value = "/add", method = RequestMethod.POST)
	    public String addPerson(@Valid @ModelAttribute("person")
	    Person person, BindingResult result) {
	    	
	    	if(result.hasErrors())  
	    	    {  
	    	        return "persons";  
	    	    } 
	    	
	    	Integer associationId = person.getAssociation().getAssociationId(); 
	    	if(associationId < 0){
	    		person.setAssociation(null);
	    	}
	    	 
	        personBo.addPerson(person);
	        
	        if(associationId > 0){
	    		associationBo.addPerson(Integer.toString(person.getPersonId()),
	    				Integer.toString(associationId));
	    		}
	    	 
	 
	        return "redirect:persons";
	    }
	    
	    @RequestMapping("/delete/{personId}")
	    public String deletePerson(@PathVariable("personId")
	    String personId) {
	 
	       personBo.deletePerson(personId);
	 
	       return "redirect:/person/persons";
	    }
	    
	    @RequestMapping(value = "/edit/{personId}")
	    public String editPerson(@PathVariable("personId")
	    String personId,ModelMap map) {
	    	
	    	
	    	Person person = personBo.findPersonById(personId);
	    	map.addAttribute(person);
	 
	        return "editPerson";
	    }
	    
	    @RequestMapping(value = "/edit/edited")
	    public String editedPerson(@Valid @ModelAttribute("person")
	    Person person, BindingResult result) {
	    	
	    	 if(result.hasErrors())  
	    	    {  
	    		 return "editPerson";  
	    	    }
	    	 
	    	
	    	 Integer associationId = person.getAssociation().getAssociationId(); 
		    	if(associationId < 0){
		    		associationBo.removePersonFromAssociation(Integer.toString(person.getPersonId()));
		    		person.setAssociation(null);
		    	}
		    	 
		    	personBo.updatePerson(person);
		        
		        if(associationId > 0){
		        	associationBo.removePersonFromAssociation(Integer.toString(person.getPersonId()));
		        	associationBo.addPerson(Integer.toString(person.getPersonId()),
		    				Integer.toString(associationId));
		    		}
	    	 
	    	 
	    	 return "redirect:/person/persons";
	    }
	    
	
}
