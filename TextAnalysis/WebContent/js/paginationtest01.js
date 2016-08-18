	    var obj=new Object();
	    var param=new Object();
	    var pagesize=10;
	    $.extend(param, {page : 1});
		function getPagerContent(page,pagenum,types){
			if(types=='01')
			{
				obj=$("#pager1");
			}
			else if(types=='02'){
				obj=$("#pager2");
			}
			else if(types=='03'){
				obj=$("#pager3");
			}
			else if(types=='04'){
				obj=$("#pager4");
			}
			else if(types=='05'){
				obj=$("#pager5");
			}
			else{
				obj=$("#pager1");
			}
			var pcontent = "";
 
			if(page>1)
				{
				pcontent += "<li><a href=\"javascript:void(0)\">&lt</a></li>";
				}else{
					pcontent += "<li class='disabled'><a href=\"javascript:void(0)\">&lt</a></li>";
				}
			
			var start=Math.floor((page/10))*10+1;
			//alert(page);
			var end=Math.floor(page/10)*10+10;
			if((page%10)>5||page%10==0)
			{
				start=Math.floor((page/10))*10+5;
				if(start>page)
					{start=page;
					end=page+9;
					}else{
						end=Math.floor((page/10))*10+14;
					}
			}else{
				
				if(start-4>=1)
					{
					start-=4;
					end-=4;
					}
			}
			
			for(var i=start;i<=end;i++)
				{
				if(i<=pagenum){ 
				if(i==page)
				{
					pcontent += "<li class=\"active\"><a href=\"javascript:void(0)\">"+i+"</a></li>";
				}else{
					pcontent += "<li><a href=\"javascript:void(0)\">"+i+"</a></li>";
				}
				}else{
					pcontent += "<li  class='disabled'> <a href=\"javascript:void(0)\">"+i+"</a></li>";
				}
				}
			
				
			if(page<pagenum){
				
				pcontent += "<li ><a href=\"javascript:void(0)\">&gt</a></li>";
			}else{
				
				pcontent += "<li class=\"disabled\"><a href=\"javascript:void(0)\">&gt</a></li>";
			}
		 
			$(obj).find("ul").html(pcontent);
		//	initEvent(types);
			
		}
		
		
		
		
		
		function initEvent(types){
			$(obj).find("ul li").each(function(index){
				switch(index){
				case 0:
					$(this).bind("click", function() {
						if($(this).hasClass("disabled")){
							return;
						}
						var curpage = parseInt($(obj).find("ul li.active a").text());
						--curpage;
						if (curpage <= 0) {
							curpage = 1;
						}
						$.extend(param, {page : curpage});
						pagecontent(types);
						 
					});
					break;
				case 11:
					$(this).bind("click", function() {
						if($(this).hasClass("disabled")){
							return;
						}
						var curpage = parseInt($(obj).find("ul li.active a").text());
						++curpage;
						$.extend(param, {page : curpage});
						pagecontent(types);
						 
					});
					break;
				default:
					$(this).bind("click", function() {
						if($(this).hasClass("disabled")){
							return;
						}
						var curpage = parseInt($(this).find("a").text());
	 
						$.extend(param, {page : curpage});
						
						pagecontent(types);
						 
					});
					break;
				}
			});
		}
 
		function pagecontent(types) {
			//alert();
			var pagenum=objlist.length%pagesize==0?0:1;
			pagenum=Math.floor(objlist.length/pagesize)+pagenum;
			getPagerContent(param.page,pagenum);
			var content="";
			 if(types=='01')
				{                                                                                           
				 content="<table class='table table-bordered table-striped' border='1'> <thead> <tr>  <th width='10%'>姓名</th><th align='center' width='15%'>证件号</th> <th width='10%'>性别</th><th width='20%'>案件名称</th> <th width='20%'>发案时间</th>   <th width='10%'> 案件描述</th> </tr> </thead>";
				}
			else if(types=='02'){
				content="<table class='table table-bordered table-striped' border='1'> <thead> <tr>  <th width='20%'>监所</th><th align='center' width='10%'>姓名</th> <th width='15%'>证件号</th>   <th width='15%'>入所时间</th><th width='15%'>出所时间</th><th width='15%'> 入所原因</th> </tr> </thead>";
				                                                                  //姓名，证件号，性别，监所名称，同监时间，入所原因
			}
			else if(types=='03'){
				content="<table class='table table-bordered table-striped' border='1'> <thead> <tr>  <th width='10%'>姓名</th><th align='center' width='15%'>证件号</th> <th width='10%'>性别</th>  <th width='20%'> 关系描述</th> </tr> </thead>";
				
			}
			else if(types=='04'){
				content="<table class='table table-bordered table-striped' border='1'> <thead> <tr>  <th width='10%'>姓名</th><th align='center' width='15%'>证件号</th>     <th width='25%'>住所名称</th><th width='10%'>房间号</th><th width='20%'>入住时间</th><th width='20%'>退房时间</th><th width='15%'> 个人信息</th> </tr> </thead>";
			}
		     
				int start = (param.page- 1) *pagesize;
				int end = param.page*pagesize-1;
			    for(var ii=start;ii<=(end>objlist.length?objlist.length:end);ii++){
			    	content+=objlist[ii];
			    }
			 
			 
		    content+="<tbody style='border: none'>";
			content+="</tbody></table>";
			if(types=='01')
				{
				$("#map1").html("");
				$("#map1").html(content);
				}
			else if(types=='02'){
				$("#map2").html("");
				$("#map2").html(content);
				
			}
			else if(types=='03'){
				$("#map3").html("");
				$("#map3").html(content);
				
			}
			else if(types=='04'){
				$("#map4").html("");
				$("#map4").html(content);
				
			}
		 
			else{
				$("#map1").html("");
				$("#map1").html(content);
				
			}
		}
		
		
	
