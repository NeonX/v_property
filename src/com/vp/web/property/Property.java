package com.vp.web.property;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import com.vp.web.AbstractBackingBean;

@Name("propertyMng")
@Scope(ScopeType.PAGE)
public class Property extends AbstractBackingBean<Property>{
	
	private List<Property> propertyList;
	
    @Out(scope = ScopeType.SESSION, required=false)
    Integer pptId;
	
	public Property() {
		super(Property.class);
	}

	@Create
	public void init() {
		
	}
	
	public void doAddEditData(Integer idx){
		pptId = idx;
		forceRedirectPage("/property/land_from.xhtml");
	}

	public List<Property> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<Property> propertyList) {
		this.propertyList = propertyList;
	}

}
