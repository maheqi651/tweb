package com.founderinternational.rscenter.hankesbservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HttpXMLPaser {
	   public static Document document;
	   public HttpXMLPaser(String xmlpath)
	   {
			   try {
				initDom(xmlpath);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	   
	   
	   public static void dealXml(HankServiceBean hankServiceBean ) {
	        if(document!=null)
	        {
	        	List<Element> flow =document.selectNodes("/broker/executeGroup/flow");
	        	if(flow!=null)
	        	{
	        		if(flow.size()>0)
	        		{
	        			Attribute attr=flow.get(0).attribute("name");
	        			if(attr!=null)
	        			attr.setText(hankServiceBean.getServicename());
	        		}	        		 
	        	}
	        	List<Element> endpointlist=document.selectNodes("/broker/executeGroup/flow/endpoint");
	        	if(endpointlist!=null)
	        	{
	        		for(Element ele:endpointlist)
	        		{
	        			Attribute attr=ele.attribute("name");
	        			String namestr=attr.getText();
	        			if("Http_Input".equals(namestr))
	        			{
	        				updateEle(ele,hankServiceBean);
	        			}else if("http_Request".equals(namestr))
	        			{
	        				updateEle(ele,hankServiceBean);
	        			}else if("Http_Reply".equals(namestr))
	        			{
	        				
	        			}else{
	        				
	        			}
	        			
	        		}
	        	}
	        }
	   }
	   
	   
	   public static void updateEle(Element ele,HankServiceBean hankServiceBean){
		   System.out.println("-------11----------"+hankServiceBean.getESBPort());
		   List<Element> lists=ele.element("properties").elements("property");
		   if(lists!=null)
		   {
			   for(Element e:lists)
			   {
				   Attribute attr=e.attribute("name");
       			   String namestr=attr.getText();
       			   System.out.println("namestr11---"+namestr);
       			   if("port".equals(namestr)){
       				  e.setText(hankServiceBean.getESBPort()); 
       			   }else if("context-Path".equals(namestr)){
       				  e.setText(hankServiceBean.getESBContextPath()); 
       			   }else if("ws-Url".equals(namestr)){
       				  e.setText(hankServiceBean.getFWurl()); 
       			   }
			   }
		   }
	   }
	   
	   public static void saveXml(String xmlpath)
	   {  
		   OutputFormat format=OutputFormat.createPrettyPrint();
		   format.setEncoding("UTF-8");
		   try {
				XMLWriter writer=new XMLWriter(new FileWriter(xmlpath),format);
			    writer.write(document);
			    writer.close();
		   } catch (IOException e) {
			    e.printStackTrace();
		}
	   }
	   
       public void initDom(String xmlpath) throws   DocumentException
       {
    	   /*DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
    	   DocumentBuilder db=dbf.newDocumentBuilder();*/
    	   SAXReader reader = new SAXReader(); 
    	   this.document=reader.read(new File(xmlpath));
    	    
    	    
       }
}
