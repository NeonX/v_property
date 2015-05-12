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
 * The persistent class for the posession database table.
 * 
 */
@Entity
@Table(name="posession")
@SequenceGenerator(name="posession_generator", sequenceName="posession_pos_id_seq", allocationSize=1)
public class Posession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="posession_generator")
	@Column(name="pos_id")
	private Integer posId;

	@Column(name="posession_date")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date posessionDate;

	//bi-directional many-to-one association to Owner
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="owner_id")
	private Owner owner;

	//bi-directional many-to-one association to Property
    @ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="ppt_id")
	private Property property;

    public Posession() {
    }

	public Integer getPosId() {
		return this.posId;
	}

	public void setPosId(Integer posId) {
		this.posId = posId;
	}

	public Date getPosessionDate() {
		return this.posessionDate;
	}

	public void setPosessionDate(Date posessionDate) {
		this.posessionDate = posessionDate;
	}

	public Owner getOwner() {
		return this.owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public Property getProperty() {
		return this.property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
	
}