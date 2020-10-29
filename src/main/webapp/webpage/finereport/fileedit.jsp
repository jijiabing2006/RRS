<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>FR模板</title>
<t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script type="text/javascript">
	//编写自定义JS代码
</script>
</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="frController.do?doUpdate" tiptype="1">
		<input id="id" name="id" type="hidden"
			value="${fRReportTemplatePage.id }">
		<input id="createBy" name="createBy" type="hidden"
			value="${fRReportTemplatePage.createBy}">
		<input id="updateBy" name="updateBy" type="hidden"
			value="${fRReportTemplatePage.updateBy}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="right"><label class="Validform_label"> 模板名称:
				</label></td>
				<td class="value"><input id="cptname" name="cptname"
					type="text" class="inputxt" datatype="*" readonly="readonly"
					value='${fRReportTemplatePage.cptname}'> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">cptname</label></td>
				<td align="right"><label class="Validform_label"> <label
						class="Validform_label" style="width: 200px"> 所属机构 </label></td>
				<td class="value"><t:dictSelect field="regorg" type="list"
						typeGroupCode="regorg" defaultVal='${fRReportTemplatePage.regorg}' hasLabel="false"
						title="所属机构" datatype="*"></t:dictSelect></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 创建日期:
				</label></td>
				<td class="value"><input id="createDate" name="createDate"
					type="text" class="inputxt" readonly="readonly"
					value='<fmt:formatDate value='${fRReportTemplatePage.createDate}' type="date" pattern="yyyy-MM-dd"/>'>
					<span class="Validform_checktip"></span> <label
					class="Validform_label" style="display: none;">createDate</label></td>
				<td align="right"><label class="Validform_label"> 创建人:
				</label></td>
				<td class="value"><input id="createName" name="createName"
					type="text" class="inputxt" readonly="readonly"
					value='${fRReportTemplatePage.createName}'> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">createName</label></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label">
						最后变动日期: </label></td>
				<td class="value"><input id="updateDate" name="updateDate"
					type="text" class=" inputxt" readonly="readonly"
					value='<fmt:formatDate value='${fRReportTemplatePage.updateDate}' type="date" pattern="yyyy-MM-dd"/>'>
					<span class="Validform_checktip"></span> <label
					class="Validform_label" style="display: none;">updateDate</label></td>
				<td align="right"><label class="Validform_label"> 修改人:
				</label></td>
				<td class="value"><input id="updateName" name="updateName"
					type="text" class="inputxt" readonly="readonly"
					value='${fRReportTemplatePage.updateName}'> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">updateName</label></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 扩展名:
				</label></td>
				<td class="value"><input id="extend" name="extend" type="text"
					class="inputxt" readonly="readonly"
					value='${fRReportTemplatePage.extend}'> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">extend</label></td>
						<td align="right"><label class="Validform_label"> </label></td>
							<td class="value"/>
			</tr>
		</table>
	</t:formvalid>

	<Script>
		$(function() {

			$("#regorg").select2({
				minimumResultsForSearch : Infinity,
				theme : "bootstrap"
			});

			var regorg = $("#regorg").val();
			if (regorg == "inner") {
				$("#regorg").attr("disabled", "disabled").attr("style",
						"background:#EEEEEE").val("").attr("ignore", "ignore");
				$("#showDetails").attr("disabled", "disabled").attr("style",
			} 
			$("#cptname").attr("style", "background:#EEEEEE");
			$("#createDate").attr("style", "background:#EEEEEE");
			$("#createName").attr("style", "background:#EEEEEE");
			$("#updateDate").attr("style", "background:#EEEEEE");
			$("#updateName").attr("style", "background:#EEEEEE");
			$("#extend").attr("style", "background:#EEEEEE");
		});
	</Script>

</body>