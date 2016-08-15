
package com.founderinternational.rscenter.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.EZService;
import com.founderinternational.rscenter.entity.EZServiceMTODef;
import com.founderinternational.rscenter.entity.EZServiceMditem;
import com.founderinternational.rscenter.entity.EZServiceMditemDEF;
import com.founderinternational.rscenter.entity.EZServiceMditemDEFVO;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.form.EZFunctionVO;
import com.founderinternational.rscenter.entity.form.UserVO;
import com.founderinternational.rscenter.service.EZFunctionService;
import com.founderinternational.rscenter.service.EZServiceService;
import com.founderinternational.rscenter.service.MenuService;
import com.founderinternational.rscenter.tools.Constants;
import com.founderinternational.rscenter.tools.PrintGenerator;
import com.founderinternational.rscenter.tools.Tools;
import com.founderinternational.rscenter.tools.impl.TimeGeneral;



@Controller
@RequestMapping(value = "fg")
public class EZServiceController {

	@Resource(name = "eZServiceService")
	private EZServiceService eZServiceService;
	
	 /*
	 * author cloudMa
	 * 点赞处理
	 * date  2015-06-24
	 * */
	
	@ResponseBody
	@RequestMapping(value="batchUpdateService")  
	public Map<String, Object>   batchUpdateService(HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		 
		//取出所有的服务列表
		   System.out.println("批量更新开始......");
			List<EZService> listez=this.eZServiceService.findNoGroup();
			String timestr="2015-10-18 18:18:18";
			int count =0;
			if(listez!=null)
			{
				//遍历	
				for(EZService ezser:listez)
				{
					 List<EZServiceMditem> list=this.eZServiceService.findEZServiceMditem(ezser.getSERVICEID());
				     if(list!=null)
				     {   boolean flag=false;
				    	 for(EZServiceMditem ezm:list)
				    	 {
				    		 if(ezm.getMD_CODE().equals(Constants.SMD_BASE_TIME))
				    		 {   
				    			 flag=true;
				    		 }
				    	 }
				    	 if(!flag)
				    	 {//添加服务
				    		 System.out.println("更新服务id 为 ： "+ezser.getSERVICEID());
				    		 this.eZServiceService.InsertEZServiceM(getNewEZServiceMditem(timestr,ezser.getSERVICEID()));
				    		 //ezser.getSERVICEID()
				    		 count++;
				    	 }
				    		 //MD_CODE
				     }else{//添加服务
				    	     System.out.println("更新服务id 为 ： "+ezser.getSERVICEID());
				    	     this.eZServiceService.InsertEZServiceM(getNewEZServiceMditem(timestr,ezser.getSERVICEID()));
				             count++;
				     }
				}
			}
			
		    System.out.println("一共更新"+count+"条记录");
		    System.out.println("更新结束......");
		 
		
		return map;
	}
	
	public EZServiceMditem getNewEZServiceMditem(String timestr,String servieceid){
		   EZServiceMditem ezmnew=new EZServiceMditem();
		   ezmnew.setMD_CODE(Constants.SMD_BASE_TIME);
		   ezmnew.setSERVICE_ID(servieceid);
		   ezmnew.setMD_VALUE(timestr);
		   return ezmnew;
	}
	
	   @ResponseBody
	   @RequestMapping(value = "upload")  
	    public Map<String, Object>  upload(@RequestParam(value = "ajaxfile", required = false) MultipartFile ajaxfile, HttpServletRequest request) {  
		    Map<String, Object> map = new HashMap<String, Object>();
	        System.out.println("开始");  
	        if(ajaxfile!=null){
	        String path = request.getSession().getServletContext().getRealPath("");
	        System.out.println("path1:"+path);  
	        int index=path.lastIndexOf("\\");
	        System.out.println("index:"+index);
	        path= path.substring(0, index);
	        path=path+"\\upload";
	        System.out.println("path2:"+path);
	        String name=ajaxfile.getOriginalFilename().toLowerCase();
	        if(name.contains(".jpg")||name.contains(".png")||name.contains(".gif"))
	        {
	        	String fileName = Tools.getUUID()+".jpg";  
	 	       
	 	        File targetFile = new File(path, fileName);  
	 	        if(!targetFile.exists()){  
	 	            targetFile.mkdirs();  
	 	        }  
	 	        //保存  
	 	        try {  
	 	        	ajaxfile.transferTo(targetFile);  
	 	        } catch (Exception e) {  
	 	            e.printStackTrace();  
	 	        }  
	 	        map.put("result", fileName);
	        	
	        }else 
	        {
	        	map.put("result", "图片格式有误，请选择图片文件！");
	        }
	        }else{
	        	map.put("result", "请选择一个图片文件！");
	        }
	        return map;  
	    }  
	   
