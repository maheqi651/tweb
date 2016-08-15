
package com.founderinternational.rscenter.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.founderinternational.rscenter.entity.EZFunction;
import com.founderinternational.rscenter.entity.EZFunctionC;
import com.founderinternational.rscenter.entity.EZPFunctionService;
import com.founderinternational.rscenter.entity.EZService;
import com.founderinternational.rscenter.entity.EZServiceApply;
import com.founderinternational.rscenter.entity.EZServiceApplyInfo;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.form.EZFunctionVO;
import com.founderinternational.rscenter.service.EZFunctionCService;
import com.founderinternational.rscenter.service.EZFunctionService;
import com.founderinternational.rscenter.service.EZServiceApplyService;
import com.founderinternational.rscenter.service.EZServiceService;
import com.founderinternational.rscenter.service.MassegeInfoService;
import com.founderinternational.rscenter.service.MenuService;
import com.founderinternational.rscenter.tools.Constants;
import com.founderinternational.rscenter.tools.PrintGenerator;
import com.founderinternational.rscenter.tools.Tools;
import com.founderinternational.rscenter.tools.impl.TimeGeneral;
import  com.founderinternational.rscenter.entity.form.*;

@Controller
@RequestMapping(value = "fg")
public class EZServiceApplyController {

	@Resource(name = "eZServiceApplyService")
	private EZServiceApplyService eZServiceApplyService;
	@Resource(name = "eZFunctionCService")
    private EZFunctionCService eZFunctionCService;
	@Resource(name = "eZFunctionService")
    private EZFunctionService eZFunctionService;
	@Resource(name = "massegeInfoService")
	private MassegeInfoService massegeInfoService;
	@Resource(name = "eZServiceService")
	private EZServiceService eZServiceService;
	
