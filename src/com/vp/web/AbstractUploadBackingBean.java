package com.vp.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.vp.utils.AppUtils;
import com.vp.web.utils.WebUtils;



@MappedSuperclass
public abstract class AbstractUploadBackingBean<T> extends AbstractBackingBean<T> {

	private ResourceBundleMessageSource msgSrcUpload = (ResourceBundleMessageSource)getContextBackingBean().getBean("messageSourceUpload");
	
	protected String rootPath = msgSrcUpload.getMessage("upload.ROOT_PATH", null, null);
	protected String rootAttach = msgSrcUpload.getMessage("attach.type.ROOT_ATTACH", null, null);
	
	protected String thumbnail = msgSrcUpload.getMessage("storage.thumbnail.app", null, null);
	//protected String thumdownload = msgSrcUpload.getMessage("storage.download.app", null, null);
	
	protected String storage_url = msgSrcUpload.getMessage("storage.url", null, null);
	protected String rootPathView = msgSrcUpload.getMessage("url.root.path.view", null, null);
	protected String rootPathDocket = msgSrcUpload.getMessage("attach.type.ROOT_ATTACH.DOCKET", null,null);
	protected String rootPathSTD  = msgSrcUpload.getMessage("attach.type.ROOT_ATTACH.STD", null, null);
	protected String rootPathSTR = msgSrcUpload.getMessage("attach.type.STRENGTH", null, null);
	
	protected List<FileItem> fileItems = new LinkedList<FileItem>();
	
	protected int element = 15;
	protected int uploadsAvailable = 10;
	protected int fileSizeBytesAllows = 6000000;//6MB Upload Limit
	protected int fileSizeBytesAllows25MB = 25000000;//25MB Upload Limit
	protected int fileSizeBytesAllows50MB = 50000000;//50MB Upload Limit
	protected final int viewCol = 5;
	protected boolean autoUpload = false;
	protected boolean useFlash = false;
	
	protected Boolean saveSuccess = false;
	protected String errorMsg = "";
	protected String currentTypeUpload = "";
	
	public AbstractUploadBackingBean(Class<T> persistentClass) {
		super(persistentClass);
	}

	public abstract void specifyBean(UploadItem uploadItem);
	
	public void doListenerUpload(UploadEvent uploadEvent)throws Exception{
		UploadItem uploadItem = uploadEvent.getUploadItem();
		specifyBean(uploadItem);
	}
	
	public String getThumbnailUrl(String projId,String workGroupName,String real_fname,String fname){

		String savePath = "";//rootPath;
		if(!"".equals(workGroupName) && workGroupName !=null){
			savePath += "/"+workGroupName;
		}
		if(!"".equals(projId) && projId !=null){
			savePath += "/"+projId;
		}
		
		if(!"".equals(fname) && fname !=null){
			savePath += "/"+fname;
		}
		
		
		return StringUtils.replace(thumbnail, "<<SRC>>", savePath);
		
	}
	
	public String getUrlEmbeddedView(Integer projectId,String workGroupName, String fname){
		return WebUtils.getHost() + "/" + rootPathView
				+ "/" + workGroupName + "/" + projectId+
				 "/" + fname;
	}
	
	public String getFullImage(String projId,String workGroupName,String real_fname,String fname){
		String url = "";
		if(!url.startsWith("http://")){
			url = "http://"+storage_url;
		}
		url += "/"+rootPathView;
		
		if(!"".equals(workGroupName) && workGroupName !=null){
			url += "/"+workGroupName;
		}
		if(!"".equals(projId) && projId !=null){
			url += "/"+projId;
		}
		
		url += "/"+fname;
		
		return url;
	}//*/

	public Boolean doCheckFileExists(String pathFile){
		File f = new File(pathFile);
		Boolean chke = false;
		if(f.exists()){
			chke = true;
		}
		return chke;
	}
	public void savefile(File upFile, String filepath)throws Exception{
		
		String filename = getFileName(filepath);
		
		String savePath = rootPath;
		savePath += File.separator+rootAttach+File.separator;
		
		File file = new File(savePath,filename);
		FileOutputStream output = new FileOutputStream(file);

		try{
			FileInputStream in = new FileInputStream(upFile);
			while(true){
				int count = in.read();
				
				if(count == -1){
					break;
				}
				output.write(count);
			}
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			output.close();
		}
	}
	
