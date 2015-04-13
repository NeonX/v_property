package com.vp.model;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="contractPlot")
@SequenceGenerator(name="contractPlot_generator", sequenceName="contractPlot_seq", allocationSize=1)
public class ContractPlot implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="contractPlot_generator")
	@Column(name="cp_id")
	private Integer cpId;

	@Column(name="rent_for")
	private String rentFor;

	@Column(name="rental_rate")
	private float rentalRate;

	@Column(name="tax_rate")
	private float taxRate;

	@Column(name="update_by")
	private String updateBy;

	@Column(name="update_date")
	private Timestamp updateDate;

	//bi-directional many-to-one association to Contract
    @ManyToOne
	@JoinColumn(name="ct_id")
	private Contract contract;

	//bi-directional many-to-one association to PlotRent
    @ManyToOne
	@JoinColumn(name="pr_id")
	private PlotRent plotRent;

    public ContractPlot() {
    }

	public Integer getCpId() {
		return this.cpId;
	}

	public void setCpId(Integer cpId) {
		this.cpId = cpId;
	}

	public String getRentFor() {
		return this.rentFor;
	}

	public void setRentFor(String rentFor) {
		this.rentFor = rentFor;
	}

	public float getRentalRate() {
		return this.rentalRate;
	}

	public void setRentalRate(float rentalRate) {
		this.rentalRate = rentalRate;
	}

	public float getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(float taxRate) {
		this.taxRate = taxRate;
	}

	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public Contract getContract() {
		return this.contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	public PlotRent getPlotRent() {
		return this.plotRent;
	}

	public void setPlotRent(PlotRent plotRent) {
		this.plotRent = plotRent;
	}
	
}