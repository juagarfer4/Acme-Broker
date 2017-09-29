package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Contract extends DomainEntity{
	
	// Constructors ---------------------------------
	
	public Contract(){
		super();
	}
	
	// Attributes -----------------------------------
	
	private String code;
	private Date creationMoment;
	private String description;
	private Date contractHolderDateSign;
	private Date contractorDateSign;
	private Date startingDate;
	private Date endingDate;
	
	@NotBlank
	@Pattern(regexp = "^\\w+(-\\w)?$")
	@Column(unique = true)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	@Past
	public Date getCreationMoment() {
		return creationMoment;
	}
	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}
	
	@NotNull
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getContractHolderDateSign() {
		return contractHolderDateSign;
	}
	public void setContractHolderDateSign(Date contractHolderDateSign) {
		this.contractHolderDateSign = contractHolderDateSign;
	}
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getContractorDateSign() {
		return contractorDateSign;
	}
	public void setContractorDateSign(Date contractorDateSign) {
		this.contractorDateSign = contractorDateSign;
	}
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	public Date getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	public Date getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}
	
	// Relationships ------------------------------------
	
	private Consumer contractHolder;
	private Supplier contractor;
	private Request request;

	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Consumer getContractHolder() {
		return contractHolder;
	}
	public void setContractHolder(Consumer contractHolder) {
		this.contractHolder = contractHolder;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Supplier getContractor() {
		return contractor;
	}
	public void setContractor(Supplier contractor) {
		this.contractor = contractor;
	}
	
	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}

}
