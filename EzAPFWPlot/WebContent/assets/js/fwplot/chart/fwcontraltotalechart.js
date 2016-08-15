require.config({  
	paths: {  
		echarts: './assets'  
	}  
});  
function loadtotal(){
	 
		   var nd = new Date();
		   nd1 = nd.valueOf();
		   nd = nd.valueOf();
		   nd = nd - 7 * 24 * 60 * 60 * 1000;
		   nd = new Date(nd);
		   //alert(nd.getFullYear() + "年" + (nd.getMonth() + 1) + "月" + nd.getDate() + "日");
		var y = nd.getFullYear();
		var m = nd.getMonth()+1;
		var d = nd.getDate();
		if(m <= 9) m = "0"+m;
		if(d <= 9) d = "0"+d; 
		var cdate = y+"-"+m+"-"+d;
		 nd1 = new Date(nd1);
		 var y = nd1.getFullYear();
			var m = nd1.getMonth()+1;
			var d= nd1.getDate();
			if(m <= 9) m = "0"+m;
			if(d <= 9) d= "0"+d; 
			var cdate2 = y+"-"+m+"-"+d;
			//alert(cdate);
			//alert(cdate2);
			 $("#startdate3").val(cdate);
			 $("#enddate3").val(cdate2);
			 $("#startdate").val(cdate);
			 $("#enddate").val(cdate2);
			 $("#startdate1").val(cdate);
			 $("#enddate1").val(cdate2);
			 searchFunctionTotal();
			 searchtotalService();
			 searchSuccessRate();
			 
}
function loadfunction(){
	$.ajax({
		type : "POST",
		url : "fg/searchFunction.do",
		data : null,
		dataType :"json",
		type : "POST",
		cache : false,
		success : function(data) {
				var result=data.result;
				var htmls="<option value='all'>所有应用</option>";
                $("#functionlist").html(htmls);
				//<option>应用</option>functionlist
		        for(to in result)
		        {
		        	htmls+="<option value='"+result[to].code+"'>"+result[to].name+"</option>";
		        	
		        }
		        $("#functionlist").html("");
		        $("#functionlist").html(htmls);
		}});
	
	
	
}
function searchFunctionTotal(){
	    var selectfucntion= $("#functionlist").val();
	    //alert(selectfucntion);
	    var  startdate= $("#startdate3").val();
	    var  enddate= $("#enddate3").val();
		var keyword=null;
		var param =new Object();
		param.functioncode=selectfucntion;
		if(startdate!=null&&startdate!="")
		{
		   param.REQEUST_DATETIME_start=startdate;
		}else{
			alert("开始时间不可以为空！");
			 return ;
		}
		if(enddate!=null&&enddate!="")
		{
		   param.REQEUST_DATETIME_end=enddate;
		}else{
			alert("结束时间不可以为空！");
			 return ;
		}
		
		var start  = new Date(startdate.replace(/-/g,"/")).getTime();
		var end = new Date(enddate.replace(/-/g,"/")).getTime();
		if(end - start>30*24*60*60*1000||end - start<0)
			{
			  alert("时间间隔不可以大于一个月并且不可以小于一天");
			  return ;
			}
		 
		
		$.ajax({
			type : "POST",
			url : "fg/searchFunctionTotal.do",
			data : param,
			dataType :"json",
			type : "POST",
			cache : false,
			success : function(data) {
				var datacount=data.datacount;
				var servicecount=data.servicecount;
				var daytimes=data.daytimes;
				//alert(daytimes);
				require(  
						[  
						 'echarts',  
						 'echarts/chart/bar',  
						 'echarts/chart/line',  
						 ],  
						 function (ec) {  
										option = {
											    title : {
											        text: '服务流量统计',
											        subtext: '按时间段统计分析'
											    },
											    tooltip : {
											        trigger: 'axis'
											    },
											    legend: {
											        data:['数据流量','服务访问量']
											    },
											    toolbox: {
											        show : true,
											        feature : {
											            mark : {show: true},
											            dataView : {show: true, readOnly: false},
											            magicType : {show: true, type: ['line', 'bar']},
											            restore : {show: true},
											       
										            
											            saveAsImage : {show: true}
											        }
											    },
											    calculable : false,
											    xAxis : [
											        {
											            type : 'category',
											            data : daytimes
											        }
											    ],
											    yAxis : [
											        {
											            type : 'value'
											        }
											    ], 
											    color: [ 
														  '#cd5c5c', '#00fa9a', '#87cefa', '#db70d6',
														'#32cd32'  
														],
											    series : [
											        {
											            name:'数据流量',
											            type:'bar',
											            data:datacount,
											            markPoint : {
											                data : [
											                    {type : 'max', name: '最大值'},
											                    {type : 'min', name: '最小值'}
											                ]
											            },
											            markLine : {
											                data : [
											                    {type : 'average', name: '平均值'}
											                ]
											            }
											        },
											        {
											            name:'服务访问量',
											            type:'bar',
											            data:servicecount,
											            markPoint : {
											                data : [
											                        {type : 'max', name: '最大值'},
												                    {type : 'min', name: '最小值'}
											                ]
											            },
											            markLine : {
											                data : [
											                    {type : 'average', name : '平均值'}
											                ]
											            }
											        }
											    ]
											};
										var myChart = ec.init(document.getElementById('mainBar3'));  
										myChart.setOption(option);
						}  
				); 
			}
		   });
}

