package com.mortenporten.dugnad.core.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.persistence.Festival;

@Controller
@RequestMapping("/festival")
public class FestivalController {

	@Autowired
	FestivalBo festivalBo;
	
	@RequestMapping("/festivals") 
    public String listFestivals(Map<String, Object> map) {
 
        map.put("festivals",festivalBo.getAllFestivals());
        map.put("festival", new Festival());
        
 
        return "festivals";
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addFestival(@Valid @ModelAttribute("festival") Festival festival,
			BindingResult result){
		
		 if(result.hasErrors())  
 	    {  
			 return "festivals";  
 	    }
		
		 try {
			 festivalBo.addFestival(festival); 
			
		} catch (DataIntegrityViolationException e) {
			return "festivals";
		}
		
		 
		return "redirect:festivals";
	}
	
	  @RequestMapping("/delete/{festivalId}")
	    public String deleteFestival(@PathVariable("festivalId")
	    String festivalId) {
	 
	       festivalBo.deleteFestival(festivalId);
	 
	        return "redirect:/festival/festivals";
	    }
	  
	  @RequestMapping("/choose/{festivalName}")
	    public String chooseFestival(@PathVariable("festivalName")
	    String festivalName) {
	 
		 
		  	 
	        return "redirect:/" + festivalName + "/duty/duties";
	    }
	
	
}
