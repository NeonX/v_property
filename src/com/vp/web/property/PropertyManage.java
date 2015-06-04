package com.vp.web.property;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import com.vp.entity.Posession;
import com.vp.entity.Property;
import com.vp.service.PropertyService;
import com.vp.web.AbstractBackingBean;

@Name("propertyMng")
@Scope(ScopeType.PAGE)
public class PropertyManage extends AbstractBackingBean<PropertyManage>{
	
    private PropertyService propertyService = (PropertyService) getContextBackingBean().getBean("propertyService");
	
    private List<Object[]> dataList;
    
    private String strSearch = null;
    private Integer searchType = 0;
    private Integer pageRow = 9;
    
    @Out(scope = ScopeType.SESSION, required = false)
    String pptId;
    @Out(scope = ScopeType.SESSION, required = false)
    String ownerId;
    @Out(scope = ScopeType.SESSION, required = false)
    String posId;
    
    public PropertyManage() {
		super(PropertyManage.class);
	}

	@Create
	public void init() {
		dataList = propertyService.getPropertyAll();
	}
	
	public void searchData(){
		if(searchType == 1){
			dataList = propertyService.getPropertyByPropCode(strSearch);
			System.out.println(dataList);
		}else{
			String cond = "AND ow.owner_name LIKE '%%"+strSearch+"%%'";
			dataList = propertyService.getPropertyByCond(cond);
			System.out.println(dataList);
		}
		
	}
	
	public void doAddEditData(String pid,String oid,String posid){
		pptId = pid;
		ownerId = oid;
		posId = posid;
		forceRedirectPage("/property/land_from.xhtml");
	}
	
	public void doDeleteData(String posId,String pptid){
		this.posId = posId;
		pptId = pptid;
	}
	
	public void remove() {
		
		Property property = propertyService.getPropertyBypptId(pptId);
		
		Posession posession = propertyService.getPosessionById(posId);
		
		propertyService.removePosession(posession);
		propertyService.remove(property);
		
		prepraeData();
	}
	
	public void prepraeData(){
		posId = null;
		pptId = null;
		ownerId = null;
		dataList = propertyService.getPropertyAll();
	}

	public List<Object[]> getDataList() {
		return dataList;
	}

	public void setDataList(List<Object[]> datalist) {
		this.dataList = datalist;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public String getStrSearch() {
		return strSearch;
	}

	public void setStrSearch(String strSearch) {
		this.strSearch = strSearch;
	}

	public Integer getPageRow() {
		return pageRow;
	}

	public void setPageRow(Integer pageRow) {
		this.pageRow = pageRow;
	}
	
	
}
