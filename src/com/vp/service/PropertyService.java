package com.vp.service;

import java.util.List;

import com.vp.dao.CoordinateDao;
import com.vp.dao.OwnerDao;
import com.vp.dao.PlotRentDao;
import com.vp.dao.PosessionDao;
import com.vp.dao.PropertyDao;
import com.vp.entity.Coordinate;
import com.vp.entity.Owner;
import com.vp.entity.PlotRent;
import com.vp.entity.Posession;
import com.vp.entity.Property;

public class PropertyService {
	
	private PropertyDao propertyDao;
	private OwnerDao ownerDao ;
	private PosessionDao posessionDao;
	private PlotRentDao plotRentDao;
	private CoordinateDao coordinateDao;

	public Owner getOwnerById(String ownerId) {
		return ownerDao.getOwnerById(ownerId);
	}
	
	public List<Object[]> getOwnerNameAll() {
		return ownerDao.getOwnerNameAll();
	}
	
	public Owner getOwnerByName(String ownerName) {
		return ownerDao.getOwnerByName(ownerName);
	}
	
	public Property save(Property property){
		return propertyDao.merge(property);
	}
	
	public Coordinate save(Coordinate coordinate){
		return coordinateDao.merge(coordinate);
	}
	
	public Posession getPosessionById(String id){
		return posessionDao.getPosessionById(id);
	}
	
	public void remove(Property property){
		propertyDao.remove(property);
	}
	
	public void remove(Posession posession){
		posessionDao.remove(posession);
	}
	
	public List<Object[]> getPropertyAll() {
		return propertyDao.getPropertyAll();
	}
	
	public List<Object[]> getPropertyByCond(String cond) {
		return propertyDao.getPropertyByCond(cond);
	}
	
	public Property getPropertyBypptId(String id) {
		return propertyDao.getPropertyBypptId(id);
	}
	
	public List<PlotRent> getPlotRentBypptId(String id) {
		return plotRentDao.getPlotRentBypptId(id);
	}
	
	public PlotRent getPlotRentById(String id) {
		return plotRentDao.getPlotRentById(id);
	}
	
	public PropertyDao getPropertyDao() {
		return propertyDao;
	}

	public void setPropertyDao(PropertyDao propertyDao) {
		this.propertyDao = propertyDao;
	}
	
	public OwnerDao getOwnerDao() {
		return ownerDao;
	}

	public void setOwnerDao(OwnerDao ownerDao) {
		this.ownerDao = ownerDao;
	}

	public Posession savePosession(Posession posession) {
		return posessionDao.merge(posession);
	}

	public PosessionDao getPosessionDao() {
		return posessionDao;
	}

	public void setPosessionDao(PosessionDao posessionDao) {
		this.posessionDao = posessionDao;
	}

	public void removePosession(Posession posession) {
		posessionDao.remove(posession);
		
	}

	public PlotRentDao getPlotRentDao() {
		return plotRentDao;
	}

	public void setPlotRentDao(PlotRentDao plotRentDao) {
		this.plotRentDao = plotRentDao;
	}

	public CoordinateDao getCoordinateDao() {
		return coordinateDao;
	}

	public void setCoordinateDao(CoordinateDao coordinateDao) {
		this.coordinateDao = coordinateDao;
	}
	

}
