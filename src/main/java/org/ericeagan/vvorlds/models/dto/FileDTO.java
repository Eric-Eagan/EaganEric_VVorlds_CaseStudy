package org.ericeagan.vvorlds.models.dto;

import javax.validation.constraints.NotNull;

/**
	 * DTO for retrieving relevant data from view
	 * 
	 * @author Eric
	 *
	 */
public class FileDTO {
	/**
	 * Name of the file to visible to users
	 */
	@NotNull(message="File name must not be null")
	String fileName;
	
	/**
	 * id of fileType for this File
	 */
	@NotNull
	int fileType;
	
	public FileDTO() {
		super();
	}

	public FileDTO(String fileName, int fileType) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}
}
