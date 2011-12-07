package com.mortenporten.dugnad.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mortenporten.dugnad.core.dao.DutyDao;
import com.mortenporten.dugnad.core.persistence.Duty;
import com.mortenporten.dugnad.core.persistence.Person;
import com.mortenporten.dugnad.core.persistence.util.CustomHibernateDAOsupport;

@Repository("dutyDao")
public class DutyDaoImpl extends CustomHibernateDAOsupport implements DutyDao {

	@Override
	public void addDuty(Duty duty) {
		getHibernateTemplate().save(duty);
		
	}

	@Override
	public void deleteDuty(String id) {
		getHibernateTemplate().delete(findDutyById(id));
		
	}

	@Override
	public Duty findDutyById(String id) {
		List list = getHibernateTemplate().find(
                "from Duty where Id=?",id
           );
			return (Duty)list.get(0);
	}

	@Override
	public List<Duty> getAllDuties() {
		List<Duty> list = (List<Duty>) getHibernateTemplate().find(
                "from Duty"
           );
			return list;
	}

}
