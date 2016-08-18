var curHBaseInstance = "HBase01";//以后用cookie解决
var request;
var isNextPageRequest=false;
var nextRowkey;
$(document).ready(function() {
	basepath = getBaseURL();
	var paramOfGetTablesList = new Object();
	paramOfGetTablesList.type = "listTables";
	paramOfGetTablesList.hbaseInstance = curHBaseInstance;
	paramOfGetTablesList.regex = ".*";
	$("#nextPageDiv").hide();
	$("#noExactMatchWarningDiv").hide();
	$("#noResultWarningDiv").hide();
	$.ajax({
		type : "POST",
		url : basepath + "/StreamManagementServlet",
		data : JSON.stringify(paramOfGetTablesList),
		dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
		success : function(restext) {
//			alert(restext);
			for(var x in restext.result){
				var name = restext.result[x].name;
				$("#tableName").append("<option value='"+name+"'>"+name+"</option>");
			}
		}
	});
});

$("#_action_Scan").click(function() {
	request=new Object();
	$("#nextPageDiv").hide();
	$("#noExactMatchWarningDiv").hide();
	$("#noResultWarningDiv").hide();
	$("#getresult tbody").html("");
	isNextPageRequest=false;
	request.hbaseInstance=curHBaseInstance;
	request.hbaseTable=$("#tableName").val();
	request.qasType="KvQuery";
	request.params=new Object();
	
	if($("#fuzzyQuery:checked").val()){
		request.params.isFuzzy="true";
		request.params.isBatch="false";
		request.params.fuzzyRow=$("#rowKey").val();
		request.params.rowkey="";
	}
	else{
		request.params.isFuzzy="false";
		request.params.isBatch="true";
		request.params.rowkey=$("#rowKey").val();
	}
	request.params.pageSize=$("#rows").val();
	if(request.params.pageSize==""){
		request.params.pageSize=3;
	}
	request.params.returnType="rowkey";
	request.params.maxVersions=$("#versions").val();
	if(request.params.maxVersions==""){
		request.params.maxVersions=100;
	}
//	nextPageRequest=request;
	postRequest(request);
	
});

$("#nextPage").click(function() {
	$("#noExactMatchWarningDiv").hide();
	$("#noResultWarningDiv").hide();
	$("#getresult tbody").html("");
	isNextPageRequest=true;
	request.params.rowkey=nextRowkey;
	request.params.pageSize=$("#rows").val();
	if(request.params.pageSize==""){
		request.params.pageSize=3;
	}
	request.params.maxVersions=$("#versions").val();
	if(request.params.maxVersions==""){
		request.params.maxVersions=100;
	}
	postRequest(request);
});

function postRequest(request){
	$.ajax({
		type : "POST",
		url : basepath + "/StreamQASServlet",
		data : JSON.stringify(request),
		dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
		processData: false,
		jsonp:false,//如果此处为true的话，会将发送过去的??转码
		success : function(resultO) {
			nextRowkey = resultO.nextRowkey;
			if(resultO.nextRowkey!=""){
				$("#nextPageDiv").show();
			}else{
				$("#nextPageDiv").hide();
			}
			var result = resultO.result;
			if(result==null ||result==""){
				$("#noResultWarningDiv").show();
			}
			else{
				if(request.params.isFuzzy=="false" &&
					Object.getOwnPropertyNames(result)[0]!=request.params.rowkey
					 && !isNextPageRequest){
					$("#noExactMatchWarningDiv").show();
				}
					for(var rowkey in result){
						var tss = Object.getOwnPropertyNames(result[rowkey]);
						var rowkeyHeadQualifiers="";
						for(var x in result[rowkey][tss[0]]){
							rowkeyHeadQualifiers+="<span class='columnname'>"+x+
							"</span>: "+result[rowkey][tss[0]][x]+"<br>";							
						}
						var rowkeyHead = "<tr class='flatrow'>\
						<td><span class='rowkey'>"+rowkey+"</span><br>\
						<div style='float: right; margin: 0'>\
							<span class='switch' id='rkswitchON"+rowkey+"'\
								onclick=on('"+rowkey+"');>\
								Show all "+tss.length+" Timstamps </span> <span class='switch'\
								id='rkswitchOFF"+rowkey+"' style='display: none;' \
								onclick=off('"+rowkey+"');>Hide Timestamps</span>\
						</div></td>\
					<td>"+rowkeyHeadQualifiers+"<td></td>\
				</tr>";
						$("#getresult tbody").append(rowkeyHead);
						for (var ts in result[rowkey]){
							var timestampQualifiers="";
							for (var x in result[rowkey][ts]){
								timestampQualifiers+="<span class='columnname'>"+x+"</span>: "+result[rowkey][ts][x]+"<br>";
							}
							var timestampRows = "<tr class='tsrow rowkey"+rowkey+"'\
								style='display: none;'>\
							<td>"+ts+"</td>\
							<td>"+timestampQualifiers+"<td></td>\
						</tr>";
							$("#getresult tbody").append(timestampRows);
						}
					}
					
				}
			}
		});
	}
