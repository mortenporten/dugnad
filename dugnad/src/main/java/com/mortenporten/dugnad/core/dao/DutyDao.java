package com.mortenporten.dugnad.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mortenporten.dugnad.core.persistence.Duty;


public interface DutyDao {
	
	void addDuty(Duty duty);
	void deleteDuty(String id);
	Duty findDutyById(String id);
	List<Duty> getAllDuties();
	

}
