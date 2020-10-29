<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>bop_n</title>
<t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script type="text/javascript">
  //编写自定义JS代码
  </script>
</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="bopNController.do?doUpdate" tiptype="1"
		beforeSubmit="check(curform,'N');">
		<input id="id" name="id" type="hidden" value="${bopNPage.id }">
		<input id="buscode" name="buscode" type="hidden"
			value="${bopNPage.buscode }">
		<input id="cap" name="cap" type="hidden" value="${bopNPage.cap }">
		<input id="brca" name="brca" type="hidden" value="${bopNPage.brca }">
		<input id="importdate" name="importdate" type="hidden"
			value="${bopNPage.importdate }">
		<input id="isdel" name="isdel" type="hidden"
			value="${bopNPage.isdel }">
		<input id="isedit" name="isedit" type="hidden"
			value="${bopNPage.isedit }">
		<input id="isexport" name="isexport" type="hidden"
			value="${bopNPage.isexport }">
		<input id="ishandadd" name="ishandadd" type="hidden"
			value="${bopNPage.ishandadd }">
		<input id="isvalidation" name="isvalidation" type="hidden"
			value="${bopNPage.isvalidation }">
		<input id="filename" name="filename" type="hidden"
			value="${bopNPage.filename }">
		<input id="isinsafe" name="isinsafe" type="hidden"
			value="${bopNPage.isinsafe }">
		<input id="tfilename" name="tfilename" type="hidden"
			value="${bopNPage.tfilename }">
		<input id="remark" name="remark" type="hidden"
			value="${bopNPage.remark }">
		<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="left"><label class="Validform_label"> 操作类型
				</label></td>
				<td class="value"><t:dictSelect field="actiontype" type="list"
						typeGroupCode="actiontype" defaultVal="${bopNPage.actiontype}"
						hasLabel="false" title="操作类型"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						修改删除原因</label></td>
				<td class="value"><input id="actiondesc" name="actiondesc"
					type="text"  class="inputxt"
					value='${bopNPage.actiondesc}" > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 申报号码
				</label></td>
				<td class="value"><input id="rptno" name="rptno" type="text"
					 class="inputxt" datatype="*"
					value='${bopNPage.rptno}" > <span class="Validform_checktip"></span>
				</td>
			<tr>
				<td align="left"><label class="Validform_label"> 合同号
				</label></td>
				<td class="value"><input id="contrno" name="contrno"
					type="text"  class="inputxt" datatype="*"
					value='${bopNPage.contrno}" > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 发票号
				</label></td>
				<td class="value"><input id="invoino" name="invoino"
					type="text"  class="inputxt" datatype="*"
					value='${bopNPage.invoino}" > <span
					class="Validform_checktip"></span></td>
				<td colspan="2" style="background:white;"><span id="showremark"
					style="color:red;" type="hidden"> </span></td>
			<tr>
				<td align="left"><label class="Validform_label"> 申报人
				</label></td>
				<td class="value"><input id="crtuser" name="crtuser"
					type="text"  class="inputxt" datatype="*"
					value='${bopNPage.crtuser}" > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						申报人电话</label></td>
				<td class="value"><input id="inptelc" name="inptelc"
					type="text"  class="inputxt" datatype="*"
					value='${bopNPage.inptelc}" > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 申报日期
				</label></td>
				<td class="value"><input id="rptdate" name="rptdate"
					type="text"  class="Wdate inputxt"
					onClick="WdatePicker()" datatype="*"
					value='<fmt:formatDate value="${bopNPage.rptdate}"  type="date" pattern="yyyy-MM-dd"/>'>
					<span class="Validform_checktip"></span></td>
			</tr>
		</table>
	</t:formvalid>
</body>
<script src="webpage/safe/bop/bopcommon.js"></script>
<script src="webpage/safe/bop/bopN.js"></script>