package com.vp.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the posession database table.
 * 
 */
@Entity
@Table(name="posession")
@SequenceGenerator(name="posession_generator", sequenceName="posession_seq", allocationSize=1)
public class Posession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="posession_generator")
	@Column(name="pos_id")
	private Integer posId;

	@Column(name="posession_date")
	private Timestamp posessionDate;

	//bi-directional many-to-one association to Owner
    @ManyToOne
	@JoinColumn(name="owner_id")
	private Owner owner;

	//bi-directional many-to-one association to Property
    @ManyToOne
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

	public Timestamp getPosessionDate() {
		return this.posessionDate;
	}

	public void setPosessionDate(Timestamp posessionDate) {
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