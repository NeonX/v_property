package com.vp.service;

import java.util.List;

import com.vp.dao.AttachmentDao;
import com.vp.entity.Attachment;

public class AttachmentService {
	private AttachmentDao attachmentDao;
	
	
	public List<Attachment> getAttachList(String group, String type, Integer item_id){
		return attachmentDao.getAttachList(group, type, item_id);
	}
	
	public List<Object[]> getAttachNativeList(String group, String type, Integer item_id){
		return attachmentDao.getAttachNativeList(group, type, item_id);
	}

	public AttachmentDao getAttachmentDao() {
		return attachmentDao;
	}

	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}
}
