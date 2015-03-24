package com.vp.service;


import java.util.List;

import com.vp.dao.HistoryUserDao;
import com.vp.entity.HistoryUser;
import com.vp.utils.CommonType;

public class HistoryUserService {

	private HistoryUserDao historyUserDao;

	
	public final static String HISTORY_SERVICENAME = "historyUserService";
	
	public List<HistoryUser> getHistoryAll(){
		return historyUserDao.getAll("update_date", CommonType.DESC);
	}
	
	
	public Long getCountHistoryAll(){
		return historyUserDao.getCountHistoryAll();
	}
	
	public HistoryUser doSaveHistoryUser(HistoryUser user){
		return historyUserDao.merge(user);
	}
	

	
	// SETTTER AND GETTER
	public HistoryUserDao getHistoryUserDao() {
		return historyUserDao;
	}
	public void setHistoryUserDao(HistoryUserDao historyUserDao) {
		this.historyUserDao = historyUserDao;
	}



	
}
