package com.mortenporten.dugnad.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mortenporten.dugnad.core.dao.PersonDao;
import com.mortenporten.dugnad.core.persistence.Person;
import com.mortenporten.dugnad.core.persistence.util.CustomHibernateDAOsupport;

@Repository("personDao")
public class PersonDaoImpl extends CustomHibernateDAOsupport implements PersonDao {

	@Override
	public Person findPersonById(String id) {
		
		
		List list = getHibernateTemplate().find(
                "from Person where Id=?",id
           );
			return (Person)list.get(0);
		
	}

	@Override
	public List<Person> findAllPersons() {
		List<Person> list = (List<Person>) getHibernateTemplate().find(
                "from Person"
           );
			return list;
	}

	@Override
	public void addPerson(Person person) {
		getHibernateTemplate().save(person);
	
	}

	@Override
	public void deletePerson(String id) {
		getHibernateTemplate().delete(findPersonById(id));
		
	}

	@Override
	public void updatePerson(Person person) {
		getHibernateTemplate().update(person);
		
	}

	
	
}
