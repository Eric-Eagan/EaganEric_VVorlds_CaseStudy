package org.ericeagan.vvorlds.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller Advice for if file upload fails
 * 
 * @author Eric
 *
 */
@ControllerAdvice
public class FileUploadExceptionAdvice {

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView handleMaxSeizeException(
			MaxUploadSizeExceededException exc, 
		    HttpServletRequest request,
		    HttpServletResponse response) {
		
		ModelAndView modelAndView = new ModelAndView("file");
		modelAndView.getModel().put("msg", "File too big, max is about 1MB");
		modelAndView.setViewName("upload_confirmation");
		return modelAndView;
	}
}
