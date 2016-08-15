 
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
function loaddataResource(){
	$.ajax({
		type : "POST",
		url : "fg/findDataTree.do",
		data : "user=admin",
		dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
		type : "POST",
		cache : false,
		success : function(data) {
			zNodes=data.result;
			$(document).ready(function () {
				zTreeObj=$.fn.zTree.init($("#treeDemo1"), setting1,zNodes);
			});
		}});
}

function appli(name,info,serviceid,methodname){
	$("#serviceids").val(serviceid);
	$("#servicename").val(name);
	$("#servicedesp").val(info);
	 
	loadapp();
	
	if(methodname=="QueryData")
		{
		//alert();
		
		
		$("#treedivid").css("display","");
		$("#modalbodyid").removeClass("col-lg-10");
		$("#modalbodyid").addClass("col-lg-8");
		$("#modal2div").css("width","80%");
		//alert(1);
		loaddataResource();
		}else{
			
		}
	 $('#myModal2').on('hide.bs.modal', function () {
         window.location.reload();    
     });
    $('#myModal2').modal('toggle');
    
}
var functionlist;
function loadapp(){
	$.ajax({
		type : "POST",
		url : "fg/findFunctionCByuser.do",
		data : "user=admin",
		dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
		type : "POST",
		cache : false,
		success : function(data) {
			 var result=data.result;
			 functionlist=result;
			 var htmlstr= "<option value='no'></option>";
			 var counts=1;
			 for(var oo in result)
				 {
				     htmlstr+="<option value='"+result[oo].code+"'>"+counts+"  "+result[oo].name+"</option>";
				     counts++;
				 }
			  
			    $("#sqls").html(htmlstr);
		}});
}

function selectFunction(){
	var status= $("#sqls").val();
	if(status=="no")
	{
				$("#appname").val("");
				$("#appinfo").val("");
				$("#appaddress").val("");
				$("#appphone").val("");
				$("#appcode").val("");
				$("#appname").removeAttr("readonly");
				$("#appinfo").removeAttr("readonly");
				$("#appaddress").removeAttr("readonly");
				$("#appphone").removeAttr("readonly");
				$("#appcode").removeAttr("readonly");
	}else{
		for(var oo in functionlist)
		{
			if(functionlist[oo].code==status)
			{
				$("#appname").val(functionlist[oo].name);
				$("#appinfo").val(functionlist[oo].description);
				$("#appaddress").val(functionlist[oo].address);
				$("#appphone").val(functionlist[oo].phone);
				$("#appcode").val(functionlist[oo].code);
				
				$("#appname").attr("readonly","readonly");
				$("#appinfo").attr("readonly","readonly");
				$("#appaddress").attr("readonly","readonly");
				
				$("#appphone").attr("readonly","readonly");
				$("#appcode").attr("readonly","readonly");
				
				break;
			}
		}
	}
}
function applyService(){
	
	
	var flag=true;
	if(isEmpty($("#appphone").val()))
		{
		 flag=false;
		 $("#appphoneerror").html("联系电话不可以为空！")
		}
	if(isEmpty($("#appname").val())){
	    	flag=false;
		 $("#appnameerror").html("应用名称不可以为空！")
	}
	if(isEmpty($("#appaddress").val())){
    	flag=false;
	    $("#appaddresserror").html("应用地址不可以为空！")
    }
	if(isEmpty($("#appinfo").val())){
    	flag=false;
	    $("#appinfoerror").html("应用描述不可以为空！")
    }
    if(!flag)
    	{
    	 return;
	}else{
		$("#queren").attr("disabled","disabled");
		$("#quxiao").attr("disabled","disabled");
	}
    	
	var param=new Object();
	param.serviceid=$("#serviceids").val();
	param.NAME=$("#appname").val();
	param.DESCRIPTION=$("#appinfo").val();
	param.ADDRESS=$("#appaddress").val();
	param.PHONE=$("#appphone").val();
	param.CODE=$("#appcode").val();
	//alert(zTreeObj);
	var tablecode="";
	var themecode="";
	if(zTreeObj!=undefined)
		{
		var nodes = zTreeObj.getCheckedNodes(true);
		 
		for(var str in nodes)
		{      
			   if(nodes[str].tablecode!=null)
				   {
				   tablecode+=nodes[str].tablecode+",";
				   themecode+=nodes[str].themecode+",";
				   }
		}
		 
		} 
			param.TABLECODE=tablecode.substring(0, tablecode.length-1);
			param.THEMECODE=themecode.substring(0, themecode.length-1);
	$.ajax({
		type : "POST",
		url : "fg/addServiceApply.do",
		data : param,
		dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
		type : "POST",
		cache : false,
		success : function(data) {
			if(data.result>0)
				{
				  alert("恭喜您，您的服务申请提交成功！");
				}else{
				  alert("您的服务申请提交失败！");
				}
			  window.location.reload();
		}});
}

function findCheckNodes()
{   
	var nodes = zTreeObj.getCheckedNodes(true);
	var htmls="";
	for(var str in nodes)
		{
			   htmls+=nodes[str].tablecode+"---";
		}
	alert(htmls);

}