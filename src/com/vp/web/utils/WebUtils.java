package com.vp.web.utils;

import java.net.URLEncoder;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.vp.utils.AppUtils;

@Name("webUtils")
@Scope(ScopeType.APPLICATION)
public class WebUtils extends AppUtils{

	
	public static String getHost(){
		HttpServletRequest  request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();		
		String hostStr = request.getHeader("X-Forwarded-Host");
		if(hostStr==null){
			return  "http://"+request.getServerName();
		}
		return "http://"+hostStr;
	}
	
	public static int getPort(){
		
		HttpServletRequest  request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		return request.getServerPort();
	}
	
	public static String getContextApp(){
		HttpServletRequest  request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();	
    	return request.getContextPath();
	}
	
	public static String getHostContextUrl(){
		
		String context = getHost();
		int port = getPort();
		if(port > 0){
			context += ":"+port;
		}
		context += getContextApp();
		
		return context;
	}
	
	
	public static String urlEncode(String str){
		try {
			str = URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	
}
