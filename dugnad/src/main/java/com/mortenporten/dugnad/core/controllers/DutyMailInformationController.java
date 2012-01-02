package com.mortenporten.dugnad.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.persistence.Person;
import com.mortenporten.dugnad.mail.DutyInformationMail;

@Controller
public class DutyMailInformationController {

	@Autowired
	PersonBo personBo;
	@Autowired
	DutyInformationMail dutyInformationMail;
	
	@RequestMapping("/mail") 
    public String listPersons( ModelMap map) {
 
        dutyInformationMail.sendMail("morten h");
        
        return "redirect:person/persons";
    }
	
}
