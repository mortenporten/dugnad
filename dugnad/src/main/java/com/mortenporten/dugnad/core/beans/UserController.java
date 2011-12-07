package com.mortenporten.dugnad.core.beans;

import com.mortenporten.dugnad.core.persistence.Festival;

public interface UserController {

	void setFestival(Festival festival);
	Festival getFestival();
	
}
