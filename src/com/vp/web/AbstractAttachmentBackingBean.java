package com.vp.web;

import java.util.ArrayList;
import java.util.List;

import com.vp.service.AttachmentService;


public class AbstractAttachmentBackingBean<T> extends AbstractBackingBean<T> {
	private AttachmentService attachmentService = (AttachmentService) getContextBackingBean().getBean("attachmentService");
	
	private List<Object[]> listDoc;
	private List<Object[]> listImg;
	private String attachGrp;
	private Integer itemId;
	

	public AbstractAttachmentBackingBean(Class<T> persistentClass) {
		super(persistentClass);
	}
	
	public void initAttachment(String attGrp, Integer itmId, Boolean attDoc, Boolean attImag){
		attachGrp = attGrp;
		itemId = itmId;
		if(attDoc){
			if(itemId != null){
				listDoc = attachmentService.getAttachNativeList(attachGrp, "DOCUMENT", itemId);
			}else{
				listDoc = new ArrayList<Object[]>();
			}
		}
		
		if(attImag){
			if(itemId != null){
				listImg = attachmentService.getAttachNativeList(attachGrp, "IMAGE", itemId);
			}else{
				listImg = new ArrayList<Object[]>();
			}	
		}
	}
	
	

	public void setListDoc(List<Object[]> listDoc) {
		this.listDoc = listDoc;
	}

	public List<Object[]> getListDoc() {
		return listDoc;
	}

	public void setListImg(List<Object[]> listImg) {
		this.listImg = listImg;
	}

	public List<Object[]> getListImg() {
		return listImg;
	}

	public void setAttachGrp(String attachGrp) {
		this.attachGrp = attachGrp;
	}

	public String getAttachGrp() {
		return attachGrp;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getItemId() {
		return itemId;
	}

}
