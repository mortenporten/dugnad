package com.mortenporten.dugnad.core.bo;

import java.util.List;
import java.util.Map;

import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;

public interface PersonBo {
	
	Person findPersonById(String id);
    
	List<Person> findAllPersons(); 
	
	public void addPerson(Person person);
	
	public void deletePerson(String id);
	
	//void addDuty(Duty duty, Person person);
	
	Map<String,String> getAllPersonMap();

	void deleteDutyFromPerson(Duty duty, Person person);
	
	void updatePerson(Person person);
}
