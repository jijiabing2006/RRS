<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>acc_accountopenclose</title>
<t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script type="text/javascript">
  //编写自定义JS代码
  
  </script>
</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="accCAController.do?doUpdate" tiptype="1"
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
		<input id="importdate" name="importdate" type="hidden"
			value="${accCAPage.importdate }">
		<table  cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="left"><label class="Validform_label"> 操作类型
				</label></td>
				<td class="value"><t:dictSelect field="actiontype" type="list"
						typeGroupCode="actiontype" defaultVal="${accCAPage.actiontype}"
						hasLabel="false" title="操作类型" datatype="*" ></t:dictSelect> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 删除原因
				</label></td>
				<td class="value"><input id="actiondesc" name="actiondesc"
					type="text"  class="inputxt"
					value="${accCAPage.actiondesc}" > <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						金融机构标识码 </label></td>
				<td class="value"><input id="branchcode" name="branchcode"
					type="text"  class="inputxt"
					readonly="readonly" datatype="*" value="${accCAPage.branchcode}" >
					<span class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						金融机构名称 </label></td>
				<td class="value"><input id="branchname" name="branchname"
					type="text"  class="inputxt"
					readonly="readonly" datatype="*" value="${accCAPage.branchname}" >
					<span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 账号 </label>
				</td>
				<td class="value"><input id="accountno" name="accountno"
					type="text"  class="inputxt" datatype="*"
					value="${accCAPage.accountno}" > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"><t:mutiLang langKey='r.account.status'/>
				</label></td>
				<td class="value"><t:dictSelect field="accountstat" type="list"
						typeGroupCode="accountstat" defaultVal="${accCAPage.accountstat}"
						hasLabel="false" title="账户状态" datatype="*" nullmsg="<t:mutiLang langKey='common.notnull.param' langArg='r.account.status'/>"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						<t:mutiLang langKey='r.amtype'/> </label></td>
				<td class="value"><t:dictSelect field="amtype" type="list"
						typeGroupCode="amtype" defaultVal="${accCAPage.amtype}"
						hasLabel="false" title="开户主体类型" datatype="*" nullmsg="<t:mutiLang langKey='common.notnull.param' langArg='r.amtype'/>"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						开户主体代码 </label></td>
				<td class="value"><input id="encode" name="encode" type="text"
					 class="inputxt" datatype="*" with="amtype#eq#11"
					value="${accCAPage.encode}" > <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						开户主体名称 </label></td>
				<td class="value"><input id="enname" name="enname" type="text"
					 class="inputxt" datatype="*"
					value="${accCAPage.enname}" > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						<t:mutiLang langKey='r.account.type'/></label></td>
				<td class="value"><t:dictSelect field="accounttype" type="list"
						dictTable="s_p_accounttype" dictField="code" dictText="text"
						defaultVal="${accCAPage.accounttype}" hasLabel="false"
						title="账户性质代码" datatype="*"  nullmsg="<t:mutiLang langKey='common.notnull.param' langArg='r.account.type'/>"></t:dictSelect> <span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> <t:mutiLang langKey='r.account.cata'/>
				</label></td>
				<td class="value"><t:dictSelect field="accountcata" type="list"
						typeGroupCode="accountcata" defaultVal="${accCAPage.accountcata}"
						hasLabel="false" title="账户类别" datatype="*" nullmsg="<t:mutiLang langKey='common.notnull.param' langArg='r.account.cata'/>"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> <t:mutiLang langKey='r.currency.code'/> </label>
				</td>
				<td class="value"><t:dictSelect field="currencycode"
						type="list" dictTable="normal_currency" dictField="currency"
						dictText="text" defaultVal="${accCAPage.currencycode}"
						hasLabel="false" title="币种" datatype="*" nullmsg="<t:mutiLang langKey='common.notnull.param' langArg='r.currency.code'/>"></t:dictSelect> <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">币种</label></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						业务发生日期 </label></td>
				<td class="value"><input id="businessdate" name="businessdate"
					type="text"  class="Wdate inputxt"
					onClick="WdatePicker()" datatype="*"
					value='<fmt:formatDate value="${accCAPage.businessdate}"  type="date" pattern="yyyy-MM-dd"/>'>
					<span class="Validform_checktip"></span> <label
					class="Validform_label" style="display: none;">业务发生日期</label></td>
				<td align="left"><label class="Validform_label">
						外汇局批件号 </label></td>
				<td class="value"><input id="filenumber" name="filenumber"
					type="text"  class="inputxt"
					value="${accCAPage.filenumber}" > <span
					class="Validform_checktip"></span> <label class="Validform_label"
					style="display: none;">外汇局批件号</label></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> <t:mutiLang langKey='r.account.limit'/>
				</label></td>
				<td class="value"><t:dictSelect id="limittype" field="limittype" type="list"
						typeGroupCode="limittype" defaultVal="${accCAPage.limittype}"
						hasLabel="false" title="限额类型" datatype="*" nullmsg="<t:mutiLang langKey='common.notnull.param' langArg='r.account.limit'/>"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 账户限额
				</label></td>
				<td class="value"><input id="accountlimit" name="accountlimit"
					type="text"  class="inputxt"
					value="${accCAPage.accountlimit}"  ignore="ignore" datatype="positivenum"> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 备注 </label>
				</td>
				<td class="value"><input id="remark" name="remark" type="text"
					 class="inputxt" value="${accCAPage.remark}"  >
					<span class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> </label></td>
				<td class="value"></td>
			</tr>
		</table>
	</t:formvalid>
</body>
<script src="webpage/safe/acc/acccommon.js"></script>
<script src="webpage/safe/acc/accCA.js"></script>
<script type="text/javascript">
   function checkbak(curform) {
   	// alertTip("已经通过审核的记录不可以再进行编辑操作");
	// $.Showmsg ("已经通过审核的记录不可以再进行编辑操作");
	
	
	if($("#isvalidation").val()=="1"){
	// tip ("审核状态为[完成]的记录不可以再进行编辑操作");
	$.Showmsg ("已经通过审核的记录不可以再进行编辑操作");
//		$("#accountno").focus().attr("style","background:red");
//		$("#accounttype").prop("disabled",true);
		$("#accounttype").select2("open");
		return false;
	}else{
		$("#importdate").val($("#businessdate").val());
		$("#isedit").val("1");
		$("#isvalidation").val("0");
		$("#remark").removeAttr("disabled").val("");
	}
	}
</script>