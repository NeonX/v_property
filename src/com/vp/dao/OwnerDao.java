package com.vp.dao;

import java.util.List;

import javax.persistence.Query;

import com.vp.entity.Owner;

public class OwnerDao extends AbstractGenericDao<Owner, Integer>{
	
	public OwnerDao() {
		super(OwnerDao.class,Owner.class);
	}
	
	@SuppressWarnings("unchecked")
	public Owner getOwnerById(String ownerId) {
		String hql =" FROM Owner model WHERE 1=1 AND model.ownerId ="+ownerId;

		Query q = getEntityManager().createQuery(hql);
		
        List<Owner> list = q.getResultList();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
	}
	
	
}
