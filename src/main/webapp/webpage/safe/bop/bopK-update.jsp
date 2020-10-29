<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>bop_k</title>
<t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script type="text/javascript">
  //编写自定义JS代码
  </script>
</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="bopKController.do?doUpdate" tiptype="1"
		beforeSubmit="check(curform,'K');">
		<input id="id" name="id" type="hidden" value="${bopKPage.id }">
		<input id="buscode" name="buscode" type="hidden"
			value="${bopKPage.buscode }">
		<input id="cap" name="cap" type="hidden" value="${bopKPage.cap }">
		<input id="brca" name="brca" type="hidden" value="${bopKPage.brca }">
		<input id="importdate" name="importdate" type="hidden"
			value="${bopKPage.importdate }">
		<input id="isdel" name="isdel" type="hidden"
			value="${bopKPage.isdel }">
		<input id="isedit" name="isedit" type="hidden"
			value="${bopKPage.isedit }">
		<input id="isexport" name="isexport" type="hidden"
			value="${bopKPage.isexport }">
		<input id="ishandadd" name="ishandadd" type="hidden"
			value="${bopKPage.ishandadd }">
		<input id="isvalidation" name="isvalidation" type="hidden"
			value="${bopKPage.isvalidation }">
		<input id="filename" name="filename" type="hidden"
			value="${bopKPage.filename }">
		<input id="isinsafe" name="isinsafe" type="hidden"
			value="${bopKPage.isinsafe }">
		<input id="tfilename" name="tfilename" type="hidden"
			value="${bopKPage.tfilename }">
		<input id="remark" name="remark" type="hidden"
			value="${bopKPage.remark }">
		<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="left"><label class="Validform_label"> 操作类型 </label></td>
				<td class="value"><t:dictSelect field="actiontype" type="list"
						typeGroupCode="actiontype" defaultVal="${bopKPage.actiontype}"
						hasLabel="false" title="操作类型" datatype="*"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 修改删除原因</label></td>
				<td class="value"><input id="actiondesc" name="actiondesc"
					type="text"  class="inputxt"
					value='${bopKPage.actiondesc}" > <span
					class="Validform_checktip"></span></td>

				<td align="left"><label class="Validform_label"> 申报号码 </label></td>
				<td class="value"><input id="rptno" name="rptno" type="text"
					 class="inputxt" readonly="readonly"
					value='${bopKPage.rptno}" > <span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						收款人常驻国家</label></td>
				<td class="value"><t:dictSelect field="country" type="list"
						dictTable="s_p_countrycode" dictField="code " dictText="text"
						defaultVal="${bopKPage.country}" hasLabel="false"
						title="收款人常驻国家地区代码" datatype="*"></t:dictSelect> <span
					class="Validform_checktip"></span></td>

				<td align="left"><label class="Validform_label"> 付款性质 </label></td>
				<td class="value"><t:dictSelect field="paytype" type="list"
						typeGroupCode="paytype" defaultVal="${bopKPage.paytype}"
						hasLabel="false" title="付款性质" datatype="*"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
				<td colspan="2" style="background:white;"><span id="showremark"
					style="color:red;" type="hidden"> </span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 交易编码1</label></td>
				<td class="value"><t:dictSelect field="txcode" type="list"
						dictTable="s_p_transcationcode where dir='2'" dictField="code"
						dictText="text" defaultVal="${bopKPage.txcode}" hasLabel="false"
						title="交易编码1" datatype="*"></t:dictSelect> <span
					class="Validform_checktip"></span></td>

				<td align="left"><label class="Validform_label"> 相应金额1</label></td>
				<td class="value"><input id="tc1amt" name="tc1amt" type="text"
					 class="inputxt"  datatype="integer"
					value='${bopKPage.tc1amt}" > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 交易附言1</label></td>
				<td class="value"><input id="txrem" name="txrem" type="text"
					 class="inputxt" datatype="*"
					value='${bopKPage.txrem}" > <span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 交易编码2</label></td>
				<td class="value"><t:dictSelect field="txcode2" type="list"
						dictTable="s_p_transcationcode where dir='2'" dictField="code"
						dictText="text" defaultVal="${bopKPage.txcode2}" hasLabel="false"
						title="交易编码2" ignore="ignore" datatype="duplicate,linkcheck"
						with="duplicate:txcode,linkcheck:tc2amt#empty#false,linkcheck:tx2rem#empty#false"></t:dictSelect>
					<span class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 相应金额2</label></td>
				<td class="value"><input id="tc2amt" name="tc2amt" type="text"
					 class="inputxt" value="${bopKPage.tc2amt}" 
					ignore="ignore" datatype="integer,linkcheck"
					with="txcode2#empty#false,tx2rem#empty#false"> <span
					class="Validform_checktip"></span></td>

				<td align="left"><label class="Validform_label"> 交易附言2</label></td>
				<td class="value"><input id="tx2rem" name="tx2rem" type="text"
					 class="inputxt" value="${bopKPage.tx2rem}" 
					ignore="ignore" datatype="linkcheck"
					with="tc2amt#empty#false,txcode2#empty#false"> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 外汇局批件号</label></td>
				<td class="value"><input id="regno" name="regno" type="text"
					 class="inputxt" value="${bopKPage.regno}" >
					<span class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						是否保税货物项下</label></td>
				<td class="value"><t:dictSelect field="isref" type="list"
						typeGroupCode="sf_YN" defaultVal="${bopKPage.isref}"
						hasLabel="false" title="是否保税货物项下" datatype="*"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
				<td colspan="2" style="background:white;"><span id="showremark"
					style="color:red;" type="hidden"> </span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 联系人 </label></td>
				<td class="value"><input id="crtuser" name="crtuser"
					type="text"  class="inputxt" datatype="*"
					value='${bopKPage.crtuser}" > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 联系人电话</label></td>
				<td class="value"><input id="inptelc" name="inptelc"
					type="text"  class="inputxt" datatype="*"
					value='${bopKPage.inptelc}" > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 申报日期 </label></td>
				<td class="value"><input id="rptdate" name="rptdate"
					type="text"  class="Wdate inputxt"
					onClick="WdatePicker()" datatype="*"
					value='<fmt:formatDate value="${bopKPage.rptdate}"  type="date" pattern="yyyy-MM-dd"/>'>
					<span class="Validform_checktip"></span></td>
			</tr>
		</table>
	</t:formvalid>
</body>
<script src="webpage/safe/bop/bopcommon.js"></script>
<script src="webpage/safe/bop/bopK.js"></script>