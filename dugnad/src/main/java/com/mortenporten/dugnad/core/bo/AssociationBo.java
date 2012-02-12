package com.mortenporten.dugnad.core.bo;

import java.util.List;
import java.util.Map;

import com.mortenporten.dugnad.core.persistence.Association;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;
import com.mortenporten.dugnad.core.persistence.Ticket;



public interface AssociationBo {
	
	void addAssociation(Association association);
	void deleteAssociation(String associationId);
	Association findAssociationById(String associationId);
	List<Association> getAllAssociations();
	void updateAssociation(Association association);
	Map<String,String> getAllAssociationsMap();
	void addPerson(String personId, String associationId);
	List<Duty> getAllDutiesForAssociationByFestivalName(String associationId,String festivalName);

	

}
