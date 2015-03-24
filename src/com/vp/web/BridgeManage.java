package com.vp.web;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import com.pte.util.PteUtil;



@Name("bridgeManage")
@Scope(ScopeType.PAGE)
public class BridgeManage extends AbstractBackingBean<BridgeManage> {

	@In(scope = ScopeType.SESSION,required=false)
    String respondArea;
	
	@Out(scope=ScopeType.SESSION,required=false)
	String menuPage;

	
	private String target = "";
	
	public BridgeManage() {
		super(BridgeManage.class);
	}

	@Create
	public void init(){	
		String[] menu = new String[8];

		menu[0] = "ERROR";
		menu[1] = "/imgs/theme_n/status_grp_oss.png";
		menu[2] = "/imgs/theme_n/status_grp_standard.png";
		menu[3] = "/imgs/theme_n/status_grp_geotechnical.png";
		menu[4] = "/imgs/theme_n/status_grp_strength.png";
		menu[5] = "/imgs/theme_n/status_grp_survey.png";
		menu[6] = "/imgs/theme_n/status_grp_science.png";
		menu[7] = "/imgs/theme_n/status_grp_asphalt.png";
		
		if(respondArea==null || "".equals(respondArea)){
			respondArea = "0";
		}
		
		respondArea  = identity.hasPermission("system_management_user", null, null)?"9":respondArea;
		
		target = "init.menu.part."+respondArea;
		Integer val = new Double(respondArea).intValue();
		
		//Integer val = Integer.parseInt(respondArea);
		if(val==8 || val==9){
			val = 1;
		}
		menuPage = menu[val];
		//System.out.println("Initial");
	}
	
	public void directPage(){
		//System.out.println(target);
		String pagex = PteUtil.getMessageByEL(target);
	
		forceRedirectPage(pagex);
	}
	
	public void directCheckPage(){
		target = "init.menu.part.check";
		String pagex = PteUtil.getMessageByEL(target);
	
		forceRedirectPage(pagex);
	}

	
	//----########## Get Set ##########-----//
	public void setTarget(String target) {
		this.target = target;
	}

	public String getTarget() {
		return target;
	}


	
}
