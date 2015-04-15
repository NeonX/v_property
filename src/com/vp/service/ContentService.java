package com.vp.service;

import com.vp.dao.ContentDao;
import com.vp.entity.Content;

public class ContentService {
	private ContentDao contentDao;
	
	
	public Content getLastContent(){
		return contentDao.getLastContent();
	}
	
	public Content saveContent(Content content){
		return contentDao.merge(content);
	}

	public ContentDao getContentDao() {
		return contentDao;
	}

	public void setContentDao(ContentDao contentDao) {
		this.contentDao = contentDao;
	}

	
}
