
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
			if(arr[0]=="status")
				{
				  var status=decodeURI(decodeURI(arr[1]));
				  pstatus=status;
				  $("#statusid").val(status);
				  dotable(status);
				  break;
				}
		   }
}

function spyj(str)
{
  $("#reply").val(str);
  $("#myModal3").modal("toggle");
}

function miyao(str)
{
  $("#senderid").val(str);
  $("#myModal4").modal("toggle");
}



function dotable(status){
	
	if(status==0)
	{
		$("#fwmsxxid").html("待授权服务申请列表");
	}else if(status==1){
		$("#fwmsxxid").html("已通过授权服务申请列表");
		 
	}else{
		$("#fwmsxxid").html("未通过授权服务申请列表");
	}
	htmlstr="";
	if(status==0)
		{
           htmlstr="<tr><th>服务名称 </th><th width='20%'>应用名称 </th><th width='10%'> 应用描述 </th><th width='10%'>申请时间</th><th width='10%'>申请人 </th><th>操作</th></tr>";
           $("#tablethread").html(htmlstr);
           InitiateExpandableDataTable.init();
		}
	
	else{
		   htmlstr+="<tr><th width='15%'> 服务名称 </th> <th width='12%'> 应用名称 </th> <th width='12%'> 应用描述 </th>   <th width='12%'> 申请时间</th> <th width='8%'> 申请人 </th>";
		   htmlstr+=" <th width='12%'> 审批时间</th><th width='10%'> 审批人 </th><th width='7%'>审核状态</th><th width='12%'>操作</th></tr>";
		   $("#tablethread").html(htmlstr);
		   InitiateExpandableDataTable1.init();
	    }
}      
var InitiateExpandableDataTable = function () {
    return {
        init: function () {
            /* Formatting function for row details */
            function fnFormatDetails(oTable, nTr) {
                var aData = oTable.fnGetData(nTr);
                var sOut = '<table>';
                sOut += '</table>';
                return sOut;
            }
            var oTable = $('#expandabledatatable').dataTable({
                "sDom": "Tflt<'row DTTTFooter'<'col-sm-6'i><'col-sm-6'p>>",
                "sAjaxSource": "fg/findServiceApply.do?status=0&flag=0", //ajax请求地址
                "aoColumns" : [ {
                    "bVisible" : true,
                    "data" : "name",
                    "aTargets" : [0]
                }, {
                    "aTargets": [1],
                    "mRender": function (data, type, full) {
                    	 
                    	 if(full.appname==""||full.appname==null)
                    		 {
                    		 return '';
                    		 }else if(full.appname.length>10){
                    			 return full.appname.substring(0,10)+'...';
                    		 }else{
                    			 return full.appname;
                    		 }
                    		  
                    		 
                       
                    }
                }, {
                    "aTargets": [2],
                    "mRender": function (data, type, full) {
                    	 
                    	 if(full.description==""||full.description==null)
                    		 {
                    		 return '';
                    		 }else if(full.description.length>10){
                    			 return full.description.substring(0,10)+'...';
                    		 }else{
                    			 return full.description;
                    		 }
                    		  
                    		 
                       
                    }
                }
                , {
                    "bVisible" : true,
                    "data" : "applytime",
                    "aTargets" : [3]
                } 
                , {
                    "bVisible" : true,
                    "data" : "applyuser",
                    "aTargets" : [4] 
                }  
               ],
                "aoColumnDefs": [
                { "bSortable": false, "aTargets": [0] },
				{
				    sDefaultContent : " ",
				    aTargets : [1]
				},{
                    "aTargets": [5],
                    "mRender": function (data, type, full) {
                    	 
                        return '<a  class="btn btn-primary btn-sm"  onclick="appli(\''+full.name+'\',\''+full.description+'\',\''+full.servicecode+'\',\''+full.aid+'\',\''+full.methodname+'\')"><i class="icon-wrench icon-white" ></i>详情</a>'  ;
                    }
                }],
                "aaSorting": [[3, 'desc']],
                "aLengthMenu": [
                   [5,10, 15, 20],
                   [5, 10,15, 20]
                ],
                "iDisplayLength":10,
                "sPaginationType": "bootstrap",
                "oTableTools": {
                    "aButtons": []},
                "language": {
                    "search": "",
                    "sLengthMenu": "每页显示  _MENU_ 条记录",  
                    "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                    "oPaginate": {
                    	  
                         "sPrevious": "前一页",
                         "sNext": "后一页" 
                    },
                    "sZeroRecords": "&nbsp",
                    "sInfoEmpty": "没有数据"
                }
            });
            $('#expandabledatatable').on('click', ' tbody td .row-details', function () 
            {
                var nTr = $(this).parents('tr')[0];
                if (oTable.fnIsOpen(nTr)) {
                    $(this).addClass("fa-plus-square-o").removeClass("fa-minus-square-o");
                    oTable.fnClose(nTr);
                }
                else 
                {
                    $(this).addClass("fa-minus-square-o").removeClass("fa-plus-square-o");;
                    oTable.fnOpen(nTr, fnFormatDetails(oTable, nTr), 'details');
                }
            });
            $('#expandabledatatable_column_toggler input[type="checkbox"]').change(function () {
                var iCol = parseInt($(this).attr("data-column"));
                var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
                oTable.fnSetColumnVis(iCol, (bVis ? false : true));
            });

            $('body').on('click', '.dropdown-menu.hold-on-click', function (e) 
            {
                e.stopPropagation();
            })
        }
    };
}();



