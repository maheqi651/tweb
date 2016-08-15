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
             /*var jsonNum=[0,0,0,0,0,0,0];  
             var jsonTime=[71,71,70,94,11,11,42];  
             var jsonDay=["1月23日","1月24日","1月25日","1月26日","1月27日","1月28日","1月29日"];*/ 
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
      						        text: '服务访问量实时统计',
      						        subtext: ''
      						    },
      						    tooltip : {
      						        trigger: 'axis'
      						    },
      						    legend: {
      						        data:['服务访问量']
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
      						                	var hour=now.getHours();
      						                	if(hour<10)
      						                		hour="0"+hour;
      						                	minuties=now.getMinutes();
      						                	if(minuties<10)
      						                		minuties="0"+minuties;
      						                	Seconds=now.getSeconds();
      						                	if(Seconds<10)
      						                		Seconds="0"+Seconds;
      						                    res.unshift(hour+":"+minuties+":"+Seconds);
      						                    now = new Date(now - 600000);
      						                }
      						                return res;
      						            })()
      						        } 
      						    ],
      						    yAxis : [
      						        {
      						            type : 'value',
      						            scale: true,
      						            name : '访问量',
      						            boundaryGap: [0, 0]
      						        }
      						    ],
							    color: [ 
			                            '#cd5c5c' 
					        	       ],
      						    series : [
      						         
      						        {
      						            name:'服务访问量',
      						            type:'line',
      						            data:realservicetotal
      						        }
      						    ]
      						};
      						var lastData = 11;
      						var axisData;
      						var timeTicket=null;
      						clearInterval(timeTicket);
      						timeTicket = setInterval(function (){
      						    
      						    var now=new Date();
      							var hour=now.getHours();
				                	if(hour<10)
				                		hour="0"+hour;
				                	minuties=now.getMinutes();
				                	if(minuties<10)
				                		minuties="0"+minuties;
				                	Seconds=now.getSeconds();
				                	if(Seconds<10)
				                		Seconds="0"+Seconds;
				                    //res.unshift(hour+":"+minuties+":"+Seconds);
      						    axisData=hour+":"+minuties+":"+Seconds;
      						   // axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
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
    	                 						            realservicetotal, // 新增数据
    	                 						            false,     // 新增数据是否从队列头部插入
    	                 						            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
    	                 						            ,axisData
    	                 						            ] 
    	                 						    ]); 
    	            				                      
    	            				}});
      						    // 动态数据接口 addData
      						},600000);	
      	                  myChart.setOption(option);  
      					 
      					 
      					 
    				}});
			  
                  
		}  
); 



