package com.vp.dao;

import java.util.List;

import javax.persistence.Query;

import com.vp.entity.Content;
import com.vp.entity.Property;

public class PropertyDao extends AbstractGenericDao<Property, Integer>{
	
	public PropertyDao(){
		super(PropertyDao.class,Property.class);
	}
	
	@SuppressWarnings("unchecked")
	public Property getPropertyBypptId(String id) {
		try{
			
			String hql = "FROM Property WHERE pptId ="+id;
				Query q = getEntityManager().createQuery(hql);

				List<Property> list = q.getResultList();
				if(list != null && list.size() > 0){
					return list.get(0);
				}

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	 }
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPropertyAll() {
    	String sql ="SELECT pr.ppt_id, pr.prop_code, pr.p_address, pr.prop_desc,ow.owner_name,ow.owner_id FROM property AS pr " +
    			"inner join posession AS ps on pr.ppt_id = ps.ppt_id " +
    			"inner join owner AS ow on ow.owner_id = ps.owner_id " +
    			"where 1=1 ";

        Query q = getEntityManager().createNativeQuery(sql);
        List<Object[]> list = q.getResultList();
        if (list.size() > 0) {
            return list;
        }
        return null;
	 }
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPropertyByCond(String cond) {
		String sql ="SELECT pr.ppt_id, pr.prop_code, pr.p_address, pr.prop_desc,ow.owner_name,ow.owner_id FROM property AS pr " +
    			"inner join posession AS ps on pr.ppt_id = ps.ppt_id " +
    			"inner join owner AS ow on ow.owner_id = ps.owner_id " +
    			"where 1=1 ";
		if(cond != null){
			sql += cond;
		}

        Query q = getEntityManager().createNativeQuery(sql);
        List<Object[]> list = q.getResultList();
        if (list.size() > 0) {
            return list;
        }
        return null;
	}

	
}
