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
			   // 基于准备好的dom，初始化echarts图表    
             var myChart = ec.init(document.getElementById('mainBar7'));     
             var jsonNum=[0,0,0,0,0,0,0];  
             var jsonTime=[71,71,70,94,11,11,42];  
             var jsonDay=["1月23日","1月24日","1月25日","1月26日","1月27日","1月28日","1月29日"]; 
			 
			  
			  
			  
			  $.ajax({
    				type : "POST",
    				url : "fg/findRealTimeTotalChartDataInit.do",
    				data : null,
    				dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
    				type : "POST",
    				cache : false,
    				success : function(data) {
    					var realservicetotal;
    					realservicetotal=data.realservicetotal;
    					var realdatatotal;
      					realdatatotal=data.realdatatotal;
      					option = {  
      		                      title : {  
      		                          text: '服务请求实时监控',  
      		                          subtext: ''  
      		                      },  
      		                      tooltip : {  
      		                          trigger: 'axis'  
      		                      },  
      		                      legend: {  
      		                          data:['数据流量', '服务请求量']  
      		                      },  

      		                      dataZoom : {  
      		                          show : false,  
      		                          start : 0,  
      		                          end : 10000  
      		                      },  
      		                      xAxis : [  
      		                          {  
      		                              type : 'category',  
      		                              boundaryGap : true,  
      		                              data : (function (){  
      		                                  var now = new Date();  
      		                                  var res = [];  
      		                                  var len = 10;  
      		                                  while (len--) {  
      		                                      res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));  
      		                                      now = new Date(now -20000);  
      		                                  }  
      		                                  return res;  
      		                              })()  
      		                          },  
      		                          {  
      		                              type : 'category',  
      		                              boundaryGap : true,  
      		                              data : (function (){  
      		                                  var res = [];  
      		                                  var len = 10;  
      		                                  while (len--) {  
      		                                      res.push(len + 1);  
      		                                  }  
      		                                  return res;  
      		                              })()  
      		                          }  
      		                      ],  
      		                      yAxis : [  
      		                          {  
      		                              type : 'value',  
      		                              scale: true,  
      		                              name : '数据记录量',  
      		                              boundaryGap: [0.2, 0.2]  
      		                          },  
      		                          {  
      		                              type : 'value',  
      		                              scale: true,  
      		                              name : '服务请求数量',  
      		                              boundaryGap: [0.2, 0.2]  
      		                          }  
      		                      ], color: [ 
      		                                   '#A6E22E' ,'#83AAF0'   
      		  		        	        ], 
      		                      series : [  
      		                          {  
      		                              name:'数据流量',  
      		                              type:'bar',  
      		                              xAxisIndex: 1,  
      		                              yAxisIndex: 1,  
      		                              data:realdatatotal  
      		                          },  
      		                          {  
      		                              name:'服务请求量',  
      		                              type:'line',  
      		                              data:realservicetotal 
      		                          }  
      		                      ]  
      		                  };
      					 
      					 //
      					 var lastData = 11;  
      	                  var axisData;  
      	                  var timeTicket;
      	                  clearInterval(timeTicket);  
      	                  timeTicket = setInterval(function (){  
      	                      lastData += Math.random() * ((Math.round(Math.random() * 10) % 2) == 0 ? 1 : -1);  
      	                      lastData = lastData.toFixed(1) - 0;  
      	                      axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');  
      	                      $.ajax({
      	            				type : "POST",
      	            				url : "fg/findRealTimeTotalChartDatabys.do",
      	            				data : null,
      	            				dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
      	            				type : "POST",
      	            				cache : false,
      	            				success : function(data) {
      	            					var realservicetotal;
      	            					realservicetotal=data.realservicetotal;
      	            					var realdatatotal;
      	              					realdatatotal=data.realdatatotal;
      	            				    myChart.addData([  
      	            				                          [  
      	            				                              0,        // 系列索引  
      	            				                              realdatatotal, // 新增数据  
      	            				                              true,     // 新增数据是否从队列头部插入  
      	            				                              false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头  
      	            				                          ],  
      	            				                          [  
      	            				                              1,        // 系列索引  
      	            				                              realservicetotal, // 新增数据  
      	            				                              true,    // 新增数据是否从队列头部插入  
      	            				                              false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头  
      	            				                              axisData  // 坐标轴标签  
      	            				                          ]  
      	            				                      ]);  
      	            				                      
      	            				}});
      	                      // 动态数据接口 addData  
      	                  },20000);  
      	                                        
      	      
      	          // 为echarts对象加载数据     
      	          myChart.setOption(option);  
      					 //
      					 
      					 
    				}});
			  
                  
		}  
); 



