var curHBaseInstance = "HBase01"; 
var request=new Object();
var isNextPageRequest=false;
var nextRowkey;
var basepath = getBaseURL();
var totalpagecount=0;
var totalpagecountQHB=0;
var result=new Object();
var resultQHB=new Object();
var totalsize=0;
//var pagesize=10;

$(document).ready(function() {
	var paramOfGetTablesList = new Object();
	paramOfGetTablesList.type = "listHBases";
	 
	$.ajax({
		type : "POST",
		url : basepath + "/HBaseInstanceManagementServlet",
		data : JSON.stringify(paramOfGetTablesList),
		dataType : "json",  
		success : function(restext) {
//			alert(restext);
			for(var x in restext.result){
				
				var name = x;
				//alert(name);
				$("#hbaseInstance01").append("<option value='"+name+"'>"+name+"</option>");
				$("#hbaseInstance02").append("<option value='"+name+"'>"+name+"</option>");
			}
		}
	});
});


 

function updateHBase(habsename)
{
	 $("#MRPath03").val(resultQHB[habsename].MRPath);
	 $("#HBasename03").val(resultQHB[habsename].name);
	 $("#QuorumIP03").val(resultQHB[habsename].QuorumIP);
	 $("#QuorumPort03").val(resultQHB[habsename].QuorumPort);
	 $("#ServerIP03").val(resultQHB[habsename].ServerIP);
	 $("#username03").val(resultQHB[habsename].username);
	 $("#password03").val(resultQHB[habsename].password);
	 $("#defaultFSName03").val(resultQHB[habsename].defaultFSName);
	 $("#HadoopPath03").val(resultQHB[habsename].HadoopPath);
	 $("#MRPath03").val(resultQHB[habsename].MRPath);
	$("#warningModalLabel3").text("修改HBase实例");
	$("#warningModal3").modal('toggle');
}

function addHbaseinfo(){//添加Hbase实例
	$("#warningModalLabel2").text("添加HBase实例");
	$("#warningModal2").modal('toggle');
}
function updateHBaseInfo(){
	var requestUHB=new Object();
	requestUHB.type="updateHBase";
	requestUHB.name=$("#HBasename03").val();
	requestUHB.QuorumIP=$("#QuorumIP03").val();
	 //alert($("#QuorumPort01").val());
	requestUHB.QuorumPort=$("#QuorumPort03").val();
	requestUHB.ServerIP=$("#ServerIP03").val();
	requestUHB.username=$("#username03").val();
	requestUHB.password=$("#password03").val();
	requestUHB.defaultFSName=$("#defaultFSName03").val();
	requestUHB.HadoopPath=$("#HadoopPath03").val();
	requestUHB.MRPath=$("#MRPath03").val();
	$.ajax({
		type : "POST",
		url : basepath + "/HBaseInstanceManagementServlet",
		data : JSON.stringify(requestUHB),
		dataType : "json", 
		processData: false,
		jsonp:false, 
		success : function(resultO) {
			$("#MRPath03").val("");
			 $("#HBasename03").val("");
			 $("#QuorumIP03").val("");
			 $("#QuorumPort03").val("");
			 $("#ServerIP03").val("");
			 $("#username03").val("");
			 $("#password03").val("");
			 $("#defaultFSName03").val("");
			 $("#HadoopPath03").val("");
			 $("#MRPath03").val("");
			searchHbase();
			},
			error:function(resultO){
		    searchHbase();
			}
		});
	
	
}


