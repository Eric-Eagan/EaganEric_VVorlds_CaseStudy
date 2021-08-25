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
				us.save(user);
			}		   };
	}
	/*
	@Bean
	public CommandLineRunner insertFileTypes(FileTypeService fts) {
		return args -> {
			FileType doc = fts.
			FileType img = new FileType("Image", "img/image.png");
			
			if (user == null) {
				doc = new FileType("Document", "img/document.png");
				Account account = new Account();
				user.setUserRole("ROLE_ADMIN");
				user.setAccount(account);
				account.setUser(user);
				us.save(user);
			}
			
			if (user == null) {
				img = new FileType("Image", "img/image.png");
				Account account = new Account();
				user.setUserRole("ROLE_ADMIN");
				user.setAccount(account);
				account.setUser(user);
				us.save(user);
			}		   };
	}*/
}
