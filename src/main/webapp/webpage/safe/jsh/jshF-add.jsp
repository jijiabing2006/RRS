<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>购汇管理信息</title>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="jshFController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${jshFPage.id }">
					<input id="buscode" name="buscode" type="hidden" value="${jshFPage.buscode }">
					<input id="cap" name="cap" type="hidden" value="${jshFPage.cap }">
					<input id="brca" name="brca" type="hidden" value="${jshFPage.brca }">
					<input id="parentbrca" name="parentbrca" type="hidden" value="${jshFPage.parentbrca }">
					<input id="isdel" name="isdel" type="hidden" value="${jshFPage.isdel }">
					<input id="isedit" name="isedit" type="hidden" value="${jshFPage.isedit }">
					<input id="isexport" name="isexport" type="hidden" value="${jshFPage.isexport }">
					<input id="ishandadd" name="ishandadd" type="hidden" value="${jshFPage.ishandadd }">
					<input id="isvalidation" name="isvalidation" type="hidden" value="${jshFPage.isvalidation }">
					<input id="filename" name="filename" type="hidden" value="${jshFPage.filename }">
					<input id="isinsafe" name="isinsafe" type="hidden" value="${jshFPage.isinsafe }">
					<input id="tfilename" name="tfilename" type="hidden" value="${jshFPage.tfilename }">
					<input id="remark" name="remark" type="hidden" value="${jshFPage.remark }">
		<table  cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="left">
						<label class="Validform_label">
							操作类型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="actiontype" type="list"
									typeGroupCode="actiontype" defaultVal="${jshFPage.actiontype}" hasLabel="false"  title="操作类型"></t:dictSelect>     
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
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申报号码</label>
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
							交易编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="txcode" name="txcode" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">交易编码</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							结汇用途:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="usetype" type="list"
									dictTable="s_p_jshpurpose" dictField="code" dictText="text" defaultVal="${jshFPage.usetype}" hasLabel="false"  title="结汇用途"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">结汇用途</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							结汇详细用途:
						</label>
					</td>
					<td class="value">
					     	 <input id="usedetail" name="usedetail" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">结汇详细用途</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							填报人:
						</label>
					</td>
					<td class="value">
					     	 <input id="crtuser" name="crtuser" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">填报人</label>
						</td>
					</tr>
				<tr>
					<td align="left">
						<label class="Validform_label">
							填报人电话:
						</label>
					</td>
					<td class="value">
					     	 <input id="inptelc" name="inptelc" type="text"  class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">填报人电话</label>
						</td>
					<td align="left">
						<label class="Validform_label">
							申报日期:
						</label>
					</td>
					<td class="value">
							   <input id="rptdate" name="rptdate" type="text"  
					      						class="Wdate" onClick="WdatePicker()"
								                
								               >    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申报日期</label>
						</td>
					</tr>
				<tr>
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
  <script src = "webpage/safe/jsh/jshcommon.js"></script>		
  <script src = "webpage/safe/jsh/jshF.js"></script>	
       <script type="text/javascript">
	 $(document).ready(function(){
	 	 $("#actiontype").select2({
	 		 }).val("A").trigger("change");
	  	 });
   </script>		