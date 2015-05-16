package com.vp.web.contract;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import com.vp.entity.Contract;
import com.vp.service.ContractService;
import com.vp.web.AbstractBackingBean;


@Name("contrListMng")
@Scope(ScopeType.PAGE)
public class ContractListManage extends AbstractBackingBean<ContractListManage> {
	
	private ContractService contractService = (ContractService) getContextBackingBean().getBean("contractService");

	private List<Object[]> contList;

    @Out(scope = ScopeType.SESSION, required=false)
    Integer contractId;
	
	public ContractListManage() {
		super(ContractListManage.class);
	}

	@Create
	public void init() {
		contList = new ArrayList<Object[]>();
		
		contList = contractService.getConNativeList(null);
		
	}
	
	public void doAddEditData(Integer idx){
		contractId = idx;
		forceRedirectPage("/contract/contract_form.xhtml");
	}

	public List<Object[]> getContList() {
		return contList;
	}

	public void setContList(List<Object[]> contList) {
		this.contList = contList;
	}
	

	
}

