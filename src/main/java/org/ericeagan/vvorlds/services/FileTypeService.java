package org.ericeagan.vvorlds.services;

import java.util.List;

import org.ericeagan.vvorlds.models.FileType;

public interface FileTypeService {
	FileType save(FileType file);
	FileType getById(Integer id);
	List<FileType> getAllFileTypes();
	void deleteFileType(FileType fileType);
}
