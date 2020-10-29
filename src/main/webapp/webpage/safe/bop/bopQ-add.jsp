<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>bop_q</title>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="bopQController.do?doAdd" tiptype="1"
  beforeSubmit="check(curform,'Q');">
					<input id="id" name="id" type="hidden" value="${bopQPage.id }">
					<input id="buscode" name="buscode" type="hidden" value="${bopQPage.buscode }">
					<input id="cap" name="cap" type="hidden" value="${bopQPage.cap }">
					<input id="brca" name="brca" type="hidden" value="${bopQPage.brca }">
					<input id="importdate" name="importdate" type="hidden" value="${bopQPage.importdate }">
					<input id="isdel" name="isdel" type="hidden" value="${bopQPage.isdel }">
					<input id="isedit" name="isedit" type="hidden" value="${bopQPage.isedit }">
					<input id="isexport" name="isexport" type="hidden" value="${bopQPage.isexport }">
					<input id="ishandadd" name="ishandadd" type="hidden" value="${bopQPage.ishandadd }">
					<input id="isvalidation" name="isvalidation" type="hidden" value="${bopQPage.isvalidation }">
					<input id="filename" name="filename" type="hidden" value="${bopQPage.filename }">
					<input id="isinsafe" name="isinsafe" type="hidden" value="${bopQPage.isinsafe }">
					<input id="tfilename" name="tfilename" type="hidden" value="${bopQPage.tfilename }">
					<input id="remark" name="remark" type="hidden" value="${bopQPage.remark }">
		<table  cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="left">
						<label class="Validform_label">
							操作类型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="actiontype" type="list"
									typeGroupCode="actiontype" defaultVal="${bopQPage.actiontype}" hasLabel="false"  title="操作类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">操作类型</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							修改删除原因:
						</label>
					</td>
					<td class="value">
					     	 <input id="actiondesc" name="actiondesc" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">修改删除原因</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							申报号码:
						</label>
					</td>
					<td class="value">
					     	 <input id="rptno" name="rptno" type="text"  class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申报号码</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							收款人常驻国家地区代码:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="country" type="list"
									dictTable="s_p_countrycode" dictField="code" dictText="text" defaultVal="${bopQPage.country}" hasLabel="false"  title="收款人常驻国家地区代码"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收款人常驻国家地区代码</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							是否保税货物项下:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="isref" type="list"
									typeGroupCode="sf_YN" defaultVal="${bopQPage.isref}" hasLabel="false"  title="是否保税货物项下"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否保税货物项下</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							境内收入类型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="payattr" type="list"
									typeGroupCode="domesticpayattr" defaultVal="${bopQPage.payattr}" hasLabel="false"  title="境内收入类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">境内收入类型</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							付款性质:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="paytype" type="list"
									typeGroupCode="paytype" defaultVal="${bopQPage.paytype}" hasLabel="false"  title="付款性质"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">付款性质</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							交易编码1:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="txcode" type="list"
									dictTable="s_p_transcationcode where  dir='2'" dictField="code" dictText="text" defaultVal="${bopQPage.txcode}" hasLabel="false"  title="交易编码1"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">交易编码1</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							相应金额1:
						</label>
					</td>
					<td class="value">
					     	 <input id="tc1amt" name="tc1amt" type="text"  class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">相应金额1</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							交易编码2:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="txcode2" type="list"
									dictTable="s_p_transcationcode where  dir='2'" dictField="code" dictText="text" defaultVal="${bopQPage.txcode2}" hasLabel="false"  title="交易编码2"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">交易编码2</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							相应金额2:
						</label>
					</td>
					<td class="value">
					     	 <input id="tc2amt" name="tc2amt" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">相应金额2</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							合同号:
						</label>
					</td>
					<td class="value">
					     	 <input id="contrno" name="contrno" type="text"  class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同号</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							发票号:
						</label>
					</td>
					<td class="value">
					     	 <input id="invoino" name="invoino" type="text"  class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发票号</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							外汇局批件号:
						</label>
					</td>
					<td class="value">
					     	 <input id="regno" name="regno" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">外汇局批件号</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							填报人:
						</label>
					</td>
					<td class="value">
					     	 <input id="crtuser" name="crtuser" type="text"  class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">填报人</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							填报人电话:
						</label>
					</td>
					<td class="value">
					     	 <input id="inptelc" name="inptelc" type="text"  class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">填报人电话</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							申报日期:
						</label>
					</td>
					<td class="value">
							   <input id="rptdate" name="rptdate" type="text"  
					      						class="Wdate" onClick="WdatePicker()"
								               datatype="*" 
								               >    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申报日期</label>
						</td>
				<td align="left">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/safe/bop/bopQ.js"></script>		