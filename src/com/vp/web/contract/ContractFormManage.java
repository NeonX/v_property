package com.vp.web.contract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.vp.entity.Contract;
import com.vp.entity.ContractPlot;
import com.vp.service.ContractService;
import com.vp.web.AbstractBackingBean;


@Name("contrFormMng")
@Scope(ScopeType.PAGE)
public class ContractFormManage extends AbstractBackingBean<ContractFormManage> {
	
	private ContractService contractService = (ContractService) getContextBackingBean().getBean("contractService");
	private Contract contr;
	private List<Object[]> ctPlotList; 
	private Integer staus = 0;

    @In(scope = ScopeType.SESSION, required=false)
    Integer contractId;
	
	public ContractFormManage() {
		super(ContractFormManage.class);
	}

	@Create
	public void init() {
		if(contractId !=null){
			contr = contractService.getContractByID(contractId);
			ctPlotList = contractService.getContPlotListByCtID(contractId.toString());
		}else{
			contr = new Contract();
			ctPlotList = new ArrayList<Object[]>();
		}
	}
	
	public void doSaveContract(){
		staus = 0;
		try {
			contr = contractService.saveContract(contr);
			
			staus = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void setContr(Contract contr) {
		this.contr = contr;
	}

	public Contract getContr() {
		return contr;
	}

	public void setCtPlotList(List<Object[]> ctPlotList) {
		this.ctPlotList = ctPlotList;
	}

	public List<Object[]> getCtPlotList() {
		return ctPlotList;
	}

	public void setStaus(Integer staus) {
		this.staus = staus;
	}

	public Integer getStaus() {
		return staus;
	}

	
}

