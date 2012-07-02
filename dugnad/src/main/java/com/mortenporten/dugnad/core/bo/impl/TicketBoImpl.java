package com.mortenporten.dugnad.core.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.bo.TicketBo;
import com.mortenporten.dugnad.core.dao.TicketDao;
import com.mortenporten.dugnad.core.persistence.Festival;
import com.mortenporten.dugnad.core.persistence.Person;
import com.mortenporten.dugnad.core.persistence.Ticket;

@Service("ticketBo")
public class TicketBoImpl implements TicketBo {

	@Autowired
	TicketDao ticketDao;
	@Autowired
	PersonBo personBo;
	@Autowired
	FestivalBo festivalBo;
	
	@Override
	@Transactional
	public void addTicket(Ticket ticket) {
		ticketDao.addTicket(ticket);
		
	}

	@Override
	@Transactional
	public void deleteTicket(String ticketId) {
		ticketDao.deleteTicket(ticketId);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Ticket findTicketById(String ticketId) {
		return ticketDao.findTicketById(ticketId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ticket> getAllTickets() {
		return ticketDao.getAllTickets();
	}

	@Override
	@Transactional
	public void addPerson(String personId, String ticketId) {
		Ticket ticket = findTicketById(ticketId);
		ticket.getPersons().add(personBo.findPersonById(personId));
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Person> findAllPersonsAssigned2Ticket(Ticket ticket) {
		return  (List<Person>) ticket.getPersons();
	}

	@Override
	@Transactional
	public void updateTicket(Ticket ticket) {
		ticketDao.updateTicket(ticket);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, String> getMapOfTicketsForFestival(String festivalName) {
		Map<String,String> map = new HashMap<String, String>();
		List<Ticket> tickets = getAllTickets();
		
		for(Ticket t : tickets){
			
			if(festivalName.equals(t.getFestival().getFestivalName())){
				if( t.getDate() != null){
				DateTime date = new DateTime(t.getDate().getTimeInMillis());
				Locale locale = new Locale("no", "NO");
				String onlyDate = date.toString("EEEE dd-MM-yy",locale);
				map.put(Integer.toString(t.getTicketId()), 
						t.getTicketType() + "," + onlyDate);
				}else{
				map.put(Integer.toString(t.getTicketId()), 
							t.getTicketType());
				}
			}
		}
		return map;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ticket> getTicketsByFestival(String festivalName) {
		Festival festival = festivalBo.findFestivalByName(festivalName);
		return ticketDao.getTicketsByFestivalId(Integer.toString(festival.getFestivalId()));
	}

	@Override
	@Transactional
	public void deleteTicketsByFestival(String festivalId) {
		for(Ticket t : getAllTickets()){
			if(Integer.toString(t.getFestival().getFestivalId()).equals(festivalId)){
				for(Person p : t.getPersons()){
					p.getTickets().remove(t);
				}
				deleteTicket(Integer.toString(t.getTicketId()));
			}
		}
		
	}
	
	

}
