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
	
}
