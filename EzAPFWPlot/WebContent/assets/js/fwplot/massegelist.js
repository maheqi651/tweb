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
             *   <th> 服务名称 </th>
                                                <th> 应用名称 </th>
                                                <th> 应用描述 </th>
                                                <th> 申请单位 </th>
                                                <th> 联系电话 </th>
                                                <th> 申请时间</th>
                                                <th> 申请人 </th>
                                                <th> 操作</th>
             * Initialize DataTables, with no sorting on the 'details' column
             */没
            var oTable = $('#expandabledatatable').dataTable({
                "sDom": "Tflt<'row DTTTFooter'<'col-sm-6'i><'col-sm-6'p>>",
                "sAjaxSource": "fg/findServiceApply.do?status=0", //ajax请求地址
                "aoColumns" : [ {
                    "bVisible" : true,
                    "data" : "name",
                    "aTargets" : [0]
                },  {
                    "bVisible" : true,
                    "data" : "appname",
                    "aTargets" : [1]
                }, {
                    "bVisible" : true,
                    "data" : "description",
                    "aTargets" : [2]
                } , {
                    "bVisible" : true,
                    "data" : "phone",
                    "aTargets" : [3]
                } 
                , {
                    "bVisible" : true,
                    "data" : "applytime",
                    "aTargets" : [4]
                } 
                , {
                    "bVisible" : true,
                    "data" : "applyuser",
                    "aTargets" : [5]
                } 
               ],
                "aoColumnDefs": [
                { "bSortable": false, "aTargets": [0] },
				{
				    sDefaultContent : " ",
				    aTargets : [1]
				} ,{
                    "aTargets": [6],
                    "mRender": function (data, type, full) {
                        return '<a data-toggle="modal"     class="btn btn-success btn-xs" onclick="accept2(\''+full.aid+'\')"><i class="icon-edit icon-white"></i>接受申请</a>'+'&nbsp;'+'<a    class="btn btn-danger btn-xs"  onclick="noaccept2(\''+full.aid+'\')"><i class="icon-wrench icon-white" ></i>拒绝申请</a>' +'&nbsp;'+'<a    class="btn btn-primary btn-xs"  onclick="appli(\''+full.name+'\',\''+full.description+'\',\''+full.servicecode+'\',\''+full.aid+'\')"><i class="icon-wrench icon-white" ></i>查看详情</a>'  ;
                    }
                }],
                "aaSorting": [[4, 'desc']],
                "aLengthMenu": [
                   [5,10, 15, 20],
                   [5, 10,15, 20]
                ],
                "iDisplayLength":5,
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
                    "sZeroRecords": "抱歉， 没有找到",
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

