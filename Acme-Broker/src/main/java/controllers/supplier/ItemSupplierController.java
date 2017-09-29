package controllers.supplier;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ItemService;

import controllers.AbstractController;
import domain.Item;

@Controller
@RequestMapping("/item/supplier")
public class ItemSupplierController extends AbstractController{
	
	// Services --------------------------------------------------------------
	
	@Autowired
	private ItemService itemService;

	// Constructors ----------------------------------------------------------

	public ItemSupplierController() {
		super();
	}

	// Listing ---------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Item> items;
		Boolean myItems;
			
		items=itemService.findAllItemsByPrincipal();
		myItems=true;

		result = new ModelAndView("item/supplier/list");
		result.addObject("items", items);
		result.addObject("myItems", myItems);
		result.addObject("requestURI", "item/supplier/list.do");

		return result;
	}
		
	// Creation ---------------------------------------------------------------
		
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Item item;
			
		item = itemService.create();
			
		result = new ModelAndView("item/supplier/edit");
		result.addObject("item", item);
		result.addObject("actionURI", "item/supplier/edit.do");
		
		return result;
	}
		
	// Edition ---------------------------------------------------------------
		
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int itemId){
		ModelAndView result;
		Item item;
			
		item = itemService.findOne(itemId);
		result = createEditModelAndView(item);
			
		return result;
			
	}
		
	@RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Item item, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			System.out.println(binding.getAllErrors().get(0));
			result=createEditModelAndView(item);
		}else{
			try{
				itemService.save(item);
				result=new ModelAndView("redirect:list.do");
			}catch(Throwable oops){
				result=createEditModelAndView(item, "item.commit.error");
				System.out.println(oops.getStackTrace());
			}
		}
			
		result.addObject("actionURI", "item/supplier/edit.do");
		return result;
	}
		
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Item item, BindingResult binding){
		ModelAndView result;
		
		try{
			itemService.delete(item);
			result = new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(item, "item.commit.error");
		}
			
		return result;
	}
		
	// Ancillary methods ---------------------------------------------------------------
		
	public ModelAndView createEditModelAndView(Item item){
		ModelAndView result;
			
		result=createEditModelAndView(item, null);
			
		return result;
	}
		
	public ModelAndView createEditModelAndView(Item item, String message){
		ModelAndView result;
			
		result=new ModelAndView("item/supplier/edit");
		result.addObject("item", item);
		result.addObject("message", message);
		
		return result;
	}

}
