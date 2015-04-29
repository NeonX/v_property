package com.vp.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


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

}