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
		String hql ="FROM Owner model WHERE 1=1 AND model.ownerId ="+ownerId;

		Query q = getEntityManager().createQuery(hql);
		
        List<Owner> list = q.getResultList();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Owner> getOwnerCondition(String conn) {
		String hql ="FROM Owner model WHERE 1=1 ";
		if(conn!=null){
			hql += conn;
		}
		Query q = getEntityManager().createQuery(hql);
		
        List<Owner> list = q.getResultList();
        if (list.size() > 0) {
            return list;
        }
        return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getOwnerNameAll() {
		String sql = "SELECT * from owner where 1=1 "; 
		Query q = getEntityManager().createNativeQuery(sql);
        List<Object[]> list = q.getResultList();
        if (list.size() > 0) {
            return list;
        }
        return null;
		/*String hql ="FROM Owner model WHERE 1=1";

		Query q = getEntityManager().createQuery(hql);
		
        List<Owner> list = q.getResultList();
        
        return list;*/
	}

	@SuppressWarnings("unchecked")
	public Owner getOwnerByName(String ownerName) {
		String hql ="FROM Owner model WHERE 1=1 AND model.ownerName ='"+ownerName+"'";

		Query q = getEntityManager().createQuery(hql);
		
        List<Owner> list = q.getResultList();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
	}
	
}
