package com.mortenporten.dugnad.core.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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

@Controller
@RequestMapping("/{festivalName}/duty")
@SessionAttributes({"duties", "duty"})
public class DutyController {

	@Autowired
	DutyBo dutyBo;
	@Autowired
	FestivalBo festivalBo;
	@Autowired
	PersonBo personBo;
	
	@InitBinder("duty")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new DutyValidator());
    }

	
	@RequestMapping("/duties") 
    public String listDuties(@PathVariable("festivalName")
    String festivalName, Map<String, Object> map) {
 
		map.put("duties",festivalBo.getAllDuties(festivalName));
        map.put("duty", new Duty());
        map.put("person", new Person());
        map.put("persons", personBo.getAllPersonMap());
        
        return "duties";
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addDuty(@PathVariable("festivalName")
    String festivalName,@Valid @ModelAttribute("duty")
    Duty duty, BindingResult result) {
    	
		if(result.hasErrors())  
    	    {  
    	       return "duties";  
    	    } 
    	
    	 
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
		
        return "editDuty";
    }
	
	@RequestMapping("/edit/edited")
    public String editContact(@PathVariable("festivalName")
    String festivalName,@Valid @ModelAttribute("duty")
    Duty duty, BindingResult result, ModelMap map) {
 
		if(result.hasErrors()){
			return "editDuty";
		}
		
		dutyBo.updateDuty(duty);
		
        return "redirect:/" + festivalName + "/duty/duties";
    }
}