	protected void doMoveFileFromTmp(){
		try {
			if(fileItems != null && fileItems.size() > 0){
				savefiles(fileItems);
				fileItems.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	protected void doMoveFileTestResultFromTmp(String depart){
		try {
			if(fileItems != null && fileItems.size() > 0){
				
				String savePath = rootPath + File.separator + rootAttach + File.separator + depart + File.separator;
				
				for(FileItem fitem : fileItems){
					/*String subPath = "";
					if(fitem.getOssTestCode() != null && fitem.getOssTestCode().trim().length() > 0){
						subPath += fitem.getOssTestCode()+File.separator;
					}
				
					new File(savePath+subPath).mkdirs();
				
					fitem.getFileTmp().renameTo(new File(savePath+subPath+fitem.getName()));*/
				}
				
				fileItems.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void savefiles(List<FileItem> files)throws Exception{
			
			String savePath = rootPath;//+File.separator+rootAttach;
			
			if(files != null && files.size() > 0){
				for(FileItem fitem : files){
			/*		String subPath = "";
					subPath +=fitem.getAttachType();
					if(fitem.getProjRefId() >0){
						subPath += File.separator+fitem.getProjRefId();
					}
					String mainPath = savePath+File.separator+subPath;
					new File(mainPath).mkdirs();
				
					//fitem.getFileTmp().renameTo(new File(mainPath+fitem.getName()));
					File file = new File(mainPath, fitem.getName());
					FileOutputStream output = new FileOutputStream(file);
					
					try{
						//########################################
						FileInputStream in = new FileInputStream(fitem.getFileTmp());
						while(true){
							int count = in.read();
							
							if(count == -1){
								break;
							}
							output.write(count);
						}
						in.close();
						
					}catch(Exception e){
						e.printStackTrace();
					}finally{
						output.close();
					}*/
				}
			}
	}
	//*/
	
	public void setDoUploadDoc(String type){
		currentTypeUpload   = type;
		saveSuccess = false;
		errorMsg = "";
		fileItems.clear();
	}

	public void doNotThingMethod(){}
	
	public void getAttachmentByType(){
		
	}
	
	public void saveDocAttachTmpList(){
		//saveSuccess = false;
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			//errorMsg = "ไม่สามารถบันทึกไฟล์แนบได้";
		}
		
	}
	
	public void savefile(File upFile, String filepath, String fNameToSave, String projId, String pageType)throws Exception{
		
		//String filename = getFileName(filepath);
		
		String savePath = rootPath;
		savePath += File.separator+rootAttach;
		savePath += File.separator+projId;
		savePath += File.separator+pageType+File.separator;
		
		new File(savePath).mkdirs();
		
//		log.info(">> savePath : "+savePath);
//		log.info(">> filename : "+filename);
		
		
		File file = new File(savePath, fNameToSave);
		FileOutputStream output = new FileOutputStream(file);
		
		try{
			//########################################
			FileInputStream in = new FileInputStream(upFile);
			while(true){
				int count = in.read();
				
				if(count == -1){
					break;
				}
				output.write(count);
			}
			in.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			output.close();
		}
	}
	
	public String checkFileForUpload( UploadItem item){
		String fileName = "";
		String typeToSave = "";
		String[] splitName = StringUtils.split(item.getFileName(), "\\");
		if (splitName.length > 0) {
			fileName = splitName[splitName.length - 1];
		}
		int extDot = fileName.lastIndexOf(".");
		if(extDot>0){
			String extension = fileName.substring(extDot +1);
			String tDoc = AppUtils.getMessageByEL("label.attach.typeupload.doc");
			String tImage = AppUtils.getMessageByEL("label.attach.typeupload.image");
			
			if(tDoc.contains(extension)){
				typeToSave = AppUtils.getMessageByEL("attach.type.document");
				  
			}else if(tImage.contains(extension)){
				typeToSave = AppUtils.getMessageByEL("attach.type.image");
			}	
		}
		return typeToSave;
	}
	
	public String getGenerateFileName(String prefix){
		Date date = new Date();
		String dateStr = new SimpleDateFormat("yyyyMMdd_HHmmss", new Locale("US")).format(date);
		
		Random rand = new Random();
		if(prefix != null && prefix.trim().length() > 0){
			return dateStr+"-"+rand.nextInt(10000)+"."+prefix;
		}else{
			return dateStr+"-"+rand.nextInt(10000)+"."+prefix;
		}
	}
	
	public String getGenerateFileNameForReport(String prefix){
		if(prefix != null && prefix.trim().length() > 0){
			return prefix;
		}else{
			return prefix;
		}
	}
	
	public String getFileName(String filepath){
//		log.info("===== getFileName IN : "+filepath);
//		if(filepath != null){
//			log.info("===== getFileName OUT : "+new File(filepath).getName());
//			return new File(filepath).getName();
//		}
		
		String[] splitName = StringUtils.split(filepath, "\\");
		if(splitName.length>0){
//			log.info("===== getFileName OUT : "+splitName[splitName.length-1]);
			return splitName[splitName.length-1];
		}
		
		return "";
	}
	
	public String getServletImgUrl(Integer projectId, String fileName, String type){
		
		String url ="";
		String context = WebUtils.getHostContextUrl()+"/attach_file/projattach";
		
		String param1 = "projid="+projectId;
		String param2 = "fname="+fileName;
		String param3 = "ptype="+type;
		
		if(projectId != null){
			url = context+"?"+param1+"&"+param2+"&"+param3;
		}
		
		return url;
	}
	
	public String getServletDownloadUrl(Integer projectId, Integer attachId, String type){
		return AppUtils.getServletDownloadUrl(projectId, attachId, type);
	}
	
	public String getServletDownloadUrl(Integer projectId, String fname, String type){
		return AppUtils.getServletDownloadUrl(projectId, fname, type);
	}
	
	public String doGenerateCode128(String fileName,String testNumber) {
        BitMatrix bitMatrix;
        int width = 380;
        int height = 48;
        String urlbarcode = "";
        String cod128FileName = fileName+ ".png";
        String filePath = rootPath+"/"+rootAttach+"/";
        try {
            bitMatrix = new Code128Writer().encode(testNumber, BarcodeFormat.CODE_128, width, height, null);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(new File(filePath + cod128FileName)));
           // urlbarcode = getFullImage(null, rootAttach, cod128FileName, cod128FileName);
            
            urlbarcode = getThumbnailUrl(null,rootAttach+File.separator,null , cod128FileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlbarcode;
    }
    
    public String doGenerateQRcode(String fileName,String testNumber) {
    	String urlQRcode = "";
        try {
        	String filePath = rootPath+File.separator+rootAttach+File.separator;
            Writer writer = new QRCodeWriter();
            String qrcodeFileName = fileName+ ".png";
            BitMatrix bitMatrix;
            bitMatrix = writer.encode(testNumber, BarcodeFormat.QR_CODE, 200, 200, null);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(new File(filePath + qrcodeFileName)));

           // urlQRcode = getFullImage(null, rootAttach, qrcodeFileName, qrcodeFileName);
            urlQRcode = getThumbnailUrl(null,rootAttach+File.separator,null , qrcodeFileName); 	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlQRcode;
    }
    
    public String doGenerateQRcodeForReport(String fileName,String testNumber,String subFolder) {
    	String urlQRcode = "";
        try {
        	String filePath = rootPath+"/"+rootAttach+"/"+subFolder+"/";
            Writer writer = new QRCodeWriter();
            String qrcodeFileName = fileName+ ".png";
            BitMatrix bitMatrix;
            bitMatrix = writer.encode(testNumber, BarcodeFormat.QR_CODE, 200, 200, null);
           
			new File(filePath).mkdirs();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(new File(filePath + qrcodeFileName)));

            String path = rootAttach+"/"+subFolder;
            urlQRcode = getThumbnailUrl(null,path,null , qrcodeFileName); 	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlQRcode;
    }
    
    public void deleteElementForDirectory(String fullpath){
    	try {
    		  String files;
    		  File folder = new File(fullpath);
    		  File[] listOfFiles = folder.listFiles(); 
    		  if(listOfFiles !=null && listOfFiles.length >0){
    			  for (int i = 0; i < listOfFiles.length; i++) {
        			  if (listOfFiles[i].isFile()){
    		    		   files = listOfFiles[i].getName();
    		    		  
    		    		   deleteFile(fullpath, files);
        			  }
        		  }
    		  }
    		  
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	public boolean deleteFile(String fileName){
		String pathFileName = rootPath+File.separator+fileName;
		boolean result = false;
		try{
			File delFile = new File(pathFileName);
			if(!delFile.isDirectory()){
				result = delFile.delete();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean deleteFile(String fullPath, String fileName){
		String pathFileName = fullPath+File.separator+fileName;
		
		boolean result = false;
		try{
			File delFile = new File(pathFileName);
			if(!delFile.isDirectory()){
				result = delFile.delete();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public String getNameWithOutExtension(String fileName){
		if(fileName != null){
			File f = new File(fileName);
			int index = f.getName().lastIndexOf('.');
			if(index > 0){
				return f.getName().substring(0, index);
			}
		}
		return "";
	}
	
	
	public String getFileExtension(String fileName){
		if(fileName != null){
			String[] split = fileName.split("\\.");
			if(split != null && split.length > 0){
				return split[split.length-1];
			}
		}
		return "";
	}
	
	public int getUploadsAvailable() {
		return uploadsAvailable;
	}

	public void setUploadsAvailable(int uploadsAvailable) {
		this.uploadsAvailable = uploadsAvailable;
	}

	public boolean getAutoUpload() {
		return autoUpload;
	}

	public void setAutoUpload(boolean autoUpload) {
		this.autoUpload = autoUpload;
	}

	public boolean getUseFlash() {
		return useFlash;
	}

	public void setUseFlash(boolean useFlash) {
		this.useFlash = useFlash;
	}

	public ResourceBundleMessageSource getMsgSrcUpload() {
		return msgSrcUpload;
	}

	public void setMsgSrcUpload(ResourceBundleMessageSource msgSrcUpload) {
		this.msgSrcUpload = msgSrcUpload;
	}

	public int getViewCol() {
		return viewCol;
	}

	public int getFileSizeBytesAllows() {
		return fileSizeBytesAllows;
	}

	public void setFileSizeBytesAllows(int fileSizeBytesAllows) {
		this.fileSizeBytesAllows = fileSizeBytesAllows;
	}

	
	public int getFileSizeBytesAllows25MB() {
		return fileSizeBytesAllows25MB;
	}

	public void setFileSizeBytesAllows25MB(int fileSizeBytesAllows25MB) {
		this.fileSizeBytesAllows25MB = fileSizeBytesAllows25MB;
	}

	public int getFileSizeBytesAllows50MB() {
		return fileSizeBytesAllows50MB;
	}

	public void setFileSizeBytesAllows50MB(int fileSizeBytesAllows50MB) {
		this.fileSizeBytesAllows50MB = fileSizeBytesAllows50MB;
	}

	public int getElement() {
		return element;
	}

	public void setElement(int element) {
		this.element = element;
	}

	public List<FileItem> getFileItems() {
		return fileItems;
	}

	public void setFileItems(List<FileItem> fileItems) {
		this.fileItems = fileItems;
	}

	public Boolean getSaveSuccess() {
		return saveSuccess;
	}

	public void setSaveSuccess(Boolean saveSuccess) {
		this.saveSuccess = saveSuccess;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getCurrentTypeUpload() {
		return currentTypeUpload;
	}

	public void setCurrentTypeUpload(String currentTypeUpload) {
		this.currentTypeUpload = currentTypeUpload;
	}

}
