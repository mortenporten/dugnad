package com.mortenporten.dugnad.validators;

import java.util.Calendar;

import org.joda.time.Period;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mortenporten.dugnad.core.persistence.Duty;

@Component("dutyValidator")
public class DutyValidator implements Validator{

	@Override
	public boolean supports(Class clazz) {
		
		return Duty.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		
		Duty duty = (Duty) obj;
		
		if(duty.getStart() == null){
			e.rejectValue("start", "start.must.be.set");
			
		}
		if(duty.getEnd() == null){
			e.rejectValue("end", "end.must.be.set");
			
		}
		
		if(duty.getEnd() == null || duty.getStart() == null){
			return;
		}
		
		Calendar start = duty.getStart();
		Calendar end = duty.getEnd();
		
		
		if(start.compareTo(end) >= 0){
			e.rejectValue("end", "start.after.end");
		}
		
		if(!duty.getDefinedHours()){
			long duration = end.getTimeInMillis() - start.getTimeInMillis();
			Period period = new Period(duration);
			double hoursAndMins = period.getHours() + (period.getMinutes() / 60.00 ); 
			duty.setHours(hoursAndMins);
		}
		
		
		if(duty.getHours() > 24){
			e.rejectValue("hours", "too.long.duty");
		}
		
		
	}

}
