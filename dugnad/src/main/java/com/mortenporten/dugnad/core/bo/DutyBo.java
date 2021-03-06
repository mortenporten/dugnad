package com.mortenporten.dugnad.core.bo;

import java.util.List;
import java.util.Map;

import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;

public interface DutyBo {

	void addDuty(Duty duty);
	void deleteDuty(String id);
	Duty findDutyById(String id);
	List<Duty> getAllDuties();
	void addPerson(String personId, String dutyId);
	List<Person> findAllPersonsAssigned2Duty(Duty duty);
	void deletePerson(String personId, String dutyId);
	void updateDuty(Duty duty);
	void updateRequired(boolean add, Duty duty);
	void deleteResponsible(String personId);

	
}
