var dataresult=null;
function showdetail(content){
	 var str=dataresult[content].request_CONTENT;
	 $("#request_content").val(formatXml(str)); 
	 $('#myModal3').modal('toggle');
}
//格式化xml
function formatXml(text)
{
    //去掉多余的空格
	if(text==null||text=='')
		return '';
    text = '\n' + text.replace(/(<\w+)(\s.*?>)/g,function($0, name, props)
    {
        return name + ' ' + props.replace(/\s+(\w+=)/g," $1");
    }).replace(/>\s*?</g,">\n<");

    //把注释编码
    text = text.replace(/\n/g,'\r').replace(/<!--(.+?)-->/g,function($0, text)
    {
        var ret = '<!--' + escape(text) + '-->';
        //alert(ret);
        return ret;
    }).replace(/\r/g,'\n');

    //调整格式
    var rgx = /\n(<(([^\?]).+?)(?:\s|\s*?>|\s*?(\/)>)(?:.*?(?:(?:(\/)>)|(?:<(\/)\2>)))?)/mg;
    var nodeStack = [];
    var output = text.replace(rgx,function($0,all,name,isBegin,isCloseFull1,isCloseFull2 ,isFull1,isFull2){
        var isClosed = (isCloseFull1 == '/') || (isCloseFull2 == '/' ) || (isFull1 == '/') || (isFull2 == '/');
        //alert([all,isClosed].join('='));
        var prefix = '';
        if(isBegin == '!')
        {
            prefix = getPrefix(nodeStack.length);
        }
        else 
        {
            if(isBegin != '/')
            {
                prefix = getPrefix(nodeStack.length);
                if(!isClosed)
                {
                    nodeStack.push(name);
                }
            }
            else
            {
                nodeStack.pop();
                prefix = getPrefix(nodeStack.length);
            }

        }
            var ret =  '\n' + prefix + all;
            return ret;
    });

    var prefixSpace = -1;
    var outputText = output.substring(1);
    //alert(outputText);

    //把注释还原并解码，调格式
    outputText = outputText.replace(/\n/g,'\r').replace(/(\s*)<!--(.+?)-->/g,function($0, prefix,  text)
    {
        //alert(['[',prefix,']=',prefix.length].join(''));
        if(prefix.charAt(0) == '\r')
            prefix = prefix.substring(1);
        text = unescape(text).replace(/\r/g,'\n');
        var ret = '\n' + prefix + '<!--' + text.replace(/^\s*/mg, prefix ) + '-->';
        //alert(ret);
        return ret;
    });

    return outputText.replace(/\s+$/g,'').replace(/\r/g,'\r\n');
}
function getPrefix(prefixIndex)
{
    var span = '    ';
    var output = [];
    for(var i = 0 ; i < prefixIndex; ++i)
    {
        output.push(span);
    }

    return output.join('');
} 

function dotime(timestr){
	//20140721135217
	if(timestr==""||timestr==null)
		return "无";
	var nian=timestr.substr(0,4);
	 
	var yy=timestr.substr(4,2);
	var rr=timestr.substr(6,2);
	var hh=timestr.substr(8,2);
	var mm=timestr.substr(10,2);
	var ss=timestr.substr(12,2);
	var ms=timestr.substr(14,2);
//	alert(nian+"-"+yy+"-"+rr+" "+" "+hh+":"+mm+":"+ss);
	return nian+"-"+yy+"-"+rr+" "+" "+hh+":"+mm+":"+ms;
}

function dealstr(datastr){
	 if(datastr==null||datastr=='null')
	 return "无";
	 else
		 return datastr;
}
function searchVisitInfo() {
		 
	    var  appname= $("#app_name").val();
	    var  requsetip= $("#requset_ip").val();
	    var  senderid= $("#sender_id").val();
	    var  servicename= $("#service_name").val();
	    var  startdate= $("#startdate").val();
	    var  enddate= $("#enddate").val();
		var keyword=null;
		 
		/*keyword = {
			passenger_name : $("#passenger_name").val()
		};*/
		
		$("#pagerrow").html("");
		$("#pagerculom").html("");
		 
		var param =new Object();
		if(appname!=null&&appname!="")
			{
			   param.APP_NAME=appname;
			}
		if(requsetip!=null&&requsetip!="")
		{
			 
		   param.CONSUMER_IP=requsetip;
		}
		if(senderid!=null&&senderid!="")
		{
		   param.SENDER_ID=senderid;
		}
		if(servicename!=null&&servicename!="")
		{
		   param.SERVICE_NAME=servicename;
		}
		if(startdate!=null&&startdate!="")
		{
		   param.REQEUST_DATETIME_start=startdate;
		}
		if(enddate!=null&&enddate!="")
		{
		   param.REQEUST_DATETIME_end=enddate;
		}
		param.page=1;
		
		$.extend(param, keyword);
		$("#pager").pagination(
						  "fg/findserviceVisitInfo.do",
						param,
						function(data) {
							  
							result=data.result;
							dataresult=result;
							$("#pinfo").html("");
							 
							var points = null;
							var len = data.result.length;
							var content = "<thead  > <tr style='align:center'>  " +
							/*" <th  align='center' width='8%'>请求ID</th>" +
							" <th  width='10%'>消息ID</th> " +*/
							"<th  width='20%'>应用名称</th>" +
							" <th  width='20%'>请求时间</th>" +
							"<th  width='15%'>服务名称</th> " +
							" <th  width='15%'>请求IP</th>" +
							" <th  width='10%'>返回结果数</th> " +
							"<th  width='15%'>请求内容 </th></tr>" +
							"</thead> <tbody style='border:none'>";
							 
							for (var i = 0; i < len; i++) {
								var temp = result[i];
							 
								content += "<tr>" ;
									/*	"<td title='" + temp.request_ID + "' style='cursor:pointer'>" + temp.request_ID.substr(0,10)+'...' + "</td>";
								content += " <td title='" +temp.msg_ID + "' style='cursor:pointer'>" + temp.msg_ID.substr(0,10)+'...' + "</td>";
							*/	
								content += "<td> " + dealstr(temp.app_NAME) + "</a></td>";
								content += "<td>" + dotime(temp.reqeust_DATETIME) + "</td>";
								content += " <td> " + dealstr(temp.service_NAME) + "</td>";
								content += "<td> " + dealstr(temp.consumer_IP) + "</td>";
								content += "<td > " + dealstr(temp.response_RESULT_COUNT)+ "</td>";
								//var temp11={temp[0],temp[1],temp[2],temp[3],temp[4],temp[5],temp[6],temp[7],temp[8],temp[9],temp[10],temp[11],temp[12]};
							//alert(temp[12])
								content += "<td ><div class='btn-group'> <button class='btn  btn-xs btn-primary' onclick='showdetail("+i+")' >详情</button></td> ";
								content += "</tr> ";
								// alert(content);

							}
							content += "</tbody>";
							$("#pagerrow").html(content);
							if (len <= 0) {
								$("#pagerculom").html("");
							}
						 
						});
	}