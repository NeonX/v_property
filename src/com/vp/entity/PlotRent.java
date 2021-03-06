package com.vp.entity;

import java.io.Serializable;
import java.util.Date;
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


/**
 * The persistent class for the plot_rent database table.
 * 
 */
@Entity
@Table(name="plot_rent")
@SequenceGenerator(name="plot_rent_generator", sequenceName="plot_rent_pr_id_seq", allocationSize=1)
public class PlotRent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="plot_rent_generator")
	@Column(name="pr_id")
	private Integer prId;

	@Column(name="create_by")
	private String createBy;

	@Column(name="create_date")
	private Date createDate;

	@Column(name="plot_size")
	private float plotSize;

	@Column(name="update_by")
	private String updateBy;

	@Column(name="update_date")
	private Date updateDate;

	//bi-directional many-to-one association to Property
    @ManyToOne
	@JoinColumn(name="ppt_id")
	private Property property;

    public PlotRent() {
    }

	public Integer getPrId() {
		return this.prId;
	}

	public void setPrId(Integer prId) {
		this.prId = prId;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public float getPlotSize() {
		return this.plotSize;
	}

	public void setPlotSize(float plotSize) {
		this.plotSize = plotSize;
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