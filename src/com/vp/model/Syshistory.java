package com.vp.model;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="syshistory")
@SequenceGenerator(name="syshistory_generator", sequenceName="syshistory_seq", allocationSize=1)
public class Syshistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="syshistory_generator")
	@Column(name="history_id")
	private Integer historyId;

	@Column(name="detail_time")
	private String detailTime;

	@Column(name="first_name")
	private String firstName;

	@Column(name="history_detail")
	private String historyDetail;

	@Column(name="last_name")
	private String lastName;

	@Column(name="local_ip")
	private String localIp;

	@Column(name="login_name")
	private String loginName;

	@Column(name="public_ip")
	private String publicIp;

	@Column(name="remote_hostname")
	private String remoteHostname;

	@Column(name="update_by")
	private String updateBy;

	@Column(name="update_date")
	private Timestamp updateDate;

    public Syshistory() {
    }

	public Integer getHistoryId() {
		return this.historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}

	public String getDetailTime() {
		return this.detailTime;
	}

	public void setDetailTime(String detailTime) {
		this.detailTime = detailTime;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getHistoryDetail() {
		return this.historyDetail;
	}

	public void setHistoryDetail(String historyDetail) {
		this.historyDetail = historyDetail;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLocalIp() {
		return this.localIp;
	}

	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPublicIp() {
		return this.publicIp;
	}

	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}

	public String getRemoteHostname() {
		return this.remoteHostname;
	}

	public void setRemoteHostname(String remoteHostname) {
		this.remoteHostname = remoteHostname;
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

}