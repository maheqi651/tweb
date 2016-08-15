 
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
	zNodes = [];
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
		}});
}

function appli(name,info,servicecode,aid,methodname)
{
	$("#servicename").val(name);
	$("#servicedesp").val(info);
	$("#aid").val(aid);
	
	loadapp(servicecode);
	if(methodname=="QueryData")
	{
	// alert();
		$("#treedivid").css("display","");
		$("#modalbodyid").removeClass("col-lg-10");
		$("#modalbodyid").addClass("col-lg-8");
		$("#modal2div").css("width","80%");
		//alert(1);
		loaddataResource(aid);
	}else{
		$("#treedivid").css("display","none");
		$("#modalbodyid").removeClass("col-lg-8");
		$("#modalbodyid").addClass("col-lg-10");
		$("#modal2div").css("width","50%");
	}
    $('#myModal2').modal('toggle');
}
var functionlist;
function loadapp(code){
	$.ajax({
		type : "POST",
		url : "fg/findFunctionCByCode.do",
		data : "code="+code,
		dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
		type : "POST",
		cache : false,
		success : function(data) {
			var result=data.result;
			$("#appname").val(result.name);
			$("#appinfo").val(result.description);
			$("#appaddress").val(result.address);
			$("#appphone").val(result.phone);
		}});
}
 
 
 
