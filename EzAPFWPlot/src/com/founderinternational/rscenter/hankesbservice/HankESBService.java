package com.founderinternational.rscenter.hankesbservice;

import com.founderinternational.rscenter.file.ZipCompressor;
import com.founderinternational.rscenter.tools.Constants;
import com.sun.corba.se.impl.orbutil.closure.Constant;

public class HankESBService implements Runnable {
    private HankServiceBean hankServiceBean;
    private String xmlPath;
    private String type;
	@Override
	public void run() {
		System.out.println("-----");
		if(Constants.HANKHTTP.equals(type))
		{      //http服务挂载
			   String loadpath=xmlPath+"/gzip/http_flow/config.xml";
			   String savepath=xmlPath+"/temp/http_flow/config.xml";
			   String savezippath=xmlPath+"/zip/http/"+hankServiceBean.getServicename()+".zip";
			   String[] postzip=new String[3];
			   postzip[0]=xmlPath+"/temp/http_flow/resources";
			   postzip[1]=xmlPath+"/temp/http_flow/lib";
			   postzip[2]=xmlPath+"/temp/http_flow/config.xml";
			   if(hankServiceBean!=null)
			   {
				   synchronized(this)
				   {
					   //替换xml
                       HttpXMLPaser httpxmlp=new HttpXMLPaser(loadpath);
                       httpxmlp.dealXml(hankServiceBean);
                       httpxmlp.saveXml(savepath);
                       System.out.println("-----"+savepath);
					   //打zip包
                       ZipCompressor zc=new ZipCompressor(savezippath);
                       zc.compress(postzip);  
					   //发送
                       HttpPostFile.postZIP(savezippath, hankServiceBean.getServicename()+".zip");
				   }
			   }
		}else{
			   String loadpath=xmlPath+"/gzip/ws_flow/config.xml";
			   String savepath=xmlPath+"/temp/ws_flow/config.xml";
			   
			   
			   String savezippath=xmlPath+"/zip/wsdl/"+hankServiceBean.getServicename()+".zip";
			   String[] postzip=new String[3];
			   postzip[0]=xmlPath+"/temp/ws_flow/resources";
			   postzip[1]=xmlPath+"/temp/ws_flow/lib";
			   postzip[2]=xmlPath+"/temp/ws_flow/config.xml";
			   if(hankServiceBean!=null)
			   {
				   synchronized(this)
				   {
					   //替换xml
                       WSDLXMLPaser wsdlxmlp=new WSDLXMLPaser(loadpath);
                       wsdlxmlp.dealXml(hankServiceBean);
                       wsdlxmlp.saveXml(savepath);
                       System.out.println("-----"+savepath);
					   //打zip包
                       ZipCompressor zc=new ZipCompressor(savezippath);
                       zc.compress(postzip);  
					   //发送
                       HttpPostFile.postZIP(savezippath, hankServiceBean.getServicename()+".zip");
				   }
			   }
		}
		   
	}
	
	
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getXmlPath() {
		return xmlPath;
	}


	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}


	public HankServiceBean getHankServiceBean() {
		   return hankServiceBean;
	}

	public void setHankServiceBean(HankServiceBean hankServiceBean) {
		   this.hankServiceBean = hankServiceBean;
	}

}
