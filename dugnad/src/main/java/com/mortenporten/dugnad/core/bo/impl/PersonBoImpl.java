package com.mortenporten.dugnad.core.bo.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mortenporten.dugnad.core.bo.AssociationBo;
import com.mortenporten.dugnad.core.bo.DutyBo;
import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.bo.TicketBo;
import com.mortenporten.dugnad.core.dao.PersonDao;
import com.mortenporten.dugnad.core.persistence.Association;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Festival;
import com.mortenporten.dugnad.core.persistence.Paid;
import com.mortenporten.dugnad.core.persistence.Person;
import com.mortenporten.dugnad.core.persistence.Ticket;

@Service("personBo")
public class PersonBoImpl implements PersonBo {

	@Autowired
	PersonDao personDao;
	@Autowired
	DutyBo dutyBo;
	@Autowired
	FestivalBo festivalBo;
	@Autowired
	TicketBo ticketBo;
	@Autowired 
	AssociationBo associationBo;
	
	@Override
	@Transactional(readOnly = true)
	public Person findPersonById(String id) {
		return personDao.findPersonById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Person> findAllPersons() {
		
		return personDao.findAllPersons();
	}

	@Override
	@Transactional
	public void addPerson(Person person) {
		personDao.addPerson(person);
		
	}

	@Override
	@Transactional
	public void deletePerson(String personId) {
		List<String> duties = getPersonDutiesId(personId);
		for(String s:duties){
			dutyBo.deletePerson(personId, s);
		}
		dutyBo.deleteResponsible(personId);
		removePersonFromAssociation(findPersonById(personId));
		personDao.deletePerson(personId);
		
	}

	
	@Override
	public Map<String,String> getAllPersonMap(){
		List<Person> personList = findAllPersons();
		Map<String, String> personMap = new LinkedHashMap<String,String>();
		for(Person p : personList ){
			personMap.put(Integer.toString(p.getPersonId()), p.getLastName() + 
					", " + p.getFirstName());
		}
		return personMap;
	}

	@Override
	@Transactional
	public void deleteDutyFromPerson(String dutyId, String personId) {
		Person person = findPersonById(personId);
		Duty duty = dutyBo.findDutyById(dutyId);
		dutyBo.updateRequired(true, duty);
		
		person.getDuties().remove(duty);
	}

	

	@Override
	@Transactional(readOnly = true)
	public List<Duty> findAllDutiesForPersonForFestival(String personId, String festivalName) {
		Person person = findPersonById(personId);
		Festival festival = festivalBo.findFestivalByName(festivalName);
		
		List<Duty> duties = (List<Duty>) person.getDuties();
		List<Duty> dutiesForFestival = new ArrayList<Duty>();
		
		for(Duty d : duties){
			if(d.getFestival().equals(festival)){
			dutiesForFestival.add(d);
			}
		}
		
		return dutiesForFestival;
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> getPersonDutiesId(String personId) {
		List<String> dutiesId = new ArrayList<String>();
		Person person = findPersonById(personId);
		for(Duty d : person.getDuties()){
			dutiesId.add(Integer.toString(d.getDutyId()));
		}
		return dutiesId;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean containsPerson(Person person) {
		return personDao.containsPerson(person);
	}

	@Override
	@Transactional
	public void updatePerson(Person person) {
		personDao.updatePerson(person);
		
	}

	@Override
	public Double findHoursForPerson(List<Duty> duties) {
		Double hours = 0.00;
		for(Duty d : duties){
			if(d.getHours() != null){
				hours+=d.getHours();
			}
		}
		return hours;
	}

	@Override
	@Transactional
	public void addTicket(String ticketId, String personId) {
		Ticket ticket = ticketBo.findTicketById(ticketId);
		Person person = findPersonById(personId);
		person.getTickets().add(ticket);
		
	}

	@Override
	@Transactional
	public void deleteTicket(String ticketId, String personId) {
		Person person = findPersonById(personId);
		Ticket ticket = ticketBo.findTicketById(ticketId);
		
		person.getTickets().remove(ticket);
		
	}


	@Transactional
	private void removePersonFromAssociation(Person person){
		for(Association a : associationBo.getAllAssociations()){
			if(a.getAssociationPersons().contains(person)){
				a.getAssociationPersons().remove(person);
			}
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Paid findPaidByPersonAndFestival(String personId, String festivalName) {
		 Person person = findPersonById(personId);
		 for(Paid p : person.getReceipts()){
			 if(p.getFestival().getFestivalName().equals(festivalName)){
				 return p;
			 }
		 }
		 return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ticket> getTicketsByFestivalNameAndPersonId(
			String festivalName, String personId) {
		Person person =personDao.findPersonById(personId);
		ArrayList<Ticket> list = new ArrayList<Ticket>();
		for(Ticket t : person.getTickets()){
			if(t.getFestival().getFestivalName().equals(festivalName)){
				list.add(t);
			}
		}
		return list;
	}
	
	

}
