package com.mortenporten.dugnad.core.bo.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mortenporten.dugnad.core.bo.DutyBo;
import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.dao.PersonDao;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Festival;
import com.mortenporten.dugnad.core.persistence.Person;

@Service("personBo")
public class PersonBoImpl implements PersonBo {

	@Autowired
	PersonDao personDao;
	@Autowired
	DutyBo dutyBo;
	@Autowired
	FestivalBo festivalBo;
	
	
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
	public void deletePerson(String personId) {
		List<String> duties = getPersonDutiesId(personId);
		for(String s:duties){
			dutyBo.deletePerson(personId, s);
		}
		personDao.deletePerson(personId);
		
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
	@Transactional(readOnly = true)
	public List<Duty> findAllDutiesForPersonForFestival(String personId, String festivalName) {
		Person person = findPersonById(personId);
		Festival festival = festivalBo.findFestivalByName(festivalName);
		
		List<Duty> duties = (List<Duty>) person.getDuties();
		List<Duty> dutiesForFestival = new ArrayList<Duty>();
		
		for(Duty d : duties){
			if(d.getFestival().equals(festival)){
			dutiesForFestival.add(d);
			}
		}
		
		return dutiesForFestival;
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> getPersonDutiesId(String personId) {
		List<String> dutiesId = new ArrayList<String>();
		Person person = findPersonById(personId);
		for(Duty d : person.getDuties()){
			dutiesId.add(Integer.toString(d.getDutyId()));
		}
		return dutiesId;
	}

	@Override
	public boolean containsPerson(Person person) {
		return personDao.containsPerson(person);
	}

	@Override
	public void updatePerson(Person person) {
		personDao.updatePerson(person);
		
	}


		

}
