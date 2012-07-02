package com.mortenporten.dugnad.core.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mortenporten.dugnad.core.beans.MailingList;
import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;
import com.mortenporten.dugnad.mail.DutyInformationMail;

@Controller
@RequestMapping("/{festivalName}/*")
@SessionAttributes({"list","persons",})
public class DutyMailInformationController {

	@Autowired
	PersonBo personBo;
	@Autowired
	DutyInformationMail dutyInformationMail;
	
	@RequestMapping("/mail") 
    public String listPersons(@PathVariable("festivalName")
	String festivalName, ModelMap map) {
 
       //dutyInformationMail.sendMail("morten h");
		map.put("persons",personBo.getPersonsWithDutiesInFestival(festivalName));
		map.put("list",new MailingList());
		
		
		
        return "mailPersons";
    }
	
	 @RequestMapping(value = "/add", method = RequestMethod.POST)
	    public String addPerson(@ModelAttribute("list")
	    MailingList list, @PathVariable("festivalName")
		String festivalName, ModelMap map) {
	    	
		if(list != null){
			for(String s: list.getMailingList()){
				System.err.println(s);
			}
				
		}
	    	
	 
	        return "redirect:mailPersons";
	    }
	
}
