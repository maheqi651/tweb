 
function appli(ids){
	$("#aid").val(ids);
	$.ajax({
		type : "POST",
		url : "fg/findFwSqById.do",
		data : "ids="+ids,
		dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
		type : "POST",
		cache : false,
		success : function(data) {
			var result=data.result;
			var htmlstrfw="";
			var statusstr="";
			if(result.status=="1")
				{
				   statusstr="通过";
				}else if(result.status=="-1"){
					statusstr="拒绝";
				}else{
					statusstr="待审核";
				}
			htmlstrfw+="<tr><td width='10%' style='font-weight:bold'>服务名称：</td><td width='40%'>"+result.fwname+"</td><td width='10%' style='font-weight:bold'>接口类型：</td><td>"+result.interfacetype+"</td></tr>";
			htmlstrfw+="<tr><td width='10%' style='font-weight:bold'>请求方法：</td><td width='40%'>"+result.methodname+"</td><td width='10%' style='font-weight:bold'>服务介绍：</td><td><p style='white-space:normal; word-wrap:break-word;width:350px;' >"+result.fwinfo+"</p></td></tr>";
			htmlstrfw+="<tr><td width='10%' style='font-weight:bold'>服务类型：</td><td width='40%'><p style='white-space:normal; word-wrap:break-word;width:350px;'  >"+result.fwtype+"</p></td><td width='10%'  style='font-weight:bold'>服务地址：</td><td><p style='white-space:normal; word-wrap:break-word;width:350px;'>"+result.fwurl+"</p></td></tr>";
			htmlstrfw+="<tr><td width='10%' style='font-weight:bold'>帮助地址：</td><td width='40%'><p style='white-space:normal; word-wrap:break-word;width:350px;' >"+result.fwhelpurl+"</p></td><td width='10%'  style='font-weight:bold'>示例地址：</td><td><p style='white-space:normal; word-wrap:break-word;width:350px;'>"+result.demourl+"</p></td></tr>";
			htmlstrfw+="<tr><td width='10%' style='font-weight:bold'>注册单位：</td><td width='40%'>"+result.fwregion+"</td><td width='10%' style='font-weight:bold'> 审核状态：</td><td>"+statusstr+" </td>";
			htmlstrfw+="</tr><tr><td width='10%' style='font-weight:bold'>注册人：</td><td width='40%'>"+result.applyuser+"</td><td width='10%' style='font-weight:bold'>注册时间：</td><td>"+result.applytime+"</td></tr>";
			htmlstrfw+="<tr><td width='10%' style='font-weight:bold'> </td><td width='40%'> </td><td width='10%' style='font-weight:bold'>服务图片：</td><td><img src='../upload/"+result.imageurl+"' width='300' height='180'></td></tr> ";
			$("#fwsqdetailtable").html("");
            $("#fwsqdetailtable").html(htmlstrfw);
            $('#myModal2').modal('toggle');
		}});
	 
}
 
 
 