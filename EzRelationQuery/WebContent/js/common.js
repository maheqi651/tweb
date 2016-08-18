	function getBaseURL()
	{
		var strFullPath=window.document.location.href;
		var strPath=window.document.location.pathname;
		var pos=strFullPath.indexOf(strPath);
		var prePath=strFullPath.substring(0,pos);
		var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
		var baseurl = prePath+postPath;
		return baseurl;
	}
	function dotime(timestr){
		//20140721135217
		if(timestr==""||timestr==null)
			return "";
		var nian=timestr.substr(0,4);
		 
		var yy=timestr.substr(4,2);
		var rr=timestr.substr(6,2);
		var hh=timestr.substr(8,2);
		var mm=timestr.substr(10,2);
		var ss=timestr.substr(12,2);
	//	alert(nian+"-"+yy+"-"+rr+" "+" "+hh+":"+mm+":"+ss);
		return nian+"-"+yy+"-"+rr+" "+" "+hh+":"+mm;
	}
	String.prototype.trim=function(){
		return this.replace(/(^\s*)|(\s*$)/g, "");
	}