package com.mortenporten.dugnad.core.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mortenporten.dugnad.core.bo.DutyBo;
import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;

@Controller
@RequestMapping("/{festivalName}/assignduty/*")
public class AssignDuty2PersonController {

	@Autowired
	DutyBo dutyBo;
	@Autowired
	PersonBo personBo;

	@RequestMapping("/{dutyId}")
	public String assigningDuty2Person(@PathVariable("dutyId") String dutyId,
			Map<String, Object> map) {

		Duty duty = dutyBo.findDutyById(dutyId);
		
		
		map.put("duty", duty);
		map.put("persons", personBo.getAllPersonMap());
		map.put("person", new Person());
		map.put("assigned", dutyBo.findAllPersonsAssigned2Duty(duty));
		

		return "assigning";
	}

	@RequestMapping("/{dutyId}/add")
	public String addDuty2Person(@PathVariable("dutyId") String dutyId,
			@PathVariable("festivalName")
    		String festivalName ,@ModelAttribute("person")
    		Person person, Map<String, Object> map) {
        
		person = personBo.findPersonById(Integer.toString(person.getPersonId()));
		Duty duty = dutyBo.findDutyById(dutyId);
		
		
		dutyBo.addPerson(person, duty);

		return "redirect:/" + festivalName + "/assignduty/" + dutyId;
	}
	
	@RequestMapping("/{dutyId}/delete/{personId}")
	public String deletePersonFromDuty(@PathVariable("dutyId") String dutyId,
			@PathVariable("festivalName")
    		String festivalName ,@PathVariable("personId") String personId, 
    		Map<String, Object> map) {
        
		
		Person person = personBo.findPersonById(personId);
		Duty duty = dutyBo.findDutyById(dutyId);
		
		
		personBo.deleteDutyFromPerson(duty, person);

		return "redirect:/" + festivalName + "/assignduty/" + dutyId;
	}

}
