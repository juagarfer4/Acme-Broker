package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Admin;
import domain.Consumer;
import domain.Contract;
import domain.Request;
import domain.Supplier;

import repositories.ContractRepository;
import security.UserAccount;

@Service
@Transactional
public class ContractService {
	
	// Managed repository --------------------------------
	
	@Autowired
	private ContractRepository contractRepository;
	
	// Supporting services ------------------------------
	
	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private AdminService adminService;
	
	// Constructors ---------------------------------------
	
	public ContractService(){
		super();
	}
	
	// Simple CRUD methods -------------------------------
	
	public Contract create(Integer requestId){
		Request request;

		request=requestService.findOne(requestId);
		
		Assert.notNull(request);
		
		Contract result;
		Date creationMoment;
		Date startingDate;
		Date endingDate;
		Consumer contractHolder;
		
		result=new Contract();
		creationMoment=new Date(System.currentTimeMillis()-1);
		startingDate=request.getStartingDate();
		endingDate=request.getEndingDate();
		contractHolder=request.getConsumer();
		
		result.setRequest(request);
		result.setCreationMoment(creationMoment);
		result.setStartingDate(startingDate);
		result.setEndingDate(endingDate);
		result.setContractHolder(contractHolder);
		
		return result;
	}
	
	public Contract findOne(Integer contractId) {
		Contract result;
		
		result= contractRepository.findOne(contractId);
		
		return result;
	}

	public Collection<Contract> findAll(){
		Collection<Contract> result;
		
		result=contractRepository.findAll();
		
		return result;
	}
	
	public void save(Contract contract){
		Assert.notNull(contract);
		
		this.checkPrincipal();
		
		Date creationMoment;
		Request request;
		
		creationMoment=new Date(System.currentTimeMillis()-1);
		request=contract.getRequest();
		
		Assert.notNull(request);
		
		contract.setCreationMoment(creationMoment);
		
		requestService.saveContractInRequest(request,contract);
		contractRepository.save(contract);
	}
	
	// Other business methods ---------------------------------------
	
	public void checkPrincipal() {
		Admin admin;

		admin = adminService.findByPrincipal();
		
		Assert.notNull(admin);
	}
	
	public void checkPrincipalConsumer(Contract contract){
		Consumer consumer;
		Consumer principal;
		
		consumer=contract.getContractHolder();
		principal=consumerService.findByPrincipal();
		
		Assert.isTrue(consumer==principal);
	}
	
	public void checkPrincipalSupplier(Contract contract){
		Supplier supplier;
		Supplier principal;
		
		supplier=contract.getContractor();
		principal=supplierService.findByPrincipal();
		
		Assert.isTrue(supplier==principal);
	}
	
	public void signContractByConsumer(Contract contract){
		Assert.notNull(contract);
		
		this.checkPrincipalConsumer(contract);
		
		Date contractHolderDateSign;
		
		contractHolderDateSign=contract.getContractHolderDateSign();
		
		Assert.isNull(contractHolderDateSign);
		
		Date currentMoment;
		
		currentMoment=new Date(System.currentTimeMillis()-1);
		
		contract.setContractHolderDateSign(currentMoment);
		
		this.save(contract);
	}
	
	public void signContractBySupplier(Contract contract){
		Assert.notNull(contract);
		
		this.checkPrincipalSupplier(contract);
		
		Date contractorDateSign;
		
		contractorDateSign=contract.getContractorDateSign();
		
		Assert.isNull(contractorDateSign);
		
		Date currentMoment;
		
		currentMoment=new Date(System.currentTimeMillis()-1);
		
		contract.setContractorDateSign(currentMoment);
		
		this.save(contract);
	}
	
	public Collection<Contract> findContractsConsumerByPrincipal() {
		Collection<Contract> result;
		Consumer consumer;
		UserAccount userAccount;
		Integer consumerId;

		consumer = consumerService.findByPrincipal();
		
		Assert.notNull(consumer);
		
		userAccount=consumer.getUserAccount();
		consumerId = userAccount.getId();

		result = contractRepository.findContractsOfConsumer(consumerId);

		return result;
	}

	public Collection<Contract> findContractsSupplierByPrincipal() {
		Collection<Contract> result;
		Supplier supplier;
		UserAccount userAccount;
		Integer supplierId;

		supplier = supplierService.findByPrincipal();
		
		Assert.notNull(supplier);
		
		userAccount=supplier.getUserAccount();
		supplierId = userAccount.getId();

		result = contractRepository.findContractsOfSupplier(supplierId);

		return result;
	}
	
	public Collection<Contract> findContractsNotSignedYet() {
		Collection<Contract> result;

		result = contractRepository.findContractsNotSignedYet();

		return result;
	}

	public Collection<Contract> findAllCancelledContracts() {
		Collection<Contract> result;

		result = contractRepository.findAllCancelledContracts();
		
		return result;
	}
	
}
