package com.mortenporten.dugnad.core.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mortenporten.dugnad.core.bo.DutyBo;
import com.mortenporten.dugnad.core.dao.DutyDao;
import com.mortenporten.dugnad.core.persistence.Duty;

@Service("dutyBo")
public class DutyBoImpl implements DutyBo {

	@Autowired
	DutyDao dutyDao;
	
	@Override
	public void addDuty(Duty duty) {
		dutyDao.addDuty(duty);
		
	}

	@Override
	public void deleteDuty(String id) {
		dutyDao.deleteDuty(id);
		
	}

	@Override
	public Duty findDutyById(String id) {
		
		return dutyDao.findDutyById(id);
	}

	@Override
	public List<Duty> getAllDuties() {
		
		return dutyDao.getAllDuties();
	}

}
