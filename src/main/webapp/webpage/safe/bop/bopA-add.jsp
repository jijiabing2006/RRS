<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>涉外收入申报单基础信息</title>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="bopAController.do?doAdd" tiptype="1"
beforeSubmit="check(curform,'A');">
					<input id="id" name="id" type="hidden" value="${bopAPage.id }">
					<input id="brca" name="brca" type="hidden" value="${bopAPage.brca }">
				    <input id="rptno" name="rptno" type="hidden" value="${bopAPage.rptno }">
					<input id="isdel" name="isdel" type="hidden" value="${bopAPage.isdel }">
					<input id="isedit" name="isedit" type="hidden" value="${bopAPage.isedit }">
					<input id="isexport" name="isexport" type="hidden" value="${bopAPage.isexport }">
					<input id="ishandadd" name="ishandadd" type="hidden" value="${bopAPage.ishandadd }">
					<input id="isvalidation" name="isvalidation" type="hidden" value="${bopAPage.isvalidation }">
					<input id="filename" name="filename" type="hidden" value="${bopAPage.filename }">
					<input id="isinsafe" name="isinsafe" type="hidden" value="${bopAPage.isinsafe }">
					<input id="tfilename" name="tfilename" type="hidden" value="${bopAPage.tfilename }">
					<input id="remark" name="remark" type="hidden" value="${bopAPage.remark }">
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="left">
						<label class="Validform_label">
							操作类型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="actiontype" type="list"
									typeGroupCode="actiontype" defaultVal="${bopAPage.actiontype}" hasLabel="false"  title="操作类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">操作类型</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							修改删除原因:
						</label>
					</td>
					<td class="value">
					     	 <input id="actiondesc" name="actiondesc" type="text"  class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">修改删除原因</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							收款人类型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="custype" type="list"
									typeGroupCode="custype" defaultVal="${bopAPage.custype}" hasLabel="false"  title="收款人类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收款人类型</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							个人身份证件号码:
						</label>
					</td>
					<td class="value">
					     	 <input id="idcode" name="idcode" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">个人身份证件号码</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							组织机构代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="custcod" name="custcod" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">组织机构代码</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							收款人名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="custnm" name="custnm" type="text"  class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收款人名称</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							付款人名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="oppuser" name="oppuser" type="text"  class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">付款人名称</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							收入款币种:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="txccy" type="list"
									dictTable="normal_currency" dictField="currency" dictText="text" defaultVal="${bopAPage.txccy}" hasLabel="false"  title="收入款币种"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收入款币种</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							收入款金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="txamt" name="txamt" type="text"  class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收入款金额</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							结汇汇率:
						</label>
					</td>
					<td class="value">
					     	 <input id="exrate" name="exrate" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">结汇汇率</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							结汇金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="lcyamt" name="lcyamt" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">结汇金额</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							人民币帐号银行卡号:
						</label>
					</td>
					<td class="value">
					     	 <input id="lcyacc" name="lcyacc" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">人民币帐号银行卡号</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							现汇金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="fcyamt" name="fcyamt" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">现汇金额</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							外汇帐号银行卡号:
						</label>
					</td>
					<td class="value">
					     	 <input id="fcyacc" name="fcyacc" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">外汇帐号银行卡号</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							其它金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="othamt" name="othamt" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">其它金额</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							其它帐号银行卡号:
						</label>
					</td>
					<td class="value">
					     	 <input id="othacc" name="othacc" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">其它帐号银行卡号</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							结算方式:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="method" type="list"
									dictTable="s_p_bopmethod " dictField="code" dictText="name" defaultVal="${bopAPage.method}" hasLabel="false"  title="结算方式"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">结算方式</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							银行业务编号:
						</label>
					</td>
					<td class="value">
					     	 <input id="buscode" name="buscode" type="text"  class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">银行业务编号</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							国内银行扣费币种:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="inchargeccy" type="list"
									dictTable="normal_currency" dictField="currency" dictText="text" defaultVal="${bopAPage.inchargeccy}" hasLabel="false"  title="国内银行扣费币种"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">国内银行扣费币种</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							国内银行扣费金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="inchargeamt" name="inchargeamt" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">国内银行扣费金额</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							国外银行扣费币种:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="outchargeccy" type="list"
									dictTable="normal_currency" dictField="currency" dictText="text" defaultVal="${bopAPage.outchargeccy}" hasLabel="false"  title="国外银行扣费币种"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">国外银行扣费币种</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							国外银行扣费金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="outchargeamt" name="outchargeamt" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">国外银行扣费金额</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							营业日期:
						</label>
					</td>
					<td class="value">
							   <input id="importdate" name="importdate" type="text"  
					      						class="Wdate" onClick="WdatePicker()"
								               datatype="*" 
								               >    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">营业日期</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/safe/bop/bopcommon.js"></script>		
  <script src = "webpage/safe/bop/bopA.js"></script>	
   <script type="text/javascript">
	 $(document).ready(function(){
	 	 $("#actiontype").select2({
	 		 }).val("A").trigger("change");
	  	 });
   </script>	
  	