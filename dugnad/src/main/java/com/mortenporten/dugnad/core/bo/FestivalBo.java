package com.mortenporten.dugnad.core.bo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Festival;

public interface FestivalBo {

	public void addFestival(Festival festival);
	public void deleteFestival(String id);
	public List<Festival> getAllFestivals();
	void addDuty(String festivalId, Duty duty);
	Festival findFestivalById(String id);
	Festival findFestivalByName(String festivalName);
	List<Duty> getAllDuties(String festivalName);
	void updateFestival(Festival festival);
	Map<String,List<Duty>> findSchedule(String festivalName);
	Map<String,List<Duty>> findAvailableDuties(String festivalName);
}
