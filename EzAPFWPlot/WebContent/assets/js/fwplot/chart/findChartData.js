var lastweekdata;
var lastweekservice;
var lastweekfunction;
var lastweekapplytotal;
var highservice;
var poorservice;

function findChartData(){
	$.ajax({
		type : "POST",
		url : "fg/findTotalChartData.do",
		data : null,
		dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
		type : "POST",
		cache : false,
		success : function(data) {
				lastweekdata=data.lastweekdata;
				lastweekservice=data.lastweekservice;
				lastweekfunction=data.lastweekfunction;
				lastweekapplytotal=data.lastweekapplytotal;
				highservice=data.highservice;
				poorservice=data.poorservice;
				//近一周数据流量
				
				
		}});
	
	
	
}