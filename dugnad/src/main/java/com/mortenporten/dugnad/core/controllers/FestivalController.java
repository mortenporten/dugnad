package com.mortenporten.dugnad.core.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.persistence.Festival;
import com.mortenporten.dugnad.core.persistence.Person;
import com.mortenporten.dugnad.validators.DutyValidator;
import com.mortenporten.dugnad.validators.FestivalValidator;
import com.mortenporten.dugnad.validators.util.ValidatorUtil;

@Controller
@RequestMapping("/festival")
@SessionAttributes("festival")
public class FestivalController {

	@Autowired
	FestivalBo festivalBo;
	@Autowired
	FestivalValidator festivalValidator;
	@Autowired
	ValidatorUtil validatorUtil;
	
	
	
	@RequestMapping("/festivals") 
    public String listFestivals(Map<String, Object> map) {
 
        map.put("festivals",festivalBo.getAllFestivals());
        map.put("festival", new Festival());
        
 
        return "festivals";
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addFestival(@Valid @ModelAttribute("festival") Festival festival,
			BindingResult result){
		
		
		validatorUtil.setValidator(festivalValidator);
		validatorUtil.validate(festival, result);
		
		
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
	
	  @RequestMapping(value = "/edit/{festivalId}")
	    public String editPerson(@PathVariable("festivalId")
	    String festivalId,ModelMap map) {
	    	
	    	
	    	Festival festival = festivalBo.findFestivalById(festivalId);
	    	map.addAttribute(festival);
	 
	        return "editFestival";
	    }
	    
	    @RequestMapping(value = "/edit/edited")
	    public String editedPerson(@Valid @ModelAttribute("festival")
	    Festival festival, BindingResult result) {
	    	
	    	validatorUtil.setValidator(festivalValidator);
			validatorUtil.validate(festival, result);
			
	    	if(result.hasErrors())  
	    	    {  
	    		 return "editFestival";  
	    	    }
	    	 
	    	festivalBo.updateFestival(festival);
	    	 
	    	return "redirect:/festival/festivals";
	    }
}
