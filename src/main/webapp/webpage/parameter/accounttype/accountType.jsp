<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>账户性质</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="accountTypeController.do?save">
			<input id="id" name="id" type="hidden" value="${accountTypePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							账户性质代码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="code" name="code" 
							   value="${accountTypePage.code}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							账户性质名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" ignore="ignore"
							   value="${accountTypePage.name}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>