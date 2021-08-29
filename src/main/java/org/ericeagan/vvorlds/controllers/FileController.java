package org.ericeagan.vvorlds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.ericeagan.vvorlds.exceptions.UserNotFoundException;
import org.ericeagan.vvorlds.models.File;
import org.ericeagan.vvorlds.models.FileType;
import org.ericeagan.vvorlds.models.User;
import org.ericeagan.vvorlds.models.dto.FileDTO;
import org.ericeagan.vvorlds.services.FileService;
import org.ericeagan.vvorlds.services.FileTypeService;
import org.ericeagan.vvorlds.services.UserService;

/**
 * Controller for the files page and the file entities
 * 
 * @author Eric
 *
 */
@Controller
public class FileController {
	/**
	 * Services for handling entites in database
	 */
	private FileService fs;
	private FileTypeService fts;
	private UserService us;
	
	/**
	 * string constants
	 */
	private static final String CU = "currentUser";
	private static final String CUID = "currentUserId";
	private static final String FILEDIR = "fileDir";
	private static final String NOTICE_JSP = "notice";
	private static final String FILES_JSP = "files";
	private static final String DOCUMENTS = "Documents";
	
	/**
	 * Autowired constructor to inject services
	 * 
	 * @param fs Service for handling File Entities 
	 * @param fts Service for handling FileType Entities
	 * @param us Service for handling User Entities
	 */
	@Autowired
	public FileController(FileService fs, FileTypeService fts, UserService us) {
		this.fs = fs;
		this.fts = fts;
		this.us = us;
	}
	
	/**
	 * Sets up model with Files owned by and shared with current user
	 * Then directs to the files JSP
	 * 
	 * @param session For retrieving the current user
	 * @param model For assigning the lists of files
	 * @return the name of files JSP to be sent to view
	 */
	@GetMapping("/files")
	public String showFilesPage(HttpSession session, Model model) {
		List<File> ownedList = fs.getByOwnerId((Integer) session.getAttribute(CUID));
		List<File> shareList = fs.getBySharerId((Integer) session.getAttribute(CUID));
		
		model.addAttribute("ownedFiles", ownedList);
		model.addAttribute("shareFiles", shareList);
		
		return FILES_JSP;
	}
	
	/**
	 * Sets up new file to be uploaded then directs to the upload_file JSP
	 * 
	 * @param model For assigning the empty file
	 * @return the name of upload_file JSP to be sent to view
	 */
	@GetMapping("/uploadFile")
	public String showUploadPage(Model model) {
		List<FileType> fileTypeList = fts.getAllFileTypes();
		
		model.addAttribute("newFile", new FileDTO());
		model.addAttribute("fileTypes", fileTypeList);
		return "upload_file";
	}
	
	/**
	 * Downloads file provided and places associated File Entity in database
	 * 
	 * @param actualFile is the file to be uploaded
	 * @param model for returning a message
	 * @param session for retrieving current user
	 * @param file DTO for retrieving user input from form
	 * @return the name of upload_confirmation JSP to be sent to view
	 * @throws IOException
	 */
	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile actualFile, Model model, HttpSession session, 
			@Valid @ModelAttribute("newFile")FileDTO file) throws IOException {
		
		java.io.File directory = new java.io.File((String)session.getAttribute(FILEDIR));
		 if (! directory.exists()){
			directory.mkdir();
	    }
		
		if (!Objects.requireNonNull(actualFile, "File is null.").isEmpty()) {
			try(BufferedOutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(new java.io.File(
							(String)session.getAttribute(FILEDIR), 
							actualFile.getOriginalFilename())))) {
				outputStream.write(actualFile.getBytes());
				outputStream.flush();
			}
		} else {
			MyErrorController.noticeSetup(model, "No file selected", FILES_JSP, DOCUMENTS);
			return NOTICE_JSP;
		}
		MyErrorController.noticeSetup(model, "File uploaded", FILES_JSP, DOCUMENTS);
		
		File dbFile = new File(us.getByUsername((String) session.getAttribute(CU)), 
						new HashSet<>(), 
						fts.getById(file.getFileType()),
						file.getFileName(),
						"/"+actualFile.getOriginalFilename());
		
		fs.save(dbFile);
		
		return NOTICE_JSP;
	}
	
	/**
	 * Delete file associated with id provided
	 * 
	 * @param id of the file in the DB to be deleted
	 * @return string redirecting to /files handler
	 */
	@PostMapping("/delete_file/{id}")
	public String deleteFile(HttpSession session, @PathVariable int id) {
		File file = fs.getById(id);
		
		try {
			Files.delete(Paths.get(session.getAttribute(FILEDIR) + file.getPath()));
		} catch (IOException e) {
			e.getClass();
		}

		Set<User> sharers = file.getSharers();
		for(User share : sharers) {
			share.getSharedFiles().remove(file);
			us.save(share);
		}
		
		file.setSharers(new HashSet<>());
		fs.save(file);
		
		fs.deleteFile(file);
		
		return "redirect:/files";
	}
	
	/**
	 * Shares file associated with id with user username
	 * Unshares if already shared with that user
	 * Updates DB to represent this
	 * 
	 * @param model for returning a message
	 * @param id of file to be shared
	 * @param username of user to be shared/unshared with
	 * @return the name of notice JSP to be sent to view
	 */
	@PostMapping("/share_file/{id}/{username}")
	public String shareFile(Model model, @PathVariable int id, @PathVariable String username) {
		
		User user;
		try {
			user = us.getByUsername(username);
			if (user == null)
				throw new UserNotFoundException("User not found");
		} catch (UserNotFoundException e) {
			MyErrorController.noticeSetup(model, "User " + username + " does not exist", FILES_JSP, DOCUMENTS);
			return NOTICE_JSP;
		}
		
		File file = fs.getById(id);
		if (file.getSharers().contains(user)) {
			user.getSharedFiles().remove(file);
			file.getSharers().remove(user);
			MyErrorController.noticeSetup(model, "File unshared with "+username, FILES_JSP, DOCUMENTS);
		}else {
			user.getSharedFiles().add(file);
			file.getSharers().add(user);
			MyErrorController.noticeSetup(model, "File shared with "+username, FILES_JSP, DOCUMENTS);
		}
		
		us.save(user);
		fs.save(file);
		
		return NOTICE_JSP;
	}
	
	/**
	 * Download file associated with id provided
	 * 
	 * @param session for delivering message to other handler
	 * @param id of file to be downloaded
	 * @param httpServletResponse for redirecting if needed
	 * @return ResponseEntity with resource to be downloaded, or null if file not found
	 */
	@GetMapping("/download_file/{id}")
	public ResponseEntity<Resource> downloadFileFromLocal(HttpSession session, @PathVariable int id,
			HttpServletResponse httpServletResponse) {
		File file = fs.getById(id);
		
		System.out.println(session.getAttribute(FILEDIR) + file.getPath());
		java.io.File test = new java.io.File(session.getAttribute(FILEDIR) + file.getPath());
		if (!test.exists()) {
			session.setAttribute("msg", "That file is missing, sorry. I'll remove it for you.");
			deleteFile(session, id);
			httpServletResponse.setHeader("Location", "/notice");
			httpServletResponse.setStatus(302);
			return null;
		}
		
		Path path = Paths.get(session.getAttribute(FILEDIR) + file.getPath());
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if (resource == null) {
			session.setAttribute("msg", "That file path was strange.");
			httpServletResponse.setHeader("Location", "/notice");
			httpServletResponse.setStatus(302);
			return null;
		}
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
