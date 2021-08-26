package org.ericeagan.vvorlds.repositories;

import org.ericeagan.vvorlds.models.FileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository extended Repository for accessing FileTypes in DB
 * 
 * @author Eric
 *
 */
@Repository
public interface FileTypeRepository extends JpaRepository<FileType, Integer> {
	/**
	 * Get FileType with this id from DB
	 * @param id of a FileType in DB
	 * @return FileType associated with this id
	 */
	FileType getById(Integer id);
	
	/**
	 * Get FileType with this type from DB
	 * @param name of type of a FileType in DB
	 * @return FileType associated with this type
	 */
	FileType getByType(String type);
}
