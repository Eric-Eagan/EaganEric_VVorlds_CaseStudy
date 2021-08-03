package vvorlds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vvorlds.models.User;
import vvorlds.services.UserService;
//test
@Controller
public class HomeController {
	private UserService us;
	
	@Autowired
	public HomeController(UserService ser) {
		this.us = ser;
	}
	
	@GetMapping("/")
	public String showStartPage() {
		return "index";
	}
	
	@GetMapping("/index.html")
	public String showIndexPage() {
		return "index";
	}
	
	@GetMapping("/login.html")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/resources.html")
	public String showResourcesPage() {
		return "resources";
	}
	
	@PostMapping("/login")
	public String validateLogin(@RequestParam String userName, @RequestParam String password) {
		User foundUser = us.findUserByUsername(userName);
		
		if (foundUser == null || foundUser.getPassword().equals(password)) {
			return "redirect:/login.html";
		}
		
		return "redirect:/index.html";
	}
}