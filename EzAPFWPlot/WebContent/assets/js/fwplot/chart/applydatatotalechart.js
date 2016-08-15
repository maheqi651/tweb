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
			
			var serviceName;
			var lastweekapplytotal;
			//lastweekservice
			$.ajax({
				type : "POST",
				url : "fg/findApplyTotalChart.do",
				data : null,
				dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
				type : "POST",
				cache : false,
				success : function(data) {
					    //alert(data);
					    lastweekapplytotal=data.lastweekapplytotal;
					    serviceName=data.serviceName;
						//alert(dataCount);
						option2 = {
							    title : {
							    	text: '近一周服务申请量排名'  
							    },
							    tooltip : {
							        trigger: 'axis'
							    },
							    legend: {
							        data:['服务申请流量']
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
							            axisLabel:{
							        		interval:0,
							        		rotate:30,
							        		margin:2,
							        		textStyle:{
							        		color:"#0071C6"
							        			 
							        		}
							        		
							        		
							        	},
							            data : serviceName
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
										'#87cefa', '#3cb371', '#cd5c5c', '#00fa9a', '#87cefa', '#db70d6',
										'#32cd32'  
										],
							    series : [
							        {
							            name:'服务申请流量',
							            type:'line',
							            data:lastweekapplytotal,
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
						
						
						 
						var myChart2 = ec.init(document.getElementById('mainBar13'));
						myChart2.setOption(option2);
						 
				}});
			
			//
				
		}  
); 



