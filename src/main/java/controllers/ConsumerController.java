package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ConsumerService;

import controllers.AbstractController;
import domain.Consumer;

@Controller
@RequestMapping("/consumer")
public class ConsumerController extends AbstractController{
	
	// Services --------------------------------------------------------------
	
	@Autowired
	private ConsumerService consumerService;

	// Constructors -----------------------------------------------------------

	public ConsumerController() {
		super();
	}
	
	// Listing ------------------------------------------------
		
	// Creation ---------------------------------------------
		
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Consumer consumer;
			
		consumer = consumerService.create();
			
		result = new ModelAndView("consumer/edit");
		result.addObject("consumer", consumer);
		result.addObject("actionURI", "consumer/edit.do");
			
		return result;
	}
		
	// Edition ---------------------------------------------------------------
		
	@RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
		public ModelAndView save(@Valid Consumer consumer, BindingResult binding){
			ModelAndView result;
			if(binding.hasErrors()){
				System.out.println(binding.getAllErrors().get(0));
				result=createEditModelAndView(consumer);
			}else{
				try{
					consumerService.save(consumer);
					result=new ModelAndView("redirect:/security/login.do");
				}catch(Throwable oops){
					result=createEditModelAndView(consumer, "consumer.commit.error");
					System.out.println(oops.getStackTrace());
				}
			}
				
			return result;
				
		}
		
	// Ancillary methods ---------------------------------------------------------------
		
	public ModelAndView createEditModelAndView(Consumer consumer){
		ModelAndView result;
			
		result=createEditModelAndView(consumer, null);
			
		return result;
			
	}
		
	public ModelAndView createEditModelAndView(Consumer consumer, String message){
		ModelAndView result;
			
		result=new ModelAndView("consumer/edit");
		result.addObject("consumer", consumer);
		result.addObject("message", message);
			
		return result;
			
	}
}
