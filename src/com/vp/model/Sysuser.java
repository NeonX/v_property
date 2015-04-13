package com.vp.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name="sysuser")
@SequenceGenerator(name="sysuser_generator", sequenceName="sysuser_seq", allocationSize=1)

public class Sysuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="sysuser_generator")
	private String username;

	private String degree;

	private String firstname;

	@Column(name="fullname_eng")
	private String fullnameEng;

	@Column(name="idx_attach")
	private Integer idxAttach;

	private String lastname;

	private String password;

	@Column(name="permission_level")
	private String permissionLevel;

	@Column(name="person_address")
	private String personAddress;

	@Column(name="person_email")
	private String personEmail;

	@Column(name="person_group")
	private String personGroup;

	private String position;

	private String prefix;

	private Integer projectgroupid;

	private String telephonenumber;

	@Column(name="update_by")
	private String updateBy;

    @Temporal( TemporalType.DATE)
	@Column(name="update_date")
	private Date updateDate;

    public Sysuser() {
    }

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFullnameEng() {
		return this.fullnameEng;
	}

	public void setFullnameEng(String fullnameEng) {
		this.fullnameEng = fullnameEng;
	}

	public Integer getIdxAttach() {
		return this.idxAttach;
	}

	public void setIdxAttach(Integer idxAttach) {
		this.idxAttach = idxAttach;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermissionLevel() {
		return this.permissionLevel;
	}

	public void setPermissionLevel(String permissionLevel) {
		this.permissionLevel = permissionLevel;
	}

	public String getPersonAddress() {
		return this.personAddress;
	}

	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}

	public String getPersonEmail() {
		return this.personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public String getPersonGroup() {
		return this.personGroup;
	}

	public void setPersonGroup(String personGroup) {
		this.personGroup = personGroup;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Integer getProjectgroupid() {
		return this.projectgroupid;
	}

	public void setProjectgroupid(Integer projectgroupid) {
		this.projectgroupid = projectgroupid;
	}

	public String getTelephonenumber() {
		return this.telephonenumber;
	}

	public void setTelephonenumber(String telephonenumber) {
		this.telephonenumber = telephonenumber;
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

}