package org.ericeagan.vvorlds.services.impl;

import java.util.List;

import org.ericeagan.vvorlds.models.File;
import org.ericeagan.vvorlds.repositories.FileRepository;
import org.ericeagan.vvorlds.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;

public class FileServiceImpl implements FileService {
	private FileRepository fr;
	
	@Autowired
	public FileServiceImpl(FileRepository fr) {
		this.fr = fr;
	}

	@Override
	public File save(File file) {
		return fr.save(file);
	}

	@Override
	public File getById(Integer id) {
		return fr.getById(id);
	}

	@Override
	public List<File> getByOwnerId(Integer id) {
		return fr.getByOwnerId(id);
	}

	@Override
	public List<File> getBySharerId(Integer id) {
		return fr.getBySharers_Id(id);
	}

	@Override
	public void deleteFile(File file) {
		fr.delete(file);
	}
}
