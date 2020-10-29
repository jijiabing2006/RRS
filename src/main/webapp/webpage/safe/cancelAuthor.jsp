<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>审核操作</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" >
	<input id="id" name="id" type="hidden" value="${cancelPojo.id }">
	<input id="type" name="type" type="hidden" value="${type }">
	<input id="actionname" name="actionname" type="hidden" r value="${actionname}">
	
	<table  cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="15%" nowrap><label class="Validform_label"> 原因</label></td>
			<td class="value" width="85%"><input id="remark" class="inputxt" name="remark" value="${cancelPojo.remark}" datatype="s2-50"> <span class="Validform_checktip">原因描述范围在2~50位字符</span></td>
		</tr>
	</table>
</t:formvalid>
 <script type="text/javascript">
 $(document).ready(function(){
	        // 使用以下方法时，去掉form中属性“action"
 		    	var actionname=$("#actionname").val()+".do?cancelAuthor";
 				$("#formobj").attr("action",actionname).val();
 });
 
 </script>
</body>