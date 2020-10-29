<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>购汇基础信息</title>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="jshEController.do?doAdd" tiptype="1"
		beforeSubmit="check(curform,'E');">
		<input id="id" name="id" type="hidden" value="${jshEPage.id }">
		<input id="brca" name="brca" type="hidden" value="${jshEPage.brca }">
		<input id="rptno" name="rptno" type="hidden"
			value="${jshEPage.rptno }">
		<input id="isdel" name="isdel" type="hidden"
			value="${jshEPage.isdel }">
		<input id="isedit" name="isedit" type="hidden"
			value="${jshEPage.isedit }">
		<input id="isexport" name="isexport" type="hidden"
			value="${jshEPage.isexport }">
		<input id="ishandadd" name="ishandadd" type="hidden"
			value="${jshEPage.ishandadd }">
		<input id="isvalidation" name="isvalidation" type="hidden"
			value="${jshEPage.isvalidation }">
		<input id="filename" name="filename" type="hidden"
			value="${jshEPage.filename }">
		<input id="isinsafe" name="isinsafe" type="hidden"
			value="${jshEPage.isinsafe }">
		<input id="tfilename" name="tfilename" type="hidden"
			value="${jshEPage.tfilename }">
		<input id="remark" name="remark" type="hidden"
			value="${jshEPage.remark }">
		<table  cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="left"><label class="Validform_label"> 操作类型 </label>
				</td>
				<td class="value"><t:dictSelect field="actiontype" type="list"
						typeGroupCode="actiontype" defaultVal="${jshEPage.actiontype}"
						hasLabel="false" title="操作类型" datatype="*"></t:dictSelect> <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 修改删除原因
				</label></td>
				<td class="value"><input id="actiondesc" name="actiondesc"
					type="text"  class="inputxt"
					value="${jshEPage.actiondesc}" > <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						购汇人主体类型 </label></td>
				<td class="value"><t:dictSelect field="custype" type="list"
						typeGroupCode="custype" defaultVal="${jshEPage.custype}"
						hasLabel="false" title="购汇申请人主体类型" datatype="*"></t:dictSelect> <span
					class="Validform_checktip"></span></td>

				<td align="left"><label class="Validform_label"> 银行业务编号
				</label></td>
				<td class="value"><input id="buscode" name="buscode"
					type="text"  class="inputxt" datatype="*"
					value="${jshEPage.buscode}" > <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 组织机构代码
				</label></td>
				<td class="value"><input id="custcod" name="custcod"
					type="text"  class="inputxt"
					value="${jshEPage.custcod}"  datatype="*,s9-9">
					<span class="Validform_checktip"></span></td>

				<td align="left"><label class="Validform_label">
						个人身份证件号码 </label></td>
				<td class="value"><input id="idcode" name="idcode" type="text"
					 class="inputxt" value="${jshEPage.idcode}" 
					datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						购汇申请人名称 </label></td>
				<td class="value" colspan="3"><input id="custnm" name="custnm"
					type="text" style="width: 90%" class="inputxt" datatype="*"
					value="${jshEPage.custnm}" > <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label">
						人民币账户账号 </label></td>
				<td class="value"><input id="lcyacc" name="lcyacc" type="text"
					 class="inputxt" value="${jshEPage.lcyacc}" 
					 > <span class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 购汇币别 </label>
				</td>
				<td class="value"><t:dictSelect field="lcyccy" type="list"
						dictTable="normal_currency" dictField="currency" dictText="text"
						defaultVal="${jshEPage.lcyccy}" hasLabel="false" title="购汇币别"
						datatype="*"></t:dictSelect> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>

				<td align="left"><label class="Validform_label"> 购汇金额 </label>
				</td>
				<td class="value"><input id="lcyamt" name="lcyamt" type="text"
					 class="inputxt" datatype="*,integer"
					value="${jshEPage.lcyamt}" > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label"> 汇率 </label></td>
				<td class="value"><input id="exrate" name="exrate" type="text"
					 class="inputxt" datatype="positivenum"
					value="${jshEPage.exrate}" > <span
					class="Validform_checktip"></span></td>
			</tr>
			<td colspan="4" style="border-color: #fff;  border-style: groove;  border-width: 0 2px 2px 2px;line-height:5%;"><font
				style="color:white;">.</font></td>
			<tr>
				<td align="left"><label class="Validform_label">
						外汇收款人名称 </label></td>
				<td class="value" colspan="3"><input id="oppuser"
					name="oppuser" type="text" style="width: 90%" class="inputxt"
					datatype="*" value="${jshEPage.oppuser}" > <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="left"><label class="Validform_label"> 外汇账户账号
				</label></td>
				<td class="value"><input id="fcyacc" name="fcyacc" type="text"
					 class="inputxt" datatype="*"
					value="${jshEPage.fcyacc}" > <span
					class="Validform_checktip"></span></td>
				<td align="left"><label class="Validform_label">
						外汇账户开户行 </label></td>
				<td class="value"><input id="oppbank" name="oppbank"
					type="text"  class="inputxt" datatype="*"
					value="${jshEPage.oppbank}" > <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>

				<td align="left"><label class="Validform_label"> 营业日期 </label>
				</td>
				<td class="value"><input id="importdate" name="importdate"
					type="text"  class="Wdate inputxt"
					onClick="WdatePicker()" datatype="*"
					value='<fmt:formatDate value="${jshEPage.importdate}"  type="date" pattern="yyyy-MM-dd"/>'>
					<span class="Validform_checktip"></span></td>
				<td colspan="2" style="background:white;line-height:30%;"><font
					style="color:white;">.</font></td>
			</tr>
		</table>
	</t:formvalid>
</body>
  <script src = "webpage/safe/jsh/jshcommon.js"></script>		
  <script src = "webpage/safe/jsh/jshE.js"></script>		
       <script type="text/javascript">
	 $(document).ready(function(){
	 	 $("#actiontype").select2({
	 		 }).val("A").trigger("change");
	  	 });
   </script>	