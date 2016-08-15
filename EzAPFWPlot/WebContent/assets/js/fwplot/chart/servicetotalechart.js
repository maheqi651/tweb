 
require(  
		[  
		 'echarts',  
		 'echarts/chart/bar',  
		 'echarts/chart/line',
		 'echarts/chart/pie',
		 ],  
		 function (ec) {  
			
			
			
			
			
			//
			
			var serviceName;
			var serviceCount;
			var lastweekService;
			//lastweekservice
			$.ajax({
				type : "POST",
				url : "fg/findServiceTotalChart.do",
				data : null,
				dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
				type : "POST",
				cache : false,
				success : function(data) {
					    //alert(data);
					    lastweekservice=data.lastweekservice;
					    serviceName=data.serviceName;
					    serviceCount=data.serviceCount;
						//alert(dataCount);
						/*option = {
								 
							    title : {
							        text: '近一周服务资源访问次数排名',
							        x:'center'
							    },
							    tooltip : {
							        trigger: 'item',
							        formatter: "{a} <br/>{b} : {c} ({d}%)"
							    },
							    legend: {
							        orient : 'vertical',
							        x : 'left',
							        data:serviceName
							    },
							    calculable : false,
							    series : [
							        {
							            name:'访问来源',
							            type:'pie',
							            radius : '55%',
							            center: ['50%', 225],
							            data:lastweekservice
							        }
							    ]
							};*/
						option2 = {
							    title : {
							    	 text: '近一周服务资源访问次数排名'
							    },
							    tooltip : {
							        trigger: 'axis'
							    },
							    legend: {
							        data:['服务访问周流量']
							    },
							    toolbox: {
							        show : true,
							        feature : {
							            mark : {show: true},
							            dataView : {show: true, readOnly: false},
							            magicType : {show: true, type: ['bar','line']},
							            restore : {show: true},
							            saveAsImage : {show: true}
							        }
							    },
							    calculable : false,
							    xAxis : [
							        {
							            type : 'category',
							            data : serviceName,
							            axisLabel:{
							        		interval:0,
							        		rotate:30,
							        		margin:2,
							        		textStyle:{
							        		color:"#0071C6"
							        			 
							        		}
							        		
							        		
							        	},
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
			                            '#cd5c5c' 
					        	       ],
							    series : [
							        {
							            name:'服务访问周流量',
							            type:'bar',
							            data:serviceCount,
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
						
						
						 
						/*var myChart = ec.init(document.getElementById('mainBar1'));*/
						var myChart2 = ec.init(document.getElementById('mainBar2'));
						myChart2.setOption(option2);
						/*myChart.setOption(option);*/
						/*myChart.connect(myChart2);
						myChart2.connect(myChart);*/

						setTimeout(function (){
						    window.onresize = function () {
						        /*myChart.resize();*/
						        myChart2.resize();
						    }
						},200)
				}});
			//
			  
		 
	
  
		});
                    