package com.mortenporten.dugnad.core.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.bo.TicketBo;
import com.mortenporten.dugnad.core.persistence.Person;
import com.mortenporten.dugnad.core.persistence.Ticket;

@Controller
@RequestMapping("/{festivalName}/ticket/")
@SessionAttributes("ticket")
public class TicketController {

	@Autowired
	TicketBo ticketBo;
	@Autowired
	FestivalBo festivalBo;
	  
		@RequestMapping("/tickets") 
	    public String listTickets( ModelMap map, @PathVariable("festivalName")
    	String festivalName) {
	 
	        map.put("tickets",ticketBo.getTicketsByFestival(festivalName));
	        map.put("ticket", new Ticket());
	        
	        return "tickets";
	    }
	    
	    @RequestMapping(value = "/add", method = RequestMethod.POST)
	    public String addTicket(@Valid @ModelAttribute("ticket")
	    	Ticket ticket, BindingResult result, 
	    	@PathVariable("festivalName")
	    	String festivalName) {
	    	
	    	 if(result.hasErrors())  
	    	    {  
	    	        return "tickets";  
	    	    } 
	    	
	    	 	ticket.setFestival(festivalBo.findFestivalByName(festivalName));
	        	ticketBo.addTicket(ticket);
	       
	    	 
	 
	        return "redirect:tickets";
	    }
	    
	    @RequestMapping("/delete/{ticketId}")
	    public String deleteTicket(@PathVariable("ticketId")
	    String ticketId, @PathVariable("festivalName")
	    String festivalName) {
	 
	       ticketBo.deleteTicket(ticketId);
	 
	       return "redirect:/" + festivalName + "/ticket/tickets";
	    }
	    
	    @RequestMapping(value = "/edit/{ticketId}")
	    public String editTicket(@PathVariable("ticketId")
	    String ticketId, ModelMap map) {
	    	
	    	
	    	Ticket ticket = ticketBo.findTicketById(ticketId);
	    	map.addAttribute(ticket);
	 
	        return "editTicket";
	    }
	    
	    @RequestMapping(value = "/edit/edited")
	    public String editedTicket(@Valid @ModelAttribute("ticket")
	    Ticket ticket, BindingResult result, @PathVariable("festivalName")
	    String festivalName) {
	    	
	    	 if(result.hasErrors())  
	    	    {  
	    		 return "editTicket";  
	    	    }
	    	 
	    	ticketBo.updateTicket(ticket);
	    	 
	    	return "redirect:/" + festivalName + "/ticket/tickets";
	    }
	
}
