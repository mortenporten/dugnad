package com.mortenporten.dugnad.core.bo.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.bo.PaidBo;
import com.mortenporten.dugnad.core.bo.TicketBo;
import com.mortenporten.dugnad.core.dao.FestivalDao;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Festival;

@Service("festivalBo")
public class FestivalBoImpl implements FestivalBo {

	@Autowired
	FestivalDao festivalDao;
	@Autowired
	PaidBo paidBo;
	@Autowired
	TicketBo ticketBo;
	
	@Override
	@Transactional
	public void addFestival(Festival festival) {
		festivalDao.addFestival(festival);
		
	}

	@Override
	@Transactional
	public void deleteFestival(String festivalId) {
		paidBo.deleteAllPaidByFestival(festivalId);
		ticketBo.deleteTicketsByFestival(festivalId);
		festivalDao.deleteFestival(festivalId);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Festival> getAllFestivals() {
		return festivalDao.getAllFestivals();
	}

	@Override
	@Transactional
	public void addDuty(String festivalId, Duty duty) {
		festivalDao.addDuty(festivalId, duty);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Festival findFestivalById(String id) {
		
		return festivalDao.findFestivalById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Festival findFestivalByName(String festivalName) {
		return festivalDao.findFestivalByName(festivalName);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Duty> getAllDuties(String festivalName) {
		Festival festival = findFestivalByName(festivalName);
		return (List<Duty>) festival.getDuties();
	}

	@Override
	@Transactional
	public void updateFestival(Festival festival) {
		festivalDao.updateFestival(festival);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map<String, List<Duty>> findSchedule(String festivalName) {
		
		List<Duty> duties = getAllDuties(festivalName);
		Map<String, List<Duty>> days = getMapOfDutyDates(duties);
		
		return days;
		
	}
	
	
	private Map<String, List<Duty>> getMapOfDutyDates(
			List<Duty> duties) {
		
		Map<String, List<Duty>> days = new LinkedHashMap<String, List<Duty>>();
		Collections.sort(duties);
		
		for(Duty d: duties){
			DateTime date = new DateTime(d.getStart().getTimeInMillis());
			Locale locale = new Locale("no", "NO");
			String onlyDate = date.toString("EEEE dd-MM-yy",locale);	
			if(days.containsKey(onlyDate)){
				days.get(onlyDate).add(d);
			}else{
				List<Duty> dutiesOnSameDate = new ArrayList<Duty>();
				dutiesOnSameDate.add(d);
				days.put(onlyDate, dutiesOnSameDate);
			}
		}
		return days;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, List<Duty>> findAvailableDuties(String festivalName) {
		
		List<Duty> duties = getAllDuties(festivalName);
		List<Duty> availableDuties = new ArrayList<Duty>();
		for(Duty d : duties){
	    	if(d.getRequired() != null && d.getRequired() > 0){
	    		availableDuties.add(d);
	    	}
	    }
		
		Map<String, List<Duty>> days = getMapOfDutyDates(availableDuties);
		
		return days;
	}
	
	
}
