<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>账户开关户信息</title>
<t:base type="jquery,easyui,tools,DatePicker,select2"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		tiptype="1" layout="table" action="accCAController.do?save">
		<input id="id" name="id" type="hidden" value="${accCAPage.id }">
		<table  cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="left"><label class="Validform_label"> <t:mutiLang
							langKey="r.actiontype" />:
				</label></td>
				<td class="value"><t:dictSelect field="actiontype"
						id="actiontype" 
						typeGroupCode="actiontype" defaultVal="${accCAPage.actiontype}"
						hasLabel="false"></t:dictSelect> <span class="Validform_checktip"></span></td>

				<td align="left"><label class="Validform_label"> <t:mutiLang
							langKey="r.actiondesc" />:
				</label></td>
				<td class="value"><input class="inputxt" id="actiondesc"
					name="actiondesc" ignore="ignore" value="${accCAPage.actiondesc}">
					<span class="Validform_checktip"></span></td>

			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> <t:mutiLang
							langKey="r.branch.code" />:
				</label></td>

				<td class="value"><input class="inputxt" id="branchcode" readonly="readonly"
					name="branchcode" value="${accCAPage.branchcode}"> <span
					class="Validform_checktip"></span></td>

				<td align="left"><label class="Validform_label"> <t:mutiLang
							langKey="r.branch.name" />:
				</label></td>
				<td class="value" ><input class="inputxt" id="branchname" style="text-align:center;"
					readonly="readonly" name="branchname"
					value="${accCAPage.branchname}"> <span
					class="Validform_checktip"></span></td>

			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> <t:mutiLang
							langKey="r.account.stat" />:
				</label></td>

				<td class="value"><t:dictSelect field="accountstat"
						 typeGroupCode="accountstat"
						defaultVal="${accCAPage.accountstat}" hasLabel="false"></t:dictSelect>
					<span class="Validform_checktip"></span></td>

				<td align="left"><label class="Validform_label"> <t:mutiLang
							langKey="r.business.date" />:
				</label></td>
				<td class="value"><input class="easyui-datebox"
					onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
					id="businessdate" name="businessdate"
					value="<fmt:formatDate value="${accCAPage.businessdate}"  type="date" pattern="yyyy-MM-dd"/>"
					errormsg="日期格式不正确!"> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td>
				<td>
			<tr>
			<tr>
				<td align="left"><label class="Validform_label"> <t:mutiLang
							langKey="r.amtype" />:
				</label></td>

				<td class="value"><t:dictSelect field="amtype"
						 typeGroupCode="amtype"
						defaultVal="${accCAPage.amtype}" hasLabel="false"></t:dictSelect>
					<span class="Validform_checktip"></span></td>


				<td align="left"><label class="Validform_label"> <t:mutiLang
							langKey="r.encode" />:
				</label></td>
				<td class="value"><input class="inputxt" id="encode"
					name="encode" value="${accCAPage.encode}" datatype="*"> <span
					class="Validform_checktip"></span></td>

			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> <t:mutiLang
							langKey="r.enname" />:
				</label></td>
				<td class="value" colspan="3"><input class="inputxt"
					id="enname" style="width:94%;" name="enname"
					value="${accCAPage.enname}" datatype="*"> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> <t:mutiLang
							langKey="r.account.no" />:
				</label></td>
				<td class="value"><input class="inputxt" id="accountno"
					name="accountno" value="${accCAPage.accountno}" datatype="*">
					<span class="Validform_checktip"></span></td>

				<td align="left"><label class="Validform_label"> <t:mutiLang
							langKey="r.currency.code" />:
				</label></td>

				<td class="value"><t:comboBox
						url="currencyController.do?getCurrencyBox" field="currencycode"
						name="ccy" text="currency,name" id="currency" editable="true"
						width="155" defaultvalue="${accCAPage.currencycode}"
						listHeight="200" listWidth="300"></t:comboBox></td>

			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> <t:mutiLang
							langKey="r.account.type" />:
				</label></td>

				<td class="value"><t:comboBox
						url="accountTypeController.do?getAccountTypeBox"
						field="accounttype" name="acctype" text="code,name" id="code"
						editable="true" width="155"
						defaultvalue="${accCAPage.accounttype}" listHeight="200"
						listWidth="300"></t:comboBox></td>



				<td align="left"><label class="Validform_label"> <t:mutiLang
							langKey="r.account.cata" />:
				</label></td>
				<td class="value"><t:dictSelect field="accountcata"
						 typeGroupCode="accountcata"
						defaultVal="${accCAPage.accountcata}" hasLabel="false"
						type="select"></t:dictSelect> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> <t:mutiLang
							langKey="r.limit.type" />:
				</label></td>
				<td class="value"><t:dictSelect field="limittype"
						 typeGroupCode="limittype"
						defaultVal="${accCAPage.limittype}" hasLabel="false" type="select"></t:dictSelect>
					<span class="Validform_checktip"></span></td>



				<td align="left"><label class="Validform_label"
					name="accountlimit"> <t:mutiLang langKey="r.account.limit" />:
				</label></td>
				<td class="value"><input class="inputxt" id="accountlimit"
					name="accountlimit" value="${accCAPage.accountlimit}" datatype="d">
					<span class="Validform_checktip"></span></td>
			</tr>
			<tr>
			<td align="left"><label class="Validform_label"> <t:mutiLang
							langKey="r.file.number" />:
				</label></td>
				<td class="value"><input class="inputxt" id="filenumber"
					name="filenumber" ignore="ignore" value="${accCAPage.filenumber}">
					<span class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> <t:mutiLang
							langKey="common.remark" />:
				</label></td>
				<td class="value"><input class="inputxt" id="remark"
					name="remark" ignore="ignore" value="${accCAPage.remark}">
					<span class="Validform_checktip"></span></td>

			</tr>
			<tr>
				<input id="brca" name="brca" type="hidden" value="${accCAPage.brca}">
				<input id="parentbrca" name="parentbrca" type="hidden"
					value="${accCAPage.parentbrca}">
				<input id="importdate" name="importdate" type="hidden"
					value="${accCAPage.importdate}">
				<input id="isdel" name="isdel" ignore="ignore" type="hidden"
					value="${accCAPage.isdel}">
				<input id="isedit" name="isedit" ignore="ignore" type="hidden"
					value="${accCAPage.isedit}">
				<input id="isexport" name="isexport" ignore="ignore" type="hidden"
					value="${accCAPage.isexport}">
				<input id="ishandadd" name="ishandadd" ignore="ignore" type="hidden"
					value="${accCAPage.ishandadd}">
				<input id="isvalidation" name="isvalidation" ignore="ignore"
					type="hidden" value="${accCAPage.isvalidation}">
				<input id="filename" name="filename" ignore="ignore" type="hidden"
					value="${accCAPage.filename}">
				<input id="isinsafe" name="isinsafe" ignore="ignore" type="hidden"
					value="${accCAPage.isinsafe}">
				<input id="tfilename" name="tfilename" ignore="ignore" type="hidden"
					value="${accCAPage.tfilename}">
				<input id="rptno" name="rptno" ignore="ignore" type="hidden"
					value="${accCAPage.rptno}">
				<span class="Validform_checktip"></span>
				</td>
			</tr>
		</table>
	</t:formvalid>
</body>