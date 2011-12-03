package com.mortenporten.dugnad.core.dao;

import java.util.Collection;
import java.util.List;

import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Festival;

public interface FestivalDao {

	public void addFestival(Festival festival);
	public void deleteFestival(Festival festival);
	public List<Festival> getAllFestivals();
	
}
