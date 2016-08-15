function loadupload(){
	document.getElementById("objDiv").style.display="none";
	var filename=$("#ajaxfile").val();
	//alert(getFileName(filename));
	//$("#ajaxfile").val(filename);
	 
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
				$("#fwimage").val(result);
				$("#fwimageerr").html("");
			}else{
				 
				$("#fwimageerr").html("文件格式不正确，请选择图片文件");
			}
			
			
		} 
	});
}
function getFileName(o){
    var pos=o.lastIndexOf("\\");
    return o.substring(pos+1);  
}
var flagpd=true;

function fwnameerr()
{
	$("#fwnameerr").html("");
	if(isEmpty($("#fwname").val()))
		{
		   $("#fwnameerr").html("服务名称不可以为空！");
		   flagpd=false;
		   return;
		}else{
			 if(isLength($("#fwname").val(),51))
				 {
				 $("#fwnameerr").html("服务名称字符长度不可超过50！");
				 return;
				 }
		}
}

function fwmethoderr()
{
	$("#fwmethoderr").html("");
	if(isEmpty($("#fwmethod").val()))
		{
		   $("#fwmethoderr").html("请求方法不可以为空！");
		   flagpd=false;
		   return;
		}else{
			 if(isLength($("#fwmethod").val(),51))
			 {
			 $("#fwmethoderr").html("请求方法字符长度不可超过50！");
			 return;
			 }
		}
}


function fwinfoerr()
{
	$("#fwinfoerr").html("");
	if(isEmpty($("#fwinfo").val()))
		{
		   $("#fwinfoerr").html("服务介绍不可以为空！");
		   flagpd=false;
		   return;
		}else{
			 if(isLength($("#fwinfo").val(),101))
			 {
			 $("#fwinfoerr").html("服务详情字符长度不可超过100！");
			 return;
			 }
		}
}

function demourlerr()
{
	$("#demourlerr").html("");
	if(isEmpty($("#demourl").val()))
		{
		   $("#demourlerr").html("服务示例地址不可以为空！");
		   flagpd=false;
		   return;
		}else{
			 if(isLength($("#demourl").val(),101))
			 {
			 $("#demourlerr").html("示例链接字符长度不可超过100！");
			 return;
			 }
		}
}

function fwurlerr()
{
	$("#fwurlerr").html("");
	if(isEmpty($("#fwurl").val()))
		{
		   $("#fwurlerr").html("服务链接不可以为空！");
		   flagpd=false;
		   return;
		}else{
			if(isLength($("#fwurl").val(),101))
			 {
			 $("#fwurlerr").html("服务链接字符长度不可超过100！");
			 return;
			 }
		}
}

function fwimageerr()
{
	 
	$("#fwimageerr").html("");
	if(isEmpty($("#fwimage").val()))
		{
		   $("#fwimageerr").html("<br>服务图片不可以为空！");
		    flagpd=false;
		    return;
		}else{
			flagpd=true;
			return;
		}
}



function fwhelpurlerr()
{
	$("#fwhelpurlerr").html("");
	if(isEmpty($("#fwhelpurl").val()))
		{
		   $("#fwhelpurlerr").html("服务帮助地址不可以为空！");
		   flagpd=false;
		   return;
		}else{
			if(isLength($("#fwhelpurl").val(),101))
			 {
				
			 $("#fwhelpurlerr").html("帮助链接字符长度不可超过100！");
			 return;
			 }
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
    param.FWNAME=$("#fwname").val();
    param.METHODNAME=$("#fwmethod").val();
    param.FWINFO=$("#fwinfo").val();
    param.FWTYPE=$("#fwtype").val();
    param.INTERFACETYPE=$("#interfacetype").val();
    param.FWURL=$("#fwurl").val();
    param.FWHELPURL=$("#fwhelpurl").val();
    param.DEMOURL=$("#demourl").val();
    param.IMAGEURL=$("#fwimage").val();
    param.FWREGION=$("#fwregion").val();
    if($("#acceptid").attr("checked")=='checked')
    	 param.PUBLISHTYPE="1";
    else
    	 param.PUBLISHTYPE="0";
	if(isEmpty($("#fwurl").val())||isEmpty($("#fwimage").val())||isEmpty($("#fwname").val())||isEmpty($("#fwmethod").val())||isEmpty($("#fwinfo").val())||isEmpty($("#fwurl").val())||isEmpty($("#demourl").val())) 
	{   
		
		return;
	}
	demourlerr();
	fwnameerr(); 
	fwmethoderr();
	fwinfoerr(); 
	fwurlerr();
	fwhelpurlerr();
	fwimageerr();
		$.ajax({
		type : "POST",
		url : "fg/addFwSq.do",
		data : param,
		dataType :"json",
		type : "POST",
		cache : false,
		success : function(obj) {
			if(obj.result==1||obj.result=="1")
				{
				 $('#myModal6').on('hide.bs.modal', function () {
					 window.location.reload(true);
				      });
				$('#myModal6').modal('toggle');
				  
				}
		}
	   });



}