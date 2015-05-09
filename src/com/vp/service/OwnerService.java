package com.vp.service;

import com.vp.dao.OwnerDao;
import com.vp.entity.Owner;

public class OwnerService {
	
	private OwnerDao ownerDao; 

	public Owner getOwnerById(String ownerId) {
		return ownerDao.getOwnerById(ownerId);
	}

	public OwnerDao getOwnerDao() {
		return ownerDao;
	}

	public void setOwnerDao(OwnerDao ownerDao) {
		this.ownerDao = ownerDao;
	}

}
