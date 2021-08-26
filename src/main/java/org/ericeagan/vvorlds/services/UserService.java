package org.ericeagan.vvorlds.services;

import org.ericeagan.vvorlds.exceptions.UserNotFoundException;
import org.ericeagan.vvorlds.models.User;

/**
 * Service for accessing UserRepository
 * 
 * @author Eric
 *
 */
public interface UserService {
	
	/**
	 * Persists User entity to DB
	 * 
	 * @param user to be persisted
	 * @return User returned as modified by being persisted
	 */
	User save(User user);
	
	/**
	 * Encodes password of provided User then uses save method
	 * 
	 * @param user to be encoded and persisted
	 * @return User returned as modified by being persisted and encoded
	 */
	User createUser(User user);
	
	/**
	 * Get user associated with this Id
	 * 
	 * @param id of a user in DB
	 * @return User with that Id
	 */
	User getById(Integer id);
	
	/**
	 * Get user associated with this Username
	 * 
	 * @param username of a user in DB
	 * @return That user
	 * @throws UserNotFoundException
	 */
	User getByUsername(String username) throws UserNotFoundException;
	
	/**
	 * Tries to retrieve user from database with that name
	 * 
	 * @param username to be checked
	 * @return true if finds no user with that name, otherwise false
	 */
	boolean availableUsername(String username);
	
	/**
	 * Checks if password matches the password in DB for this user
	 * Checks using the encoder Autowired in
	 * 
	 * @param user with encoded password
	 * @param password in plain text to be checked
	 * @return true if it matches once encoded
	 */
	boolean validatePassword(User user, String password);
	
	/**
	 * Checks if given user passes validation
	 * 
	 * @param user to be validated
	 * @return true or false based on if valid
	 */
	boolean validateUser(User user);
	
	/**
	 * Deletes User from DB
	 * 
	 * @param user to be deleted
	 */
	void deleteUser(User user);
}
