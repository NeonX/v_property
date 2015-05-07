package com.vp.service;

import java.util.List;

import com.vp.dao.PropertyDao;
import com.vp.entity.Property;

public class PropertyService {
	private PropertyDao propertyDao;
	
	public List<Object[]> getPropertyAll() {
		return propertyDao.getPropertyAll();
	}
	
	public List<Object[]> getPropertyByCond(String cond) {
		return propertyDao.getPropertyByCond(cond);
	}
	
	public Property getPropertyBypptId(String id) {
		return propertyDao.getPropertyBypptId(id);
	}
	
	public PropertyDao getPropertyDao() {
		return propertyDao;
	}

	public void setPropertyDao(PropertyDao propertyDao) {
		this.propertyDao = propertyDao;
	}

	
	

}
