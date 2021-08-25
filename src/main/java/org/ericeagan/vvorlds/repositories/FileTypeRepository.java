package org.ericeagan.vvorlds.repositories;

import org.ericeagan.vvorlds.models.FileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileTypeRepository extends JpaRepository<FileType, Integer> {
	FileType getById(Integer id);
	FileType getByType(String type);
}
