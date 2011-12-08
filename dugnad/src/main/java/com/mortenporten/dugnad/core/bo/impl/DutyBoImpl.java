package com.mortenporten.dugnad.core.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mortenporten.dugnad.core.bo.DutyBo;
import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.dao.DutyDao;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Festival;
import com.mortenporten.dugnad.core.persistence.Person;

@Service("dutyBo")
public class DutyBoImpl implements DutyBo {

	@Autowired
	DutyDao dutyDao;
	@Autowired
	FestivalBo festivalBo;
	
	@Override
	@Transactional
	public void addDuty(Duty duty) {
		dutyDao.addDuty(duty);
		
	}

	@Override
	@Transactional
	public void deleteDuty(String id) {
		removeDutyFromFestival(findDutyById(id));
		dutyDao.deleteDuty(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Duty findDutyById(String id) {
		
		return dutyDao.findDutyById(id);
	}

	@Override
	@Transactional
	public List<Duty> getAllDuties() {
		
		return dutyDao.getAllDuties();
	}

	@Transactional
	private void removeDutyFromFestival(Duty duty){
		for(Festival f : festivalBo.getAllFestivals()){
			if(f.getDuties().contains(duty)){
				f.getDuties().remove(duty);
			}
		}
	}

	@Override
	@Transactional
	public void addPerson(Person person, Duty duty) {
		duty.getPersons().add(person);
		updateDuty(duty);
	}

	@Override
	@Transactional
	public List<Person> findAllPersonsAssigned2Duty(Duty duty) {
		
		return (List<Person>) duty.getPersons();
	}

	@Override
	public void deletePerson(Person person, Duty duty) {
		if(duty.getPersons().contains(person)){
			System.out.println("teit");
			duty.getPersons().remove(person);
		}
		
	}

	@Override
	public void updateDuty(Duty duty) {
		dutyDao.updateDuty(duty);
		
	}
	
	
}
