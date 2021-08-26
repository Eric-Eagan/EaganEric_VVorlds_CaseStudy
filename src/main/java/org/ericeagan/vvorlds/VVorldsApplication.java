package org.ericeagan.vvorlds;

import org.ericeagan.vvorlds.models.Account;
import org.ericeagan.vvorlds.models.FileType;
import org.ericeagan.vvorlds.models.User;
import org.ericeagan.vvorlds.services.FileTypeService;
import org.ericeagan.vvorlds.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VVorldsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VVorldsApplication.class, args);
	}
	
	/**
	 * setup default admin account
	 * 
	 * @param us UserService for adding to DB
	 * @return CommandLineRunner
	 */
	@Bean
	public CommandLineRunner insertEmployeeRecords(UserService us) {
		return args -> {
			User user = us.getByUsername("admin");
			
			if (user == null) {
				user = new User("admin", "admin");
				Account account = new Account();
				user.setUserRole("ROLE_ADMIN");
				user.setAccount(account);
				account.setUser(user);
				us.createUser(user);
			}		   };
	}
	
	/**
	 * setup default FileTypes
	 * 
	 * @param fts FileTypeService for adding to DB
	 * @return CommandLineRunner
	 */
	@Bean
	public CommandLineRunner insertFileTypes(FileTypeService fts) {
		return args -> {
			FileType doc = fts.getByType("Document");
			FileType img = fts.getByType("Image");
			
			if (doc == null) {
				doc = new FileType("Document", "img/document.png");
				fts.save(doc);
			}
			
			if (img == null) {
				img = new FileType("Image", "img/image.png");
				fts.save(img);
			}		   };
	}
}