var InitiateExpandableDataTable1 = function () {
    return {
        init: function () {
            /* Formatting function for row details */
            function fnFormatDetails(oTable, nTr) {
                var aData = oTable.fnGetData(nTr);
                var sOut = '<table>';
                sOut += '</table>';
                return sOut;
            }
            
            /*
             * Insert a 'details' column to the table
             
            var nCloneTh = document.createElement('th');
            var nCloneTd = document.createElement('td');
            nCloneTd.innerHTML = '<i class="fa fa-plus-square-o row-details"></i>';

            $('#expandabledatatable thead tr').each(function () {
                this.insertBefore(nCloneTh, this.childNodes[0]);
            });

            $('#expandabledatatable tbody tr').each(function () {
                this.insertBefore(nCloneTd.cloneNode(true), this.childNodes[0]);
            }); */
       
            /*
             *   
             * Initialize DataTables, with no sorting on the 'details' column
             */
            var oTable = $('#expandabledatatable').dataTable({
                "sDom": "Tflt<'row DTTTFooter'<'col-sm-6'i><'col-sm-6'p>>",
                "sAjaxSource": "fg/findServiceApply.do?status="+$("#statusid").val(), //ajax请求地址
                "aoColumns" : [ {
                    "bVisible" : true,
                    "data" : "name",
                    "aTargets" : [0]
                }, {
                    "aTargets": [1],
                    "mRender": function (data, type, full) {
                    	 
                    	 if(full.appname==""||full.appname==null)
                    		 {
                    		 return '';
                    		 }else if(full.appname.length>10){
                    			 return full.appname.substring(0,10)+'...';
                    		 }else{
                    			 return full.appname;
                    		 }
                    		  
                    		 
                       
                    }
                } , 
                {
                    "aTargets": [2],
                    "mRender": function (data, type, full) {
                    	 
                    	 if(full.description==""||full.description==null)
                    		 {
                    		 return '';
                    		 }else if(full.description.length>10){
                    			 return full.description.substring(0,10)+'...';
                    		 }else{
                    			 return full.description;
                    		 }
                    		  
                    		 
                       
                    }
                } 
                , {
                    "bVisible" : true,
                    "data" : "applytime",
                    "aTargets" : [3]
                } 
                , {
                    "bVisible" : true,
                    "data" : "applyuser",
                    "aTargets" : [4] 
                }  , {
                    "bVisible" : true,
                    "data" : "dealtime",
                    "aTargets" : [5]
                } 
                , {
                    "bVisible" : true,
                    "data" : "dealuser",
                    "aTargets" : [6]
                } 
               ],
                "aoColumnDefs": [
                { "bSortable": false, "aTargets": [0] },
				{
				    sDefaultContent : " ",
				    aTargets : [1]
				},{
                    "aTargets": [7],
                    "mRender": function (data, type, full) {
                    	 
                    	var statusid=$("#statusid").val();
                    	if(statusid=="1")
                    		{
                    		  return '通过'  ;
                    		}else{
                    		  return '拒绝'  ;
                    		}
                       
                    }
                } ,{
                    "aTargets": [8],
                    "mRender": function (data, type, full) {
                    	var statusid=$("#statusid").val();
                    	if(statusid=="1")
                		{
                    	//return '<div class="btn-group btn-group-xs" role="group"><a  class="btn btn-danger btn-xs"   onclick="miyao(\''+full.funcode+'\')"></i>查看秘钥</a><a  class="btn btn-primary btn-xs"  onclick="appli(\''+full.name+'\',\''+full.description+'\',\''+full.servicecode+'\',\''+full.aid+'\',\''+full.methodname+'\')"><i class="icon-wrench icon-white"></i>详情</a></div>'  ;
                		
                    		/*if(full.type=='Analyse')
                    		{
                    			return '<div class="btn-group btn-group-xs" role="group"><a  class="btn btn-danger btn-xs"   onclick="miyao(\''+full.aid+'\')"></i>查看秘钥</a><a  class="btn btn-primary btn-xs"  onclick="appli(\''+full.name+'\',\''+full.description+'\',\''+full.servicecode+'\',\''+full.aid+'\',\''+full.methodname+'\')"><i class="icon-wrench icon-white"></i>详情</a></div>'  ;
                    		}else
                    		{
                    			return '<div class="btn-group btn-group-xs" role="group"><a  class="btn btn-danger btn-xs"   onclick="miyao(\''+full.funcode+'\')"></i>查看秘钥</a><a  class="btn btn-primary btn-xs"  onclick="appli(\''+full.name+'\',\''+full.description+'\',\''+full.servicecode+'\',\''+full.aid+'\',\''+full.methodname+'\')"><i class="icon-wrench icon-white"></i>详情</a></div>'  ;
                    		}*/
                    		return '<div class="btn-group btn-group-xs" role="group"><a  class="btn btn-danger btn-xs"   onclick="miyao(\''+full.funcode+'\')"></i>查看秘钥</a><a  class="btn btn-primary btn-xs"  onclick="appli(\''+full.name+'\',\''+full.description+'\',\''+full.servicecode+'\',\''+full.aid+'\',\''+full.methodname+'\')"><i class="icon-wrench icon-white"></i>详情</a></div>'  ;
                		}else{
                			return '<div class="btn-group btn-group-xs" role="group"><a  class="btn btn-info btn-xs"  onclick="spyj(\''+full.reply+'\')" ></i>审核意见</a><a  class="btn btn-primary btn-xs"  onclick="appli(\''+full.name+'\',\''+full.description+'\',\''+full.servicecode+'\',\''+full.aid+'\',\''+full.methodname+'\')"><i class="icon-wrench icon-white"></i>详情</a></div>'  ;
                		}
                        
                    }
                }],
                "aaSorting": [[5, 'desc']],
                "aLengthMenu": [
                   [5,10, 15, 20],
                   [5, 10,15, 20]
                ],
                "iDisplayLength":10,
                "sPaginationType": "bootstrap",
                "oTableTools": {
                    "aButtons": []},
                "language": {
                    "search": "",
                    "sLengthMenu": "每页显示  _MENU_ 条记录",  
                    "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                    "oPaginate": {
                    	  
                         "sPrevious": "前一页",
                         "sNext": "后一页" 
                    },
                    "sZeroRecords": "&nbsp",
                    "sInfoEmpty": "没有数据"
                }
            });
            $('#expandabledatatable').on('click', ' tbody td .row-details', function () 
            {
                var nTr = $(this).parents('tr')[0];
                if (oTable.fnIsOpen(nTr)) {
                    $(this).addClass("fa-plus-square-o").removeClass("fa-minus-square-o");
                    oTable.fnClose(nTr);
                }
                else 
                {
                    $(this).addClass("fa-minus-square-o").removeClass("fa-plus-square-o");;
                    oTable.fnOpen(nTr, fnFormatDetails(oTable, nTr), 'details');
                }
            });
            $('#expandabledatatable_column_toggler input[type="checkbox"]').change(function () {
                var iCol = parseInt($(this).attr("data-column"));
                var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
                oTable.fnSetColumnVis(iCol, (bVis ? false : true));
            });

            $('body').on('click', '.dropdown-menu.hold-on-click', function (e) 
            {
                e.stopPropagation();
            })
        }
    };
}();

