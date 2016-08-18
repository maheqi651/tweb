/**
 * @author LuoJi
 */
//document.write("<script language='javascript' src='./jQuery.js'></script>");aa
//document.write("<script language='javascript' src='./common.js'></script>");
var hbaseMetaData;// to do改为按{}
var modalMappingInst;
var basepath;
var importingMappings = new Object();

function initdwr(path) {
	dwr.engine.setActiveReverseAjax(true);
	SendUtil.initPath(path);
	register();
}

function register(){
	SendUtil.regUser(function(data){
		$('sessionid').value = data;
	});
}

function initImportPage() {
	
	basepath = getBaseURL();
	initdwr(basepath);
//	tableMappingString = $.post(basepath+"/GetAllMappings");
	$.ajax({
		type: "POST",
		url: basepath+"/GetAllMappings",
		data:"dataType=json",
		dataType: "json",//此处只能填写json或text，否则回调函数无法正确执行
		success: function(rtJson){
			hbaseMetaData =rtJson;
			writePage();
		}
		});
	
//	SendUtil.regUser(function(data){//应该是回调函数
////		$('sessionid').value = data;
//		alert(data);
//		sessionIdValue = data;
//	});
}

function destroyImportPage(){
//	SendUtil.removeUser($('sessionid').value);
}

function writePage(){
	$("#hbaseTableName ul").append(getNaviBarHTML());
	$("#hbaseOracleMapping").append(getAccordionHTML());
	for ( var item in hbaseMetaData) {
//		$("#" + hbaseMetaData[item].hbaseTableName + " div:first-child").append(
//				getAccordinInnerHTML(hbaseMetaData[item]));
		$("#" + hbaseMetaData[item].hbaseTableName + " .accordion-group").append(
		getAccordinInnerHTML(hbaseMetaData[item]));
	}
	
	$('[data-spy="scroll"]').each(function () {//当监听和被监听内容发生改变时，需要调用此段代码刷新滚动监听
		  var $spy = $(this).scrollspy('refresh');//$(this)表示当前的jQuery对象
		  //美元符做变量名，表明这个是JQuery对象,尽在名字上区别于普通Javascript变量
		});
	
	$('#bsImportModal').on('hidden', function () {
		$("#checkOracleConnResult").text("");
		$("#bsBeginImport").attr({disabled:"disabled"});
		$("#bsBeginImport").attr({class:"btn"});
		$("#oraclePasswordInput").val("");
	});
	
	$('#bsTimeImportModal').on('hidden', function () {
		$("#checkTimeOracleConnResult").text("");
		$("#bsTimeBeginImport").attr({disabled:"disabled"});
		$("#bsTimeBeginImport").attr({class:"btn"});
		$("#timeOraclePasswordInput").val("");
		$("#startTime").val("");
		$("#endTime").val("");
	});
	
	$('#logModal').on('hidden', function () {
		$('#logModal .modal-body').text("请稍候...")
	});
}

function getNaviBarHTML() {
	var naviBarHTML = "";
	for ( var item in hbaseMetaData) {
		naviBarHTML += "<li><a href='#" + hbaseMetaData[item].hbaseTableName
				+ "'>" + hbaseMetaData[item].hbaseTableName + "</a></li>";
	}

	return naviBarHTML;
	// return "<li><a href='#reg02'>reg02</a></li>";
}

function getAccordionHTML() {
	var accordionHTML = "";
	for ( var item in hbaseMetaData) {
		accordionHTML += 
//			"<p>" + hbaseMetaData[item].hbaseTableName + "</p>"+
				 "<div class='accordion' id='"
				+ hbaseMetaData[item].hbaseTableName
				+ "'><br><br><h4>" + hbaseMetaData[item].hbaseTableName + "</h4>"+
						"<div class='accordion-group'></div></div>";
	}
	return accordionHTML.replace(/<br>/, "<h4>映射列表，按HBase表顺序排列，请选择您需要的操作，可以点击左侧导航条跳到" +
			"相应的HBase表的映射位置：</h4>");
}

