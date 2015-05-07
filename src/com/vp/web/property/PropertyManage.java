package com.vp.web.property;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import com.vp.service.PropertyService;
import com.vp.web.AbstractBackingBean;

@Name("propertyMng")
@Scope(ScopeType.PAGE)
public class PropertyManage extends AbstractBackingBean<PropertyManage>{
	
	/*private ContentService contentService = (ContentService) getContextBackingBean().getBean("contentService");
	private AttachmentService atmService = (AttachmentService) getContextBackingBean().getBean("attachmentService");*/
	
    private PropertyService propertyService = (PropertyService) getContextBackingBean().getBean("propertyService");
	
    private List<Object[]> dataList;
    private String owner = null;
    @Out(scope = ScopeType.SESSION, required = false)
    String pptId;
    @Out(scope = ScopeType.SESSION, required = false)
    String ownerId;
    
    public PropertyManage() {
		super(PropertyManage.class);
	}

	@Create
	public void init() {
		dataList = propertyService.getPropertyAll();
		//System.out.println(dataList);
	}
	
	public void searchByOwnerName(){
		String cond = "AND ow.owner_name LIKE '%%"+owner+"%%'";
		dataList = propertyService.getPropertyByCond(cond);
		System.out.println(dataList);
	}
	
	public void doAddEditData(String pid,String oid){
		pptId = pid;
		ownerId = oid;
		System.out.println("ownerId "+ownerId );
		forceRedirectPage("/property/land_from.xhtml");
	}
	
	public void doDeleteData(String pid,String oid){
		//
	}

	public List<Object[]> getDataList() {
		return dataList;
	}

	public void setDataList(List<Object[]> datalist) {
		this.dataList = datalist;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
