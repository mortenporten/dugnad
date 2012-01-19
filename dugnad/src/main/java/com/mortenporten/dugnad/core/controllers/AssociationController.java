package com.mortenporten.dugnad.core.controllers;

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
import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.persistence.Association;


@Controller
@RequestMapping("/association/")
@SessionAttributes("association")
public class AssociationController {

	@Autowired
	AssociationBo associationBo;
	@Autowired
	FestivalBo festivalBo;
	
	@RequestMapping("/associations") 
    public String listAssociations( ModelMap map) {
 
        map.put("associations",associationBo.getAllAssociations());
        map.put("association", new Association());
        
        return "associations";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAssociation(@Valid @ModelAttribute("association")
    	Association association, BindingResult result) {
    	
    	 if(result.hasErrors())  
    	    {  
    	        return "associations";  
    	    } 
    	
    	 	associationBo.addAssociation(association);
       
    	 
 
        return "redirect:associations";
    }
    
    @RequestMapping("/delete/{associationId}")
    public String deleteAssociation(@PathVariable("associationId")
    String associationId) {
 
       associationBo.deleteAssociation(associationId);
 
       return "redirect:/association/associations";
    }
    
    @RequestMapping(value = "/edit/{associationId}")
    public String editAssociation(@PathVariable("associationId")
    String associationId, ModelMap map) {
    	
    	
    	Association association = associationBo.findAssociationById(associationId);
    	map.addAttribute(association);
 
        return "editAssociation";
    }
    
    @RequestMapping(value = "/edit/edited")
    public String editedAssociation(@Valid @ModelAttribute("association")
    Association association, BindingResult result) {
    	
    	 if(result.hasErrors())  
    	    {  
    		 return "editAssociation";  
    	    }
    	 
    	associationBo.updateAssociation(association);
    	 
    	return "redirect:/association/associations";
    }
	
	
	
}
