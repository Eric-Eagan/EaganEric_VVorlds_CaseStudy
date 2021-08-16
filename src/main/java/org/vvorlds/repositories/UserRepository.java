package org.vvorlds.repositories;

import org.vvorlds.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	User getByUsername(String username);
	User getById(Integer id);
}
