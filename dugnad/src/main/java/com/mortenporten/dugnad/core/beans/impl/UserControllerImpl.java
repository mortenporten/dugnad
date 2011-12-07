package com.mortenporten.dugnad.core.beans.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mortenporten.dugnad.core.beans.UserController;
import com.mortenporten.dugnad.core.persistence.Festival;

@Component
@Scope("session")
public class UserControllerImpl implements UserController {

	
	Festival festival;
	
	
	@Override
	public void setFestival(Festival festival) {
		this.festival = festival;
		
	}

	@Override
	public Festival getFestival() {
		return this.festival;
	}

}
