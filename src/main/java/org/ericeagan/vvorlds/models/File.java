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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "files")
public class File {
	@Id @Column(name="fileId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int fileId;
	
	@ManyToOne
	@JoinColumn(name="ownerId", nullable=false)
	@NotNull
	User owner;
	
	@ManyToMany(mappedBy="sharedFiles")
	Set<User> sharers;
	
	@ManyToOne
	@JoinColumn(name="fileTypeId", nullable=false)
	FileType fileType;
	
	@Column(name="fileName")
	@NotNull
	String fileName;
	
	@Column(name="path")
	@NotNull
	String path;

	public File() {
		
	}

	public File(@NotNull User owner, Set<User> sharers, FileType fileType, @NotNull String fileName,
			@NotNull String path) {
		super();
		this.owner = owner;
		this.sharers = sharers;
		this.fileType = fileType;
		this.fileName = fileName;
		this.path = path;
	}

	public int getFileId() {
		return fileId;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Set<User> getSharers() {
		return sharers;
	}

	public void setSharers(Set<User> sharers) {
		this.sharers = sharers;
	}

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

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
