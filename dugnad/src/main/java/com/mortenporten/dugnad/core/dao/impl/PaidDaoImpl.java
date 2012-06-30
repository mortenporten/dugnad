package com.mortenporten.dugnad.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mortenporten.dugnad.core.dao.PaidDao;
import com.mortenporten.dugnad.core.persistence.Paid;
import com.mortenporten.dugnad.core.persistence.util.CustomHibernateDAOsupport;

@Repository("paidDao")
public class PaidDaoImpl extends CustomHibernateDAOsupport implements PaidDao  {

	@Override
	public void addPaid(Paid paid) {
		getHibernateTemplate().save(paid);
		
	}

	@Override
	public void deletePaid(String paidId) {
		getHibernateTemplate().delete(findPaidById(paidId));
		
	}

	@Override
	public Paid findPaidById(String paidId) {
		List<Paid> list = getHibernateTemplate().find(
                "from Paid where Id=?",paidId
           );
			return (Paid) list.get(0);
	}

	@Override
	public List<Paid> getAllPaid() {
		List<Paid> list = getHibernateTemplate().find(
                "from Paid");
			return list;
	}
	

	@Override
	public void updatePaid(Paid paid) {
		getHibernateTemplate().update(paid);
	}

	@Override
	public List<Paid> getPaidByFestivalId(String paidId) {
		List<Paid> list = getHibernateTemplate().find(
                "from Paid where festival_Id=?", paidId
           );
			return list;
	}
	

}
