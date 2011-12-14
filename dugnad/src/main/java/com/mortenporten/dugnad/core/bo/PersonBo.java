package com.mortenporten.dugnad.core.bo;

import java.util.List;
import java.util.Map;

import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;

public interface PersonBo {
	
	Person findPersonById(String id);
    
	List<Person> findAllPersons(); 
	
	void addPerson(Person person);
	
	void deletePerson(String id);
		
	Map<String,String> getAllPersonMap();

	void deleteDutyFromPerson(String dutyId, String personId);
		
	List<Duty> findAllDutiesForPersonForFestival(String personId, String festivalName);
	
	List<String> getPersonDutiesId(String personId);
	
	boolean containsPerson(Person person);
	
	void updatePerson(Person person);
	
	Double findHoursForPerson(List<Duty> duties);
}
