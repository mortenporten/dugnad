package com.mortenporten.dugnad.core.dao;

import java.util.Collection;
import java.util.List;

import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Festival;

public interface FestivalDao {

	public void addFestival(Festival festival);
	public void deleteFestival(String festivalId);
	Festival findFestivalById(String festivalId);
	public List<Festival> getAllFestivals();
	void addDuty(String festivalId, Duty duty);
	Festival findFestivalByName(String festivalName);
	void updateFestival(Festival festival);
	
}
