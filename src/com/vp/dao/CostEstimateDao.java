package com.vp.dao;

import java.util.List;

import javax.persistence.Query;

import com.vp.entity.CostEstimate;

public class CostEstimateDao extends AbstractGenericDao<CostEstimate, Integer>{
	
	public CostEstimateDao() {
		super(CostEstimateDao.class,CostEstimate.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<CostEstimate> getCostEstimateList(Integer ppt_id){
		try{
			String hql = " FROM CostEstimate WHERE 1=1 and property.pptId="+ppt_id;
			
			Query q = getEntityManager().createQuery(hql);
			return q.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
}
