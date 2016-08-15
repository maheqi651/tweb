function initParam(){
		var strFullPath = window.document.location.href;
		var index = strFullPath.indexOf("?");
		if(index==-1){
			return;
		}
		var params = strFullPath.substr(index+1);
		var listarr=params.split("&");//参数
		for(i=0;i<listarr.length;i++){
			var arr = listarr[i].split("=");
			if(arr[0]=="condition")
				{
				var serachname=decodeURI(decodeURI(arr[1]));
			    //alert(decodeURI(decodeURI(arr[1])));
				 $('#searchinput').val(serachname);
				 findServiceallbyType();
				 break;
				}
		   }
}
	
function loadservicedetail(servicedid){
     	
	fw(servicedid);
	window.location.href="serviceinfo.html?serviceid="+servicedid;
}

function findServiceallbyType()
{

	var searchinput=$('#searchinput').val();
	//alert(searchinput);
	var servicetype=$('#servicetype').val();
	var regiontype=$('#regiontype').val();
	var interfacetype=$('#interfacetype').val();
	var namesort=$('#namesort').val();
	var scoresort=$('#scoresort').val();
	var accessort=$('#accessort').val();
	var newsort=$('#newsort').val();
	
	var data="";
 
	$("#servicelist").html("");	
	$("#pagerrow").html("");
	$("#pagerculom").html("");
	var param = new Object();
	param.page=1;
	if(searchinput!=""&&searchinput!=null)
	{	
		param.NAME=searchinput;
		param.DESP=searchinput;
	}
		 
	if(newsort!="-1")
		param.newsort=newsort;
	if(accessort!="-1")
		param.accessort=accessort;
	if(scoresort!="-1")
		param.scoresort=scoresort;
	if(namesort!="-1")
		param.namesort=namesort;
	
	if(servicetype!="all")
		param.types=servicetype;
	if(regiontype!="all")
		param.regiontype=regiontype;
	if(interfacetype!="all")
		param.interfacetype=interfacetype;
	$.extend(param, null);
	$("#pager1").pagination(
			       "fg/findservice.do",
					param,
					function(obj) {
			    	  // alert(obj);
			    	   var html='';
						var jsonli=obj;
						var list=jsonli.result; 
						var len=list.length;
						var start=new Array(len);
						var scores=new Array(len);
						for(var ooo in list){ 
							var oo=list[ooo];
							var ezsm=oo.eZServiceMditemDEF;
                            html+='<div class="col-md-3"> <div class="thumbnail">';
                            var points=0;
							if(ezsm!=null)
							{
								var imageurl=oo.eZServiceMditemDEF.imageurl;
								if(imageurl==null||imageurl=='null')
									imageurl='../assets/img/ditu.png';
								html+='<img id="defaultimag" alt="300x200"   src="../upload/'+imageurl+'" class="img-thumbnail"   onclick="loadservicedetail(\''+oo.serviceid+'\')" style="cursor: pointer;width:300px;height:200px;"/>';
							}
							else
							   html+='<img id="defaultimag"  alt="300x200" src="image/Tulips.jpg"   onclick="loadservicedetail(\''+oo.serviceid+'\')"  class="img-thumbnail"  style="cursor: pointer;width:300px;height:200px;"/>';
							if(oo.score==null||oo.score=='null')
								{}else{
									points=oo.score;
								}
							html+='<div class="caption" style="text-align:right;">';
							html+='<p style="text-align: left; font-weight: bold; font-size: 15px;" title="'+ oo.name +'">'+dealNameStr(oo.name)+'</p> ';
							html+='<span  id="start11'+ooo+'"	style="float: left"> ';
							start[ooo]="start11"+ooo;
							scores[ooo]=points;
							
							/*	for(ii=1;ii<=5;ii++)
								{
								   if(ii<=points)
									   {
									       html+='<a class="btn" style="border: 0px;"><span class="glyphicon glyphicon-star" style="color: #F9B111"></span></a>';
									   }else{
										   html+='  <a class="btn" style="border: 0px;"><span 	class="glyphicon glyphicon-star-empty" style="color: #F9B111"></span> </a>';
									   }
								}*/
							html+='</span> <span  > <a class="btn btn-blue  btn-xs " onclick="loadservicedetail(\''+oo.serviceid+'\')">';
							html+='<span class="glyphicon glyphicon-globe"></span>详情 </a> </span> </div> </div> </div>';
						}
						
					
						$("#servicelist").html(html); 
						for (var i = 0; i < len; i++)
						{
						   $("#"+start[i]).raty({path:'raty/images',readOnly:true,score:scores[i]});
						}
						var t = document.getElementsByTagName("img");
						for(i = 0; i < t.length; i++){
						      t.item(i).onerror = function(){
						        if(this.id =="defaultimag"){
						            this.src = "image/Tulips.jpg";
						            this.onerror = null;
						          }
						      }
						    }
			       }
			     );
}

