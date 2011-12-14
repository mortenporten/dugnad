package com.mortenporten.dugnad.core.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mortenporten.dugnad.core.bo.DutyBo;
import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;
import com.mortenporten.dugnad.validators.AssignDutyValidator;

@Controller
@RequestMapping("/{festivalName}/assignduty/*")
@SessionAttributes({"duty","persons","person","assigned"})
public class AssignDuty2PersonController {

	@Autowired
	DutyBo dutyBo;
	@Autowired
	PersonBo personBo;
	@Autowired
	AssignDutyValidator assignDutyValidator;

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

	@RequestMapping("/add")
	public String addDuty2Person(
			@PathVariable("festivalName")
    		String festivalName,@ModelAttribute("duty")
    		Duty duty,  @ModelAttribute("person")
    		Person person, BindingResult result, Map<String, Object> map) {
        
		String personId = Integer.toString(person.getPersonId());
		String dutyId = Integer.toString(duty.getDutyId());
		assignDutyValidator.validateAssignDuty(result, personId, dutyId);
		
		if(result.hasErrors()){
			return "assigning";
		}
	
		dutyBo.addPerson(personId, dutyId);

		return "redirect:/" + festivalName + "/assignduty/" + dutyId;
	}
	
	@RequestMapping("/{dutyId}/delete/{personId}")
	public String deletePersonFromDuty(@PathVariable("dutyId") String dutyId,
			@PathVariable("festivalName")
    		String festivalName, @PathVariable("personId") String personId, 
    		Map<String, Object> map) {
        
		
		dutyBo.deletePerson(personId, dutyId);

		return "redirect:/" + festivalName + "/assignduty/" + dutyId;
	}

}
