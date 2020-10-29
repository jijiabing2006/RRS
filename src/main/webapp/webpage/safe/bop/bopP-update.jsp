<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>bop_p</title>
<t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script type="text/javascript">
  //编写自定义JS代码
  </script>
</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="bopPController.do?doUpdate" tiptype="1"
		beforeSubmit="check(curform,'P');">
		<input id="id" name="id" type="hidden" value="${bopPPage.id }">
		<input id="buscode" name="buscode" type="hidden"
			value="${bopPPage.buscode }">
		<input id="cap" name="cap" type="hidden" value="${bopPPage.cap }">
		<input id="brca" name="brca" type="hidden" value="${bopPPage.brca }">
		<input id="importdate" name="importdate" type="hidden"
			value="${bopPPage.importdate }">
		<input id="isdel" name="isdel" type="hidden"
			value="${bopPPage.isdel }">
		<input id="isedit" name="isedit" type="hidden"
			value="${bopPPage.isedit }">
		<input id="isexport" name="isexport" type="hidden"
			value="${bopPPage.isexport }">
		<input id="ishandadd" name="ishandadd" type="hidden"
			value="${bopPPage.ishandadd }">
		<input id="isvalidation" name="isvalidation" type="hidden"
			value="${bopPPage.isvalidation }">
		<input id="filename" name="filename" type="hidden"
			value="${bopPPage.filename }">
		<input id="isinsafe" name="isinsafe" type="hidden"
			value="${bopPPage.isinsafe }">
		<input id="tfilename" name="tfilename" type="hidden"
			value="${bopPPage.tfilename }">
		<input id="remark" name="remark" type="hidden"
			value="${bopPPage.remark }">
		<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="left"><label class="Validform_label"> 操作类型
				</label></td>
				<td class="value"><t:dictSelect field="actiontype" type="list"
						typeGroupCode="actiontype" defaultVal="${bopPPage.actiontype}"
						hasLabel="false" title="操作类型"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						修改删除原因</label></td>
				<td class="value"><input id="actiondesc" name="actiondesc"
					type="text"  class="inputxt"
					value='${bopPPage.actiondesc}" > <span
					class="Validform_checktip"></span></td>

				<td align="left"><label class="Validform_label"> 申报号码
				</label></td>
				<td class="value"><input id="rptno" name="rptno" type="text"
					 class="inputxt" datatype="*"
					value='${bopPPage.rptno}" > <span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 合同号
				</label></td>
				<td class="value"><input id="contrno" name="contrno"
					type="text"  class="inputxt" datatype="*"
					value='${bopPPage.contrno}"  > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 发票号
				</label></td>
				<td class="value"><input id="invoino" name="invoino"
					type="text"  class="inputxt" datatype="*"
					value='${bopPPage.invoino}" > <span
					class="Validform_checktip"></span></td>
									<td colspan="2" rowspan="2"  style="background:white;"><span
					id="showremark" style="color:red;" type="hidden"> </span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 提运单号
				</label></td>
				<td class="value"><input id="billno" name="billno" type="text"
					 class="inputxt" value="${bopPPage.billno}" >
					<span class="Validform_checktip"></span></td>

				<td align="left"><label class="Validform_label"> 合同金额
				</label></td>
				<td class="value"><input id="contamt" name="contamt"
					type="text"  class="inputxt"
					value='${bopPPage.contamt}"  ignore="ignore" datatype="integer"> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 填报人
				</label></td>
				<td class="value"><input id="crtuser" name="crtuser"
					type="text"  class="inputxt" datatype="*"
					value='${bopPPage.crtuser}" > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						填报人电话</label></td>
				<td class="value"><input id="inptelc" name="inptelc"
					type="text"  class="inputxt" datatype="*"
					value='${bopPPage.inptelc}" > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 申报日期
				</label></td>
				<td class="value"><input id="rptdate" name="rptdate"
					type="text"  class="Wdate inputxt"
					onClick="WdatePicker()" datatype="*"
					value='<fmt:formatDate value="${bopPPage.rptdate}"  type="date" pattern="yyyy-MM-dd"/>'>
					<span class="Validform_checktip"></span></td>
			</tr>
		</table>
	</t:formvalid>
</body>
<script src="webpage/safe/bop/bopcommon.js"></script>
<script src="webpage/safe/bop/bopP.js"></script>