package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Item;
import domain.Supplier;

import repositories.ItemRepository;
import security.UserAccount;

@Service
@Transactional
public class ItemService {
	
	// Managed repository -------------------------------
	
	@Autowired
	private ItemRepository itemRepository;
	
	// Supporting services -------------------------------
	
	@Autowired
	private SupplierService supplierService;
	
	// Constructors ---------------------------------------
	
	public ItemService(){
		super();
	}
	
	// Simple CRUD methods -------------------------------
	
	public Item create(){
		Item result;
		Supplier supplier;
		
		result=new Item();
		supplier=supplierService.findByPrincipal();
		
		result.setSupplier(supplier);
		
		return result;
	}
	
	public Item findOne(Integer itemId){
		Item result;
		
		result=itemRepository.findOne(itemId);
		
		Assert.notNull(result);
		
		this.checkPrincipal(result);
		
		return result;
	}
	
	public Collection<Item> findAll(){
		Collection<Item> result;
		
		result=itemRepository.findAll();
		
		return result;
	}
	
	public void save(Item item){
		Assert.notNull(item);
		
		this.checkPrincipal(item);
		
		itemRepository.save(item);
	}
	
	public void delete(Item item){
		Assert.notNull(item);
		
		this.checkPrincipal(item);
		
		itemRepository.delete(item);
	}
	
	// Other business methods --------------------------------
	
	public void checkPrincipal(Item item) {
		Supplier supplier;
		Supplier principal;

		supplier=item.getSupplier();
		principal = supplierService.findByPrincipal();
		
		Assert.isTrue(supplier==principal);
	}
	
	public Collection<Item> findAllItemsBySupplier(Supplier supplier) {
		Assert.notNull(supplier);
		
		Collection<Item> result;
		UserAccount userAccount;
		Integer supplierId;
		
		result = new ArrayList<Item>();
		userAccount=supplier.getUserAccount();
		supplierId = userAccount.getId();
		result = itemRepository.findAllItemsBySupplier(supplierId);

		return result;
	}
	
	public Collection<Item> findAllItemsByPrincipal() {
		Collection<Item> result;
		Supplier supplier;
		UserAccount userAccount;
		Integer supplierId;

		supplier = supplierService.findByPrincipal();
		
		Assert.notNull(supplier);
		
		result = new ArrayList<Item>();
		userAccount=supplier.getUserAccount();
		supplierId=userAccount.getId();
		result = itemRepository.findAllItemsBySupplier(supplierId);

		return result;
	}

}
