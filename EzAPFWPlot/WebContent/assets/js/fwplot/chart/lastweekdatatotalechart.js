 
require(  
		[ 'echarts',  
		 'echarts/chart/bar',  
		 'echarts/chart/line',
		 'echarts/chart/pie',
		 ],  
		 function (ec) {  
			var weekName;
			var dataCount;
			var lastweekdata;
			$.ajax({
				type : "POST",
				url : "fg/findLastWeekData.do",
				data : null,
				dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
				type : "POST",
				cache : false,
				success : function(data) {
					    //alert(data);
						lastweekdata=data.lastweekdata;
						weekName=data.weekName;
						dataCount=data.dataCount;
						//alert(dataCount);
						option = {
							    title : {
							        text: '近一周数据流量统计' 
							       
							    },
							    tooltip : {
							        trigger: 'axis'
							    },
							    legend: {
							    	x: 'right',  
							        data:['日流量']
							    },
							    toolbox: {
							        show : false,
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
							            data : weekName
							        }
							    ],
							    yAxis : [
							        {
							            type : 'value'
							        }
							    ],
							    color: [ 
			                            '#3cb371' 
					        	       ],
							    series : [
							        {
							            name:'日流量',
							            type:'line',
							            data:dataCount,
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
						option2 = {

								 
								tooltip : {
									trigger: 'item',
									formatter: "{a} <br/>{b} : {c} ({d}%)"
								},
								legend: {
									orient : 'vertical',
									x : 'left',
									data:weekName
								},
								calculable : false,
								color: [ 
			                               '#3cb371', '#cd5c5c', '#00fa9a', '#87cefa', '#db70d6',
			                               '#32cd32', '#6495ed'    
					        	           
					        	       ],
								series : [
								          {
								        	  name:'日流量',
								        	  type:'pie',
								        	  radius : '55%',
								        	  center: ['50%',225],
								        	 
								        	  data:lastweekdata
								          }
								          ]
						};
						
						var myChart = ec.init(document.getElementById('mainBar3'));  
						var myChart2 = ec.init(document.getElementById('mainBar4'));

						myChart2.setOption(option2);
						myChart.setOption(option);
						myChart.connect(myChart2);
						myChart2.connect(myChart);
						setTimeout(function (){
							window.onresize = function () {
								myChart.resize();
							}
						},200) 
				}});
			
  
		});
                    