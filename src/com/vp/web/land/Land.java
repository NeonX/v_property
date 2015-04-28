package com.vp.web.land;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import com.vp.web.AbstractBackingBean;

@Name("landMng")
@Scope(ScopeType.PAGE)
public class Land extends AbstractBackingBean<Land>{

    @Out(scope = ScopeType.SESSION, required=false)
    Integer contractId;
	
	public Land() {
		super(Land.class);
	}

	@Create
	public void init() {
		
	}
	
	public void doAddEditData(Integer idx){
		contractId = idx;
		forceRedirectPage("/property/land_from.xhtml");
	}

}
