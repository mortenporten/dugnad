package com.mortenporten.dugnad.validators;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import com.mortenporten.dugnad.core.bo.DutyBo;
import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;

public class AssignDutyValidator {

	@Autowired
	PersonBo personBo;
	@Autowired
	DutyBo dutyBo;
	
	public void validateAssignDuty(BindingResult result, String personId,
			String dutyId){
		
			Person person = personBo.findPersonById(personId);
			Duty duty = dutyBo.findDutyById(personId);
			
			
			Collection<Duty> duties =  person.getDuties();
			
			
			
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
