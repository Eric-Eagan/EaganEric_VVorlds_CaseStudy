package org.ericeagan.vvorlds.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * File entity for storing information about uploaded files in DB
 * 
 * @author Eric
 *
 */
@Entity
@Table(name = "files")
@NamedQuery(name="File.getAllFiles", query = "SELECT f FROM File f")
public class File {
	/**
	 * id in DB of File
	 */
	@Id @Column(name="fileId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int fileId;
	
	/**
	 * Many-1 associated User who uploaded this file
	 */
	@ManyToOne
	@JoinColumn(name="ownerId", nullable=false)
	@NotNull
	User owner;
	
	/**
	 * Many-Many association, set of users this file is shared to
	 */
	@ManyToMany(mappedBy="sharedFiles")
	Set<User> sharers;
	
	/**
	 * Many-1 association, FileType associaated with this file
	 */
	@ManyToOne
	@JoinColumn(name="fileTypeId", nullable=false)
	FileType fileType;
	
	/**
	 * data associated with File
	 */
	@Column(name="fileName")
	@NotNull
	String fileName;
	@Column(name="path")
	@NotNull
	String path;

	/**
	 * no-arg constructor
	 */
	public File() {
	}

	/**
	 * Constructor for creating a file with fields populated
	 * 
	 * @param owner User entity that uploaded this file
	 * @param sharers Set of Users this file is shared with, likely emptyset
	 * @param fileType FileType defining the type of this file
	 * @param fileName Name of this file to be displayed
	 * @param path to file 
	 */
	public File(@NotNull User owner, Set<User> sharers, FileType fileType, @NotNull String fileName,
			@NotNull String path) {
		super();
		this.owner = owner;
		this.sharers = sharers;
		this.fileType = fileType;
		this.fileName = fileName;
		this.path = path;
	}

	/**
	 * @return this File's id
	 */
	public int getFileId() {
		return fileId;
	}

	/**
	 * @return  this File's User owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner User owning this File
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * @return this File's set of shared users
	 */
	public Set<User> getSharers() {
		return sharers;
	}

	/**
	 * @param sharers set to be assigned to this File
	 */
	public void setSharers(Set<User> sharers) {
		this.sharers = sharers;
	}

	/**
	 * @return this File's FileType
	 */
	public FileType getFileType() {
		return fileType;
	}

	/**
	 * @param fileType to set this File to
	 */
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return this File's filename
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName to be applied to this File
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return this File's path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path of this file
	 */
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "File [fileId=" + fileId + ", fileName=" + fileName + ", path=" + path + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
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
		File other = (File) obj;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		return true;
	}
}
