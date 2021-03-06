package com.mortenporten.dugnad.core.bo.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mortenporten.dugnad.core.bo.AssociationBo;
import com.mortenporten.dugnad.core.bo.PersonBo;
import com.mortenporten.dugnad.core.dao.AssociationDao;
import com.mortenporten.dugnad.core.persistence.Association;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;

@Service("associationBo")
public class AssociationBoImpl implements AssociationBo {

	@Autowired
	AssociationDao associationDao;
	@Autowired
	PersonBo personBo;
	
	@Override
	@Transactional
	public void addAssociation(Association association) {
		associationDao.addAssociation(association);
		
	}

	@Override
	@Transactional
	public void deleteAssociation(String associationId) {
		Association association = findAssociationById(associationId);
		association.getAssociationPersons().clear();
		updateAssociation(association);
		personBo.deleteAssociation(associationId);
		associationDao.deleteAssociation(associationId);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Association findAssociationById(String associationId) {
		return associationDao.findAssociationById(associationId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Association> getAllAssociations() {
		return associationDao.getAllAssociations();
	}

	@Override
	@Transactional
	public void updateAssociation(Association association) {
		associationDao.updateAssociation(association);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map<String, String> getAllAssociationsMap() {
		List<Association> associations = getAllAssociations();
		Map<String,String> associationsMap = new LinkedHashMap<String, String>();
		for(Association a: associations){
			associationsMap.put(Integer.toString(a.getAssociationId()), 
					a.getAssociationName());
		}
		return associationsMap;
	}

	@Override
	@Transactional
	public void addPerson(String personId, String associationId) {
		Person person = personBo.findPersonById(personId);
		Association association = findAssociationById(associationId);
		association.getAssociationPersons().add(person);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Duty> getAllDutiesForAssociationByFestivalName(String associationId, String festivalName) {
		Association association = findAssociationById(associationId);
		List<Person> persons = (List<Person>) association.getAssociationPersons();
		List<Duty> associationDuties = new ArrayList<Duty>();
		
		for(Person p: persons){
			associationDuties.addAll(personBo.findAllDutiesForPersonForFestival(
					Integer.toString(p.getPersonId()), festivalName));
		}
		
		return associationDuties;
	}
	
	@Override
	@Transactional
	public void removePersonFromAssociation(String personId) {
		List<Association> associations = getAllAssociations();
		
		for(Association a: associations){
			List<Person> persons = (List<Person>) a.getAssociationPersons();
			for(Person p : persons){
				if(Integer.toString(p.getPersonId()).equals(personId)){
					a.getAssociationPersons().remove(p);
				}
			}
		}
		
	}

	@Override
	public Double findHoursForAssociation(List<Duty> duties) {
			Double hours = 0.00;
			for(Duty d : duties){
				if(d.getHours() != null){
					hours+=d.getHours();
				}
			}
			return hours;
		
	}
	
}
