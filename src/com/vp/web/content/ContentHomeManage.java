package com.vp.web.content;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

import com.vp.entity.Content;
import com.vp.service.ContentService;
import com.vp.web.AbstractBackingBean;

@Synchronized(timeout = 90000)
@Name("contHomeMng")
@Scope(ScopeType.PAGE)
public class ContentHomeManage extends AbstractBackingBean<ContentHomeManage> {

	private ContentService contentService = (ContentService) getContextBackingBean().getBean("contentService");
	private Content content = new Content();
	private boolean editMode = false;
	
	private String editor = "";
	
	
	public ContentHomeManage() {
		super(ContentHomeManage.class);
	}

	@Create
	public void init() {
		editMode = false;
		content = contentService.getLastContent();
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
	    
	   
	
}

