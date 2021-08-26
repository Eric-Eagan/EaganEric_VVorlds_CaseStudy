package org.ericeagan.vvorlds.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Account Entity for storing random, and non-required, info about a user
 * 
 * @author Eric
 *
 */
@Entity
@Table(name = "accounts")
public class Account {
	/**
	 * Id, taken from the User entity associated with the Account
	 */
	@Id
	int userId;
	
	/**
	 * Fields to store info
	 */
	@Column(name="firstName")
	String firstName;
	@Column(name="lastName")
	String lastName;
	@Column(name="email")
	String email;
	@Column(name="address")
	String address;
	@Column(name="phone")
	String phone;
	
	/**
	 * 1-1 association with a User entity
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
    @JoinColumn(name = "user_id")
	User user;

	/**
	 * No-arg constructor
	 */
	public Account() {
	}
	
	/**
	 * Create Account with data already input
	 * Most parameters Strings and self explanatory
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param address
	 * @param phone
	 * @param user User entity associated with this Account
	 */
	public Account(String firstName, String lastName, String email, String address, String phone, User user) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.user = user;
	}

	/**
	 * @return the id of the account's User
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId set this as the id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return this account's firstname
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName set this as the firstname
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return this account's lastname
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName set this as the lastname
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return this account's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email set this as the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return this account's address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address set this as the address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return this account's phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone set this as the phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return this account's associated User
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user set this as the associated User
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Account [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", address="
				+ address + ", phone=" + phone + "]";
	}
}
