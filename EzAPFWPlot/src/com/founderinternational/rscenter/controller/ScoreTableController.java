 
package com.founderinternational.rscenter.controller;

import java.text.DecimalFormat;
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
import com.founderinternational.rscenter.entity.EZService;
import com.founderinternational.rscenter.entity.EZServiceMditem;
import com.founderinternational.rscenter.entity.Menu;
import com.founderinternational.rscenter.entity.ScoreTable;
import com.founderinternational.rscenter.entity.ServiceVisitInfo;
import com.founderinternational.rscenter.entity.form.EZFunctionVO;
import com.founderinternational.rscenter.entity.form.UserVO;
import com.founderinternational.rscenter.service.EZFunctionCService;
import com.founderinternational.rscenter.service.EZFunctionService;
import com.founderinternational.rscenter.service.EZServiceService;
import com.founderinternational.rscenter.service.MassegeInfoService;
import com.founderinternational.rscenter.service.MenuService;
import com.founderinternational.rscenter.service.ScoreTableService;
import com.founderinternational.rscenter.tools.Constants;
import com.founderinternational.rscenter.tools.PrintGenerator;
import com.founderinternational.rscenter.tools.Tools;
import com.founderinternational.rscenter.tools.impl.TimeGeneral;
 

 
@Controller
@RequestMapping(value = "fg")
public class ScoreTableController {
	private static Object key=new Object();
	 @Resource(name = "scoreTableService")
	 private ScoreTableService scoreTableService;
	 @Resource(name = "eZServiceService")
	 private EZServiceService eZServiceService;
	 @Resource(name = "massegeInfoService")
	 private MassegeInfoService massegeInfoService;
	 
	 @ResponseBody
	 @RequestMapping(value="addsocre")  
	 public Map<String, Object>   addsocre(HttpServletResponse response,HttpServletRequest request,int score,String serviceid,String servicename,String content) throws Exception {
		  response.setCharacterEncoding("utf-8");
		  Map<String, Object> map = new HashMap<String, Object>();
		  ScoreTable st=new ScoreTable();
		  st.setADDTIME(TimeGeneral.getInstance().next());   
		  st.setCONTENT(content);
		  String ip=request.getRemoteAddr();
		  ip=ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
		  st.setIP(ip);
		  st.setSCORE(score);  
		  st.setSERVICEID(serviceid);
		  String username="";
		  UserVO user=null;
		  int status=-1;
		  if(request.getSession().getAttribute(Constants.USER)!=null)
		    user=(UserVO)request.getSession().getAttribute(Constants.USER);
		  if(user==null)
		  {
			  map.put("result", -1); 
			  return map;
		  }
		  st.setUSERNAME(user.getUsername());
		  List<EZServiceMditem> listez= this.eZServiceService.findEZServiceMditem(serviceid); 
		  String serviceuser=null;
		  
		  List<EZService> ezslist=this.eZServiceService.findByServiceid(serviceid);
		  EZService ezs=null;
		  if(ezslist!=null)
		  {
			  ezs=ezslist.get(0);
			  servicename=ezs.getNAME();
		  }
		  //添加评分
		  synchronized (key) {//确保评分更新正确性
			  status=this.scoreTableService.InsertScoreTable(st);
			  //评分更新
			  if(status>0){
			  int count=this.scoreTableService.getcount(serviceid);
			  double prescore=0;
			  if(ezs.getSCORE()!=null)
			  {
				  prescore=Double.parseDouble(ezs.getSCORE());
			  }
			  double scorenow=0;
			  if(count>0)
			  {
				  int sum=this.scoreTableService.getSumByServiceId(serviceid);
				  scorenow=(sum)*1.0/(count);
			  }
			  DecimalFormat df=new DecimalFormat("0.0");
			  System.out.println(ezs.getSCORE());
			  ezs.setSCORE(df.format(scorenow));
			  System.out.println("SCORE:"+ezs.getSCORE());
			  this.eZServiceService.UpdateEZServicefw(ezs);
			  }
		  }
		  //消息提醒
		  if(listez!=null)
		  {
			  for(EZServiceMditem ezm:listez)
			  {
				  if(ezm.getMD_CODE().equals(Constants.SMD_BASE_USER))
				  {
					  serviceuser=ezm.getMD_VALUE(); 
					  if(serviceuser!=null)
					  {
					   this.massegeInfoService.addMessageInfo(serviceuser,
									 Constants.MASSAGEPF,
									"用户"+user.getUsername()+"对你发布的服务"+servicename+"进行了评论",
									"评论时间："+TimeGeneral.getInstance().next()+"\n评论内容:"+content+"\n评分："+score+"分\n评论人："+user.getUsername());
						 
					  }
					  break;
				  }
			  }
		  }
		  map.put("result", status); 
		  return  map;
	}
	 
	 
	 
	    @ResponseBody
		@RequestMapping(value="findScoreList")  
		public Map<String, Object>   findScoreList(HttpServletResponse response,HttpServletRequest request,String serviceid, String page)
				throws Exception
		{
			int startpage=1;
			int startcount=0;
			int endcount=0;
			int pagecount =this.scoreTableService.getcount(serviceid);
	 		int pagenum = pagecount%Constants.pagesize==0?0:1;
			 pagenum = pagecount/Constants.pagesize+pagenum;
	    	if(pagenum==0) pagenum=1;
			if(page!=null)
				startpage=Integer.parseInt(page);
			startcount=(startpage-1)*Constants.pagesize;
			endcount=startpage*Constants.pagesize+1;
			List<ScoreTable> result=this.scoreTableService.findFwSqTableList(serviceid, startcount, endcount);
			response.setCharacterEncoding("utf-8");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result",result);
			UserVO user=null;
			if(request.getSession().getAttribute(Constants.USER)!=null)
				    user=(UserVO)request.getSession().getAttribute(Constants.USER);
			map.put("count", pagecount);
			if(user!=null)
			     map.put("username", user.getUsername());
			else
				 map.put("username", "no user"); 
			map.put("pagenum", pagenum);
			return  map;
		}
	 
	  
	
	 
	 
	 
	 
}