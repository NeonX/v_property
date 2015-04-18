package com.vp.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="attachment")
@SequenceGenerator(name="attachment_generator", sequenceName="attachment_seq", allocationSize=1)

public class Attachment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="attachment_generator")
	@Column(name="atm_id")
	private Integer atmId;

	@Column(name="attach_desc")
	private String attachDesc;

	@Column(name="attach_name")
	private String attachName;

	@Column(name="attach_order")
	private Integer attachOrder;

	@Column(name="attach_group")
	private String attachGroup;
	
	@Column(name="attach_type")
	private String attachType;

	@Column(name="create_by")
	private String createBy;

	@Column(name="create_date")
	private Timestamp createDate;

	@Column(name="file_name_ref")
	private String fileNameRef;

	@Column(name="file_size")
	private double fileSize;

	@Column(name="item_id")
	private Integer itemId;

	@Column(name="update_by")
	private String updateBy;

	@Column(name="update_date")
	private Timestamp updateDate;
	
	@Transient
	private String url;
	
	@Transient
	private String urlThumb;

    public Attachment() {
    }

	public Integer getAtmId() {
		return this.atmId;
	}

	public void setAtmId(Integer atmId) {
		this.atmId = atmId;
	}

	public String getAttachDesc() {
		return this.attachDesc;
	}

	public void setAttachDesc(String attachDesc) {
		this.attachDesc = attachDesc;
	}

	public String getAttachName() {
		return this.attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	public Integer getAttachOrder() {
		return this.attachOrder;
	}

	public void setAttachOrder(Integer attachOrder) {
		this.attachOrder = attachOrder;
	}

	public String getAttachType() {
		return this.attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType;
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

	public String getFileNameRef() {
		return this.fileNameRef;
	}

	public void setFileNameRef(String fileNameRef) {
		this.fileNameRef = fileNameRef;
	}

	public double getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
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

	public String getAttachGroup() {
		return attachGroup;
	}

	public void setAttachGroup(String attachGroup) {
		this.attachGroup = attachGroup;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlThumb() {
		return urlThumb;
	}

	public void setUrlThumb(String urlThumb) {
		this.urlThumb = urlThumb;
	}
	
	
}
