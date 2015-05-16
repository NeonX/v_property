package com.vp.dao;

import java.util.List;

import javax.persistence.Query;

import com.vp.entity.ContractPlot;

public class ContractPlotDao extends AbstractGenericDao<ContractPlot, Integer>{
	
	public ContractPlotDao() {
		super(ContractPlotDao.class,ContractPlot.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getContPlotListByCtID(String ct_id){
    	String sql ="SELECT ow.owner_name,pp.plot_code, " +
			    	"			 Sum(cp.rental_rate) as rental_rate,Sum(cp.tax_rate) as tax_rate " +
			    	"FROM contract_plot AS cp " +
			    	"INNER JOIN plot_rent AS pr ON cp.pr_id = pr.pr_id " +
			    	"INNER JOIN property AS pp ON pr.ppt_id = pp.ppt_id " +
			    	"INNER JOIN (SELECT px.ppt_id,max(px.owner_id) as owner_id FROM posession AS px GROUP BY px.ppt_id) AS ps ON ps.ppt_id = pp.ppt_id " +
			    	"INNER JOIN \"owner\" AS ow ON ps.owner_id = ow.owner_id " +
			    	"WHERE cp.ct_id = " +ct_id+" "+
			    	"GROUP BY cp.ct_id,ow.owner_name,pp.plot_code,cp.cp_id " +
			    	"ORDER BY cp.cp_id ASC ";

        Query q = getEntityManager().createNativeQuery(sql);
        List<Object[]> list = q.getResultList();
        if (list.size() > 0) {
            return list;
        }
        return null;
	 }

}
