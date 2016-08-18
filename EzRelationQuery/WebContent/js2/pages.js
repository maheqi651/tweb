var totalpage,pagesize,cpage,count,curcount,outstr,cpageQHB; 
//初始化 
cpage = 1; 
cpageQHB=1;
totalpage = totalpagecount; 
pagesize =3; 
outstr = ""; 

function reloadpage(target){//查询列表
	 emptytable();
	var startpagecount=(target-1)*pagesize;
	var endpagecount=startpagecount+pagesize;
	if(endpagecount<result.length)
	{
		for(var j=startpagecount;j<endpagecount;j++){
		//alert("---"+x);
		$("#getresult tbody").append("<tr><td>"+result[j].name+"</td> <td>"+result[j].size+"B </td> <td>"+result[j].lastModification+"</td> <td>"+result[j].owner+"</td> <td><div class='btn-group'><a onclick='getQualifiers(\""+result[j].name+"\")' class='btn btn-success btn-mini' >表字段</a> <a onclick='getKeys(\""+result[j].name+"\")' class='btn btn-success btn-mini' >主键</a></div></td></tr> ");
	}
	}else{
		 for(var i=startpagecount;i<result.length;i++)
			{
			$("#getresult tbody").append("<tr><td>"+result[i].name+"</td> <td>"+result[i].size+"B </td> <td>"+result[i].lastModification+"</td> <td>"+result[i].owner+"</td><td><div class='btn-group'><a onclick='getQualifiers(\""+result[i].name+"\")'  class='btn btn-success btn-mini' >表字段</a> <a onclick='getKeys(\""+result[i].name+"\")' class='btn btn-success btn-mini' >主键</a></div></td> </tr> ");
			}
	}
	
}



function reloadpageQHB(target){//查询列表
	 emptytableQHB();
	 //alert("target"+target)
	var startpagecount=(target-1)*pagesize;
	var endpagecount=startpagecount+pagesize;
	if(endpagecount<totalsize)
	{     
		     var count=0;
			 for(var x in resultQHB){
				++count;
				
				if(count>startpagecount&&count<=endpagecount)
					{
					//alert(x+"----");
					$("#hbaseresult tbody").append("<tr><td>"+x+"</td><td>"+resultQHB[x].ServerIP+"</td> <td>"+resultQHB[x].QuorumPort+"</td> <td>"+resultQHB[x].username+"</td> <td><div class='btn-group'><a onclick='getHBaseInfo(\""+x+"\")' class='btn btn-success btn-mini' >实例详情</a> <a  onclick='updateHBase(\""+x+"\")'  class='btn btn-success btn-mini  ' >实例编辑</a></div></td> </tr> ");
					}
			}
		 //alert("---"+count);
			
	   
	}else{
		var count1=0;
		for(var x in resultQHB){
			 ++count1;
			// alert(x+"----");
			 if(count1>startpagecount){ 
					$("#hbaseresult tbody").append("<tr><td>"+x+"</td><td>"+resultQHB[x].ServerIP+"</td> <td>"+resultQHB[x].QuorumPort+"</td> <td>"+resultQHB[x].username+"</td> <td><div class='btn-group'><a onclick='getHBaseInfo(\""+x+"\")' class='btn btn-success btn-mini' >实例详情</a> <a  onclick='updateHBase(\""+x+"\")'  class='btn btn-success btn-mini  ' >实例编辑</a></div></td> </tr> ");
			 }
			 }
		//alert("---"+count1);
	}
	
}


function gotopage(target) 
{     
	cpage = target;        //把页面计数定位到第几页 
	reloadpage(target);    //调用显示页面函数显示第几页,这个功能是用在页面内容用ajax载入的情况 
	setpage(); 
} 


