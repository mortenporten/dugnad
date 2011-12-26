package com.mortenporten.dugnad.core.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.persistence.Duty;

@Controller
@RequestMapping("/{festivalName}/available")
public class AvailableDutiesController {
	
	@Autowired
	FestivalBo festivalBo;
	
	@RequestMapping("/duties")
	public String getAvailableDutiesToShow(@PathVariable("festivalName") String festivalName,
			ModelMap map){
		
		
		Map<String, List<Duty>> days = festivalBo.findAvailableDuties(festivalName);
		map.addAttribute("daysDate", days.keySet());
		
		return "availableDutiesOverview";
		
	}
	
	@RequestMapping("/duties/{date}")
	public String getDateAvailableDutyToShow(@PathVariable("date") String date,
			@PathVariable("festivalName") String festivalName,
			ModelMap map){
		
		
		Map<String, List<Duty>> days = festivalBo.findAvailableDuties(festivalName);
		map.addAttribute("daysDate",days.keySet());
		map.addAttribute("date", date);
		map.addAttribute("duties", days.get(date));
		
		
		return "chosenDateAvailableDuties";
	}

}
