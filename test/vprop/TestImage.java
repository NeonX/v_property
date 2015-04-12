package vprop;

import java.util.Map;

import javaxt.io.Image;

public class TestImage {
	
	private String srcImage;
	private int targetW;
	private int targetH;
	
	
	//http://www.javaxt.com/javaxt-core/javaxt.io.Image/
	public TestImage(String srcImage){
		this.srcImage = srcImage;
	}
	
	public TestImage(String srcImage, int targetW, int targetH) {
		this.srcImage = srcImage;
		this.targetW = targetW;
		this.targetH = targetH;
	}
	
	public String  processThumpnail(){
		Image image = new Image(srcImage);
		Map<Integer, Object> exif = image.getExifTags();
		String output = "";
		
		if(image!=null){
			
			//Print Image Orientation
			  try{
			      int orientation = (Integer) exif.get(0x0112);
			      String desc = "";
			      switch (orientation) {
			          case 1: desc = "Top, left side (Horizontal / normal)"; break;
			          case 2: desc = "Top, right side (Mirror horizontal)"; break;
			          case 3: desc = "Bottom, right side (Rotate 180)"; break;
			          case 4: desc = "Bottom, left side (Mirror vertical)"; break;
			          case 5: desc = "Left side, top (Mirror horizontal and rotate 270 CW)"; break;
			          case 6: desc = "Right side, top (Rotate 90 CW)"; break;
			          case 7: desc = "Right side, bottom (Mirror horizontal and rotate 90 CW)";image.rotateClockwise(); break;
			          case 8: desc = "Left side, bottom (Rotate 270 CW)"; image.rotateCounterClockwise(); break;
			      }
			      System.out.println("Orientation: " + orientation + " -- " + desc);
			  }
			  catch(Exception e){
				  e.printStackTrace();
			  }
			  
			  int width = image.getWidth();
			  int height = image.getHeight();

		
			  boolean isHori = width > height;


			  image.setWidth(targetW);
			  image.saveAs("d:/thAll.jpg");
			  if(isHori){
				  image.crop(0,0,targetW,targetH);
			  }else{
				  image.crop(0,targetH,targetW,targetH);
			  }
			  image.saveAs("d:/thCrop.jpg");
			  
			  output = "d:/thCrop.jpg";
		}
		
		return output;
	}
	


	public String getSrcImage() {
		return srcImage;
	}

	public void setSrcImage(String srcImage) {
		this.srcImage = srcImage;
	}
	
	public int getTargetW() {
		return targetW;
	}

	public void setTargetW(int targetW) {
		this.targetW = targetW;
	}

	public int getTargetH() {
		return targetH;
	}

	public void setTargetH(int targetH) {
		this.targetH = targetH;
	}

	
	
	public static void main(String[] args) {
		//TestImage ti = new TestImage("D:/IMG_3186.JPG"); 
		TestImage ti = new TestImage("D:/IMG_0156.JPG", 264, 104); 
		
		ti.processThumpnail();

	}

	
}
