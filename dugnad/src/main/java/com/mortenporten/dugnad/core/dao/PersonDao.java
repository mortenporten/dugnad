package com.mortenporten.dugnad.core.dao;

import java.util.List;

import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;

public interface PersonDao {
	
	Person findPersonById(String id);
	
	List<Person> findAllPersons(); 
	
	public void addPerson(Person person);
	
	public void deletePerson(String id);
	
	void updatePerson(Person person);
	
	boolean containsPerson(Person person);
	
		
}
