package com.vp.web;

import java.util.HashMap;
import java.util.Map;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;



@Name("menuPageManage")
@Scope(ScopeType.SESSION)
public class MenuPageManage extends AbstractBackingBean<MenuPageManage> {

	private String pagex;
	private String idx;
	private String menux[][] = {{"งานสารบรรณทดลอง","1"},{"งานบัญชี","2"},{"สถานะแจ้งผลการทดสอบ","3"},{"ผู้บริหาร","4"},
								{"proving ring ","5"},{"เครื่องทดสอบกำลังอัดคอนกรีต ","6"},{"งานทดลองอื่นๆ ","7"},{"ตรวจผลทดสอบ","8"},
								
								{"งานทดสอบ ส่วนธรณีวิศวกรรม","9"},{"AIV","10"},{"TFV","11"},{"PLS","12"},{"IRS","13"},
								
								{"เหล็ก","14"},{"งานคอนกรีต","15"},{"งานหิน ดิน ทราย","16"},{"งานควบคุมคุณภาพ","17"},
								{"งาน สมอ.","18"},{"งานสอบเทียบแม่แรง","19"},
								
								{"งานสำรวจและประเมิน ","20"},{"สำรวจแหล่งวัสดุ ","21"},{"สำรวจรากฐาน ","22"},
								
								{"งานทดสอบ ส่วนวิเคราะห์วัสดุทางวิทยาศาสตร์","23"},{"งานป้ายจราจร","24"},{"งานโลหะ","25"},{"งานวัสดุธรรมชาติ","26"},
								
								{"งานทดสอบ ส่วนออกแบบและตรวจสอบผิวทางแอสฟัลต์","27"},{"งานตรวจสอบหรือขออนุมัติใช้","28"},{"งานทดสอบ","29"},
								
								{"รายชื่อผู้ตรวจผล","30"},{"รายชื่อผู้ใช้งานระบบ","31"},{"สิทธิ์การใช้งานระบบ","32"},{"ประวัติการใช้งานระบบ","33"},
								
								{"รายงานสรุป","34"},{"ข้อมูลพื้นฐานราคา","35"},{"ข้อมูลอันดับทดลอง","36"},{"สรุปจำนวนวัสดุทดลอง","37"},{"สรุปรายการชำระค่าทดลอง","38"},
								{"การรับเงินค่าทดลองประจำวัน","39"},{"ชำระค่าทดสอบ","40"}
								};
	private Map<String, String> mapMe = new HashMap<String, String>();
	
	@In(scope = ScopeType.SESSION,required=false)
    String respondArea;
	
	@In(scope = ScopeType.SESSION,required=false)
	@Out(scope = ScopeType.SESSION,required=false)
    String lastPage;
	
	public MenuPageManage() {
		super(MenuPageManage.class);
	}

	@Create
	public void init(){
		for(int i=0; i<menux.length; i++){
			mapMe.put(menux[i][1], menux[i][0]);
		}
		if(respondArea==null){
			respondArea = "0";
		}
		
		Integer val = new Double(respondArea).intValue();
		if("4.1".equals(respondArea)){
			val = 9;
		}
		
		if(identity.hasPermission("system_management_user", null, null)){
			val = 10;
		}
		switch (val) {
			case 1:	idx = "1"; break;
			case 2:	idx = "5"; break;
			case 3:	idx = "9"; break;
			case 4:	idx = "14"; break;
			case 5:	idx = "20"; break;
			case 6:	idx = "23"; break;
			case 7:	idx = "27"; break;
			case 8:	idx = "2"; break;
			case 9:	idx = "8"; break;
			case 10:idx = "31"; break;
			default:idx = "1";	break;
		}
		
		changePage();
	}
	
	public void changePage(){
		if(idx!=null){
			pagex = mapMe.get(idx);
		}
		System.out.println(pagex);
		
	}

	public void setPagex(String pagex) {
		this.pagex = pagex;
	}

	public String getPagex() {
		return pagex;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getIdx() {
		return idx;
	}

	public String getLastPage() {
		return lastPage;
	}

	public void setLastPage(String lastPage) {
		this.lastPage = lastPage;
	}
	
}
