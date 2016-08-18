$(document).ready(function() {
	basepath = getBaseURL();
	document.getElementById("step2").style.display = "none";
	document.getElementById("step3Trace").style.display = "none";
	document.getElementById("step3Register").style.display = "none";
	document.getElementById("step4Trace").style.display = "none";
	document.getElementById("step4Register").style.display = "none";
	document.getElementById("step3Kv").style.display = "none";
	document.getElementById("step4Kv").style.display = "none";
	$("#kvRowkeyDateTypeSwitch").attr("checked",false);
	// document.getElementById("tableNameEmpty").style.display = "none";
});

$("#step1_submit").click(function() {
	if ($("#HBaseTableName")[0].value == "") {
		shake("step1");
		return;
	}
	$("#step1_submit").attr({
		disabled : "disabled"
	});
	$.ajax({
		type : "POST",
		url : basepath + "/CheckHBaseTableExist",
		data : "tableName=" + $("#HBaseTableName").val(),
		dataType : "text",// 此处只能填写json或text，否则回调函数无法正确执行
		success : function(restext) {
			if (restext == "failed") {
				alert("HBase连接错误！请前往HBase实例管理页面检查");
			} else if (restext == "exist") {
				if (confirm("该HBase表已经存在，按确认为其添加新映射")) {
					$("#step1").animate({
						width : "toggle"
					});
					$("#mainIntro").animate({
						width : "toggle"
					});
					$("#step2").animate({
						width : "toggle"
					});
				}
			} else {
				$("#step1").animate({
					width : "toggle"
				});
				$("#mainIntro").animate({
					width : "toggle"
				});
				$("#step2").animate({
					width : "toggle"
				});
			}

			$("#step1_submit").removeAttr("disabled");
		}
	});

});

$("#back2Step1").click(function() {
	$("#step1").animate({
		width : "toggle"
	});
	$("#mainIntro").animate({
		width : "toggle"
	});
	$("#step2").animate({
		width : "toggle"
	});
});

// "username="+oracleUsername+"&password="+oraclePassword+"&url="+url+"&type=normal"

$("#step2_submit").click(
		function() {
			$("#checkOracleConnResult").text("检测密码中，请稍等");
			$("#step1_submit").attr({
				disabled : "disabled"
			});

			$.ajax({
				type : "POST",
				url : basepath + "/TestOracleConn",
				data : "username=" + $("#OracleUsername").val() + "&password="
						+ $("#OraclePassword").val() + "&url="
						+ $("#OracleURL").val() + "&type=normal",
				dataType : "text",// 此处只能填写json或text，否则回调函数无法正确执行
				success : function(restext) {
					if (restext == "true") {
						$("#checkOracleConnResult").text("");

						initStep3();
						// $("#step3").animate({
						// width : "toggle"
						// });
					} else {
						shake("step2");
						$("#checkOracleConnResult").text("密码错误，请重试");
					}
					$("#step1_submit").removeAttr("disabled");
				}
			});
		});

function initStep3() {
	$.ajax({
		type : "POST",
		url : basepath + "/GetOracleTableList",
		data : "oracleUsername=" + $("#OracleUsername").val()
				+ "&oraclePassword=" + $("#OraclePassword").val()
				+ "&oracleUrl=" + $("#OracleURL").val() + "&type=normal",
		dataType : "text",// 此处只能填写json或text，否则回调函数无法正确执行
		success : function(restext) {
			var oracleTableList = restext.split(",");
			// var choices="";
			tableChoices = "";
			for ( var item in oracleTableList) {
				tableChoices += "<option>" + oracleTableList[item]
						+ "</option>";
			}
			var val = $('input:radio[name="dataTypeRadio"]:checked').val();
			if (val == "optionsTrace") {
				$("#traceTableSelect").html(tableChoices);
				$("#step2").animate({
					width : "toggle"
				});
				$("#step3Trace").animate({
					width : "toggle"
				});
			}

			else if (val == "optionsRegister") {
				$("#registerTableSelect").html(tableChoices);
				$("#regLocTableSelect").html(tableChoices);

				$("#step2").animate({
					width : "toggle"
				});
				$("#step3Register").animate({
					width : "toggle"
				});
			}
			
			else if (val == "optionsKv") {
				$("#kvTableSelect").html(tableChoices);
				$("#step2").animate({
					width : "toggle"
				});
				$("#step3Kv").animate({
					width : "toggle"
				});
			}
		}
	});
}

$("#traceBack2Step2").click(function() {
	$("#step3Trace").animate({
		width : "toggle"
	});
	$("#step2").animate({
		width : "toggle"
	});
});

$("#kvBack2Step2").click(function() {
	$("#step3Kv").animate({
		width : "toggle"
	});
	$("#step2").animate({
		width : "toggle"
	});
});

