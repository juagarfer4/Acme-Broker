package controllers.admin;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SupplierService;
import services.ContractService;

import controllers.AbstractController;
import domain.Contract;
import domain.Supplier;

@Controller
@RequestMapping("/contract/administrator")
public class ContractAdministratorController extends AbstractController{
	
	// Services ----------------------------------

	@Autowired
	private ContractService contractService;
			
	@Autowired
	private SupplierService supplierService;
			
	// Constructors --------------------------------
			
	public ContractAdministratorController(){
		super();
	}
			
	// Listing ---------------------------------------------------------------
			
	@RequestMapping(value = "/listcancelled", method = RequestMethod.GET)
	public ModelAndView listCancelled(){
		ModelAndView result;
		Collection<Contract> contracts;
				
		contracts=contractService.findAllCancelledContracts();
				
		result=new ModelAndView("contract/administrator/listcancelled");
		result.addObject("contracts", contracts);
		result.addObject("requestURI", "contract/administrator/listcancelled.do");
				
		return result;
	}
			
	// Creation ---------------------------------------------------------------
			
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer requestId){
		ModelAndView result;
		Contract contract;
		Collection<Supplier> contractors;
		
		contract = contractService.create(requestId);
		contractors=supplierService.findAll();
				
		result = new ModelAndView("contract/administrator/edit");
		result.addObject("contract", contract);
		result.addObject("contractors", contractors);
		result.addObject("actionURI", "contract/administrator/edit.do");
				
		return result;
	}
			
	// Edition ---------------------------------------------------------------
			
	@RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Contract contract, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			System.out.println(binding.getAllErrors().get(0));
			result=createEditModelAndView(contract);
		}else{
			try{
				contractService.save(contract);
				result=new ModelAndView("redirect:/");
			}catch(Throwable oops){
				result=createEditModelAndView(contract, "contract.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}
		}
				
		result.addObject("actionURI", "contract/administrator/edit.do");
		return result;
			
	}
			
			
	// Ancillary methods ---------------------------------------------------------------
	
	public ModelAndView createEditModelAndView(Contract contract){
		ModelAndView result;
		
		result=createEditModelAndView(contract, null);
				
		return result;
		
	}
			
	public ModelAndView createEditModelAndView(Contract contract, String message){
		ModelAndView result;
		Collection<Supplier> contractors;
				
		contractors=supplierService.findAll();
				
		result=new ModelAndView("contract/administrator/edit");
		result.addObject("contract", contract);
		result.addObject("contractors", contractors);
		result.addObject("message", message);
		return result;
				
	}
			

}
