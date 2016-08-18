(function($){
	$.fn.pagination = function(url,params, handler){
		var param = {};
		$.extend(param, params);
		if(param.page==undefined)
			$.extend(param, {page : 1});
		var obj = this;
		
		function getPagerContent(page,pagenum){
			var pcontent = "";
			/*if(page>=3){
				pcontent += "<li><a href=\"javascript:void(0)\">&lt</a></li>";
				pcontent += "<li><a href=\"javascript:void(0)\">"+(page-2)+"</a></li>";
				pcontent += "<li><a href=\"javascript:void(0)\">"+(page-1)+"</a></li>";
				pcontent += "<li class=\"active\"><a href=\"javascript:void(0)\">"+page+"</a></li>";
				pcontent += "<li><a href=\"javascript:void(0)\">"+(page+1)+"</a></li>";
				pcontent += "<li><a href=\"javascript:void(0)\">"+(page+2)+"</a></li>";
				pcontent += "<li><a href=\"javascript:void(0)\">&gt</a></li>";
			}else if(page==1){
				pcontent += "<li class=\"disabled\"><a href=\"javascript:void(0)\">&lt</a></li>";
				pcontent += "<li class=\"active\"><a href=\"javascript:void(0)\">"+page+"</a></li>";
				pcontent += "<li><a href=\"javascript:void(0)\">"+(page+1)+"</a></li>";
				pcontent += "<li><a href=\"javascript:void(0)\">"+(page+2)+"</a></li>";
				pcontent += "<li><a href=\"javascript:void(0)\">"+(page+3)+"</a></li>";
				pcontent += "<li><a href=\"javascript:void(0)\">"+(page+4)+"</a></li>";
				pcontent += "<li><a href=\"javascript:void(0)\">&gt</a></li>";
			}else if(page==2){
				pcontent += "<li><a href=\"javascript:void(0)\">&lt</a></li>";
				pcontent += "<li><a href=\"javascript:void(0)\">"+(page-1)+"</a></li>";
				pcontent += "<li class=\"active\"><a href=\"javascript:void(0)\">"+page+"</a></li>";
				pcontent += "<li><a href=\"javascript:void(0)\">"+(page+1)+"</a></li>";
				pcontent += "<li><a href=\"javascript:void(0)\">"+(page+2)+"</a></li>";
				pcontent += "<li><a href=\"javascript:void(0)\">"+(page+3)+"</a></li>";
				pcontent += "<li><a href=\"javascript:void(0)\">&gt</a></li>";
			}
			*/
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
			return pcontent;
		}
		
		function initEvent(){
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
//						$(obj).find("div input").val(curpage);
//						$(this).next().find("a").text(curpage);
						document.getElementById('loadsmart').style.visibility='visible';
						$.extend(param, {page : curpage});
						pageAjax();
						document.getElementById('loadsmart').style.visibility='hidden';
					});
					break;
				case 11:
					$(this).bind("click", function() {
						if($(this).hasClass("disabled")){
							return;
						}
						var curpage = parseInt($(obj).find("ul li.active a").text());
//						var pagecount = parseInt($(obj).find(".pagecount").text());
						++curpage;
//						if (curpage >= pagecount) {
//							curpage = pagecount;
//						}
//						$(obj).find("div input").val(curpage);
//						$(this).prev().find("a").text(curpage);
//						$(this).find("ul").html(getPagerContent(curpage));
						document.getElementById('loadsmart').style.visibility='visible';
						$.extend(param, {page : curpage});
						pageAjax();
						document.getElementById('loadsmart').style.visibility='hidden';
					});
					break;
				default:
					$(this).bind("click", function() {
						if($(this).hasClass("disabled")){
							return;
						}
						var curpage = parseInt($(this).find("a").text());
	//					$(this).find("ul").html(getPagerContent(curpage));
	//					$(obj).find("div input").val(curpage);
						document.getElementById('loadsmart').style.visibility='visible';
						$.extend(param, {page : curpage});
						
						pageAjax();
						document.getElementById('loadsmart').style.visibility='hidden';
					});
					break;
				}
			});
		}
		
		
		/*$(this).find("div button").bind("click",function(){
			if($(this).attr("disabled")=="disabled"){
				return;
			}
			var curpage = parseInt($(obj).find("div input").val());
			$(obj).find("div input").val(curpage);
			$.extend(param, {page : curpage});
			pageAjax();
		});*/
		
		function pageAjax() {
			$.ajax({
				url : url,
				async : true,
				data : param,
				dataType : "json",
				type : "POST",
				cache : false,
				success : function(data) {
//					if(data.pagenum > 0){
//						$(obj).find("ul li").removeClass("disabled");
//						$(obj).removeAttr("disabled");
//						$(obj).find(".pagecount").text(data.pagenum);
//					}else if(data.pagenum==0){
//						$(obj).find("ul li").each(function(){
//							if(!$(this).hasClass("disabled")){
//								$(this).addClass("disabled");
//							}
//						});
//						$(obj).attr("disabled","disabled");
//						return;
//					}
					//alert(data.pagenum);
					$(obj).find("ul").html(getPagerContent(param.page,data.pagenum));
					initEvent();
					handler(data);
				}
			});
		}
		
		pageAjax();
	};
})(jQuery);