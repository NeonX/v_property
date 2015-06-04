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
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import com.vp.entity.Coordinate;
import com.vp.entity.CostEstimate;
import com.vp.entity.CostForSale;
import com.vp.entity.Owner;
import com.vp.entity.PlotRent;
import com.vp.entity.Posession;
import com.vp.entity.Property;
import com.vp.service.CostEstimateService;
import com.vp.service.CostForSaleService;
import com.vp.service.PropertyService;
import com.vp.web.AbstractAttachmentBackingBean;

@Name("prorertyFromMng")
@Scope(ScopeType.PAGE)
public class ProrertyFromManage extends AbstractAttachmentBackingBean<ProrertyFromManage> {
	
	private PropertyService propertyService = (PropertyService) getContextBackingBean().getBean("propertyService");
	private CostEstimateService costEstimateService = (CostEstimateService) getContextBackingBean().getBean("costEstimateService");
	private CostForSaleService costForSaleService = (CostForSaleService) getContextBackingBean().getBean("costForSaleService");

	@In(scope = ScopeType.SESSION, required=false)
	@Out(scope = ScopeType.SESSION, required = false)
    String pptId;
	@In(scope = ScopeType.SESSION, required=false)
	@Out(scope = ScopeType.SESSION, required = false)
    String ownerId;
	@In(scope = ScopeType.SESSION, required=false)
	@Out(scope = ScopeType.SESSION, required = false)
    String posId;
	
	private List<CostEstimate> estimatList;
	private List<CostForSale> costForSaleList;
	private List<String> ownerNameList = new ArrayList<String>();
	private List<Object[]> namelist =  new ArrayList<Object[]>();
	private List<PlotRent> plotRentList = new ArrayList<PlotRent>();
	private List<Coordinate> plotRentCoordinateList = new ArrayList<Coordinate>();
	private List<Coordinate> propertyCoordinateList = new ArrayList<Coordinate>();
	
	private Owner ownerList;
	
	private CostEstimate costEstimate = new CostEstimate();
	private CostForSale costForSale = new CostForSale();
	
	private String ownerSelectName;
	
	private Property property = new Property();
	private Owner owner = new Owner();
	private Owner addOwner = new Owner();
	private PlotRent plotRent = new PlotRent();
	
	private String ownerName;
	private String ownerAdd;
	
	private String r = null;
	private String ng = null;
	private String trv = null;
	private Double plotR = null;
	private Double plotNg = null;
	private Double plotTrv = null;
	
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
		
