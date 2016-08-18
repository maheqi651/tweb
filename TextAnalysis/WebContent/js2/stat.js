var curHBaseInstance = "HBase01";//以后用cookie解决



$('.tooltip-demo').tooltip({
	selector : "a[data-toggle=tooltip]"
});
$(document).ready(function() {
	basepath = getBaseURL();
	$(".span9").css("display","none");
	$("#mainIntroCont").css("display","block");
//	document.getElementById("dailyStatQuery").style.display = "none";
	// document.getElementById("dailyStatQueryHolder").style.visibility="hidden";
	//  alert(++[[]][+[]]+[+[]]);
	$('#StatResultModal').on('hidden', function () {
			$('#StatResultTableOutter').html("<table id='StatResultTable'></table><div id='StatResultPager'></div>");
//		$("#StatResultTable").text("");
//		$("#StatResultPager").text("");//不正确，jqGrid命令执行后StatResultTable已被嵌套入多层div中
	});
});

$(".nav-link").click(function() {
	$(".span9").css("display","none");
	var linkid = $(this).attr("id");
	var span9id=linkid.substring(0,linkid.indexOf("Link"));
	$("#"+span9id).slideToggle("slow");
	$("input[id^='"+span9id+"']").val("");//置空当前表单中所有的input,调试完成后打开
});



/*var i = 0;
$("#dailyStatLink").click(function() {
	$("#mainIntro").slideToggle("slow");
	$("#dataIntro").slideToggle("slow");
	//  
	if (i % 2 == 0) {
		// document.getElementById("dailyStatQuery").style.display="block";
		$("#dailyStatQuery").slideDown("slow");
		$("#dailyStatLi").addClass("active");
	} else {
		// document.getElementById("dailyStatQuery").style.visibility="hidden";
		$("#dailyStatQuery").slideToggle("slow");
		$("#dailyStatLi").removeClass("active");
	}
	i++;
});*/

$(".form_date").datetimepicker({
	format : "yyyy-mm-dd",
	autoclose : true,
	todayBtn : true,
	pickerPosition : "bottom-left",
	language : "zh-CN",
	weekStart : 0,
	minView : "month",
	todayHighligh : "true"
});

$(".form_datetime").datetimepicker({
	format : "yyyy-mm-dd  hh:ii:ss",
	autoclose : true,
	todayBtn : true,
	pickerPosition : "bottom-left",
	language : "zh-CN",
	weekStart : 0,
	minView : "hour",
	todayHighligh : "true"
});

$(".form_datetimehr").datetimepicker({
	format : "yyyy-mm-dd  hh",
	autoclose : true,
	todayBtn : true,
	pickerPosition : "bottom-left",
	language : "zh-CN",
	weekStart : 0,
	minView : "day",
	todayHighligh : "true"
});

