package com.mortenporten.dugnad.core.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mortenporten.dugnad.core.bo.DutyBo;
import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;

@Controller
@RequestMapping("/{festivalName}/day/*")
public class DayOverviewController {

	@Autowired
	FestivalBo festivalBo;
	
	@RequestMapping("/overview")
	public String getDaysToShow(@PathVariable("festivalName") String festivalName,
			ModelMap map){
		
		Map<String, List<Duty>> days = festivalBo.findSchedule(festivalName);
		map.addAttribute("daysDate", days.keySet());
	
		
		
		return "dayOverview";
		
	}
	
	@RequestMapping("/overview/{date}")
	public String getDateToShow(@PathVariable("date") String date,
			@PathVariable("festivalName") String festivalName,
			ModelMap map){
		
		Map<String, List<Duty>> days = festivalBo.findSchedule(festivalName);
		map.addAttribute("daysDate", days.keySet());
		map.addAttribute("date", date);
		map.addAttribute("duties", days.get(date));
		
		
		return "chosenDateOverview";
	}
}
