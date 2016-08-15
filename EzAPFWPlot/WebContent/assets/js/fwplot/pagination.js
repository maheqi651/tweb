(function($){
	$.fn.pagination = function(url,params, handler){
		var param = {};
		$.extend(param, params);
		if(param.page==undefined)
			$.extend(param, {page : 1});
		var obj = this;
		
		function getPagerContent(page,pagenum){
			 //alert(url)
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
						 
						$.extend(param, {page : curpage});
						pageAjax();
						 
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
						pageAjax();
						 
					});
					break;
				default:
					$(this).bind("click", function() {
						if($(this).hasClass("disabled")){
							return;
						}
						var curpage = parseInt($(this).find("a").text());
						 
						$.extend(param, {page : curpage});
						
						pageAjax();
						 
					});
					break;
				}
			});
		}
		function pageAjax() {
			$.ajax({
				url : url,
				async : true,
				data : param,
				dataType : "json",
				type : "POST",
				cache : false,
				success : function(data) {
					$(obj).find("ul").html(getPagerContent(param.page,data.pagenum));
					initEvent();
					handler(data);
				}
			});
		}
		
		pageAjax();
	};
})(jQuery);