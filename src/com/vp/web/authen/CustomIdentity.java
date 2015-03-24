package com.vp.web.authen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.log.LogProvider;
import org.jboss.seam.log.Logging;
import org.jboss.seam.security.Identity;

@Name("org.jboss.seam.security.identity")
@Scope(ScopeType.SESSION)
@Install(precedence=Install.APPLICATION)
@BypassInterceptors
@Startup
public class CustomIdentity extends Identity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3940631480735803488L;
	
	private static final LogProvider log = Logging.getLogProvider(CustomIdentity.class);
	private Map<String,String[]> permissions = new HashMap<String,String[]>();
	
	@Override
	public String login() {
		// TODO Auto-generated method stub
		return super.login();
	}

	@Override
	public boolean hasPermission(String name, String action, Object... arg) {
		// TODO Auto-generated method stub
	
		boolean result = false;
	
		if(permissions.containsKey(name)){
			String[] oper = permissions.get(name);
			
			if(action == null || action.trim().length() == 0){
				return true;//check only permission, ignore operation
			}else{
				if(oper != null){
					result = Arrays.asList(oper).contains(action);
				}
			}
		}
	
		return result;
	}
	
	public boolean addPermission(String name, String[] actions){
		
		if(name != null && name.trim().length() > 0){
			
			if(permissions == null){
				permissions = new HashMap<String, String[]>();
			}
			
			try{
				if(actions != null && actions.length > 0){
					permissions.put(name, actions);
					return true;
				}
			}catch(Exception e){
				log.error(e.toString());
			}
		}
		
		return false;
	}
	
	public boolean getLoggedInStatus(){
		return super.isLoggedIn();
	}
}
