<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>r_c_taskschedule</title>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="taskscheduleController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${taskschedulePage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								分行代码:
							</label>
						</td>
						<td class="value">
						     	 <input id="brca" name="brca" type="text" style="width: 150px" class="inputxt"  
									               
									                 value="${taskschedulePage.brca}" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">分行代码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								可执行:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="executable" type="list"
										typeGroupCode="sf_tf" defaultVal="${taskschedulePage.executable}" hasLabel="false"  title="可执行"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">可执行</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								营业日期:
							</label>
						</td>
						<td class="value">
									  <input id="importdate" name="importdate" type="text" style="width: 150px" 
						      						class="Wdate" onClick="WdatePicker()"
									                
						      						 value='<fmt:formatDate value="${taskschedulePage.importdate}"  type="date" pattern="yyyy-MM-dd"/>'>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">营业日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								上级分行:
							</label>
						</td>
						<td class="value">
						     	 <input id="parentbrca" name="parentbrca" type="text" style="width: 150px" class="inputxt"  
									               
									                 value="${taskschedulePage.parentbrca}" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上级分行</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								任务描述:
							</label>
						</td>
						<td class="value">
						     	 <input id="taskdesc" name="taskdesc" type="text" style="width: 150px" class="inputxt"  
									               
									                 value="${taskschedulePage.taskdesc}" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">任务描述</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								任务名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="taskname" name="taskname" type="text" style="width: 150px" class="inputxt"  
									               
									                 value="${taskschedulePage.taskname}" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">任务名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								计数:
							</label>
						</td>
						<td class="value">
						     	 <input id="counts" name="counts" type="text" style="width: 150px" class="inputxt"  
									               datatype="*"
									                 value="${taskschedulePage.counts}" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">计数</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
