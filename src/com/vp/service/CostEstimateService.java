package com.vp.service;

import java.util.List;

import com.vp.dao.CostEstimateDao;
import com.vp.entity.CostEstimate;

public class CostEstimateService {
	private CostEstimateDao costEstimateDao;
	
	public CostEstimate saveCostEstimate(CostEstimate costEstimate){
		return costEstimateDao.merge(costEstimate);
	}
	
	public void removeCostEstimate(CostEstimate costEstimate){
		costEstimateDao.remove(costEstimate);
	}
	
	public CostEstimate getCostEstimateById(String estmId) {
		return costEstimateDao.getCostEstimateById(estmId);
	}
	
	public List<CostEstimate> getCostEstimateListByCond(String cond){
		return costEstimateDao.getCostEstimateListByCond(cond);
	}
	
	public List<CostEstimate> getCostEstimateListById(String pptId){
		return costEstimateDao.getCostEstimateListById(pptId);
	}
	
	public CostEstimateDao getCostEstimateDao() {
		return costEstimateDao;
	}

	public void setCostEstimateDao(CostEstimateDao costEstimateDao) {
		this.costEstimateDao = costEstimateDao;
	}


}
