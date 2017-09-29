package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Admin;

import repositories.AdminRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AdminService {
	
	// Managed repository ---------------------------------
	
	@Autowired
	private AdminRepository adminRepository;
	
	// Supporting services ---------------------------------
	
	// Constructors ---------------------------------------
	
	public AdminService(){
		super();
	}
	
	// Simple CRUD methods -----------------------------------
	
	// Other business methods --------------------------------
	
	public Admin findByPrincipal() {
		Admin result;
		UserAccount userAccount;
		Integer userAccountId;

		userAccount = LoginService.getPrincipal();

		Assert.notNull(userAccount);

		userAccountId = userAccount.getId();

		result = adminRepository.findByUserAccountId(userAccountId);

		Assert.notNull(result);

		return result;
	}

}
