package com.vp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="sysuser", schema="public")
@SequenceGenerator(name="sysuser_generator", sequenceName="sysuser_seq", allocationSize=1)
public class SysUser {
	
	@Id	
	@Column(name="username", unique=true, nullable=false)
	private String username;

	@Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date update_date;

    @Column(length = 50)
    private String update_by;
    
	@Column(name="degree", nullable=true, length=255)	
	private String degree;
	
	@Column(name="firstname", nullable = true, length=50)	
	private String firstname;
	

	@Column(name="fullname_eng",  nullable = true, length=250)	
	private String fullname_eng;
	
	@Column(name="lastname",  nullable = true, length=50)	
	private String lastname;
	
	@Column(name="password",  nullable = true, length=50)	
	private String password;
	
	@Column(name="permission_level", nullable=true, length=255)	
	private String permission_level;
	
	@Column(name="person_address", nullable=true, length=255)	
	private String person_address;
	
	@Column(name="person_email", nullable=true, length=255)	
	private String person_email;
	
	@Column(name="person_group", nullable=true, length=255)	
	private String person_group;
	
	@Column(name="position", nullable=true, length=255)	
	private String position;
	
	@Column(name="prefix", nullable=true, length=10)	
	private String prefix;
	
	@Column(name="projectgroupid", nullable=true)	
	private Integer projectgroupid;
	
	@Column(name="telephonenumber", nullable=true, length=50)	
	private String telephonenumber;

	
	@Column(name="idx_attach",nullable=true)	
	private Integer idx_attach;
	
	
	///----SETTER AND GETTEER---//
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFullname_eng() {
		return fullname_eng;
	}

	public void setFullname_eng(String fullname_eng) {
		this.fullname_eng = fullname_eng;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermission_level() {
		return permission_level;
	}

	public void setPermission_level(String permission_level) {
		this.permission_level = permission_level;
	}

	public String getPerson_address() {
		return person_address;
	}

	public void setPerson_address(String person_address) {
		this.person_address = person_address;
	}

	public String getPerson_email() {
		return person_email;
	}

	public void setPerson_email(String person_email) {
		this.person_email = person_email;
	}

	public String getPerson_group() {
		return person_group;
	}

	public void setPerson_group(String person_group) {
		this.person_group = person_group;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Integer getProjectgroupid() {
		return projectgroupid;
	}

	public void setProjectgroupid(Integer projectgroupid) {
		this.projectgroupid = projectgroupid;
	}

	public String getTelephonenumber() {
		return telephonenumber;
	}

	public void setTelephonenumber(String telephonenumber) {
		this.telephonenumber = telephonenumber;
	}

	public Integer getIdx_attach() {
		return idx_attach;
	}

	public void setIdx_attach(Integer idx_attach) {
		this.idx_attach = idx_attach;
	}

	
}
