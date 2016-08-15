var dicmap={"资源目录服务":"Res","数据访问服务":"RBSP","非结构化服务":"NoSQL","分析类服务":"Analyse" 
		 ,"服务监控接口":"Monitor"}; 
    	 


function findservicedetail(serviceid){
	$.ajax({
		type : "POST",
		url : "fg/findFwSqById.do",
		data : "ids="+serviceid,
		dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
		type : "POST",
		cache : false,
		success : function(obj) {
			var result=obj.result;
			 $("#fwname").val(result.fwname);
		     $("#fwmethod").val(result.methodname);
		     $("#fwinfo").val(result.fwinfo);
		     $("#interfacetype").val(result.interfacetype);
		     if(result.imageurl!=null)
		     {
		    	 if(result.imageurl.endsWith(".jpg"))
					{
						$("#uploadimage").attr("src","../upload/"+result.imageurl);
						document.getElementById("objDiv").style.display="block";
					}
			     $("#fwimage").val(result.imageurl); 
		     }
		     $("#fwtype").val(result.fwtype);
		     $("#fwurl").val(result.fwurl);
		     $("#fwhelpurl").val(result.fwhelpurl);
		     $("#demourl").val(result.demourl);
		     $("#fwregion").val(result.fwregion);
		     $("#sids").val(result.id);
		     $("#acceptid").attr("checked","true");
		}
	});
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
function loadupload(){
	document.getElementById("objDiv").style.display="none";
	$("#fwimage").val("");
	$.ajaxFileUpload({
		url:"fg/upload.do",
		secureuri:false,                        
		fileElementId:'ajaxfile',            
		dataType:'json',                        
		success:function(obj, status){        
			var result=obj.result;
			if(result.endsWith(".jpg"))
			{
				$("#uploadimage").attr("src","../upload/"+result);
				document.getElementById("objDiv").style.display="block";
			}
			$("#fwimage").val(result);
		} 
	});
}
function fwnameerr()
{
	$("#fwnameerr").html("");
	if(isEmpty($("#fwname").val()))
		{
		   $("#fwnameerr").html("服务名称不可以为空！");
		}
}

function fwmethodnameerr()
{
	$("#fwmethodnameerr").html("");
	if(isEmpty($("#fwmethodname").val()))
		{
		   $("#fwmethodnameerr").html("请求参数不可以为空！");
		}
}


function fwinfoerr()
{
	$("#fwinfoerr").html("");
	if(isEmpty($("#fwinfo").val()))
		{
		   $("#fwinfoerr").html("服务介绍不可以为空！");
		}
}

function fwcompanyerr()
{
	$("#fwcompanyerr").html("");
	if(isEmpty($("#fwcompany").val()))
		{
		   $("#fwcompanyerr").html("服务连接地址不可以为空！");
		}
}

function fwversionerr()
{
	$("#fwversionerr").html("");
	if(isEmpty($("#fwversion").val()))
		{
		   $("#fwversionerr").html("服务版本号不可以为空！");
		}
}

function fwgeterr()
{
	$("#fwgeterr").html("");
	if(isEmpty($("#fwget").val()))
		{
		   $("#fwgeterr").html("服务请求方式不可以为空！");
		}
}

function acceptiderr()
{   
	if($("#acceptid").attr("checked")=='checked')
		 $("#acceptid").removeAttr("checked");
	else
		$("#acceptid").attr("checked","true");
	$("#acceptiderr").html("");
	if($("#acceptid").attr("checked")!='checked')
		{
		   $("#acceptiderr").html("请接受该系统规则！");
		}
}

function updateservice(){
	var param=new Object();
    param.FWNAME=$("#fwname").val();
    param.METHODNAME=$("#fwmethod").val();
    param.FWINFO=$("#fwinfo").val();
    param.FWTYPE=$("#fwtype").val();
    param.INTERFACETYPE=$("#interfacetype").val();
    param.FWURL=$("#fwurl").val();
    param.FWHELPURL=$("#fwhelpurl").val();
    param.DEMOURL=$("#demourl").val();
    param.IMAGEURL=$("#fwimage").val();
    param.ID=$("#sids").val();
    param.FWREGION=$("#fwregion").val();
    if($("#acceptid").attr("checked")=='checked')
    	 param.PUBLISHTYPE="1";
    else
    	 param.PUBLISHTYPE="0";
	if(isEmpty($("#fwurl").val())||isEmpty($("#fwimage").val())||isEmpty($("#fwname").val())||isEmpty($("#fwmethod").val())||isEmpty($("#fwinfo").val())||isEmpty($("#fwurl").val())||isEmpty($("#demourl").val())) 
	{   
		alert('表单填写有误，请检查您的表单是否填写完整');
		return;
	}
		 
		$.ajax({
		type : "POST",
		url : "fg/updateSqFwTable.do",
		data : param,
		dataType :"json", 
		type : "POST",
		cache : false,
		success : function(obj) {
			if(obj==null)
				{
				  alert("服务更新失败！");
				  return ;
				}
			if(obj.result==1||obj.result=="1")
				{
				 $('#myModal6').on('hide.bs.modal', function () {
					 window.location.href="fwsqdealliststatus.html?status=0";
				      });
				$('#myModal6').modal('toggle');
				  
				  
				}
		}
	   });



}