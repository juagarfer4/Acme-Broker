package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Item;
import domain.Supplier;

import services.ItemService;
import services.SupplierService;

@Controller
@RequestMapping("/item")
public class ItemController extends AbstractController{
	
	// Services --------------------------------------------------------------
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private SupplierService supplierService;

	// Constructors ----------------------------------------------------------

	public ItemController() {
		super();
	}

	// Listing ---------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int supplierId) {
		ModelAndView result;
		Collection<Item> items;
		Supplier supplier;
			
		supplier = supplierService.findOne(supplierId);
		items = itemService.findAllItemsBySupplier(supplier);

		result = new ModelAndView("item/list");
		result.addObject("items", items);
		result.addObject("requestURI", "item/list.do");

		return result;
	}
	
	// Creation --------------------------------------------
	
	// Edition -------------------------------------------
			
	// Ancillary methods -----------------------------------

}
