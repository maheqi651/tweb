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
				$("#uploadimage").attr("src","upload/"+result);
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
	/*if($("#acceptid").attr("checked")=='checked')
		 $("#acceptid").removeAttr("checked");
	else
		$("#acceptid").attr("checked","true");
	$("#acceptiderr").html("");
	if($("#acceptid").attr("checked")!='checked')
		{
		   $("#acceptiderr").html("请接受该系统规则！");
		}*/
}

function addService(){
    var param=new Object();
    param.NAME=$("#fwname").val();
    param.METHODNAME=$("#fwmethodname").val();
    param.INFO=$("#fwinfo").val();
    param.TYPE=$("#fwtype").val();
    param.Company=$("#fwcompany").val();
    param.Version=$("#fwversion").val();
    param.GET=$("#fwget").val();
    param.imageurl=$("#fwimage").val();
    
	if(isEmpty($("#fwget").val())||isEmpty($("#fwimage").val())||isEmpty($("#fwname").val())||isEmpty($("#fwmethodname").val())||isEmpty($("#fwinfo").val())||isEmpty($("#fwcompany").val())||isEmpty($("#fwversion").val())) 
	{   
		alert('表单填写有误，请检查您的表单是否填写完整');
		return;
	}
		$.ajax({
		type : "POST",
		url : "fg/addService.do",
		data : param,
		dataType :"json",
		type : "POST",
		cache : false,
		success : function(obj) {
			if(obj.result==1||obj.result=="1")
				{
				  alert("恭喜您，服务发布成功！");
				  window.location.reload();
				}
		}
	   });



}