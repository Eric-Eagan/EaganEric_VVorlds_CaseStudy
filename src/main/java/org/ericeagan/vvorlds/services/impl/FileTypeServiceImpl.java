package org.ericeagan.vvorlds.services.impl;

import java.util.List;

import org.ericeagan.vvorlds.models.FileType;
import org.ericeagan.vvorlds.repositories.FileTypeRepository;
import org.ericeagan.vvorlds.services.FileTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of FileTypeService
 * 
 * @author Eric
 *
 */
@Service
public class FileTypeServiceImpl implements FileTypeService {
	/**
	 * Repository for accessing DB
	 */
	private FileTypeRepository ftr;
	
	/**
	 * Autowired Constructor for injecting FileTypeRepository
	 * 
	 * @param ftr
	 */
	@Autowired
	public FileTypeServiceImpl(FileTypeRepository ftr) {
		this.ftr = ftr;
	}

	@Override
	public FileType save(FileType fileType) {
		return ftr.save(fileType);
	}

	@Override
	public FileType getById(Integer id) {
		return ftr.getById(id);
	}

	@Override
	public FileType getByType(String type) {
		return ftr.getByType(type);
	}

	@Override
	public List<FileType> getAllFileTypes() {
		return ftr.findAll();
	}

	@Override
	public void deleteFileType(FileType fileType) {
		ftr.delete(fileType);
	}
}
