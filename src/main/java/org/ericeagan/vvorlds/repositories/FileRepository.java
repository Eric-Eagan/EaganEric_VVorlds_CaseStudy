package org.ericeagan.vvorlds.repositories;

import java.util.List;

import org.ericeagan.vvorlds.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository extended Repository for accessing Files in DB
 * 
 * @author Eric
 *
 */
@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
	/**
	 * Get File with this id from DB
	 * @param id of a File in DB
	 * @return File associated with this id
	 */
	File getById(Integer id);
	
	/**
	 * Get Files owned by User with this id from DB
	 * @param id of a User in DB
	 * @return List of Files owned by this User
	 */
	List<File> getByOwnerId(Integer ownerId);
	
	/**
	 * Get Files shared with a User from the DB
	 * @param id of a USer in DB
	 * @return List of Files shared with this User
	 */
	List<File> getBySharers_Id(Integer userId);
}