function dz(serviceid){
	if(serviceid==null||serviceid=="")
		return;
	$.ajax({
		type : "POST",
		url : "fg/addservicedz.do",
		data : "serviceid="+serviceid,
		dataType : "text",// 此处只能填写json或text，否则回调函数无法正确执行
		success : function(obj) {
			findServiceallbyType();
		}});
	
	
}

function fw(serviceid){
	if(serviceid==null||serviceid=="")
		return;
	$.ajax({
		type : "POST",
		url : "fg/addservicefw.do",
		data : "serviceid="+serviceid,
		dataType : "text",// 此处只能填写json或text，否则回调函数无法正确执行
		success : function(obj) {
		}});
}
function dosort(type,obj){
	cancelcolor("sortbuton");
	obj.className="btn btn-success";
	var result="0";
	if(type==1)
		{ 
		 $('#scoresort').val("-1");
		 $('#accessort').val("-1");
		 $('#newsort').val("-1");
		var namesort=$('#namesort').val();
		if(namesort=="-1")
		{
			$('#namesort').val("0");
		}else if(namesort=="1"){
			$('#namesort').val("0");
		}else {
			$('#namesort').val("1");
		}
		
		
		}else if(type==2){
			$('#namesort').val("-1");
			 $('#scoresort').val("-1");
			 $('#newsort').val("-1");
			var accessort=$('#accessort').val();
			if(accessort=="-1")
			{
				$('#accessort').val("1");
			}else if(accessort=="1"){
				$('#accessort').val("0");
			}else {
				$('#accessort').val("1");
			}
		}else if(type==3){
			$('#namesort').val("-1");
			 $('#scoresort').val("-1");
			 $('#accessort').val("-1");
			var newsort=$('#newsort').val();
			if(newsort=="-1")
			{
				$('#newsort').val("1");
			}else if(newsort=="1"){
				$('#newsort').val("0");
			}else {
				$('#newsort').val("1");
			}
		}else if(type==4){
			$('#namesort').val("-1");
			 $('#accessort').val("-1");
			 $('#newsort').val("-1");
			var scoresort=$('#scoresort').val();
			if(scoresort=="-1")
			{
				$('#scoresort').val("1");
			}else if(scoresort=="1"){
				$('#scoresort').val("0");
			}else {
				$('#scoresort').val("1");
			}
		}else{}
	
	findServiceallbyType();
}

function cancelcolor(str){
	   var t = document.getElementsByTagName("button");
		for(i = 0; i < t.length; i++){
			   if(t.item(i).id ==str){
				  // alert(t.item(i).id);
				   t.item(i).className="btn btn-default";
		          }
		     }
	}
function changetype(type,obj){
	cancelcolor("servicetypebuton");
	obj.className="btn btn-primary";
	$("#servicetype").val(type);
	findServiceallbyType();
}

function changeregiontype(type,obj){
	cancelcolor("regionbuton");
	obj.className="btn btn-primary";
	$("#regiontype").val(type);
	findServiceallbyType();
}
function changeinterfacetype(type,obj){
	cancelcolor("interfacebuton");
	obj.className="btn btn-primary";
	$("#interfacetype").val(type);
	findServiceallbyType();
}

