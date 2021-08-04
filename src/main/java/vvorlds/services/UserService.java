package vvorlds.services;

import vvorlds.models.Account;
import vvorlds.models.User;

public interface UserService {
	User findUserById(int id);
	User findUserByUsername(String uName);
	void createUser(User newUser, Account newAcc);
}
