package com.mortenporten.dugnad.core.bo;

import java.util.List;
import java.util.Map;

import com.mortenporten.dugnad.core.persistence.Person;
import com.mortenporten.dugnad.core.persistence.Ticket;



public interface TicketBo {
	
	void addTicket(Ticket ticket);
	void deleteTicket(String ticketId);
	Ticket findTicketById(String ticketId);
	List<Ticket> getAllTickets();
	void addPerson(String personId, String ticketId);
	List<Person> findAllPersonsAssigned2Ticket(Ticket ticket);
	void updateTicket(Ticket ticket);
	Map<String,String> getMapOfTicketsForFestival(String festivalName);
	

}
