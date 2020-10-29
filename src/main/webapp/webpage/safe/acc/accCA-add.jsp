<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>账户开关户信息</title>
<t:base type="jquery,easyui,tools,DatePicker,select2"></t:base>
<script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>

</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="accCAController.do?doAdd" tiptype="1"
		beforeSubmit="check(curform,'CA');">
		<input id="id" name="id" type="hidden" value="${accCAPage.id }">
		<input id="isdel" name="isdel" type="hidden"
			value="${accCAPage.isdel }">
		<input id="isedit" name="isedit" type="hidden"
			value="${accCAPage.isedit }">
		<input id="isexport" name="isexport" type="hidden"
			value="${accCAPage.isexport }">
		<input id="ishandadd" name="ishandadd" type="hidden"
			value="${accCAPage.ishandadd }">
		<input id="isvalidation" name="isvalidation" type="hidden"
			value="${accCAPage.isvalidation }">
		<input id="filename" name="filename" type="hidden"
			value="${accCAPage.filename }">
		<input id="isinsafe" name="isinsafe" type="hidden"
			value="${accCAPage.isinsafe }">
		<input id="tfilename" name="tfilename" type="hidden"
			value="${accCAPage.tfilename }">
		<input id="rptno" name="rptno" type="hidden"
			value="${accCAPage.rptno }">
		<input id="brca" name="brca" type="hidden" value="${accCAPage.brca }">
		<input id="branchcode" name="branchcode" type="hidden"
			value="${accCAPage.branchcode }">
		<input id="branchname" name="branchname" type="hidden"
			value="${accCAPage.branchname }">
		<input id="importdate" name="importdate" type="hidden"
			value="${accCAPage.importdate }">
		<table  cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="left"><label class="Validform_label"> 操作类型:
				</label></td>
				<td class="value"><t:dictSelect field="actiontype" type="list"
						typeGroupCode="actiontype" defaultVal="${accCAPage.actiontype}"
						hasLabel="false" title="操作类型" datatype="*"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 删除原因:
				</label></td>
				<td class="value"><input id="actiondesc" name="actiondesc"
					type="text"  class="inputxt"> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 账号</label>
				</td>
				<td class="value"><input id="accountno" name="accountno"
					type="text"  class="inputxt" datatype="*">
					<span class="Validform_checktip"></span></td>
             <td align="left"><label class="Validform_label">
						 账户状态</label></td>
				<td class="value"><t:dictSelect field="accountstat" type="list"
						id="accountstat" typeGroupCode="accountstat"
						defaultVal="${accCAPage.accountstat}" hasLabel="false" title="账户状态"
						datatype="*"></t:dictSelect> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						开户主体类型</label></td>
				<td class="value"><t:dictSelect field="amtype" type="list"
						id="amtype" typeGroupCode="amtype"
						defaultVal="${accCAPage.amtype}" hasLabel="false" title="开户主体类型"
						datatype="*"></t:dictSelect> <span class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						开户主体代码</label></td>
				<td class="value"><input id="encode" name="encode" type="text"
					 class="inputxt" datatype="*"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">开户主体代码</label></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						开户主体名称</label></td>
				<td class="value"><input id="enname" name="enname" type="text"
					 class="inputxt" datatype="*"> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">开户主体名称</label></td>
				<td align="left"><label class="Validform_label">
						账户性质代码</label></td>
				<td class="value"><t:dictSelect field="accounttype" type="list"
						dictTable="s_p_accounttype" dictField="code" dictText="text"
						defaultVal="${accCAPage.accounttype}" hasLabel="false"
						title="账户性质代码" datatype="*"></t:dictSelect> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">账户性质代码</label></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 账户类别:
				</label></td>
				<td class="value"><t:dictSelect field="accountcata" type="list"
						typeGroupCode="accountcata" defaultVal="${accCAPage.accountcata}"
						hasLabel="false" title="账户类别" datatype="*"></t:dictSelect> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">账户类别</label></td>
				<td align="left"><label class="Validform_label"> 币种</label>
				</td>
				<td class="value"><t:dictSelect field="currencycode"
						type="list" dictTable="normal_currency" dictField="currency"
						dictText="text" defaultVal="${accCAPage.currencycode}"
						hasLabel="false" title="币种" datatype="*"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						业务发生日期</label></td>
				<td class="value"><input id="businessdate" name="businessdate"
					type="text"  class="Wdate inputxt"
					onClick="WdatePicker()" datatype="*"> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						外汇局批件号</label></td>
				<td class="value"><input id="filenumber" name="filenumber"
					type="text"  class="inputxt"> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 限额类型:
				</label></td>
				<td class="value"><t:dictSelect field="limittype" type="list"
						typeGroupCode="limittype" defaultVal="${accCAPage.limittype}"
						hasLabel="false" title="限额类型" datatype="*"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 账户限额:
				</label></td>
				<td class="value"><input id="accountlimit" name="accountlimit"
					type="text"  class="inputxt"> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 备注</label>
				</td>
				<td class="value"><input id="remark" name="remark" type="text"
					 class="inputxt"> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> </label></td>
				<td class="value"></td>
			</tr>
		</table>
	</t:formvalid>
</body>
<script src="webpage/safe/acc/acccommon.js"></script>
<script src="webpage/safe/acc/accCA.js"></script>
<script type="text/javascript">
  //编写自定义JS代码
      $(function(){
    	  $("#actiontype").val("A").trigger("change");
    	  $("#actiontype").prop("disabled",false);
  });

  </script>