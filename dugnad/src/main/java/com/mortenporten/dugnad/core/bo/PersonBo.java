package com.mortenporten.dugnad.core.bo;

import java.util.List;
import java.util.Map;

import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Paid;
import com.mortenporten.dugnad.core.persistence.Person;
import com.mortenporten.dugnad.core.persistence.Ticket;

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
	
	void addTicket(String ticketId, String personId);
	
	void deleteTicket(String ticketId, String personId);
	
	Paid findPaidByPersonAndFestival(String personId, String festivalName);
	
	List<Ticket> getTicketsByFestivalNameAndPersonId(String festivalName, String personId);
	
	List<Person> getPersonsWithDutiesInFestival(String festivalName);
	
	Map<String,Person> getPersonsWithDutiesInFestivalMap(String festivalName);
	
	void deleteAssociation(String associationId);
	
}
