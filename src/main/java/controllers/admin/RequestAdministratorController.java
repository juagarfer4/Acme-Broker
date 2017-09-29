package controllers.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import services.RequestService;
import domain.Request;

@Controller
@RequestMapping("/request/administrator")
public class RequestAdministratorController extends AbstractController{
	
	// Services ------------------------------------------
	
	@Autowired
	private RequestService requestService;
				
	// Constructors -----------------------------------
				
	public RequestAdministratorController(){
		super();
	}
				
	// Listing --------------------------------------------
				
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Request> requests;
					
		requests=requestService.findAllRequestNotSignedNotCancelled();
					
		result=new ModelAndView("request/administrator/list");
		result.addObject("requests", requests);
		result.addObject("requestURI", "request/administrator/list.do");
					
		return result;
	}
		
	// Creation --------------------------------------------
		
	// Edition -------------------------------------------
	
	// Ancillary methods -----------------------------------

}
