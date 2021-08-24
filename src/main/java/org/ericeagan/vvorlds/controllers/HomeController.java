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
	private String cu = "currentUser";
	
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
		
		session.setAttribute(cu, ((UserDetails)principal).getUsername());
		return "index";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/logoutUser")
	public String showLogoutPage(HttpSession session) {
		session.removeAttribute(cu);
		return "logout";
	}
	
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("newUser", new User());
		return "register";
	}
	
	@GetMapping("/account")
	public String showAccountPage(Model model, HttpSession session) {
		User user = us.getByUsername((String) session.getAttribute(cu));
		Account account = as.getById(user.getId());
		
		model.addAttribute("User", user);
		model.addAttribute("Account", account);
		return "account";
	}
	
	@GetMapping("/updatePassword")
	public String showUpdatePasswordPage() {
		return "update_password";
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
	
	@PostMapping("/updateAccount")
	public String updateAccount(HttpSession session, HttpServletRequest request,
			@ModelAttribute("account") Account account) {
		User user = us.getByUsername((String) session.getAttribute(cu));
		account.setUserId(user.getId());
		
		as.save(account);
		
		return "redirect:/account";
	}
	
	@PostMapping("/deleteAccount")
	public String deleteAccount(HttpSession session, HttpServletRequest request,
			@ModelAttribute("account") Account oldAccount) {
		User user = us.getByUsername((String) session.getAttribute(cu));
		Account account = as.getById(user.getId());

		as.deleteAccount(account);
		us.deleteUser(user);

		session.removeAttribute(cu);
		return "redirect:/login";
	}
	
	@PostMapping("/updatePassword")
	public String updatePassword(HttpServletRequest request, Model model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = us.getByUsername(((UserDetails)principal).getUsername());
		
		Map<String, String[]> paramMap = request.getParameterMap();
		
		if (us.validatePassword(currentUser, paramMap.get("oldPass")[0])) {
			currentUser.setPassword(paramMap.get("newPass")[0]);
			if(us.validateUser(currentUser)) {
				us.save(currentUser);
				return "redirect:/account";
			}else {
				model.addAttribute("errorMessage", "Password must be between 4 and 100 characters.");
				return "updatePassword";
			}
		}
		
		model.addAttribute("errorMessage", "Bad Credentials");
		return "updatePassword";
	}
}