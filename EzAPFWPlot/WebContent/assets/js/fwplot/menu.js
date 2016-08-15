var pstatus=0;
function loadmenu(type){
	/* var mimetype = navigator.mimeTypes["application/npclipboard"];
	  if(mimetype)
	    {
	        var plugin = mimetype.enabledPlugin;

	        if(plugin)
	        {
	            document.writeln("已经安装");
	        }
	    }
	    else
	    {
	        document.writeln("还未安装");
	    } */
	
	$.ajax({
		type : "POST",
		url : "login/findrole.do",
		data : "",
		dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
		type : "POST",
		cache : false,
		success : function(data) {
			 if(data.result=="admin")
				 {
				     pstr(1,type);
				 }else{
					 pstr(0,type);
				 }
		}});
}
function pstr(status,type)
{
	
	
	
	htmlstr='';
	
	
	if(type==11||type==12||type==13||type==14)
		{//个人中心
		if(type==11)//服务发布
		{
			htmlstr+=' <li  class="active open"> <a   class="menu-dropdown">';
		}else{
			htmlstr+=' <li> <a class="menu-dropdown">'; }
		htmlstr+=' <i class="menu-icon glyphicon glyphicon-open"></i> <span class="menu-text"> 服务发布 </span>  <i class="menu-expand"></i></a> ';
		
		if(pstatus==0)
			{
			 htmlstr+=' <ul class="submenu"><li  class="active"><a href="publishservice.html"><span class="menu-text">服务注册</span></a></li>';
			}else{
			 htmlstr+=' <ul class="submenu"><li><a href="publishservice.html"><span class="menu-text">服务注册</span></a></li>';	
			}
		if(pstatus==2)
		{
			htmlstr+=' <li class="active"><a href="fwsqdealliststatus.html?status=0"><span class="menu-text">待审核的服务</span></a></li> ';
		}else{
			htmlstr+=' <li><a href="fwsqdealliststatus.html?status=0"><span class="menu-text">待审核的服务</span></a></li> ';	
		}

		if(pstatus==1)
		{
			htmlstr+=' <li class="active"><a href="fwsqdealliststatus.html?status=1"><span class="menu-text">审核成功的服务</span></a></li> ';
		}else{
			htmlstr+=' <li><a href="fwsqdealliststatus.html?status=1"><span class="menu-text">审核成功的服务</span></a></li> ';
		}
		if(pstatus==-1)
		{
			htmlstr+=' <li class="active"><a href="fwsqdealliststatus.html?status=-1"><span class="menu-text">审核失败的服务</span></a></li> ';	
		}else{
			htmlstr+=' <li><a href="fwsqdealliststatus.html?status=-1"><span class="menu-text">审核失败的服务</span></a></li> ';
		}
		
		htmlstr+=' </ul></li>';
		if(type==12)
		{
			htmlstr+=' <li  class="active open"> <a class="menu-dropdown">';
		}else{
			htmlstr+=' <li  > <a class="menu-dropdown">';
		}
		htmlstr+=' <i class="menu-icon glyphicon glyphicon-tasks"></i> <span class="menu-text"> 服务申请 </span>  <i class="menu-expand"></i></a> ';
		
		
		if(pstatus==0)
		{
			htmlstr+='  <ul class="submenu"><li class="active"><a href="applylist.html?status=0"><span class="menu-text">待审批的申请</span></a></li>';
		}else{
			htmlstr+='  <ul class="submenu"><li><a href="applylist.html?status=0"><span class="menu-text">待审批的申请</span></a></li>';	
		}
		
		if(pstatus==1)
		{
			htmlstr+='  <li class="active"><a href="applylist.html?status=1"><span class="menu-text">申请成功的服务</span></a></li>';
		}else{
			htmlstr+='  <li><a href="applylist.html?status=1"><span class="menu-text">申请成功的服务</span></a></li>';
		}
		
		if(pstatus==-1)
		{
			htmlstr+='  <li  class="active"><a href="applylist.html?status=-1"><span class="menu-text">申请失败的服务</span></a></li></ul></li>';
		}else{
			htmlstr+='  <li><a href="applylist.html?status=-1"><span class="menu-text">申请失败的服务</span></a></li></ul></li>';
		}
		

		//htmlstr+='<li> <a href="#"> <i class="menu-icon fa fa-bar-chart-o"></i> <span class="menu-text">个人统计</span></a> </li>';
		
		
		htmlstr+='<li> <a href="visit.htm" target="blank"> <i class="menu-icon fa fa-bar-chart-o"></i> <span class="menu-text">访问已申请的服务</span></a> </li>';
		
		
		if(type==14)
		{
			htmlstr+=' <li  class="active open"> <a class="menu-dropdown">';
		}else{
			htmlstr+=' <li  > <a class="menu-dropdown">';
		}
		
		htmlstr+=' <i class="menu-icon  glyphicon glyphicon-envelope"></i> <span class="menu-text">消息通知 </span>  <i class="menu-expand"></i></a> ';
		
		if(pstatus==0)
		{
			htmlstr+='  <ul class="submenu"><li class="active"><a href="massegelist.html?status=0"><span class="menu-text">未读消息</span></a></li>';
		}else{
			htmlstr+='  <ul class="submenu"><li><a href="massegelist.html?status=0"><span class="menu-text">未读消息</span></a></li>';	
		}
		if(pstatus==1)
		{
		htmlstr+='  <li class="active"><a href="massegelist.html?status=1"><span class="menu-text">已读消息</span></a></li></ul></li>';
	
		}else{
			htmlstr+='  <li><a href="massegelist.html?status=1"><span class="menu-text">已读消息</span></a></li></ul></li>';	 
		}
		}
	
	
	
	if(type==21||type==22||type==23||type==24||type==25)
		{
		if(type==21)//服务发布
		{
			htmlstr+=' <li  class="active open"> <a   class="menu-dropdown">';
		}else{
			htmlstr+=' <li> <a class="menu-dropdown">'; }
		
		
		
		htmlstr+=' <i class="menu-icon glyphicon glyphicon-open"></i> <span class="menu-text"> 服务审核发布管理 </span>  <i class="menu-expand"></i></a> ';
		htmlstr+=' <ul class="submenu">';
		 
        if(pstatus==0)
        	{
        	htmlstr+=' <li class="active"><a href="fwsqdeallist.html"><span class="menu-text">待审核的服务</span></a></li> ';	
        	}else{
            htmlstr+=' <li><a href="fwsqdeallist.html"><span class="menu-text">待审核的服务</span></a></li> ';	
        	}
		if(pstatus==1)
			{
			htmlstr+=' <li class="active"><a href="fwsqdealliststatusadmin.html?status=1"><span class="menu-text">已通过审核的服务</span></a></li> ';
			}
		else{
			htmlstr+=' <li><a href="fwsqdealliststatusadmin.html?status=1"><span class="menu-text">已通过审核的服务</span></a></li> ';
		}
		if(pstatus==-1)
			{
			htmlstr+=' <li class="active"><a href="fwsqdealliststatusadmin.html?status=-1"><span class="menu-text">未通过审核的服务</span></a></li> ';
			}else{
				htmlstr+=' <li><a href="fwsqdealliststatusadmin.html?status=-1"><span class="menu-text">未通过审核的服务</span></a></li> ';
			}
		
		htmlstr+=' </ul></li>';
		if(type==22){
			htmlstr+='<li class="active"><a href="servicevisitinfo.html"> <i class="menu-icon fa fa-bar-chart-o"></i> <span class="menu-text">服务审计</span></a> </li>';
		}else{
			htmlstr+='<li><a href="servicevisitinfo.html"> <i class="menu-icon fa fa-bar-chart-o"></i> <span class="menu-text">服务审计</span></a> </li>';
		}
		
		
		if(type==23)
		{
			htmlstr+=' <li  class="active open"> <a class="menu-dropdown">';
		}else{
			htmlstr+=' <li  > <a class="menu-dropdown">';
		}
		htmlstr+='  <i class="menu-icon glyphicon glyphicon-tasks"></i> <span class="menu-text"> 服务申请授权管理 </span>  <i class="menu-expand"></i></a> ';
		if(pstatus==0)
		{
			htmlstr+='  <ul class="submenu"><li class="active"><a href="dealapplylist.html"><span class="menu-text">待授权服务</span></a></li>';
		}else{
			htmlstr+='  <ul class="submenu"><li><a href="dealapplylist.html"><span class="menu-text">待授权服务</span></a></li>';
		}
		if(pstatus==1)
		{
			htmlstr+='  <li class="active"><a href="applylistadmin.html?status=1"><span class="menu-text">已通过授权服务</span></a></li>';
		}else{
			htmlstr+='  <li><a href="applylistadmin.html?status=1"><span class="menu-text">已通过授权服务</span></a></li>';
		}
		if(pstatus==-1)
		{
			htmlstr+='  <li class="active"><a href="applylistadmin.html?status=-1"><span class="menu-text">未通过授权服务</span></a></li></ul></li>';
		}else{
			htmlstr+='  <li><a href="applylistadmin.html?status=-1"><span class="menu-text">未通过授权服务</span></a></li></ul></li>';
		}
		
		
	    
	 
		//htmlstr+='<li> <a href="#"> <i class="menu-icon fa fa-bar-chart-o"></i> <span class="menu-text">服务监控</span></a> </li>';
		if(type==25){
			htmlstr+='<li class="active"> <a href="totalechart.html"> <i class="menu-icon fa fa-bar-chart-o"></i> <span class="menu-text">服务统计</span></a> </li>';
		}else{
			htmlstr+='<li> <a href="totalechart.html"> <i class="menu-icon fa fa-bar-chart-o"></i> <span class="menu-text">服务统计</span></a> </li>';
		}
		
		
		}
	if(type==51)
		{
		htmlstr+='<li> <a href="#"> <i class="menu-icon fa fa-bar-chart-o"></i> <span class="menu-text">服务详情</span></a> </li>';
		}
	
	/*if(type==1)
		{
		htmlstr+=' <li  class="active open"> <a   class="menu-dropdown">';
		}else{
			htmlstr+=' <li  > <a   class="menu-dropdown">';
		}
	 
	htmlstr+=' <i class="menu-icon glyphicon glyphicon-home"></i> <span class="menu-text"> 服务注册发布 </span>  <i class="menu-expand"></i></a> ';
	htmlstr+='  <ul class="submenu"><li><a href="publishservice.html"><span class="menu-text">服务发布</span></a></li>';
	htmlstr+='  <li><a href="personservicelist.html"><span class="menu-text">已发布服务管理</span></a></li></ul></li>';

	htmlstr+='<li> <a href="servicelist.html"> <i class="menu-icon fa fa-desktop"></i> <span class="menu-text"> 服务资源目录 </span></a> </li>';
	if(type==2)
	{
	htmlstr+=' <li  class="active open"> <a   class="menu-dropdown">';
	}else{
		htmlstr+=' <li  > <a   class="menu-dropdown">';
	}
	if(status==1)
		{
		htmlstr+=' <i class="menu-icon glyphicon glyphicon-tasks"></i> <span class="menu-text"> 服务审批管理 </span>  <i class="menu-expand"></i></a> ';
		htmlstr+='  <ul class="submenu"><li><a href="dealapplylist.html"><span class="menu-text">待审批</span></a></li>';
		htmlstr+='  <li><a href="applylist.html?status=1"><span class="menu-text">已通过审批</span></a></li>';
		htmlstr+='  <li><a href="applylist.html?status=-1"><span class="menu-text">未通过审批</span></a></li></ul></li>';
		}
	else{
		htmlstr+=' <i class="menu-icon glyphicon glyphicon-tasks"></i> <span class="menu-text"> 服务审批管理 </span>  <i class="menu-expand"></i></a> ';
		htmlstr+='  <ul class="submenu"><li><a href="applylist.html?status=0"><span class="menu-text">待审批</span></a></li>';
		htmlstr+='  <li><a href="applylist.html?status=1"><span class="menu-text">已通过审批</span></a></li>';
		htmlstr+='  <li><a href="applylist.html?status=-1"><span class="menu-text">未通过审批</span></a></li></ul></li>';
	}

	 
	htmlstr+='<li> <a href="totalechart.html"> <i class="menu-icon fa fa-bar-chart-o"></i> <span class="menu-text"> 图表统计</span></a> </li>';
	htmlstr+='<li><a href="servicevisitinfo.html">  <i class="menu-icon fa fa-th"></i>  <span class="menu-text">服务审计 </span> </a> </li>';
	htmlstr+='<li> <a href="#"> <i class="menu-icon fa fa-th"></i> <span class="menu-text"> 服务总线 </span></a> </li>';
	htmlstr+='<li> <a href="#"> <i class="menu-icon fa fa-pencil-square-o"></i> <span class="menu-text"> 数据总线</span></a> </li>';
	htmlstr+='<li> <a href="#"> <i class="menu-icon fa fa-align-right"></i> <span class="menu-text"> 服务监控</span></a> </li>';
   */ 
	$("#leftmenuid").html("");
    $("#leftmenuid").html(htmlstr);


}