function getAccordinInnerHTML(mappingItem) {//每次调用此方法对应一个映射
	/*
	 * return "<h2>aa\ <\h2>";
	 */
//alert(mappingItem.mappings[item].dataType+"','"
//		+mappingItem.hbaseTableName+"','"+mappingItem.mappings[item].OracleTableName+"'," +
//		"'"+mappingItem.mappings[item].OracleUsername+"','"+mappingItem.mappings[item].OracleURL+
//		"','"+mappingItem.mappings[item].createType+"','"+0);
	var string4rtObject = "";
	for(var item in mappingItem.mappings){
		if(mappingItem.mappings[item].createType!="stream"){
		var curMappingID=mappingItem.hbaseTableName + "-"+ mappingItem.mappings[item].OracleTableName;
		string4rtObject+=  "<div class='accordion-heading contain-buttons'><div class='pseudo-accordion-toggle'>\
		"+ curMappingID
			+ "</div>\
		<div class='line-end'>\
		<button class='btn btn-info' data-toggle='collapse'\
			data-target='#collapse"+ curMappingID+"'>打开/关闭 详细信息</button>\
		<div class='btn-group'>\
			<a class='btn dropdown-toggle' data-toggle='dropdown'\
				href='#'> 操作 <span class='caret'></span>\
			</a>\
			<ul class='dropdown-menu'>\
				<li><a href='javascript:void(0);' onclick=openBSImportModal('"+mappingItem.mappings[item].dataType+"','"
					+mappingItem.hbaseTableName+"','"+mappingItem.mappings[item].OracleTableName+"'," +
						"'"+mappingItem.mappings[item].OracleUsername+"','"+mappingItem.mappings[item].OracleURL+
						"','"+mappingItem.mappings[item].createType+"','"+getTimeType(mappingItem.mappings[item],0)+"') > 导入</a></li>\
				<li><a href='javascript:void(0);' onclick=openBSTimeImportModal('"+mappingItem.mappings[item].dataType+"','"
					+mappingItem.hbaseTableName+"','"+mappingItem.mappings[item].OracleTableName+"'," +
						"'"+mappingItem.mappings[item].OracleUsername+"','"+mappingItem.mappings[item].OracleURL+
						"','"+mappingItem.mappings[item].createType+"','"+getTimeType(mappingItem.mappings[item],1)+"') >导入时段数据</a></li>\
				<li class='divider'></li>\
				<li><a href='javascript:void(0);' onclick=openImportLogModal('"+curMappingID+"')> 查看日志</a></li>\
				<li><a href='javascript:void(0);' onclick=delMapping('"+mappingItem.mappings[item].OracleURL+
						"','"+mappingItem.mappings[item].OracleUsername+"','"+mappingItem.mappings[item].OracleTableName+"','"+
						mappingItem.hbaseTableName+"')> 删除</a></li>\
			</ul>\
		</div>\
		<!-- class='btn-group' -->\
	</div>\
		<div class='progress borderbar progress-striped active' id='"+curMappingID+"BarSurround' style='"+getBarDisplay(curMappingID)+"'>\
			<div class='bar' style='width: 0%;' id='"+curMappingID+"Bar'></div>\
			<label class='absolute' id='"+curMappingID+"Progress'>进度0</label>\
		</div>\
</div><!-- accordion-heading -->\
<div id='collapse"+ curMappingID+"' class='accordion-body in collapse'><!--class中的in决定初始状态是打开还是关闭-->\
	<div class='accordion-inner'>\
		<p>数据来源类型："+mappingItem.mappings[item].createType+"</p>\
		<p>数据模型："+mappingItem.mappings[item].dataType+"</p>\
		<p>链接地址："+mappingItem.mappings[item].OracleURL+"</p>\
		<p>用户名："+mappingItem.mappings[item].OracleUsername+"</p>\
	</div>\
</div>";
		}
	}
	return new Object(string4rtObject);
}

function getBarDisplay(curMappingID){
	if($.cookie(curMappingID)!=null){
		return "display:block";
	}
	else return "display:none";
}

function getTimeType(ob, type){
	if(ob.dataType=="regLoc"){
		return 2;
	}
//	else if(!ob.beginTime && !ob.endTime){
//		return 0;
//	}
//	else return 1;
	else return type;
}

function openBSImportModal(dataType,hbaseTableName,
		oracleTableName,oracleUserName,oracleURL,createType,timeType){
	if($.cookie(hbaseTableName+"-"+oracleTableName) !=null){//当前映射有导入时，不允许开新的导入
		alert("该映射目前正在导入数据，不允许开新的导入，请等待导入完成。");
	}
	else{
	modalMappingInst = {
			dataType : dataType,
			hbaseTableName : hbaseTableName,
			oracleTableName : oracleTableName,
			oracleUsername : oracleUserName,
			oracleURL : oracleURL,
			createType : createType,
			timeType : timeType
	};
	$('#bsImportModal').modal('toggle');
	$('#oracleUserNameInModal').text(oracleUserName);
	}
}

