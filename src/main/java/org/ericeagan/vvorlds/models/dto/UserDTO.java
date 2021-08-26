package org.ericeagan.vvorlds.models.dto;

import org.ericeagan.vvorlds.models.User;

/**
	 * DTO for retrieving data from view
	 * 
	 * @author Eric
	 *
	 */
public class UserDTO {
	/**
	 * 
	 */
	String username;
	
	/**
	 * 
	 */
	String password;

	public UserDTO() {
		super();
	}

	public UserDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public UserDTO(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
