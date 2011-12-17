package com.mortenporten.dugnad.validators.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@Component("validatorUtil")
public class ValidatorUtil {
	
	Validator validator;

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public void validate(Object obj, BindingResult result){
		BindException errors = new BindException(result);
		validator.validate(obj, errors);
	}
	
}