function openBSTimeImportModal(dataType,hbaseTableName,
		oracleTableName,oracleUserName,oracleURL,createType,timeType){
	if($.cookie(hbaseTableName+"-"+oracleTableName) !=null){
		alert("该映射目前正在导入数据，不允许开新的导入，请等待导入完成。");
	}
	else if(hbaseTableName.indexOf("-RegLoc")>0){
		alert("登记类地理位置信息表不支持导入指定时段的数据，请选择其他操作");
	}
	else{
	modalMappingInst = {
			dataType : dataType,
			hbaseTableName : hbaseTableName,
			oracleTableName : oracleTableName,
			oracleUsername : oracleUserName,
			oracleURL : oracleURL,
			createType : createType,
			timeType : timeType
	};
	$('.form_datetime').datetimepicker({//参见http://www.bootcss.com/p/bootstrap-datetimepicker/
		format : 'yyyy/mm/dd hh:ii:ss',
		autoclose : true,
		todayBtn : true,
		pickerPosition : "bottom-left",
		language : "zh-CN",
		weekStart : 0,
		minView : "hour",
		todayHighligh : "true"
	});
//	$('#endDatetimepicker').datetimepicker({
//		format : 'MM/dd/yyyy hh:ii',
//		language : 'zh-CN',
//		pickDate : true,
//		pickTime : true,
//		hourStep : 1,
//		minuteStep : 15,
//		secondStep : 30,
//		inputMask : true
//	});
	$('#bsTimeImportModal').modal('toggle');
	$('#oracleTimeUserNameInModal').text(oracleUserName);
	}//else
}

function checkImportPassword(){
	
	$.ajax({
		type: "POST",
		url: basepath+"/TestOracleConn",
		data:"username="+modalMappingInst.oracleUsername+"&type=normal&url="
			+modalMappingInst.oracleURL+"&password="+$('#oraclePasswordInput').val(),//jQuery获取input输入的方式，与js不同
		dataType: "text",//此处只能填写json或text，否则回调函数无法正确执行
		success: function(rtText){
			if(rtText=="false"){
				$("#checkOracleConnResult").css({color: "red"});
				$("#checkOracleConnResult").text("密码输入错误，请重试");
				$("#bsBeginImport").attr({disabled:"disabled"});
				$("#bsBeginImport").attr({class:"btn"});
			}
			else{
				$("#checkOracleConnResult").css({color: "green"});
				$("#checkOracleConnResult").text("密码正确，可以导入");
				$("#bsBeginImport").removeAttr("disabled");
				$("#bsBeginImport").attr({class:"btn btn-success"});
//				$("#bsBeginImport").animate({ backgroundColor: "yellow" }, 1000);
			}
		}
		});
}

function checkTimeImportPassword(){
	
	$.ajax({
		type: "POST",
		url: basepath+"/TestOracleConn",
		data:"username="+modalMappingInst.oracleUsername+"&type=normal&url="
			+modalMappingInst.oracleURL+"&password="+$('#timeOraclePasswordInput').val(),//jQuery获取input输入的方式，与js不同
		dataType: "text",//此处只能填写json或text，否则回调函数无法正确执行
		success: function(rtText){
			if(rtText=="false"){
				$("#checkTimeOracleConnResult").css({color: "red"});
				$("#checkTimeOracleConnResult").text("密码输入错误，请重试");
				$("#bsTimeBeginImport").attr({disabled:"disabled"});
				$("#bsTimeBeginImport").attr({class:"btn"});
			}
			else{
				$("#checkTimeOracleConnResult").css({color: "green"});
				$("#checkTimeOracleConnResult").text("密码正确，可以导入");
				$("#bsTimeBeginImport").removeAttr("disabled");
				$("#bsTimeBeginImport").attr({class:"btn btn-success"});
//				$("#bsBeginImport").animate({ backgroundColor: "yellow" }, 1000);
			}
		}
		});
}

