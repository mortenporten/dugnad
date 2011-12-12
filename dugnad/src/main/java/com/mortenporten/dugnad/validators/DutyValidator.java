package com.mortenporten.dugnad.validators;

import java.util.Calendar;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mortenporten.dugnad.core.persistence.Duty;

public class DutyValidator implements Validator{

	@Override
	public boolean supports(Class clazz) {
		
		return Duty.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		
		Duty duty = (Duty) obj;
		
		Calendar start = duty.getStart();
		Calendar end = duty.getEnd();
		
		if(start.compareTo(end) >= 0){
			e.rejectValue("end", "start.after.end");
		}
		
	}

}
