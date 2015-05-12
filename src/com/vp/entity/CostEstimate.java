package com.vp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;


/**
 * The persistent class for the cost_estimate database table.
 * 
 */
@Entity
@Table(name="cost_estimate")
@SequenceGenerator(name="cost_estimate_generator", sequenceName="cost_estimate_estm_id_seq", allocationSize=1)
public class CostEstimate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="cost_estimate_generator")
	@Column(name="estm_id")
	private Integer estmId;

	@Column(name="cost_estimate")
	private float costEstimate;

	@Column(name="update_by")
	private String updateBy;

	@Column(name="update_date")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date updateDate;

	//bi-directional many-to-one association to Property
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="ppt_id")
	private Property property;

    public CostEstimate() {
    }

	public Integer getEstmId() {
		return this.estmId;
	}

	public void setEstmId(Integer estmId) {
		this.estmId = estmId;
	}

	public float getCostEstimate() {
		return this.costEstimate;
	}

	public void setCostEstimate(float costEstimate) {
		this.costEstimate = costEstimate;
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

	public Property getProperty() {
		return this.property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
	
}