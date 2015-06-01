package com.vp.service;

import java.util.List;

import com.vp.dao.ContractDao;
import com.vp.dao.ContractPlotDao;
import com.vp.entity.Contract;
import com.vp.entity.ContractPlot;

public class ContractService {
	private ContractDao contractDao;
	private ContractPlotDao contractPlotDao;

	public List<Contract> getContractListByCond(String cond){
		return contractDao.getContractListByCond(cond);
	}
	
	public List<Object[]> getConNativeList(String cond){
		return contractDao.getConNativeList(cond);
	}
	
	public Contract getContractByID(Integer id){
		return contractDao.findById(id);
	}
	
	public Contract saveContract(Contract cont){
		return contractDao.merge(cont);
	}
	
	public List<Object[]> getContPlotListByCtID(String ct_id){
		return contractPlotDao.getContPlotListByCtID(ct_id);
	}
	
	public List<ContractPlot> getListContPlotByCtID(String ct_id){
		return contractPlotDao.getListContPlotByCtID(ct_id);
	}
	
	public ContractPlot getContractPlotByID(Integer ctId){
		return contractPlotDao.findById(ctId);
	}
	
	public void saveContractPlotList(List<ContractPlot> listCP){
		contractPlotDao.merge(listCP);
	}
	
	
	
	public void setContractDao(ContractDao contractDao) {
		this.contractDao = contractDao;
	}

	public ContractDao getContractDao() {
		return contractDao;
	}

	public void setContractPlotDao(ContractPlotDao contractPlotDao) {
		this.contractPlotDao = contractPlotDao;
	}

	public ContractPlotDao getContractPlotDao() {
		return contractPlotDao;
	}
	
}
