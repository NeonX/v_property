package com.vp.dao;

import com.vp.entity.Posession;

public class PosessionDao extends AbstractGenericDao<Posession, Integer> {
	
	public PosessionDao() {
		super(PosessionDao.class,Posession.class);
	}
	
}
