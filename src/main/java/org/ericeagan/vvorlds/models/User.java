package org.ericeagan.vvorlds.models;

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

@Entity
@Table(name = "users")
@NamedQuery(name="User.getAllUsers", query = "SELECT u FROM User u")
public class User {
	@Id @Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	@Column(name="username", unique=true)
	@NotNull
	@Size(min=2, max=25, message="Username must be between 2 and 25 characters.")
	String username;
	
	@Column(name="password")
	@NotNull
	@Size(min=4, max=100, message="Password must be between 4 and 100 characters.")
	String password;
	String userRole;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	Account account;
	
	@OneToMany(targetEntity = File.class, mappedBy = "owner")
	private Set<File> ownedFiles;
	
	@ManyToMany
	@JoinTable(
			  name = "FileSharedUser", 
			  joinColumns = @JoinColumn(name = "userId"), 
			  inverseJoinColumns = @JoinColumn(name = "fileId"))
	private Set<File> sharedFiles;
	
	public User() {
		this.userRole = "ROLE_USER";
	}
	
	public User(String username, String password) {
		this();
		this.username = username;
		this.password = password;
		this.account = new Account();
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getId() {
		return id;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Set<File> getOwnedFiles() {
		return ownedFiles;
	}

	public void setOwnedFiles(Set<File> ownedFiles) {
		this.ownedFiles = ownedFiles;
	}

	public Set<File> getSharedFiles() {
		return sharedFiles;
	}

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
