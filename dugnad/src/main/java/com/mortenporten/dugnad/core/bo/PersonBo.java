package com.mortenporten.dugnad.core.bo;

import java.util.List;

import com.mortenporten.dugnad.core.persistence.Person;

public interface PersonBo {
	
	Person findPersonById(String id);
    
	List<Person> findAllPersons(); 
	
	public void addPerson(Person person);
	
	public void deletePerson(String id);

}
