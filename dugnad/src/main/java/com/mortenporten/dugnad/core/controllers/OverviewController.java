package com.mortenporten.dugnad.core.controllers;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mortenporten.dugnad.core.bo.DutyBo;
import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.bo.PaidBo;
import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.bo.TicketBo;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Paid;
import com.mortenporten.dugnad.core.persistence.Person;
import com.mortenporten.dugnad.core.persistence.Ticket;

@Controller
@RequestMapping("/{festivalName}/overview/*")
@SessionAttributes({"chosenPerson", "hours", "dutiesOverview","ticket", "nok", "persons"})
public class OverviewController {
	
	@Autowired
	PersonBo personBo;
	@Autowired
	DutyBo dutyBo;
	@Autowired
	TicketBo ticketBo;
	@Autowired 
	PaidBo paidBo;
	@Autowired
	FestivalBo festivalBo;
	
	@RequestMapping("/pickperson")
	public String pickPerson(ModelMap map) {

			map.put("dutiesOverview", new ArrayList<Duty>());
			map.put("chosenPerson", new Person());
			map.put("ticket", new Ticket());
			map.put("ticketsMap", new HashMap<String, String>());
			map.put("tickets",  new ArrayList<Ticket>());
		
		
		map.put("persons", personBo.getAllPersonMap());
		map.put("person", new Person());
		Locale locale = new Locale("no", "NO");
		Currency cur = Currency.getInstance(locale);
		map.put("nok",cur.getSymbol());
		
		return "personOverview";
	}
	
	@RequestMapping("/personpicked")
	public String personPicked(@ModelAttribute("person") Person person,
			BindingResult result, @PathVariable("festivalName")
    		String festivalName, @ModelAttribute("chosenPerson") Person chosenPerson,
    		BindingResult chosenResult, ModelMap map) {

		if(result.hasErrors()){
			return "personOverview";
		}
		
		
		if(person.getPersonId() != null){
			
			String personId = Integer.toString(person.getPersonId());
			
			List<Duty> duties = personBo.findAllDutiesForPersonForFestival(personId, festivalName);
			map.put("dutiesOverview", duties);
			
			person = personBo.findPersonById(personId);
			map.put("chosenPerson", person);
			map.put("ticket", new Ticket());
			map.put("tickets", personBo.getTicketsByFestivalNameAndPersonId(festivalName, Integer.toString(chosenPerson.getPersonId())));
			map.put("hours", personBo.findHoursForPerson(duties));
			Paid paid = personBo.findPaidByPersonAndFestival(personId, festivalName);
			if(paid == null){
				map.put("paid", new Paid());
			}else{
				map.put("paid",paid);
			}
		
		}else if(chosenPerson.getPersonId() != null){
			
			List<Duty> duties = personBo.findAllDutiesForPersonForFestival(
					Integer.toString(chosenPerson.getPersonId()), 
					festivalName);
			chosenPerson = personBo.findPersonById(
					Integer.toString(chosenPerson.getPersonId()));
			map.put("dutiesOverview", duties);
			map.put("tickets", personBo.getTicketsByFestivalNameAndPersonId(festivalName, Integer.toString(chosenPerson.getPersonId())) );
			Paid paid = personBo.findPaidByPersonAndFestival(Integer.toString(chosenPerson.getPersonId()), festivalName);
			if(paid == null){
				map.put("paid", new Paid());
			}else{
				map.put("paid",paid);
			}
			
		}
		
		map.put("persons", personBo.getAllPersonMap());
		map.put("person", new Person());
		map.put("ticketsMap", ticketBo.getMapOfTicketsForFestival(festivalName));
		
		return "personOverview";
	}
	
	@RequestMapping("/delete/{dutyId}")
	public String deleteDutyFromPerson(
			@PathVariable("dutyId") String dutyId,
			@PathVariable("festivalName")
    		String festivalName,
			@ModelAttribute("chosenPerson") Person chosenPerson,
			ModelMap map) {
        
		dutyBo.deletePerson(Integer.toString(chosenPerson.getPersonId()), dutyId); 
		map.put("person", chosenPerson);
		
		return "redirect:/" + festivalName + "/overview/personpicked";
	}
	
	@RequestMapping("/add")
	public String addTicket2Person(
			@PathVariable("festivalName")
    		String festivalName,
			@ModelAttribute("chosenPerson") Person chosenPerson,
			@ModelAttribute("ticket") Ticket ticket,@ModelAttribute("paid") Paid paid,
			ModelMap map) {
        
		Paid oldPaid =personBo.findPaidByPersonAndFestival(Integer.toString(chosenPerson.getPersonId()), festivalName);
		if (oldPaid == null) {
		paid.setFestival(festivalBo.findFestivalByName(festivalName));
		paid.setPerson(chosenPerson);
		chosenPerson.getReceipts().add(paid);
		personBo.updatePerson(chosenPerson);
		} else {
			oldPaid.setPaid(paid.getPaid());
			paidBo.updatePaid(oldPaid);
		}
		
		personBo.addTicket(Integer.toString(ticket.getTicketId()), 
				Integer.toString(chosenPerson.getPersonId()));
		
		
		
		return "redirect:/" + festivalName + "/overview/personpicked";
	}
	
	@RequestMapping("/remove/{ticketId}")
	public String removeTicketFromPerson(@PathVariable("ticketId")
    	String ticketId, @PathVariable("festivalName")
		String festivalName, @ModelAttribute("chosenPerson") 
		Person chosenPerson){
		
		personBo.deleteTicket(ticketId, Integer.toString(chosenPerson.getPersonId()));
		
		return "redirect:/" + festivalName + "/overview/personpicked";
	}
}