		if(pptId != null && ownerId != null && !pptId.equals("0") && !ownerId.equals("0")){
			isEdit = true;
			property = propertyService.getPropertyBypptId(pptId);
			owner = propertyService.getOwnerById(ownerId);
			ownerName = owner.getOwnerName();
			float area = property.getAreaSize();
			
			r = ((area-(area%1600))/1600)+"";
			area = area-(((area-(area%1600))/1600)*1600);
			ng = ((area-(area%400))/400)+"";
			area = area-(((area-(area%400))/400)*400);
			trv = (area/4)+"";
			
			estimatList = costEstimateService.getCostEstimateListById(pptId);
			costForSaleList = costForSaleService.getCostForSaleListById(pptId);
			
			prepreaPlotRent();
			//prepreaPlotRentCoordinateList();
			
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
				Posession pose = propertyService.getPosessionById(posId);
				Integer oldId = pose.getOwner().getOwnerId();
				Integer newId = owner.getOwnerId();
				if(oldId.equals(newId)){
					posession.setPosId(Integer.parseInt(posId));
				}
				
			}
			posession.setOwner(owner);
			posession.setProperty(property);
			posession.setPosessionDate(new Date());
			posession = propertyService.savePosession(posession);
			posId = posession.getPosId().toString();
			ownerId = owner.getOwnerId().toString();
			
		}
		
		//System.out.println("saved.");
	}
	
	public void saveCostEstimate(){
		//property = propertyService.getPropertyBypptId(pptId);
		if(property != null){
			costEstimate.setProperty(property);
			costEstimate = costEstimateService.saveCostEstimate(costEstimate);
			estimatList = costEstimateService.getCostEstimateListById(pptId);
		}
		
		System.out.println("saved.");
	}
	
	public void saveCostForSale(){
		//property = propertyService.getPropertyBypptId(pptId);
		if(property != null){
			costForSale.setProperty(property);
			costForSale = costForSaleService.saveCostForSale(costForSale);
			costForSaleList = costForSaleService.getCostForSaleListById(pptId);
		}
		
		System.out.println("saved.");
	}
	
	public void cancel(){
		prepareData();
		forceRedirectPage("/property/index.xhtml");
		
	}
	
	public void newCost(){
		costEstimate = new CostEstimate();
		costForSale = new CostForSale();
	}
	
	public void editeEstimation(String estmId){
		
		costEstimate = costEstimateService.getCostEstimateById(estmId);
		System.out.println(costEstimate.getCostEstimate());
		
	}
	
	public void editCostForSale(String saleId){
		costForSale = costForSaleService.getCostForSaleById(saleId);
	}
	
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
	public void prepareData(){
		property = new Property();
		pptId = null;
		ownerId = null;
		posId = null;
		costForSale = null;
		costEstimate = null;
		
	}
	
	public void prepreaPlotRent() {
		if(pptId != null && !pptId.equals("0")){
			prepreaRentList();
			
		}else{
			plotRentList.clear();
			plotR = 0.0;
			plotNg = 0.0;
			plotTrv = 0.0;
		}
		
		
	}
	
	public List<Double> sqmToRNgTrv(Double size) {
		
		List<Double> data = new ArrayList<Double>();
		
		Double r = ((size-(size%1600))/1600);
		size = size-(((size-(size%1600))/1600)*1600);
		Double ng = ((size-(size%400))/400);
		size = size-(((size-(size%400))/400)*400);
		Double trv = (size/4);
		
		data.add(r);
		data.add(ng);
		data.add(trv);
		
		return data;
		
	}
	
	public void prepreaRNgTrv() {
			plotR = 0.0;
			plotNg = 0.0;
			plotTrv = 0.0;
		
	}
	
	public void newPlotRent(){
		prepreaRNgTrv();
		plotRentCoordinateList.clear();
		plotRent = new PlotRent();
		
	}
	
	public void editPlotRent(String plotId){
		plotRent = propertyService.getPlotRentById(plotId);
		//plotRent.getPrId();
		prepreaPlotRent();
		prepreaPlotRentCoordinateList();
	}
	
	public void addPlotRentArea(){
		Coordinate coordinate = new Coordinate();
		if(plotRentCoordinateList.size()>0){
			coordinate = plotRentCoordinateList.get(plotRentCoordinateList.size()-1);
			if(!coordinate.getE().equals("") || !coordinate.getN().equals("")){
				
				plotRentCoordinateList.get(plotRentCoordinateList.size()-1).setIsEdit(1);
				plotRentCoordinateList.add(new Coordinate());
			}
		}else{
			plotRentCoordinateList.add(new Coordinate());
		}
		
	}
	
	public void addPropertyArea(){
		Coordinate coordinate = new Coordinate();
		
		if(propertyCoordinateList.size()>0){
			coordinate = propertyCoordinateList.get(propertyCoordinateList.size()-1);
			if(!coordinate.getE().equals("") || !coordinate.getN().equals("")){
				
				propertyCoordinateList.get(propertyCoordinateList.size()-1).setIsEdit(1);
				propertyCoordinateList.add(new Coordinate());
			}
		}else{
			propertyCoordinateList.add(new Coordinate());
		}
		
	}
	
	public void prepreaPlotRentCoordinateList(){
		if(plotRent.getPrId() != null){
			String conn =" and coType = 'PLOTRENT' and targetId = "+plotRent.getPrId();
			plotRentCoordinateList = propertyService.getCoordinateDao().getCoordinateByCondition(conn);
			if(plotRentCoordinateList!=null){
				for(int index = 0;index<plotRentCoordinateList.size();index++){
					plotRentCoordinateList.get(index).setIsEdit(1);
				}
			}
			
		}
		
	}
	
	public void prepreaPropertyCoordinateList(){
		if(property.getPptId() != null){
			String conn =" and coType = 'PROPERTY' and targetId = "+property.getPptId();
			propertyCoordinateList = propertyService.getCoordinateDao().getCoordinateByCondition(conn);
			if(propertyCoordinateList!=null){
				for(int index = 0;index<propertyCoordinateList.size();index++){
					propertyCoordinateList.get(index).setIsEdit(1);
				}
			}
		}
		
	}
	
	public void resetPlotRent(){
		plotRentCoordinateList.clear();
		prepreaPlotRent() ;
	}
	
	public void savePlotRent(){
		float area = Float.parseFloat((plotR*1600+plotNg*400+plotTrv*4)+"");
		if(plotRent.getPrId() == null){
			plotRent.setCreateDate(new Date());
			plotRent.setCreateBy(credentials.getUsername());
		}
		plotRent.setPlotSize(area);
		plotRent.setProperty(property);
		plotRent.setUpdateDate(new Date());
		plotRent.setUpdateBy(credentials.getUsername());
		plotRent = propertyService.getPlotRentDao().merge(plotRent);
		
		if(plotRent.getPrId() != null){
			savePlotRentCoordinate();
		}
		
		prepreaRentList();
		
	}
	
	public void savePlotRentCoordinate(){
		
			for(Coordinate coor:plotRentCoordinateList){
				coor.setTargetId(plotRent.getPrId());
				coor.setCoType("PLOTRENT");
				coor.setUpdateBy(credentials.getUsername());
				coor.setUpdateDate(new Date());
				propertyService.save(coor);
			}
		
	}
	
	public void savePropertyCoordinate(){
		
		for(Coordinate coor:propertyCoordinateList){
			coor.setTargetId(property.getPptId());
			coor.setCoType("PROPERTY");
			coor.setUpdateBy(credentials.getUsername());
			coor.setUpdateDate(new Date());
			propertyService.save(coor);
		}
	
	}
	
	public void prepreaRentList(){
		
		plotRentList = propertyService.getPlotRentBypptId(pptId);
		
		Double sumPlotSize = 0.0;
		if(plotRentList != null){
			for(PlotRent plot:plotRentList){
				sumPlotSize += plot.getPlotSize();
			}
		}
		List<Double> plotRentArea = sqmToRNgTrv(sumPlotSize);
		if(plotRentArea != null){
			plotR = plotRentArea.get(0);
			plotNg = plotRentArea.get(1);
			plotTrv = plotRentArea.get(2);
		}
	}
	
	public void addOwner(){
		owner = propertyService.getOwnerDao().merge(addOwner);
		if(owner!=null){
			ownerName = owner.getOwnerName();
			namelist =  propertyService.getOwnerNameAll();
		}
	}
	
	public void prepreaAddOwner(){
		addOwner = new Owner();
	}
	
	public void allowEditPropertyCondinate(String index){
		propertyCoordinateList.get(Integer.parseInt(index)).setIsEdit(2);
	}
	
	public void desibleEditPropertyCondinate(String index){
		propertyCoordinateList.get(Integer.parseInt(index)).setIsEdit(1);
	}
	
	public void deletePropertyCondinate(String index) {
		propertyCoordinateList.remove(Integer.parseInt(index));
	}
	
	public void allowEditPlotRentCondinate(String index){
		plotRentCoordinateList.get(Integer.parseInt(index)).setIsEdit(2);
	}
	
	public void desibleEditPlotRentCondinate(String index){
		plotRentCoordinateList.get(Integer.parseInt(index)).setIsEdit(1);
	}
	
	public void deletePlotRentCondinate(String index) {
		plotRentCoordinateList.remove(Integer.parseInt(index));
	}
	
	public String getRNgTrv(Double size){
		List<Double> area = sqmToRNgTrv(size);
		
		return ((int)area.get(0).doubleValue())+" - "+((int)area.get(1).doubleValue())+" - "+((int)area.get(2).doubleValue());
	}
	
	////////////////////////////////////////////
	// SETER AND GETER
	
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

	public List<PlotRent> getPlotRentList() {
		return plotRentList;
	}

	public void setPlotRentList(List<PlotRent> plotRentList) {
		this.plotRentList = plotRentList;
	}

	public Double getPlotR() {
		return plotR;
	}

	public void setPlotR(Double plotR) {
		this.plotR = plotR;
	}

	public Double getPlotNg() {
		return plotNg;
	}

	public void setPlotNg(Double plotNg) {
		this.plotNg = plotNg;
	}

	public Double getPlotTrv() {
		return plotTrv;
	}

	public void setPlotTrv(Double plotTrv) {
		this.plotTrv = plotTrv;
	}

	public List<Coordinate> getPlotRentCoordinateList() {
		return plotRentCoordinateList;
	}

	public void setPlotRentCoordinateList(List<Coordinate> plotRentCoordinateList) {
		this.plotRentCoordinateList = plotRentCoordinateList;
	}
	
	public List<Coordinate> getPropertyCoordinateList() {
		return propertyCoordinateList;
	}

	public void setPropertyCoordinateList(List<Coordinate> propertyCoordinateList) {
		this.propertyCoordinateList = propertyCoordinateList;
	}

	public String getPptId() {
		return pptId;
	}

	public void setPptId(String pptId) {
		this.pptId = pptId;
	}

	public Owner getAddOwner() {
		return addOwner;
	}

	public void setAddOwner(Owner addOwner) {
		this.addOwner = addOwner;
	}

	public PlotRent getPlotRent() {
		return plotRent;
	}

	public void setPlotRent(PlotRent plotRent) {
		this.plotRent = plotRent;
	}

}
