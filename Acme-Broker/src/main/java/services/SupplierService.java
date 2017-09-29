package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.CreditCard;
import domain.Item;
import domain.Supplier;
import domain.Contract;

import repositories.SupplierRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class SupplierService {
	
	// Managed repository --------------------------------------
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	// Supporting services --------------------------------------
	
	@Autowired
	private CreditCardService creditCardService;
	
	// Constructors ---------------------------------------
	
	public SupplierService(){
		super();
	}
	
	// Simple CRUD methods ---------------------------------------
	
	public Supplier create(){
		Supplier result;
		UserAccount userAccount;
		Collection<Authority> authorities;
		Authority authority;
		CreditCard creditCard;
		Collection<Item> items;
		Collection<Contract> contracts;
		
		result=new Supplier();
		userAccount=new UserAccount();
		authorities=new ArrayList<Authority>();
		authority=new Authority();
		creditCard=new CreditCard();
		items=new ArrayList<Item>();
		contracts=new ArrayList<Contract>();
		
		authority.setAuthority("SUPPLIER");
		
		authorities.add(authority);
		
		userAccount.setAuthorities(authorities);
		result.setUserAccount(userAccount);
		result.setCreditCard(creditCard);
		result.setItems(items);
		result.setContracts(contracts);
		
		return result;
	}
	
	public Supplier findOne(Integer SupplierId){
		Supplier result;
		
		result=supplierRepository.findOne(SupplierId);
		
		Assert.notNull(result);
		
		return result;
	}
	
	public Collection<Supplier> findAll(){
		Collection<Supplier> result;
		
		result=supplierRepository.findAll();
		
		return result;
	}
	
	public void save(Supplier supplier){
		Assert.notNull(supplier);
		
		CreditCard cc = supplier.getCreditCard();
		Calendar d = new GregorianCalendar();

		Assert.isTrue(cc.getExpirationYear() >= d.get(Calendar.YEAR));
		if (cc.getExpirationYear() == d.get(Calendar.YEAR)) Assert.isTrue(cc.getExpirationMonth() > d.get(Calendar.MONTH) + 1);
		
		Md5PasswordEncoder encoder;
		UserAccount userAccount;
		String password;
		
		encoder= new Md5PasswordEncoder();
		userAccount=supplier.getUserAccount();
		password=userAccount.getPassword();
		password=encoder.encodePassword(password, null);
		userAccount.setPassword(password);
		
		supplierRepository.save(supplier);
		
		creditCardService.save(cc);
	}
	
	// Other business methods ----------------------------------------------
	
	public Supplier findByPrincipal() {
		Supplier result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		
		result = supplierRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);
		return result;
	}
	
	public Collection<Supplier> findSuppliersWhoOfferMoreItems() {
		Collection<Supplier> result;
		
		result = supplierRepository.findSuppliersWhoOfferMoreItems();
		
		return result;
	}

//	public Collection<String> findSuppliersNamesWhoOfferLessItems() {
//		Collection<String> result;
//		
//		result = supplierRepository.findSuppliersNamesWhoOfferLessItems();
//		
//		return result;
//	}
	
	public Collection<Supplier> findSuppliersWhoOfferLessItems() {
		Collection<Supplier> result;
		
		result = supplierRepository.findSuppliersWhoOfferLessItems();
		
		return result;
	}

	public Integer findMinNumberItemsOfferedBySuppliers() {
		Integer result;

		result = supplierRepository.findMinNumberItemsOfferedBySuppliers();
		
		return result;
	}

	public Integer findMaxNumberItemsOfferedBySuppliers() {
		Integer result;

		result = supplierRepository.findMaxNumberItemsOfferedBySuppliers();

		return result;
	}

	public Double findAverageNumberItemsOfferedBySuppliers() {
		Double result;

		result = supplierRepository.findAverageNumberItemsOfferedBySuppliers();

		return result;
	}
	
//	public Collection<Supplier> findSuppliersWithMoreContracts(){
//		Collection<Supplier> result;
//		
//		result=supplierRepository.findSuppliersWithMoreContracts();
//		
//		return result;
//	}

}
