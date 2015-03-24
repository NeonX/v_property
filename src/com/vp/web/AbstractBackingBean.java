package com.vp.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jboss.seam.annotations.In;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.faces.Redirect;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vp.entity.HistoryUser;
import com.vp.service.HistoryUserService;



@MappedSuperclass
public abstract class AbstractBackingBean<T> {
	

	protected static Logger log;
	

	
	@In(required=false)
	protected FacesMessages facesMessages;
	
	@In(required=false)
    protected EntityManager em;
	
	@In(required=false)
	protected Credentials  credentials;
	
	@In(required=false)
	protected Identity  identity;
	
	//protected ResourceBundleMessageSource messageSource;
	
	public AbstractBackingBean(Class<T> persistentClass){
		log = LogManager.getLogger(persistentClass);
	}

	protected static ApplicationContext getContextBackingBean(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		ServletContext sc = (ServletContext)ec.getContext();
		ApplicationContext ac = (ApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc);
		return ac;
	}
	
	protected static HttpServletResponse getResponse(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		
		return (HttpServletResponse)ec.getResponse();
	}
	
	protected static ServletContext getServletContext(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		return (ServletContext)ec.getContext();
	}
	
	protected void forceRedirectPage(String viewId){
		Redirect redirect = Redirect.instance();
		redirect.setViewId(viewId);
		redirect.execute();
	}
	
	protected  boolean saveHistoryUser(String detail,String number,String actionType,String referId,String modelRef){
		try {
			HistoryUserService historyUserService = (HistoryUserService) getContextBackingBean().getBean(HistoryUserService.HISTORY_SERVICENAME);
			HistoryUser historyUser = new HistoryUser();
			
			historyUser.setTestNumber(number);
			historyUser.setUpdate_by(credentials.getUsername());
			historyUser.setUpdate_date(new Date());
			SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			historyUser.setDetail_time(sp.format(historyUser.getUpdate_date()));
			historyUser.setActionType(actionType);
			historyUser.setActionId(referId);
			historyUser.setAction_modelref(modelRef);
			historyUser.setDetail(detail);
			
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String remoteAddr = request.getRemoteAddr();
			String remoteHostName = request.getRemoteHost();
			String localAddress = request.getHeader("x-forwarded-for");
			log.info("External Addr = "+remoteAddr);
			log.info("LocalAddress = "+localAddress);
			
			historyUser.setLocal_ip(localAddress);
			historyUser.setRemote_hostname(remoteHostName);
			historyUser.setPublic_ip(remoteAddr);
			historyUser.setLogin_name(credentials.getUsername());
			if(historyUserService.doSaveHistoryUser(historyUser) !=null){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
}
