package com.mortenporten.dugnad.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mortenporten.dugnad.core.dao.TicketDao;
import com.mortenporten.dugnad.core.persistence.Association;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Ticket;
import com.mortenporten.dugnad.core.persistence.util.CustomHibernateDAOsupport;

@Repository("ticketDao")
public class TicketDaoImpl extends CustomHibernateDAOsupport implements TicketDao {

	@Override
	public void addTicket(Ticket ticket) {
		getHibernateTemplate().save(ticket);
		
	}

	@Override
	public void deleteTicket(String ticketId) {
		getHibernateTemplate().delete(findTicketById(ticketId));
		
	}

	@Override
	public Ticket findTicketById(String ticketId) {
		List list = getHibernateTemplate().find(
                "from Ticket where Id=?",ticketId
           );
			return (Ticket) list.get(0);
	}

	@Override
	public List<Ticket> getAllTickets() {
		List<Ticket> list = getHibernateTemplate().find(
                "from Ticket");
			return list;
	}

	@Override
	public void updateTicket(Ticket ticket) {
		getHibernateTemplate().update(ticket);
		
	}

	@Override
	public List<Ticket> getTicketsByFestivalId(String festivalId) {
		List<Ticket> list = getHibernateTemplate().find(
                "from Association where festival_Id=?", festivalId
           );
			return list;
	}
}
