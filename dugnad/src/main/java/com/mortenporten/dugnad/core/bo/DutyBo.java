package com.mortenporten.dugnad.core.bo;

import java.util.List;

import com.mortenporten.dugnad.core.persistence.Duty;

public interface DutyBo {

	void addDuty(Duty duty);
	void deleteDuty(String id);
	Duty findDutyById(String id);
	List<Duty> getAllDuties();
	
}
