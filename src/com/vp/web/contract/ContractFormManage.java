package com.vp.web.contract;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.joda.time.DateTime;
import org.joda.time.Period;

import com.vp.entity.Contract;
import com.vp.service.ContractService;
import com.vp.utils.AppUtils;
import com.vp.web.AbstractAttachmentBackingBean;


@Name("contrFormMng")
@Scope(ScopeType.PAGE)
public class ContractFormManage extends AbstractAttachmentBackingBean<ContractFormManage> {
	
	private ContractService contractService = (ContractService) getContextBackingBean().getBean("contractService");
	private Contract contr;
	private List<Object[]> ctPlotList; 
	private Integer staus = 0;
	private String ctDateBegin = "";
	private String ctDateEnd = "";
	private Integer periodYear = 0;

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
			super.initAttachment("CONTRACT", contractId, true, true);
			
			ctDateBegin = AppUtils.dateToString(contr.getContBegin(), "d/M/yyyy");
			ctDateEnd = AppUtils.dateToString(contr.getContEnd(), "d/M/yyyy");
			DateTime dateFrom = new DateTime(contr.getContBegin());  
	        DateTime dateTo = new DateTime(contr.getContEnd());
			Period period = new Period(dateFrom, dateTo);
			periodYear = period.getYears();
			
		}else{
			contr = new Contract();
			ctPlotList = new ArrayList<Object[]>();
			super.initAttachment("CONTRACT", null, true, true);
			
			
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			cal.add(Calendar.YEAR, 1); // to get previous year add -1
			Date nextYear = cal.getTime();
			ctDateBegin = AppUtils.dateToString(today, "d/M/yyyy");
			ctDateEnd = AppUtils.dateToString(nextYear, "d/M/yyyy");
			periodYear = 1;
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

	public void setCtDateBegin(String ctDateBegin) {
		this.ctDateBegin = ctDateBegin;
	}

	public String getCtDateBegin() {
		return ctDateBegin;
	}

	public void setCtDateEnd(String ctDateEnd) {
		this.ctDateEnd = ctDateEnd;
	}

	public String getCtDateEnd() {
		return ctDateEnd;
	}

	public void setPeriodYear(Integer periodYear) {
		this.periodYear = periodYear;
	}

	public Integer getPeriodYear() {
		return periodYear;
	}

	
}

