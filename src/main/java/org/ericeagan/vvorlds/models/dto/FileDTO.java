package org.ericeagan.vvorlds.models.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileDTO {
	String fileName;
	int fileType;
	MultipartFile file;
	
	public FileDTO() {
		super();
	}

	public FileDTO(String fileName, int fileType, MultipartFile file) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.file = file;
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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
