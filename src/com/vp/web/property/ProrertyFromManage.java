package com.vp.web.property;

import java.util.List;

import javaxt.utils.Array;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
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
	//private OwnerService ownerService = (OwnerService) getContextBackingBean().getBean("ownerService");
	/////private OwnerService ownerService = (OwnerService) getContextBackingBean().getBean("ownerService");
	private String pptId = "101";
	
	private List<CostEstimate> estimatList;
	private List<CostForSale> costForSaleList;
	
	private CostEstimate costEstimate = new CostEstimate();
	private CostForSale costForSale = new CostForSale();
	
	private Property property = new Property();
	private Posession posession = new Posession();
	private Owner owner = new Owner();
	
	private Float r = null;
	private Float ng = null;
	private Float trv = null;
	
	public ProrertyFromManage() {
		super(ProrertyFromManage.class);
	}
	
	@Create
	public void init() {
		if(pptId != null){
			property = propertyService.getPropertyBypptId(pptId);
			//owner = ownerService.getOwnerVypptId(pptId);
			//posession = posessionService.getPosessionBypptId(pptId);
			float area = property.getAreaSize();
			System.out.println(area);
			r = (area-(area%1600))/1600;
			area = area-(r*1600);
			ng = (area-(area%400))/400;
			area = area-(ng*400);
			trv = area/4;
			
			estimatList = costEstimateService.getCostEstimateListById(pptId);
			costForSaleList = costForSaleService.getCostForSaleListById(pptId);
		}
		
		//System.out.println(dataList);
	}
	
	public void editeEstimation(String estmId){
		costEstimate = costEstimateService.getCostEstimateById(estmId);
		System.out.println(costEstimate.getCostEstimate());
		
	}
	
	public void deleteEstimation(CostEstimate costEstimate){
		costEstimateService.removeCostEstimate(costEstimate);
	}
	
	public void editCostForSale(String saleId){
		costForSale = costForSaleService.getCostForSaleById(saleId);
	}
	
	public void deleteCostForSale(CostForSale costForSale){
		costForSaleService.removeContent(costForSale);
	}

	public String getPptId() {
		return pptId;
	}

	public void setPptId(String pptId) {
		this.pptId = pptId;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Float getR() {
		return r;
	}

	public void setR(Float r) {
		this.r = r;
	}

	public Float getNg() {
		return ng;
	}

	public void setNg(Float ng) {
		this.ng = ng;
	}

	public Float getTrv() {
		return trv;
	}

	public void setTrv(Float trv) {
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
	
}
