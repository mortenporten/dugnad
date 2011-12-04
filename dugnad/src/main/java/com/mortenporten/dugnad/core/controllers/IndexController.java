package com.mortenporten.dugnad.core.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.persistence.Festival;

@Controller
public class IndexController {

	@Autowired
	FestivalBo festivalBo;
	
	@RequestMapping("/index") 
    public String listPersons(Map<String, Object> map) {
 
       
        
 
        return "index";
    }
	
	
	
	
}

