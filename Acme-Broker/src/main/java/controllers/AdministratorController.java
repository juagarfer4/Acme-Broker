/* AdministratorController.java
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
import domain.Contract;
import domain.CreditCard;
import domain.Supplier;

import services.ConsumerService;
import services.ContractService;
import services.CreditCardService;
import services.SupplierService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Services --------------------------------------------------
	
	@Autowired
	private ContractService contractService;
		
	@Autowired
	private ConsumerService consumerService;
		
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private CreditCardService creditCardService;
	
	// Constructors -----------------------------------------------------------
	
	public AdministratorController() {
		super();
	}
		
	// Listing ---------------------------------------------------------------
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(){
		ModelAndView result;
		Collection<CreditCard> creditCards;
		Collection<Consumer> consumers;
		Integer minItems;
		Integer maxItems;
		Double avgItems;
		Collection<Supplier> suppliersMore;
		Collection<Supplier> suppliersLess;
		Collection<Consumer> consumerRequests;
		Collection<Integer> numberRequestsInteger;
		Collection<Contract> contracts;
		
		creditCards = creditCardService.findCreditCardUsedByManyCompanies();
		consumers = consumerService.findConsumersWithMoreRequests();
		minItems = supplierService.findMinNumberItemsOfferedBySuppliers();
		maxItems = supplierService.findMaxNumberItemsOfferedBySuppliers();
		avgItems = supplierService.findAverageNumberItemsOfferedBySuppliers();
		suppliersMore = supplierService.findSuppliersWhoOfferMoreItems();
		suppliersLess  = supplierService.findSuppliersWhoOfferLessItems() ;
		consumerRequests = consumerService.findAllConsumerOrderByNumberOfRequestDesc();
		numberRequestsInteger = consumerService.findAllNumbersRequestOfConsumerOrderByNumberOfRequestDesc();
		contracts=contractService.findContractsNotSignedYet();
		
		result=new ModelAndView("administrator/dashboard");
		result.addObject("creditCards", creditCards);
		result.addObject("consumers", consumers);
		result.addObject("minItems", minItems);
		result.addObject("maxItems", maxItems);
		result.addObject("avgItems", avgItems);
		result.addObject("suppliersMore", suppliersMore);
		result.addObject("suppliersLess", suppliersLess);
		result.addObject("consumerRequests", consumerRequests);
		result.addObject("numberRequestsInteger", numberRequestsInteger);
		result.addObject("contracts", contracts);
		result.addObject("requestURI", "administrator/dashboard.do");

		return result;
	}
	
	// Creation --------------------------------------------
	
	// Edition -------------------------------------------
		
	// Ancillary methods -----------------------------------
	
}