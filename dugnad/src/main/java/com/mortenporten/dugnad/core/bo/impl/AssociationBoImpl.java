package com.mortenporten.dugnad.core.bo.impl;

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

}
