package com.vp.dao;

import java.util.List;

import javax.persistence.Query;

import com.vp.entity.CostForSale;

public class CostForSaleDao extends AbstractGenericDao<CostForSale, Integer> {
	
	public CostForSaleDao() {
		super(CostEstimateDao.class,CostForSale.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<CostForSale> getCostForSale(Integer ppt_id){
		try{
			String hql = " FROM CostForSale WHERE 1=1 and property.pptId="+ppt_id;
			
			Query q = getEntityManager().createQuery(hql);
			return q.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
}
