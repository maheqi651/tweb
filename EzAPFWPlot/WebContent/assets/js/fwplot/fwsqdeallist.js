 
var setting1 = {
		data: {
			simpleData: {
				enable: true
			}
		},
check: {
	enable: true,
	 
}
};

var zNodes = [];
var zTreeObj ;
function loaddataResource(aid){
	/*zNodes = [];
	$("#treeDemo1").html("");
	$.ajax({
		type : "POST",
		url : "fg/findByuserandchoose.do",
		data : "user=admin&aid="+aid,
		dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
		type : "POST",
		cache : false,
		success : function(data) {
			zNodes=data.result;
			$(document).ready(function () {
				zTreeObj=$.fn.zTree.init($("#treeDemo1"), setting1,zNodes);
			});
		}});*/
}

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
			if(result.status=="1")
			{
			   statusstr="通过";
			}else if(result.status=="-1"){
				statusstr="拒绝";
			}else{
				statusstr="待审核";
			}
			var htmlstrfw="";
			htmlstrfw+="<tr><td width='10%'  style='font-weight:bold' >服务名称：</td><td width='40%'>"+result.fwname+"</td><td width='10%'  style='font-weight:bold'>接口类型：</td><td>"+result.interfacetype+"</td></tr>";
			htmlstrfw+="<tr><td width='10%'  style='font-weight:bold'>请求方法：</td><td width='40%'>"+result.methodname+"</td><td width='10%'  style='font-weight:bold'>服务介绍：</td><td><p style='white-space:normal; word-wrap:break-word;width:350px;' >"+result.fwinfo+"</P></td></tr>";
			htmlstrfw+="<tr><td width='10%'  style='font-weight:bold'>服务类型：</td><td width='40%'>"+result.fwtype+"</td><td width='10%'  style='font-weight:bold'>服务地址：</td><td><p style='white-space:normal; word-wrap:break-word;width:350px;' >"+result.fwurl+"</p></td></tr>";
			htmlstrfw+="<tr><td width='10%'  style='font-weight:bold'>帮助地址：</td><td width='40%' ><p style='white-space:normal; word-wrap:break-word;width:350px;' >"+result.fwhelpurl+"</p></td><td width='10%'  style='font-weight:bold'>示例地址：</td><td><p style='white-space:normal; word-wrap:break-word;width:350px;' >"+result.demourl+"</p></td></tr>";
			htmlstrfw+="<tr><td width='10%'  style='font-weight:bold'>注册单位：</td><td width='40%'>"+result.fwregion+"</td><td width='10%'  style='font-weight:bold'> 审核状态：</td><td>"+statusstr+" </td>";
			htmlstrfw+="</tr><tr><td width='10%'  style='font-weight:bold'>注册人：</td><td width='40%'>"+result.applyuser+"</td><td width='10%'  style='font-weight:bold'>注册时间：</td><td>"+result.applytime+"</td></tr>";
			htmlstrfw+="<tr><td width='10%'  style='font-weight:bold'> </td><td width='40%'> </td><td width='10%'  style='font-weight:bold'>服务图片：</td><td><img src='../upload/"+result.imageurl+"' width='300' height='180'></td></tr> ";
			$("#fwsqdetailtable").html("");
            $("#fwsqdetailtable").html(htmlstrfw);
            $('#myModal2').modal('toggle');
		}});
}
var functionlist;
 
function refuse(){
	 $('#myModal2').modal('hide');
	 $('#myModal3').modal('toggle');
	 
}

function js()
{
	 $('#myModal2').modal('hide');
	 $('#myModal4').modal('toggle');
}
function noaccept2(aid)
{
	document.getElementById("nopassid").disabled="";
	 
	//disabled="disabled"
	$("#aid").val(aid);
	$('#myModal3').modal('toggle');
}
function noaccept1()
{
	var aid=$("#aid").val();
	dealapply(-1,aid);
}
function accept2(aid)
{   
	document.getElementById("passid").disabled="";
	//$("#passid").attr("disabled","");
	$("#aid").val(aid);
	
	$('#myModal4').modal('toggle');
}
function accept1()
{   
	var aid=$("#aid").val();
	dealapply(1,aid);
}
function dealapply(status ,aid){
	document.getElementById("passid").disabled="disabled";
	var param=new Object();
	param.status=status;
	param.aid=aid;
	   if(status>0){
		   var jsreply=$("#jsreply").val();
			param.reply=jsreply;
			if(jsreply==null||jsreply=="")
		    {
				$("#jsreplyerror").html("审核意见不可以为空！");
				return;
		    }
	   }else{
		   var reply=$("#reply").val();
			param.reply=reply;
			if(reply==null||reply=="")
		    {
				$("#replyerror").html("审核意见不可以为空！");
				return;
		    }
	   }
		
	
		 
	
	$.ajax({
		type : "POST",
		url : "fg/dealFwSq.do",
		data : param,
		dataType :"json",// 此处只能填写json或text，否则回调函数无法正确执行
		type : "POST",
		cache : false,
		success : function(data) {
			  window.location.reload();
		}});
}
