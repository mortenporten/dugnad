package com.mortenporten.dugnad.core.dao;

import java.util.List;


import com.mortenporten.dugnad.core.persistence.Paid;
import com.mortenporten.dugnad.core.persistence.Ticket;

public interface PaidDao {
	
	void addPaid(Paid paid);
	void deletePaid(String paidId);
	Paid findPaidById(String paidId);
	List<Paid> getAllPaid();
	void updatePaid(Paid paid);
	List<Paid> getPaidByFestivalId(String paidId);

}
