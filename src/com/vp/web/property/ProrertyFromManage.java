package com.vp.web.property;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.vp.entity.CostEstimate;
import com.vp.entity.CostForSale;
import com.vp.entity.Owner;
import com.vp.entity.Posession;
import com.vp.entity.Property;
import com.vp.service.CostEstimateService;
import com.vp.service.CostForSaleService;
import com.vp.service.PropertyService;
import com.vp.web.AbstractBackingBean;

@Name("prorertyFromMng")
@Scope(ScopeType.PAGE)
public class ProrertyFromManage extends AbstractBackingBean<ProrertyFromManage> {
	
	private PropertyService propertyService = (PropertyService) getContextBackingBean().getBean("propertyService");
	private CostEstimateService costEstimateService = (CostEstimateService) getContextBackingBean().getBean("costEstimateService");
	private CostForSaleService costForSaleService = (CostForSaleService) getContextBackingBean().getBean("costForSaleService");

	@In(scope = ScopeType.SESSION, required=false)
    String pptId;
	@In(scope = ScopeType.SESSION, required=false)
    String ownerId;
	@In(scope = ScopeType.SESSION, required=false)
    String posId;
	
	private List<CostEstimate> estimatList;
	private List<CostForSale> costForSaleList;
	private List<String> ownerNameList = new ArrayList<String>();
	private List<Object[]> namelist =  new ArrayList<Object[]>();
	private Owner ownerList;
	
	private CostEstimate costEstimate = new CostEstimate();
	private CostForSale costForSale = new CostForSale();
	
	private String ownerSelectName;
	
	private Property property = new Property();
	private Owner owner = new Owner();
	
	private String ownerName;
	private String ownerAdd;
	private String r = null;
	private String ng = null;
	private String trv = null;
	
	private Date date;
	private Timestamp selectDate;
	private Calendar selectDates;
	
	private Boolean isEdit = false;
	
	public ProrertyFromManage() {
		super(ProrertyFromManage.class);
	}
	
	@Create
	public void init() {
		namelist =  propertyService.getOwnerNameAll();
		
		//System.out.println(namelist.get(0).getOwnerName());
		System.out.println(namelist.size());
		if(pptId != null && ownerId != null && !pptId.equals("0") && !ownerId.equals("0")){
			isEdit = true;
			property = propertyService.getPropertyBypptId(pptId);
			owner = propertyService.getOwnerById(ownerId);
			float area = property.getAreaSize();
			
			r = ((area-(area%1600))/1600)+"";
			area = area-(((area-(area%1600))/1600)*1600);
			ng = ((area-(area%400))/400)+"";
			area = area-(((area-(area%400))/400)*400);
			trv = (area/4)+"";
			
			estimatList = costEstimateService.getCostEstimateListById(pptId);
			costForSaleList = costForSaleService.getCostForSaleListById(pptId);
		}else{
			isEdit = false;
		}
		
	}
	
	public void getOwnerAddess(){
		owner =  propertyService.getOwnerByName(ownerName);
		
	}
	
	public void saveProrerty(){
		
		float area = (Float.parseFloat(r)*1600)+(Float.parseFloat(ng)*400)+(Float.parseFloat(trv)*4);
		
		property.setAreaSize(area);
		property = propertyService.save(property);
		if(property != null && owner != null){
			Posession posession = new Posession();
			if(posId!=null){
				posession.setPosId(Integer.parseInt(posId));
			}
			posession.setOwner(owner);
			posession.setProperty(property);
			posession.setPosessionDate(new Date());
			posession = propertyService.savePosession(posession);
		}
		
		System.out.println("saved.");
	}
	
	public void saveCostEstimate(){
		property = propertyService.getPropertyBypptId(pptId);
		if(property != null){
			costEstimate.setProperty(property);
			costEstimate = costEstimateService.saveCostEstimate(costEstimate);
		}
		
		System.out.println("saved.");
	}
	
	public void saveCostForSale(){
		property = propertyService.getPropertyBypptId(pptId);
		if(property != null){
			costForSale.setProperty(property);
			costForSale = costForSaleService.saveCostForSale(costForSale);
		}
		
		System.out.println("saved.");
	}
	
	public void cancel(){
		property = new Property();
	}
	
	public void newCost(){
		costEstimate = new CostEstimate();
		costForSale = new CostForSale();
	}
	
	public void editeEstimation(String estmId){
		
		costEstimate = costEstimateService.getCostEstimateById(estmId);
		System.out.println(costEstimate.getCostEstimate());
		
	}
	
/*	public void deleteEstimation(CostEstimate costEstimate){
		this.costEstimate = costEstimate;
//		costEstimateService.removeCostEstimate(costEstimate);
	}*/
	
	public void editCostForSale(String saleId){
		costForSale = costForSaleService.getCostForSaleById(saleId);
	}
	
/*	public void deleteCostForSale(CostForSale costForSale){
		this.costForSale = costForSale;
		//costForSaleService.removeContent(costForSale);
	}*/
	
	public void removeCostEstimate(){
		if(costEstimate != null){
			costEstimateService.removeCostEstimate(costEstimate);
		}
		costEstimate = null;
		
	}
	
	public void removeForSale(){
		if(costForSale != null){
			costForSaleService.removeForSale(costForSale);
		}
		costForSale = null;
		
	}

	
	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getNg() {
		return ng;
	}

	public void setNg(String ng) {
		this.ng = ng;
	}

	public String getTrv() {
		return trv;
	}

	public void setTrv(String trv) {
		this.trv = trv;
	}

	public List<CostEstimate> getEstimatList() {
		return estimatList;
	}

	public void setEstimatList(List<CostEstimate> estimatList) {
		this.estimatList = estimatList;
	}

	public List<CostForSale> getCostForSaleList() {
		return costForSaleList;
	}

	public void setCostForSaleList(List<CostForSale> costForSaleList) {
		this.costForSaleList = costForSaleList;
	}

	public CostEstimate getCostEstimate() {
		return costEstimate;
	}

	public void setCostEstimate(CostEstimate costEstimate) {
		this.costEstimate = costEstimate;
	}

	public CostForSale getCostForSale() {
		return costForSale;
	}

	public void setCostForSale(CostForSale costForSale) {
		this.costForSale = costForSale;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public List<String> getOwnerNameList() {
		return ownerNameList;
	}

	public void setOwnerNameList(List<String> ownerNameList) {
		this.ownerNameList = ownerNameList;
	}

	public String getOwnerSelectName() {
		return ownerSelectName;
	}

	public void setOwnerSelectName(String ownerSelectName) {
		this.ownerSelectName = ownerSelectName;
	}


	public List<Object[]> getNamelist() {
		return namelist;
	}

	public void setNamelist(List<Object[]> namelist) {
		this.namelist = namelist;
	}

	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerAdd() {
		return ownerAdd;
	}

	public void setOwnerAdd(String ownerAdd) {
		this.ownerAdd = ownerAdd;
	}

	public Timestamp getSelectDate() {
		return selectDate;
	}

	public void setSelectDate(Timestamp selectDate) {
		this.selectDate = selectDate;
	}

	public Owner getOwnerList() {
		return ownerList;
	}

	public void setOwnerList(Owner ownerList) {
		this.ownerList = ownerList;
	}

	public Calendar getSelectDates() {
		return selectDates;
	}

	public void setSelectDates(Calendar selectDates) {
		this.selectDates = selectDates;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


}