function addHBaseinfo2(){
	var requestAHB=new Object();
	requestAHB.type="insertHBase";
	requestAHB.name=$("#HBasename01").val();
	requestAHB.QuorumIP=$("#QuorumIP01").val();
	 //alert($("#QuorumPort01").val());
	requestAHB.QuorumPort=$("#QuorumPort01").val();
	requestAHB.ServerIP=$("#ServerIP01").val();
	requestAHB.username=$("#username01").val();
	requestAHB.password=$("#password01").val();
	requestAHB.defaultFSName=$("#defaultFSName01").val();
	requestAHB.HadoopPath=$("#HadoopPath01").val();
	requestAHB.MRPath=$("#MRPath01").val();
	$.ajax({
		type : "POST",
		url : basepath + "/HBaseInstanceManagementServlet",
		data : JSON.stringify(requestAHB),
		dataType : "json", 
		processData: false,
		jsonp:false, 
		success : function(resultO) {
			$("#MRPath01").val("");
			 $("#HBasename01").val("");
			 $("#QuorumIP01").val("");
			 $("#QuorumPort01").val("");
			 $("#ServerIP01").val("");
			 $("#username01").val("");
			 $("#password01").val("");
			 $("#defaultFSName01").val("");
			 $("#HadoopPath01").val("");
			 $("#MRPath01").val("");
			searchHbase();
			},
			error:function(resultO){
		    searchHbase();
			}
		});
}


//实例管理查询

function searchHbase() {
	//alert("11");
	var requestQHB=new Object();
	requestQHB.hbaseInstance=$("#hbaseInstance02").val();
	//alert($("#hbaseInstance02").val());
	if($("#hbaseInstance02").val()=='ALL')
		{//listHbases
		    requestQHB.type='listHBases';
		//
		}else if($("#hbaseInstance02").val()=='请选择'){
			return;
		}else{//单一查询
			requestQHB.type='getHBaseInfo';
			requestQHB.name=$("#hbaseInstance02").val();
		}
	//alert("11"+requestQHB.type);
	postRequestQHB(requestQHB);
}



$("#searchhbaseinstance").click(function() {
	
	request.hbaseInstance=$("#hbaseInstance01").val();
	request.regex=$("#regex01").val();
	request.type= $("#type01").val();
   // alert("11"+$("#type01").val());
	postRequest(request);
});

 
function emptytable(){
	$("#getresult tbody").empty();
}

function getQualifiers(hname){
	var requestQ=new Object();
	//alert($("#hbaseInstance01").val());
	requestQ.hbaseInstance=$("#hbaseInstance01").val();
    requestQ.type= "getQualifiers";
    requestQ.hbaseTableName=hname;
	 
	$.ajax({
		type : "POST",
		url : basepath + "/StreamManagementServlet",
		data : JSON.stringify(requestQ),
		dataType : "json", 
		processData: false,
		jsonp:false, 
		success : function(resultO) {
			 
			  var resultQ=new Object();
			  resultQ = resultO.result;
			// alert(resultQ.AGE);
			
			 var popstr="";
			 
			if(resultQ!=null&&resultQ!="") 
				{//有数据
				 for(xx in resultQ){
					 
					 popstr+="<p>"+xx+":"+resultQ[xx]+"</p>";
						//alert(xx); 
						 
					 }
				//popstr="<p>AGE:Number</p><p>CSRQ:Date</p><p>NAME:String</p><p>ADDRESS:String</p><p>ID:Number</p>";
				//,,,,}
				}else{//无数据
					popstr="<p>对不起，在源数据中不存在你要查询的表字段信息</p>";
					
				}
			$("#warningModalLabel").text(hname+"表字段信息");
			$("#warningModalText").html(popstr);
			$("#warningModal").modal('toggle');
			 
			},
			error:function(resultO){
				
				$("#warningModalLabel").text(hname+"表字段信息");
				$("#warningModalText").html("<p>对不起，在源数据中不存在你要查询的表字段信息</p>");
				$("#warningModal").modal('toggle');
			}
		});
	
	 
}
function getKeys(hname){
	var requestM=new Object();
	requestM.hbaseInstance=$("#hbaseInstance01").val();
	requestM.type= "getMainQualifiers";
	requestM.hbaseTableName=hname;
	requestM.dataType="kv";

	$.ajax({
		type : "POST",
		url : basepath + "/StreamManagementServlet",
		data : JSON.stringify(requestM),
		dataType : "json", 
		processData: false,
		jsonp:false, 
		success : function(resultO) {
			  var resultM=new Object();
			  resultM = resultO.result;
			// alert(resultQ.AGE);
			 var popstr="";
			if(resultM!=null&&resultM!="") 
				{//有数据
				 for(xx in resultM){
					 if(resultM[xx]==""||resultM[xx]==null)
						 {
						    popstr+="<p>该表不存在主键</p>";
						 }else{
							 popstr+="<p>"+xx+":"+resultM[xx]+"</p>";
						 }
					
						//alert(xx); 
						 
					 }
				//popstr="<p>AGE:Number</p><p>CSRQ:Date</p><p>NAME:String</p><p>ADDRESS:String</p><p>ID:Number</p>";
				}else{//无数据
					popstr="<p>对不起，在源数据中不存在你要查询的表主键信息</p>";
					
				}
			$("#warningModalLabel").text(hname+"表主键信息");
			$("#warningModalText").html(popstr);
			$("#warningModal").modal('toggle');
			 
			},
			error:function(resultO){
				
				$("#warningModalLabel").text(hname+"表主键信息");
				$("#warningModalText").html("<p>对不起，在源数据中不存在你要查询的表主键信息</p>");
				$("#warningModal").modal('toggle');
			}
		});
}
//postRequestQHB   实例管理查询ajax请求
function emptytableQHB(){
	//alert("11");
	$("#hbaseresult tbody").empty();
}

