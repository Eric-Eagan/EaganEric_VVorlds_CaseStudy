package org.vvorlds.services;

import org.vvorlds.models.User;

public interface UserService {
	User save(User user);
	User getById(Integer id);
	User getByUsername(String username);
}
