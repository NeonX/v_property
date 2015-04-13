package com.vp.model;

import java.io.Serializable;
import javax.persistence.*;


import java.sql.Timestamp;

@Entity
@Table(name="cost_for_sale")
@SequenceGenerator(name="cost_for_sale_generator", sequenceName="cost_for_sale_seq", allocationSize=1)
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
	private Timestamp updateDate;

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

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public Property getProperty() {
		return this.property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
	
}