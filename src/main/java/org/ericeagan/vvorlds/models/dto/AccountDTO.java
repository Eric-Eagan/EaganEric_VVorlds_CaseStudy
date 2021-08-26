package org.ericeagan.vvorlds.models.dto;

import org.ericeagan.vvorlds.models.Account;

/**
 * DTO for retrieving relevant data from view
 * 
 * @author Eric
 *
 */
public class AccountDTO {
	/**
	 * Account fields needed on view
	 */
	String firstName;
	String lastName;
	String email;
	String address;
	String phone;
	
	public AccountDTO() {
		super();
	}

	public AccountDTO(String firstName, String lastName, String email, String address, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}
	
	public AccountDTO(Account account) {
		this.firstName = account.getFirstName();
		this.lastName = account.getLastName();
		this.email = account.getEmail();
		this.address = account.getAddress();
		this.phone = account.getPhone();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
