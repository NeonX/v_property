package com.vp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="unit", schema="public")
@SequenceGenerator(name="unit_generator", sequenceName="unit_seq", allocationSize=1)
public class Unit {

	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="unit_generator")
	@Column(name="id", nullable=false)
	private Integer id;
	
	@Column(name="unit_name")
	private String unitName;
	
	@Column(name="order_no")
	private Integer orderNo;
	
	@Column(name="dept_id")
	private Integer deptId;
	
	public Unit(){}
	
	public Unit(String name, Integer orderNo){
		this.unitName = name;
		this.orderNo = orderNo;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
	
}
