<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<link rel="stylesheet" href="plug-in/easyui/themes/metro/main.css"
	type="text/css"></link>
<script type="text/javascript"
	src="plug-in/easyui/jquery.easyui.min.1.4.4.js"></script>
<t:formvalid formid="formobj" dialog="false" layout="div" btnsub="btn"
	action="bopController.do?exportReport">
	<input type="hidden" id="id" />
	<fieldset class="step">
		<div class="form">
			<div class="text_success">
				<label class="Validform_label"> 营业日期： </label> <input
					name="dealdate" type="text" datatype="*" required="required"
					id="dealdate"> <span class="Validform_checktip"></span>
			</div>

			<div class="button-row">
				<a href="#" class="button"
					style="background:url('plug-in/easyui/themes/icons/share.png') no-repeat; background-position:5px;text-indent:10px;"
					onclick="exportReport();">上传数据至MTS</a> <a href="#" class="button"
					style="background:url('plug-in/easyui/themes/icons/document_empty_16px.png') no-repeat; background-position:5px;text-indent:10px;"
					onclick="exportBlankReport();">上传空报文</a>
			</div>
		</div>

	</fieldset>

</t:formvalid>
<script type="text/javascript">
	$(function() {
		//给时间控件加上样式
		$("#dealdate").attr("class", "Wdate").click(function() {
			WdatePicker({
				dateFmt : 'yyyy-MM-dd'
			});
		});

	});

	//导出
	function exportReport() {
		var d = $("#dealdate").val();
		if (d == "" || d == undefined) {
			tip("请选择营业日期");
			return false;
		}

		doSubmitNunTable("bopController.do?exportReport&date=" + d, "formobj");
	}
	//导出空报文
	function exportBlankReport() {
		var d = $("#dealdate").val();
		if (d == "" || d == undefined) {
			tip("请选择营业日期");
			return false;
		}
		$.dialog.confirm(
				'<strong>要导出' + d + '的空报文吗?</strong>',
				function() {
					doSubmitNunTable("bopController.do?exportBlankReport&date="
							+ d, "formobj");
				}, function() {
				}).zindex();

	}
	
	$("#dealdate").focus( function() {
		//tip($("#dealdate").val());
		var d=$("#dealdate").val();
		if (d == ""||d== undefined) {
			$('#loading').fadeOut(100);
			tip("请选择营业日期");
			return false;
		}
		doSubmitNunTable("bopController.do?dealDateCheck&date="+d,"formobj");
	});
</script>