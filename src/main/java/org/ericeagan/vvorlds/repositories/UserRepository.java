package org.ericeagan.vvorlds.repositories;

import org.ericeagan.vvorlds.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User getById(Integer id);
	User getByUsername(String username);
}
