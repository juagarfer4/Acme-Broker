package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer>{
	
	@Query("select c.creditCard from Customer c group by c.creditCard having count(c)>1")
	Collection<CreditCard> findCreditCardUsedByCompanies();

}
