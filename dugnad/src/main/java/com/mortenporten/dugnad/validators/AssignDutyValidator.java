package com.mortenporten.dugnad.validators;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.mortenporten.dugnad.core.bo.DutyBo;
import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;

@Service
public class AssignDutyValidator {

	@Autowired
	PersonBo personBo;
	@Autowired
	DutyBo dutyBo;
	
	public void validateAssignDuty(BindingResult result, String personId,
			String dutyId){
		
			Person person = personBo.findPersonById(personId);
			Duty duty = dutyBo.findDutyById(dutyId);
			
			
			Collection<Duty> duties =  person.getDuties();
			
			if(!validate(duty, duties)){
				result.rejectValue("personId", "overlapping.duty.personId");
				}
			if(duty.getRequired() != null && duty.getRequired() <= 0){
				result.rejectValue("personId", "no.available.spots");
			}
			
			
			
	}
	
	public boolean validate(Duty duty, Collection<Duty> duties){
		Calendar start = duty.getStart();
		Calendar end = duty.getEnd();
		
		for(Duty d : duties){
			if(start.before(d.getEnd()) && start.after(d.getStart())){
				return false;
			}
			
			if(end.after(d.getStart()) && end.before(d.getEnd())){
				return false;
			}
			
			if(start.before(d.getStart()) && end.after(d.getEnd())){
				return false;
			}
			
			if(start.after(d.getStart()) && end.before(d.getEnd())){
				return false;
			}
			if(start.equals(d.getStart()) && end.equals(d.getEnd())){
				return false;
			}
			
		}
		return true;
	}

}
