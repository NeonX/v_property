package com.vp.dao;

import java.util.List;

import javax.persistence.Query;

import com.vp.entity.Coordinate;

public class CoordinateDao extends AbstractGenericDao<Coordinate, Integer> {
	
	public CoordinateDao() {
		super(CoordinateDao.class,Coordinate.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Coordinate> getCoordinateByCondition(String conn) {
		try{
			String hql = " FROM Coordinate WHERE 1=1 ";
			if(conn != null){
				hql += conn;
			}
			
			Query q = getEntityManager().createQuery(hql);
			return q.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
