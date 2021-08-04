package vvorlds.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vvorlds.models.Account;
import vvorlds.models.User;
import vvorlds.repositories.UserRepository;

import vvorlds.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository ur;

	@Autowired
	public UserServiceImpl(UserRepository rep) {
		this.ur = rep;
	}

	@Override
	public User findUserById(int id) {
		return ur.findUserById(id);
	}

	@Override
	public void createUser(User newUser, Account newAcc) {
		ur.createUser(newUser, newAcc);
	}

	@Override
	public User findUserByUsername(String uName) {
		return ur.findUserByUsername(uName);
	}
}
