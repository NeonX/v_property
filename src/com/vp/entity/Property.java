package com.vp.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the property database table.
 * 
 */
@Entity
@Table(name="property")
@SequenceGenerator(name="property_generator", sequenceName="property_seq", allocationSize=1)
public class Property implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="property_generator")
	@Column(name="ppt_id")
	private Integer pptId;

	@Column(name="area_size")
	private float areaSize;

	@Column(name="input_date")
	private Timestamp inputDate;

	@Column(name="p_address")
	private String pAddress;

	@Column(name="plot_code")
	private String plotCode;

	@Column(name="prop_code")
	private String propCode;

	@Column(name="prop_desc")
	private String propDesc;

	//bi-directional many-to-one association to Contract
	@OneToMany(mappedBy="property")
	private Set<Contract> contracts;

	//bi-directional many-to-one association to CostEstimate
	@OneToMany(mappedBy="property")
	private Set<CostEstimate> costEstimates;

	//bi-directional many-to-one association to CostForSale
	@OneToMany(mappedBy="property")
	private Set<CostForSale> costForSales;

	//bi-directional many-to-one association to PlotRent
	@OneToMany(mappedBy="property")
	private Set<PlotRent> plotRents;

	//bi-directional many-to-one association to Posession
	@OneToMany(mappedBy="property")
	private Set<Posession> posessions;

    public Property() {
    }

	public Integer getPptId() {
		return this.pptId;
	}

	public void setPptId(Integer pptId) {
		this.pptId = pptId;
	}

	public float getAreaSize() {
		return this.areaSize;
	}

	public void setAreaSize(float areaSize) {
		this.areaSize = areaSize;
	}

	public Timestamp getInputDate() {
		return this.inputDate;
	}

	public void setInputDate(Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	public String getPAddress() {
		return this.pAddress;
	}

	public void setPAddress(String pAddress) {
		this.pAddress = pAddress;
	}

	public String getPlotCode() {
		return this.plotCode;
	}

	public void setPlotCode(String plotCode) {
		this.plotCode = plotCode;
	}

	public String getPropCode() {
		return this.propCode;
	}

	public void setPropCode(String propCode) {
		this.propCode = propCode;
	}

	public String getPropDesc() {
		return this.propDesc;
	}

	public void setPropDesc(String propDesc) {
		this.propDesc = propDesc;
	}

	public Set<Contract> getContracts() {
		return this.contracts;
	}

	public void setContracts(Set<Contract> contracts) {
		this.contracts = contracts;
	}
	
	public Set<CostEstimate> getCostEstimates() {
		return this.costEstimates;
	}

	public void setCostEstimates(Set<CostEstimate> costEstimates) {
		this.costEstimates = costEstimates;
	}
	
	public Set<CostForSale> getCostForSales() {
		return this.costForSales;
	}

	public void setCostForSales(Set<CostForSale> costForSales) {
		this.costForSales = costForSales;
	}
	
	public Set<PlotRent> getPlotRents() {
		return this.plotRents;
	}

	public void setPlotRents(Set<PlotRent> plotRents) {
		this.plotRents = plotRents;
	}
	
	public Set<Posession> getPosessions() {
		return this.posessions;
	}

	public void setPosessions(Set<Posession> posessions) {
		this.posessions = posessions;
	}
	
}