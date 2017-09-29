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

import domain.Consumer;
import domain.Contract;
import domain.CreditCard;
import domain.Request;

import repositories.ConsumerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ConsumerService {
	
	// Managed repository ---------------------------------
	
	@Autowired
	private ConsumerRepository consumerRepository;
	
	// Supporting services ---------------------------------
	
	@Autowired
	private CreditCardService creditCardService;
	
	// Constructors ---------------------------------------
	
	public ConsumerService(){
		super();
	}
	
	// Simple CRUD methods -----------------------------------
	
	public Consumer create(){
		Consumer result;
		UserAccount userAccount;
		Collection<Authority> authorities;
		Authority authority;
		CreditCard creditCard;
		Collection<Request> requests;
		Collection<Contract> contracts;
		
		result=new Consumer();
		userAccount=new UserAccount();
		authorities=new ArrayList<Authority>();
		authority=new Authority();
		creditCard=new CreditCard();
		requests=new ArrayList<Request>();
		contracts=new ArrayList<Contract>();
		
		authority.setAuthority("CONSUMER");
		
		authorities.add(authority);
		
		userAccount.setAuthorities(authorities);
		result.setUserAccount(userAccount);
		result.setCreditCard(creditCard);
		result.setRequests(requests);
		result.setContracts(contracts);
		
		return result;
	}
	
	public Collection<Consumer> findAll(){
		Collection<Consumer> result;
		
		result=consumerRepository.findAll();
		
		return result;
	}
	
	public void save(Consumer consumer){
		Assert.notNull(consumer);
		
		CreditCard cc = consumer.getCreditCard();
		Calendar d = new GregorianCalendar();

		Assert.isTrue(cc.getExpirationYear() >= d.get(Calendar.YEAR));
		if (cc.getExpirationYear() == d.get(Calendar.YEAR)) Assert.isTrue(cc.getExpirationMonth() > d.get(Calendar.MONTH) + 1);
		
		Md5PasswordEncoder encoder;
		UserAccount userAccount;
		String password;
		
		encoder= new Md5PasswordEncoder();
		userAccount=consumer.getUserAccount();
		password=userAccount.getPassword();
		password=encoder.encodePassword(password, null);
		userAccount.setPassword(password);
		
		consumerRepository.save(consumer);
		
		creditCardService.save(cc);
	}
	
	// Other business methods ----------------------------------------
	
	public Consumer findByPrincipal() {
		Consumer result;
		UserAccount userAccount;
		Integer userAccountId;

		userAccount = LoginService.getPrincipal();

		Assert.notNull(userAccount);

		userAccountId = userAccount.getId();

		result = consumerRepository.findByUserAccountId(userAccountId);

		Assert.notNull(result);

		return result;
	}
	
	public Collection<Consumer> findConsumersWithMoreRequests() {
		Collection<Consumer> result;
		
		result = consumerRepository.findConsumersWithMoreRequests();
		
		return result;
	}
	
	public Collection<Consumer> findAllConsumerOrderByNumberOfRequestDesc(){
		Collection<Consumer> result;
		
		result=consumerRepository.findAllConsumerOrderByNumberOfRequestDesc();
		
		return result;
	}
	
	public Collection<Integer> findAllNumbersRequestOfConsumerOrderByNumberOfRequestDesc(){
		Collection<Integer>  result;
		
		result=consumerRepository.findAllNumbersRequestOfConsumerOrderByNumberOfRequestDesc();
		
		return result;
	}

}