function gotopageQHB(target) 
{     
	cpageQHB= target;        //把页面计数定位到第几页 
	reloadpageQHB(target);    //调用显示页面函数显示第几页,这个功能是用在页面内容用ajax载入的情况 
	setpageQHB(); 
} 
function setpage() 
{ 
	totalpage = totalpagecount; 
	 
	if(totalpage<=10){        //总页数小于十页 
		outstr = outstr + "<li class='disabled'><a>&laquo;</a></li>"; 
		for (count=1;count<=totalpage;count++) 
		{    if(count!=cpage) 
		{ 
			outstr = outstr + "<li><a href='javascript:void(0)' onclick='gotopage("+count+")'>"+count+"</a></li>"; 
		}else{ 
			outstr = outstr + "<li class='active'><span class='current' >"+count+"</span></li>"; 
		} 
		} 
		outstr = outstr + "<li class='disabled'><a>&raquo;</a></li>"; 
	} 
	if(totalpage>10){        //总页数大于十页 
		if(parseInt((cpage-1)/10) == 0) 
		{        
			outstr = outstr + "<li class='disabled'><a>&laquo;</a></li>"; 
			for (count=1;count<=10;count++) 
			{    if(count!=cpage) 
			{ 
				outstr = outstr + "<li><a href='javascript:void(0)' onclick='gotopage("+count+")'>"+count+"</a></li>"; 
			}else{ 
				outstr = outstr + "<li><span class='current'>"+count+"</span></li>"; 
			} 
			} 
			outstr = outstr + "<li><a href='javascript:void(0)' onclick='gotopage("+count+")'> &raquo; </a></li>"; 
		} 
		else if(parseInt((cpage-1)/10) == parseInt(totalpage/10)) 
		{     
			outstr = outstr + "<li><a href='javascript:void(0)' onclick='gotopage("+(parseInt((cpage-1)/10)*10)+")'>&laquo;</a></li>"; 
			for (count=parseInt(totalpage/10)*10+1;count<=totalpage;count++) 
			{    if(count!=cpage) 
			{ 
				outstr = outstr + "<li><a href='javascript:void(0)' onclick='gotopage("+count+")'>"+count+"</a></li>"; 
			}else{ 
				outstr = outstr + "<li class='active' ><span class='current'>"+count+"</span></li>"; 
			} 
			} 
			outstr = outstr + "<li class='disabled'><a>&raquo;</a></li>"; 
		} 
		else 
		{     
			outstr = outstr + "<li><a href='javascript:void(0)' onclick='gotopage("+(parseInt((cpage-1)/10)*10)+")'>&laquo;</a></li>"; 
			for (count=parseInt((cpage-1)/10)*10+1;count<=parseInt((cpage-1)/10)*10+10;count++) 
			{         
				if(count!=cpage) 
				{ 
					outstr = outstr + "<li><a href='javascript:void(0)' onclick='gotopage("+count+")'>"+count+"</a></li>"; 
				}else{ 
					outstr = outstr + "<li class='active'><span class='current'>"+count+"</span></li>"; 
				} 
			} 
			outstr = outstr + "<li><a href='javascript:void(0)' onclick='gotopage("+count+")'> &raquo; </a></li>"; 
		} 
	}     
	//document.getElementById("setpage").innerHTML = "<div id='setpage'><span id='info'>共"+totalpage+"页|第"+cpage+"页<\/span><ul>" + outstr + "</ul><\/div>"; 
	document.getElementById("setpage").innerHTML = "<div id='setpage'><ul>" + outstr + "</ul><\/div>";
	outstr = ""; 
} 
 
//实例管理查询
function setpageQHB() 
{ 
	var totalpage = totalpagecountQHB; 
	 
	if(totalpage<=10){        //总页数小于十页 
		outstr = outstr + "<li class='disabled'><a>&laquo;</a></li>"; 
		for (var count=1;count<=totalpage;count++) 
		{    if(count!=cpageQHB) 
		{ 
			outstr = outstr + "<li><a href='javascript:void(0)' onclick='gotopageQHB("+count+")'>"+count+"</a></li>"; 
		}else{ 
			outstr = outstr + "<li class='active'><span class='current' >"+count+"</span></li>"; 
		} 
		} 
		outstr = outstr + "<li class='disabled'><a>&raquo;</a></li>"; 
	} 
	if(totalpage>10){        //总页数大于十页 
		if(parseInt((cpageQHB-1)/10) == 0) 
		{        
			outstr = outstr + "<li class='disabled'><a>&laquo;</a></li>"; 
			for (count=1;count<=10;count++) 
			{    if(count!=cpageQHB) 
			{ 
				outstr = outstr + "<li><a href='javascript:void(0)' onclick='gotopageQHB("+count+")'>"+count+"</a></li>"; 
			}else{ 
				outstr = outstr + "<li><span class='current'>"+count+"</span></li>"; 
			} 
			} 
			outstr = outstr + "<li><a href='javascript:void(0)' onclick='gotopageQHB("+count+")'> &raquo; </a></li>"; 
		} 
		else if(parseInt((cpageQHB-1)/10) == parseInt(totalpage/10)) 
		{     
			outstr = outstr + "<li><a href='javascript:void(0)' onclick='gotopageQHB("+(parseInt((cpageQHB-1)/10)*10)+")'>&laquo;</a></li>"; 
			for (count=parseInt(totalpage/10)*10+1;count<=totalpage;count++) 
			{    if(count!=cpageQHB) 
			{ 
				outstr = outstr + "<li><a href='javascript:void(0)' onclick='gotopageQHB("+count+")'>"+count+"</a></li>"; 
			}else{ 
				outstr = outstr + "<li class='active' ><span class='current'>"+count+"</span></li>"; 
			} 
			} 
			outstr = outstr + "<li class='disabled'><a>&raquo;</a></li>"; 
		} 
		else 
		{     
			outstr = outstr + "<li><a href='javascript:void(0)' onclick='gotopageQHB("+(parseInt((cpageQHB-1)/10)*10)+")'>&laquo;</a></li>"; 
			for (count=parseInt((cpageQHB-1)/10)*10+1;count<=parseInt((cpageQHB-1)/10)*10+10;count++) 
			{         
				if(count!=cpageQHB) 
				{ 
					outstr = outstr + "<li><a href='javascript:void(0)' onclick='gotopageQHB("+count+")'>"+count+"</a></li>"; 
				}else{ 
					outstr = outstr + "<li class='active'><span class='current'>"+count+"</span></li>"; 
				} 
			} 
			outstr = outstr + "<li><a href='javascript:void(0)' onclick='gotopageQHB("+count+")'> &raquo; </a></li>"; 
		} 
	}     
	//document.getElementById("setpage").innerHTML = "<div id='setpage'><span id='info'>共"+totalpage+"页|第"+cpage+"页<\/span><ul>" + outstr + "</ul><\/div>"; 
	document.getElementById("hbasepage").innerHTML = "<div id='hbasepage'><ul>" + outstr + "</ul><\/div>";
	outstr = ""; 
} 

