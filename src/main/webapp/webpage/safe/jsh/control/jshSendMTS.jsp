<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<link rel="stylesheet" href="plug-in/easyui/themes/metro/main.css"
	type="text/css"></link>
<script type="text/javascript"
	src="plug-in/easyui/jquery.easyui.min.1.4.4.js"></script>
<t:formvalid formid="formobj" dialog="false" layout="div" btnsub="btn"
	action="jshController.do?exportReport">
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
					onclick="exportReport();">上传数据至MTS</a>
			</div>
		</div>

	</fieldset>

</t:formvalid>
<script type="text/javascript">
 $(function(){
		$("#loading").fadeOut(100);
 		//给时间控件加上样式
 			$("#dealdate").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导出
function exportReport() {
	$('#loading').fadeIn(1000, function () { });
	var d=$("#dealdate").val();
	if (d == ""||d== undefined) {
		$('#loading').fadeOut(100);
		tip("请选择营业日期");
		return false;
	}
	

	doSubmitNunTable("jshController.do?exportReport&date="+d,"formobj");
	$('#loading').fadeOut(100);
}

$("#dealdate").focus( function() {
	//tip($("#dealdate").val());
	var d=$("#dealdate").val();
	if (d == ""||d== undefined) {
		$('#loading').fadeOut(100);
		tip("请选择营业日期");
		return false;
	}
	doSubmitNunTable("jshController.do?dealDateCheck&date="+d,"formobj");
});
 </script>