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
    String respondArea;
    
    @In(scope = ScopeType.SESSION,required=false)
    @Out(scope = ScopeType.SESSION,required=false)
    String areaType;
    
    @In(scope = ScopeType.SESSION,required=false)
    @Out(scope = ScopeType.SESSION,required=false)
    private String urName;
    
    @In(scope = ScopeType.SESSION,required=false)
    @Out(scope = ScopeType.SESSION,required=false)
    private String urPosition;

    public Authenticator() {
        super(Authenticator.class);
    }

    public boolean authenticate(){
		try{
			
			String username = credentials.getUsername();
			String password = credentials.getPassword();
			Boolean authenSuccess = false;
			
//			log.info("---> username access :["+username+"]");
			
			Object[] authenResult = {true,123};//userAuthentication(username, password);
			if(authenResult != null){
				
				authenSuccess = (Boolean) authenResult[0];
				String keyVerify = authenResult[1].toString();
				if(authenSuccess){
					
					identity.addRole("ADMIN");
					String[] act = {"view","add","edit","del"};
					identity.addPermission("action", act);
					
					/*List<String> userRole = getUserRoles(username, keyVerify);
			
					for(String role : userRole){
						identity.addRole(role);
					}
					
					List<AuthorizeInfo> authorizeResult = getUserPermission(username, keyVerify);
					for(AuthorizeInfo info : authorizeResult){
						identity.addPermission(info.getPermission(), info.getActions());
					}*/
					
/*List<Object[]> list = sysUserService.getRespondArea(username);
respondArea = "";
if(list!=null && list.size()> 0){
	respondArea  += list.get(0);
}

list = sysUserService.getUserInfo(username);
if(list!=null && list.size()> 0){
	urName  = (list.get(0)[0] != null)?list.get(0)[0].toString():"";
	urPosition = (list.get(0)[1] != null)?list.get(0)[1].toString():"";
}*/
				}

			}
			
			return authenSuccess;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	private Object[] userAuthentication(String username, String password) {
		
		// Create a metadata of the service
		String authenWSUrl = msgSrc.getMessage("ws.url.authentication", null, null);
		Service serviceModel = new ObjectServiceFactory().create(IUserAuthenWS.class);
		
		XFire xfire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory = new XFireProxyFactory(xfire);
		
		IUserAuthenWS client = null;
		try {
			client = (IUserAuthenWS) factory.create(serviceModel, authenWSUrl);
		} catch (MalformedURLException e) {
			log.error("WsClient.UserAuthen: EXCEPTION: " + e.toString());
		}
		
		// Invoke the service
		Object[] serviceResponse = null;
		try {
			serviceResponse = client.authentication(username, password);
		} catch (Exception e) {
			log.error("WsClient.authentication(): EXCEPTION: " + e.toString());
		}
		
		log.debug("----------> WS UserAuthenResponse : " + serviceResponse);

		// Return the response
		return serviceResponse;
	}

	private List<String> getUserRoles(String username, String keyVerify) {
		IUserAuthorizeWS client = getUserAuthorizationClient();
		List<String> serviceResponse = null;
		
		if(client != null){
			// Invoke the service
			try {
				serviceResponse = client.getUserRole(username, keyVerify);
			} catch (Exception e) {
				log.error("WsClient.getUserAuthorize(): EXCEPTION: " + e.toString());
			}
		}
		
		return serviceResponse;
	}
	
	private List<AuthorizeInfo> getUserPermission(String username, String keyVerify) {
		
		IUserAuthorizeWS client = getUserAuthorizationClient();
		List<AuthorizeInfo> serviceResponse = null;
		
		if(client != null){
			// Invoke the service
			try {
				serviceResponse = client.getAuthorizeInfo(username, keyVerify);
			} catch (Exception e) {
				log.error("WsClient.getUserAuthorize(): EXCEPTION: " + e.toString());
			}
		}
		
		return serviceResponse;
	}
	
	private IUserAuthorizeWS getUserAuthorizationClient(){
		String authorizeWSUrl = msgSrc.getMessage("ws.url.authorization", null, null);
		Service serviceModel = new ObjectServiceFactory().create(IUserAuthorizeWS.class);
		
		XFire xfire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory = new XFireProxyFactory(xfire);
		
		IUserAuthorizeWS client = null;
		try {
			client = (IUserAuthorizeWS) factory.create(serviceModel, authorizeWSUrl);
		} catch (MalformedURLException e) {
			log.error("WsClient.UserAuthorize: EXCEPTION: " + e.toString());
		}
		return client;
	}

	public String getUrName() {
		return urName;
	}

	public void setUrName(String urName) {
		this.urName = urName;
	}

	public String getUrPosition() {
		return urPosition;
	}

	public void setUrPosition(String urPosition) {
		this.urPosition = urPosition;
	}
	
	
}
