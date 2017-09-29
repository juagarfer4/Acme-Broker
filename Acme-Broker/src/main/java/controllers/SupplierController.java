package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SupplierService;

import controllers.AbstractController;
import domain.Supplier;

@Controller
@RequestMapping("/supplier")
public class SupplierController extends AbstractController{
	
	// Services --------------------------------------------------------------
	
	@Autowired
	private SupplierService supplierService;
	
	// Constructors -----------------------------------------------------------
	
	public SupplierController() {
		super();
	}
	
	// Listing -----------------------------------------
	
	// Creation ---------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Supplier supplier;
		
		supplier = supplierService.create();
		
		result = new ModelAndView("supplier/edit");
		result.addObject("supplier", supplier);
		result.addObject("actionURI", "supplier/edit.do");
		
		return result;
	}
	
	// Edition ---------------------------------------------------------------
	
	@RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Supplier supplier, BindingResult binding){
		ModelAndView result;
			
		if(binding.hasErrors()){
			System.out.println(binding.getAllErrors().get(0));
			result=createEditModelAndView(supplier);
		}else{
			try{
				supplierService.save(supplier);
				result=new ModelAndView("redirect:/security/login.do");
			}catch(Throwable oops){
				result=createEditModelAndView(supplier, "supplier.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}
		}
			
		return result;
	}
	
	// Ancillary methods ---------------------------------------------------------------
	
	public ModelAndView createEditModelAndView(Supplier supplier){
		ModelAndView result;
		
		result=createEditModelAndView(supplier, null);
			
		return result;
	}
	
	public ModelAndView createEditModelAndView(Supplier supplier, String message){
		ModelAndView result;
		
		result=new ModelAndView("supplier/edit");
		result.addObject("supplier", supplier);
		result.addObject("message", message);
		
		return result;
	}
}
