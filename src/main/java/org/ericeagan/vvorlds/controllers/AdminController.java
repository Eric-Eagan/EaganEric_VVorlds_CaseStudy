package org.ericeagan.vvorlds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static org.ericeagan.vvorlds.controllers.MyErrorController.noticeSetup;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import org.ericeagan.vvorlds.models.FileType;
import org.ericeagan.vvorlds.services.FileTypeService;

@Controller
public class AdminController {
	/**
	 * Services for handling entities in database
	 */
	private FileTypeService fts;
	
	@Autowired
	public AdminController(FileTypeService fts) {
		this.fts = fts;
	}
	
	/**
	 * Admin only page for editing fileTypes
	 * 
	 * @param model for passing in list of fileTypes to display
	 * @return the name of changeFileType JSP to be sent to view
	 */
	@GetMapping("/changeFileTypes")
	public String changeFileTypes(Model model) {
		List<FileType> fileTypeList = fts.getAllFileTypes();
		
		model.addAttribute("fileTypes", fileTypeList);
		return "change_file_types";
	}
	
	/**
	 * Handler for accepting data on new fileType including icon image
	 * 
	 * @param actualFile the icon image from form
	 * @param typeName Name of new type
	 * @param model for notice setup
	 * @return the name of notice JSP to be sent to view
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@PostMapping("/changeFileTypes/{name}")
	public String addFileType(@RequestParam("image") MultipartFile actualFile, @RequestParam("typeName") String typeName,
			Model model) throws IOException, URISyntaxException {
		
		URL resource = getClass().getClassLoader().getResource("static/img");
		java.io.File imgFile = Paths.get(resource.toURI()).toFile();
		String path = imgFile.getAbsolutePath();
		
		if (!Objects.requireNonNull(actualFile, "Image is null.").isEmpty()) {
			try(BufferedOutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(new java.io.File(
							path, 
							actualFile.getOriginalFilename())))) {
				outputStream.write(actualFile.getBytes());
				outputStream.flush();
			}
		} else {
			noticeSetup(model, "No image selected", "changeFileTypes", "Return");
			return "notice";
		}
		noticeSetup(model, "FileType created", "changeFileTypes", "Return");
		
		FileType newType = new FileType(typeName, "img/"+actualFile.getOriginalFilename());
		
		fts.save(newType);
		
		return "notice";
	}
	
	/**
	 * Handler for deleting a particular FileType
	 * 
	 * @param id of fileType to be deleted
	 * @return redirect back to changFileTypes handler
	 * @throws URISyntaxException
	 */
	@PostMapping("/changeFileTypes/delete={id}")
	public String deleteFileType(@PathVariable int id) throws URISyntaxException {
		FileType doomed = fts.getById(id);
		URL resource = getClass().getClassLoader().getResource("static/"+doomed.getImgPath());
		try {
			Files.delete(Paths.get(resource.toURI()));
		} catch (IOException e) {
			e.getClass();
		}
		
		doomed.setImgPath("");
		doomed.setType("");
		fts.save(doomed);
		return "redirect:/changeFileTypes";
	}
}
