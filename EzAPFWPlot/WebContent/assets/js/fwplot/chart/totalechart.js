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
			var poorserviceName;
			var poorserviceCount;
			var highserviceName;
			var highserviceCount;
			 
			//lastweekservice
			$.ajax({
				type : "POST",
				url : "fg/findHighPoorTotalChart.do",
				data : null,
				dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
				type : "POST",
				cache : false,
				success : function(data) {
						poorserviceName=data.poorserviceName;
						poorserviceCount=data.poorserviceCount;
						highserviceName=data.highserviceName;
						highserviceCount=data.highserviceCount;
						//alert(poorserviceName);
						//alert(dataCount);
					    var option = {  
								title: {  
									text: '优质服务排名前十',  
									link: '#',  
									x: 'left',  
									y: 'top'  
								},  
								tooltip: {  
									trigger: 'item'  
								},  
								legend: {  
									show: true,  
									x: 'center',  
									y: 'top',  
									data: [ '服务访问次数']  
								},  
								toolbox: {  
									show: true,  
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
								        		rotate:45,
								        		margin:2,
								        		textStyle:{
								        		color:"#0071C6"
								        			 
								        		}
								        		
								        		
								        	},
								        	data: highserviceName  
								        }  
								        ],  
								        grid:{
								        	x:40,
								        	x2:100,
								        	y2:150
								        	
								        	
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
										        	data: highserviceName
										        }  
								                ],  
								                series: [  
								                           
								                         {  
								                        	 name: '服务访问次数',  
								                        	 type: 'bar',  
								                        	 data: highserviceCount,  
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
						
						/*var option2 = {  
								title: {  
									text: '劣质服务排行前十',  
									link: '#',  
									x: 'left',  
									y: 'top'  
								},  
								tooltip: {  
									trigger: 'item'  
								},  
								legend: {  
									show: true,  
									x: 'center',  
									y: 'top',  
									data: ['服务访问次数']  
								},  
								toolbox: {  
									show: true,  
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
								        		rotate:45,
								        		margin:2,
								        		textStyle:{
								        		color:"#0071C6"
								        			 
								        		}
								        		
								        		
								        	},
								        	 
								        	data: poorserviceName
								        }  
								        ],   
								        grid:{
								        	x:40,
								        	x2:120,
								        	y2:150
								        	
								        	
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
										        	data: poorserviceName
										        }  
								                ], 
								               
											    color: [ 
							                            'green' 
									        	       ],

								                series: [  
								                           
								                         {  
								                        	 name: '服务访问次数',  
								                        	 type: 'line',  
								                        	 data: poorserviceCount,  
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
						};  */
						
						var myChart = ec.init(document.getElementById('mainBar11'));  
						/*var myChart2 = ec.init(document.getElementById('mainBar12'));*/
						//myChart2.setOption(option2);
						myChart.setOption(option);
						//myChart.connect(myChart2);
						//myChart2.connect(myChart); 
					 
				}});
			//
			
			
			 
			  
		}  
); 



