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
public class Consumer extends Customer{
	
	// Constructors ------------------------------------
	
	public Consumer(){
		super();
	}
	
	// Attributes ---------------------------------------
	
	// Relationships ------------------------------------
	
	private Collection<Request> requests;
	private Collection<Contract> contracts;
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="consumer")
	public Collection<Request> getRequests() {
		return requests;
	}
	public void setRequests(Collection<Request> requests) {
		this.requests = requests;
	}
	
	@Valid
	@NotNull
	@OneToMany(mappedBy="contractHolder")
	public Collection<Contract> getContracts() {
		return contracts;
	}
	public void setContracts(Collection<Contract> contracts) {
		this.contracts = contracts;
	}

}
