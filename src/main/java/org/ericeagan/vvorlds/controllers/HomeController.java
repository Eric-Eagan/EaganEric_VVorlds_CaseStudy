package org.ericeagan.vvorlds.controllers;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.ericeagan.vvorlds.exceptions.UserNotFoundException;
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

/**
 * General Controller for home page, initial setup, account and resources
 * Likely could be divided further
 * 
 * @author Eric
 *
 */
@Controller
public class HomeController {
	/**
	 * Services for handling entites in database
	 */
	private UserService us;
	private AccountService as;
	
	/**
	 * string constants
	 */
	private static final String CU = "currentUser";
	private static final String CUID = "currentUserId";
	
	/**
	 * Autowired constructor to inject services
	 * 
	 * @param us Service for handling User Entities 
	 * @param as Service for handling Account Entities
	 */
	@Autowired
	public HomeController(UserService us, AccountService as) {
		this.us = us;
		this.as = as;
	}
	
	/**
	 * Sets up currentuser attributes and directs to main page
	 * If user not found, instead dirests to error page
	 * 
	 * @param session for assigning current user attributes
	 * @param model for providing message in case of error
	 * @return the name of index JSP to be sent to view
	 */
	@GetMapping("/")
	public String showStartPage(HttpSession session, Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		System.out.println(authorities);
		
		User user;
		try {
			user = us.getByUsername(((UserDetails)principal).getUsername());
		} catch (UserNotFoundException e) {
			MyErrorController.noticeSetup(model, e.getMessage(), "logout", "Logout");
			return "notice";
		}
		
		session.setAttribute(CU, user.getUsername());
		session.setAttribute(CUID, user.getId());
		session.setAttribute("fileDir", "D:\\VVorldsFiles");
		return "index";
	}
	
	/**
	 * Directs to login page
	 * 
	 * @return the name of login JSP to be sent to view
	 */
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	/**
	 * Clears current user session attributes for logging out
	 * 
	 * @param session for removing the attributes
	 * @return the name of logout JSP to be sent to view
	 */
	@GetMapping("/logoutUser")
	public String showLogoutPage(HttpSession session) {
		session.removeAttribute(CU);
		session.removeAttribute(CUID);
		return "logout";
	}
	
	/**
	 * Sets up empty user for regsitration for then directs to it
	 * 
	 * @param model for adding empty User entity
	 * @return the name of registration JSP to be sent to view
	 */
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("newUser", new User());
		return "register";
	}
	
	/**
	 * Retrieves current User entity to be displayed then directs to account page
	 * 
	 * @param model for adding empty User entity
	 * @param session for retrieving current user id
	 * @return the name of account JSP to be sent to view
	 */
	@GetMapping("/account")
	public String showAccountPage(Model model, HttpSession session) {
		User user = us.getById((Integer) session.getAttribute(CUID));
		Account account = as.getById(user.getId());
		
		model.addAttribute("User", user);
		model.addAttribute("Account", account);
		return "account";
	}
	
	/**
	 * Directs to update_password page
	 * 
	 * @return the name of update_password JSP to be sent to view
	 */
	@GetMapping("/updatePassword")
	public String showUpdatePasswordPage() {
		return "update_password";
	}
	
	/**
	 * Directs to resources page
	 * 
	 * @return the name of resources JSP to be sent to view
	 */
	@GetMapping("/resources")
	public String showResourcesPage() {
		return "resources";
	}
	
	/**
	 * Creates new user and account and adds to DB then redirects to login handler
	 * If username is taken or input is invalid, directs back to register page
	 * 
	 * @param request for retrieving user input to create account
	 * @param newUser User entity with user input included
	 * @param result for determining if input is valid
	 * @return String for redirecting to login handler
	 */
	@PostMapping("/registerNewUser")
	public String registerUser(HttpServletRequest request,
			@Valid @ModelAttribute("newUser") User newUser,
			BindingResult result) {
		
		Map<String, String[]> paramMap = request.getParameterMap();
		
		if (!us.availableUsername(paramMap.get("username")[0])) {
			result.rejectValue("username", "error.user", "Username already taken.");
		}
		if (result.hasErrors()) {
			return "register";
		}
		
		Account newAcc = new Account(paramMap.get("firstName")[0], paramMap.get("lastName")[0], 
				paramMap.get("email")[0], paramMap.get("address")[0], paramMap.get("phone")[0], 
				newUser);
		
		newUser.setAccount(newAcc);
		
		us.createUser(newUser);
		as.save(newAcc);
		
		return "redirect:/login";
	}
	
	/**
	 * Changes account of current user based on user input
	 * 
	 * @param session for retrieving current user's id
	 * @param account entity from form to be used to update DB
	 * @return string to redirect to account handler
	 */
	@PostMapping("/updateAccount")
	public String updateAccount(HttpSession session, @ModelAttribute("account") Account account) {
		User user = us.getById((Integer) session.getAttribute(CUID));
		account.setUserId(user.getId());
		
		as.save(account);
		
		return "redirect:/account";
	}
	
	/**
	 * Deletes User and Account entities from DB associated with Current user
	 * 
	 * @param session for retrieving current user id
	 * @return string to redirect to login handler
	 */
	@PostMapping("/deleteAccount")
	public String deleteAccount(HttpSession session) {
		User user = us.getById((Integer) session.getAttribute(CUID));
		Account account = as.getById(user.getId());

		as.deleteAccount(account);
		us.deleteUser(user);

		session.removeAttribute(CU);
		session.removeAttribute(CUID);
		return "redirect:/login";
	}
	
	/**
	 * Updates password from input given by user
	 * If successful, sends user to account page, otherwise back to update page 
	 * 
	 * @param request for retrieving new password input by user
	 * @param session for retrieving current user id
	 * @param model for providing error messages back to user
	 * @return string directing to account handler or back to updatePassword page
	 */
	@PostMapping("/updatePassword")
	public String updatePassword(HttpServletRequest request, HttpSession session, Model model) {
		User currentUser;
		try {
			currentUser = us.getByUsername((String) session.getAttribute(CU));
		} catch (UserNotFoundException e) {
			MyErrorController.noticeSetup(model, "You don't exist... How do you not exist?", "logout", "Logout");
			return "notice";
		}
		Map<String, String[]> paramMap = request.getParameterMap();
		
		if (us.validatePassword(currentUser, paramMap.get("oldPass")[0])) {
			currentUser.setPassword(paramMap.get("newPass")[0]);
			if(us.validateUser(currentUser)) {
				us.createUser(currentUser);
				return "redirect:/account";
			}else {
				model.addAttribute("errorMessage", "Password must be between 4 and 100 characters.");
				return "update_password";
			}
		}
		
		model.addAttribute("errorMessage", "Bad Credentials");
		return "update_password";
	}
}