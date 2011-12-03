package com.mortenporten.dugnad.core.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.dao.FestivalDao;
import com.mortenporten.dugnad.core.persistence.Festival;

@Service("festivalBo")
public class FestivalBoImpl implements FestivalBo {

	@Autowired
	FestivalDao festivalDao;
	
	@Override
	public void addFestival(Festival festival) {
		festivalDao.addFestival(festival);
		
	}

	@Override
	public void deleteFestival(Festival festival) {
		festivalDao.deleteFestival(festival);
		
	}

	@Override
	public List<Festival> getAllFestivals() {
		
		return festivalDao.getAllFestivals();
	}
	
	
}
