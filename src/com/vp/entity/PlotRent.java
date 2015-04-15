package com.vp.entity;

import java.io.Serializable;
import javax.persistence.*;


import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the plot_rent database table.
 * 
 */
@Entity
@Table(name="plot_rent")
@SequenceGenerator(name="plot_rent_generator", sequenceName="plot_rent_seq", allocationSize=1)
public class PlotRent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="plot_rent_generator")
	@Column(name="pr_id")
	private Integer prId;

	@Column(name="create_by")
	private String createBy;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="plot_size")
	private float plotSize;

	@Column(name="update_by")
	private String updateBy;

	@Column(name="update_date")
	private Timestamp updateDate;

	//bi-directional many-to-one association to ContractPlot
	@OneToMany(mappedBy="plotRent")
	private Set<ContractPlot> contractPlots;

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

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
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

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public Set<ContractPlot> getContractPlots() {
		return this.contractPlots;
	}

	public void setContractPlots(Set<ContractPlot> contractPlots) {
		this.contractPlots = contractPlots;
	}
	
	public Property getProperty() {
		return this.property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
	
}