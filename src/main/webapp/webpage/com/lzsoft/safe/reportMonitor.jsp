<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>报表监控</title>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="reportMonitorController.do?save">
			<input id="id" name="id" type="hidden" value="${reportMonitorPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							报表名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="reportName" name="reportName" 
							   value="${reportMonitorPage.reportName}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							报表类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="reportType" name="reportType" 
							   value="${reportMonitorPage.reportType}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							负责人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="userId" name="userId" 
							   value="${reportMonitorPage.userId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							告警时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="warnTime" name="warnTime" 
							   value="${reportMonitorPage.warnTime}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否完成:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isDone" name="isDone" ignore="ignore"
							   value="${reportMonitorPage.isDone}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							上一次告警时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="lastWarnTime" name="lastWarnTime" ignore="ignore"
							   value="${reportMonitorPage.lastWarnTime}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>