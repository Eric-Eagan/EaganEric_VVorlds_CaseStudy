package org.ericeagan.vvorlds.exceptions;

/**
 * Exception for if a user was not found when searching
 * 
 * @author Eric
 *
 */
public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * @param errorMessage the message
	 */
	public UserNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
