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
}
