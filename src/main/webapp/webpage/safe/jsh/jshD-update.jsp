<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>结汇基础信息</title>
<t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script type="text/javascript">
  //编写自定义JS代码
  </script>
</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="jshDController.do?doUpdate" tiptype="1"
		beforeSubmit="check(curform,'D');">
		<input id="id" name="id" type="hidden" value="${jshDPage.id }">
		<input id="brca" name="brca" type="hidden" value="${jshDPage.brca }">
		<input id="rptno" name="rptno" type="hidden"
			value="${jshDPage.rptno }">
		<input id="isdel" name="isdel" type="hidden"
			value="${jshDPage.isdel }">
		<input id="isedit" name="isedit" type="hidden"
			value="${jshDPage.isedit }">
		<input id="isexport" name="isexport" type="hidden"
			value="${jshDPage.isexport }">
		<input id="ishandadd" name="ishandadd" type="hidden"
			value="${jshDPage.ishandadd }">
		<input id="isvalidation" name="isvalidation" type="hidden"
			value="${jshDPage.isvalidation }">
		<input id="filename" name="filename" type="hidden"
			value="${jshDPage.filename }">
		<input id="isinsafe" name="isinsafe" type="hidden"
			value="${jshDPage.isinsafe }">
		<input id="tfilename" name="tfilename" type="hidden"
			value="${jshDPage.tfilename }">
		<input id="remark" name="remark" type="hidden"
			value="${jshDPage.remark }">
		<table  cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="left"><label class="Validform_label"> 操作类型 </label>
				</td>
				<td class="value"><t:dictSelect field="actiontype" type="list"
						typeGroupCode="actiontype" defaultVal="${jshDPage.actiontype}"
						hasLabel="false" title="操作类型" datatype="*"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 修改删除原因
				</label></td>
				<td class="value"><input id="actiondesc" name="actiondesc"
					type="text"  class="inputxt"
					value="${jshDPage.actiondesc}" > <span
					class="Validform_checktip"></span></td>
			</tr>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						结汇人主体类型 </label></td>
				<td class="value"><t:dictSelect id="custype" field="custype"
						type="list" typeGroupCode="custype"
						defaultVal="${jshDPage.custype}" hasLabel="false"
						title="结汇申请人主体类型" datatype="*"></t:dictSelect> <span class="Validform_checktip"></span>
				</td>
			<td align="left"><label class="Validform_label"> 银行业务编号
				</label></td>
				<td class="value"><input id="buscode" name="buscode"
					type="text"  class="inputxt" datatype="*"
					value="${jshDPage.buscode}" > <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>

				<td align="left"><label class="Validform_label"> 组织机构代码
				</label></td>
				<td class="value"><input id="custcod" name="custcod"
					type="text"  class="inputxt"
					value="${jshDPage.custcod}"  datatype="*,s9-9"> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						个人身份证件号码 </label></td>
				<td class="value"><input id="idcode" name="idcode" type="text"
					 class="inputxt"   value="${jshDPage.idcode}"  datatype="*" >
					<span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 结汇申请人名称
				</label></td>
				<td class="value" colspan="3"><input id="custnm" name="custnm"
					type="text" style="width: 90%" class="inputxt" datatype="*"
					value="${jshDPage.custnm}" > <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 外汇账户账号
				</label></td>
				<td class="value"><input id="fcyacc" name="fcyacc" type="text"
					 class="inputxt" datatype="*"
					value="${jshDPage.fcyacc}" > <span
					class="Validform_checktip"></span></td>

				<td align="left"><label class="Validform_label"> 币别 </label></td>
				<td class="value"><t:dictSelect field="fcyccy" type="list"
						dictTable="normal_currency" dictField="currency" dictText="text"
						defaultVal="${jshDPage.fcyccy}" hasLabel="false" title="币别" datatype="*"></t:dictSelect>
					<span class="Validform_checktip"></span></td>
			</tr>
			<tr>

				<td align="left"><label class="Validform_label"> 结汇金额 </label>
				</td>
				<td class="value"><input id="fcyamt" name="fcyamt" type="text"
					 class="inputxt" datatype="*,integer"
					value="${jshDPage.fcyamt}" > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 汇率 </label></td>
				<td class="value"><input id="exrate" name="exrate" type="text"
					 class="inputxt" datatype="positivenum"
					value="${jshDPage.exrate}" > <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						人民币收款人名称 </label></td>
				<td class="value" colspan="3"><input id="oppuser"
					name="oppuser" type="text" style="width: 90%" class="inputxt"
					datatype="*" value="${jshDPage.oppuser}" > <span
					class="Validform_checktip"></span></td>

			</tr>
			<td colspan="4" style="border-color: #fff;  border-style: groove;  border-width: 0 2px 2px 2px;line-height:5%;"><font
				style="color:white;">.</font></td>
			<tr>
				<td align="left"><label class="Validform_label">
						人民币账户开户行 </label></td>
				<td class="value"><input id="oppbank" name="oppbank"
					type="text"  class="inputxt" datatype="*"
					value="${jshDPage.oppbank}" > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						人民币账户账号 </label></td>
				<td class="value"><input id="lcyacc" name="lcyacc" type="text"
					 class="inputxt" value="${jshDPage.lcyacc}" >
					<span class="Validform_checktip"></span></td>
			</tr>
			<tr>
			<td align="left"><label class="Validform_label"> 营业日期 </label>
				</td>
				<td class="value"><input id="importdate" name="importdate"
					type="text"  class="Wdate inputxt"
					onClick="WdatePicker()" datatype="*"
					value='<fmt:formatDate value="${jshDPage.importdate}"  type="date" pattern="yyyy-MM-dd"/>'>
					<span class="Validform_checktip"></span></td>
					<td colspan="2" style="background:white;line-height:30%;"><font
					style="color:white;">.</font></td>

		</table>
	</t:formvalid>
</body>
<script src="webpage/safe/jsh/jshcommon.js"></script>
<script src="webpage/safe/jsh/jshD.js"></script>