package com.vp.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name="owner")
@SequenceGenerator(name="owner_generator", sequenceName="owner_seq", allocationSize=1)
public class Owner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="owner_generator")
	@Column(name="owner_id")
	private Integer ownerId;

	private String address;

	@Column(name="owner_name")
	private String ownerName;

	private String phone;

	//bi-directional many-to-one association to Posession
	@OneToMany(mappedBy="owner")
	private Set<Posession> posessions;

    public Owner() {
    }

	public Integer getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Posession> getPosessions() {
		return this.posessions;
	}

	public void setPosessions(Set<Posession> posessions) {
		this.posessions = posessions;
	}
	
}