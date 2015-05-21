package com.vp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

@Entity
@Table(name="coordinate")
@SequenceGenerator(name="coordinate_generator", sequenceName="coordinate_co_id_seq", allocationSize=1)
public class Coordinate implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="coordinate_generator")
	@Column(name="co_id")
	private Integer coId;

	@Column(name="co_type")
	private String coType;
	
	@Column(name="e")
	private String e;
	
	@Column(name="n")
	private String n;

	@Column(name="target_id")
	private Integer targetId;

	@Column(name="update_by")
	private String updateBy;

	@Column(name="update_date")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date updateDate;
	
	@Transient
	private Integer isEdit;
	
    public Coordinate() {
    }

	public Integer getCoId() {
		return this.coId;
	}

	public void setCoId(Integer coId) {
		this.coId = coId;
	}

	public String getCoType() {
		return this.coType;
	}

	public void setCoType(String coType) {
		this.coType = coType;
	}

	public String getE() {
		return this.e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getN() {
		return this.n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public Integer getTargetId() {
		return this.targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Integer isEdit) {
		this.isEdit = isEdit;
	}

}