function findServiceall()
{   
	$("#servicelist").html("");
	$('#searchinput').bind('keypress',function(event){
		if(event.keyCode == "13")    
		{
			findServiceallbyType();
		}
	});
	
	var param = new Object();
	var searchinput=$('#searchinput').val();
	if(searchinput!=""&&searchinput!=null)
	{	
		param.NAME=searchinput;
		param.DESP=searchinput;
	}
	param.page=1;
	$.extend(param, null);
	$("#pager1").pagination(
			       "fg/findservice.do",
					param,
					function(obj) {
			    	    var html='';
						var jsonli=obj;
						var list=jsonli.result; 
						var len=list.length;
						var start=new Array(len);
						var scores=new Array(len);
						//alert(list.length);
						for(var ooo in list){ 

							var oo=list[ooo];
							
							var ezsm=oo.eZServiceMditemDEF;
							var points=0;
							
							html+='<div class="col-md-3"> <div class="thumbnail">';
							if(ezsm!=null)
							{
								var imageurl=oo.eZServiceMditemDEF.imageurl;
								if(imageurl==null||imageurl=='null')
									imageurl='../assets/img/ditu.png';
								html+='<img id="defaultimag" alt="300x200"   src="../upload/'+imageurl+'" class="img-thumbnail"   onclick="loadservicedetail(\''+oo.serviceid+'\')" style="cursor: pointer;width:300px;height:200px;"/>';
								 
							}
							else
							   html+='<img id="defaultimag"  alt="300x200" src="image/Tulips.jpg"   onclick="loadservicedetail(\''+oo.serviceid+'\')"  class="img-thumbnail"  style="cursor: pointer;width:300px;height:200px;"/>';
							
							/*if(ezsm!=null)
							{
								var imageurl=oo.eZServiceMditemDEF.imageurl;
								if(imageurl==null||imageurl=='null')
									imageurl='../assets/img/ditu.png';
								html+='<img id="defaultimag" alt="300x200" width="300" height="200" src="upload/'+imageurl+'" class="img-thumbnail" />';
								 
							}
							else
							   html+='<img id="defaultimag"  alt="300x200" src="image/Tulips.jpg" width="300" height="200" class="img-thumbnail" />';
							*/if(oo.score==null||oo.score=='null')
								{}else{
									points= oo.score;
								}
							html+='<div class="caption" style="text-align:right;">';
							html+='<p style="text-align: left; font-weight: bold; font-size: 15px;" title="'+ oo.name +'">'+dealNameStr(oo.name)+'</p> ';
							html+='<span  id="start11'+ooo+'"	style="float: left"> ';
							start[ooo]="start11"+ooo;
							scores[ooo]=points;
							/*	html+='<span class="btn-group btn-group-xs" role="group" 	style="float: left"> ';
							for(ii=1;ii<=5;ii++)
								{
								   if(ii<=points)
									   {
									       html+='<a class="btn" style="border: 0px;"><span class="glyphicon glyphicon-star" style="color: #F9B111"></span></a>';
										 
									   }else{
										   html+='  <a class="btn" style="border: 0px;"><span 	class="glyphicon glyphicon-star-empty" style="color: #F9B111"></span> </a>';
											 
									   }
								}*/
							
							
							html+='</span> <span  > <a class="btn btn-blue  btn-xs " onclick="loadservicedetail(\''+oo.serviceid+'\')">';
							html+='<span class="glyphicon glyphicon-globe"></span>详情 </a> </span> </div> </div> </div>';
						}
						$("#servicelist").html(html);
						for (var i = 0; i < len; i++)
						{
						   $("#"+start[i]).raty({path:'raty/images',readOnly:true,score:scores[i]});
						}
						var t = document.getElementsByTagName("img");
						for(i = 0; i < t.length; i++){
							 
						      t.item(i).onerror = function(){
						        if(this.id =="defaultimag"){
						            this.src = "image/Tulips.jpg";
						            this.onerror = null;
						          }
						      }
						   }
						 
			       }
			     );

}

function dealNameStr(namestr)
{
	if(namestr==null)
		{}else{
			if(namestr.length>10)
				{
				 
				  namestr=namestr.substr(0,10)+" ...";
				}
		}
	return namestr;
}



