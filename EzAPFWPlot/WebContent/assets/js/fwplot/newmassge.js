	
function searchnewmassege(){
		$.ajax({
			type : "POST",
			url : "fg/findMassegeNew.do",
			data : "",
			dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
			type : "POST",
			cache : false,
			success : function(data) {
				 var htmlsstr='';
				 var resultlist=data.result;
				 var counts=data.count;
				 //alert(counts);
				 $("#newmassegelistcount").html(counts);
				 if(resultlist!=null)
				 {
				    for(i=0;i<resultlist.length;i++)
				    {
				    	htmlsstr+='<li><a href="massegelist.html?status=0&mid='+resultlist[i].id+'"> <div class="message"> <span class="message-sender">  <span class="glyphicon glyphicon-flag"   style="color:red">&nbsp;<i style="color:#20377A;font-style:normal">'+resultlist[i].title+' </i></span></span> </div> </a> </li>';
				    }
				      if(counts>resultlist.length)
				    	{
				    	htmlsstr+='<li style="text-align:right"><a href="massegelist.html?status=0"  style="color:red" >      <i class="glyphicon glyphicon-hand-right"></i> 获取更多消息    </a> </li>';
				    	}
				 }
				 if(htmlsstr=='')
					 {
					 htmlsstr+='<li style="text-align:center;color:red;height:25px;">没有新的消息  </li>';
					 }
				 $("#newmassegelist").html(htmlsstr);
				 }});
		
	}
	
	
	function opennewwindow(chname){
		window.open("http://10.235.36.113:8090/FeedbackMg/feedback.jsp?cardId=23134545654555555&userName="+chname+"&sysName=服务资源中心","scrollbars=yes,location=no,resizable=yes");
	}
	function loadSystemSet(){
		  
		
		// login/findusername
		 $.ajax({
				type : "POST",
				url : "login/findusername.do",
				data : "",
				dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
				type : "POST",
				cache : false,
				success : function(data) {
					
					 var result=data.result;
					 
					 if(result=='error')
						 {
						// alert(result);
						  window.location.reload(true);
						  return ;
						 }
					     var li= document.createElement("li"); 
						//salert(li);
						 var htmlstr='';
						 var htmltc='';
						 var li0= document.createElement("li"); 
							var ss=document.getElementsByClassName("account-area");
						htmltc="";
						htmltc+=' <a class="login-area dropdown-toggle"  style="color:white;margin-top:5px;">'; 
						htmltc+=' <i class="glyphicon glyphicon-user">'+result+'</i></a>';
						li0.innerHTML=htmltc;
						ss[0].appendChild(li0);

						var li1= document.createElement("li"); 
						htmltc="";
						htmltc+=' <a class="login-area dropdown-toggle"  style="color:white;margin-top:5px;" onclick="opennewwindow(\''+result+'\')">'; 
						htmltc+=' <i class="glyphicon glyphicon-send">意见反馈</i></a>';
						li1.innerHTML=htmltc;
						ss[0].appendChild(li1);
						
						htmltc='';
						htmltc+=' <a class="login-area dropdown-toggle" onclick="logoutsystem()" style="color:white;margin-top:5px;">'; 
						htmltc+=' <i class="glyphicon glyphicon-off">退出</i></a>';
						li.innerHTML=htmltc;
						// alert(htmltc);
						ss[0].appendChild(li);
					 }});
		
		
		
		/* ss.insertBefore(li0,ss[0]);*/
		 
		 //导航
		 //loaddaohang();
		
	}
	function loaddaohang(status){
		$.ajax({
			type : "POST",
			url : "login/findrole.do",
			data : "",
			dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
			type : "POST",
			cache : false,
			success : function(data) {
				var result=data.result;
				if("error"==result)
					{
					
					}else{
						
						var strhtml='';
						if(status==1)
							{
							strhtml+='<li class="active"><a href="plotshow.html"><i class="glyphicon glyphicon-film"></i>平台介绍</a></li>';
							}else{
								strhtml+='<li><a href="plotshow.html"><i class="glyphicon glyphicon-film"></i>平台介绍</a></li>';
							}
						if(status==3)
						{
						strhtml+='<li class="active"><a href="index.html"><i class="glyphicon glyphicon-globe"></i>服务资源</a></li>';
						}else{
							strhtml+='<li  ><a href="index.html"><i class="glyphicon glyphicon-globe"></i>服务资源</a></li>';
						}
						if("admin"==result)
							{
							if(status==2)
								{
								strhtml+='<li class="active"><a href="fwsqdeallist.html"><i class="glyphicon glyphicon-list"></i> 管理中心</a></li>';
								}else{
									strhtml+='<li ><a href="fwsqdeallist.html"><i class="glyphicon glyphicon-list"></i> 管理中心</a></li>';
								}
							
							}else{
							}
						
						if(status==4)
							{
							strhtml+='<li class="active"><a href="publishservice.html"><i class="glyphicon glyphicon-user"></i>个人中心</a></li><li  >&nbsp;</li>';
							}else{
								strhtml+='<li><a href="publishservice.html"><i class="glyphicon glyphicon-user"></i>个人中心</a></li><li  >&nbsp;</li>';
							}
						
					    $("#daohang").html(strhtml);
					}
				 }});
		
		
		
		
	}
	function reloadsystem(){
		window.location.reload();
	}
	function lgout(){
			  $.ajax({
					type : "POST",
					url : "login/to_logout.do",
					data : "",
					dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
					type : "POST",
					cache : false,
					success : function(data) {
						  window.location.href="index.html";
						 }});
		   
		
		
		
	}
	function logoutsystem(){
		$('#myModal68').modal('toggle');
	}
	 
	