function getHBaseInfo(habsename)//实例信息
{    
	var requestHBI=new Object();
	requestHBI.type= "getHBaseInfo";
	requestHBI.name=habsename;
	$.ajax({
		type : "POST",
		url : basepath + "/HBaseInstanceManagementServlet",
		data : JSON.stringify(requestHBI),
		dataType : "json", 
		processData: false,
		jsonp:false, 
		success : function(resultO) {
			  var resultHBI=new Object();
			  resultHBI = resultO.result;
			// alert(resultQ.AGE);
			 var popstr="";
			if(resultHBI!=null&&resultHBI!="") 
				{//有数据
				 for(xx in resultHBI){
					 if(resultHBI[xx]==""||resultHBI[xx]==null)
						 {
						    popstr+="<p>该实例不存在</p>";
						 }else{
							
							 for(xxx in resultHBI[xx])
								
							 popstr+="<p>"+xxx+":"+resultHBI[xx][xxx]+"</p>";
						 }
					// alert(popstr);
					 }
				//popstr="<p>AGE:Number</p><p>CSRQ:Date</p><p>NAME:String</p><p>ADDRESS:String</p><p>ID:Number</p>";
				}else{//无数据
					popstr="<p>该实例不存在</p>";
					
				}
			$("#warningModalLabel").text(habsename+"实例信息");
			$("#warningModalText").html(popstr);
			$("#warningModal").modal('toggle');
			},
			error:function(resultO){
				$("#warningModalLabel").text(habsename+"实例信息");
				$("#warningModalText").html("<p>该实例不存在</p>");
				$("#warningModal").modal('toggle');
			}
		});
	
	
	
	
}


