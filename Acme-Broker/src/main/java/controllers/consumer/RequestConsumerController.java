package controllers.consumer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RequestService;

import controllers.AbstractController;
import domain.Contract;
import domain.Request;

@Controller
@RequestMapping("/request/consumer")
public class RequestConsumerController extends AbstractController{
	
	// Services ------------------------------
	
	@Autowired
	private RequestService requestService;
			
	// Constructors --------------------------
			
	public RequestConsumerController(){
		super();
	}
			
	// Listing ----------------------------------
			
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Request> requests;
				
		requests=requestService.findAllRequestsByConsumer();
				
		result=new ModelAndView("request/consumer/list");
		result.addObject("requests", requests);
		result.addObject("requestURI", "request/consumer/list.do");
				
		return result;
	}
			
	// Creation -------------------------------
		
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Request request;
				
		request=requestService.create();
				
		result=new ModelAndView("request/consumer/edit");
		result.addObject("request", request);
		result.addObject("actionURI", "request/consumer/edit.do");
				
		return result;
				
	}
			
	// Edition -----------------------------------------
			
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int requesttId){
		ModelAndView result;
		Request request;
			
		request = requestService.findOne(requesttId);
		
		Assert.notNull(request);
				
		requestService.cancelRequest(request);
				
		result = createEditModelAndView(request);
				
		return result;
	}
			
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Request request, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			System.out.print(binding.getAllErrors().get(0));
			result=createEditModelAndView(request);
		}else{
			try{
				requestService.save(request);
				result=new ModelAndView("redirect:list.do");
			}catch (Throwable oops){
				result=createEditModelAndView(request, "request.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}
		}
				
		result.addObject("actionURI", "request/consumer/edit.do");
		return result;
	}
			
	// Ancillary methods -------------------------------
			
	protected ModelAndView createEditModelAndView(Request request){
		ModelAndView result;
				
		result=createEditModelAndView(request, null);
				
		return result;
	}
			
	protected ModelAndView createEditModelAndView(Request request, String message){
		ModelAndView result;
		Contract contract;
				
		if(request.getContract()==null){
			contract=null;
		}
		else{
			contract=request.getContract();
		}
				
		result=new ModelAndView("request/consumer/edit");
		result.addObject("request", request);
		result.addObject("contract", contract);
		result.addObject("message", message);
				
		return result;
	}

}
