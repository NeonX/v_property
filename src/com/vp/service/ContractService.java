package com.vp.service;

import java.util.List;

import com.vp.dao.ContractDao;
import com.vp.entity.Contract;

public class ContractService {
	private ContractDao contractDao;

	public List<Contract> getContractListByCond(String cond){
		return contractDao.getContractListByCond(cond);
	}
	
	public void setContractDao(ContractDao contractDao) {
		this.contractDao = contractDao;
	}

	public ContractDao getContractDao() {
		return contractDao;
	}
	
}
