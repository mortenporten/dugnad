package com.mortenporten.dugnad.core.dao;

import java.util.List;
import java.util.Map;


import com.mortenporten.dugnad.core.persistence.Association;

public interface AssociationDao {
	
	void addAssociation(Association association);
	void deleteAssociation(String associationId);
	Association findAssociationById(String associationId);
	List<Association> getAllAssociations();
	void updateAssociation(Association association);
	
}