$("#step3Trace_submit").click(
		function() {
			var tableNames = $('#traceTableSelect').val();// 使用val()即可获得selected的选中值
			if (tableNames == null) {
				warning("请至少选择一个轨迹数据源表！");
			} else {
				$.ajax({
					type : "POST",
					url : basepath + "/GetOracleColumns",
					data : "tableName=" + tableNames.toString()
							+ "&type=normal",
					dataType : "text",// 此处只能填写json或text，否则回调函数无法正确执行
					success : function(restext) {
						if (restext.indexOf("Structure false!") >= 0) {
							warning("您所选择的表结构不完全相同！");
						} else {
							var choices = "";
							var columns = restext.split(",");
							for ( var item in columns) {
								choices += "<option>"
										+ columns[item].split(":")[0]
										+ "</option>";
							}
							$(".traceColumns").html(choices);
							$("#step3Trace").animate({
								width : "toggle"
							});
							$("#step4Trace").animate({
								width : "toggle"
							});
						}
					}
				});
			}
		});
var columnNameTypeMapping =new Object();
$("#step3Kv_submit").click(
		function() {
			var tableNames = $('#kvTableSelect').val();// 使用val()即可获得selected的选中值
			if (tableNames == null) {
				warning("请至少选择一个轨迹数据源表！");
			} else {
				$.ajax({
					type : "POST",
					url : basepath + "/GetOracleColumns",
					data : "tableName=" + tableNames.toString()
							+ "&type=normal",
					dataType : "text",// 此处只能填写json或text，否则回调函数无法正确执行
					success : function(restext) {
						if (restext.indexOf("Structure false!") >= 0) {
							warning("您所选择的表结构不完全相同！");
						} else {
							var choices = "";
							var columns = restext.split(",");
							for ( var item in columns) {
								choices += "<option>"
										+ columns[item].split(":")[0]
										+ "</option>";
								columnNameTypeMapping[columns[item].split(":")[0]]=
									columns[item].split(":")[1];
							}
							$(".kvColumns").html(choices);
							$("#step3Kv").animate({
								width : "toggle"
							});
							$("#step4Kv").animate({
								width : "toggle"
							});
						}
					}
				});
			}
		});

$("#traceBack2Step3").click(function() {
	$("#step4Trace").animate({
		width : "toggle"
	});
	$("#step3Trace").animate({
		width : "toggle"
	});
});

$("#step4Trace_submit").click(
		function() {
			var param = "dataType=traceData&createType=oracle&traceDeviceID="
					+ $("#traceIDColumnSelect").val() + "&traceTime="
					+ $("#traceTimeColumnSelect").val() + "&traceX="
					+ $("#traceXColumnSelect").val() + "&traceY="
					+ $("#traceYColumnSelect").val();// dataType=traceData,registerData
			$.ajax({
				type : "POST",
				url : basepath + "/CreateHBaseTable",
				data : param,
				dataType : "text",// 此处只能填写json或text，否则回调函数无法正确执行
				success : function(restext) {
					if (restext == 'success') {
						congrat("创建成功");
						$("#step4Trace").animate({
							width : "toggle"
						});
						$("#step1").animate({
							width : "toggle"
						});
						$("#mainIntro").animate({
							width : "toggle"
						});
					} else {
						alert("创建失败，请检查连接");
					}
				}
			});

		});
function shake(o) {
	var $panel = $("#" + o);
	box_left = ($(window).width() - $panel.width()) / 1000;
	$panel.css({
		'left' : box_left,
		'position' : 'absolute'
	});
	for (var i = 1; 4 >= i; i++) {
		$panel.animate({
			left : box_left - (40 - 10 * i)
		}, 40);
		$panel.animate({
			left : box_left + 2 * (40 - 10 * i)
		}, 40);
	}
}

function warning(text) {
	$("#warningModalLabel").text("请注意");
	$("#warningModalText").text(text);
	$("#warningModal").modal('toggle');
}

function congrat(text) {
	$("#warningModalLabel").text("来自服务器端的提示");
	$("#warningModalText").text(text);
	$("#warningModal").modal('toggle');
}

$("#newLinkRegLocBtn").click(function() {
	// alert("aaa");
	$("#regLocLinkCG").animate({
		width : "show"
	});
	$("#regLocSelectCG").animate({
		width : "hide"
	});

});

$("#existLinkRegLocBtn").click(function() {
	$("#regLocTableSelect").html(tableChoices);
	$("#regLocSelectCG").animate({
		width : "show"
	});
	$("#regLocLinkCG").animate({
		width : "hide"
	});
});

