 
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

function appli(name,info,servicecode,aid){
	$("#servicename").val(name);
	$("#servicedesp").val(info);
	$("#aid").val(aid);
	loadapp(servicecode);
	loaddataResource(aid);
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
function refuse(){
	 $('#myModal2').modal('hide');
	 $('#myModal3').modal('toggle');
	 
}
function noaccept2(aid)
{
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
	$("#aid").val(aid);
	$('#myModal6').modal('toggle');
	//dealapply(1,aid);
}
function accept1()
{   var aid=$("#aid").val();
	dealapply(1,aid);
}
function dealapply(status ,aid){
	var param=new Object();
	param.status=status;
	param.aid=aid;
	if(status<0){
		var reply=$("#reply").val();
		param.reply=reply;
		if(reply==null||reply=="")
	    {
			$("#replyerror").html("请填写审批意见！");
			return;
	    }
	}
		
	else{
		
		
	}
	$.ajax({
		type : "POST",
		url : "fg/dealapply.do",
		data : param,
		dataType :"json",// 此处只能填写json或text，否则回调函数无法正确执行
		type : "POST",
		cache : false,
		success : function(data) {
			  window.location.reload();
		}});
}
