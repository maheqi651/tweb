package com.easymap.ticket.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.dom4j.DocumentException;

import com.easymap.ticket.db.ReadProperties;
import com.easymap.ticket.tools.testUrlCon;

import sun.misc.BASE64Encoder;
public class ImgJpgQuery extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}
	private void process(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 Object parm=request.getParameter("idcard");
		 String idcard=null;
		 String result="";
		 long times =System.currentTimeMillis();
		 if(parm!=null)
		 {
		    idcard=(String)parm;
		    if(idcard!=null&&!"".equals(idcard))
		    {
		    	try {
		    		result=testUrlCon.getInstance().getPostUrl(ReadProperties.getimageserverurl()+"/"+"ImgQuery?idcard="+idcard, "");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		 }
		 
		 System.out.println(System.currentTimeMillis()-times);
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("image/jpeg");
		 // response.getWriter().print("data:image/jpg;base64,"+result);
		 BufferedImage images= decodeToimage(result);
		 if(images!=null)
		 ImageIO.write(images, "jpg", response.getOutputStream());
	}
	public BufferedImage decodeToimage(String imagestr){
		
		   byte[] imageBytes=ParseBase64Binary(imagestr);
		   try {
			BufferedImage images=ImageIO.read(new ByteArrayInputStream(imageBytes));
			return images;
		} catch (IOException e) {
			e.printStackTrace();
		}
		   return null;
		   
	}

	private byte[] ParseBase64Binary(String imagestr) {
		if(imagestr!=null)
		return DatatypeConverter.parseBase64Binary(imagestr);
		else 
			return null;
	}
}
