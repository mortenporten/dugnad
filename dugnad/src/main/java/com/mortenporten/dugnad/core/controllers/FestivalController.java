package com.mortenporten.dugnad.core.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.persistence.Festival;

@Controller
public class FestivalController {

	@Autowired
	FestivalBo festivalBo;
	
	@RequestMapping("/festivals") 
    public String listPersons(Map<String, Object> map) {
 
        map.put("festivals",festivalBo.getAllFestivals());
        map.put("festival", new Festival());
        
 
        return "festivals";
    }
	
	
	
	
}
