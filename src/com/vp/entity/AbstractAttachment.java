package com.vp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractAttachment extends AbstractEntity {

	@Column(name="order_no")
	private Integer orderNo;
	
	@Column(name="origin_filename", length=100)
	private String originFilename;
	
	@Column(name="refer_filename", length=100)
	private String referFilename;
	
	@Column(name="attach_type", length=50)
	private String attachType;
	
	@Column(name="content_type", length=50)
	private String contentType;
	
	@Column(name="attach_date")
	private Date attachDate;
	
	@Column(name="description")
	private String description;
	
	@Column(name="file_size_bytes")
	private Integer fileSizeBytes;

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getOriginFilename() {
		return originFilename;
	}

	public void setOriginFilename(String originFilename) {
		this.originFilename = originFilename;
	}

	public String getReferFilename() {
		return referFilename;
	}

	public void setReferFilename(String referFilename) {
		this.referFilename = referFilename;
	}

	public Date getAttachDate() {
		return attachDate;
	}

	public void setAttachDate(Date attachDate) {
		this.attachDate = attachDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFileSizeBytes() {
		return fileSizeBytes;
	}

	public void setFileSizeBytes(Integer fileSizeBytes) {
		this.fileSizeBytes = fileSizeBytes;
	}

	public String getAttachType() {
		return attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	
}
