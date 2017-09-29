package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Contract;
@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer>{
	
	
	@Query("select c from Contract c where c.contractHolder.userAccount.id=?1")
	Collection<Contract> findContractsOfConsumer(int consumerId);
	
	@Query("select c from Contract c where c.contractor.userAccount.id=?1")
	Collection<Contract> findContractsOfSupplier(int supplierId);
	
	@Query("select c from Contract c where c.contractHolderDateSign is null and c.contractorDateSign is null order by c.creationMoment")
	Collection<Contract> findContractsNotSignedYet();
	
	@Query("select r.contract from Request r where r.isCancelled=true")
	Collection<Contract> findAllCancelledContracts();

}
