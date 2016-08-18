var baseurl,_MapApp;
	    (function() {
			var strFullPath = window.document.location.href;
			var strPath = window.document.location.pathname;
			var pos = strFullPath.indexOf(strPath);
			var prePath = strFullPath.substring(0, pos);
			var postPath = strPath.substring(0, strPath.substr(1).indexOf('/')+ 1);
			baseurl = prePath + postPath;
		})();
 

	    $(document).ready(function(){
	    	  //alert();
		    initMap();
		});

	    function queryone(){
	    	 
	    	if($.trim($("#passenger_name").val())==""&&$.trim($("#id_card").val())==""){
	    		alert("乘车姓名和证件号至少填写一个");
				return;
	    	}
	    	 var target=document.getElementById("top1");
	   	  target.style.display="none";
	    	var keyword;
	    	// if(/^[\u4e00-\u9fa5]+$/.test($("#id_card").val())){
	    	  keyword = {passenger_name:$("#passenger_name").val()};
	     
		    var param = {startdate:$("#startdate").val(),enddate:$("#enddate").val(),zjh:$.trim($("#id_card").val()),page:1};
		    $.extend(param, keyword);
		    $("#pager").pagination(baseurl+"/QueryOne",param,function(data){
		    	document.getElementById('loadquery').style.visibility='visible';
				$("#pinfo").html("");
				_MapApp.clearOverlays();
				var points = null;
				var len = data.result.length;
				//alert(data.pagecount);
				var content = "<thead> <tr> <th  align='center' width='8%'>序号</th> <th  width='10%'>乘客姓名</th> <th  width='15%'>证件号</th> <th  width='10%'>乘车时间</th><th  width='15%'>车次</th>  <th  width='10%'>出发站</th> <th  width='10%'>到达站</th> <th  width='25%'>操作 </th></tr> </thead> <tbody style='border:none'>";
				var points = null;
				for ( var i = 0; i < len; i++) 
				{
					var temp = data.result[i];
					var icon_start = new Icon();// 构造一个图标类
					icon_start.image="images/qd.png";
					icon_start.height=24;
					icon_start.width=24;

					var icon_end = new Icon();// 构造一个图标类
					icon_end.image="images/zd.png";
					icon_end.height=24;
					icon_end.width=24;

					var qi = getWord(temp[8]);
					var zh = getWord(temp[9]);
					var point1 = $.EzSearch.getCityPoint(qi);
					if(point1==undefined){
						console.error("找不到"+qi+"坐标");
						getNotExistCoordinate(qi);
					}
					points = getPointMinMax(points,new Point(point1[0],point1[1]));
					
					var point2 = $.EzSearch.getCityPoint(zh);
					if(point2==undefined){
						console.error("找不到"+zh+"坐标");
						getNotExistCoordinate(zh);
					}
					points = getPointMinMax(points,new Point(point2[0],point2[1]));
					var marker1 = new Marker(new Point(point1[0],point1[1]),icon_start);
					var marker2 = new Marker(new Point(point2[0],point2[1]),icon_end);
					_MapApp.addOverlay(marker1);
					_MapApp.addOverlay(marker2);
					content+="<tr><td>"+temp[12]+"</td>";
					 
					 
					var name = temp[0]==null?'无数据':temp[0];
					content += " <td>"+name+"</td>";
					content += "<td>"+temp[10]+"</td>";
					content += "<td>"+temp[4]+"</td>";
					content += " <td> "+temp[5]+"</td>";
					
					content += "<td> "+temp[8]+"</td>";
					content += "<td > "+temp[9]+"</td>";
					//var temp11={temp[0],temp[1],temp[2],temp[3],temp[4],temp[5],temp[6],temp[7],temp[8],temp[9],temp[10],temp[11],temp[12]};
				    content+="<td ><div class='btn-group'> <button class='btn  btn-xs btn-default'  onclick='detail(\""+temp[0]+"\",\""+temp[1]+"\",\""+temp[2]+"\",\""+temp[3]+"\",\""+temp[4]+"\",\""+temp[5]+"\",\""+temp[6]+"\",\""+temp[7]+"\",\""+temp[8]+"\",\""+temp[9]+"\",\""+temp[10]+"\",\""+temp[11]+"\",\""+temp[12]+"\")'>详情</button> "; 
					 content+="<button class='btn  btn-xs btn-default' onclick='samecart(\""+temp[5]+"\",\""+temp[4]+"\",\""+name+"\",\""+temp[6]+"\")'>同车人员</button> ";  
					 content+=" <button class='btn  btn-xs btn-default'  onclick='ditu(\""+temp[8]+"\",\""+temp[9]+"\")' >地图</button></div>";
					  content += "</tr> "; 
				    
				    
					 // alert(content);
					
				}
				content+="</tbody>";
				// alert(content);
				$("#pagerrow").html(content);
				document.getElementById('loadquery').style.visibility='hidden';
				if(len<=0)
					{
					$("#pagerculom").html("");
					}
				if(points!=null)
					_MapApp.centerAndZoom(new Point((points[0].x+points[1].x)/2,(points[0].y+points[1].y)/2), 7);
				initInfoContent();
				//$("#showcount").html("搜索结果:"+len+"条");
			});
	    }
     
     
     function querytwo(){
    	  
	    	//alert("");
	    	if($.trim($("#train_number").val())==""||$.trim($("#start_station").val())==""){
	    		alert("车次和出发站不可以为空");
				return;
	    	}
	    	 
	    	
	    	
	    	 var target=document.getElementById("top1");
	   	  target.style.display="none";
	    	var keyword;
	    	// if(/^[\u4e00-\u9fa5]+$/.test($("#id_card").val())){
	    	  keyword = {train_number:$("#train_number").val()};
	       // }else{
	        //	alert("证件号格式有误");
			//	return;
	    	// }
	    	  document.getElementById('loadquery').style.visibility='visible';
	    	//var idcard=$("#id_card").val();
		    var param = {startdate1:$("#startdate1").val(),enddate1:$("#enddate1").val(),start_station:$.trim($("#start_station").val()),end_station:$.trim($("#end_station").val()),page:1};
		    $.extend(param, keyword);
		    $("#pager").pagination(baseurl+"/QueryTwo",param,function(data){
		    	
				$("#pinfo").html("");
				var len = data.result.length;
				_MapApp.clearOverlays();
				var points = null;
				var len = data.result.length;
				//alert(data.pagecount);
                var content = "<thead> <tr> <th  align='center' width='5%'>序号</th> <th  width='10%'>乘客姓名</th> <th  width='15%'>证件号</th>  <th  width='10%'>乘车时间</th> <th  width='15%'>车次</th><th  width='10%'>出发站</th> <th  width='10%'>到达站</th> <th  width='25%'>操作 </th></tr> </thead> <tbody style='border:none'>";
				var points = null;
				for ( var i = 0; i < len; i++) {
					var temp = data.result[i];
					var icon_start = new Icon();// 构造一个图标类
					icon_start.image="images/qd.png";
					icon_start.height=24;
					icon_start.width=24;

					var icon_end = new Icon();// 构造一个图标类
					icon_end.image="images/zd.png";
					icon_end.height=24;
					icon_end.width=24;

					var qi = getWord(temp[8]);
					var zh = getWord(temp[9]);
					var point1 = $.EzSearch.getCityPoint(qi);
					if(point1==undefined){
						console.error("找不到"+qi+"坐标");
						getNotExistCoordinate(qi);
					}
					points = getPointMinMax(points,new Point(point1[0],point1[1]));
					
					var point2 = $.EzSearch.getCityPoint(zh);
					if(point2==undefined){
						console.error("找不到"+zh+"坐标");
						getNotExistCoordinate(zh);
					}
					points = getPointMinMax(points,new Point(point2[0],point2[1]));
					var marker1 = new Marker(new Point(point1[0],point1[1]),icon_start);
					var marker2 = new Marker(new Point(point2[0],point2[1]),icon_end);
					_MapApp.addOverlay(marker1);
					_MapApp.addOverlay(marker2);
					content+="<tr><td>"+temp[12]+"</td>";
					 
					 
					var name = temp[0]==null?'无数据':temp[0];
					content += " <td>"+name+"</td>";
					content += "<td>"+temp[10]+"</td>";
					content += "<td>"+temp[4]+"</td>";
					content += " <td> "+temp[5]+"</td>";
					
					content += "<td> "+temp[8]+"</td>";
					content += "<td > "+temp[9]+"</td>";
					  content+="<td ><div class='btn-group'> <button class='btn  btn-xs btn-default'  onclick='detail(\""+temp[0]+"\",\""+temp[1]+"\",\""+temp[2]+"\",\""+temp[3]+"\",\""+temp[4]+"\",\""+temp[5]+"\",\""+temp[6]+"\",\""+temp[7]+"\",\""+temp[8]+"\",\""+temp[9]+"\",\""+temp[10]+"\",\""+temp[11]+"\",\""+temp[12]+"\")'>详情</button> "; 
				    content+="<button class='btn  btn-xs btn-default' onclick='samecart(\""+temp[5]+"\",\""+temp[4]+"\",\""+name+"\",\""+temp[6]+"\")'>同车人员</button> ";  
				    content+=" <button class='btn  btn-xs btn-default'  onclick='ditu(\""+temp[8]+"\",\""+temp[9]+"\")' >地图</button></div>";
				    content += "</tr> "; 
				}
				content+="</tbody>";
				$("#pagerrow").html(content);
				
				  document.getElementById('loadquery').style.visibility='hidden';
				if(len<=0)
					$("#pagerculom").html("");
				if(points!=null)
					_MapApp.centerAndZoom(new Point((points[0].x+points[1].x)/2,(points[0].y+points[1].y)/2), 7);
				initInfoContent();
			});
	    }
	    
	    
	    
	    function initInfoContent(){
			$("div.row div.unitcontent").mouseenter(function() {
			    $( this ).addClass("bg-info");
			  }).mouseleave(function() {
			    $( this ).removeClass("bg-info");
			}).click(function(){
				$("#pinfo div").removeClass("bg-success");
				$( this ).addClass("bg-success");
				_MapApp.clearOverlays();
				
				var qitext = $(this).find("dl:last dd.qi").text();
				var zhtext = $(this).find("dl:last dd.zh").text();

				var icon_start = new Icon();// 构造一个图标类
				icon_start.image="images/qd.png";
				icon_start.height=24;
				icon_start.width=24;

				var icon_end = new Icon();// 构造一个图标类
				icon_end.image="images/zd.png";
				icon_end.height=24;
				icon_end.width=24;

				var qi = getWord(qitext);
				var zh = getWord(zhtext);
				var point1 = $.EzSearch.getCityPoint(qi);
				var point2 = $.EzSearch.getCityPoint(zh);
				var marker1 = new Marker(new Point(point1[0],point1[1]),icon_start);
				var marker2 = new Marker(new Point(point2[0],point2[1]),icon_end);
				_MapApp.addOverlay(marker1);
				_MapApp.addOverlay(marker2);
				_MapApp.centerAndZoom(new Point((point1[0]+point2[0])/2,(point1[1]+point2[1])/2), 7);
			});
	    }
      
	    function initMap(){
	    	//1） ********构造地图控件对象，用于装载地图
	    	_MapApp = new EzMap(document.getElementById("map"));
	     
	    	//2）********初始化地图，并显示地图
	    	//_MapApp.initialize();
	     
	    	//3）********以下为其它一些附属功能
	    	// 显示左侧导航工具条
	    	_MapApp.showMapControl();
	     
	    	// 构造鹰眼对象
	    	var ov = new OverView();
	    	// 设置鹰眼的宽度，单位为像素px
	    	ov.width = 200;
	    	// 设置鹰眼的高度
	    	ov.height = 200;
	    	// 添加鹰眼对象到地图对象中
	    	_MapApp.addOverView(ov);
	    	_MapApp.addMapEventListener(EzEvent.MAP_ZOOMCHANGE,function(data){
	    		/**
	    		if(data.zoomLevel <10){
	    			if(tileLyr0!=null){
	    				var temArr = _MapApp.getGroupLayer();
	    				temArr[0].getLayers().removevalue(tileLyr0);
	    				temArr[0].getLayers().removevalue(tilehotspotLyr0);
	    				_MapApp.refresh();
	    			}
	    			clearAll();
	    		}*/
	    	});
	    	_MapApp.hideMapServer(); 
//	    	zoomTo(12);
	    }

	    function getWord(word){
	    	word = $.trim(word);
	    	/*
			if(word=="济南"){
				return word;
			}
			var lastw = word.substr(word.length-1);
			if(lastw=="东"||lastw=="西"||lastw=="南"||lastw=="北"){
				var prew = word.substr(0,word.length-1);
				return prew;
			}else{
				return word;
			}*/
	    	return word;
		}


	    function getNotExistCoordinate(keyword) {
            var url = "http://ditu.google.cn/maps/api/geocode/json?address=" + encodeURIComponent(keyword) + "&sensor=false" + "&randomNum=" + Math.random();
            $.ajax({
                url: url,
                dataType: 'json',
                success: function(data) {
                    if (data.status == 'OK') {
                        console.log(",{\n"
                        		+"\t\"name\" : \""+keyword+"\",\n"
                        		+"\t\"x\" : "+data.results[0].geometry.location.lng+",\n"
                        		+"\t\"y\" : "+data.results[0].geometry.location.lat+"\n"
                            	+"}");
                    }
                    else {
                        alert("没找到你要查询的位置，请重新输入！");
                    }
                },
                error: function() {
                    alert("网络繁忙，请重试！");
                }
            });
        }

	    function getPointMinMax(points,point){
		    var back = new Array();	
		    var minpoint;
		    var maxpoint;
			if(points==null){
				minpoint = point;
				maxpoint = point;
			}else{
				var minx,miny;
				if(points[0].x > point.x){
					minx = point.x;
				}else{
					minx = points[0].x;
				}

				if(points[0].y > point.y){
					miny = point.y;
				}else{
					miny = points[0].y;
				}

				minpoint = new Point(minx,miny);
				var maxx,maxy;
				if(points[1].x > point.x){
					maxx = points[1].x;
				}else{
					maxx = point.x;
				}

				if(points[1].y > point.y){
					maxy = points[1].y;
				}else{
					maxy = point.y;
				}
				maxpoint = new Point(maxx,maxy);
			}

			back.push(minpoint);
			back.push(maxpoint);
			return back;
	    }
        
	    function samecart(filed,ccrq,xm,cx){
			// alert(filed);
			 //alert(ccrq);
			  document.getElementById('loadsmart').style.visibility='visible';
			 
	    	var keyword;
	    	  keyword = {CC:filed};
		    var param = {page:1,ccrq:ccrq,cx:cx};
		    
		    
		    
		    $.extend(param,keyword);
		    $("#pager1").pagination(baseurl+"/QuerySameCart",param,function(data){
				$("#pinfo1").html("");
				var len = data.result.length;
				_MapApp.clearOverlays();
				var points = null;
				var content = "";
				var content = "<thead> <tr> <th  align='center' width='8%'>序号</th> <th  width='10%'>乘客姓名</th> <th  width='15%'>证件号</th> <th  width='15%'>车厢号</th> <th  width='10%'>座位号</th> <th  width='10%'>出发站</th> <th  width='10%'>到达站</th> <th  width='25%'>操作 </th></tr> </thead> <tbody style='border:none'>";
				var points = null;
				for ( var i = 0; i < len; i++)
				{
					var temp = data.result[i];
					var name = temp[0]==null?'无数据':temp[0];
					content+="<tr><td>"+temp[12]+"</td>";
					content += " <td>"+name+"</td>";
					content += "<td>"+temp[10]+"</td>";
					content += " <td> "+temp[6]+"</td>";
					content += "<td>"+temp[7]+"</td>";
					content += "<td> "+temp[8]+"</td>";
					content += "<td > "+temp[9]+"</td>";
					//var temp11={temp[0],temp[1],temp[2],temp[3],temp[4],temp[5],temp[6],temp[7],temp[8],temp[9],temp[10],temp[11],temp[12]};
				    content+="<td ><div class='btn-group'> <button class='btn  btn-xs btn-default'  onclick='detail1(\""+temp[0]+"\",\""+temp[1]+"\",\""+temp[2]+"\",\""+temp[3]+"\",\""+temp[4]+"\",\""+temp[5]+"\",\""+temp[6]+"\",\""+temp[7]+"\",\""+temp[8]+"\",\""+temp[9]+"\",\""+temp[10]+"\",\""+temp[11]+"\",\""+temp[12]+"\")'>详情</button> "; 
					 content+=" <button class='btn  btn-xs btn-default'  onclick='ditu1(\""+temp[8]+"\",\""+temp[9]+"\")' >地图</button></div>";
					  content += "</tr> "; 
				}
			
				content+="	</tbody>";
				$("#pagesamecart").html("");
				$("#pagesamecart").html(content);
				if(len<=0)
					$("#pagerculom1").html("");
				if(points!=null)
					_MapApp.centerAndZoom(new Point((points[0].x+points[1].x)/2,(points[0].y+points[1].y)/2), 7);
				initInfoContent();
				 document.getElementById('loadsmart').style.visibility='hidden';
			});
		    $("#smartcxh").html('<option>正在读取.....</option>');
		    $.ajax({
	            type: "post",
	            url: baseurl+"/QueryCxhList",
	            data: 'ccrq='+ccrq+'&cc='+filed,
	            dataType: "json",
	            success: function(data){
	            	//alert(data.result.length);
	                 var str='';
	                 str+="<option>请选择</option>";
	            	 for(i=0;i<data.result.length;i++)
	            		 {
	            		 str+="<option value='"+data.result[i][0]+"&"+filed+"&"+ccrq+"'>"+data.result[i][0]+"</option>";
	            		 }
	            	     //alert(str);
	            	     $("#smartcxh").html(str);
	                     }
	           });
		    $("#myModal").modal('show');   
		    $("#myModalLabel").text(ccrq+'  与'+xm+'同乘'+filed+'次列车的人员');
	}
	  function detail1(v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13)
	  {   
		  $("#myModal").modal('hide'); 
	  
		  var str='';
		  //alert(v1+v2+v3+v4+v5+v6+v7+v8+v9+v10+v11+v12+v13);
		 // xm,spczjc,to_char(spsj,'yyyy-mm-dd'),cph,to_char(ccrq,'yyyy-mm-dd'),cc,cxh,zwh,fz,dz,zjh,SPCKH
		     str="<tr><td width='20%' align='right'>乘车姓名：</td><td width='30%' align='left'>"+v1+"</td>";
		     str+="<td width='20%' align='right'>证件号：</td><td width='30%' align='left'>"+v11+"</td></tr>";
			  str+="<tr> <td align='right'>购票站点：</td><td align='left'>"+v2+"</td>";  
			  str+="<td align='right'>购票窗口号：</td><td align='left'>"+v12+"</td> </tr>";
	          str+="<tr> <td align='right'>票号：</td><td align='left'>"+v4+"</td>";
	          str+="<td align='right'>购票时间：</td><td align='left'>"+v3+"</td> </tr>";
	          str+="<tr> <td align='right'>车次：</td><td align='left'>"+v6+"</td>";
	          str+="<td align='right'>乘车时间：</td><td align='left'>"+v5+"</td> </tr>";
	          str+= "<tr> <td align='right'>车厢号：</td><td align='left'>"+v7+"</td>";
	          str+=" <td align='right'>座位号：</td><td align='left'>"+v8+"</td> </tr>";
	          str+= "<tr> <td align='right'>出发站：</td><td align='left'>"+v9+"</td>";
	          str+= "<td align='right'>到达站：</td><td align='left'>"+v10+"</td> </tr>";
	          
	         $("#detailtable").html(str); 
		     $("#myModal5").modal('show');
		   
		        $("#myModalLabel5").text('乘车详情：');
		        $('#myModal5').on('hidden.bs.modal', function (e) {
		        	$("#myModal").modal('show'); 
		      
		        	  
		        	});
	  }
	  
	  
	  function detail(v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13)
	  {   
		  
	  
		  var str='';
		  //alert(v1+v2+v3+v4+v5+v6+v7+v8+v9+v10+v11+v12+v13);
		 // xm,spczjc,to_char(spsj,'yyyy-mm-dd'),cph,to_char(ccrq,'yyyy-mm-dd'),cc,cxh,zwh,fz,dz,zjh,SPCKH
		     str="<tr><td width='20%' align='right'>乘车姓名：</td><td width='30%' align='left'>"+v1+"</td>";
		     str+="<td width='20%' align='right'>证件号：</td><td width='30%' align='left'>"+v11+"</td></tr>";
			  str+="<tr> <td align='right'>购票站点：</td><td align='left'>"+v2+"</td>";  
			  str+="<td align='right'>购票窗口号：</td><td align='left'>"+v12+"</td> </tr>";
	          str+="<tr> <td align='right'>票号：</td><td align='left'>"+v4+"</td>";
	          str+="<td align='right'>购票时间：</td><td align='left'>"+v3+"</td> </tr>";
	          str+="<tr> <td align='right'>车次：</td><td align='left'>"+v6+"</td>";
	          str+="<td align='right'>乘车时间：</td><td align='left'>"+v5+"</td> </tr>";
	          str+= "<tr> <td align='right'>车厢号：</td><td align='left'>"+v7+"</td>";
	          str+=" <td align='right'>座位号：</td><td align='left'>"+v8+"</td> </tr>";
	          str+= "<tr> <td align='right'>出发站：</td><td align='left'>"+v9+"</td>";
	          str+= "<td align='right'>到达站：</td><td align='left'>"+v10+"</td> </tr>";
	          
	         $("#detailtable").html(str); 
		     $("#myModal5").modal('show');
		   
		        $("#myModalLabel5").text('乘车详情：');
		         
	  }
	  function smartcxh(){
		  document.getElementById('loadsmart').style.visibility='visible';
	 
	     var strx=$("#smartcxh").val();
	     if(strx!=null&&strx!=""){
	    	 var strs=strx.split("&");
	    		var keyword;
	    	  	  keyword = {CC:strs[1]};
	    		    var param = {page:1,ccrq:strs[2],cx:strs[0]};
	    		    $.extend(param,keyword);
	    		    $("#pager1").pagination(baseurl+"/QuerySameCart",param,function(data){
	    				$("#pinfo1").html("");
	    				var len = data.result.length;
	    				var content = "";
	    				 content = "<thead> <tr> <th  align='center' width='8%'>序号</th> <th  width='10%'>乘客姓名</th> <th  width='15%'>证件号</th> <th  width='15%'>车厢号</th> <th  width='10%'>座位号</th> <th  width='10%'>出发站</th> <th  width='10%'>到达站</th> <th  width='25%'>操作 </th></tr> </thead> <tbody style='border:none'>";
	    				var points = null;
	    				for ( var i = 0; i < len; i++) {
	    					
	    					var temp = data.result[i];
	    					var icon_start = new Icon();// 构造一个图标类
	    					icon_start.image="images/qd.png";
	    					icon_start.height=24;
	    					icon_start.width=24;

	    					var icon_end = new Icon();// 构造一个图标类
	    					icon_end.image="images/zd.png";
	    					icon_end.height=24;
	    					icon_end.width=24;

	    					var qi = getWord(temp[8]);
	    					var zh = getWord(temp[9]);
	    					var point1 = $.EzSearch.getCityPoint(qi);
	    					if(point1==undefined){
	    						console.error("找不到"+qi+"坐标");
	    						getNotExistCoordinate(qi);
	    					}
	    					points = getPointMinMax(points,new Point(point1[0],point1[1]));
	    					
	    					var point2 = $.EzSearch.getCityPoint(zh);
	    					if(point2==undefined){
	    						console.error("找不到"+zh+"坐标");
	    						getNotExistCoordinate(zh);
	    					}
	    					points = getPointMinMax(points,new Point(point2[0],point2[1]));
	    					var marker1 = new Marker(new Point(point1[0],point1[1]),icon_start);
	    					var marker2 = new Marker(new Point(point2[0],point2[1]),icon_end);
	    					_MapApp.addOverlay(marker1);
	    					_MapApp.addOverlay(marker2);
	    					var name = temp[0]==null?'无数据':temp[0];
	                //xm,spczjc,to_char(spsj,'yyyy-mm-dd'),cph,to_char(ccrq,'yyyy-mm-dd'),cc,cxh,zwh,fz,dz,zjh,SPCKH 
	    					content+="<tr><td>"+temp[12]+"</td>";
	    					content += " <td>"+name+"</td>";
	    					content += "<td>"+temp[10]+"</td>";
	    					content += " <td> "+temp[6]+"</td>";
	    					content += "<td>"+temp[7]+"</td>";
	    					content += "<td> "+temp[8]+"</td>";
	    					content += "<td > "+temp[9]+"</td>";
	    					//var temp11={temp[0],temp[1],temp[2],temp[3],temp[4],temp[5],temp[6],temp[7],temp[8],temp[9],temp[10],temp[11],temp[12]};
	    				    content+="<td ><div class='btn-group'> <button class='btn  btn-xs btn-default'  onclick='detail1(\""+temp[0]+"\",\""+temp[1]+"\",\""+temp[2]+"\",\""+temp[3]+"\",\""+temp[4]+"\",\""+temp[5]+"\",\""+temp[6]+"\",\""+temp[7]+"\",\""+temp[8]+"\",\""+temp[9]+"\",\""+temp[10]+"\",\""+temp[11]+"\",\""+temp[12]+"\")'>详情</button> "; 
	    					 content+=" <button class='btn  btn-xs btn-default'  onclick='ditu1(\""+temp[8]+"\",\""+temp[9]+"\")' >地图</button></div>";
	    					  content += "</tr> "; 
	    					 
	    				}
	    				 document.getElementById('loadsmart').style.visibility='hidden';
	    				content+="	</tbody>";
	    				$("#pagesamecart").html("");
	    				$("#pagesamecart").html(content);
	    				if(len<=0)
	    					$("#pagerculom1").html("");
	    			});
	    		   // $("#myModal").modal('show');
	    		    
	    		    	 // $("#myModalLabel").text('同车厢人员列表');
	     
	     
	     }
	    
	  
		  
		  
		  
	  }
	  
	 
	  function samechex(filed,ccrq,cx){
			// alert(filed);
			 //alert(ccrq);
	  	var keyword;
	  	  keyword = {CC:filed};
		    var param = {page:1,ccrq:ccrq,cx:cx};
		    $.extend(param,keyword);
		    $("#pager1").pagination(baseurl+"/QuerySameCart",param,function(data){
				$("#pinfo1").html("");
				var len = data.result.length;
				var content = "";
				var points = null;
				for ( var i = 0; i < len; i++) {
					
					var temp = data.result[i];
					var icon_start = new Icon();// 构造一个图标类
					icon_start.image="images/qd.png";
					icon_start.height=24;
					icon_start.width=24;

					var icon_end = new Icon();// 构造一个图标类
					icon_end.image="images/zd.png";
					icon_end.height=24;
					icon_end.width=24;

					var qi = getWord(temp[8]);
					var zh = getWord(temp[9]);
					var point1 = $.EzSearch.getCityPoint(qi);
					if(point1==undefined){
						console.error("找不到"+qi+"坐标");
						getNotExistCoordinate(qi);
					}
					points = getPointMinMax(points,new Point(point1[0],point1[1]));
					
					var point2 = $.EzSearch.getCityPoint(zh);
					if(point2==undefined){
						console.error("找不到"+zh+"坐标");
						getNotExistCoordinate(zh);
					}
					points = getPointMinMax(points,new Point(point2[0],point2[1]));
					var marker1 = new Marker(new Point(point1[0],point1[1]),icon_start);
					var marker2 = new Marker(new Point(point2[0],point2[1]),icon_end);
					_MapApp.addOverlay(marker1);
					_MapApp.addOverlay(marker2);
					content += "<tbody style='border:none'><tr style='font-weight:500'>";
					content += "<td ><font style='font-weight:600'>购票信息</font></td>";
					var name = temp[0]==null?'无数据':temp[0];
					content += " <td>乘客姓名:"+name+"</td>";
					content += "<td>证件号:"+temp[10]+"</td>";
					content += "<td>购票车站:"+temp[1]+"</td>";
					content += "<td>购票时间:"+temp[2]+"</td>";
					content += "<td colspan='2'>票号:"+temp[3]+"</td>";
					 
					content+="</tr> <tr style='font-weight:500 ;'><td><font style='font-weight:600'>乘车信息</font></td>";
					content += "<td>乘车日期:"+temp[4]+"</td>";
					content += " <td>车次:"+temp[5]+"</td>";
					content += "<td>车厢号:"+temp[6]+"</td>";
					content += " <td>座位号:"+temp[7]+"</td>";
					content += "<td>出发站:"+temp[8]+"</td>";
					content += "<td >到达站:"+temp[9]+"</td>";
					content += "</tr></tbody>";
				}
			
				content+="	";
				$("#pagesamecart").html("");
				$("#pagesamecart").html(content);
				if(len<=0)
					$("#pagerculom1").html("");
			});
		    $("#myModal").modal('show');
		    if(cx==''||cx==null)
		        $("#myModalLabel").text('同车人员列表');
		    else
		    	 $("#myModalLabel").text('同车厢人员列表');
	}
	   
	  
	  function ditu(startz,stopz){
			// alert(filed);
			 //alert(ccrq);
			    
			    _MapApp.clearOverlays();
				
				var qitext =startz;
				var zhtext =stopz;

				var icon_start = new Icon();// 构造一个图标类
				icon_start.image="images/qd.png";
				icon_start.height=24;
				icon_start.width=24;

				var icon_end = new Icon();// 构造一个图标类
				icon_end.image="images/zd.png";
				icon_end.height=24;
				icon_end.width=24;

				var qi = getWord(qitext);
				var zh = getWord(zhtext);
				var point1 = $.EzSearch.getCityPoint(qi);
				var point2 = $.EzSearch.getCityPoint(zh);
				var marker1 = new Marker(new Point(point1[0],point1[1]),icon_start);
				var marker2 = new Marker(new Point(point2[0],point2[1]),icon_end);
				_MapApp.addOverlay(marker1);
				_MapApp.addOverlay(marker2);
				_MapApp.centerAndZoom(new Point((point1[0]+point2[0])/2,(point1[1]+point2[1])/2), 7);
	     var target=document.getElementById("top1");
		 target.style.display="block";
		 var target2=document.getElementById("top2");
		 target2.style.display="none";
		 
		 var target3=document.getElementById("divh");
		 target3.style.display="none";
		  location.hash='#top1';
	}
	  function ditu1(startz,stopz){
			// alert(filed);
		      
		       $("#myModal").modal('hide');
			    
			    _MapApp.clearOverlays();
				
				var qitext =startz;
				var zhtext =stopz;

				var icon_start = new Icon();// 构造一个图标类
				icon_start.image="images/qd.png";
				icon_start.height=24;
				icon_start.width=24;

				var icon_end = new Icon();// 构造一个图标类
				icon_end.image="images/zd.png";
				icon_end.height=24;
				icon_end.width=24;

				var qi = getWord(qitext);
				var zh = getWord(zhtext);
				var point1 = $.EzSearch.getCityPoint(qi);
				var point2 = $.EzSearch.getCityPoint(zh);
				var marker1 = new Marker(new Point(point1[0],point1[1]),icon_start);
				var marker2 = new Marker(new Point(point2[0],point2[1]),icon_end);
				_MapApp.addOverlay(marker1);
				_MapApp.addOverlay(marker2);
				_MapApp.centerAndZoom(new Point((point1[0]+point2[0])/2,(point1[1]+point2[1])/2), 7);
	   var target=document.getElementById("top1");
		 target.style.display="block";
		 var target2=document.getElementById("top2");
		 target2.style.display="none";
		 
		 var target3=document.getElementById("divh");
		 target3.style.display="none";
		  location.hash='#top1';
		  $("#ispass").val("1");
	}

	  
	  function fanhui(){
		  var target=document.getElementById("top1");
		  target.style.display="none";
		  var target2=document.getElementById("top2");
		  
			 target2.style.display="block";
			 var target3=document.getElementById("divh");
			 target3.style.display="block";
		  location.hash='#top2';
		  var strs=$("#ispass").val();
		  if(strs=='1')
			  {
			  
			  $("#myModal").modal('show');
			  $("#ispass").val("0");
			  }
		  
	  }	    