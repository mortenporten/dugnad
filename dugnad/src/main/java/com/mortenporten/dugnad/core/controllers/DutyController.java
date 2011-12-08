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


import com.mortenporten.dugnad.core.bo.DutyBo;
import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Festival;
import com.mortenporten.dugnad.core.persistence.Person;

@Controller
@RequestMapping("/{festivalName}/duty")
public class DutyController {

	@Autowired
	DutyBo dutyBo;
	@Autowired
	FestivalBo festivalBo;
	
	
	@RequestMapping("/duties") 
    public String listDuties(Map<String, Object> map) {
 
        map.put("duties",dutyBo.getAllDuties());
        map.put("duty", new Duty());
        
 
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
}
