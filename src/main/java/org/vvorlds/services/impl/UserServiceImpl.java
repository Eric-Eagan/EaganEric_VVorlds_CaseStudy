package org.vvorlds.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vvorlds.models.User;
import org.vvorlds.repositories.UserRepository;
import org.vvorlds.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	private PasswordEncoder pswdEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder pswdEncoder) {
		this.userRepository = userRepository;
		this.pswdEncoder = pswdEncoder;
	}
	
	@Override
	public User save(User user) {
		System.out.print(user.getPassword());
		user.setPassword(pswdEncoder.encode(user.getPassword()));
		System.out.print(user.getPassword());
		return userRepository.save(user);
	}

	@Override
	public User getById(Integer id) {
		return userRepository.getById(id);
	}

	@Override
	public User getByUsername(String username) {
		return userRepository.getByUsername(username);
	}
}
