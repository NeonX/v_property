package com.vp.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;


@Entity
@Table(name="history_user")
@SequenceGenerator(name="historyuser_generator",sequenceName="historyuser_systemmanage_seq", allocationSize=1)
public class HistoryUser  implements Serializable{

	private static final long serialVersionUID = -8418650039300132595L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="historyuser_generator")
	private Integer history_id;
	
	@Column(name = "login_name", length = 50)
	private String login_name;
	
	@Column(name = "public_ip", length = 50)
	private String public_ip;
	
	@Column(name = "local_ip", length = 50)
	private String local_ip;
	
	@Column(name = "remote_hostname", length = 100)
	private String remote_hostname;
	
	@Column(name = "test_numeber", nullable=true)
	private String testNumber;
	
	@Column(name = "action_id",length =10)
	private String actionId;
	
	@Column(name = "action_modelref",length =250)
	private String action_modelref;
	
	@Column(name = "detail_time", length = 50)
	private String detail_time;
	
	@Column(name ="detail", nullable=true)
	private String detail;
	
	
	@Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date update_date;
	
	@Column(name ="action_type")
	private String actionType;

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

	@Column(nullable = false, length = 50)
    private String update_by;
    
	public Integer getHistory_id() {
		return history_id;
	}

	public void setHistory_id(Integer history_id) {
		this.history_id = history_id;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getPublic_ip() {
		return public_ip;
	}

	public void setPublic_ip(String public_ip) {
		this.public_ip = public_ip;
	}

	public String getLocal_ip() {
		return local_ip;
	}

	public void setLocal_ip(String local_ip) {
		this.local_ip = local_ip;
	}

	public String getRemote_hostname() {
		return remote_hostname;
	}

	public void setRemote_hostname(String remote_hostname) {
		this.remote_hostname = remote_hostname;
	}


	public String getDetail_time() {
		return detail_time;
	}

	public void setDetail_time(String detail_time) {
		this.detail_time = detail_time;
	}

	

	public String getActionId() {
		
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getAction_modelref() {
		return action_modelref;
	}

	public void setAction_modelref(String action_modelref) {
		this.action_modelref = action_modelref;
	}

	public String getTestNumber() {
		return testNumber;
	}

	public void setTestNumber(String testNumber) {
		this.testNumber = testNumber;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