function postRequestQHB(requestQHB){
	$.ajax({
		type : "POST",
		url : basepath + "/HBaseInstanceManagementServlet",
		data : JSON.stringify(requestQHB),
		dataType : "json", 
		processData: false,
		jsonp:false, 
		success : function(resultO) {
			emptytableQHB(); 
			cpageQHB=1;
			resultQHB = resultO.result;
			cpage=1;
			if(resultQHB==null ||resultQHB==""){
				 
			}
			else{
				var count=0;
				for(var xx in resultQHB){
					++count;
					
				}
				totalpagecountQHB=count;
				totalsize=count;
				//alert(count);
 			   totalpagecountQHB=(totalpagecountQHB%pagesize==0)?(totalpagecountQHB/pagesize):((totalpagecountQHB+pagesize)/pagesize);
 				totalpagecountQHB=parseInt(totalpagecountQHB);
				 //alert(totalpagecount);
				if(count==1)
					{
					//情况页码
					$("#hbasepage").empty();
					for(var x in resultQHB){
					$("#hbaseresult tbody").append("<tr><td>"+x+"</td><td>"+resultQHB[x].ServerIP+"</td> <td>"+resultQHB[x].QuorumPort+"</td> <td>"+resultQHB[x].username+"</td> <td><div class='btn-group'><a onclick='getHBaseInfo(\""+x+"\")' class='btn btn-success btn-mini' >实例详情</a> <a  onclick='updateHBase(\""+x+"\")'  class='btn btn-success btn-mini  ' >实例编辑</a></div></td> </tr> ");
					}
					}else{ 
				if(pagesize>=count)
				{
					for(var x in resultQHB){
						//alert(x);
						//<a class='btn btn-success btn-middle' >表字段</a>&nbsp;<a class='btn btn-success btn-middle' >主字段</a><div class='btn-group'>
					//alert("---"+x);
					$("#hbaseresult tbody").append("<tr><td>"+x+"</td><td>"+resultQHB[x].ServerIP+"</td> <td>"+resultQHB[x].QuorumPort+"</td> <td>"+resultQHB[x].username+"</td> <td><div class='btn-group'><a onclick='getHBaseInfo(\""+x+"\")' class='btn btn-success btn-mini' >实例详情</a> <a  onclick='updateHBase(\""+x+"\")'  class='btn btn-success btn-mini  ' >实例编辑</a></div></td> </tr> ");
				}
				}else{
					var ii=0;
					 for(var i in resultQHB)
						{
						 ++ii;
						 if(ii>pagesize)
							 {
							 break;
							 }
						 //alert(x);
						$("#hbaseresult tbody").append("<tr ><td>"+i+"</td><td>"+resultQHB[i].ServerIP+"</td> <td>"+resultQHB[i].QuorumPort+"</td> <td>"+resultQHB[i].username+"</td> <td><div class='btn-group'><a   onclick='getHBaseInfo(\""+i+"\")'  class='btn btn-success btn-mini' >实例详情</a> <a onclick='updateHBase(\""+i+"\")' class='btn btn-success btn-mini' >实例编辑</a></div></td></tr> ");
						
						}
					
					
				}
				setpageQHB();
					}
				
				}
			}
		});
	}





//数据表管理ajax请求
function postRequest(request){
	$.ajax({
		type : "POST",
		url : basepath + "/StreamManagementServlet",
		data : JSON.stringify(request),
		dataType : "json", 
		processData: false,
		jsonp:false, 
		success : function(resultO) {
			emptytable(); 
			result = resultO.result;
			cpage=1;
			if(result==null ||result==""){
				 
			}
			else{
				
				totalpagecount=result.length;
				totalpagecount=(totalpagecount%pagesize==0)?(totalpagecount/pagesize):((totalpagecount+pagesize)/pagesize);
				totalpagecount=parseInt(totalpagecount);
				//alert(totalpagecount);
				if(pagesize>=result.length)
				{
					for(var x in result){
						//<a class='btn btn-success btn-middle' >表字段</a>&nbsp;<a class='btn btn-success btn-middle' >主字段</a><div class='btn-group'>
					//alert("---"+x);
					$("#getresult tbody").append("<tr><td>"+result[x].name+"</td> <td>"+result[x].size+"B </td> <td>"+result[x].lastModification+"</td> <td>"+result[x].owner+"</td><td>"+result[x].lastModification+"</td> <td><div class='btn-group'><a onclick='getQualifiers(\""+result[x].name+"\")' class='btn btn-success btn-mini' >表字段</a> <a  onclick='getKeys(\""+result[x].name+"\")'  class='btn btn-success btn-mini  ' >主键</a></div></td> </tr> ");
				}
				}else{
					 for(var i=0;i<pagesize;i++)
						{
						$("#getresult tbody").append("<tr ><td>"+result[i].name+"</td> <td>"+result[i].size+"B </td> <td>"+result[i].lastModification+"</td> <td>"+result[i].owner+"</td> <td><div class='btn-group'><a   onclick='getQualifiers(\""+result[i].name+"\")'  class='btn btn-success btn-mini' >表字段</a> <a onclick='getKeys(\""+result[i].name+"\")' class='btn btn-success btn-mini' >主键</a></div></td></tr> ");
						
						}
					
					
				}
				setpage();
				}
			}
		});
	}
