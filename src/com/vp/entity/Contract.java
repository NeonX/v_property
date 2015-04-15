package com.vp.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "contract")
@SequenceGenerator(name="contract_generator",sequenceName="contract_seq",allocationSize=1)
public class Contract {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="contract_generator")
	@Column(name="ct_id")
	private Integer ctId;

	@Column(name="cont_begin")
	private Timestamp contBegin;

	@Column(name="cont_code")
	private String contCode;

	@Column(name="cont_end")
	private Timestamp contEnd;

	@Column(name="cont_with")
	private String contWith;

	@Column(name="renter_address")
	private String renterAddress;

	@Column(name="renter_name")
	private String renterName;

	@Column(name="renter_phone")
	private String renterPhone;

	//bi-directional many-to-one association to Property
    @ManyToOne
	@JoinColumn(name="ppt_id")
	private Property property;

	//bi-directional many-to-one association to ContractPlot
	@OneToMany(mappedBy="contract")
	private Set<ContractPlot> contractPlots;

    public Contract() {
    }

	public Integer getCtId() {
		return this.ctId;
	}

	public void setCtId(Integer ctId) {
		this.ctId = ctId;
	}

	public Timestamp getContBegin() {
		return this.contBegin;
	}

	public void setContBegin(Timestamp contBegin) {
		this.contBegin = contBegin;
	}

	public String getContCode() {
		return this.contCode;
	}

	public void setContCode(String contCode) {
		this.contCode = contCode;
	}

	public Timestamp getContEnd() {
		return this.contEnd;
	}

	public void setContEnd(Timestamp contEnd) {
		this.contEnd = contEnd;
	}

	public String getContWith() {
		return this.contWith;
	}

	public void setContWith(String contWith) {
		this.contWith = contWith;
	}

	public String getRenterAddress() {
		return this.renterAddress;
	}

	public void setRenterAddress(String renterAddress) {
		this.renterAddress = renterAddress;
	}

	public String getRenterName() {
		return this.renterName;
	}

	public void setRenterName(String renterName) {
		this.renterName = renterName;
	}

	public String getRenterPhone() {
		return this.renterPhone;
	}

	public void setRenterPhone(String renterPhone) {
		this.renterPhone = renterPhone;
	}

	public Property getProperty() {
		return this.property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
	
	public Set<ContractPlot> getContractPlots() {
		return this.contractPlots;
	}

	public void setContractPlots(Set<ContractPlot> contractPlots) {
		this.contractPlots = contractPlots;
	}
}
