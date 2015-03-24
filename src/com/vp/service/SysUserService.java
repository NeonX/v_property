package com.vp.service;

import java.util.List;

import com.vp.dao.SysUserDao;
import com.vp.entity.SysUser;
import com.vp.utils.CommonType;

public class SysUserService {
	private SysUserDao sysUserDao;

	public List<SysUser> getByLimitAndOffset(String sql,Integer limit,Integer offsets ,String fildCriteria,CommonType pteType){
		return sysUserDao.getByLimitAndOffset(sql, limit, offsets, fildCriteria, pteType);
	}
	
	public List<SysUser> getSysUserAll(){
		return sysUserDao.getAll("username", CommonType.ASC);
	}
	
	public List<SysUser> getSysUserByCondition(String con){
		return sysUserDao.getSysUserByCondition(con);
	}
	
	public Integer getMaxIdxForAttachment(){
		return sysUserDao.getMaxIdxForAttachment();
	}
	
	public String getPassWordOldUserName(String name,String pwd){
		return sysUserDao.getPassWordOldUserName(name,pwd);
	}
	
	public SysUser getSysUserByuserName(String name){
		return sysUserDao.getSysUserByUserName(name);
	}
	
	public SysUser doSaveSysUser(SysUser user){
		return sysUserDao.merge(user);
	}
	
	public int doRemoveSysUserByUserName(String username){
		return sysUserDao.removeSysUserByUserName(username);
	}
	
	public List<Object[]> getRespondArea(String user) {
		return sysUserDao.getRespondArea(user);
	}
	
	public List<Object[]> getUserInfo(String user) {
		return sysUserDao.getUserInfo(user);
	}
	
	public int doUpdateChangePWD(String[] user) {
		return (sysUserDao.doUpdateChangePWD(user)>0?2:1);
	}
	
	////-------SETTER AND GETTER ---///
	public SysUserDao getSysUserDao() {
		return sysUserDao;
	}

	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	
	
}
