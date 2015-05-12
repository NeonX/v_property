package com.vp.dao;

import java.util.List;

import javax.persistence.Query;

import com.vp.entity.Posession;

public class PosessionDao extends AbstractGenericDao<Posession, Integer> {
	
	public PosessionDao() {
		super(PosessionDao.class,Posession.class);
	}
	
	@SuppressWarnings("unchecked")
	public Posession getPosessionById(String id) {
		try{
			String hql = "from Posession where 1=1 And posId="+id;
			
			Query q = getEntityManager().createQuery(hql);
	
			List<Posession> list = q.getResultList();
			if(list != null && list.size() > 0){
				return list.get(0);
					}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Posession removePosession(String id) {
		try{
			String sql = "delete from posession where 1=1 And pos_id="+id;
			Query q = getEntityManager().createNativeQuery(sql);
			// getEntityManager().createQuery(hql);
	
			/*List<Posession> list = q.getResultList();
			if(list != null && list.size() > 0){
				return list.get(0);
					}*/
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
