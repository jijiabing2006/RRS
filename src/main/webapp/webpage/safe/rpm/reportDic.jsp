<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>报表字典</title>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="reportDicController.do?save">
			<input id="id" name="id" type="hidden" value="${reportDicPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							报表名称:
						</label>
					</td>
					<td class="value">
						   <input class="inputxt" id="reportName" name="reportName" 
							   value="${reportDicPage.reportName}" datatype="*">
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
					 	<t:dictSelect field="reportType" type="list"  datatype="*"
									typeGroupCode="reportType" defaultVal="${reportDicPage.reportType}" 
									hasLabel="false"  title="报表类型"></t:dictSelect> 
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
<script src = "webpage/safe/rpm/reportDicList.js"></script>