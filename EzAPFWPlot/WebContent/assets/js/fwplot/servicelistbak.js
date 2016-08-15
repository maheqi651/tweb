


function findServiceallbyType()
{

	var searchinput=$('#searchinput').val();
	var servicetype=$('#servicetype').val();
	var data="";
	if(searchinput!=""&&searchinput!=null)
		data+="NAME="+searchinput+"&DESP="+searchinput;
	if(servicetype!="all")
		if(data=="")
			data+="types="+servicetype;
		else
			data+="&types="+servicetype;
	$("#servicelist").html("");	
 
	$.ajax({
		type : "POST",
		url : "fg/findservice.do",
		data : data,
		dataType : "text",// 此处只能填写json或text，否则回调函数无法正确执行
		success : function(obj) {
			var html='';
			var jsonli=$.parseJSON(obj);
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
						imageurl='assets/img/ditu.png';
					html+='<img style="width: 100px; height: 60px;" src="'+imageurl+'">';
				}
				else
					html+='<img style="width: 100px; height: 60px;" src="assets/img/ditu.png">';	
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
				html+=' <button class="btn btn-danger  btn-xs"  onclick="dz(\''+oo.serviceid+'\')">点     赞</button>';
				html+='<button class="btn btn-primary   btn-xs"  onclick="appli()">申请访问</button>';
				html+='<button class="btn btn-xs  btn-success"  >查看详情</button>';
				html+='</div> </p></span> </div> </div> </div> <hr>	';
			}
			$("#servicelist").html(html);
		}
	});
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




function changetype(type){
	$("#servicetype").val(type);
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

	$.ajax({
		type : "POST",
		url : "fg/findservice.do",
		data : "",
		dataType : "text",// 此处只能填写json或text，否则回调函数无法正确执行
		success : function(obj) {
			var html='';
			var jsonli=$.parseJSON(obj);
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
						imageurl='assets/img/ditu.png';
					html+='<img style="width: 100px; height: 60px;" src="'+imageurl+'">';
				}
				else
					html+='<img style="width: 100px; height: 60px;" src="assets/img/ditu.png">';	
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
				html+=' <button class="btn btn-danger  btn-xs"  onclick="dz(\''+oo.serviceid+'\')">点     赞</button>';
				html+='<button class="btn btn-primary   btn-xs"  onclick="appli()">申请访问</button>';
				html+='<button class="btn btn-xs  btn-success"  >查看详情</button>';
				html+='</div> </p></span> </div> </div> </div> <hr>	';
			}
			$("#servicelist").html(html);

		}
	}); 




}