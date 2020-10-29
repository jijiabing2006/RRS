<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>境内收入申报单基础信息</title>
<t:base type="jquery,easyui,tools,DatePicker,select2"></t:base>
<script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
<script type="text/javascript">
	//编写自定义JS代码
</script>
</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="bopDController.do?doUpdate" tiptype="1"
		beforeSubmit="check(curform,'D');">
		<input id="id" name="id" type="hidden" value="${bopDPage.id }">
		<input id="brca" name="brca" type="hidden" value="${bopDPage.brca }">
		<input id="rptno" name="rptno" type="hidden" value="${bopDPage.rptno }">
		<input id="isdel" name="isdel" type="hidden" value="${bopDPage.isdel }">
		<input id="isedit" name="isedit" type="hidden" value="${bopDPage.isedit }">
		<input id="isexport" name="isexport" type="hidden" value="${bopDPage.isexport }">
		<input id="ishandadd" name="ishandadd" type="hidden" value="${bopDPage.ishandadd }">
		<input id="isvalidation" name="isvalidation" type="hidden" value="${bopDPage.isvalidation }">
		<input id="filename" name="filename" type="hidden" value="${bopDPage.filename }">
		<input id="isinsafe" name="isinsafe" type="hidden" value="${bopDPage.isinsafe }">
		<input id="tfilename" name="tfilename" type="hidden" value="${bopDPage.tfilename }">
		<input id="remark" name="remark" type="hidden" value="${bopDPage.remark }">
	<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="left"><label class="Validform_label"> 操作类型
				</label></td>
				<td class="value"><t:dictSelect field="actiontype" type="list"
						typeGroupCode="actiontype" defaultVal="${bopDPage.actiontype}"
						hasLabel="false" title="操作类型" datatype="*"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						修改删除原因 </label></td>
				<td class="value"><input id="actiondesc" name="actiondesc"
					type="text"  class="inputxt"
					value="${bopDPage.actiondesc}" > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 营业日期
				</label></td>
				<td class="value"><input id="importdate" name="importdate"
					type="text"  class="Wdate inputxt"
					onClick="WdatePicker()"
					value='<fmt:formatDate value="${bopDPage.importdate}"  type="date" pattern="yyyy-MM-dd"/>'
					datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						收款人名称 </label></td>
				<td class="value" colspan="3"><input id="custnm" name="custnm"
					type="text" style="width: 94%" class="inputxt" datatype="*2-128"
					value="${bopDPage.custnm}" > <span
					class="Validform_checktip"></span></td>


				<td align="left"><label class="Validform_label">
						银行业务编号 </label></td>
				<td class="value"><input id="buscode" name="buscode"
					type="text"  class="inputxt"  datatype="s1-22"
					value="${bopDPage.buscode}" > <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						收入款币种 </label></td>
				<td class="value"><t:dictSelect field="txccy" type="list"
						dictTable="normal_currency" dictField="currency" dictText="text"
						defaultVal="${bopDPage.txccy}" hasLabel="false" title="收入款币种" datatype="*"></t:dictSelect>
					<span class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						收入款金额 </label></td>
				<td class="value"><input id="txamt" name="txamt" type="text"
					 class="inputxt" 
					value="${bopDPage.txamt}"  datatype="integer,sumge" with="lcyamt,fcyamt,othamt"> <span class="Validform_checktip"></span>
				</td>
				<td align="left"><label class="Validform_label"> 结算方式
				</label></td>
				<td class="value"><t:dictSelect field="method" type="list"
						dictTable="s_p_bopmethod " dictField="code" dictText="name"
						defaultVal="${bopDPage.method}" hasLabel="false" title="结算方式" datatype="*"></t:dictSelect>
					<span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						收款人类型 </label></td>
				<td class="value"><t:dictSelect field="custype" type="list"
						typeGroupCode="custype" defaultVal="${bopDPage.custype}"
						hasLabel="false" title="收款人类型" datatype="*"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						个人身份证件号码 </label></td>
				<td class="value"><input id="idcode" name="idcode" type="text"
					 class="inputxt" value="${bopDPage.idcode}"  datatype="*">
					<span class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						组织机构代码 </label></td>
				<td class="value"><input id="custcod" name="custcod"
					type="text"  class="inputxt"
					value="${bopDPage.custcod}"  datatype="*,s9-9"> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						付款人名称 </label></td>
				<td class="value" colspan="5"><input id="oppuser"
					name="oppuser" type="text" style="width: 96%" class="inputxt"
					datatype="*2-128" value="${bopDPage.oppuser}" > <span
					class="Validform_checktip"></span></td>
				<td colspan="2"></td>
			</tr>
			<td colspan="6" style="border-color: #fff;  border-style: groove;  border-width: 0 2px 2px 2px;line-height:5%;"><font
				style="color:white;">.</font></td>
			<tr>

				<td align="left"><label class="Validform_label"> 结汇金额
				</label></td>
				<td class="value"><input id="lcyamt" name="lcyamt" type="text"
					 class="inputxt" value="${bopDPage.lcyamt}"  ignore="ignore" datatype="integer,linkcheck" with="lcyacc#empty#false,exrate#empty#false">
					<span class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						人民币帐号卡号 </label></td>
				<td class="value"><input id="lcyacc" name="lcyacc" type="text"
					 class="inputxt" value="${bopDPage.lcyacc}"  ignore="ignore" datatype="linkcheck" with="lcyamt#empty#false,exrate#empty#false">
					<span class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 结汇汇率
				</label></td>
				<td class="value"><input id="exrate" name="exrate" type="text"
					 class="inputxt" value="${bopDPage.exrate}"  ignore="ignore" datatype="num,linkcheck" with="lcyamt#empty#false,lcyacc#empty#false">
					<span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 现汇金额
				</label></td>
				<td class="value"><input id="fcyamt" name="fcyamt" type="text"
					 class="inputxt" value="${bopDPage.fcyamt}"  ignore="ignore" datatype="integer,bondcheck" with="fcyacc">
					<span class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						外汇帐号卡号 </label></td>
				<td class="value"><input id="fcyacc" name="fcyacc" type="text"
					 class="inputxt" value="${bopDPage.fcyacc}"  ignore="ignore" datatype="bondcheck" with="fcyamt">
					<span class="Validform_checktip"></span></td>
				<td colspan="2" rowspan="4" style="background:white;"><span
					id="showremark" style="color:red;" type="hidden">${bopDPage.remark
						}</span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 其它金额
				</label></td>
				<td class="value"><input id="othamt" name="othamt" type="text"
					 class="inputxt" value="${bopDPage.othamt}"  ignore="ignore" datatype="integer,bondcheck" with="othacc">
					<span class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						其它帐号卡号 </label></td>
				<td class="value"><input id="othacc" name="othacc" type="text"
					 class="inputxt" value="${bopDPage.othacc}"  ignore="ignore" datatype="bondcheck" with="othamt">
					<span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 国内银行扣费金额
				</label></td>
				<td class="value"><input id="inchargeamt" name="inchargeamt"
					type="text" class="inputxt" value="${bopDPage.inchargeamt}"  ignore="ignore" datatype="integer,bondcheck" with="inchargeccy">
					<span class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						国内银行扣费币种 </label></td>
				<td class="value"><t:dictSelect field="inchargeccy" type="list"
						dictTable="normal_currency" dictField="currency" dictText="text"
						defaultVal="${bopDPage.inchargeccy}" ignore="ignore" datatype="bondcheck" with="inchargeamt"></t:dictSelect> <span
					class="Validform_checktip"></span></td>

			</tr>
		</table>
	</t:formvalid>
</body>
<script src="webpage/safe/bop/bopcommon.js"></script>
<script src="webpage/safe/bop/bopD.js"></script>
<script type="text/javascript">

</script>