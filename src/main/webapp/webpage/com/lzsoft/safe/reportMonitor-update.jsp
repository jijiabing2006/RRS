<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>报表监控</title>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="reportMonitorController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${reportMonitorPage.id }">
					<input id="createName" name="createName" type="hidden" value="${reportMonitorPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${reportMonitorPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${reportMonitorPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${reportMonitorPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${reportMonitorPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${reportMonitorPage.updateDate }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								报表名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="reportName" name="reportName" type="text" style="width: 150px" class="inputxt"  
									               datatype="*"
									                 value='${reportMonitorPage.reportName}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">报表名称</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								报表类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="reportType" name="reportType" type="text" style="width: 150px" class="inputxt"  
									               datatype="*"
									                 value='${reportMonitorPage.reportType}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">报表类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								负责人:
							</label>
						</td>
						<td class="value">
						     	 <input id="userId" name="userId" type="text" style="width: 150px" class="inputxt"  
									               datatype="*"
									                 value='${reportMonitorPage.userId}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">负责人</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								告警时间:
							</label>
						</td>
						<td class="value">
						     	 <input id="warnTime" name="warnTime" type="text" style="width: 150px" class="inputxt"  
									               datatype="*"
									                 value='${reportMonitorPage.warnTime}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">告警时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否完成:
							</label>
						</td>
						<td class="value">
						     	 <input id="isDone" name="isDone" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${reportMonitorPage.isDone}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否完成</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								上一次告警时间:
							</label>
						</td>
						<td class="value">
						     	 <input id="lastWarnTime" name="lastWarnTime" type="text" style="width: 150px" class="inputxt"  
									               
									                 value='${reportMonitorPage.lastWarnTime}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上一次告警时间</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/lzsoft/safe/reportMonitor.js"></script>		