package com.vp.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.activation.MimetypesFileTypeMap;

import org.jboss.seam.core.Expressions;

import com.vp.web.utils.WebUtils;



public class AppUtils {

	public static SimpleDateFormat formatter_thai = new SimpleDateFormat("yyyyMMdd", new Locale("th", "TH"));
	public static SimpleDateFormat formatter_thaiPresent = new SimpleDateFormat("dd/MM/yy", new Locale("th", "TH"));
	public static Locale thLocale = new Locale("th", "TH");
	public static Locale ukLocale = new Locale("USA", "USA");
	
	//---change number to String bath thai //
	static String mtch[] = {"","หนึ่ง","สอง","สาม","สึ่","ห้า","หก","เจ็ด","แปด","เก้า"};
	static String mtdec[]={"พัน","ร้อย","สิบ","ล้าน","แสน","หมื่น","พัน","ร้อย","สิบ",""} ;
	static String mstnum="",mstthai="",choice1="",cnumthai="",cin_number = "",tmp_key="" ;
	static double  choice=0;
	static int ncnt = 1,nlen = 0,nlen2 = 0,nnumber=0,din_number2 = 0 ;
	
	 public static String numberBathToString(Double in_number){
		 
		   if ((in_number > 9999999999.99) || (in_number <= 0)) {
		        return "" ;
		   }
		    cnumthai = "" ;
		    din_number2 = (int) (in_number*100) ;
		    cin_number = Integer.toString(din_number2) ;
		    mstnum = cin_number ;
		    nlen = cin_number.length() ;
		    nlen2= 12-nlen ;
		    //add "x"
		    for (int xj=0;xj<nlen2;xj++){
		        mstnum = "x"+mstnum ;
		    }
		    for(int xe =0;xe<10;xe++){
		        String  ccharnum = mstnum.substring(xe,xe+1) ;
		        if (ccharnum.equals("x")){
		            cnumthai = cnumthai + "" ;
		       }else{
		           if (ccharnum.equals("0")){
		               if (xe==4){
		                    cnumthai= cnumthai + "ล้าน" ;
		               }else{
		                    cnumthai = cnumthai + "" ;
		               }
		            }else{
		                   if (ccharnum.equals("1")){
		                   if ((xe==3&&nlen!=9)||(xe==9&&nlen!=3)){
		                        cnumthai = cnumthai+"เอ็ด";
		                   }else {
		                        if (xe!=2||xe!=8) {
		                            cnumthai=cnumthai + mtch[Integer.parseInt(ccharnum)];
		                        }
		                   }
		               }else{
		                   if (ccharnum.equals("2")&&(xe==2||xe==8)) {
		                            cnumthai=cnumthai + "ยี่";
		                    }else{
		                            cnumthai=cnumthai + mtch[Integer.parseInt(ccharnum)];
		                    }
		               }
		               cnumthai = cnumthai+ mtdec[xe] ;
		           }
		       }
		    }
		    cnumthai = cnumthai+"บาท" ;
		    //decimal
		    String ccharnum = mstnum.substring(10) ;
		    if (ccharnum.equals("00")){
		        cnumthai = cnumthai+"ถ้วน" ;
		   } else{
		        ccharnum = mstnum.substring(10,11) ;
		        if (!ccharnum.equals("0")){
		            if (!ccharnum.equals("1")){
		               if (ccharnum.equals("2")){
		                  cnumthai = cnumthai+"ยี่" ;
		               }else{
		                   cnumthai = cnumthai+mtch[Integer.parseInt(ccharnum)] ;
		               }
		            }
		            cnumthai = cnumthai+mtdec[8] ;
		        }
		         String ccharnum1 = mstnum.substring(11) ;
		            if (!ccharnum1.equals("0")){
		                if (ccharnum1.equals("1")||ccharnum.equals("0")){
		                      cnumthai = cnumthai+"เอ็ด" ;
		                   }else{
		                       cnumthai = cnumthai+mtch[Integer.parseInt(ccharnum1)] ;
		                   }
		            }
		        cnumthai = cnumthai+"สตางค์" ;
		       }
		   
		        return cnumthai ;
	 }
	 
	 
	public static Object getObjectByEL(String expressionlabel){
		Object  obj = null;
		try{
			obj = Expressions.instance().createValueExpression(expressionlabel).getValue();
		}catch(Exception ex){
			obj = null;
		}
		return obj;
	}
	
	public static String getMessageByEL(String keyName){
		String expressionlabel = "#{messages['"+keyName+"']}";
		Object obj = getObjectByEL(expressionlabel);
		if(obj!=null)
			return (String)obj;
		return "";
	}
	
