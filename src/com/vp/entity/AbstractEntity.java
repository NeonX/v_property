package com.vp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

@MappedSuperclass
public abstract class AbstractEntity {
	
	@Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date update_date;

    @Column(length = 50)
    private String update_by;
    
    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date create_date;
    
    @Column(length = 50)
    private String create_by;

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}

	public String getUpdate_by() {
		return update_by;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	
}
