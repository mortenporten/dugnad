package com.mortenporten.dugnad.core.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.dao.PersonDao;
import com.mortenporten.dugnad.core.persistence.Person;

@Service("personBo")
public class PersonBoImpl implements PersonBo {

	@Autowired
	PersonDao personDao;
	
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

}
