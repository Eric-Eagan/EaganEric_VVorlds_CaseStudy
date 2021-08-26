package org.ericeagan.vvorlds.services;

import java.util.List;

import org.ericeagan.vvorlds.models.FileType;

public interface FileTypeService {
	/**
	 * Persist FileType in database
	 * 
	 * @param fileType to be saved 
	 * @return fileType after being modified by being persisted
	 */
	FileType save(FileType fileType);
	
	/**
	 * Get FileType associated with this id
	 * 
	 * @param id of a FileType in DB
	 * @return that FileType
	 */
	FileType getById(Integer id);
	
	/**
	 * Get FileType associated with that type name
	 * 
	 * @param type name of a FileType
	 * @return that FileType
	 */
	FileType getByType(String type);
	
	/**
	 * Get all the FileTypes from DB
	 * 
	 * @return List of all FileTypes stored
	 */
	List<FileType> getAllFileTypes();
	
	/**
	 * Delete FileType provided from the DB
	 * 
	 * @param fileType to be deleted
	 */
	void deleteFileType(FileType fileType);
}
