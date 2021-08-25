package org.ericeagan.vvorlds.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "fileType")
public class FileType {
	@Id @Column(name="typeId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int typeId;
	
	@Column(name="type")
	@NotNull
	String type;
	
	@Column(name="imgPath")
	@NotNull
	String imgPath;
	
	public FileType() {
		
	}

	public FileType(@NotNull String type, @NotNull String imgPath) {
		super();
		this.type = type;
		this.imgPath = imgPath;
	}

	public int getTypeId() {
		return typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + typeId;
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
		FileType other = (FileType) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FileType [typeId=" + typeId + ", type=" + type + ", imgPath=" + imgPath + "]";
	}
}
