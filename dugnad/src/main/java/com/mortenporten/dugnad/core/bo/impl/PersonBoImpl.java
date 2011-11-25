package com.mortenporten.dugnad.core.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.dao.PersonDao;
import com.mortenporten.dugnad.core.persistence.Person;

@Service("personBo")
public class PersonBoImpl implements PersonBo {

	@Autowired
	PersonDao personDao;
	
	@Override
	public Person findPersonById(String id) {
		return personDao.findPersonById(id);
	}

	@Override
	public List<Person> findAllPersons() {
		
		return personDao.findAllPersons();
	}

	@Override
	public void addPerson(Person person) {
		personDao.addPerson(person);
		
	}

	@Override
	public void deletePerson(String id) {
		personDao.deletePerson(id);
		
	}

}
