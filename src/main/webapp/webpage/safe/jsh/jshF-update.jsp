<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>购汇管理信息</title>
<t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script type="text/javascript">
  //编写自定义JS代码
  </script>
</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="jshFController.do?doUpdate" tiptype="1"
		beforeSubmit="check(curform,'F');">
		<input id="id" name="id" type="hidden" value="${jshFPage.id }">
		<input id="buscode" name="buscode" type="hidden"
			value="${jshFPage.buscode }">
		<input id="cap" name="cap" type="hidden" value="${jshFPage.cap }">
		<input id="brca" name="brca" type="hidden" value="${jshFPage.brca }">
		<input id="isdel" name="isdel" type="hidden"
			value="${jshFPage.isdel }">
		<input id="isedit" name="isedit" type="hidden"
			value="${jshFPage.isedit }">
		<input id="isexport" name="isexport" type="hidden"
			value="${jshFPage.isexport }">
		<input id="ishandadd" name="ishandadd" type="hidden"
			value="${jshFPage.ishandadd }">
		<input id="isvalidation" name="isvalidation" type="hidden"
			value="${jshFPage.isvalidation }">
		<input id="filename" name="filename" type="hidden"
			value="${jshFPage.filename }">
		<input id="isinsafe" name="isinsafe" type="hidden"
			value="${jshFPage.isinsafe }">
		<input id="tfilename" name="tfilename" type="hidden"
			value="${jshFPage.tfilename }">
		<input id="remark" name="remark" type="hidden"
			value="${jshFPage.remark }">
		<table  cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="left"><label class="Validform_label"> 操作类型:
				</label></td>
				<td class="value"><t:dictSelect id="acctiontype" field="actiontype" type="list"
						typeGroupCode="actiontype" defaultVal="${jshFPage.actiontype}"
						hasLabel="false" title="操作类型" datatype="*"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						修改删除原因</label></td>
				<td class="value"><input id="actiondesc" name="actiondesc"
					type="text"  class="inputxt"
					value="${jshFPage.actiondesc}" > <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 申报号码:
				</label></td>
				<td class="value"><input id="rptno" name="rptno" type="text" readonly="readonly"
					 class="inputxt" value="${jshFPage.rptno}" >
					<span class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						外汇局批件号</label></td>
				<td class="value"><input id="regno" name="regno" type="text"
					 class="inputxt" value="${jshFPage.regno}" >
					<span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 交易编码:
				</label></td>
                <td class="value"><t:dictSelect  id="txcode" field="txcode" type="list"
						dictTable="s_p_transcationcode where dir='1'" dictField="code"
						dictText="text" defaultVal="${jshFPage.txcode}" hasLabel="false"
						title="交易编码" datatype="*"></t:dictSelect> <span class="Validform_checktip"></span>
				</td>
				
				
				<td align="left"><label class="Validform_label"> 结汇用途:
				</label></td>
				<td class="value"><t:dictSelect id="usetype" field="usetype" type="list"
						dictTable="s_p_jshpurpose" dictField="code" dictText="text"
						defaultVal="${jshFPage.usetype}" hasLabel="false" title="结汇用途" datatype="*"></t:dictSelect>
					<span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						结汇详细用途</label></td>
				<td class="value"><input id="usedetail" name="usedetail"
					type="text"  class="inputxt"
					value="${jshFPage.usedetail}" > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 填报人:
				</label></td>
				<td class="value"><input id="crtuser" name="crtuser"
					type="text"  class="inputxt"
					value="${jshFPage.crtuser}"  datatype="*"> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						填报人电话</label></td>
				<td class="value"><input id="inptelc" name="inptelc"
					type="text"  class="inputxt"
					value="${jshFPage.inptelc}"  datatype="*"> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 申报日期:
				</label></td>
				<td class="value"><input id="rptdate" name="rptdate"
					type="text"  class="Wdate inputxt"
					onClick="WdatePicker()"  datatype="*"
					value='<fmt:formatDate value="${jshFPage.rptdate}"  type="date" pattern="yyyy-MM-dd"/>'>
					<span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 营业日期:
				</label></td>
				<td class="value"><input id="importdate" name="importdate"
					type="text"  class="Wdate inputxt"
					onClick="WdatePicker()" datatype="*"
					value='<fmt:formatDate value="${jshFPage.importdate}"  type="date" pattern="yyyy-MM-dd"/>'>
					<span class="Validform_checktip"></span></td>
					<td align="left"><label class="Validform_label"> </label></td>
				<td class="value"></td>
			</tr>
		</table>
	</t:formvalid>
</body>
<script src="webpage/safe/jsh/jshcommon.js"></script>
<script src="webpage/safe/jsh/jshF.js"></script>