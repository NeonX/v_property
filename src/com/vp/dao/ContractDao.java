package com.vp.dao;

import java.util.List;

import javax.persistence.Query;

import com.vp.entity.Contract;

public class ContractDao extends AbstractGenericDao<Contract, Integer>{
	
	public ContractDao() {
		super(ContentDao.class,Contract.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Contract> getContractByProperty(Integer ppt_id){
		try{
			String hql = " FROM Contract WHERE 1=1 and property.pptId="+ppt_id;
			
			Query q = getEntityManager().createQuery(hql);
			return q.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Object[]> getContractByProperty(Integer ppt_id) {
    	String sql ="SELECT * FROM contract AS ctr " +
    			"inner join property AS ppt on ctr.ppt_id = ppt.ppt_id " +
    			"where 1=1 AND ppt_id="+ppt_id;

        Query q = getEntityManager().createNativeQuery(sql);
        List<Object[]> list = q.getResultList();
        if (list.size() > 0) {
            return list;
        }
        return null;
	 }*/

}
