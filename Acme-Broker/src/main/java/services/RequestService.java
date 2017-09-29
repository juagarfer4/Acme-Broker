package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Contract;
import domain.Request;
import domain.Consumer;

import repositories.RequestRepository;
import security.UserAccount;

@Service
@Transactional
public class RequestService {
	
	// Managed repository -------------------------------
	
	@Autowired
	private RequestRepository requestRepository;
		
	// Supporting services -------------------------------
		
	@Autowired
	private ConsumerService consumerService;
	
	// Constructors ---------------------------------------
	
	public RequestService(){
		super();
	}
		
	// Simple CRUD methods -------------------------------
	
	public Request create(){
		Request result;
		Consumer consumer;
		Boolean isCancelled;
		
		result=new Request();
		consumer=consumerService.findByPrincipal();
		isCancelled=false;
		
		result.setConsumer(consumer);
		result.setIsCancelled(isCancelled);
		
		return result;
	}
	
	public Request findOne(Integer requestId){
		Request result;
		
		result=requestRepository.findOne(requestId);
		
		Assert.notNull(result);
		
		return result;
	}
	
	public void save(Request request){
		Assert.notNull(request);
		
		this.checkPrincipal(request);
		
		Date startingDate;
		Date endingDate;
		
		startingDate=request.getStartingDate();
		endingDate=request.getEndingDate();
		
		Assert.isTrue(startingDate.before(endingDate));
		
		requestRepository.save(request);
	}
	
	// Other business methods -------------------------------
	
	public void checkPrincipal(Request request) {
		Consumer consumer;
		Consumer principal;

		consumer = request.getConsumer();
		principal = consumerService.findByPrincipal();
		
		Assert.isTrue(consumer==principal);
	}
	
	public void cancelRequest(Request request) {
		Assert.notNull(request);
		
		this.checkPrincipal(request);
		
		Contract contract;
		
		contract=request.getContract();
		
		if(contract!=null){
			Date contractHolderDateSign;
			Date contractorDateSign;
		
			contract=request.getContract();
			contractHolderDateSign=contract.getContractHolderDateSign();
			contractorDateSign=contract.getContractorDateSign();
		
			Assert.isTrue(contractHolderDateSign == null || contractorDateSign == null);
		}
		
		request.setIsCancelled(true);
		
		this.save(request);

	}
	
	public Collection<Request> findAllRequestsByConsumer() {
		Collection<Request> result;
		Consumer consumer;
		UserAccount userAccount;
		Integer consumerId;

		consumer = consumerService.findByPrincipal();
		userAccount=consumer.getUserAccount();
		consumerId = userAccount.getId();
		result = requestRepository.findAllRequestsByConsumer(consumerId);

		return result;

	}
	
	public Collection<Request> findAllRequestNotSignedNotCancelled() {
		Collection<Request> result;

		result = requestRepository.findAllRequestNotSignedNotCancelled();

		return result;
	}
	
	public void saveContractInRequest(Request request, Contract contract) {
		Request requestModified;
		requestModified=request;
		
		requestModified.setContract(contract);

		requestRepository.save(requestModified);
	}

}
