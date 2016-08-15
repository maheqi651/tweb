 
require(  
		[  
		 'echarts',  
		 'echarts/chart/gauge',  
		
		 ],  
		 function (ec) {  
            var myChart = ec.init(document.getElementById('mainBar8'));     
            option7 = {
            		 title : {
					        text: '实时数据流量监控',
					        x:'center'
					    },
            	    tooltip : {
            	        formatter: "{a} <br/>{b} : {c}%"
            	    },
            	    toolbox: {
            	        show : true,
            	        feature : {
            	            mark : {show: false},
            	            restore : {show: true},
            	            saveAsImage : {show: true}
            	        }
            	    },
            	    series : [
            	        {
            	            name:'数据流量',
            	            type:'gauge',
            	            min:0,
            	            max:100000,
            	            splitNumber: 10,       // 分割段数，默认为5
            	            axisLine: {            // 坐标轴线
            	                lineStyle: {       // 属性lineStyle控制线条样式
            	                    color: [[0.2, '#5A92DE'],[0.8, '#5A92DE'],[1, '#5A92DE']], 
            	                    width:12
            	                }
            	            },
            	            axisTick: {            // 坐标轴小标记
            	                splitNumber: 5,   // 每份split细分多少段
            	                length :12,        // 属性length控制线长
            	                lineStyle: {       // 属性lineStyle控制线条样式
            	                    color: 'auto'
            	                }
            	            },
            	            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
            	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
            	                    color: 'auto'
            	                }
            	            },
            	            splitLine: {           // 分隔线
            	                show: true,        // 默认显示，属性show控制显示与否
            	                length :40,         // 属性length控制线长
            	                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
            	                    color: 'auto'
            	                }
            	            },
            	            pointer : {
            	                width : 5
            	            },
            	            title : {
            	                show : true,
            	                offsetCenter: [0, '-40'],       // x, y，单位px
            	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
            	                    fontWeight: 'bolder'
            	                }
            	            },
            	            detail : {
            	                formatter:'{value}条/小时',
            	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
            	                    color: 'auto',
            	                    fontWeight: 'bolder'
            	                }
            	            },
            	            data:[{value: 0, name: '数据流量'}]
            	        }
            	    ]
            	};
                var timeTicket;
            	clearInterval(timeTicket);
            	timeTicket = setInterval(function (){
            		$.ajax({
        				type : "POST",
        				url : "fg/findRealTimeTotalChartDataByhour.do",
        				data : null,
        				dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
        				type : "POST",
        				cache : false,
        				success : function(data) {
        					    var realhourtimetotal=data.realhourtimetotal;
        					    option7.series[0].data[0].value = realhourtimetotal;
        	            	    myChart.setOption(option7,true);
        				}});
            	  
            	},2000);
            	              
            	 myChart.setOption(option7);
             
		}  
); 



