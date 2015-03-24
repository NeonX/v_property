package com.vp.dao;



import java.util.List;

import com.vp.entity.HistoryUser;

public class HistoryUserDao extends AbstractGenericDao<HistoryUser, Integer>{
	
	public HistoryUserDao(){
		super(HistoryUser.class);
	}
	

	
	public Long getCountHistoryAll(){
		try {
			String sql = "select count(his.history_id) from history_user as his where 1=1";
			Object obj = getEntityManager().createNativeQuery(sql).getSingleResult();
			return new Long(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getHistoryUserAll(){
		
		try {
			String sql = " SELECT his.login_name FROM history_user as his GROUP BY his.login_name ORDER BY  his.login_name ASC ";
			return getEntityManager().createNativeQuery(sql).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	 @SuppressWarnings("unchecked")
	 public List<String> getUserNameAll() {
	        String sql = "SELECT his.login_name FROM history_user AS his where 1=1 GROUP BY his.login_name ORDER BY his.login_name asc ";
	        try {
	            return getEntityManager().createNativeQuery(sql).getResultList();
	        } catch (Exception ex) {
	        }
	        return null;
	 }
}

