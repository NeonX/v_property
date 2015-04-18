package com.vp.web;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vprop.TestImage;

public class StreamImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String srcPath = request.getParameter("srcPath");
		String toThumb = request.getParameter("toThumb");
		String wi   = request.getParameter("wi");
		String hi   = request.getParameter("hi");
		boolean isThumb = false;
		int width = 100;
		int height = 100;
		if(toThumb!=null && !("".equals(toThumb))){
			isThumb = Boolean.parseBoolean(toThumb);
		}
		if(wi!=null && !("".equals(wi))){
			width = Integer.parseInt(wi);
		}
		if(hi!=null && !("".equals(hi))){
			height = Integer.parseInt(hi);
		}
		
		
		if(isThumb){
			TestImage ti = new TestImage(srcPath, width, height); 
			srcPath = ti.processThumpnail();
		}

		response.setContentType("image/jpeg");

		//String pathToWeb = getServletContext().getRealPath(File.separator);
		File f = new File(srcPath);
		BufferedImage bi = ImageIO.read(f);
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.close();

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}

}