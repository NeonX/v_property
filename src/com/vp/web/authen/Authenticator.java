package com.vp.web.authen;

import java.net.MalformedURLException;
import java.util.List;

import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.security.Credentials;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.pte.ws.IUserAuthenWS;
import com.pte.ws.IUserAuthorizeWS;
import com.pte.ws.wrapper.AuthorizeInfo;
import com.vp.service.SysUserService;
import com.vp.web.AbstractBackingBean;

@Name("authenticator")
public class Authenticator extends AbstractBackingBean<Authenticator> {

    private ResourceBundleMessageSource msgSrc = (ResourceBundleMessageSource) getContextBackingBean().getBean("messageSourceUpload");
	private SysUserService sysUserService = (SysUserService)getContextBackingBean().getBean("sysUserService");
	
    @In
    @Out(scope = ScopeType.SESSION)
    Credentials credentials;
    
    @In
    CustomIdentity identity;
    
    @In(scope = ScopeType.SESSION,required=false)
    @Out(scope = ScopeType.SESSION,required=false)
    private String urName;


    public Authenticator() {
        super(Authenticator.class);
    }

    public boolean authenticate(){
		try{
			
			String username = credentials.getUsername();
			String password = credentials.getPassword();
			Boolean authenSuccess = false;
			
			Object[] authenResult = sysUserService.getAuthen(username, password);

			if(authenResult != null){
				
				authenSuccess = true;
				urName = authenResult[0]+" "+authenResult[1];
				Integer level = Integer.parseInt(authenResult[2].toString());
				
				if(level==0){
					identity.addRole("ADMIN");
					String[] act = {"view","add","edit","del"};
					identity.addPermission("action", act);
				}else{
					identity.addRole("GUESE");
					String[] act = {"view"};
					identity.addPermission("action", act);
				}
			
			}
			
			return authenSuccess;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public String getUrName() {
		return urName;
	}

	public void setUrName(String urName) {
		this.urName = urName;
	}
	
}
