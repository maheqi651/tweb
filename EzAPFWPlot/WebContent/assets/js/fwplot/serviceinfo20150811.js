 function load(){
    	  $('#star').raty({
    		 
    		  path:'raty/images',
    			  round : {down:.26,full:.6,up:.76},
    			  hints: [1,2,3, 4,5] ,
    			  click:function(score,evt){
    				  $("#scoreselect").val(score);
    			  }
    			  });
    	  
      }
function fbpf(){
	var param=new Object();
	param.score=$("#scoreselect").val();
	var serviceid=$("#serviceid").val();
	param.serviceid=serviceid;
	param.servicename='';
	var pfcontent=$("#pfcontent").val();
	if(pfcontent==null||pfcontent=="")
		{
		alert("评价内容不可以为空！");
		return;
		}
  
	if(pfcontent.length>150)
		
		{
		  alert("评论内容超出150字，请重新输入，您当前输入字符长度为"+pfcontent.length+"！");
		  return ;
		}
	param.content=pfcontent
	$.ajax({
		type : "POST",
		url : "fg/addsocre.do",
		data : param,
		dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
		type : "POST",
		cache : false,
		success : function(data) {
			 //window.location.reload();
			$("#pfcontent").val("")
			 
			findservicedetail(serviceid);
		}});
	
	
}
function findscore(serviceid){
	$("#serviceid").val(serviceid);
	var param =new Object();
	var keyword=null;
	param.serviceid=serviceid;
	$.extend(param, keyword);
	$("#pager").pagination(
					  "fg/findScoreList.do",
					param,
					function(data) {
						result=data.result;
						var username=data.username;
						$("#adduser").val(username);
						var len=result.length;
						var content="";
						$("#scorelist").html(content);
						var start=new Array(len);
						var scores=new Array(len);
						for (var i = 0; i < len; i++) {
							var temp = result[i];
							content +="<div  class='col-lg-12 col-md-12 col-sm-12 col-xs-12' style='border-bottom: 1px dotted #5286D6;padding-bottom: 5px;padding-top: 10px;'>";
							//content +="<div style='float:left'><img   src='assets/img/ditu.png' style='width:60px;height:60px;'> </div>";
							content +="<div class='col-lg-12 col-md-12 col-sm-12 col-xs-12'><ul>";
							content +="<li><font color='blue'>"+temp.username+"</font></li><li>"+temp.ip+"</li><li  style='width:30%; '><span id='start11"+temp.id+"'></span>&nbsp;"+temp.score+"分</li> <li style='width:30%;text-align:right;'>"+temp.addtime+"</li></ul>";
							start[i]="start11"+temp.id;
							scores[i]=temp.score;
							content +="<p style='white-space:normal; word-wrap:break-word;width:100%;'>"+temp.content+"</p>";
							content +="</div></div> ";
						}
						 //alert(data.count);
						$("#pfrs").html(data.count);
						$("#scorelist").html(content);
						for (var i = 0; i < len; i++)
							{
							   $("#"+start[i]).raty({path:'raty/images',readOnly:true,score:scores[i]});
							}
					});
}  

   
function findservicedetail(serviceid){
	$.ajax({
		type : "POST",
		url : "fg/findserviceByid.do",
		data : "serviceid="+serviceid,
		dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
		type : "POST",
		cache : false,
		success : function(obj) {
			var result=obj.result;
			var html1='';
			$("#fwname").html(result.name);
			$("#fwsm").html(result.info);
			$("#fwregion").html(result.regiontype);
			var eZServiceMditemDEF=result.eZServiceMditemDEF;
			var fwdz="无";
			var times="无";
			var sldz="无";
			var bzdz="无";
			var imageurl=null;
			if(eZServiceMditemDEF!=null)
				{
				  times=eZServiceMditemDEF.time;
				  fwdz=eZServiceMditemDEF.company;
				  sldz=eZServiceMditemDEF.version;
				  bzdz=eZServiceMditemDEF.get;
				  imageurl=eZServiceMditemDEF.imageurl;
				}
			 
			$("#fwaddtime").html(times);
			$("#fwtype").html(result.type);
			$("#fwaccess").html(result.accesscount);
			$("#hqmy").html("<a class='btn btn-success btn' onclick=\'appli(\""+result.name+"\",\""+result.info+"\",\""+result.serviceid+"\",\""+result.methodname+"\")\'><i class='glyphicon glyphicon-save'></i> &nbsp;获 取 密 钥</a>");
			if(imageurl!=null)
				$("#serviceimage").attr("src","../upload/"+imageurl);
			var score=0;
			if(result.score!=null)
				score=result.score;
			$('#star1').raty({path:'raty/images',readOnly:true,score:score});
			$("#fwinfo").html(result.info);
			$("#scorepfs").html(score+"分");
			 pdurl(fwdz,1);
			 pdurl(sldz,2);
			 pdurl(bzdz,3);
			/*$("#fwdz").html("服务地址：<a href='"+fwdz+"' target='blank'>"+fwdz+"</a>");
			$("#sldz").html("示例地址：<a href='"+sldz+"' target='blank'>"+sldz+"</a>");
			$("#demodz").html("帮助地址：<a href='"+bzdz+"' target='blank'>"+bzdz+"</a>");*/
			findscore(result.serviceid);
			
			
			var t = document.getElementsByTagName("img");
			for(i = 0; i < t.length; i++){
			      t.item(i).onerror = function(){
			        if(this.id =="defaultimag"){
			            this.src = "image/Tulips.jpg";
			            this.onerror = null;
			          }
			      }
			   }
		}
	});
}
function pdurl(urls,status){
	 $("#fwdz").html("服务地址：无");
	 /*$("#sldz").html("示例地址：无");*/
	 $("#demodz").html("使用帮助");
	if(urls==null)
		{
		urls="无";
		}
	 $.ajax({
		type : "POST",
		url : "fg/testRealUrl.do?urls="+urls,
		data : "",
		dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
		type : "POST",
		cache : false,
		success : function(result) {
			if(result.status==200)
				{
				if( status==1){
					 $("#fwdz").html("服务地址：<a href='"+urls+"' target='blank' style='cursor: pointer'>"+urls+"</a>");
				 }else if( status==2){
					/* $("#sldz").html("示例地址：<a href='"+urls+"' target='blank' style='cursor: pointer'>"+urls+"</a>");*/
				 }else if( status==3){
					 $("#demodz").html("<a href='"+urls+"' target='blank' style='cursor: pointer'>使用帮助</a>");
				 }
				}else{
					if( status==1){
						 $("#fwdz").html("服务地址：<a onclick='javascript:alert(\"该链接为无效链接！\")' style='cursor: pointer'>"+urls+"</a>");
					 }else if( status==2){
						/* $("#sldz").html("示例地址：<a onclick='javascript:alert(\"该链接为无效链接！\")' style='cursor: pointer'>"+urls+"</a>");*/
					 }else if( status==3){
						 $("#demodz").html("<a onclick='javascript:alert(\"该链接为无效链接！\")' style='cursor: pointer'>使用帮助</a>");
					 }
				}
			 
		} 
	}); 
	
  
	/* $.ajax({
		  url: 'http://172.18.70.49:8080/EzAPFWPlot/',
		  type: 'GET',
		  complete: function(response) {
			  alert(response.status);
		   if(response.status == 200) {
			   
		    alert('有效');
		   } else {
		    alert('无效');
		   }
		  }
		 });*/
}
function NulltoStr(str){
	 
	if(str==null)
		return "无";
	return str;
}
function initParam(){
		var strFullPath = window.document.location.href;
		var index = strFullPath.indexOf("?");
		if(index==-1){
			return;
		}
		var params = strFullPath.substr(index+1);
		var listarr=params.split("&");//参数
		for(i=0;i<listarr.length;i++){
			var arr = listarr[i].split("=");
			  if(arr[0]=="serviceid")
				{
				 findservicedetail(decodeURI(decodeURI(arr[1])));
				 break;
				}
			  if(arr[0]=="flag")
				{
				 window.location.reload(true); 
				 break;
				}
		   }
}
	
