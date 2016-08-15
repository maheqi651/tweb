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
				 $('#searchinput').val(decodeURI(decodeURI(arr[1])));
				 findServiceallbyuser();
				 break;
				}
		   }
}

function loadservicedetail(servicedid){
	window.location.href="serviceinfo.html?serviceid="+servicedid;
}

function updateservice(servicedid){
	window.location.href="updateservice.html?serviceid="+servicedid;
}
function findServiceallbyuser()
{

	var searchinput=$('#searchinput').val();
	var servicetype=$('#servicetype').val();
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
		 
	if(servicetype!="all")
		param.types=servicetype;
	$.extend(param, null);
	$("#pager1").pagination(
			       "fg/findservicebyuser.do",
					param,
					function(obj) {
			    	  // alert(obj);
			    	   var html='';
						var jsonli=obj;
						var list=jsonli.result; 
						for(var ooo in list){ 

							var oo=list[ooo];
							var ezsm=oo.eZServiceMditemDEF;
							html+='<div class="row">';
							html+='<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">';
							html+='<div class="user-thumb">';
							if(ezsm!=null)
							{
								var imageurl=oo.eZServiceMditemDEF.imageurl;
								if(imageurl==null||imageurl=='null')
									imageurl='../assets/img/ditu.png';
								html+='<img style="width: 175px; height: 85px;" src="upload/'+imageurl+'">';
							}
							else
								html+='<img style="width: 175px; height: 85px;" src="assets/img/ditu.png">';	
							html+='</div>';
							html+='</div>';
							html+='<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">';
							html+='<div class="article-post">';
							html+='<span class="user-info"><p>服务名称：'+oo.name+'</p></span> ';
							html+='<span class="user-info"><p>服务说明：'+oo.info+'</p></span> ';
							html+='<span class="user-info"><p>';
							if(ezsm!=null)
							{ 
								var acount=oo.eZServiceMditemDEF.acount;
								if(acount==null||acount=='null')
									acount=0;
								var dz=oo.eZServiceMditemDEF.dz;
								if(dz==null||dz=='null')
									dz=0;
								html+='访问次数：'+ acount+' 点赞：'+dz+'  ';
							}else
								html+='访问次数：0  点赞：0';	
							html+=' <div class="btn-group btn-group-xs" role="group"  style="float:right">';
							html+='<a href="#" class="btn btn-warning btn-xs edit" onclick="updateservice(\''+oo.serviceid+'\')"><i class="fa fa-edit"></i> 更新服务</a>';
							html+='<button class="btn btn-xs  btn-success"  onclick="loadservicedetail(\''+oo.serviceid+'\')">查看详情</button>';
							html+='</div> </p></span> </div> </div> </div> <hr>	';
						}
						$("#servicelist").html(html);  
			       }
			     );
}


function changetype(type){
	$("#servicetype").val(type);
	findServiceallbyuser();
}

function findServiceall()
{   
	$("#servicelist").html("");
	$('#searchinput').bind('keypress',function(event){
		if(event.keyCode == "13")    
		{
			findServiceallbyuser();
		}
	});

	var param = new Object();
	param.page=1;
	$.extend(param, null);
	$("#pager1").pagination(
			       "fg/findservicebyuser.do",
					param,
					function(obj) {
			    	   //alert(obj);
			    	   var html='';
						var jsonli=obj;
						var list=jsonli.result; 
						for(var ooo in list){ 

							var oo=list[ooo];
							var ezsm=oo.eZServiceMditemDEF;
							html+='<div class="row">';
							html+='<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">';
							html+='<div class="user-thumb">';
							if(ezsm!=null)
							{
								var imageurl=oo.eZServiceMditemDEF.imageurl;
								if(imageurl==null||imageurl=='null')
									imageurl='../assets/img/ditu.png';
								html+='<img style="width: 175px; height: 85px;" src="upload/'+imageurl+'">';
							}
							else
								html+='<img style="width: 175px; height: 85px;" src="assets/img/ditu.png">';	
							html+='</div>';
							html+='</div>';
							html+='<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">';
							html+='<div class="article-post">';
							html+='<span class="user-info"><p>服务名称：'+oo.name+'</p></span> ';
							html+='<span class="user-info"><p>服务说明：'+oo.info+'</p></span> ';
							html+='<span class="user-info"><p>';
							if(ezsm!=null)
							{ 
								var acount=oo.eZServiceMditemDEF.acount;
								if(acount==null||acount=='null')
									acount=0;
								var dz=oo.eZServiceMditemDEF.dz;
								if(dz==null||dz=='null')
									dz=0;
								html+='访问次数：'+ acount+' 点赞：'+dz+'  ';
							}else
								html+='访问次数：0  点赞：0';	
							html+=' <div class="btn-group btn-group-xs" role="group"  style="float:right">';
							
							html+='<a href="#" class="btn btn-warning btn-xs edit" onclick="updateservice(\''+oo.serviceid+'\')"><i class="fa fa-edit"></i> 更新服务</a>';
							html+='<button class="btn btn-xs  btn-info"  onclick="loadservicedetail(\''+oo.serviceid+'\')">查看详情</button>';
							html+='</div> </p></span> </div> </div> </div> <hr>	';
						}
						$("#servicelist").html(html);  
			       }
			     );

}