function searchtotalService(){
	   //alert();
	    var  startdate= $("#startdate").val();
	    var  enddate= $("#enddate").val();
		var keyword=null;
		var param =new Object();
		if(startdate!=null&&startdate!="")
		{
		   param.REQEUST_DATETIME_start=startdate;
		}else{
			alert("开始时间不可以为空！");
			 return ;
		}
		if(enddate!=null&&enddate!="")
		{
		   param.REQEUST_DATETIME_end=enddate;
		}else{
			alert("结束时间不可以为空！");
			 return ;
		}
		
		var start  = new Date(startdate.replace(/-/g,"/")).getTime();
		var end = new Date(enddate.replace(/-/g,"/")).getTime();
		if(end - start>30*24*60*60*1000||end - start<0)
			{
			  alert("时间间隔不可以大于一个月并且不可以小于一天");
			  return ;
			}
		 
		
		$.ajax({
			type : "POST",
			url : "fg/findFWControlService.do",
			data : param,
			dataType :"json",
			type : "POST",
			cache : false,
			success : function(data) {
				/*map.put("datacount", datacount);
				map.put("servicecount",servicecount);
				map.put("daytimes", daytimes);*/
				var datacount=data.datacount;
				var servicecount=data.servicecount;
				var daytimes=data.daytimes;
				//alert(daytimes);
				require(  
						[  
						 'echarts',  
						 'echarts/chart/bar',  
						 'echarts/chart/line',  
						 ],  
						 function (ec) {  
										option = {
											    title : {
											        text: '服务流量统计',
											        subtext: '按时间段统计分析'
											    },
											    tooltip : {
											        trigger: 'axis'
											    },
											    legend: {
											        data:['数据流量','服务访问量']
											    },
											    toolbox: {
											        show : true,
											        feature : {
											            mark : {show: true},
											            dataView : {show: true, readOnly: false},
											            magicType : {show: true, type: ['line', 'bar']},
											            restore : {show: true},
											       
										            
											            saveAsImage : {show: true}
											        }
											    },
											    calculable : false,
											    xAxis : [
											        {
											            type : 'category',
											            data : daytimes
											        }
											    ],
											    yAxis : [
											        {
											            type : 'value'
											        }
											    ],
											    series : [
											        {
											            name:'数据流量',
											            type:'bar',
											            data:datacount,
											            markPoint : {
											                data : [
											                    {type : 'max', name: '最大值'},
											                    {type : 'min', name: '最小值'}
											                ]
											            },
											            markLine : {
											                data : [
											                    {type : 'average', name: '平均值'}
											                ]
											            }
											        },
											        {
											            name:'服务访问量',
											            type:'bar',
											            data:servicecount,
											            markPoint : {
											                data : [
											                        {type : 'max', name: '最大值'},
												                    {type : 'min', name: '最小值'}
											                ]
											            },
											            markLine : {
											                data : [
											                    {type : 'average', name : '平均值'}
											                ]
											            }
											        }
											    ]
											};
										var myChart = ec.init(document.getElementById('mainBar1'));  
										myChart.setOption(option);
						}  
				); 
			}
		   });
}


