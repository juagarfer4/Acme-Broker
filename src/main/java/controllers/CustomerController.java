/* CustomerController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Consumer;
import domain.Supplier;

import services.ConsumerService;
import services.SupplierService;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	// Services ---------------------------------------------------------
	
	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private SupplierService supplierService;
	
	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}

	// Listing ---------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Consumer> consumers;
		Collection<Supplier> suppliers;
		
		consumers=consumerService.findAll();
		suppliers=supplierService.findAll();
		
		result = new ModelAndView("customer/list");
		result.addObject("consumers", consumers);
		result.addObject("suppliers", suppliers);
		result.addObject("requestURI", "customer/list.do");

		return result;
	}
	
	// Creation --------------------------------------------
	
	// Edition -------------------------------------------
			
	// Ancillary methods -----------------------------------
}