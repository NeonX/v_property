package com.vp.dao;

import java.util.List;

import javax.persistence.Query;

import com.vp.entity.SysUser;

public class SysUserDao extends AbstractGenericDao<SysUser, String> {

	public SysUserDao(){
		super(SysUserDao.class, SysUser.class);
	}

 	@SuppressWarnings("unchecked")
    public SysUser getSysUserByUserName(String name) {
        try {
            String hql = "FROM SysUser WHERE 1=1 ";
            if (name != null) {
                hql += "AND username = '" + name + "' ";
            }
            Query q = getEntityManager().createQuery(hql);
            List<SysUser> list = q.getResultList();
            if(list.size()>0){
            	 return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
	@SuppressWarnings("unchecked")
	public String getPassWordOldUserName(String username,String pwd){
		if(username != null && username.trim().length() > 0){
			String hql = "SELECT password FROM SysUser WHERE username = :uname and password = :pwd ";
			Query q = getEntityManager().createQuery(hql);
			q.setParameter("uname", username);
			q.setParameter("pwd", pwd);
			List<String> resultList = q.getResultList();
			if(resultList != null && resultList.size() > 0){
				return resultList.get(0);
			}
		}
		return null;
	}
 
	@SuppressWarnings("unchecked")
	public Integer getMaxIdxForAttachment() {
    	String sql ="SELECT max(sys.idx_attach) as mxidx FROM sysuser AS sys where 1=1 ";

        Query q = getEntityManager().createNativeQuery(sql);
        List<Object[]> list = q.getResultList();
        if (list.size() > 0) {
        	Object obj = list.get(0);
            return Integer.parseInt(obj.toString());
        }
        return null;
	 }
	
	public int doUpdateChangePWD(String[] user) {
	    try {
	        String sql = "update sysuser set \"password\" = encode(digest(username||'"+user[2]+"', 'sha1'), 'hex') where username= '"+user[0]+"' and \"password\" = encode(digest(username||'"+user[1]+"', 'sha1'), 'hex')" ;
	        Query q = getEntityManager().createNativeQuery(sql);

	        return q.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return 0;
	}
	 
	 public int removeSysUserByUserName(String name) {
	    try {
	        String hql = "DELETE FROM SysUser WHERE username = :name ";
	        Query q = getEntityManager().createQuery(hql);
	        q.setParameter("name", name);
	
	        return q.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return 0;
	}
 
	@SuppressWarnings("unchecked")
    public List<SysUser> getSysUserByCondition(String condition) {
        try {
            String hql = "FROM SysUser model WHERE 1 = 1 ";
            if (condition != null) {
                hql += condition;
            }
            hql +=" order by username ";
            Query q = getEntityManager().createQuery(hql);
            return q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
    @SuppressWarnings("unchecked")
    public List<Object[]> getRespondArea(String user) {
        String sql = "	SELECT dr.dep_id  " +
        			"	FROM depart_responsible AS dr WHERE dr.username = '"+user+"'";
        Query q = getEntityManager().createNativeQuery(sql);
        return q.getResultList();

    }
    
    @SuppressWarnings("unchecked")
    public List<Object[]> getUserInfo(String user) {
        String sql = "SELECT su.\"prefix\"||su.firstname||' '||su.lastname as rname, su.\"position\" " +
        			 "FROM sysuser as su where su.username = '"+user+"'";
        Query q = getEntityManager().createNativeQuery(sql);
        return q.getResultList();

    }
    
    public Object[] getAuthen(String user, String pwd) {
        String sql = "SELECT firstname, lastname, permission_level from sysuser where username = '"+user+"' and \"password\" = md5('"+pwd+"') ";
        Query q = getEntityManager().createNativeQuery(sql);
        List<Object[]> list =  q.getResultList();
        if(list!=null && list.size()>0){
        	return list.get(0);
        }
        
        return null;

    }
	
}
