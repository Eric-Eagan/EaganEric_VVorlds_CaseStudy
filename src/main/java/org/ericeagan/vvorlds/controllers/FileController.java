package org.ericeagan.vvorlds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.ericeagan.vvorlds.models.File;
import org.ericeagan.vvorlds.models.dto.FileDTO;
import org.ericeagan.vvorlds.services.FileService;
import org.ericeagan.vvorlds.services.FileTypeService;
import org.ericeagan.vvorlds.services.UserService;

@Controller
public class FileController {
	private FileService fs;
	private FileTypeService fts;
	private UserService us;
	private String cuId = "currentUserId";
	
	@Autowired
	public FileController(FileService fs, FileTypeService fts, UserService us) {
		this.fs = fs;
		this.fts = fts;
		this.us = us;
	}
	
	@GetMapping("/files")
	public String showFilesPage(HttpSession session, Model model) {
		List<File> ownedList = fs.getByOwnerId((Integer) session.getAttribute(cuId));
		List<File> shareList = fs.getBySharerId((Integer) session.getAttribute(cuId));
		
		model.addAttribute("ownedFiles", ownedList);
		model.addAttribute("shareFiles", shareList);
		
		return "files";
	}
	
	@GetMapping("/uploadFile")
	public String showUploadPage(HttpSession session, Model model) {
		model.addAttribute("newFile", new FileDTO());
		return "upload_file";
	}
	
	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile actualFile, Model model, HttpSession session, 
			@ModelAttribute("newFile")FileDTO file) throws IOException {
		
		if (!Objects.requireNonNull(actualFile, "Image file is null.").isEmpty()) {
			try(BufferedOutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(new java.io.File(
							(String)session.getAttribute("fileDir"), 
							actualFile.getOriginalFilename())))) {
				outputStream.write(actualFile.getBytes());
				outputStream.flush();
			}
		} else {
			model.addAttribute("msg", "No file selected");
			return "upload_confirmation";
		}
		model.addAttribute("msg", "File uploaded");
		
		File dbFile = new File(us.getById((Integer) session.getAttribute(cuId)), 
						new HashSet<>(), 
						fts.getById(file.getFileType()),
						file.getFileName(),
						(String)session.getAttribute("fileDir") + "\\" + actualFile.getOriginalFilename());
		
		fs.save(dbFile);
		
		return "upload_confirmation";
	}
}
