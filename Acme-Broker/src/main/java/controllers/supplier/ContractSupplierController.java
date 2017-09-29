package controllers.supplier;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ContractService;

import controllers.AbstractController;
import domain.Contract;

@Controller
@RequestMapping("/contract/supplier")
public class ContractSupplierController extends AbstractController{
	
	// Services ---------------------------------
	
	@Autowired
	private ContractService contractService;
			
	// Constructors ----------------------------
			
	public ContractSupplierController(){
		super();
	}
			
	// Listing -------------------------
			
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Contract> contracts;
				
		contracts=contractService.findContractsSupplierByPrincipal();
				
		result=new ModelAndView("contract/supplier/list");
		result.addObject("contracts", contracts);
		result.addObject("requestURI", "contract/supplier/list.do");
				
		return result;
	}
			
	// Edition ----------------------------
			
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int contractId){
		ModelAndView result;
		Contract contract;
				
		contract = contractService.findOne(contractId);
			
		Assert.notNull(contract);
				
		result = createEditModelAndView(contract);
		
		return result;
	}
			
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Contract contract, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			System.out.println(binding.getAllErrors().get(0));
			result=createEditModelAndView(contract);
		}else{
			try{
				contractService.signContractBySupplier(contract);
				result=new ModelAndView("redirect:list.do");
			}catch (Throwable oops){
				result=createEditModelAndView(contract, "contract.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}
		}
				
		result.addObject("actionURI", "contract/supplier/edit.do");
				
		return result;
	}
			
	// Ancillary methods ---------------------------
			
	protected ModelAndView createEditModelAndView(Contract contract){
		ModelAndView result;
			
		result=createEditModelAndView(contract, null);
				
		return result;
	}
			
	protected ModelAndView createEditModelAndView(Contract contract, String message){
		ModelAndView result;
		Date contractorDateSign;
		contractorDateSign=contract.getContractorDateSign();
				
		if(contract.getContractorDateSign()==null){
			contractorDateSign=null;
		}else{
			contractorDateSign=contract.getContractorDateSign();
		}
				
		result=new ModelAndView("contract/supplier/edit");
		result.addObject("contract", contract);
		result.addObject("contractorDateSign", contractorDateSign);
		result.addObject("message", message);
				
		return result;
	}

}
