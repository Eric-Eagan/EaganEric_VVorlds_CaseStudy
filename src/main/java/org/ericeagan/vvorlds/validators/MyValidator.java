package org.ericeagan.vvorlds.validators;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.ericeagan.vvorlds.models.User;

/**
 * Validator for confirming entity validity
 * 
 * @author Eric
 *
 */
public class MyValidator {
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	
	public boolean validateUser(User user) {
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		return violations.isEmpty();
	}
}
