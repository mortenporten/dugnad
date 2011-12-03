package com.mortenporten.dugnad.core.bo;

import java.util.List;

import com.mortenporten.dugnad.core.persistence.Festival;

public interface FestivalBo {

	public void addFestival(Festival festival);
	public void deleteFestival(Festival festival);
	public List<Festival> getAllFestivals();
}
