package org.ericeagan.vvorlds.services;

import org.ericeagan.vvorlds.models.User;

public interface UserService {
	User save(User user);
	User getById(Integer id);
	User getByUsername(String username);
}