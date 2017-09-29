package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Consumer;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Integer>{

	@Query("select c from Consumer c where c.userAccount.id=?1")
	Consumer findByUserAccountId(int userAccountId);
	
	@Query("select c from Consumer c where c.requests.size = ( select max(c.requests.size) from Consumer c)")
	Collection<Consumer> findConsumersWithMoreRequests();

	@Query("select c from Consumer c order by c.requests.size desc ")
	Collection<Consumer> findAllConsumerOrderByNumberOfRequestDesc();
	
	@Query("select c.requests.size from Consumer c order by c.requests.size desc")
	Collection<Integer> findAllNumbersRequestOfConsumerOrderByNumberOfRequestDesc();

}
