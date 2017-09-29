package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Item extends DomainEntity{
	
	// Constructors ----------------------------------
	
	public Item(){
		super();
	}
	
	// Attributes ---------------------------------------
	
	private String code;
	private String name;
	private String description;
	private double price;
	
	@NotBlank
	@Pattern(regexp = "^\\w+(-\\w)?$")
	@Column(unique = true)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Min(0)
	@Digits(integer = 9, fraction = 2)
	@NotNull
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	// Relationships --------------------------------
	
	private Supplier supplier;

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}
