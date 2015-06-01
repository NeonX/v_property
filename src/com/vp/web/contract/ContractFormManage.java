package com.vp.web.contract;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.joda.time.DateTime;
import org.joda.time.Period;

import com.vp.entity.Contract;
import com.vp.entity.ContractPlot;
import com.vp.service.ContractService;
import com.vp.utils.AppUtils;
import com.vp.web.AbstractAttachmentBackingBean;


@Name("contrFormMng")
@Scope(ScopeType.PAGE)
public class ContractFormManage extends AbstractAttachmentBackingBean<ContractFormManage> {
	
	private ContractService contractService = (ContractService) getContextBackingBean().getBean("contractService");
	private Locale thLocale = new Locale("th", "TH");
	private Contract contr;
	private List<Object[]> ctPlotList; 
	private Integer staus = 0;
	private String ctDateBegin = "";
	private String ctDateEnd = "";
	private String periodCt = "";
	
	private ContractPlot contPlot;

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
			
			ctDateBegin = AppUtils.dateToStringEng(contr.getContBegin());
			ctDateEnd = AppUtils.dateToStringEng(contr.getContEnd());
			setPeriodCt(contr.getContBegin(), contr.getContEnd());
			
		}else{
			contr = new Contract();
			ctPlotList = new ArrayList<Object[]>();
			super.initAttachment("CONTRACT", null, true, true);
			
			
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			cal.add(Calendar.YEAR, 1); // to get previous year add -1
			Date nextYear = cal.getTime();
			ctDateBegin = AppUtils.dateToString(today, "dd/MM/yyyy");
			ctDateEnd = AppUtils.dateToString(nextYear, "dd/MM/yyyy");
			
			setPeriodCt(today, nextYear);
		}
	}
	
	public void doDateDiff(){
		Date from = contr.getContBegin();
		Date to = contr.getContEnd();
		setPeriodCt(from, to);
	}
	
	public void setPeriodCt(Date bg_date, Date end_date){
		Calendar cal = Calendar.getInstance();
        cal.setTime(end_date);
        cal.add(Calendar.DATE, 1);
        Date newEnd = cal.getTime();
        
		DateTime dateFrom = new DateTime(bg_date);  
        DateTime dateTo = new DateTime(newEnd);
        
        Period period = new Period(dateFrom, dateTo);
		int y = period.getYears();
		int m = period.getMonths();
		int d = (period.getWeeks()*7)+period.getDays();
		
		periodCt = y+" ปี "+m+" เดือน "+d+" วัน";
	}
	
	public void doMngContractPlot(Integer ctId){
		if(ctId != null && ctId != 0){
			contPlot = contractService.getContractPlotByID(ctId);
		}else{
			contPlot = new ContractPlot();
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

	public void setPeriodCt(String periodCt) {
		this.periodCt = periodCt;
	}

	public String getPeriodCt() {
		return periodCt;
	}

	public void setThLocale(Locale thLocale) {
		this.thLocale = thLocale;
	}

	public Locale getThLocale() {
		return thLocale;
	}

	public void setContPlot(ContractPlot contPlot) {
		this.contPlot = contPlot;
	}

	public ContractPlot getContPlot() {
		return contPlot;
	}

	
}

