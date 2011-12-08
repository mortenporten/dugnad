package com.mortenporten.dugnad.core.bo;

import java.util.List;

import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;

public interface DutyBo {

	void addDuty(Duty duty);
	void deleteDuty(String id);
	Duty findDutyById(String id);
	List<Duty> getAllDuties();
	void addPerson(Person person, Duty duty);
	List<Person> findAllPersonsAssigned2Duty(Duty duty);
	void deletePerson(Person person, Duty duty);
	void updateDuty(Duty duty);
	
}
