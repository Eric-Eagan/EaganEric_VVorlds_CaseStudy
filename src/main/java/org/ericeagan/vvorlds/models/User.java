package org.ericeagan.vvorlds.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User entity for storing username and password
 * 
 * @author Eric
 *
 */
@Entity
@Table(name = "users")
@NamedQuery(name="User.getAllUsers", query = "SELECT u FROM User u")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * DB id auto-generated PK
	 */
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	/**
	 * username for identifying a user, unique
	 */
	@Column(name="username", unique=true)
	@NotNull
	@Size(min=2, max=25, message="Username must be between 2 and 25 characters.")
	String username;
	
	/**
	 * encoded password for authentication
	 */
	@Column(name="password")
	@NotNull
	@Size(min=4, max=100, message="Password must be between 4 and 100 characters.")
	String password;
	
	/**
	 * authentication label
	 */
	String userRole;
	
	/**
	 * 1-1 association with Account entity
	 */
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	Account account;
	
	/**
	 * 1-Many association, Set of files owned buy this User
	 */
	@OneToMany(targetEntity = File.class, mappedBy = "owner", cascade = CascadeType.ALL)
	private Set<File> ownedFiles;
	
	/**
	 * Many-Many association, Set of files that have been shared with User
	 */
	@ManyToMany
	@JoinTable(
			  name = "FileSharedUser", 
			  joinColumns = @JoinColumn(name = "userId"), 
			  inverseJoinColumns = @JoinColumn(name = "fileId"))
	private Set<File> sharedFiles;
	
	/**
	 * No-arg constructor to default create User with default role
	 */
	public User() {
		this.userRole = "ROLE_USER";
		this.ownedFiles = new HashSet<>();
		this.sharedFiles = new HashSet<>();
	}
	
	/**
	 * Main constructor for creating Users
	 * 
	 * @param username of the user
	 * @param password of the user
	 */
	public User(String username, String password) {
		this();
		this.username = username;
		this.password = password;
		this.account = new Account();
	}

	/**
	 * @return username of user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username new users username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return password of user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password new users password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Account of user
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account new users Account
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * @return id of user
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return userRole of user
	 */
	public String getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole new users Role
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	/**
	 * @return set of files owned by user
	 */
	public Set<File> getOwnedFiles() {
		return ownedFiles;
	}

	/**
	 * @param ownedFiles new users set of owned files
	 */
	public void setOwnedFiles(Set<File> ownedFiles) {
		this.ownedFiles = ownedFiles;
	}

	/**
	 * @return set of files shared with user
	 */
	public Set<File> getSharedFiles() {
		return sharedFiles;
	}

	/**
	 * @param sharedFiles new users set of shared files
	 */
	public void setSharedFiles(Set<File> sharedFiles) {
		this.sharedFiles = sharedFiles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", userRole=" + userRole + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userRole == null) {
			if (other.userRole != null)
				return false;
		} else if (!userRole.equals(other.userRole))
			return false;
		return true;
	}
}