	   /*
		 * author cloudMa
		 * 点赞处理
		 * date  2015-06-24
		 * */
		
		@ResponseBody
		@RequestMapping(value="addservicefwS")  
		public Map<String, Object>   addservicefwS(HttpServletResponse response,String serviceid) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			if(serviceid!=null&&!"".equals(serviceid)){
				List<EZServiceMditem> result=this.eZServiceService.findEZServiceMditem(serviceid);
				if(result==null)
				{
					EZServiceMditem em=new EZServiceMditem();
					em.setMD_CODE(Constants.SMD_BASE_ACOUNT);
					em.setSERVICE_ID(serviceid);
	                em.setMD_VALUE("1");
	                this.eZServiceService.InsertEZServiceM(em);
				}else{
					//检测
					boolean flag=false;
					for(EZServiceMditem ezm :result)
					{
						if(Constants.SMD_BASE_ACOUNT.equals(ezm.getMD_CODE()))
						{
							ezm.setMD_VALUE(""+(Integer.parseInt(ezm.getMD_VALUE())+1));
							this.eZServiceService.UpdateEZServiceM(ezm);
							flag=true;
						}
						
					}
					if(!flag)
					{//不存在
						EZServiceMditem em=new EZServiceMditem();
						em.setMD_CODE(Constants.SMD_BASE_ACOUNT);
						em.setSERVICE_ID(serviceid);
		                em.setMD_VALUE("1");
		                this.eZServiceService.InsertEZServiceM(em);
					}
	    
				}

				map.put("result",result);
			}
			return map;
		}
		
		@ResponseBody
		@RequestMapping(value="addservicefw")  
		public Map<String, Object>   addservicefw(HttpServletResponse response,String serviceid) throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			if(serviceid!=null&&!"".equals(serviceid)){
				List<EZService> result=this.eZServiceService.findByServiceid(serviceid);
				if(result==null)
				{
					 
				}else{
					EZService ezs=result.get(0);
					if(ezs.getACCESSCOUNT()==null)
					{
						ezs.setACCESSCOUNT(0);
					}
					else{
						ezs.setACCESSCOUNT(ezs.getACCESSCOUNT()+1);
					}
					if(ezs.getSCORE()==null)
						ezs.setSCORE(0+"");
					this.eZServiceService.UpdateEZServicefw(ezs);
				}

				map.put("result",result);
			}
			return map;
		}
		
		
		
	/*
	 * author cloudMa
	 * 点赞处理
	 * date  2015-06-24
	 * */
	
	@ResponseBody
	@RequestMapping(value="addservicedz")  
	public Map<String, Object>   addservicedz(HttpServletResponse response,String serviceid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if(serviceid!=null&&!"".equals(serviceid)){
			List<EZServiceMditem> result=this.eZServiceService.findEZServiceMditem(serviceid);
			if(result==null)
			{
				EZServiceMditem em=new EZServiceMditem();
				em.setMD_CODE(Constants.SMD_BASE_DZ);
				em.setSERVICE_ID(serviceid);
                em.setMD_VALUE("1");
                this.eZServiceService.InsertEZServiceM(em);
			}else{
				//检测
				boolean flag=false;
				for(EZServiceMditem ezm :result)
				{
					if(Constants.SMD_BASE_DZ.equals(ezm.getMD_CODE()))
					{
						ezm.setMD_VALUE(""+(Integer.parseInt(ezm.getMD_VALUE())+1));
						this.eZServiceService.UpdateEZServiceM(ezm);
						flag=true;
					}
				}
				if(!flag)
				{//不存在
					EZServiceMditem em=new EZServiceMditem();
					em.setMD_CODE(Constants.SMD_BASE_DZ);
					em.setSERVICE_ID(serviceid);
	                em.setMD_VALUE("1");
	                this.eZServiceService.InsertEZServiceM(em);
				}
    
			}

			map.put("result",result);
		}
		return map;
	}
	
	
	/*
	 * author cloudMa
	 * 服务列表元信息
	 * date  2015-06-24
	 * */
	
	@ResponseBody
	@RequestMapping(value="findgroup")  
	public Map<String, Object>   findgroup(HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<EZService> result=this.eZServiceService.findGroup();
		map.put("result",result);
		return map;
	}
	
	
	
	/*
	 * author cloudMa
	 * 服务列表元信息
	 * date  2015-06-24
	 * */
	
	@ResponseBody
	@RequestMapping(value="findserviceByid")  
	public Map<String, Object>   findserviceByid(HttpServletResponse response,String serviceid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if(serviceid!=null&&!"".equals(serviceid))
		 {    
			
			 List<EZService> listservice=this.eZServiceService.findByServiceid(serviceid);
			 if(listservice!=null)
			 {
				 if(listservice.size()>0)
				 {  
					 EZService ezs=listservice.get(0);
					 List<EZServiceMditem> list=this.eZServiceService.findEZServiceMditem(serviceid);
					 EZServiceMditemDEF def=EZServiceMTODef.MTOdef(list);
					 ezs.setTYPE(Constants.dicMap.get(ezs.getTYPE().trim()));
					 ezs.seteZServiceMditemDEF(def);
					 map.put("result",ezs);
				 }
			 }
		 }
		return map;
	}
	
	
	
	
	/*
	 * author cloudMa
	 * 个人服务列表
	 * date  2015-06-26
	 * */
	@ResponseBody
	@RequestMapping(value="findservicebyuser")  
	public Map<String, Object>   findservicebyuser(HttpServletRequest requset,HttpServletResponse response,String types,String NAME,String DESP,String page) throws Exception {
	     String username="admin";
		if(requset.getSession().getAttribute(Constants.USER)!=null)
	    {
	    	UserVO user=(UserVO)requset.getSession().getAttribute(Constants.USER);
	    	username=user.getUsername();
	    }
		long times =System.currentTimeMillis();
		int startpage=1;
		int startcount=0;
		int endcount=0;
		int pagecount =this.eZServiceService.findEZServiceCount(username,types, NAME, DESP);
 		int pagenum = pagecount%Constants.pagesize==0?0:1;
		 pagenum = pagecount/Constants.pagesize+pagenum;
    	if(pagenum==0) pagenum=1;
		 
		if(page!=null)
			startpage=Integer.parseInt(page);
		startcount=(startpage-1)*Constants.pagesize;
		endcount=startpage*Constants.pagesize+1;
		List<EZService> result=this.eZServiceService.findEZService(username,types,NAME, DESP, startcount, endcount);
		if(result!=null)
		{
			for(EZService ez:result)
			{
				 List<EZServiceMditem> list=this.eZServiceService.findEZServiceMditem(ez.getSERVICEID());
				 ez.seteZServiceMditemDEF(EZServiceMTODef.MTOdef(list));
			}
		}
		response.setCharacterEncoding("utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result",result);
		map.put("pagenum", pagenum);
		System.out.println(System.currentTimeMillis()-times);
		return  map;
	}
	
	public int judgeSort(String sortstr)
	{   
		int results=Constants.NOSORT;
		if(sortstr==null||sortstr=="")
		    return Constants.NOSORT;
		else{
			results=Integer.parseInt(sortstr.trim());
			if(results>Constants.ASC)
				return Constants.DESC;
			else if(results==Constants.ASC) 
				return Constants.ASC;
			else
				return Constants.NOSORT;
			
		}
	}
	
	/*
	 * author cloudMa
	 * 服务列表
	 * date  2015-06-18
	 * */
	@ResponseBody
	@RequestMapping(value="findservice")  
	public Map<String, Object>   findservice(HttpServletResponse response,String types,String regiontype,String interfacetype,String NAME,String DESP,String page,String namesort,String scoresort,String accessort,String newsort) throws Exception {
	    long times =System.currentTimeMillis();
		int startpage=1;
		int startcount=0;
		int endcount=0;
		int namesortint=Constants.NOSORT;
		int scoresortint=Constants.NOSORT;
		int accessortint=Constants.NOSORT;
		int newsortint=Constants.NOSORT;
		
		namesortint=judgeSort(namesort);
		scoresortint=judgeSort(scoresort);
		accessortint=judgeSort(accessort);
		newsortint=judgeSort(newsort);
		int pagecount =this.eZServiceService.findEZServiceCount(regiontype,interfacetype,types, NAME, DESP);
 		int pagenum = pagecount%Constants.pagesizeindex==0?0:1;
		 pagenum = pagecount/Constants.pagesizeindex+pagenum;
    	if(pagenum==0) pagenum=1;
		 
		if(page!=null)
			startpage=Integer.parseInt(page);
		startcount=(startpage-1)*Constants.pagesizeindex+1;
		endcount=startpage*Constants.pagesizeindex+1;
		List<EZService> result=this.eZServiceService.findEZService(regiontype,interfacetype,types,NAME, DESP,
				namesortint,scoresortint,accessortint,newsortint,startcount, endcount);
		if(result!=null)
		{
			for(EZService ez:result)
			{
				 List<EZServiceMditem> list=this.eZServiceService.findEZServiceMditem(ez.getSERVICEID());
				 ez.seteZServiceMditemDEF(EZServiceMTODef.MTOdef(list));
			}
		}
		response.setCharacterEncoding("utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result",result);
		map.put("pagenum", pagenum);
		map.put("totalcount", pagecount);
		System.out.println(System.currentTimeMillis()-times);
		return  map;
	}
	
	/*
	 * author cloudMa
	 * 添加服务date 
	 * date  2015-06-18
	 * 2015-06-17
	 * */
	
	
	@ResponseBody
	@RequestMapping(value="addService")  
	public Map<String, Object>   addService(HttpServletRequest request,HttpServletResponse response,EZService ezservice,EZServiceMditemDEFVO ezservicedef) throws Exception {
		response.setCharacterEncoding("utf-8");
		 UserVO user=new UserVO();
		 user.setRole(Constants.ADMINROLE);
		 user.setUsername("admin");
		 request.getSession().setAttribute(Constants.USER, user);
		Map<String, Object> map = new HashMap<String, Object>();
		int status=-1;
		if(ezservice!=null){
			ezservice.setPARENTSERVICEID(Constants.dicParentService.get(ezservice.getTYPE()));
			String serviceid=Tools.getUUID();
			System.out.println(serviceid);
			ezservice.setSERVICEID(serviceid);
			status=this.eZServiceService.InsertEZService(ezservice);
			if(status>0)
			{
				if(ezservicedef!=null)
				{   
					if(ezservicedef.getCompany()!=null)
					{
						EZServiceMditem ezsm=new EZServiceMditem();
						ezsm.setMD_CODE(Constants.SMD_BASE_COMPANY);
						ezsm.setMD_VALUE(ezservicedef.getCompany());
						ezsm.setSERVICE_ID(serviceid);
						this.eZServiceService.InsertEZServiceM(ezsm);
					}
					if(ezservicedef.getGET()!=null)
					{
						EZServiceMditem ezsm1=new EZServiceMditem();
						ezsm1.setMD_CODE(Constants.SMD_BASE_GET);
						ezsm1.setMD_VALUE(ezservicedef.getGET());
						ezsm1.setSERVICE_ID(serviceid);
						this.eZServiceService.InsertEZServiceM(ezsm1);
					}
					if(ezservicedef.getImageurl()!=null)
					{
						EZServiceMditem ezsm2=new EZServiceMditem();
						ezsm2.setMD_CODE(Constants.SMD_BASE_IMAGEURL);
						ezsm2.setMD_VALUE(ezservicedef.getImageurl());
						ezsm2.setSERVICE_ID(serviceid);
						this.eZServiceService.InsertEZServiceM(ezsm2);
					}
					if(ezservicedef.getVersion()!=null)
					{
						EZServiceMditem ezsm3=new EZServiceMditem();
						ezsm3.setMD_CODE(Constants.SMD_BASE_VERSION);
						ezsm3.setMD_VALUE(ezservicedef.getVersion());
						ezsm3.setSERVICE_ID(serviceid);
						this.eZServiceService.InsertEZServiceM(ezsm3);
					}
					EZServiceMditem ezsm4=new EZServiceMditem();
					ezsm4.setMD_CODE(Constants.SMD_BASE_TIME);
					ezsm4.setMD_VALUE(TimeGeneral.getInstance().next());
					ezsm4.setSERVICE_ID(serviceid);
					this.eZServiceService.InsertEZServiceM(ezsm4);


					if(request.getSession().getAttribute("user")!=null)
					{
						UserVO uservo=(UserVO) request.getSession().getAttribute("user");
						EZServiceMditem ezsm5=new EZServiceMditem();
						ezsm5.setMD_CODE(Constants.SMD_BASE_USER);
						ezsm5.setMD_VALUE(uservo.getUsername());
						ezsm5.setSERVICE_ID(serviceid);
						this.eZServiceService.InsertEZServiceM(ezsm5);
					}

				}
			}
		}

		map.put("result",status);
		return  map;
	}


  
	/*
	 * author cloudMa
	 * 修改服务
	 * date 2015-06-18
	 * */

	@ResponseBody
	@RequestMapping(value="updateService")  
	public Map<String, Object>   updateService(HttpServletRequest request,HttpServletResponse response,EZService ezservice,EZServiceMditemDEFVO ezservicedef) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int status=-1;
		if(ezservice!=null){
			ezservice.setPARENTSERVICEID(Constants.dicParentService.get(ezservice.getTYPE()));
			if(ezservice.getACCESSCOUNT()==null)
			{
				ezservice.setACCESSCOUNT(0);
			}
			status=this.eZServiceService.UpdateEZService(ezservice);
			List<EZService> list=this.eZServiceService.findByid(ezservice.getID());
			if(list!=null)
			{
				EZService ez=list.get(0);
				String serviceid=ez.getSERVICEID();
				List<EZServiceMditem> ezsmli=this.eZServiceService.findEZServiceMditem(serviceid);
				if(ezservicedef!=null)
				{   
					if(ezservicedef.getCompany()!=null)
					{
						EZServiceMditem ezsm= getEZServiceMditem(ezsmli,Constants.SMD_BASE_COMPANY);
						if(ezsm!=null){//更新
							ezsm.setMD_VALUE(ezservicedef.getCompany());
							this.eZServiceService.UpdateEZServiceM(ezsm);
						}else{
							ezsm=new EZServiceMditem();
							ezsm.setMD_CODE(Constants.SMD_BASE_COMPANY);
							ezsm.setMD_VALUE(ezservicedef.getCompany());
							ezsm.setSERVICE_ID(serviceid);
							this.eZServiceService.InsertEZServiceM(ezsm);
						}
						
					}
					if(ezservicedef.getGET()!=null)
					{
						
						EZServiceMditem ezsm1= getEZServiceMditem(ezsmli,Constants.SMD_BASE_GET);
						if(ezsm1!=null){//更新
							ezsm1.setMD_VALUE(ezservicedef.getGET());
							this.eZServiceService.UpdateEZServiceM(ezsm1);
						}else{
							ezsm1=new EZServiceMditem();
							ezsm1.setMD_CODE(Constants.SMD_BASE_GET);
							ezsm1.setMD_VALUE(ezservicedef.getGET());
							ezsm1.setSERVICE_ID(serviceid);
							this.eZServiceService.InsertEZServiceM(ezsm1);
						}
						
					 
					}
					if(ezservicedef.getImageurl()!=null)
					{
						EZServiceMditem ezsm2= getEZServiceMditem(ezsmli,Constants.SMD_BASE_IMAGEURL);
						if(ezsm2!=null){//更新
							ezsm2.setMD_VALUE(ezservicedef.getImageurl());
							this.eZServiceService.UpdateEZServiceM(ezsm2);
						}else{
							ezsm2=new EZServiceMditem();
							ezsm2.setMD_CODE(Constants.SMD_BASE_IMAGEURL);
							ezsm2.setMD_VALUE(ezservicedef.getImageurl());
							ezsm2.setSERVICE_ID(serviceid);
							this.eZServiceService.InsertEZServiceM(ezsm2);
						}
					}
					if(ezservicedef.getVersion()!=null)
					{
						EZServiceMditem ezsm3= getEZServiceMditem(ezsmli,Constants.SMD_BASE_VERSION);
						if(ezsm3!=null){//更新
							ezsm3.setMD_VALUE(ezservicedef.getVersion());
							this.eZServiceService.UpdateEZServiceM(ezsm3);
						}else{
							ezsm3=new EZServiceMditem();
							ezsm3.setMD_CODE(Constants.SMD_BASE_VERSION);
							ezsm3.setMD_VALUE(ezservicedef.getVersion());
							ezsm3.setSERVICE_ID(serviceid);
							this.eZServiceService.InsertEZServiceM(ezsm3);
						}
						 
					}
					 


					if(request.getSession().getAttribute("user")!=null)
					{
						UserVO uservo=(UserVO) request.getSession().getAttribute("user");
						EZServiceMditem ezsm5= getEZServiceMditem(ezsmli,Constants.SMD_BASE_USER);
						if(ezsm5!=null){//更新
							ezsm5.setMD_VALUE(uservo.getUsername());
							this.eZServiceService.UpdateEZServiceM(ezsm5);
						}else{
							ezsm5=new EZServiceMditem();
							ezsm5.setMD_CODE(Constants.SMD_BASE_USER);
							ezsm5.setMD_VALUE(uservo.getUsername());
							ezsm5.setSERVICE_ID(serviceid);
							this.eZServiceService.InsertEZServiceM(ezsm5);
						}
					}

				}
			}
		}
		map.put("result", status); 
		return  map;
	}
	
	
	public EZServiceMditem getEZServiceMditem(List<EZServiceMditem> list,String str)
	{
		
		if(list!=null)
		{
			for(EZServiceMditem ez:list)
			{
				if(ez.getMD_CODE().equals(str))
					return ez;
			}
		}
		return null;
	}


}