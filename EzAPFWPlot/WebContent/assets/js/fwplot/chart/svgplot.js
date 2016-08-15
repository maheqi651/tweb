	  
    
 
	
	require.config({  
		paths: {  
			echarts: './assets'  
		}  
	}); 
      
        require(
            [
                'echarts',
                'echarts/util/mapData/params',
                'echarts/chart/map'
            ],
            function (ec, params) {
                params.params.football = {
                    getGeoJson: function (callback) {
                        $.ajax({
                            url: '../ZytjXmlFlow.do',
                            dataType: 'xml',
                            success: function (xml) {
                                callback(xml);
                            }
                        });
                    }
                };
                var Chart = ec.init(document.getElementById('plotsvg'));
                option = {
                	    backgroundColor: '#3b3b3b',
                	    title: {
                	        text: '广州公安大数据平台 - 数据资源总图',
                	        textStyle: {
                	            fontFamily: "微软雅黑",
                	            fontSize: 18,
                	            fontWeight: 'bolder',
                	            color: '#fff'
                	        }
                	    },
                	    tooltip: {
                	        trigger: 'item'
                	    },
                	    toolbox: {
                	        show: true,
                	        feature: {
                	            mark: { show: true,
                		            	title : {
                		                    mark : '辅助线开关',
                		                    markUndo : '删除辅助线',
                		                    markClear : '清空辅助线'
                		                }
                	            },
                	            dataZoom : {
                	                show : false,
                	                title : {
                	                    dataZoom : '区域缩放',
                	                    dataZoomReset : '区域缩放后退'
                	                }
                	            },
                	            dataView: { show: true, readOnly: false },
                	            restore: { show: true },
                	            saveAsImage: { show: true }
                	        }
                	    },
                	    dataRange: {
                	        min : 0,
                	        max : 100,
                	        calculable : true,
                	        color: ['#ff3333', 'orange', 'yellow','lime','aqua'],
                	        textStyle:{
                	            color:'#fff'
                	        },
                	        itemHeight:10
                	    },
                	    series: [
                	        {
                	            name: '库体',
                	            type: 'map',
                	            mapType: 'football', // 自定义扩展图表类型
                	            roam: false,
                	            hoverable: false,
                	            itemStyle: {
                	               normal: {
                	            	   label:{show: true,
                	            		  // position: 'left',
                    	            	   textStyle: {
                    	            		    align:'left',
               	                    	    	color:'#FFFFFF',//#FF6347
               	                    	    	fontFamily: "微软雅黑",
               	                    	    	baseline: "top",
               	                    	    	//fontSize: 12// 16
                    	            	   }
                	            		}
                	            	  },
                	               emphasis:{label:{show:true}}
                	            },
                	             
                	            data:null,
                	            textFixed : {},
                	            markLine: {
                	                smooth: true,
                	                effect : {
                	                    show: true,
                	                    scaleSize: 1,
                	                    period: 20,
                	                    color: '#fff',
                	                    shadowBlur: 5
                	                },
                	                symbol: ['none'],
                	                itemStyle: {
                	                    normal: {
                	                        label:{show:true},
                	                        borderWidth: 0.5,
                	                        color: 'green',
                	                        lineStyle: {
                	                            type: 'solid'//'solid'
                	                        }
                	                    }
                	                },
                	                data:null
                	            },//makeLine结束
                	            geoCoord: null
                	        }]};
                Chart.setOption(option);
                // 设置点击事件
                var ecConfig = require('echarts/config'); 
                Chart.on(ecConfig.EVENT.CLICK, function(param){
                	var userid = window.parent.getUserId();
                	var n = param.name;
                	 alert();
    		           
                     

                });// endClick
            }
        );
