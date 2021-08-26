package org.ericeagan.vvorlds.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * FileType Entity for storing types and paths to associated image
 * 
 * @author Eric
 *
 */
@Entity
@Table(name = "fileType")
public class FileType {
	/**
	 * id of this FileType
	 */
	@Id @Column(name="typeId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int typeId;
	
	/**
	 * date about this fileType
	 */
	@Column(name="type")
	@NotNull
	String type;
	@Column(name="imgPath")
	@NotNull
	String imgPath;
	
	/**
	 * no-arg constructor
	 */
	public FileType() {
	}

	/**
	 * Constructor for making new FileType with fields populated
	 * 
	 * @param type
	 * @param imgPath
	 */
	public FileType(@NotNull String type, @NotNull String imgPath) {
		super();
		this.type = type;
		this.imgPath = imgPath;
	}

	/**
	 * @return the id of this FileType
	 */
	public int getTypeId() {
		return typeId;
	}

	/**
	 * @return the name of this FileType
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type name to be applied
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the path to the image of this FileType
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * @param imgPath to the image for this FileType
	 */
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
