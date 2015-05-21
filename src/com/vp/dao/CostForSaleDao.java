package com.vp.dao;

import java.util.List;

import javax.persistence.Query;

import com.vp.entity.CostForSale;

public class CostForSaleDao extends AbstractGenericDao<CostForSale, Integer> {
	
	public CostForSaleDao() {
		super(CostEstimateDao.class,CostForSale.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<CostForSale> getCostForSaleListById(String ppt_id){
		try{
			String hql = " FROM CostForSale WHERE 1=1 and property.pptId="+ppt_id+" order by updateDate DESC";
			
			Query q = getEntityManager().createQuery(hql);
			return q.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CostForSale> getCostForSaleByCond(String cond){
		try{
			String hql = " FROM CostForSale WHERE 1=1 ";
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
	public CostForSale getCostForSaleById(String saleId) {
		String hql =" FROM CostForSale WHERE 1=1 AND saleId = "+saleId;

		Query q = getEntityManager().createQuery(hql);
        List<CostForSale> list = q.getResultList();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
	}

}
