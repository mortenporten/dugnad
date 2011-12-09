package com.mortenporten.dugnad.core.bo.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mortenporten.dugnad.core.bo.DutyBo;
import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.bo.PersonBo;
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
	@Autowired
	PersonBo personBo;
	
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
	public void addPerson(String personId, String dutyId) {
		Duty duty = findDutyById(dutyId);
		Person person = personBo.findPersonById(personId);
		duty.getPersons().add(person);
		
	}

	@Override
	@Transactional
	public List<Person> findAllPersonsAssigned2Duty(Duty duty) {
		return (List<Person>) duty.getPersons();
	}

	@Override
	@Transactional
	public void deletePerson(String personId, String dutyId) {
		Duty duty = findDutyById(dutyId);
		Person person = personBo.findPersonById(personId);
		
		duty.getPersons().remove(person);
		
		
	}

	
	
}
