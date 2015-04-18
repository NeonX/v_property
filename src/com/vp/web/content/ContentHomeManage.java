package com.vp.web.content;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

import com.vp.entity.Attachment;
import com.vp.entity.Content;
import com.vp.service.AttachmentService;
import com.vp.service.ContentService;
import com.vp.utils.AppUtils;
import com.vp.web.AbstractBackingBean;
import com.vp.web.utils.WebUtils;

@Synchronized(timeout = 90000)
@Name("contHomeMng")
@Scope(ScopeType.PAGE)
public class ContentHomeManage extends AbstractBackingBean<ContentHomeManage> {

	private ContentService contentService = (ContentService) getContextBackingBean().getBean("contentService");
	private AttachmentService atmService = (AttachmentService) getContextBackingBean().getBean("attachmentService");
	
	private Content content = new Content();
	private List<Attachment> listIMG;
	
	private boolean editMode = false;
	
	
	private String editor = "";
	
	
	public ContentHomeManage() {
		super(ContentHomeManage.class);
	}

	@Create
	public void init() {
		editMode = false;
		content = contentService.getLastContent();
		
		listIMG = atmService.getAttachList("CONTENT", "IMAGE", content.getContent_id());
		
		String root = AppUtils.getMessageByEL("attach.root.path");
		
		for(Attachment atm : listIMG){
			String url = WebUtils.getHostContextUrl()+"/imagex?srcPath=";
			String pathFile = root+atm.getAttachGroup()+"\\"+atm.getItemId()+"\\"+atm.getAttachType()+"\\"+atm.getFileNameRef();
			url += pathFile;
			atm.setUrl(url);
			url += "&toThumb=true";
			atm.setUrlThumb(url);
		}
		
	}
	
	public void doSaveContent(){
		
		content = contentService.saveContent(content);
		
		System.out.println(content.getContent());
		
		editMode = false;
	}
	
	public void doChangeMode(){
		if(editMode){
			editMode = false;
		}else{
			editMode = true;
		}
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public List<Attachment> getListIMG() {
		return listIMG;
	}

	public void setListIMG(List<Attachment> listIMG) {
		this.listIMG = listIMG;
	}
	    
	   
	
}

