package com.vp.service;

import com.vp.dao.OwnerDao;
import com.vp.entity.Owner;

public class OwnerService {
	
	private OwnerDao ownerDao = new OwnerDao(); 

	public Owner getOwnerById(String ownerId) {
		return ownerDao.getOwnerById(ownerId);
	}

}
