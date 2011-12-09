package com.mortenporten.dugnad.core.bo.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mortenporten.dugnad.core.bo.DutyBo;
import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.dao.PersonDao;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;

@Service("personBo")
public class PersonBoImpl implements PersonBo {

	@Autowired
	PersonDao personDao;
	@Autowired
	DutyBo dutyBo;
	
	@Override
	@Transactional(readOnly = true)
	public Person findPersonById(String id) {
		return personDao.findPersonById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Person> findAllPersons() {
		
		return personDao.findAllPersons();
	}

	@Override
	@Transactional
	public void addPerson(Person person) {
		personDao.addPerson(person);
		
	}

	@Override
	@Transactional
	public void deletePerson(String id) {
		personDao.deletePerson(id);
		
	}

	@Override
	@Transactional
	public void addDuty(Duty duty, Person person) {
		person.getDuties().add(duty);
		
		
	}
	
	@Override
	public Map<String,String> getAllPersonMap(){
		List<Person> personList = findAllPersons();
		Map<String, String> personMap = new LinkedHashMap<String,String>();
		for(Person p : personList ){
			personMap.put(Integer.toString(p.getPersonId()), p.getName());
		}
		return personMap;
	}

	@Override
	@Transactional
	public void deleteDutyFromPerson(String dutyId, String personId) {
		Person person = findPersonById(personId);
		Duty duty = dutyBo.findDutyById(dutyId);
		
		person.getDuties().remove(duty);
	}

	@Override
	public void updatePerson(Person person) {
		personDao.updatePerson(person);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Duty> findAllDutiesForPerson(String personId) {
		Person person = findPersonById(personId);
		
		return (List<Duty>) person.getDuties();
	}
	
	

}