	/*
	 * author cloudMa
	 * 管理员处理申请
	 * date  2015-06-18
	 * */
	@ResponseBody
	@RequestMapping(value="dealapply")  
	public  Map<String, Object>   dealapply(HttpServletResponse response,HttpServletRequest request,int status,String aid,String reply) throws Exception 
	{
		System.out.println("aid"+aid);
		Map<String, Object> map = new HashMap<String, Object>();
		/* UserVO user=new UserVO();
		 user.setRole(Constants.ADMINROLE);
		 user.setUsername("admin");
		 request.getSession().setAttribute(Constants.USER, user);*/
		/*
		 * 权限处理
		 * */
		 
	    if(request.getSession().getAttribute(Constants.USER)==null)
	    {
	    	map.put("result","no auth to access the operation");
	    	return map;
	    }
	    UserVO uservo=(UserVO)request.getSession().getAttribute(Constants.USER);
	    if(!Constants.ADMINROLE.equals(uservo.getRole()))
	    {
	    	map.put("result","no auth to access the operation");
	    	return map;
	    }
	    
	    
	    if(aid!=null)
	    {
		if(status==Constants.TGSTATUS/*通过处理*/)
		{
			EZServiceApply eza=this.eZServiceApplyService.findApplyInfoById(aid);
			if(eza!=null)
			{
				eza.setDEALUSER(uservo.getUsername());
				eza.setDEALTIME(new Date());
				eza.setSTATUS(Constants.TGSTATUS);
				eza.setREPLY("");
				String funcode=eza.getFUNCODE();
				String serviceid=eza.getSERCODE();
				this.eZServiceApplyService.UpdateEZServiceApply(eza);
				EZFunctionC ezfc=this.eZFunctionCService.findByCode(funcode);
				if(ezfc!=null)
				{
					if(this.eZFunctionService.findByCode(funcode)==null)
					{
						EZFunction ezf=new EZFunction();
						ezf.setAPPTYPE(ezfc.getAPPTYPE());
						ezf.setCODE(ezfc.getCODE());
						ezf.setDESCRIPTION(ezfc.getDESCRIPTION());
						ezf.setNAME(ezfc.getNAME());
						ezf.setOWNER(ezfc.getOWNER());
						ezf.setPARENTCODE(ezfc.getPARENTCODE());
						ezf.setTYPE(ezfc.getTYPE());
						ezf.setSEQ(1);
						this.eZFunctionService.Insert(ezf);
					}
					List<EZServiceApplyInfo> ezservicelist=this.eZServiceApplyService.findEZServiceApplyInfo(aid);
			        if(ezservicelist==null)
			        {
			        	EZPFunctionService ezpfs=new EZPFunctionService();
	        			ezpfs.setFUNCTION(funcode);
	        			ezpfs.setSERCODE(serviceid);
	        			this.eZServiceApplyService.insertEZFunctionService(ezpfs);
	        			map.put("result","success");
			        }else{
			        	if(ezservicelist.size()>0)
			        	{
			        		for(EZServiceApplyInfo ezinfotmp:ezservicelist)
			        		{
			        			EZPFunctionService ezpfs=new EZPFunctionService();
			        			ezpfs.setFUNCTION(funcode);
			        			ezpfs.setSERCODE(serviceid);
			        			ezpfs.setTABLECODE(ezinfotmp.getTABLECODE()+"");
			        			ezpfs.setTHEMECODE(ezinfotmp.getTHEMECODE()+"");
			        			this.eZServiceApplyService.insertEZFunctionService(ezpfs);
			        		}
			        		map.put("result","success");
			        	}else{
			        		EZPFunctionService ezpfs=new EZPFunctionService();
		        			ezpfs.setFUNCTION(funcode);
		        			ezpfs.setSERCODE(serviceid);
		        			this.eZServiceApplyService.insertEZFunctionService(ezpfs);
		        			map.put("result","success");
			        	}
			        }
			        
				}
				
				
				//审批通过填写提醒消息
				List<EZService> ezservicelist =this.eZServiceService.findByServiceid(eza.getSERCODE());
				if(ezservicelist!=null)
				{
					EZService ezservice=ezservicelist.get(0);
					this.massegeInfoService.addMessageInfo(ezfc.getOWNER(), Constants.MASSAGEAPTG, "申请服务"+ezservice.getNAME()+"成功", "审批时间："+TimeGeneral.getInstance().next(eza.getDEALTIME())+"\n应用"+ezfc.getNAME()+"申请"+ezservice.getNAME()+"成功 "+"\n审批意见："+eza.getREPLY()+"\n审批人："+uservo.getUsername()+"\n申请人："+eza.getAPPLYUSER()+"\n申请时间："+TimeGeneral.getInstance().next(eza.getAPPLYTIME()));
				}
				
				
			}
			
		}
		else if(status==Constants.jjSTATUS/*拒绝处理*/)
		{
			if(reply!=null)
			{
				EZServiceApply ezajj=this.eZServiceApplyService.findApplyInfoById(aid);
				if(ezajj!=null)
				{  
					ezajj.setDEALUSER(uservo.getUsername());
				    ezajj.setDEALTIME(new Date());
					ezajj.setSTATUS(Constants.jjSTATUS);
					ezajj.setREPLY(reply);
					this.eZServiceApplyService.UpdateEZServiceApply(ezajj);
					map.put("result","success");
					//审批通过填写提醒消息
					List<EZService> ezservicelist =this.eZServiceService.findByServiceid(ezajj.getSERCODE());
					if(ezservicelist!=null)
					{
						EZService ezservice=ezservicelist.get(0);
						EZFunctionC ezfcc=this.eZFunctionCService.findByCode(ezajj.getFUNCODE());
						if(ezfcc!=null)
						{
							this.massegeInfoService.addMessageInfo(ezajj.getDEALUSER(), Constants.MASSAGEAPJJ, 
									"申请服务"+ezservice.getNAME()+"失败", "审批时间："+TimeGeneral.getInstance().next(ezajj.getDEALTIME())+"\n应用"+ezfcc.getNAME()+"申请"+ezservice.getNAME()+"失败 "+"\n审批意见："+ezajj.getREPLY()+"\n审批人："+uservo.getUsername()+"\n申请人："+ezajj.getAPPLYUSER()+"\n申请时间："+TimeGeneral.getInstance().next(ezajj.getAPPLYTIME()));
						}
						}
				}else{
					map.put("result","reply is null");
				}
			}
		}else{
			map.put("result","deal apply faild");
	    	return map;
		}
		}else{
			map.put("result","aid is null");
			return map;
		}
		return  map;
	}
	
	
	
	
	/*
	 * author cloudMa
	 * 普通用户查看个人权限申请，管理员查看所有的
	 * date  2015-06-18
	 * */
	@ResponseBody
	@RequestMapping(value="findServiceApply")  
	public  Map<String, Object>   findServiceApply(HttpServletResponse response,HttpServletRequest request,int status,String start,String flag) throws Exception 
	{
		int startpage=1;
		int startcount=0;
		int endcount=0;
		if(start!=null)
			startpage=Integer.parseInt(start);
		Map<String, Object> map = new HashMap<String, Object>();
		startcount=(startpage-1)*Constants.pagesize;
		endcount=startpage*Constants.pagesize+1;
		UserVO user=null;
		if(request.getSession().getAttribute("user")!=null)
		{
			user=(UserVO)request.getSession().getAttribute("user");
		}
		 
	    
		List<ApplyVO> result=null;
		if(status==Constants.STATUS)//待审批
		{
			if(user.getRole().equals(Constants.ADMINROLE))
			{
				if(flag!=null)
				{
					result=this.eZServiceApplyService.findApplyList(status, user.getUsername(),null);//待审批不需要用户
				}else{
					result=this.eZServiceApplyService.findApplyList(status, null,null);//待审批不需要用户
				}
				
			}else{
				result=this.eZServiceApplyService.findApplyList(status, user.getUsername(), null);
			}
		}else{
			if(user.getRole().equals(Constants.ADMINROLE))
			{
				if(flag!=null)
				{
					result=this.eZServiceApplyService.findApplyList(status, user.getUsername(),null);//待审批不需要用户
				}else{
					result=this.eZServiceApplyService.findApplyList(status, null, user.getUsername());
				}
				
			}else{
				result=this.eZServiceApplyService.findApplyList(status, user.getUsername(), null);
			}
			//管理员是不是可以申请
		}
		response.setCharacterEncoding("utf-8");
		if(result!=null)
		{
			for(ApplyVO av:result)
			{
				av.setDEALTIME(dotime(av.getDEALTIME()));
				av.setAPPLYTIME(dotime(av.getAPPLYTIME()));;
			}
		}
		map.put("data",result);
		return  map;
	}
	public String dotime(String str){
		if(str!=null&&str!=""){
			int endindex=str.lastIndexOf(":");
			if(endindex>10)
				str=str.substring(0, endindex);
		}
		  return str;
	}
	/*
	 * author cloudMa
	 * 添加申请表date 
	 * date  2015-06-18
	 * 2015-06-17
	 * */
	@ResponseBody
	@RequestMapping(value="addServiceApply")  
	public Map<String, Object>   addServiceApply(HttpServletRequest request,HttpServletResponse response,EZFunctionC eZServicec,EZServiceApplyInfo eZServiceApplyInfo,String serviceid) throws Exception {
		response.setCharacterEncoding("utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		String uuid=Tools.getUUID();
		String username="admin";
		int status=0;
		if(request.getSession().getAttribute(Constants.USER)!=null){
			UserVO uservo=(UserVO)request.getSession().getAttribute(Constants.USER);
			username=uservo.getUsername();
		}
		//添加应用副本
		if(eZServicec!=null)
		{   
			if(eZServicec.getCODE()!=null&&!"".equals(eZServicec.getCODE()))
			{
				uuid=eZServicec.getCODE();
				status=1;
			}else{
				eZServicec.setCODE(uuid);
				eZServicec.setTYPE("APP");
				eZServicec.setAPPTYPE("NORMAL");
				eZServicec.setOWNER(username);
				eZServicec.setPARENTCODE(Constants.PARENTCODE);
				status=this.eZFunctionCService.Insert(eZServicec);
			}
		}
		//添加申请表单
		if(status>0)
		{
			if(serviceid!=null)
			{
				String uuidapply=Tools.getUUID();
				EZServiceApply  eza=new EZServiceApply();
				eza.setID(uuidapply);
				eza.setAPPLYTIME(new Date());
				eza.setAPPLYUSER(username);
				eza.setFUNCODE(uuid);
				eza.setSERCODE(serviceid); 
				eza.setSTATUS(Constants.STATUS);
			   status=this.eZServiceApplyService.InsertEZServiceApply(eza);
			   if(status>0)
			   {
				   if(eZServiceApplyInfo!=null)
					{
						eZServiceApplyInfo.setAID(uuidapply);
						String table=eZServiceApplyInfo.getTABLECODE();
						String tables[]=table.split(",");
						String themecode[]=eZServiceApplyInfo.getTHEMECODE().split(",");
						for(int i=0;i<tables.length;i++)
						{
							EZServiceApplyInfo temp=new EZServiceApplyInfo();
							temp.setAID(uuidapply);
							temp.setTABLECODE(tables[i]);
							temp.setTHEMECODE(themecode[i]);
							this.eZServiceApplyService.InsertEZServiceApplyInfo(temp);
						}
					}  
			   }
			}
		}
		//申请表
		map.put("result", status);
		return  map;
	}



	/*
	 * author cloudMa
	 * 审批处理
	 * 修改审批状态码  如果同意进行添加到 应用服务表
	 * date 2015-06-18
	 * */

	@ResponseBody
	@RequestMapping(value="updateServiceApply")  
	public Map<String, Object>   updateServiceApply(HttpServletResponse response,HttpServletRequest request,EZServiceApply eZServiceApply) throws Exception {
		response.setCharacterEncoding("utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		if(request.getSession().getAttribute("user")==null)
		{
			    map.put("result","nu auth to the operation,you must login this system");
			    return map;
		}else{
			UserVO user=(UserVO)request.getSession().getAttribute("user");
			List<EZServiceApply> result=null;
			if(!user.getRole().equals(Constants.ADMINROLE))
			{
				map.put("result","nu auth to the operation");
				return map;
			}
		}
		int status=0;
		if(eZServiceApply!=null){
			if(eZServiceApply.getSTATUS()==Constants.TGSTATUS)
			{
				this.eZServiceApplyService.UpdateEZServiceApply(eZServiceApply);
				//eZPFunctionService.setFUNCTION();待开发
				List<EZServiceApplyInfo> ezinfolist=this.eZServiceApplyService.findEZServiceApplyInfo(eZServiceApply.getID());
				if(ezinfolist!=null)
				{
					for(EZServiceApplyInfo ezinfo:ezinfolist)
					{//添加数据权限
						EZPFunctionService eZPFunctionService=new EZPFunctionService();
						eZPFunctionService.setFUNCTION(eZServiceApply.getFUNCODE());
						eZPFunctionService.setSERCODE(eZServiceApply.getSERCODE()); 
						eZPFunctionService.setTABLECODE(ezinfo.getTABLECODE());
						eZPFunctionService.setTHEMECODE(ezinfo.getTHEMECODE());
						this.eZServiceApplyService.insertEZFunctionService(eZPFunctionService);
					}
				}else{//没有申请数据资源的
					
					EZPFunctionService eZPFunctionService=new EZPFunctionService();
					eZPFunctionService.setFUNCTION(eZServiceApply.getFUNCODE());
					eZPFunctionService.setSERCODE(eZServiceApply.getSERCODE()); 
					this.eZServiceApplyService.insertEZFunctionService(eZPFunctionService);
				}
				//先查询   many  
				//然后插入多个eZPFunctionService
			}else if(eZServiceApply.getSTATUS()==Constants.jjSTATUS){
				this.eZServiceApplyService.UpdateEZServiceApply(eZServiceApply);
			}
			//status=this.eZServiceService.UpdateEZService(ezservice);
		}
		map.put("result", status); 
		return  map;
	}


}