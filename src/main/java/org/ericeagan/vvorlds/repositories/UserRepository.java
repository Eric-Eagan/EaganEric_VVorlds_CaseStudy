package org.ericeagan.vvorlds.repositories;

import org.ericeagan.vvorlds.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository extended Repository for accessing Users in DB
 * 
 * @author Eric
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	/**
	 * Get user with this id from DB
	 * @param id of a user in DB
	 * @return User associated with this id
	 */
	User getById(Integer id);
	
	/**
	 * Get user with this username from DB
	 * @param username of a user in DB
	 * @return User associated with this username
	 */
	User getByUsername(String username);
}