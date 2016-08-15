

require.config({  
	paths: {  
		echarts: './assets'  
	}  
});  

require(  
		[  
		 'echarts',  
		 'echarts/chart/bar',  
		 'echarts/chart/line',  
		 ],  
		 function (ec) {  
			
			//
			var serviceName;
			var serviceCount;
			 
			 
			//lastweekservice
			$.ajax({
				type : "POST",
				url : "fg/findServiceTotalChart.do",
				data : null,
				dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
				type : "POST",
				cache : false,
				success : function(data) {
					serviceName=data.serviceName;
					serviceCount=data.serviceCount;
						//alert(serviceName); 
						//alert(dataCount);
					    var option = {  
								title: {  
									text: '',  
									link: '#',  
									x: 'left',  
									y: 'top'  
								},  
								tooltip: {  
									trigger: 'item'  
								},  
								legend: {  
									show: true,  
									x: 'left',  
									y: 'top',  
									data: [ '服务访问次数']  
								},  
								toolbox: {  
									show: false,  
									feature: {  
										mark: {show: true},  
										dataZoom: {  
											show: true,  
											title: {  
												dataZoom: '区域缩放',  
												dataZoomReset: '区域缩放后退'  
											}  
										},  
										dataView: {show: true, readOnly: true},  
										magicType: {show: true, type: ['line', 'bar']},  
										restore: {show: true},  
										saveAsImage: {show: true}  
									}  
								},  
								calculable: false,  
								xAxis: [  
								        {  
								        	show: true,  
								        	type: 'category', 
								        	axisLabel:{
								        		interval:0,
								        		rotate:30,
								        		margin:2,
								        		textStyle:{
								        		color:"#0071C6"
								        			 
								        		}
								        		
								        		
								        	},
								        	data: serviceName  
								        }  
								        ],  
								        grid:{
								        	x:40,
								        	x2:100,
								        	y2:120
								        	
								        	
								        },
								        yAxis: [  
								                {  
								                	show: true,  
								                	type: 'value',  
								                	splitArea: {show: true}  
								                } 
								                ,{  
										        	show: false,  
										        	type: 'category',  
										        	data: serviceName
										        }  
								                ],  
								                
								                series: [  
								                           
								                         {  
								                        	 name: '服务访问次数',  
								                        	 type: 'bar',  
								                        	 data: serviceCount,  
								                        	 markPoint: {  
								                        		 data: [  
								                        		        {type: 'max', name: '最大值'},  
								                        		        {type: 'min', name: '最小值'}  
								                        		        ]  
								                        	 },  
								                        	 markLine: {  
								                        		 data: [  
								                        		        {type: 'average', name: '平均值'}  
								                        		        ]  
								                        	 }  
								                         }  
								                         ]  
						};  
						
					 
						var myChart = ec.init(document.getElementById('mainBar6'));  
					 
						 
						myChart.setOption(option);
						 
					 
				}});
			//
			
			
			 
			  
		}  
); 



