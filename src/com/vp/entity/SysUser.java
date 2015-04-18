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
public class SysUser {
	
	@Id	
	@Column(name="username", unique=true, nullable=false)
	private String username;
	
	@Column(name="firstname", nullable = true, length=50)	
	private String firstname;
	
	@Column(name="lastname",  nullable = true, length=50)	
	private String lastname;
	
	@Column(name="password",  nullable = true, length=50)	
	private String password;
	
	@Column(name="permission_level", nullable=true, length=255)	
	private String permission_level;
	
	@Column(name="person_email", nullable=true, length=255)	
	private String person_email;
	
	@Column(name="telephonenumber", nullable=true, length=50)	
	private String telephonenumber;

	
	@Column(name="idx_attach",nullable=true)	
	private Integer idx_attach;
	
	@Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date update_date;

    @Column(length = 50)
    private String update_by;
	
	
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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

	public String getPerson_email() {
		return person_email;
	}

	public void setPerson_email(String person_email) {
		this.person_email = person_email;
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
