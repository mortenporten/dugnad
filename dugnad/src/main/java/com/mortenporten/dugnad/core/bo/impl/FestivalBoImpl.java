package com.mortenporten.dugnad.core.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.dao.FestivalDao;
import com.mortenporten.dugnad.core.persistence.Duty;
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
	public void deleteFestival(String festivalId) {
		festivalDao.deleteFestival(festivalId);
		
	}

	@Override
	public List<Festival> getAllFestivals() {
		return festivalDao.getAllFestivals();
	}

	@Override
	@Transactional
	public void addDuty(String festivalId, Duty duty) {
		festivalDao.addDuty(festivalId, duty);
		
	}

	@Override
	public Festival findFestivalById(String id) {
		
		return festivalDao.findFestivalById(id);
	}

	@Override
	public Festival findFestivalByName(String festivalName) {
		return festivalDao.findFestivalByName(festivalName);
	}
	
	
}
