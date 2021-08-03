package vvorlds.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//test
@Controller
public class HomeController {
	
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
	public String validateLogin() {
		return "redirect:/login.html";
	}
}