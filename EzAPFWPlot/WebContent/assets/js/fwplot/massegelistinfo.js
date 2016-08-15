
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
				  $("#statusid").val(status);
				  pstatus=status;
				  dotable(status);
				  
				}
			if(arr[0]=="mid")
			{
			  var mid=decodeURI(decodeURI(arr[1]));
			  
			  massegeinfo1(mid);
			  
			}
		   }
}

function massegeinfo1(ids){
	$.ajax({
		type : "POST",
		url : "fg/findMassegeInfoById.do",
		data : "ids="+ids,
		dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
		type : "POST",
		cache : false,
		success : function(data) {
			 var result=data.result;
			 
			 $("#massgeinfo").html("");
			 $("#massgeinfo").html(result.content.replace(/\n/g,'<br/>'));
			// $("#myModal4").model();
			 $('#myModal4').on('hide.bs.modal', function () {
				 window.location.href="massegelist.html?status=0";    
			      });
			 $('#myModal4').modal('toggle');
			 
			 }});
	
	
}
function massegeinfo(ids){
	$.ajax({
		type : "POST",
		url : "fg/findMassegeInfoById.do",
		data : "ids="+ids,
		dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
		type : "POST",
		cache : false,
		success : function(data) {
			 var result=data.result;
			 
			 $("#massgeinfo").html("");
			 $("#massgeinfo").html(result.content.replace(/\n/g,'<br/>'));
			// $("#myModal4").model();
			 $('#myModal4').on('hide.bs.modal', function () {
			          window.location.reload();    
			      });
			 $('#myModal4').modal('toggle');
			 
			 }});
	
	
}
function deletem(ids){
	$("#deleteids").val(ids);
	$('#myModal6').modal('toggle');
}
function deletepre()
{
	var ids=$("#deleteids").val();
	 
		 $.ajax({
				type : "POST",
				url : "fg/DeleteMassegeInfoById.do",
				data : "ids="+ids,
				dataType : "json",// 此处只能填写json或text，否则回调函数无法正确执行
				type : "POST",
				cache : false,
				success : function(data) {
					 window.location.reload();	 
					 }});
	  
	
}
function dotable(status){
	if(status==0)
	{
		$("#fwmsxxid").html("未读消息列表");
	}else if(status==1){
		$("#fwmsxxid").html("已读消息列表");
		 
	}else{
		$("#fwmsxxid").html("已读消息列表");
	}
	       htmlstr="";
           htmlstr="<tr><th width='20%'>标题 </th><th>发送时间 </th><th>消息类型</th><th width='15%'>接收人 </th><th>状态</th> <th>操作 </th></tr>";
           $("#tablethread").html(htmlstr);
           InitiateExpandableDataTable.init();
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
                "sAjaxSource": "fg/findMassegeBystatus.do?status="+$("#statusid").val(), //ajax请求地址
                "aoColumns" : [ {
                    "bVisible" : true,
                    "data" : "title",
                    "aTargets" : [0]
                },  {
                    "bVisible" : true,
                    "data" : "times",
                    "aTargets" : [1]
                }, {
                    "bVisible" : true,
                    "data" : "type",
                    "aTargets" : [2]
                } , {
                    "bVisible" : true,
                    "data" : "username",
                    "aTargets" : [3]
                } 
                , {
                    "bVisible" : true,
                    "data" : "status",
                    "aTargets" : [4]
                } , {
                    "bVisible" : true,
                    "data" : "",
                    "aTargets" : [5]
                }  
                
               ],
                "aoColumnDefs": [
                { "bSortable": false, "aTargets": [0] },
				{
				    sDefaultContent : " ",
				    aTargets : [1]
				},{
                    "aTargets": [2],
                    "mRender": function (data, type, full) {
                    	 var str="";
                    	 if(full.type==1)
                    		 {
                    		  str="服务审核通过";
                    		 }
                    	 if(full.type==-1)
                		 {
                		  str="服务审核拒绝";
                		 }
                    	 if(full.type==2)
                		 {
                		  str="应用申请服务通过";
                		 }
                    	 if(full.type==-2)
                		 {
                		  str="应用申请服务拒绝";
                		 }
                    	 if(full.type==4)
                		 {
                		  str="服务评分消息";
                		 }
                    	 if(full.type==3)
                		 {
                		  str="订阅推送消息";
                		 }
                    	 
                         return str;
                    }
                },{
                    "aTargets": [4],
                    "mRender": function (data, type, full) {
                    	if(full.status==0)
                    		{
                    		   return ' 未读　'  ;
                    		}else{
                    			return ' 已读　'  ;
                    		}
                       
                    }
                }
              /*  ,{
                    "aTargets": [5],
                    "mRender": function (data, type, full) {
                    	var content=full.content;
                    		    return   ;
                    		 
                       
                    }
                }*/ ,{
                    "aTargets": [5],
                    "mRender": function (data, type, full) {
                    	        
                    		    return '<div class="btn-group btn-group-xs" role="group"><a  class="btn btn-info btn-xs" onclick=\'massegeinfo(\"'+full.id+'\")\' >详情</a>'+'<a  class="btn btn-danger btn-xs" onclick="deletem(\''+full.id+'\')">删除</a></div>'  ;
                    		 
                       
                    }
                }
                ],
                "aaSorting": [[4, 'desc']],
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

 

