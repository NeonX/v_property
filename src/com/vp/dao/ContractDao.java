package com.vp.dao;

import java.util.List;

import javax.persistence.Query;

import com.vp.entity.Contract;
import com.vp.utils.AppUtils;

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
	
	@SuppressWarnings("unchecked")
	public List<Contract> getContractListByCond(String cond){
		try{
			String hql = " FROM Contract WHERE 1=1 ";
			if(!AppUtils.isNullOrEmpty(cond)){
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
	public List<Object[]> getConNativeList(String cond){
    	String sql ="SELECT " +
			    	"ct.cont_code, " +
			    	"ct.renter_name, " +
			    	"area_str(cast(COALESCE(Sum(pr.plot_size),0) as numeric)), " +
			    	"date_part('year',cont_end)-date_part('year',cont_begin) as yrs, " +
			    	"to_char(ct.cont_begin,'DD-MM-YYYY') as bg_d, " +
			    	"to_char(ct.cont_end,'DD-MM-YYYY') as en_d, "+
			    	"cp.ct_id "+
			    	"FROM contract AS ct " +
			    	"INNER JOIN contract_plot AS cp ON cp.ct_id = ct.ct_id " +
			    	"INNER JOIN plot_rent AS pr ON cp.pr_id = pr.pr_id " +
			    	"WHERE 1 = 1 ";
    	if(!AppUtils.isNullOrEmpty(cond)){
    		sql += cond;
    	}
    	
    	sql += " GROUP BY cp.ct_id,ct.cont_code,ct.renter_name,ct.cont_begin,ct.cont_end " +
    		   " ORDER BY ct.cont_code DESC ";

        Query q = getEntityManager().createNativeQuery(sql);
        List<Object[]> list = q.getResultList();
        if (list.size() > 0) {
            return list;
        }
        return null;
	 }

}
