
function findservicedetail(serviceid){
	$.ajax({
		type : "POST",
		url : "fg/findserviceByid.do",
		data : "serviceid="+serviceid,
		dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
		type : "POST",
		cache : false,
		success : function(obj) {
			var result=obj.result;
			var html1='';
			html1+='<span class="user-info"><p>服务名称：'+result.name+' </p></span>';
			var eZServiceMditemDEF=result.eZServiceMditemDEF;
			if(eZServiceMditemDEF!=null)
			{
			html1+='<span class="user-info"> <p>服务提供者： '+NulltoStr(eZServiceMditemDEF.user)+'      发布时间：'+NulltoStr(eZServiceMditemDEF.time)+'</p>   </span>';
			html1+='<span class="user-info"><p> 访问次数：'+eZServiceMditemDEF.acount+'   点赞：'+eZServiceMditemDEF.dz+'</p></span>';
			if(eZServiceMditemDEF.imageurl!=null)
			$("#serviceimage").attr("src","upload/"+eZServiceMditemDEF.imageurl);
			}
			html1+='<span class="user-info"><p> 服务类型：'+result.type+' &nbsp;';
			//html1+='	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
			html1+='	<button class="btn btn-success   btn-xs" ';
				html1+= 'style="float:right" onclick=\'appli(\"'+result.name+'\",\"'+result.info+'\",\"'+result.serviceid+'\")\'>申请访问</button>';
            $("#jbxx").html(html1);
            $("#gnsm").html("服务描述："+result.info);
            var html2='';
            if(eZServiceMditemDEF!=null){
            	 html2+='<p>请求方式：'+NulltoStr(eZServiceMditemDEF.get)+'</p> ';
            	 html2+='<p>服务版本：'+NulltoStr(eZServiceMditemDEF.version)+' </p> ';
                 html2+='<p>服务地址：'+NulltoStr(eZServiceMditemDEF.company)+'</p>';
            }
            $("#fwxx").html(html2);
		}
	});
}

function NulltoStr(str){
	if(str==null)
		return "无";
	return str;
}
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
			if(arr[0]=="serviceid")
				{
				 findservicedetail(decodeURI(decodeURI(arr[1])));
				 break;
				}
		   }
}
	
