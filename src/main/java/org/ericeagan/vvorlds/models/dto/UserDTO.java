package org.ericeagan.vvorlds.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@NotNull
	@Size(min=2, max=25, message="Username must be between 2 and 25 characters.")
	String username;
	
	/**
	 * 
	 */
	@NotNull
	@Size(min=4, max=100, message="Password must be between 4 and 100 characters.")
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
