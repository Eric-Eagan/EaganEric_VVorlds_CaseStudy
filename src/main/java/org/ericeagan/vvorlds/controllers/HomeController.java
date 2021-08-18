package org.ericeagan.vvorlds.controllers;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.ericeagan.vvorlds.models.Account;
import org.ericeagan.vvorlds.models.User;
import org.ericeagan.vvorlds.services.AccountService;
import org.ericeagan.vvorlds.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	private UserService us;
	private AccountService as;
	
	@Autowired
	public HomeController(UserService uSer, AccountService aSer) {
		this.us = uSer;
		this.as = aSer;
	}
	
	@GetMapping("/")
	public String showStartPage(HttpSession session) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		System.out.println(authorities);
		
		session.setAttribute("currentUser", ((UserDetails)principal).getUsername());
		return "index";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/logoutUser")
	public String showLogoutPage(HttpSession session) {
		session.removeAttribute("currentUser");
		return "logout";
	}
	
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("newUser", new User());
		return "register";
	}
	
	@GetMapping("/account")
	public String showAccountPage() {
		return "account";
	}
	
	@GetMapping("/resources")
	public String showResourcesPage() {
		return "resources";
	}
	
	@PostMapping("/registerNewUser")
	public String registerUser(HttpServletRequest request,
			@Valid @ModelAttribute("newUser") User newUser,
			BindingResult result) {
		
		Map<String, String[]> paramMap = request.getParameterMap();
		
		
		if (us.getByUsername(paramMap.get("username")[0]) != null) {
			result.rejectValue("username", "error.user", "Username already taken.");
		}
		if (result.hasErrors()) {
			return "register";
		}
		
		Account newAcc = new Account(paramMap.get("firstName")[0], paramMap.get("lastName")[0], 
				paramMap.get("email")[0], paramMap.get("address")[0], paramMap.get("phone")[0], 
				newUser);
		
		newUser.setAccount(newAcc);
		
		us.save(newUser);
		as.save(newAcc);
		
		return "redirect:/login";
	}
}