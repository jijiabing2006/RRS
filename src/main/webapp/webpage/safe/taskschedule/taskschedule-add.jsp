<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>提取任务状态控制表</title>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="taskscheduleController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${taskschedulePage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							brca:
						</label>
					</td>
					<td class="value">
					     	 <input id="brca" name="brca" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">brca</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							executable:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="executable" type="list"
									dictTable="sf_10" dictField="code" dictText="text" defaultVal="${taskschedulePage.executable}" hasLabel="false"  title="executable"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">executable</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							importdate:
						</label>
					</td>
					<td class="value">
							   <input id="importdate" name="importdate" type="text" style="width: 150px" 
					      						class="Wdate" onClick="WdatePicker()"
								                
								               >    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">importdate</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							parentbrca:
						</label>
					</td>
					<td class="value">
					     	 <input id="parentbrca" name="parentbrca" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">parentbrca</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							taskdesc:
						</label>
					</td>
					<td class="value">
					     	 <input id="taskdesc" name="taskdesc" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">taskdesc</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							taskname:
						</label>
					</td>
					<td class="value">
					     	 <input id="taskname" name="taskname" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">taskname</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							counts:
						</label>
					</td>
					<td class="value">
					     	 <input id="counts" name="counts" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">counts</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/lzsoft/com.lzsoft/taskschedule.js"></script>		