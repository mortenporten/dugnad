package com.mortenporten.dugnad.core.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mortenporten.dugnad.core.dao.FestivalDao;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Festival;
import com.mortenporten.dugnad.core.persistence.Person;
import com.mortenporten.dugnad.core.persistence.util.CustomHibernateDAOsupport;

@Repository("festivalDao")
public class FestivalDaoImpl extends CustomHibernateDAOsupport implements FestivalDao{

	@Override
	public void addFestival(Festival festival) {
		getHibernateTemplate().save(festival);
		
	}

	@Override
	public void deleteFestival(String festivalId) {
		getHibernateTemplate().delete(findFestivalById(festivalId));

	}
	
	@Override
	public List<Festival> getAllFestivals(){
		
		List<Festival> festivals = getHibernateTemplate().find("from Festival");
		
		return festivals;
	}

	@Override
	public Festival findFestivalById(String festivalId) {
		
		List list = getHibernateTemplate().find(
                "from Festival where Id=?",festivalId
           );
			return (Festival)list.get(0);
	}


	@Override
	public void addDuty(String festivalId, Duty duty) {
		Festival festival = findFestivalById(festivalId);
		festival.getDuties().add(duty);
		
	}

	@Override
	public Festival findFestivalByName(String festivalName) {
		List list = getHibernateTemplate().find(
                "from Festival where Name=?",festivalName
           );
			return (Festival)list.get(0);
	}

	
}
