package com.mortenporten.dugnad.core.dao;

import java.util.List;


import com.mortenporten.dugnad.core.persistence.Ticket;

public interface TicketDao {
	
	void addTicket(Ticket ticket);
	void deleteTicket(String ticketId);
	Ticket findTicketById(String ticketId);
	List<Ticket> getAllTickets();
	void updateTicket(Ticket ticket);
	List<Ticket> getTicketsByFestivalId(String festivalId);

}
