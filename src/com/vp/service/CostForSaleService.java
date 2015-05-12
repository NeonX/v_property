package com.vp.service;

import java.util.List;

import com.vp.dao.CostForSaleDao;
import com.vp.entity.CostForSale;

public class CostForSaleService {
	
	private CostForSaleDao costForSaleDao;
	
	public CostForSale saveCostForSale(CostForSale costForSale){
		return costForSaleDao.merge(costForSale);
	}
	
	public void removeForSale(CostForSale costForSale){
		costForSaleDao.remove(costForSale);
	}
	
	public CostForSale getCostForSaleById(String estmId) {
		return costForSaleDao.getCostForSaleById(estmId);
	}
	
	public List<CostForSale> getCostForSaleListById(String pptId) {
		return costForSaleDao.getCostForSaleListById(pptId);
	}
	
	public List<CostForSale> getCostForSaleByCond(String cond ) {
		return costForSaleDao.getCostForSaleByCond(cond);
	}
	
	public CostForSaleDao getCostForSaleDao() {
		return costForSaleDao;
	}

	public void setCostForSaleDao(CostForSaleDao costForSaleDao) {
		this.costForSaleDao = costForSaleDao;
	}


}
