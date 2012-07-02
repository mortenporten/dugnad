package com.mortenporten.dugnad.core.bo;

import java.util.List;

import com.mortenporten.dugnad.core.persistence.Paid;




public interface PaidBo {
	
	void addPaid(Paid paid);
	void deletePaid(String paidId);
	Paid findPaidById(String paidId);
	List<Paid> getAllPaid();
	void updatePaid(Paid paid);
	List<Paid> getPaidByFestival(String festivalName);
	Paid findPaidByFestivalAndPerson(String festivalName, String personId);
	void deleteAllPaidByFestival(String festivalId);

}