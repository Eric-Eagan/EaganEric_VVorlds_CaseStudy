package org.ericeagan.vvorlds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.ericeagan.vvorlds.models.File;
import org.ericeagan.vvorlds.models.FileType;
import org.ericeagan.vvorlds.models.dto.FileDTO;
import org.ericeagan.vvorlds.services.FileService;
import org.ericeagan.vvorlds.services.UserService;

@Controller
public class FileController {
	private FileService fs;
	private UserService us;
	private String cuId = "currentUserId";
	
	@Autowired
	public FileController(FileService fs, UserService us) {
		this.fs = fs;
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
	public String uploadFile(Model model, HttpSession session, 
			@ModelAttribute("newFile")FileDTO file) throws IOException {
		
		if (!Objects.requireNonNull(file.getFile(), "Image file is null.").isEmpty()) {
			try(BufferedOutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(new java.io.File(
							(String)session.getAttribute("fileDir"), 
							file.getFile().getOriginalFilename())))) {
				outputStream.write(file.getFile().getBytes());
				outputStream.flush();
			}
		} else {
			model.addAttribute("msg", "No file selected");
			return "upload_confirmation";
		}
		model.addAttribute("msg", "File uploaded");
		
		File dbFile = new File(us.getById((Integer) session.getAttribute(cuId)), 
						new HashSet<>(), 
						new FileType(), //TODO
						file.getFileName(),
						(String)session.getAttribute("fileDir") + "\\" + file.getFile().getOriginalFilename());
		
		fs.save(dbFile);
		
		return "upload_confirmation";
	}
}
