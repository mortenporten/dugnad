package com.mortenporten.dugnad.validators;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;

public class AssignDutyValidatorTest {

	private AssignDutyValidator dutyValidator;
	private Duty duty;
	private Person person;
	private Duty overlap;
	
	@Before
	public void setUp(){
		duty = new Duty();
		overlap = new Duty();
		person = new Person();
		dutyValidator = new AssignDutyValidator();
		
		Calendar oStart = Calendar.getInstance();
		oStart.set(2011,3,15);
		Calendar oEnd = Calendar.getInstance();
		oEnd.set(2011,6,15);
		
		
		overlap.setStart(oStart);
		overlap.setEnd(oEnd);
		
		person.setDuties(new ArrayList<Duty>());
		person.getDuties().add(overlap);
		
	}
	
	@Test
	public void withStartInsideOfDuty(){
		
		Calendar start = Calendar.getInstance();
		start.set(2011,4,1);
		Calendar end = Calendar.getInstance();
		end.set(2011,8,1);
		
		duty.setStart(start);
		duty.setEnd(end);
		
		TestCase.assertFalse("Did not catch start inside of duty",
				(dutyValidator.validate(duty,person.getDuties())));
		
	}
	
	@Test
	public void withEndInsideOfDuty(){
		
		Calendar start = Calendar.getInstance();
		start.set(2011,1,1);
		Calendar end = Calendar.getInstance();
		end.set(2011,5,1);
		
		duty.setStart(start);
		duty.setEnd(end);
		
		TestCase.assertFalse("Did not catch end inside of duty",
				(dutyValidator.validate(duty,person.getDuties())));
		
	}
	
	@Test
	public void withStartAndEndInsideOfDuty(){
		
		Calendar start = Calendar.getInstance();
		start.set(2011,4,1);
		Calendar end = Calendar.getInstance();
		end.set(2011,5,1);
		
		duty.setStart(start);
		duty.setEnd(end);
		
		TestCase.assertFalse("Did not catch start and end inside of duty",
				(dutyValidator.validate(duty,person.getDuties())));
		
	}
	
	@Test
	public void withStartAndEndBeforeAndAfterDuty(){
		
		Calendar start = Calendar.getInstance();
		start.set(2011,1,1);
		Calendar end = Calendar.getInstance();
		end.set(2011,12,1);
		
		duty.setStart(start);
		duty.setEnd(end);
		
		TestCase.assertFalse("Did not catch start and end before and after duty",
				(dutyValidator.validate(duty,person.getDuties())));
		
	}
	
	@Test
	public void withStartAndEndAfterDuty(){
		
		Calendar start = Calendar.getInstance();
		start.set(2012,1,1);
		Calendar end = Calendar.getInstance();
		end.set(2012,12,1);
		
		duty.setStart(start);
		duty.setEnd(end);
		
		TestCase.assertTrue("Caught start and end after duty",
				(dutyValidator.validate(duty,person.getDuties())));
		
	}
	
	@Test
	public void withStartAndEndBeforeDuty(){
		
		Calendar start = Calendar.getInstance();
		start.set(2012,1,1);
		Calendar end = Calendar.getInstance();
		end.set(2012,12,1);
		
		duty.setStart(start);
		duty.setEnd(end);
		
		TestCase.assertTrue("Caught start and end before duty",
				(dutyValidator.validate(duty,person.getDuties())));
		
	}
	
	@Test
	public void withStartAndEndTheSameAsDuty(){
		
		Calendar start = Calendar.getInstance();
		start.set(2011,3,15);
		Calendar end = Calendar.getInstance();
		end.set(2011,6,15);
		
		duty.setStart(start);
		duty.setEnd(end);
		
		TestCase.assertFalse("Did not catch when start and end are the same as duty",
				(dutyValidator.validate(duty,person.getDuties())));
		
	}
	
}
