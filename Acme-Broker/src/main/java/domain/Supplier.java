package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Supplier extends Customer{
	
	// Constructors ---------------------------------------
	
	public Supplier(){
		super();
	}
	
	// Attributes -------------------------------------------
	
	// Relationships ------------------------------------------
	
	private Collection<Item> items;
	private Collection<Contract> contracts;
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="supplier")
	public Collection<Item> getItems() {
		return items;
	}
	public void setItems(Collection<Item> items) {
		this.items = items;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="contractor")
	public Collection<Contract> getContracts() {
		return contracts;
	}
	public void setContracts(Collection<Contract> contracts) {
		this.contracts = contracts;
	}

}
