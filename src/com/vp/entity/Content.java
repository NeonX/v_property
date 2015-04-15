package com.vp.entity;

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
public class Content extends AbstractEntity{
	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="content_generator")
	private Integer content_id;

	@Column(name="header")	
	private String header;
	
	@Column(name="content")	
	private String content;
	
	@Column(name="on_home")	
	private boolean on_home;
    
    public Integer getContent_id() {
		return content_id;
	}

	public void setContent_id(Integer content_id) {
		this.content_id = content_id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isOn_home() {
		return on_home;
	}

	public void setOn_home(boolean on_home) {
		this.on_home = on_home;
	}

}
