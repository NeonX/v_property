package com.vp.dao;

import java.util.List;

import javax.persistence.Query;

import com.vp.entity.Attachment;

public class AttachmentDao extends AbstractGenericDao<Attachment, Integer> {

	public AttachmentDao(){
		super(AttachmentDao.class, Attachment.class);
	}

	@SuppressWarnings("unchecked")
	public List<Attachment> getAttachList(String group, String type, Integer item_id){
		try{
			String hql = " FROM Attachment WHERE attachGroup = '"+group+"' "+
						 " and attachType = '"+type+"' and itemId="+item_id;
			
			Query q = getEntityManager().createQuery(hql);
			return q.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAttachNativeList(String group, String type, Integer item_id){
		String sql =" SELECT atm.atm_id,atm.attach_group, atm.attach_type, atm.attach_name, " +
					"			 atm.attach_desc, atm.file_size, atm.update_date, atm.update_by " +
					" FROM attachment AS atm " +
					" WHERE atm.attach_group = '" +group+"' "+
					" AND	atm.attach_type = '" +type+"' "+
					" AND atm.item_id =  " +item_id+
					" ORDER BY atm.attach_order ASC,atm.atm_id DESC";

		Query q = getEntityManager().createNativeQuery(sql);
		List<Object[]> list = q.getResultList();
		if (list.size() > 0) {
			return list;
		}
		return null;
	}
	
}
