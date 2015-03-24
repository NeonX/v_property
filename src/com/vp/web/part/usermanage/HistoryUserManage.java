package com.vp.web.part.usermanage;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

import com.vp.service.HistoryUserService;
import com.vp.web.AbstractBackingBean;

@Synchronized(timeout = 90000)
@Name("historyUserManage")
@Scope(ScopeType.PAGE)
public class HistoryUserManage extends AbstractBackingBean<HistoryUserManage> {

	private HistoryUserService serService = (HistoryUserService) getContextBackingBean().getBean("historyUserService");
	


    
	
	 public HistoryUserManage() {
	        super(HistoryUserManage.class);
	    }

	    @Create
	    public void init() {
	    	
	    	//History List display
	    }
	    
	   
	
}

