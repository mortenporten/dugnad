package com.mortenporten.dugnad.core.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


import com.mortenporten.dugnad.core.bo.DutyBo;
import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Festival;
import com.mortenporten.dugnad.core.persistence.Person;
import com.mortenporten.dugnad.validators.DutyValidator;
import com.mortenporten.dugnad.validators.util.ValidatorUtil;

@Controller
@RequestMapping("/{festivalName}/duty")
@SessionAttributes({"duties", "duty", "festivalName"})
public class DutyController {

	@Autowired
	DutyBo dutyBo;
	@Autowired
	FestivalBo festivalBo;
	@Autowired
	PersonBo personBo;
	@Autowired
	DutyValidator dutyValidator;
	@Autowired
	ValidatorUtil validatorUtil;
	

	
	@RequestMapping("/duties") 
    public String listDuties(@PathVariable("festivalName")
    String festivalName, Map<String, Object> map) {
 
		map.put("duties",festivalBo.getAllDuties(festivalName));
        map.put("duty", new Duty());
        map.put("persons", personBo.getAllPersonMap());
        map.put("festivalName", festivalName);
        
        return "duties";
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addDuty( @Valid @ModelAttribute("duty")
    Duty duty, BindingResult result, @PathVariable("festivalName")
    String festivalName) {
    	
		validatorUtil.setValidator(dutyValidator);
		validatorUtil.validate(duty, result);
		
		
		if(result.hasErrors())  
    	    {  
    	       return "duties";  
    	    } 
    	
    	 removeOrFindResponsible(duty);
		
		
    	Festival festival = festivalBo.findFestivalByName(festivalName);
    	duty.setFestival(festival);
    	dutyBo.addDuty(duty);
    	festivalBo.addDuty(Integer.toString(festival.getFestivalId()), duty);
    	
        return "redirect:duties";
    }


	
	@RequestMapping("/delete/{dutyId}")
    public String deleteContact(@PathVariable("dutyId")
    String dutyId, @PathVariable("festivalName")
    String festivalName) {
 
		dutyBo.deleteDuty(dutyId);
 
        return "redirect:/" + festivalName + "/duty/duties";
    }
	
	@RequestMapping("/edit/{dutyId}")
    public String editContact(@PathVariable("dutyId")
    String dutyId, @PathVariable("festivalName")
    String festivalName, ModelMap map) {
 
		Duty duty = dutyBo.findDutyById(dutyId);
		map.addAttribute(duty);
		map.put("persons", personBo.getAllPersonMap());
		
        return "editDuty";
    }
	
	@RequestMapping("/edit/edited")
    public String editContact(@PathVariable("festivalName")
    String festivalName,@Valid @ModelAttribute("duty")
    Duty duty, BindingResult result, ModelMap map) {
 
		validatorUtil.setValidator(dutyValidator);
		validatorUtil.validate(duty, result);
		
		if(result.hasErrors()){
			return "editDuty";
		}
		
		removeOrFindResponsible(duty);
		
		dutyBo.updateDuty(duty);
		
        return "redirect:/" + festivalName + "/duty/duties";
    }
	
	private void removeOrFindResponsible(Duty duty) {
		if(duty.getResponsible().getPersonId() != null){
    		 Person person = personBo.findPersonById(Integer.toString(duty.getResponsible().getPersonId()));
    		 duty.setResponsible(person);
    	 }else{
    		 duty.setResponsible(null);
    	 }
	}
}
