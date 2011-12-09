package com.mortenporten.dugnad.core.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;

@Controller
@RequestMapping("/{festivalName}/overview/*")
@SessionAttributes({"chosenPerson"})
public class OverviewController {
	
	@Autowired
	PersonBo personBo;
	
	@RequestMapping("/pickperson")
	public String pickPerson(@ModelAttribute("person") Person person,
			@PathVariable("festivalName")
    		String festivalName,
			ModelMap map) {

		if(person.getPersonId() != null){
			String personId = Integer.toString(person.getPersonId());
			List<Duty> duties = personBo.findAllDutiesForPersonForFestival(personId, festivalName);
			map.put("duties", duties);
			person = personBo.findPersonById(personId);
			map.put("chosenPerson", person);
		}else{
			map.put("duties", new ArrayList<Duty>());
		}
		
		map.put("persons", personBo.getAllPersonMap());
		map.put("person", new Person());
		
		return "personOverview";
	}
	
	@RequestMapping("/delete/{dutyId}")
	public String deleteDutyFromPerson(
			@PathVariable("dutyId") String dutyId,
			@PathVariable("festivalName")
    		String festivalName,
			@ModelAttribute("chosenPerson") Person chosenPerson,
			ModelMap map) {
        
		personBo.deleteDutyFromPerson(dutyId, 
				Integer.toString(chosenPerson.getPersonId()));
		map.put("person", chosenPerson);
		
		return "redirect:/" + festivalName + "/overview/pickperson";
	}
	
	
	
	
}
