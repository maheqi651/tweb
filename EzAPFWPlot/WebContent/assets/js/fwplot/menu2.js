
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
	if(type==1)
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
    $("#leftmenuid").html("");
    $("#leftmenuid").html(htmlstr);


}