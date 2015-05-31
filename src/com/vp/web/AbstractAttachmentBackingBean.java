package com.vp.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.vp.entity.Attachment;
import com.vp.service.AttachmentService;
import com.vp.utils.AppUtils;


public class AbstractAttachmentBackingBean<T> extends AbstractBackingBean<T> {
	private AttachmentService attachmentService = (AttachmentService) getContextBackingBean().getBean("attachmentService");
	
	private int listSize = 8;
	
	private List<Object[]> listDoc;
	private List<Object[]> listImg;
	private String attachGrp;
	private Integer itemId;
	private String attachType;
	
	protected List<Attachment> fileItems;
	private String path = "";
	
	private int rsSave = 0;;
	

	public AbstractAttachmentBackingBean(Class<T> persistentClass) {
		super(persistentClass);
	}
	
	public void initAttachment(String attGrp, Integer itmId, Boolean attDoc, Boolean attImag){
		attachGrp = attGrp;
		itemId = (itmId==null || itmId==0)?null:itmId;
		path = AppUtils.getMessageByEL("attach.root.path")+attachGrp+"\\"+itmId+"\\";
		
		prepareList(attDoc,attImag);
	}
	
	public void prepareList(Boolean attDoc, Boolean attImag){
		if(attDoc){
			if(itemId != null){
				listDoc = attachmentService.getAttachNativeList(attachGrp, "DOCUMENT", itemId);
			}else{
				listDoc = new ArrayList<Object[]>();
			}
			listDoc = fillList(listDoc, listSize);
		}
		
		if(attImag){
			if(itemId != null){
				listImg = attachmentService.getAttachNativeList(attachGrp, "IMAGE", itemId);
			}else{
				listImg = new ArrayList<Object[]>();
			}	
			listImg = fillList(listImg, listSize);
		}
	}
	
	
	
	public void doPrepareUpload(String attType){
		attachType = attType;
		fileItems = new ArrayList<Attachment>();
		rsSave = 0;
	}
	
	public void doListenerUpload(UploadEvent uploadEvent)throws Exception{
		UploadItem uploadItem = uploadEvent.getUploadItem();
		
		String refName = generateRefName(uploadItem.getFileName());
		
		if(refName!=null){
			Attachment attach = new Attachment();
			attach.setFileTmp(uploadItem.getFile());
			attach.setAttachName(uploadItem.getFileName());
			attach.setFileNameRef(refName);
			attach.setAttachGroup(attachGrp);
			attach.setItemId(itemId);
			attach.setAttachType(attachType);
			attach.setFileSize(uploadItem.getFileSize());
			attach.setCreateDate(new Date());
			attach.setCreateBy(credentials.getUsername());
			attach.setUpdateDate(new Date());
			attach.setUpdateBy(credentials.getUsername());
			attach.setAttachOrder(0);
			
			// set more detail
			
			fileItems.add(attach);
		}
		
		rsSave = 1;
	}
	
	public String generateRefName(String oriName){
		if(oriName!=null){
			if(oriName.contains(".")){
				String tmp[] = StringUtils.split(oriName,".");
				String subfix = tmp[1];
				String fnameRef = new SimpleDateFormat("ddMMyyyy_hhmmss").format(new Date());
				
				return fnameRef+"."+subfix;
				
			}
		}
		
		return null;
	}
	
	public void doSaveAttach(){
		rsSave = 2;
		try {
			for(Attachment att:fileItems){
				String ptmp = path+att.getAttachType();//+"\\"+att.getFileNameRef();
				File des = new File(ptmp);
				if(!des.exists()){
					des.mkdirs();
				}
				ptmp = path+att.getAttachType()+"\\"+att.getFileNameRef();
				des = new File(ptmp);
				File src = att.getFileTmp();
				
				src.renameTo(des);
				
				att = attachmentService.saveAttachment(att);
			}
			
			rsSave = 3;
			
			if("DOCUMENT".equals(attachType)){
				prepareList(true,false);
			}else{
				prepareList(false,true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			rsSave = 4;
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

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getListSize() {
		return listSize;
	}

	public List<Attachment> getFileItems() {
		return fileItems;
	}

	public void setFileItems(List<Attachment> fileItems) {
		this.fileItems = fileItems;
	}

	public void setRsSave(int rsSave) {
		this.rsSave = rsSave;
	}

	public int getRsSave() {
		return rsSave;
	}

}
