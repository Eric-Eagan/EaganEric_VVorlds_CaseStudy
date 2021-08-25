package org.ericeagan.vvorlds.services.impl;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.ericeagan.vvorlds.models.User;
import org.ericeagan.vvorlds.repositories.UserRepository;
import org.ericeagan.vvorlds.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	private PasswordEncoder pswdEncoder;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder pswdEncoder) {
		this.userRepository = userRepository;
		this.pswdEncoder = pswdEncoder;
	}
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public User createUser(User user) {
		user.setPassword(pswdEncoder.encode(user.getPassword()));
		return save(user);
	}

	@Override
	public User getById(Integer id) {
		return userRepository.getById(id);
	}

	@Override
	public User getByUsername(String username) {
		return userRepository.getByUsername(username);
	}

	@Override
	public boolean validatePassword(User user, String password) {
		return pswdEncoder.matches(password, user.getPassword());
	}

	@Override
	public boolean validateUser(User user) {
		Set<ConstraintViolation<User>> violations = validator.validate(user);
		return violations.isEmpty();
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
}
