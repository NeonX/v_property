package com.vp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="cost_for_sale")
@SequenceGenerator(name="cost_for_sale_generator", sequenceName="cost_for_sale_sale_id_seq", allocationSize=1)
public class CostForSale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="cost_for_sale_generator")
	@Column(name="sale_id")
	private Integer saleId;

	@Column(name="cost_4sale")
	private float cost4sale;

	@Column(name="update_by")
	private String updateBy;

	@Column(name="update_date")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date updateDate;

	//bi-directional many-to-one association to Property
    @ManyToOne
	@JoinColumn(name="ppt_id")
	private Property property;

    public CostForSale() {
    }

	public Integer getSaleId() {
		return this.saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	public float getCost4sale() {
		return this.cost4sale;
	}

	public void setCost4sale(float cost4sale) {
		this.cost4sale = cost4sale;
	}

	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Property getProperty() {
		return this.property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}