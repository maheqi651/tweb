
function loadmenu(type){
	
	htmlstr='';
	//htmlstr+='<li class="active"><a href="index.html"><i class="menu-icon glyphicon glyphicon-home"></i> <span class="menu-text">服务注册发布</span> </a> </li>';
	
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
	/*htmlstr+='<li><a href="#" class="menu-dropdown"> <i class="menu-icon fa fa-desktop"></i><span class="menu-text"> 服务资源目录 </span> <i class="menu-expand"></i> </a>';
	htmlstr+='<ul class="submenu">';
 
	htmlstr+='<li><a href="servicelist.html" class="menu-dropdown"> <span class="menu-text">服务接口列表 </span> <i class="menu-expand"></i> </a> <ul class="submenu">';
	htmlstr+='<li> <a href="font-awesome.html">  <i class="menu-icon fa fa-rocket"></i> <span class="menu-text">访问最高</span></a> </li> ';
	htmlstr+='<li><a href="font-awesome.html"><i class="menu-icon fa fa-rocket"></i><span class="menu-text">评分最高</span></a>';
	htmlstr+='</li><li><a href="font-awesome.html"> <i class="menu-icon fa fa-rocket"></i><span class="menu-text">已发布服务</span></a></li>';
	htmlstr+='</ul></li>';
	htmlstr+=' </ul> </li>';*/
	
	
	if(type==2)
	{
	htmlstr+=' <li  class="active open"> <a   class="menu-dropdown">';
	}else{
		htmlstr+=' <li  > <a   class="menu-dropdown">';
	}
	 
	htmlstr+=' <i class="menu-icon glyphicon glyphicon-tasks"></i> <span class="menu-text"> 服务审批管理 </span>  <i class="menu-expand"></i></a> ';
	htmlstr+='  <ul class="submenu"><li><a href="dealapplylist.html"><span class="menu-text">待审批</span></a></li>';
	htmlstr+='  <li><a href="applylist.html?status=1"><span class="menu-text">已通过审批</span></a></li>';
	htmlstr+='  <li><a href="applylist.html?status=-1"><span class="menu-text">未通过审批</span></a></li></ul></li>';
	 
	htmlstr+='<li> <a href="totalechart.html"> <i class="menu-icon fa fa-bar-chart-o"></i> <span class="menu-text"> 图表统计</span></a> </li>';
	htmlstr+='<li><a href="servicevisitinfo.html">  <i class="menu-icon fa fa-th"></i>  <span class="menu-text">服务审计 </span> </a> </li>';
	htmlstr+='<li> <a href="#"> <i class="menu-icon fa fa-th"></i> <span class="menu-text"> 服务总线 </span></a> </li>';
	htmlstr+='<li> <a href="#"> <i class="menu-icon fa fa-pencil-square-o"></i> <span class="menu-text"> 数据总线</span></a> </li>';
	htmlstr+='<li> <a href="#"> <i class="menu-icon fa fa-align-right"></i> <span class="menu-text"> 服务监控</span></a> </li>';
    $("#leftmenuid").html("");
    $("#leftmenuid").html(htmlstr);
}