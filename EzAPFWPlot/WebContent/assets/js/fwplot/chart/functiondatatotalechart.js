require.config({  
	paths: {  
		echarts: './assets'  
	}  
});  
require(  
		[  
		 'echarts',  
		 'echarts/chart/gauge'  
		 
		 ],  
		 function (ec) {  
			
			
			//
			
			var functionName;
			var lastweekfunction;
			//lastweekservice
			$.ajax({
				type : "POST",
				url : "fg/findFunctionTotalChart.do",
				data : null,
				dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
				type : "POST",
				cache : false,
				success : function(data) {
					    //alert(data);
					    lastweekfunction=data.lastweekfunction;
					    functionName=data.functionName;
					     
						//alert(dataCount);
						 
						option2 = {
							    title : {
							    	text: '近一周应用请求排名'  
							    },
							    tooltip : {
							        trigger: 'axis'
							    },
							    legend: {
							        data:['应用请求周流量']
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
							            data : functionName,
							            axisLabel:{
							        		interval:0,
							        		rotate:30,
							        		margin:2,
							        		textStyle:{
							        		color:"#0071C6"
							        		}
							        	}
							        }
							    ],
							    grid:{
						        	x:40,
						        	x2:100,
						        	y2:120
						        	
						        	
						        },
							    yAxis : [
							        {
							            type : 'value'
							        }
							    ],
							    color: [ 
										'#db70d6', '#3cb371', '#cd5c5c', '#00fa9a', '#87cefa', '#db70d6',
										'#32cd32'  
										],
							    series : [
							        {
							            name:'应用请求周流量',
							            type:'bar',
							            data:lastweekfunction,
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
							        }
							        
							    ]
							}; 
						
						
						 
						var myChart2 = ec.init(document.getElementById('mainBar6'));
						myChart2.setOption(option2);
						 
				}});
			
			//
				
		}  
); 



