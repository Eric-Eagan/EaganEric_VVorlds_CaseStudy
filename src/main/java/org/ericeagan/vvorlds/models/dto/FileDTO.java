package org.ericeagan.vvorlds.models.dto;

public class FileDTO {
	String fileName;
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
