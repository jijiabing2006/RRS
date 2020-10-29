<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>账户收支余信息</title>
<t:base type="jquery,easyui,tools,DatePicker,select2"></t:base>
<script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script type="text/javascript">
  //编写自定义JS代码

  </script>
</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="accCBController.do?doUpdate" tiptype="1"  beforeSubmit="check(curform,'CB');">
		<input id="id" name="id" type="hidden" value="${accCBPage.id }">
		<input id="branchcode" name="branchcode" type="hidden"
			value="${accCBPage.branchcode }">
		<input id="brca" name="brca" type="hidden" value="${accCBPage.brca }">
		<input id="importdate" name="importdate" type="hidden"
			value="${accCBPage.importdate }">
		<input id="isdel" name="isdel" type="hidden"
			value="${accCBPage.isdel }">
		<input id="isedit" name="isedit" type="hidden"
			value="${accCBPage.isedit }">
		<input id="isexport" name="isexport" type="hidden"
			value="${accCBPage.isexport }">
		<input id="ishandadd" name="ishandadd" type="hidden"
			value="${accCBPage.ishandadd }">
		<input id="isvalidation" name="isvalidation" type="hidden"
			value="${accCBPage.isvalidation }">
		<input id="filename" name="filename" type="hidden"
			value="${accCBPage.filename }">
		<input id="isinsafe" name="isinsafe" type="hidden"
			value="${accCBPage.isinsafe }">
		<input id="tfilename" name="tfilename" type="hidden"
			value="${accCBPage.tfilename }">
		<input id="state" name="state" type="hidden"
			value="${accCBPage.state }">
		<input id="rptno" name="rptno" type="hidden"
			value="${accCBPage.rptno }">
		<input id="islastexport" name="islastexport" type="hidden"
			value="${accCBPage.islastexport }">
		<table  cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="left"><label class="Validform_label"> 操作类型
				</label></td>
				<td class="value"><t:dictSelect field="actiontype" type="list"
						 typeGroupCode="actiontype"
						defaultVal="${accCBPage.actiontype}" hasLabel="false" title="操作类型"
						datatype="*"></t:dictSelect> <span class="Validform_checktip"></span>
				</td>
				<td align="left"><label class="Validform_label"> 删除原因
				</label></td>
				<td class="value"><input id="actiondesc" name="actiondesc"
					type="text"  class="inputxt"
					value="${accCBPage.actiondesc}" > <span
					class="Validform_checktip"></span> </td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 账号 </label>
				</td>
				<td class="value"><input id="accountno" name="accountno"
					type="text"  class="inputxt" datatype="*"
					value="${accCBPage.accountno}" > <span
					class="Validform_checktip"></span> </td>
				<td align="left"><label class="Validform_label"> 发生日期
				</label></td>
				<td class="value"><input id="dealdate" name="dealdate"
					type="text"  class="Wdate inputxt"
					onClick="WdatePicker()" datatype="*"
					value='<fmt:formatDate value="${accCBPage.dealdate}"  type="date" pattern="yyyy-MM-dd"/>'>
					<span class="Validform_checktip"></span> </td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 币种</label>
				</td>
				<td class="value"><t:dictSelect field="currencycode"
						type="list"  dictTable="normal_currency"
						dictField="currency" dictText="text"
						defaultVal="${accCBPage.currencycode}" hasLabel="false" title="币种"></t:dictSelect>
					<span class="Validform_checktip"></span> </td>
				<td align="left"><label class="Validform_label">
						当日贷方发生额 </label></td>
				<td class="value"><input id="credit" name="credit" type="text"
					 class="inputxt" datatype="*,positivenum"
					value="${accCBPage.credit}" > <span
					class="Validform_checktip"></span> </td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						当日借方发生额 </label></td>
				<td class="value"><input id="debit" name="debit" type="text"
					 class="inputxt" datatype="*,positivenum"
					value="${accCBPage.debit}" > <span
					class="Validform_checktip"></span> </td>
				<td align="left"><label class="Validform_label"> 账户余额
				</label></td>
				<td class="value"><input id="balance" name="balance"
					type="text"  class="inputxt" datatype="num"
					value="${accCBPage.balance}" > <span
					class="Validform_checktip"></span> </td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						上一日余额</label></td>
				<td class="value"><input id="lastbalance" name="lastbalance"
					type="text"  class="inputxt"
					value="${accCBPage.lastbalance}"  datatype="num"> <span
					class="Validform_checktip"></span> </td>
				<td align="left"><label class="Validform_label">
						已上报余额</label></td>
				<td class="value"><input id="safebalance" name="safebalance"
					type="text"  class="inputxt"
					value="${accCBPage.safebalance}"  datatype="num"> <span
					class="Validform_checktip"></span> </td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 备注</label>
				</td>
				<td class="value"><input id="remark" name="remark" type="text" disabled="true"
					 class="inputxt" value="${accCBPage.remark}" >
					<span class="Validform_checktip"></span> </td>
				<td align="left"><label class="Validform_label"> </label></td>
				<td class="value"></td>
			</tr>
		</table>
	</t:formvalid>
</body>
<script src="webpage/safe/acc/acccommon.js"></script>
<script src="webpage/safe/acc/accCB.js"></script>
   <script type="text/javascript">
   function checkbad(curform) {
	  // alertTip("已经通过审核的记录不可以再进行编辑操作");
	//  $.Showmsg ("已经通过审核的记录不可以再进行编辑操作");
	if($("#isvalidation").val()=="1"){
		alertTip ("审核状态为[完成]的记录不可以再进行编辑操作");
		return false;
	}else{
		
		$("#isedit").val('1');
		$("#isvalidation").val('0');
		$("#remark").removeAttr("disabled").val("");
	}		 
	}
   </script>	