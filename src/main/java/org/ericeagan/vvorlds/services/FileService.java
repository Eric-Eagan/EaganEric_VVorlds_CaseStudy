package org.ericeagan.vvorlds.services;

import java.util.List;

import org.ericeagan.vvorlds.models.File;

public interface FileService {
	File save(File file);
	File getById(Integer id);
	List<File> getByOwnerId(Integer id);
	List<File> getBySharerId(Integer id);
	void deleteFile(File file);
}
