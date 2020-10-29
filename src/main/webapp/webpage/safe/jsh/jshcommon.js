$(function() {

	// 设置下拉选择框
	$("#actiontype").select2({
		minimumResultsForSearch : Infinity,
		theme : "bootstrap"

	});
	$("#custype").select2({
		minimumResultsForSearch : Infinity,
		theme : "bootstrap",
		placeholder : {
			id : "-1",
			placeholder : "Select an option",
			allowClear : true
		}

	}).on(
			"select2:select",
			function(e) {// 客户证件号码联动设置(原来写on.("change")时focus()失效)
				var cust = $("#custype").val();
				if (cust == "C") {
					$("#idcode").attr("disabled", "disabled").attr("style",
							"background:#EEEEEE").val("").attr("ignore",
							"ignore");
					$("#custcod").attr("disabled", false).attr("style",
							"background:white").removeAttr("ignore").focus();
				} else {
					$("#custcod").attr("disabled", "disabled").attr("style",
							"background:#EEEEEE").val("").attr("ignore",
							"ignore");
					$("#idcode").attr("disabled", false).attr("style",
							"background:none").removeAttr("ignore").focus();
				}
			});

	$("#fcyccy").select2({
		theme : "bootstrap"

	});
	$("#lcyccy").select2({
		theme : "bootstrap"

	});
	$("#usetype").select2({
		theme : "bootstrap"

	});
	$("#txcode").select2({
		theme : "bootstrap"

	});
	// 设置客户证件号码的默认值
	var cust = $("#custype").val();
	if (cust == "C") {
		$("#idcode").attr("disabled", "disabled").attr("style",
				"background:#EEEEEE").val("").attr("ignore", "ignore");
		$("#custcod").attr("disabled", false).attr("style", "background:white");
	} else {
		$("#custcod").attr("disabled", "disabled").attr("style",
				"background:#EEEEEE").val("").attr("ignore", "ignore");
		$("#idcode").attr("disabled", false).attr("style", "background:none");
	}
	// 如果没有进入SAFE的记录，Actiontype不可以修改
	if ($("#isinsafe").val() != "1") {
		$("#actiontype").prop("disabled", true);
		$("#actiondesc").attr("disabled", "disabled").attr("style",
				"background:#EEEEEE").val("").attr("ignore", "ignore");
	} else {
		$("#actiontype").val(null).trigger("change");
		$("#actiontype").find("option[value='A']").attr("disabled", "disabled");

		// $("#actiontype").find("option[value='C']").attr("selected","selected");
		// alert($("#s2id_actiontype").find("span.select2-chosen").text());
		// $("#s2id_actiontype").find("span.select2-chosen").text("修改");

		// alert($("#s2id_actiontype").find("span.select2-chosen").text());

	}

	//
});

function check(curform, type) {

	if($("#isvalidation").val()=="1" && $("#isinsafe").val()!="1"){
		$.Showmsg("已审核过记录不可直接编辑");
		return false;
	}

	if ($("#actiontype").val() != "A" && $("#actiondesc").val() == "") {
		$.Showmsg("操作类型为修改/删除时，修改删除原因不能为空");
		$("#actiondesc").focus();
		return false;
	}
	if (type == "F") {
		var usetype = $("#usetype").val();
		if ((usetype == "005" || usetype == "006" || usetype == "099")
				&& $("#usedetail").val() == "") {
			$.Showmsg("如果结汇用途选择“005”、“006”或“099”，则应填列详细用途。");
			$("#usedetail").focus();
			return false;
		}
	}
	if (type == "F" || type == "G") {
		var cap = $("#cap").val();
		if (cap == "true") {
			var arr = [ "923010", "923020", "923090", "924010", "924020", "924030",
						"924090", "929020" ];
				var txcode = $("#txcode").val();
				var fc = txcode.substring(0, 1);
				var regno = $("#regno").val();
			if (fc == "5" || fc == "6" || fc == "7" || fc == "8"
					|| $.inArray(txcode, arr) != -1) {
				if (regno == "" || regno == "N/A") {
					$.Showmsg("资本项目项下交易（涉外收支交易编码以“5”、“6”、“7”、“8”和部分“9”开头）"
							+ "的“外汇局批件号/备案表号/业务编号”为必输项。");
					$("#regno").val("").focus();
					return false;

				}
			}
		}
	}

	// 提交时修改Actiontype的disable属性，避免acitontype：disable时值丢失的情况
	$("#actiontype").prop("disabled", false);
	$("#isedit").val('1');
	$("#isvalidation").val('0');
	if ($("#isexport").val() == "" || $("#isexport").val() == null) {
		$("#isexport").val('0');
	}
	if ($("#isinsafe").val() == "" || $("#isinsafe").val() == null) {
		$("#isinsafe").val('0');
	}
	$("#remark").val("");
}