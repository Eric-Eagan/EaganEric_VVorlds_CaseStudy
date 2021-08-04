package vvorlds.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import vvorlds.models.Account;
import vvorlds.models.User;
import vvorlds.services.UserService;
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
	
	@GetMapping("/register.html")
	public String showRegisterPage() {
		return "register";
	}
	
	@GetMapping("/resources.html")
	public String showResourcesPage() {
		return "resources";
	}
	
	@PostMapping("/login")
	public String validateLogin(HttpServletRequest request) {
		Map<String, String[]> paramMap = request.getParameterMap();
		
		User foundUser = us.findUserByUsername(paramMap.get("userName")[0]);
		
		if (foundUser == null || !foundUser.getPassword().equals(paramMap.get("password")[0])) {
			System.out.println("Bad Login");
			return null;
		}
		return "redirect:/index.html";
	}
	
	@PostMapping("/register")
	public String registerUser(HttpServletRequest request) {
		Map<String, String[]> paramMap = request.getParameterMap();
		
		User newUser = new User(paramMap.get("userName")[0], paramMap.get("password")[0]);
		Account newAcc = new Account(paramMap.get("firstName")[0], paramMap.get("lastName")[0], 
				paramMap.get("email")[0], paramMap.get("address")[0], paramMap.get("phone")[0], 
				newUser);
		
		newUser.setAccount(newAcc);
		
		us.createUser(newUser, newAcc);
		
		return "redirect:/login.html";
	}
}