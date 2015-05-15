package com.vp.dao;

import java.util.List;

import javax.persistence.Query;

import com.vp.entity.PlotRent;

public class PlotRentDao extends AbstractGenericDao<PlotRent, Integer> {
	
	public PlotRentDao(){
		super(PlotRentDao.class,PlotRent.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<PlotRent> getPlotRentBypptId(String id) {
		try{
			
			String hql = "FROM PlotRent model WHERE model.property.pptId ="+id;
				Query q = getEntityManager().createQuery(hql);

				List<PlotRent> list = q.getResultList();
				if(list != null && list.size() > 0){
					return list;
				}

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	 }
}
