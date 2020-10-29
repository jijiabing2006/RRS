<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>币种代码表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="currencyController.do?save">
			<input id="id" name="id" type="hidden" value="${currencyPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							币种代码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="currency" name="currency" 
							   value="${currencyPage.currency}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							币种名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" ignore="ignore"
							   value="${currencyPage.name}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							数字代码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="number" name="number" 
							   value="${currencyPage.number}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>