function bsBeginImport(){
	$.ajax({
		type: "POST",
		url: basepath+"/BeginImport",
		data:"dataType="+modalMappingInst.dataType+"&oracleUrl="+modalMappingInst.oracleURL
		+"&hbaseTableName="+modalMappingInst.hbaseTableName+
		"&oracleTableName="+modalMappingInst.oracleTableName+"&oracleUsername="+modalMappingInst.oracleUsername
		+"&createType="+modalMappingInst.createType+"&oraclePassword="+$('#oraclePasswordInput').val()
		+"&timeType="+modalMappingInst.timeType,
		success:function(rtText){
			var cookieName = rtText.split(" ")[0];
			$.cookie(cookieName, null);//删除cookie
		}
	});
	
	
	$.ajax({
		type: "POST",
		url: basepath+"/GetImportNumbers",
		data:"dataType="+modalMappingInst.dataType+"&oracleUrl="+modalMappingInst.oracleURL
		+"&hbaseTableName="+modalMappingInst.hbaseTableName+
		"&oracleTableName="+modalMappingInst.oracleTableName+"&oracleUsername="+modalMappingInst.oracleUsername
		+"&createType="+modalMappingInst.createType+"&oraclePassword="+$('#oraclePasswordInput').val()
		+"&timeType="+modalMappingInst.timeType,
		dataType: "text",
		success: function(rtText) {
			modalMappingInst.totalNumbers = rtText;
			var curMappingID = modalMappingInst.hbaseTableName + "-"+ modalMappingInst.oracleTableName;
//			importingMappings[curMappingID]= modalMappingInst;
			$.cookie(curMappingID,rtText);//cookie的值必须是字符串
			//建议cookie的键使用curMappingID，生成页面时，对元数据的对象进行遍历，
			//若$.cookie(curMappingID)为null，则不加该映射的进度条，否则添加进度条。
			
//			alert(rtText);
//			alert(importingMappings[curMappingID].totalNumbers);
			//如果这个地方会由于下个导入的太快而使curMappingID出现错误，则考虑把$('#bsImportModal').modal('hide');放到这里

			$('#bsImportModal').modal('hide');
			$("#"+curMappingID+"BarSurround").show();
		}
	});
	$("#bsBeginImport").attr({disabled:"disabled"});
	$("#checkOracleConnResult").css({color: "blue"});
	$("#checkOracleConnResult").html("正在努力为您加载进度条，请耐心等待……");
	$("#checkOracleConnResult").append(new Object("<img src='./img/loading.gif' height='50px' width='50px'/>"));
	//TO DO 加一个苹果的等待转圈的gif
	
	
}

function bsTimeBeginImport(){
	var startTimeString = $('#startTime').val().replace(/[^0-9]/g,"");
	var endTimeString =$('#endTime').val().replace(/[^0-9]/g,"");
	
	$.ajax({
		type: "POST",
		url: basepath+"/BeginImport",
		data:"dataType="+modalMappingInst.dataType+"&oracleUrl="+modalMappingInst.oracleURL
		+"&hbaseTableName="+modalMappingInst.hbaseTableName+
		"&oracleTableName="+modalMappingInst.oracleTableName+"&oracleUsername="+modalMappingInst.oracleUsername
		+"&createType="+modalMappingInst.createType+"&oraclePassword="+$('#timeOraclePasswordInput').val()
		+"&timeType="+modalMappingInst.timeType+"&beginTime="+startTimeString
		+"&endTime="+endTimeString,
		success:function(rtText){
			var cookieName = rtText.split(" ")[0];
			$.cookie(cookieName, null);
			
		}
	
	});
	
	
	$.ajax({
		type: "POST",
		url: basepath+"/GetImportNumbers",
		data:"dataType="+modalMappingInst.dataType+"&oracleUrl="+modalMappingInst.oracleURL
		+"&hbaseTableName="+modalMappingInst.hbaseTableName+
		"&oracleTableName="+modalMappingInst.oracleTableName+"&oracleUsername="+modalMappingInst.oracleUsername
		+"&createType="+modalMappingInst.createType+"&oraclePassword="+$('#timeOraclePasswordInput').val()
		+"&timeType="+modalMappingInst.timeType+"&beginTime="+startTimeString
		+"&endTime="+endTimeString,
		dataType: "text",
		success: function(rtText) {
			modalMappingInst.totalNumbers = rtText;
			var curMappingID = modalMappingInst.hbaseTableName + "-"+ modalMappingInst.oracleTableName;
//			importingMappings[curMappingID]= modalMappingInst;
			$.cookie(curMappingID,rtText);
//			alert(rtText);
//			alert(importingMappings[curMappingID].totalNumbers);
			//如果这个地方会由于下个导入的太快而使curMappingID出现错误，则考虑把$('#bsImportModal').modal('hide');放到这里

			$('#bsTimeImportModal').modal('hide');
			$("#"+curMappingID+"BarSurround").show();
		}
	});
	$("#bsTimeBeginImport").attr({disabled:"disabled"});
	$("#checkTimeOracleConnResult").css({color: "blue"});
	$("#checkTimeOracleConnResult").html("正在努力为您加载进度条，请耐心等待……");
	$("#checkTimeOracleConnResult").append(new Object("<img src='./img/loading.gif' height='50px' width='50px'/>"));

	
}

