package com.mortenporten.dugnad.core.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mortenporten.dugnad.core.dao.AssociationDao;
import com.mortenporten.dugnad.core.persistence.Association;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.util.CustomHibernateDAOsupport;

@Repository("associationDao")
public class AssociationDaoImpl extends CustomHibernateDAOsupport implements AssociationDao {

	@Override
	public void addAssociation(Association association) {
		getHibernateTemplate().save(association);
		
	}

	@Override
	public void deleteAssociation(String associationId) {
		getHibernateTemplate().delete(findAssociationById(associationId));
		
	}

	@Override
	public Association findAssociationById(String associationId) {
		List list = getHibernateTemplate().find(
                "from Association where Id=?", associationId
           );
			return (Association)list.get(0);
		
	}

	@Override
	public List<Association> getAllAssociations() {
		List<Association> list = (List<Association>) getHibernateTemplate().find(
                "from Association"
           );
			return list;
	}

	@Override
	public void updateAssociation(Association association) {
		getHibernateTemplate().update(association);
		
	}
	

}
