package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import domain.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer>{

	@Query("select s from Supplier s where s.userAccount.id=?1")
	Supplier findByUserAccountId(int id);
	
	@Query("select s from Supplier s where s.items.size=(select max(z.items.size) from Supplier z)")
	Collection<Supplier> findSuppliersWhoOfferMoreItems();
	
	@Query("select s from Supplier s where s.items.size=(select min(z.items.size) from Supplier z)")
	Collection<Supplier> findSuppliersWhoOfferLessItems();
	
	@Query("select min(s.items.size) from Supplier s")
	Integer findMinNumberItemsOfferedBySuppliers();
	
	@Query("select max(s.items.size) from Supplier s")
	Integer findMaxNumberItemsOfferedBySuppliers();
	
	@Query("select avg (s.items.size) from Supplier s")
	Double findAverageNumberItemsOfferedBySuppliers();
	
}
