package org.ericeagan.vvorlds.repositories;

import java.util.List;

import org.ericeagan.vvorlds.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
	File getById(Integer id);
	List<File> getByOwnerId(Integer ownerId);
	List<File> getBySharers_Id(Integer userId);
}
