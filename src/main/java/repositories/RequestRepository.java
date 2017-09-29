package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Request;
@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{
	
	@Query("select r from Request r where r.consumer.userAccount.id=?1")
	Collection<Request> findAllRequestsByConsumer(int consumerId);
	
	@Query("select r.contract.contractor.name, count(r) from Request r group by r.contract.contractor order by count(r) desc")
	Object[] findSuppliersAndRequestCreatedByThem();
	
	@Query("select r from Request r where r.contract is null and r.isCancelled = false")
	Collection<Request> findAllRequestNotSignedNotCancelled();
	
}