function searchSuccessRate(){
	//  alert($("#enddate1").val());
    var  startdate= $("#startdate1").val();
    var  enddate= $("#enddate1").val();
	var keyword=null;
	var param =new Object();
	if(startdate!=null&&startdate!="")
	{
	   param.REQEUST_DATETIME_start=startdate;
	}else{
		 alert("开始时间不可以为空！");
		 return ;
	}
	if(enddate!=null&&enddate!="")
	{
	   param.REQEUST_DATETIME_end=enddate;
	}else{
		alert("结束时间不可以为空！");
		 return ;
	}
	
	var start  = new Date(startdate.replace(/-/g,"/")).getTime();
	var end = new Date(enddate.replace(/-/g,"/")).getTime();
	if(end - start>30*24*60*60*1000||end - start<0)
		{
		  alert("时间间隔不可以大于一个月并且不可以小于一天");
		  return ;
		}
	 
	
	$.ajax({
		type : "POST",
		url : "fg/searchSuccessRate.do",
		data : param,
		dataType :"json",
		type : "POST",
		cache : false,
		success : function(data) {
			var requestcount=data.requestcount;
			var successrate=data.successrate;
			var daytimes=data.daytimes;
			//alert(daytimes);
			require(  
					[  
					 'echarts',  
					 'echarts/chart/bar',  
					 'echarts/chart/line',  
					 ],  
					 function (ec) {  
									option = {
										    title : {
										        text: '服务成功率统计',
										        subtext: '按时间段统计分析'
										    },
										    tooltip : {
										        trigger: 'axis'
										    },
										    legend: {
										        data:['服务成功率','服务总访问次数']
										    },
										    toolbox: {
										        show : true,
										        feature : {
										            mark : {show: true},
										            dataView : {show: true, readOnly: false},
										            magicType : {show: true, type: ['line', 'bar']},
										            restore : {show: true},
										       
									            
										            saveAsImage : {show: true}
										        }
										    },
										    calculable : true,
										    xAxis : [
										        {
										            type : 'category',
										            data : daytimes
										        }
										    ],yAxis: [  
										                {  
										                	show: true,  
										                	type: 'value',  
										                	splitArea: {show: true}  
										                } 
										                
										                ], 
										    color: [ 
													'#db70d6', '#3cb371', '#cd5c5c', '#00fa9a', '#87cefa', '#db70d6',
													'#32cd32'  
													]
										    ,
										    series : [
										        {
										            name:'服务成功率',
										            type:'bar',
										            data:successrate,
										            markPoint : {
										                data : [
										                    {type : 'max', name: '最大值'},
										                    {type : 'min', name: '最小值'}
										                ]
										            },
										            markLine : {
										                data : [
										                    {type : 'average', name: '平均值'}
										                ]
										            }
										        },
										        {
										            name:'服务总访问次数',
										            type:'bar',
										            data:requestcount,
										            markPoint : {
										                data : [
										                        {type : 'max', name: '最大值'},
											                    {type : 'min', name: '最小值'}
										                ]
										            },
										            markLine : {
										                data : [
										                    {type : 'average', name : '平均值'}
										                ]
										            }
										        }
										    ]
										};
									var myChart = ec.init(document.getElementById('mainBar2'));  
									myChart.setOption(option);
					}  
			); 
		}
	   });
}

