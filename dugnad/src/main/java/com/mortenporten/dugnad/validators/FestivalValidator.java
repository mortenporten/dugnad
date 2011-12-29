package com.mortenporten.dugnad.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mortenporten.dugnad.core.bo.FestivalBo;
import com.mortenporten.dugnad.core.persistence.Festival;

@Component("festivalValidator")
public class FestivalValidator implements Validator{

	@Autowired
	FestivalBo festivalBo;
	
	@Override
	public boolean supports(Class clazz) {
		
		return Festival.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		Festival festival = (Festival) obj;
		
		if(festival.getFestivalName() != null){
			if(!festival.getFestivalName().equals("")){
				if(festivalBo.findFestivalByName(festival.getFestivalName()) != null){
						e.rejectValue("festivalName", "name.already.used");
					}
			}
		}
		
		
		
	}

}

