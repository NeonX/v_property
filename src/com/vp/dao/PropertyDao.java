package com.vp.dao;

import java.util.List;

import javax.persistence.Query;

import com.vp.entity.Property;

public class PropertyDao extends AbstractGenericDao<Property, Integer>{
	
	public PropertyDao(){
		super(PropertyDao.class,Property.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPropertyById(Integer id) {
    	String sql ="SELECT * FROM property AS pr " +
    			"inner join posession AS ps on pr.ppt_id = ps.ppt_id " +
    			"inner join owner AS ow on ow.owner_id = ps.owner_id " +
    			"where 1=1 AND ppt_id="+id;

        Query q = getEntityManager().createNativeQuery(sql);
        List<Object[]> list = q.getResultList();
        if (list.size() > 0) {
            return list;
        }
        return null;
	 }
	
}
