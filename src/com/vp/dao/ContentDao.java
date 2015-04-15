package com.vp.dao;

import java.util.List;

import javax.persistence.Query;

import com.vp.entity.Content;

public class ContentDao extends AbstractGenericDao<Content, Integer> {

	public ContentDao(){
		super(ContentDao.class, Content.class);
	}
	
	@SuppressWarnings("unchecked")
	public Content getLastContent(){
		try{
			
			String hql = "FROM Content WHERE content_id = (select max(content_id) from Content) ";
				Query q = getEntityManager().createQuery(hql);

				List<Content> list = q.getResultList();
				if(list != null && list.size() > 0){
					return list.get(0);
				}

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

 	
}
