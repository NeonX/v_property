package com.vp.dao;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pte.module.entitybean.AbstractEntityBean;
import com.vp.utils.AppUtils;
import com.vp.utils.CommonType;



@MappedSuperclass
public  class AbstractGenericDao<T,PK extends Serializable> {
	
	private  Class<T> persistentClass;
	
	protected final Log log;
	
	public AbstractGenericDao(final Class<T> persistentClass){
		this.persistentClass = persistentClass;
		log = LogFactory.getLog(persistentClass);
	}
	
	@SuppressWarnings("unchecked")
	public AbstractGenericDao(Class logClass,final Class<T> persistentClass){
		this.persistentClass = persistentClass;
		log = LogFactory.getLog(logClass);
	}
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void persist(T obj){
		log.debug("persisting "+persistentClass.getName()+" instance");
		try {
			entityManager.persist(obj);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	public void remove(T obj){
		log.debug("removing "+persistentClass.getName()+" instance");
		try {
			entityManager.remove(obj);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}
	
	public T merge(T obj){
		log.debug("merging "+persistentClass.getName()+" instance");
		try {
			T result = entityManager.merge(obj);
			log.debug("merge successful");
			log.info("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public List<T> merge(List<T> objList){
		log.debug("merging "+persistentClass.getName()+" instance");
		List<T> resultList = new LinkedList<T>();
		try{
			for(T obj : objList){
				resultList.add(entityManager.merge(obj));
			}
			log.debug("merge list successful");
			log.info("merge list successful");
			
			return resultList;
		}catch(RuntimeException re){
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public T findById(PK objId){
		log.debug("getting "+persistentClass+" instance with id: " + objId);
		try {
			T instance = entityManager.find(persistentClass, objId);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll(){
		String query = "from "+persistentClass.getName()+"  model";		
		if(persistentClass.getSuperclass()==AbstractEntityBean.class){
			query += "  order by model.update_date desc";
		}
		List objs = entityManager.createQuery(query).getResultList();
		return objs;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll(String fieldOrder,CommonType pteType){
		String sql = "from "+persistentClass.getName()+" model order by model."+fieldOrder;
		if(pteType==CommonType.ASC)
			sql += " asc  ";
		else if(pteType==CommonType.DESC)
			sql += " desc ";
		return entityManager.createQuery(sql).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getByLikeField(String fieldSearch,String value){
		String sql = "from "+persistentClass.getName()+" p where p."+fieldSearch+" like '%"+value+"%'";
		return entityManager.createQuery(sql).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getByLikeFieldIsNum(String fieldSearch,String value,String fieldOrder,CommonType pteType){
		String sql = "from "+persistentClass.getName()+" p where p."+fieldSearch+" = "+value;
		if(fieldOrder!=null && "".equals(fieldOrder)){
			sql += " p."+fieldOrder+" "+pteType;
		}
		return entityManager.createQuery(sql).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getDataByNotInSomeData(List<T> datasNotIn){
		String sql = "from  "+persistentClass.getName()+" model where model not in (:models)";
		return entityManager.createQuery(sql).setParameter("models", datasNotIn).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getByLimitAndOffset(String sql,Integer limit,Integer offsets,String orderField, CommonType pteType){
		if(!("".equals(orderField))){
			sql += " order by model."+orderField+" ";
			if(pteType.equals(CommonType.DESC)){
				sql += " desc ";
			}else{
				sql += " asc ";
			}
		}
		Query query = entityManager.createQuery(sql).setMaxResults(limit).setFirstResult(offsets);
			
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getSingleFieldListWithCriteria(String fieldSelect, String[] fieldCriteria, Object[] filterValue, String fieldOrder, CommonType orderType){
		
		try{
			
			String hql = "SELECT "+fieldSelect+" ";
			hql += "FROM "+persistentClass.getName()+" ";
			
			if(fieldCriteria != null && fieldCriteria.length > 0){
				for(int idx=0; idx<fieldCriteria.length; idx++){
					if(idx == 0){
						hql += "WHERE "+fieldCriteria[idx]+" = :val"+idx+" ";
					}else{
						hql += "AND "+fieldCriteria[idx]+" = :val"+idx+" ";
					}
				}
			}
			
			if(!AppUtils.isNullOrEmpty(orderType)){
				if(!AppUtils.isNullOrEmpty(fieldOrder)){
					hql += "ORDER BY "+fieldOrder+" "+orderType;
				}else{
					hql += "ORDER BY "+fieldSelect+" "+orderType;
				}
			}
			
			Query q = getEntityManager().createQuery(hql);
			if(filterValue != null && filterValue.length > 0){
				for(int idx=0; idx<filterValue.length; idx++){
					q.setParameter("val"+idx, filterValue[idx]);
				}
			}
			
			return q.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public Long getSizeAll(String fieldCount){
		String sql = "select COUNT(model."+fieldCount+") as counter from "+persistentClass.getName()+" model";
		return(Long) entityManager.createQuery(sql).getSingleResult();
	}
	
	public Long getCountByWithCondition(String fieldCount, String condition){
		String sql = "select COUNT(model."+fieldCount+") as counter from "+persistentClass.getName()+" model where 1=1 ";
		sql += condition;
		return(Long) entityManager.createQuery(sql).getSingleResult();
	}
	
	protected EntityManager getEntityManager(){
		return entityManager;
	}

}