	public static String dateToString(Date date, String pattern, Locale locale){
		String dateString = null;
		
		if(date != null){
			try{
				dateString = new SimpleDateFormat(pattern,locale).format(date);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return dateString;
	}
	
	public static boolean isNullOrEmpty(Object obj){
		
		if(obj == null){
			return true;
		}
		
		if(obj.getClass().equals(String.class)){
			if(obj.toString().trim().length() == 0){
				return true;
			}
		}
		
		return false;
	}
	
	public static long calDateDiff(Date fromDate, Date toDate){
		
		long result = 0;
		
		if(fromDate != null && toDate != null){
			Calendar frmCal = Calendar.getInstance();
			Calendar toCal = Calendar.getInstance();
			
			frmCal.setTime(fromDate);
			toCal.setTime(toDate);
			
			if(frmCal.before(toCal)){
				result = ((toCal.getTimeInMillis()-frmCal.getTimeInMillis())/86400000)+1;
			}
		}
		
		return result;
	}
	
	public static boolean endDateNotBeforeStartDate(Date startDate, Date endDate){
		
		try{
			if(startDate != null && endDate != null){
				Calendar startCal = Calendar.getInstance();
				Calendar endCal = Calendar.getInstance();
				
				startCal.setTime(startDate);
				endCal.setTime(endDate);
				
				return startCal.before(endCal);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static Date  getDateWithoutTime(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static String dateToString(Date date){
		String dateString = null;
		
		if(date != null){
			try{
				dateString = new SimpleDateFormat("dd/MM/yyyy", thLocale).format(date);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return dateString;
	}
	
	public static String dateToStringEng(Date date){
		String dateString = null;
		
		if(date != null){
			try{
				dateString = new SimpleDateFormat("dd/MM/yyyy", ukLocale).format(date);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return dateString;
	}
	
	public static String dateToString(Date date, String pattern){
		/*Example: 26/11/2556
		  Pattern: d/M/yyyy
		  
		  Example: 26 พ.ย. 2556
		  Pattern: d MMM yyyy
		  
		  Example: 26 พฤศจิกายน 2556
		  Pattern: d MMMM yyyy

 		  Example: วันอังคารที่ 26 พฤศจิกายน พ.ศ. 2556
		  Pattern: EEEE'ที่ 'd MMMM G yyyy
		 * 
		 * */
		String dateString = null;
		
		if(date != null){
			try{
				dateString = new SimpleDateFormat(pattern,thLocale).format(date);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return dateString;
	}
	
	public static String convertStrDateEngThai(String date, String sep, boolean toEng){		
		String res = "";
		if(date!=null && !"".equals(date)){
			res = date.substring(0, date.lastIndexOf(sep));
			String year = date.substring(date.lastIndexOf(sep)+1);
			
			int tmp = Integer.parseInt(year);
			if(toEng){
				tmp -= 543;
			}else{
				tmp += 543;
			}
			res += sep+tmp;
		}

		return res;
	}
	
	public static String formatPatternDateStr(String dateStr, String pattern){
		
		Date dateVal = stringToDate(dateStr);
		
		if(dateVal != null){
			try{
				dateStr = new SimpleDateFormat(pattern, thLocale).format(dateVal);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return dateStr;
	}
	
	public static String getMimeType(String fileName){
		if(fileName != null && fileName.trim().length() > 0){
			return new MimetypesFileTypeMap().getContentType(fileName);
		}else{
			return null;
		}
	}
	
	public static String formatPatternDateStr(String dateStr, String pattern, Locale locale){
		
		Date dateVal = stringToDate(dateStr);
		
		if(dateVal != null){
			try{
				dateStr = new SimpleDateFormat(pattern, locale).format(dateVal);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return dateStr;
	}
	
	public static String formatPatternDateStr(Date dateVal, String pattern, Locale locale){
		/*Example: 26/11/2556
		  Pattern: d/M/yyyy
		  
		  Example: 26 พ.ย. 2556
		  Pattern: d MMM yyyy
		  
		  Example: 26 พฤศจิกายน 2556
		  Pattern: d MMMM yyyy

		  Example: วันอังคารที่ 26 พฤศจิกายน พ.ศ. 2556
		  Pattern: EEEE'ที่ 'd MMMM G yyyy
		 * 
		 * */
		String dateStr = "";
		
		if(dateVal != null){
			try{
				dateStr = new SimpleDateFormat(pattern, locale).format(dateVal);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return dateStr;
	}
	
	public static Date stringToDate(String dateStr){
		Date dateConvert = null;
		
		if(dateStr != null && dateStr.trim().length() > 0){
			try{
				dateConvert = new SimpleDateFormat("dd/MM/yyyy", thLocale).parse(dateStr);
			}catch(Exception e){
				e.printStackTrace();
				dateConvert = null;
			}
		}
		
		return dateConvert;
	}
	
	public static Date stringToDateEng(String dateStr){
		Date dateConvert = null;
		
		if(dateStr != null && dateStr.trim().length() > 0){
			try{
				dateConvert = new SimpleDateFormat("dd/MM/yyyy", ukLocale).parse(dateStr);
			}catch(Exception e){
				e.printStackTrace();
				dateConvert = null;
			}
		}
		
		return dateConvert;
	}
	
	public static Date stringToDate(String dateStr, String pattern){
		Date dateConvert = null;
		
		if(dateStr != null && dateStr.trim().length() > 0){
			try{
				dateConvert = new SimpleDateFormat(pattern, thLocale).parse(dateStr);
			}catch(Exception e){
				e.printStackTrace();
				dateConvert = null;
			}
		}
		
		return dateConvert;
	}
	
	public static Date stringToDate(String dateStr, String pattern, Locale locale){
		Date dateConvert = null;
		
		if(dateStr != null && dateStr.trim().length() > 0){
			try{
				dateConvert = new SimpleDateFormat(pattern, locale).parse(dateStr);
			}catch(Exception e){
				e.printStackTrace();
				dateConvert = null;
			}
		}
		
		return dateConvert;
	}
	
	public static String numberFormater(Object num, String... patterns){
		try{
			if(num == null){
				num = 0;
			}
			
			String defaultPattern = "#,###,##0.00";
			if(patterns.length > 0){
				defaultPattern = patterns[0];
			}
			
			NumberFormat formatter = new DecimalFormat(defaultPattern);
			return formatter.format(num);
		}catch (Exception e) {
			if("-".equals(num) ){
				return "-";
			}
			return "0";
		}
	}
	
	public static Double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("0.00");
		return Double.valueOf(twoDForm.format(d));
	}
	
	public static Double roundTwoDecimalsHalfUp(double d) {
		DecimalFormat twoDForm = new DecimalFormat("0.00");
		twoDForm.setRoundingMode(RoundingMode.HALF_UP);
		return Double.valueOf(twoDForm.format(d));
	}
	
	public static Double roundHalfUpOnePrecision(double d) {
		DecimalFormat twoDForm = new DecimalFormat("0.0");
		twoDForm.setRoundingMode(RoundingMode.HALF_UP);
		return Double.valueOf(twoDForm.format(d));
	}
	
	public static Double calculateDistanceKm(String kmBeginStr, String kmEndStr) {

		Double value = 0D;

		try {
			if((kmBeginStr != null && kmBeginStr.trim().length() > 0) && 
					(kmEndStr != null && kmEndStr.trim().length() > 0)){
				
				kmBeginStr = kmBeginStr.replace("+", "");
				kmEndStr = kmEndStr.replace("+", "");

				Double kmBegin = new Double(kmBeginStr);
				Double kmEnd = new Double(kmEndStr);

				value += Math.abs(kmEnd - kmBegin);
				value = value / 1000;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}
	
	public static BigDecimal BigDecimalByPosition(Integer position,Double number,RoundingMode round){
		BigDecimal bd = new BigDecimal(number);//("123456789.0163456890");
		return bd.setScale(position, round);
	}
	
	public static BigDecimal stringToBigDecimal(String value){
		
		BigDecimal result = new BigDecimal(0);
		
		if(value != null && value.trim().length() > 0){
			
			try{
				
				String tmpVal = value.replace(",", "");
				result = new BigDecimal(tmpVal);
				return result;
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		return result;
	}
	
	public static Double numStrToDouble(String value){
		
		Double result = 0D;
		
		if(value != null && value.trim().length() > 0){
			
			try{
				
				String tmpVal = value.replace(",", "");
				result = Double.parseDouble(tmpVal);
				return result;
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		return result;
	}
	
	public static Double getWeightOfProject(BigDecimal projectCost, BigDecimal quantity, BigDecimal costPerUnit) {
		try{
			BigDecimal actCost = quantity.multiply(costPerUnit);
			BigDecimal weightProj = actCost.multiply(new BigDecimal(100));
			BigDecimal result = weightProj.divide(projectCost, 3, RoundingMode.HALF_UP);
			
			return roundTwoDecimals(result.doubleValue());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0D;
	}
	
	public static Double getWeightOfProjByProgress(BigDecimal percentProg, BigDecimal projCost, BigDecimal actCost ){
		try{
			BigDecimal actCostPrg = percentProg.divide(new BigDecimal(100), 3, RoundingMode.HALF_UP).multiply(actCost);
			BigDecimal percentWeight = actCostPrg.multiply(new BigDecimal(100)).divide(projCost, 3, RoundingMode.HALF_UP);
			return roundTwoDecimals(percentWeight.doubleValue());
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0D;
	}
	
	public static String getServletDownloadUrl(Integer projectId, String fname, String type){
		String url ="";
		String context = WebUtils.getHostContextUrl()+"/attach_file/downloadfile";
		
		String param1 = "projid="+projectId;
		String param2 = "fname="+fname;
		String param3 = "ptype="+type;
		
		if(projectId != null){
			url = context+"?"+param1+"&"+param2+"&"+param3;
		}else{
			url = context+"?"+param2+"&"+param3;
		}
		
		return url;
	}
	
	public static String getServletDownloadUrl(Integer projectId, Integer attachId, String type){
		String url ="";
		String context = WebUtils.getHostContextUrl()+"/attach_file/downloadfile";
		
		String param1 = "projid="+projectId;
		String param2 = "attachid="+attachId;
		String param3 = "ptype="+type;
		
		if(projectId != null){
			url = context+"?"+param1+"&"+param2+"&"+param3;
		}
		
		return url;
	}
	
	public static Double convertKmFormatToNum(String kmStr){
		
		Double value = 0D;
		
		try{
			if(kmStr != null && kmStr.trim().length() > 0){
				kmStr = kmStr.replace("+", "");
				value = new Double(kmStr);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return value;
	}
	
	public static String convertKmValToKmPattern(Double kmVal){
		
		String result = "";
		try{
			if(kmVal != null && kmVal > 0){
				double km = Math.floor(kmVal/1000);
				double m = kmVal % 1000;
				
				if(m > 0){
					result = numberFormater(km,"#,###.##")+"+"+numberFormater(m, "000.##");
				}else{
					result = numberFormater(km,"#,###.##")+"+"+numberFormater(m, "000");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
		
	}
	
	public static boolean isAfterEndOfFiscalYear(Date dateCheck){
		
		if(dateCheck != null){
			
			//==== set the end of fiscal year to 30,SEPTEMBER ====
			Calendar calFiscal = Calendar.getInstance(Locale.US);
			calFiscal.set(Calendar.MONTH, Calendar.SEPTEMBER);
			calFiscal.set(Calendar.DATE, calFiscal.getActualMaximum(Calendar.DATE));
			
			Calendar calChk = Calendar.getInstance(Locale.US);
			calChk.setTime(dateCheck);
			
			if(calChk.after(calFiscal)){
				return true;
			}
			
		}
		
		return false;
	}
	
	public static String convertOssCodeToFolderName(String ossCode){
		if(ossCode != null && ossCode.trim().length() > 0){
			return ossCode.replace("/", "_");
		}
		return null;
	}
	
	public static String hiLight(String text){
		
		text = "<font color='blue'><b>"+text+"</b></font>";
		return text;
	}
	
	public static String SHA1(String text){
		
		try{
			if(text != null && text.length() > 0){
				
				MessageDigest md = MessageDigest.getInstance("SHA1");
				md.update(text.getBytes());
				
				byte[] output = md.digest();
				
				//==== bytes to hex ====
				StringBuffer hexStr = new StringBuffer();
				for (int i = 0; i < output.length; i++) {
					String hex = Integer.toHexString(0xFF & output[i]);

					if (hex.length() == 1) {
						hexStr.append(0);
					}
					hexStr.append(hex);
				}
				return hexStr.toString();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String generateKeyAccessSysUser(String username){
		if(username != null){
			String dateStr = dateToString(new Date(), "ddMMyyyyHH:mm", Locale.US);
			return SHA1(username+dateStr);
		}
		return null;
	}
	
	
	
	 public static String getGenerateFileName(String detail,String prefix){
			Date date = new Date();
			String dateStr = new SimpleDateFormat("yyyyMMdd_HHmmss", new Locale("US")).format(date);
			
			Random rand = new Random();
			if(prefix != null && prefix.trim().length() > 0){
				return detail+"-"+dateStr+"-"+rand.nextInt(10000)+"."+prefix;
			}else{
				return detail+"-"+dateStr+"-"+rand.nextInt(10000)+"."+prefix;
			}
		}
}
