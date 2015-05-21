package com.vp.dao;

import java.util.List;

import javax.persistence.Query;

import com.vp.entity.CostEstimate;

public class CostEstimateDao extends AbstractGenericDao<CostEstimate, Integer>{
	
	public CostEstimateDao() {
		super(CostEstimateDao.class,CostEstimate.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<CostEstimate> getCostEstimateListById(String pptId){
		try{
			String hql = " FROM CostEstimate WHERE 1=1 and property.pptId="+pptId+" order by updateDate DESC";
			
			Query q = getEntityManager().createQuery(hql);
			return q.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CostEstimate> getCostEstimateListByCond(String cond){
		try{
			String hql = " FROM CostEstimate WHERE 1=1 ";
			if(cond != null){
				hql += cond;
			}
			Query q = getEntityManager().createQuery(hql);
			return q.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	@SuppressWarnings("unchecked")
	public CostEstimate getCostEstimateById(String estmId) {
		String hql =" FROM CostEstimate WHERE 1=1 AND estmId = "+estmId;

		Query q = getEntityManager().createQuery(hql);
        List<CostEstimate> list = q.getResultList();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
	}
}