$("#noLocBtn").click(function() {
	$("#regLocSelectCG").animate({
		width : "hide"
	});
	$("#regLocLinkCG").animate({
		width : "hide"
	});
});
$("#checkRegLocLink").click(
		function() {
			$("#checkRegLocOracleConnResult").text("请稍等");
			$("#checkRegLocLink").attr({
				disabled : "disabled"
			});
			$.ajax({
				type : "POST",
				url : basepath + "/TestOracleConn",
				data : "username=" + $("#regLocOracleUsername").val()
						+ "&password=" + $("#regLocOraclePassword").val()
						+ "&url=" + $("#regLocOracleURL").val()
						+ "&type=RegLoc",
				dataType : "text",// 此处只能填写json或text，否则回调函数无法正确执行
				success : function(restext) {
					if (restext == "true") {

						$("#checkRegLocOracleConnResult").text("");
						writeRegLocOracleList();
						$("#regLocSelectCG").animate({
							width : "show"
						});
						$("#regLocLinkCG").animate({
							width : "hide"
						});
					} else {
						shake("step3Register");
						$("#checkRegLocOracleConnResult").text("密码错误，请重试");
					}
					$("#checkRegLocLink").removeAttr("disabled");
				}
			});
		});

function writeRegLocOracleList() {
	$("#regLocTableSelect").html("");

	$.ajax({
		type : "POST",
		url : basepath + "/GetOracleTableList",
		data : "regLocUsername=" + $("#regLocOracleUsername").val()
				+ "&regLocPassword=" + $("#regLocOraclePassword").val()
				+ "&regLocUrl=" + $("#regLocOracleURL").val() + "&type=RegLoc",
		dataType : "text",// 此处只能填写json或text，否则回调函数无法正确执行
		success : function(restext) {
			var oracleTableList = restext.split(",");
			regLocTableChoices = "";
			for ( var item in oracleTableList) {
				regLocTableChoices += "<option>" + oracleTableList[item]
						+ "</option>";
			}
			$("#regLocTableSelect").html(regLocTableChoices);
		}
	});
}

$("#registerBack2Step2").click(function() {
	$("#step3Register").animate({
		width : "toggle"
	});
	$("#step2").animate({
		width : "toggle"
	});
});

$("#step3Register_submit").click(
		function() {
			var tableNames = $('#registerTableSelect').val();
			var regLocTableNames = $('#regLocTableSelect').val();
			var regLocType = $('#regLocTableType > .active').val();
			if (tableNames == null) {
				warning("请至少选择一个登记数据源表！");
			} else if (regLocTableNames == null && regLocType != "noLoc") {
				warning("请指定一个地理位置信息源表，或者将表来源设定为‘没有地理位置信息表’！");
			} else {
				$.ajax({
					type : "POST",
					url : basepath + "/GetOracleColumns",
					async : false,
					data : "tableName=" + tableNames.toString()
							+ "&type=normal",
					dataType : "text",// 此处只能填写json或text，否则回调函数无法正确执行
					success : function(restext) {
						if (restext.indexOf("Structure false!") >= 0) {
							warning("您所选择的登记数据源表结构不完全相同！");
						}

						else {
							var choices = "";
							var columns = restext.split(",");
							for ( var item in columns) {
								choices += "<option>"
										+ columns[item].split(":")[0]
										+ "</option>";
							}
							$(".registerColumns").html(choices);
							if (regLocType == "noLoc") {
								$("#regLocColumn").css("display", "none");
							} else {
								$.ajax({
									type : "POST",
									url : basepath + "/GetOracleColumns",
									async : false,
									data : "tableName=" + regLocTableNames.toString()
											+ "&type=RegLoc&regLocType=" + regLocType,
									dataType : "text",// 此处只能填写json或text，否则回调函数无法正确执行
									success : function(restext) {
										var regLocChoices = "";
										var regLocColumns = restext.split(",");
										for ( var item in regLocColumns) {
											regLocChoices += "<option>"
													+ regLocColumns[item].split(":")[0]
													+ "</option>";
										}
										$(".regLocColumns").html(regLocChoices);
										$("#regLocColumn").css("display", "block");
									}
								});
							}
							$("#step3Register").animate({
								width : "toggle"
							});
							$("#step4Register").animate({
								width : "toggle"
							});
						}
					}
				});


			}
		});

$("#registerBack2Step3").click(function() {
	$("#step4Register").animate({
		width : "toggle"
	});
	$("#step3Register").animate({
		width : "toggle"
	});
});

$("#addRegisteeIDColumn").click(function() {
	$("#registeeColumnsDiv").append("<div class='controls'><select class='registerColumns registees'\
				style='width: auto'></select>\
				<img class='removeRegisteeIDColumn' onclick='removeCurRegisteeIDColumn(this)' src='./img/minus.png' height='25px' width='25px'/></div>");
	var selects=$("#registeeColumnsDiv select").first().html();
	$("#registeeColumnsDiv select").last().html(selects);
	
});

