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
		TestImage ti = new TestImage(srcPath, 264, 104); 
		String thumpPath = ti.processThumpnail();

		response.setContentType("image/jpeg");

		//String pathToWeb = getServletContext().getRealPath(File.separator);
		File f = new File(thumpPath);
		BufferedImage bi = ImageIO.read(f);
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.close();

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}

}