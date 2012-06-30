package com.mortenporten.dugnad.core.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mortenporten.dugnad.core.bo.PaidBo;
import com.mortenporten.dugnad.core.dao.PaidDao;
import com.mortenporten.dugnad.core.persistence.Paid;

@Service("paidBo")
public class PaidBoImpl implements PaidBo{

	@Autowired
	PaidDao paidDao;
	
	
	@Override
	@Transactional 
	public void addPaid(Paid paid) {
		paidDao.addPaid(paid);
		
	}

	@Override
	@Transactional
	public void deletePaid(String paidId) {
		paidDao.deletePaid(paidId);
		
	}

	@Override
	@Transactional(readOnly= true)
	public Paid findPaidById(String paidId) {
		return paidDao.findPaidById(paidId);
	}

	@Override
	@Transactional(readOnly= true)
	public List<Paid> getAllPaid() {
		return paidDao.getAllPaid();
	}

	@Override
	@Transactional
	public void updatePaid(Paid paid) {
		paidDao.updatePaid(paid);
	}

	@Override
	@Transactional(readOnly= true)
	public List<Paid> getPaidByFestival(String festivalName) {
		return paidDao.getPaidByFestivalId(festivalName);
	}

	@Override
	@Transactional(readOnly= true)
	public Paid findPaidByFestivalAndPerson(String festivalName, String personId) {
		List<Paid> paids = getPaidByFestival(festivalName);
		for(Paid p : paids){
			if(Integer.toString(p.getPerson().getPersonId()).equals(personId)){
				return p;
			}
				
		}
		return null;
	}

}
