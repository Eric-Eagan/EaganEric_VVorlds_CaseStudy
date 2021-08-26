package org.ericeagan.vvorlds.services;

import java.util.List;

import org.ericeagan.vvorlds.models.File;

public interface FileService {
	/**
	 * Persist File in the DB
	 * 
	 * @param file to be persisted
	 * @return File after being modified by being persisted
	 */
	File save(File file);
	
	/**
	 * Get File from DB associated with id
	 * 
	 * @param id of File in th DB
	 * @return that File
	 */
	File getById(Integer id);
	
	/**
	 * Returns all files owned by User with id
	 * 
	 * @param id of User in DB
	 * @return list of Files owned by that User
	 */
	List<File> getByOwnerId(Integer id);
	
	/**
	 * Returns all files shared to the User with id
	 * 
	 * @param id of User in DB
	 * @return List of files shared to User
	 */
	List<File> getBySharerId(Integer id);
	
	/**
	 * Remove File from DB
	 * 
	 * @param file to be removed from DB
	 */
	void deleteFile(File file);
}
