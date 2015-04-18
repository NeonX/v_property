package com.vp.web.contract;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import com.vp.entity.Contract;
import com.vp.web.AbstractBackingBean;


@Name("contrListMng")
@Scope(ScopeType.PAGE)
public class ContractListManage extends AbstractBackingBean<ContractListManage> {

	private List<Contract> contList;

    @Out(scope = ScopeType.SESSION, required=false)
    Integer contractId;
	
	public ContractListManage() {
		super(ContractListManage.class);
	}

	@Create
	public void init() {
		contList = new ArrayList<Contract>();
		
	}
	
	public void doAddEditData(Integer idx){
		contractId = idx;
		forceRedirectPage("/contract/contract_form.xhtml");
	}

	public List<Contract> getContList() {
		return contList;
	}

	public void setContList(List<Contract> contList) {
		this.contList = contList;
	}
	

	
}

