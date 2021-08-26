package org.ericeagan.vvorlds.services.impl;

import org.ericeagan.vvorlds.models.User;
import org.ericeagan.vvorlds.repositories.UserRepository;
import org.ericeagan.vvorlds.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implmentation of UserDetailsService of UserDetailsService from SpringFramework
 * 
 * @author Eric
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	/**
	 * Repository for VVorld User class
	 */
	private UserRepository userRepository;
	
	/**
	 * Autowired Constructor to inject UserRepository
	 * 
	 * @param userRepository
	 */
	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new CurrentUser(user);
	}

}
