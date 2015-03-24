package com.vp.dao;

import java.util.List;

import javax.persistence.Query;

import com.vp.entity.Unit;

public class UnitDao extends AbstractGenericDao<Unit, Integer> {

	public UnitDao() {
		super(UnitDao.class, Unit.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllUnitName(){
		try{
			
				String hql = "SELECT unitName FROM Unit ORDER BY orderNo ";
				Query q = getEntityManager().createQuery(hql);
				return q.getResultList();
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
