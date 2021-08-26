package org.ericeagan.vvorlds.controllers;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for handling errors
 * 
 * @author Eric
 *
 */
@Controller
public class MyErrorController implements ErrorController {
	static Model noticeSetup(Model model, String msg, String send, String sendName) {
		
		model.addAllAttributes(Map.of(
			    "msg", msg, 
			    "send",send, 
			    "sendName",sendName));
		
		return model;
	}
	
	/**
	 * error handler, directs to relevant error JSP 
	 * 
	 * @param request to get Error code
	 * @return string of name to relevant error JSP
	 */
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	    
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return "error-404";
	        }
	    }
	    return "error";
	}
	
	/**
	 * Special access to notice using session
	 * Sets up model with message to be displayed, also cleans session of old message
	 * 
	 * @param session for accessing message
	 * @param model assigning message for display
	 * @return the name of notice JSP to be sent to view
	 */
	@GetMapping("/notice")
	public String messageDisplay(HttpSession session, Model model) {
		MyErrorController.noticeSetup(model, (String) session.getAttribute("msg"), "files", "Documents");
		session.removeAttribute("msg");
		return "notice";
	}
}
