package com.vp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="content", schema="public")
@SequenceGenerator(name="content_generator", sequenceName="content_seq", allocationSize=1)
public class Content {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="content_generator")
	@Column(name="content_id")
	private Integer contentId;

	private String content;

	@Column(name="create_by")
	private String createBy;

	@Column(name="create_date")
	private Timestamp createDate;

	private String header;

	@Column(name="on_home")
	private Boolean onHome;

	@Column(name="update_by")
	private String updateBy;

	@Column(name="update_date")
	private Timestamp updateDate;

    public Content() {
    }

	public Integer getContentId() {
		return this.contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getHeader() {
		return this.header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Boolean getOnHome() {
		return this.onHome;
	}

	public void setOnHome(Boolean onHome) {
		this.onHome = onHome;
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
