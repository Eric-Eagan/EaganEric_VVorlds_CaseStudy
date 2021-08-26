package org.ericeagan.vvorlds.services.impl;

import org.ericeagan.vvorlds.exceptions.UserNotFoundException;
import org.ericeagan.vvorlds.models.User;
import org.ericeagan.vvorlds.repositories.UserRepository;
import org.ericeagan.vvorlds.services.UserService;
import org.ericeagan.vvorlds.validators.MyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementation of UserService
 * 
 * @author Eric
 *
 */
@Service
public class UserServiceImpl implements UserService {
	/**
	 * User Repository for accessing DB
	 */
	private UserRepository userRepository;
	
	/**
	 * Password Encoder
	 */
	private PasswordEncoder pswdEncoder;
	
	private MyValidator validator = new MyValidator();
	
	/**
	 * Autowired Constructor for injecting UserRepository and Encoder
	 * 
	 * @param userRepository
	 * @param pswdEncoder
	 */
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
	public User getById(Integer id) throws UserNotFoundException {
		User user = userRepository.getById(id);
		if (user == null) 
			throw new UserNotFoundException("User "+ id +" not found");
		return user;
	}

	@Override
	public User getByUsername(String username){
		return userRepository.getByUsername(username);
	}
	
	@Override
	public boolean availableUsername(String username) {
		return userRepository.getByUsername(username) == null;
	}

	@Override
	public boolean validatePassword(User user, String password) {
		return pswdEncoder.matches(password, user.getPassword());
	}

	@Override
	public boolean validateUser(User user) {
		return validator.validateUser(user);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
}