function updateProgressBar(mappingID){
	alert(mappingID);
}

function testGlobalParam(){
//	SendUtil.regUser(function(data){
//		$('sessionid').value = data;
//	});
	var countInfo=eval("({name : 'local-GA_GPS_HISTORY_2012_04_02',count : 20000})");
	alert(countInfo.count);
}	
	function testGlobalParam1(){
		SendUtil.sendMes("aaa");

}
function getContentHTML() {

}

function receiveMsg(data){
//	var aa= $("#tts").text();
//	$("#tts").text(aa+data);
	var countInfo = eval("(" + data + ")");
	if($.cookie(countInfo.name)!=null){
	
	$("#"+countInfo.name+"Progress").text("进度"+countInfo.count+"/"+$.cookie(countInfo.name));
	
	var percent = countInfo.count/parseInt($.cookie(countInfo.name))*100;
	$("#"+countInfo.name+"Bar").css("width",percent+"%");
	}
	else{
		$("#"+countInfo.name+"Progress").text("完成");
		$("#"+countInfo.name+"Bar").css("width","100%");
	}
}

function delMapping(url,username,tablename,hbasetable){
	if(confirm("需要删除此映射吗？")){
		$.ajax({
			type: "POST",
			url: basepath+"/DelMappings",
			data:"mappings2DelString="+url+","+username+","+tablename+","+hbasetable+";",//jQuery获取input输入的方式，与js不同
			dataType: "text",//此处只能填写json或text，否则回调函数无法正确执行
			success: function(rtText){
				window.location.reload();
			}
			});
	}
}

function openImportLogModal(curMappingID){
	$('#logModal').modal('toggle');
	$.ajax({
		type: "POST",
		url: basepath+"/GetImportLog",
		data:"curMappingID="+curMappingID+"&lineNumber=100",//jQuery获取input输入的方式，与js不同
		dataType: "text",//此处只能填写json或text，否则回调函数无法正确执行
		success: function(rtText){
			rtText=rtText.replace(/INFO/g, "<br/>")
			$('#logModal .modal-body').html(rtText);
		}
		});
}
//function getHBaseMetaData() {
//	
////	var tableMappinginstArray = new 
//
//	
//	
//	var tableMappinginst = new Object();
//	tableMappinginst.hbaseTableName = "gps02";
//	tableMappinginst.mappings = new Array();
//	tableMappinginst.mappings[0] = {
//		createType : "Oracle",
//		dataType : "trace",
//		oracleURL : "jdbc:oracle:thin:@localhost:1521:orcl",
//		oracleTableName : "GPS_201203012",
//		oracleUsername : "hadoop"
//	};
//	
//	tableMappinginst.mappings[1] = {
//			createType : "Oracle",
//			dataType : "trace",
//			oracleURL : "jdbc:oracle:thin:@172.18.70.128:1521:orcl",
//			oracleTableName : "GPS_201203015",
//			oracleUsername : "hadoop"
//		};
//	var tableMappinginst1 = new Object();
//	tableMappinginst1.hbaseTableName = "gps03";
//	tableMappinginst1.mappings = new Array();
//	tableMappinginst1.mappings[0] = {
//		createType : "Oracle",
//		dataType : "trace",
//		oracleURL : "jdbc:oracle:thin:@localhost:1521:orcl",
//		oracleTableName : "GPS_201203016",
//		oracleUsername : "hadoop"
//	};
//
//	/*
//	 * var dataType = document.getElementById('dataType'+index).innerHTML; var
//	 * oracleURL = document.getElementById('OracleURL'+index).innerHTML; var
//	 * hbaseTableName =
//	 * document.getElementById('HBaseTableName'+index).innerHTML; var
//	 * oracleTableName =
//	 * document.getElementById('OracleTableName'+index).innerHTML; var
//	 * oracleUsername =
//	 * document.getElementById('OracleUsername'+index).innerHTML; var createType =
//	 * document.getElementById('createType'+index).innerHTML;
//	 */
//
//	hbaseMetaData.push(tableMappinginst);
//	hbaseMetaData.push(tableMappinginst1);
//	// tableMappinginst.hbaseTableName="gps03";
//	//	hbaseMetaData.push(tableMappinginst);
//
//};                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           