$("#addKvRowkeyColumn").click(function() {
	$("#kvRowkeyColumnsDiv").append("<div class='controls'><select class='kvColumns kvRowkeys'\
				style='width: auto'></select>\
				<img class='removeKvRowkeyColumn' onclick='removeCurKvRowkeyColumn(this)' src='./img/minus.png' height='25px' width='25px'/></div>");
	var selects=$("#kvRowkeyColumnsDiv select").first().html();
	$("#kvRowkeyColumnsDiv select").last().html(selects);
	
});

function removeCurRegisteeIDColumn(index) {
	var cdiv = index.parentNode;
	 index.parentNode.parentNode.removeChild(cdiv);
}

function removeCurKvRowkeyColumn(index) {
	var cdiv = index.parentNode;
	 index.parentNode.parentNode.removeChild(cdiv);
}

$("#step4Register_submit").click(
		function() {
			var regLocType = $('#regLocTableType > .active').val();
			var registerObjectString="";
			$(".registees").each(function(index,element){
				registerObjectString += document.getElementById("registeeColumnSelect")[element.selectedIndex].text;
				registerObjectString += ",";
			});
			registerObjectString= registerObjectString.substring(0, registerObjectString.lastIndexOf(","));
			var param = "dataType=registerData&createType=oracle&registerID="
					+ $("#registerIDColumnSelect").val() + "&registerTime="
					+ $("#registerTimeColumnSelect").val() + "&regLocType="
					+ regLocType + "&registerObjectString="
					+ registerObjectString;
//					+ $("#registeeColumnSelect").val();
			if (regLocType != 'noLoc') {
				param = param + "&registerX=" + $("#regLocXColumnSelect").val()
						+ "&registerY=" + $("#regLocYColumnSelect").val()
						+ "&registerRegLocID="
						+ $("#regLocIDColumnSelect").val();
			}

			$.ajax({
				type : "POST",
				url : basepath + "/CreateHBaseTable",
				data : param,
				dataType : "text",// 此处只能填写json或text，否则回调函数无法正确执行
				success : function(restext) {

					if (restext == 'success') {
						congrat("创建成功");
						$("#step4Register").animate({
							width : "toggle"
						});
						$("#step1").animate({
							width : "toggle"
						});
						$("#mainIntro").animate({
							width : "toggle"
						});
					}
					else{
						alert("创建失败，请检查网络设置！");
					}
				}
			});
		});

$("#kvRowkeyDateTypeSwitch").change(
		function() {
            if (!$("#kvRowkeyDateTypeSwitch:checked").val()) {
            	$("#kvRowkeyDateTypeSelect").removeAttr('disabled');
                $("#kvRowkeyDateTypeInput").attr('disabled','disabled');
            }else if($("#kvRowkeyDateTypeSwitch:checked").val()){
            	$("#kvRowkeyDateTypeSelect").attr('disabled','disabled');
                $("#kvRowkeyDateTypeInput").removeAttr('disabled');
            }
		});

$("#kvTimeStampTypeSelectDiv :radio").change(
		function() {
            if (!($("#kvTimeStampTypeSelectDiv :checked").val()=="column")) {
                $("#kvTimestampColumnSelect").attr('disabled','disabled');
            }else if($("#kvTimeStampTypeSelectDiv :checked").val()=="column"){
                $("#kvTimestampColumnSelect").removeAttr('disabled');
            }
		});

$("#kvBack2Step3").click(function() {
	$("#step4Kv").animate({
		width : "toggle"
	});
	$("#step3Kv").animate({
		width : "toggle"
	});
});

$("#step4Kv_submit").click(function() {
	var rowkeyColumns="";
	$(".kvRowkeys").each(function(index,element){
		var curKey=document.getElementById("kvRowkeyColumnSelect")[element.selectedIndex].text;
		rowkeyColumns +=curKey; 
		rowkeyColumns += "::"+columnNameTypeMapping[curKey]+",";
	});
	var param = "dataType=kvData&createType=oracle&timestampType="
		+ $("#kvTimeStampTypeSelectDiv :checked").val() + "&rowkeyColumn="
		+ rowkeyColumns+"&dateFormat="
		+ $("#kvRowkeyDateTypeSelect").val();
	if($("#kvTimeStampTypeSelectDiv :checked").val()=="column"){
		param=param+"&kvTime="+$("#kvTimestampColumnSelect").val();
	}
	$.ajax({
		type : "POST",
		url : basepath + "/CreateHBaseTable",
		data : param,
		dataType : "text",// 此处只能填写json或text，否则回调函数无法正确执行
		success : function(restext) {

			if (restext == 'success') {
				congrat("创建成功");
				$("#step4Kv").animate({
					width : "toggle"
				});
				$("#step1").animate({
					width : "toggle"
				});
				$("#mainIntro").animate({
					width : "toggle"
				});
			}
			else{
				alert("创建失败，请检查网络设置！");
			}
		}
	});
});