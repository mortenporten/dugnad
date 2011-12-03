package com.mortenporten.dugnad.core.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mortenporten.dugnad.core.dao.FestivalDao;
import com.mortenporten.dugnad.core.persistence.Festival;
import com.mortenporten.dugnad.core.persistence.util.CustomHibernateDAOsupport;

@Repository("festivalDao")
public class FestivalDaoImpl extends CustomHibernateDAOsupport implements FestivalDao{

	@Override
	public void addFestival(Festival festival) {
		getHibernateTemplate().save(festival);
		
	}

	@Override
	public void deleteFestival(Festival festival) {
		getHibernateTemplate().delete(festival);

	}
	
	@Override
	public List<Festival> getAllFestivals(){
		
		List<Festival> festivals = getHibernateTemplate().find("from Festival");
		
		
		return festivals;
	}

	
}