/*$(document).ready(function() {// DOM的onload事件处理函数
	$("#submit").click(function() {// 当按钮submit被点击时的处理函数
		postdata();// submit被点击时执行postdata函数
	});
});*/
$("#DailyTraceStat_submit").click(function() {
	var qasObject=new Object();
	qasObject["hbaseInstance"]=curHBaseInstance;
	qasObject["hbaseTable"]=$("#DailyTraceStatHBaseTable").val();
	qasObject['qasType']='DailyTraceStat';
	qasObject["resultTableSuffix"]=$("#DailyTraceStatResultTableSuffix").val();
	qasObject['params']=new Object();
	qasObject['params']['beginTime']=$("#DailyTraceStatStartTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['endTime']=$("#DailyTraceStatEndTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['speedField']=$("#DailyTraceStatSpeedField").val();
	qasObject['params']['speedThreshold']=$("#DailyTraceStatSpeedThreshold").val();
	qasObject['params']['minSpeed']=$("#DailyTraceStatMinSpeed").val();
	qasObject['params']['maxSpeed']=$("#DailyTraceStatMaxSpeed").val();
	postStat(qasObject);
});

$("#DailyTraceStatQuery_submit").click(function() {
	var qasObject=new Object();
	qasObject["hbaseInstance"]=curHBaseInstance;
	qasObject["hbaseTable"]=$("#DailyTraceStatQueryHBaseTable").val();
	qasObject['qasType']='DailyTraceStatQuery';
	qasObject["resultTableSuffix"]=$("#DailyTraceStatQueryResultTableSuffix").val();
	qasObject['params']=new Object();
	qasObject['params']['beginTime']=$("#DailyTraceStatQueryStartTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['endTime']=$("#DailyTraceStatQueryEndTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['traceIDs']=$("#DailyTraceStatQueryDeviceID").val();
	postdata(qasObject);
});

$("#CustomPatrolStat_submit").click(function() {
	
	var qasObject=new Object();
	qasObject["hbaseInstance"]=curHBaseInstance;
	qasObject["hbaseTable"]=$("#CustomPatrolStatTableName").val();
	qasObject['qasType']='CustomPatrolStat';
	qasObject["resultTableSuffix"]=$("#CustomPatrolStatTableSuffix").val();
	qasObject['params']=new Object();
	qasObject['params']['beginTime']=$("#CustomPatrolStatStartTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['endTime']=$("#CustomPatrolStatEndTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['speedField']=$("#CustomPatrolStatSpeedField").val();
	qasObject['params']['minSpeed']=$("#CustomPatrolStatMinSpeed").val();
	qasObject['params']['maxSpeed']=$("#CustomPatrolStatMaxSpeed").val();
	qasObject['params']['speedThreshold']=$("#CustomPatrolStatSpeedThreshold").val();
	postStat(qasObject);
});
$("#CustomPatrolStatQuery_submit").click(function() {
	
	var qasObject=new Object();
	qasObject["hbaseInstance"]=curHBaseInstance;
	qasObject["hbaseTable"]=$("#CustomPatrolStatQueryTableName").val();
	qasObject['qasType']='CustomPatrolStatQuery';
	qasObject["resultTableSuffix"]=$("#CustomPatrolStatQueryTableSuffix").val();
	qasObject['params']=new Object();
	qasObject['params']['traceIDs']=$("#CustomPatrolStatQueryDeviceID").val();
	postdata(qasObject);
});

$("#AbnormalStat_submit").click(function() {
	var qasObject=new Object();
	qasObject["hbaseInstance"]=curHBaseInstance;
	qasObject["hbaseTable"]=$("#AbnormalStatTableName").val();
	qasObject['qasType']='AbnormalStat';
	qasObject["resultTableSuffix"]=$("#AbnormalStatTableSuffix").val();
	qasObject['params']=new Object();
	qasObject['params']['beginTime']=$("#AbnormalStatStartTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['endTime']=$("#AbnormalStatEndTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['minSpeed']=$("#AbnormalStatSpeedLimit").val();
	postStat(qasObject);
});

$("#AbnormalStatQuery_submit").click(function() {
	var qasObject=new Object();
	qasObject["hbaseInstance"]=curHBaseInstance;
	qasObject['hbaseTable']=$("#AbnormalStatQueryTableName").val();
	qasObject['qasType']='AbnormalStatQuery';
	qasObject["resultTableSuffix"]=$("#AbnormalStatQueryTableSuffix").val();
	qasObject['params']=new Object();
	qasObject['params']['beginTime']=$("#AbnormalStatQueryStartTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['endTime']=$("#AbnormalStatQueryEndTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['minSpeed']=$("#AbnormalStatQuerySpeedLimit").val();
	postdata(qasObject);
});

$("#SpeedingStat_submit").click(function() {
	var qasObject=new Object();
	qasObject["hbaseInstance"]=curHBaseInstance;
	qasObject["hbaseTable"]=$("#SpeedingStatTableName").val();
	qasObject['qasType']='SpeedingStat';
	qasObject["resultTableSuffix"]=$("#SpeedingStatTableSuffix").val();
	qasObject['params']=new Object();
	qasObject['params']['beginTime']=$("#SpeedingStatStartTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['endTime']=$("#SpeedingStatEndTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['minSpeed']=$("#SpeedingStatSpeedLimit").val();
	qasObject['params']['speedField']=$("#SpeedingStatSpeedColumn").val();
	postStat(qasObject);
});

$("#SpeedingStatQuery_submit").click(function() {
	var qasObject=new Object();
	qasObject["hbaseInstance"]=curHBaseInstance;
	qasObject["hbaseTable"]=$("#SpeedingStatQueryTableName").val();
	qasObject['qasType']='SpeedingStatQuery';
	qasObject["resultTableSuffix"]=$("#SpeedingStatQueryTableSuffix").val();
	qasObject['params']=new Object();
	qasObject['params']['beginTime']=$("#SpeedingStatQueryStartTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['endTime']=$("#SpeedingStatQueryEndTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['minSpeed']=$("#SpeedingStatQuerySpeedLimit").val();
	qasObject['params']['speedField']=$("#SpeedingStatQuerySpeedColumn").val();
	qasObject['params']['registerDevices']=$("#SpeedingStatQueryDeviceIDs").val();
	postdata(qasObject);
});

$("#MultiCommonAnalyse_submit").click(function() {
	var qasObject=new Object();
	qasObject["hbaseInstance"]=curHBaseInstance;
	qasObject["hbaseTable"]=$("#MultiCommonAnalyseTableName").val();
	qasObject['qasType']='MultiCommonAnalyse';
	qasObject['params']=new Object();
	qasObject['params']['hourRange']=$("#MultiCommonAnalyseHours").val();
	qasObject['params']['deviceInfo']=new Object();
	var sctoList=$("#MultiCommonAnalyseObjects").val().split(",");
	var sctstList=$("#MultiCommonAnalyseStartTime").val().split(",");
	if(sctstList.length ==1){
		for (var item in sctoList){
			qasObject['params']['deviceInfo'][sctoList[item]]=sctstList[0];
		}
		postdata(qasObject);
	}
	else if(sctstList.length >1){
		if (sctstList.length != sctoList.length){
			alert("当‘起始时间’个数多于1个时,请保证起始时间的个数与车辆ID的个数相同");
		}
		else{
			for (var item in sctoList){
				qasObject['params']['deviceInfo'][sctoList[item]]=sctstList[item];
			}
			postdata(qasObject);
		}
	}
});

$("#FlowStat_submit").click(function() {
	var qasObject=new Object();
	qasObject["hbaseInstance"]=curHBaseInstance;
	qasObject["hbaseTable"]=$("#FlowStatTableName").val();
	qasObject['qasType']='FlowStat';
	qasObject["resultTableSuffix"]=$("#FlowStatTableSuffix").val();
	qasObject['params']=new Object();
	qasObject['params']['beginTime']=$("#FlowStatStartTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['endTime']=$("#FlowStatEndTime").val().replace(/[^0-9]/g,"");
	postStat(qasObject);
});

$("#FlowStatQuery_submit").click(function() {
	var qasObject=new Object();
	qasObject["hbaseInstance"]=curHBaseInstance;
	qasObject["hbaseTable"]=$("#FlowStatQueryTableName").val();
	qasObject['qasType']='FlowStatQuery';
	qasObject["resultTableSuffix"]=$("#FlowStatQueryTableSuffix").val();
	qasObject['params']=new Object();
	qasObject['params']['beginTime']=$("#FlowStatQueryStartTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['endTime']=$("#FlowStatQueryEndTime").val().replace(/[^0-9]/g,"");
	postdata(qasObject);
});

$("#FakeRegisterStat_submit").click(function() {
	var qasObject=new Object();
	qasObject["hbaseInstance"]=curHBaseInstance;
	qasObject["hbaseTable"]=$("#FakeRegisterStatTableName").val();
	qasObject['qasType']='FakeRegisterStat';
	qasObject["resultTableSuffix"]=$("#FakeRegisterStatTableSuffix").val();
	qasObject['params']=new Object();
	qasObject['params']['beginTime']=$("#FakeRegisterStatStartTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['endTime']=$("#FakeRegisterStatEndTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['speedThreshold']=$("#FakeRegisterStatSpeedLimit").val();
	postStat(qasObject);
});

$("#FakeRegisterStatQuery_submit").click(function() {
	var qasObject=new Object();
	qasObject["hbaseInstance"]=curHBaseInstance;
	qasObject["hbaseTable"]=$("#FakeRegisterStatQueryTableName").val();
	qasObject['qasType']='FakeRegisterStatQuery';
	qasObject["resultTableSuffix"]=$("#FakeRegisterStatQueryTableSuffix").val();
	qasObject['params']=new Object();
	qasObject['params']['beginTime']=$("#FakeRegisterStatQueryStartTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['endTime']=$("#FakeRegisterStatQueryEndTime").val().replace(/[^0-9]/g,"");
	postdata(qasObject);
});

$("#NumCalculate_submit").click(function() {
	var qasObject=new Object();
	qasObject["hbaseInstance"]=curHBaseInstance;
	qasObject["hbaseTable"]=$("#NumCalculateTableName").val();
	qasObject['qasType']='NumCalculate';
	qasObject["resultTableSuffix"]=$("#NumCalculateTableSuffix").val();
	
	qasObject['params']=new Object();
	qasObject['params']['beginTime']=$("#NumCalculateStartTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['endTime']=$("#NumCalculateEndTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['groupBy']=$("#NumCalculateGroupBy").val();
	qasObject['params']['fieldName']=$("#NumCalculateFieldName").val();
	qasObject['params']['startRow']=$("#NumCalculateStartRow").val();
	qasObject['params']['stopRow']=$("#NumCalculateStopRow").val();
	postStat(qasObject);
});

$("#NumCalculateQuery_submit").click(function() {
	var qasObject=new Object();
	qasObject["hbaseInstance"]=curHBaseInstance;
	qasObject["hbaseTable"]=$("#NumCalculateQueryTableName").val();
	qasObject['qasType']='NumCalculateQuery';
	qasObject["resultTableSuffix"]=$("#NumCalculateQueryTableSuffix").val();
	qasObject['params']=new Object();
	postdata(qasObject);
});

$("#TraceQuery_submit").click(function() {
	var qasObject=new Object();
	qasObject["hbaseInstance"]=curHBaseInstance;
	qasObject["hbaseTable"]=$("#TraceQueryTableName").val();
	qasObject['qasType']='TraceQuery';
	qasObject['params']=new Object();
	qasObject['params']['beginTime']=$("#TraceQueryStartTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['endTime']=$("#TraceQueryEndTime").val().replace(/[^0-9]/g,"");
	switch ($("#TraceQueryType > .active").val()){
	case "byID":{
		qasObject['params']['deviceIDs']=$("#TraceQueryDeviceID").val();
		if($("#TraceQueryVacuateDistance").val()!=""){
			qasObject['params']['vacuateDistance']=$("#TraceQueryVacuateDistance").val();
		}
		if($("#TraceQueryVacuateDP").val()!=""){
			qasObject['params']['vacuateDP']=$("#TraceQueryVacuateDP").val();
		}
	}
		break;
					case "bySpatial":{
						qasObject['params']['ezGeo']=new Object();
						qasObject['params']['ezGeo']['shape']='rectangle';
						qasObject['params']['ezGeo']['coordinate'] = $("#TraceQuerySmallX")
								.val()
								+ ","
								+ $("#TraceQuerySmallY").val()
								+ ","
								+ $("#TraceQueryBigX").val()
								+ ","
								+ $("#TraceQueryBigY").val();
						qasObject['params']['returnType']=$("#TraceQueryReturnType").val();
					}
						break;
					case "byIDAndSpatial": {
						qasObject['params']['deviceIDs'] = $(
								"#TraceQueryDeviceID").val();
						qasObject['params']['ezGeo']=new Object();
						qasObject['params']['ezGeo']['shape']='rectangle';
						qasObject['params']['ezGeo']['coordinate'] = $("#TraceQuerySmallX")
								.val()
								+ ","
								+ $("#TraceQuerySmallY").val()
								+ ","
								+ $("#TraceQueryBigX").val()
								+ ","
								+ $("#TraceQueryBigY").val();
						qasObject['params']['returnType'] = $(
								"#TraceQueryReturnType").val();
					}
		break;
	default:
		break;
	}

	postdata(qasObject);
});

$("#RegisterQuery_submit").click(function() {
	var qasObject=new Object();
	qasObject["hbaseInstance"]=curHBaseInstance;
	qasObject["hbaseTable"]=$("#RegisterQueryTableName").val();
	qasObject['qasType']='RegisterQuery';
	qasObject['params']=new Object();
	qasObject['params']['beginTime']=$("#RegisterQueryStartTime").val().replace(/[^0-9]/g,"");
	qasObject['params']['endTime']=$("#RegisterQueryEndTime").val().replace(/[^0-9]/g,"");

//TO DO
	switch ($("#RegisterQueryType > .active").val()) {
	case "byRegLocs":
		qasObject['params']['regLocs']=$("#RegisterQueryRegLocs").val();
		break;
	
case "byRegObjs":
	qasObject['params']['regObjs']=$("#RegisterQueryRegObjs").val();
		break;
		
case "bySpatial":
	qasObject['params']['ezGeo']=new Object();
	qasObject['params']['ezGeo']['shape']='rectangle';
	qasObject['params']['ezGeo']['coordinate'] = $("#RegisterQuerySmallX")
			.val()
			+ ","
			+ $("#RegisterQuerySmallY").val()
			+ ","
			+ $("#RegisterQueryBigX").val()
			+ ","
			+ $("#RegisterQueryBigY").val();
	break;

	default:
		break;
	}
	postdata(qasObject);
});
/*function postdata() {// 提交数据函数
	$.ajax({// 调用jquery的ajax方法
				type : "POST",// 设置ajax方法提交数据的形式
				url : basepath+"/StreamQASServlet",// 把数据提交到url
				 输入框deviceID中的值作为提交的数据，
				 * 必须使用key/value的形式，如"key=value"，
				 * 如果多个键值对，就使用&分隔开，
				 * 如"key1=value1&key2=value2" 
				//data: "deviceID="+$("#deviceID").val()+"&startTime="+$("#startTime").val().replace(/-/g,"")+"&endTime="+$("#endTime").val().replace(/-/g,""),
				data : "{'hbaseInstance':'HBase02','hbaseTable':'DailyStatistics','qasType':'DailyPatrolStat','params':{'beginTime':'"
						+ $("#startTime").val().replace(/-/g, "")
						+ "','endTime':'"
						+ $("#endTime").val().replace(/-/g, "")
						+ "','regObjs':'"
						+ $("#deviceID").val()
						+ "'}}",
				success : function(msg) {// 提交成功后的回调，msg变量是访问页面输出的内容
					alert("数据提交成功");// 如果有必要，可以把msg变量的值显示到某个DIV元素中
					//alert(data);
					alert(msg);
					postCallBack(msg, qasType);
				},
				error : function() {
					alert(arguments[1]);
				}
			});
}*/

function postStat(qasObject){//提交分析统计。
	var doAjax=true;//调试开关
	$("#warningModalLabel").text("提示");
	$("#warningModalText").text("统计分析开始，结束时会给您提示。统计过程中不影响任何操作");
	$("#warningModal").modal('show');
	
	$.ajax({// 调用jquery的ajax方法
			type : "POST",
			url : basepath+"/StreamQASServlet",
			data : JSON.stringify(qasObject),
			dataType: "text",
			success : function(msg) {
//				alert(msg);
				$("#warningModalLabel").text("来自服务器端的提示");
				$("#warningModalText").text(msg);
				$("#warningModal").modal('show');
			},
			error : function() {
				alert(arguments[1]);
			}
		});
}

function postdata(qasObject) {// 提交数据函数，每个不同的分析表单的提交按钮用来负责组装不同的qasObject
	var doAjax=true;//调试开关
	$("#"+qasObject["qasType"]+"_submit").attr({disabled:"disabled"});
	$("#"+qasObject["qasType"]+"Wait").css("display","block");
	if(doAjax){
	$.ajax({// 调用jquery的ajax方法
			type : "POST",
			url : basepath+"/StreamQASServlet",
			data : JSON.stringify(qasObject),
			dataType: "json",
			success : function(msg) {
//				alert(msg);
				$("#"+qasObject["qasType"]+"_submit").removeAttr("disabled");
				$("#"+qasObject["qasType"]+"Wait").css("display","none");
				postCallBack(msg, qasObject["qasType"]);
			},
			error : function() {
				alert(arguments[1]);
			}
		});
	}
	else{
		msg = {
			"result" : [ {
				"TIME" : "20120401011358",
				"GPSID" : "10001",
				"DIR" : "0.0",
				"SPEED" : "0.0",
				"Y" : "39.7655066666667",
				"X" : "116.209666666667"
			}, {
				"TIME" : "20120401011353",
				"GPSID" : "10001",
				"DIR" : "0.0",
				"SPEED" : "0.0",
				"Y" : "39.7655066666667",
				"X" : "116.209666666667"
			}, {
				"TIME" : "20120401011348",
				"GPSID" : "10001",
				"DIR" : "0.0",
				"SPEED" : "0.0",
				"Y" : "39.765505",
				"X" : "116.209668333333"
			}, {
				"TIME" : "20120401011343",
				"GPSID" : "10001",
				"DIR" : "0.0",
				"SPEED" : "0.0",
				"Y" : "39.765505",
				"X" : "116.209668333333"
			}, {
				"TIME" : "20120401011338",
				"GPSID" : "10001",
				"DIR" : "0.0",
				"SPEED" : "0.0",
				"Y" : "39.765505",
				"X" : "116.209668333333"
			}, {
				"TIME" : "20120401011333",
				"GPSID" : "10001",
				"DIR" : "0.0",
				"SPEED" : "0.0",
				"Y" : "39.765505",
				"X" : "116.20967"
			},{
				"TIME" : "20120401011353",
				"GPSID" : "10000",
				"DIR" : "0.0",
				"SPEED" : "32.0",
				"Y" : "39.7429666666667",
				"X" : "116.131021666667"
			}, {
				"TIME" : "20120401011348",
				"GPSID" : "10000",
				"DIR" : "0.0",
				"SPEED" : "32.0",
				"Y" : "39.74296",
				"X" : "116.131021666667"
			}, {
				"TIME" : "20120401011343",
				"GPSID" : "10000",
				"DIR" : "0.0",
				"SPEED" : "32.0",
				"Y" : "39.742945",
				"X" : "116.131018333333"
			}]
		};
postCallBack(msg, qasObject["qasType"]);
	}
}

function postCallBack(msg, qasType){
//	alert(qasType);
	var resObject=msg;
	var colNames;
	var colModel;
	var tableName="统计结果";
	var mydata=msg.result;
	var isGrouping =false;
	var groupField;
	var axisFormatOptions = {decimalPlaces: 5};
	switch (qasType) {//如何将数据展现
	case "CustomPatrolStatQuery":
		
	   	colNames=['车辆ID','平均速度(km/h)', '最大速度(km/h))','最小速度(km/h))','巡逻里程(m)','巡逻时间','轨迹点数'];
   		colModel=[
   		{name:'traceID',index:'traceID', width:60, sorttype:"string"},
   		{name:'avgSpeed', width:95, align:"center",sorttype:"float", formatter:"number"},
   		{name:'maxSpeed', width:95, align:"center",sorttype:"float", formatter:"number"},
   		{name:'minSpeed', width:95, align:"center",sorttype:"float",formatter:"number"},
   		{name:'distance', width:90,align:"center",sorttype:"float", formatter:"number"},		
   		{name:'patrolTime', width:70,align:"center"},	
   		{name:'records', width:85,align:"center",sorttype:"float", formatter:"integer"}
   	];
   		tableName = "自定义时间统计";
		break;
	case "AbnormalStatQuery":
	   	colNames=['车辆ID','时间','异常速度(km/h)','起始点X','起始点Y','终结点X','终结点Y'];
	   	colModel=[
	   		{name:'traceID',index:'traceID', width:60, sorttype:"string"},
	   		{name:'time', width:150, align:"center", sorttype:"string"},
	   		{name:'speed', width:100, align:"center",sorttype:"float",formatter:"number"},
	   		{name:'startX', width:90,align:"center",sorttype:"float", formatter:"number", formatoptions:axisFormatOptions},
	   		{name:'startY', width:90,align:"center",sorttype:"float", formatter:"number", formatoptions:axisFormatOptions},
	   		{name:'endX', width:90,align:"center",sorttype:"float", formatter:"number", formatoptions:axisFormatOptions},
	   		{name:'endY', width:90,align:"center",sorttype:"float", formatter:"number", formatoptions:axisFormatOptions}
	   	];
       	tableName="异常速度统计";
      	groupField = ['traceID'];
       	isGrouping = true;
		break;

	case "SpeedingStatQuery":
	   	colNames=['车辆ID','卡口ID', '时间','超速速度(km/h))'];
   		colModel=[
   		{name:'registerObj',index:'registerObj', width:120, sorttype:"string"},
   		{name:'registerDevice', width:150, align:"center",sorttype:"string"},
   		{name:'time', width:150, align:"center",sorttype:"string"},
   		{name:'speed', width:95, align:"center",sorttype:"float",formatter:"number"}
	];
   		tableName = "超速统计";
		break;
	case "MultiCommonAnalyse":
	   	colNames=['路段','过车记录数'];
   		colModel=[
   		{name:'road',index:'road', width:500, align:"center", sorttype:"string"},
   		{name:'times', width:200, align:"center",sorttype:"integer"}
   		];
   		tableName = "多轨迹流向分析";
		break;
		
	case "FlowStatQuery":
	   	colNames=['路段','过车记录数'];
   		colModel=[
   		{name:'road',index:'road', width:500, align:"center", sorttype:"string"},
   		{name:'times', width:200, align:"center",sorttype:"integer"}
   		];
   		tableName = "流量分析";
		break;
		
	case "FakeRegisterStatQuery":
	   	colNames=['车辆ID','开始时间', '结束时间','开始卡口','结束卡口','速度'];
	   	colModel=[
	   		{name:'deviceID',index:'deviceID', width:150, align:"center",sorttype:"string"},

	   		{name:'startTime', width:150, align:"center",sorttype:"string"},
	   		{name:'endTime', width:150, align:"center",sorttype:"string"},
	   		{name:'startLoc', width:150, align:"center",sorttype:"string"},
	   		{name:'endLoc', width:150,align:"center",sorttype:"string"},		
	   		{name:'speed', width:100, align:"center",sorttype:"float", formatter:"number"}
	   	];
   		tableName = "冒用分析";
		break;
		
	case "NumCalculateQuery":
	   	colNames=['ID','平均值', '最大值','最小值','总计','数据条目'];
	   	colModel=[
	   		{name:'ID',index:'ID', width:200, align:"center", sorttype:"string"},
	   		{name:'average', width:100, align:"center",sorttype:"float",formatter:"number", editable:true},
	   		{name:'maxValue', width:100, align:"center",sorttype:"float", formatter:"number", editable:true},
	   		{name:'minValue', width:100, align:"center",sorttype:"float",formatter:"number", editable:true},
	   		{name:'sum', width:100,align:"center",sorttype:"float", formatter:"number"},		
	   		{name:'records', width:100, align:"center",sorttype:"integer"}		
	   	];
       	tableName="数值类数据统计";
//       	groupField = ['ID'];
//       	isGrouping = true;
		break;
		
	case "DailyTraceStatQuery":
		colNames = ['车辆ID','日期','平均速度(km/h)', '最大速度(km/h)','最小速度(km/h)','巡逻里程(m)','巡逻时间','轨迹点数'];
//		colNames = ['aaa','bbb'];
		colModel = [
		       		{name:'traceID',width:70, index:'traceID',sorttype:"string"},
		       		{name:'statDate', width:70, align:"center", sorttype:"string"},
		       		{name:'avgSpeed', width:95, align:"center",sorttype:"float", formatter:"number", editable:true},
		       		{name:'maxSpeed', width:95, align:"center",sorttype:"float", formatter:"number", editable:true},
		       		{name:'minSpeed', width:95, align:"center",sorttype:"float",formatter:"number", editable:true},
		       		{name:'distance', width:90,align:"center",sorttype:"float", formatter:"number"},		
		       		{name:'patrolTime', width:70, sorttype:"float",align:"center"},	
		       		{name:'records', width:85,align:"center",sorttype:"float", formatter:"integer"}
		       	];

		       	tableName="按天统计";
		       	groupField = ['traceID'];
		       	isGrouping = true;
		break;
	case "TraceQuery":
		if (mydata.length>0){
			colNames=new Array();
			colModel=new Array();
			for(var y in mydata[0]){
					colNames.push(y);
					colModel.push({name:y, width:120, align:"center", sorttype:"string"});
			}
			colNames.reverse();
			colModel.reverse();
		}
		else{
			colNames=['无数据'];
			colModel = [
			       		{name:'noData',width:500, sorttype:"string"}];
		}
//		colNames = ['车辆ID','日期','平均速度(km/h)', '最大速度(km/h)','最小速度(km/h)','巡逻里程(km)','巡逻时间','轨迹点数'];

//		colModel = [
//		       		{name:'traceID',width:70, index:'traceID',sorttype:"string"},
//		       		{name:'statDate', width:70, align:"center", sorttype:"string"},
//		       		{name:'avgSpeed', width:95, align:"center",sorttype:"float", formatter:"number", editable:true},
//		       		{name:'maxSpeed', width:95, align:"center",sorttype:"float", formatter:"number", editable:true},
//		       		{name:'minSpeed', width:95, align:"center",sorttype:"float",formatter:"number", editable:true},
//		       		{name:'distance', width:90,align:"center",sorttype:"float", formatter:"number"},		
//		       		{name:'patrolTime', width:70, sorttype:"float",align:"center"},	
//		       		{name:'records', width:85,align:"center",sorttype:"float", formatter:"integer"}
//		       	];

		       	tableName="轨迹类数据查询";

		break;
	case "RegisterQuery":
//		colNames = ['车辆ID','日期','平均速度(km/h)', '最大速度(km/h)','最小速度(km/h)','巡逻里程(km)','巡逻时间','轨迹点数'];

//		colModel = [
//		       		{name:'traceID',width:70, index:'traceID',sorttype:"string"},
//		       		{name:'statDate', width:70, align:"center", sorttype:"string"},
//		       		{name:'avgSpeed', width:95, align:"center",sorttype:"float", formatter:"number", editable:true},
//		       		{name:'maxSpeed', width:95, align:"center",sorttype:"float", formatter:"number", editable:true},
//		       		{name:'minSpeed', width:95, align:"center",sorttype:"float",formatter:"number", editable:true},
//		       		{name:'distance', width:90,align:"center",sorttype:"float", formatter:"number"},		
//		       		{name:'patrolTime', width:70, sorttype:"float",align:"center"},	
//		       		{name:'records', width:85,align:"center",sorttype:"float", formatter:"integer"}
//		       	];
		if (mydata.length>0){
			colNames=new Array();
			colModel=new Array();
			for(var y in mydata[0]){
					colNames.push(y);
					colModel.push({name:y, width:70, align:"center", sorttype:"string"});
			}
			colNames.reverse();
			colModel.reverse();
		}
		else{
			colNames=['无数据'];
			colModel = [
			       		{name:'noData',width:500, sorttype:"string"}];
		}
		       	tableName="登记类数据查询";

		break;
	default:
		mydata = msg.result;
		break;
	}
	
	$("#StatResultTable").jqGrid({
		data : mydata,//是否是名称不能加引号的原因？经验证不是这个原因
        height : 'auto',
        altRows:true,
        datatype: 'local',//采用data传数据的话这个必须要有，否则会ajax调用url的postdata，而这个jqGrid里，url和postdata都为空！！
        autowidth:true,// window.screen.availWidth - 30,
        colNames : colNames,
        colModel : colModel,
        rowNum : 10,// 分页,显示记录数
        rowList : [10,20,50],// 分页,可选择的显示记录数
        pager : '#StatResultPager',// 导航栏ID
        //rownumbers : true,// 是否显示序号
        rownumWidth : 30,  // 设置序号列宽度
       	viewrecords: true,
       	sortname: 'name',
       	grouping:isGrouping,
       	groupingView : {
       		groupField : groupField,
//       		groupSummary : [true],
       		groupColumnShow : [true],
       		groupText : ['<b>{0}</b>'],
       		groupCollapse : false,
    		groupOrder: ['asc']
       	},
       	caption: tableName//表格名称
        
	});
//	$("#"+qasType+"Result").slideToggle("slow");//展现结果页面,不再使用，改使用统一的ResultTable。
	var totalWidth=70;
	for(var row in colModel){
		totalWidth+=colModel[row].width;
	}
	$("#StatResultModal").css("width", totalWidth);
	$("#StatResultModal").modal('toggle');
}

$("#traceQueryByIDBtn").click(function() {
	$("#TraceQueryDeviceIDDiv").animate({
		width : "show"
	});
	$("#TraceQueryAxisDiv").animate({
		width : "hide"
	});
	$("#TraceQueryVacuateDistanceDiv").animate({
		width : "show"
	});
	$("#TraceQueryVacuateDPDiv").animate({
		width : "show"
	});
	$("#TraceQueryReturnTypeDiv").animate({
		width : "hide"
	});
});
$("#traceQueryBySpatialBtn").click(function() {
	$("#TraceQueryDeviceIDDiv").animate({
		width : "hide"
	});
	$("#TraceQueryAxisDiv").animate({
		width : "show"
	});
	$("#TraceQueryVacuateDistanceDiv").animate({
		width : "hide"
	});
	$("#TraceQueryVacuateDPDiv").animate({
		width : "hide"
	});
	$("#TraceQueryReturnTypeDiv").animate({
		width : "show"
	});
});
$("#traceQueryByIDAndSpatialBtn").click(function() {
	$("#TraceQueryDeviceIDDiv").animate({
		width : "show"
	});
	$("#TraceQueryAxisDiv").animate({
		width : "show"
	});
	$("#TraceQueryVacuateDistanceDiv").animate({
		width : "hide"
	});
	$("#TraceQueryVacuateDPDiv").animate({
		width : "hide"
	});
	$("#TraceQueryReturnTypeDiv").animate({
		width : "show"
	});
});

$("#RegisterQueryByRegLocsBtn").click(function() {
	$("#RegisterQueryRegLocsDiv").animate({
		width : "show"
	});
	$("#RegisterQueryRegObjsDiv").animate({
		width : "hide"
	});
	$("#RegisterQueryAxisDiv").animate({
		width : "hide"
	});

});

$("#RegisterQueryByRegObjsBtn").click(function() {
	$("#RegisterQueryRegLocsDiv").animate({
		width : "hide"
	});
	$("#RegisterQueryRegObjsDiv").animate({
		width : "show"
	});
	$("#RegisterQueryAxisDiv").animate({
		width : "hide"
	});

});

$("#RegisterQueryBySpatialBtn").click(function() {
	$("#RegisterQueryRegLocsDiv").animate({
		width : "hide"
	});
	$("#RegisterQueryRegObjsDiv").animate({
		width : "hide"
	});
	$("#RegisterQueryAxisDiv").animate({
		width : "show"
	});

});
function toggleStat(opt){
	$("#"+opt+"Div").animate({
		width : "show"
	});
	$("#"+opt+"QueryDiv").animate({
		width : "hide"
	});
}

function toggleQuery(opt){
	$("#"+opt+"Div").animate({
		width : "hide"
	});
	$("#"+opt+"QueryDiv").animate({
		width : "show"
	});
}

function dailyPatrolStatPassResult(msg){
	
}

function json2Table(json,columns) {
    var tr;
    for (var i = 0; i < json.length; i++) {
        tr = $('<tr/>');
        for(var j=0; j<columns.length; j++)
        tr.append("<td>" + json[i][columns[j]] + "</td>");

        $('table').append(tr);
    }
}
//每个按钮拼装已有控件的字符串还是要写


