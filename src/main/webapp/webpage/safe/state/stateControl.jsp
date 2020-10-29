<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,select2"></t:base>
<t:tabs id="tt" iframe="false" tabPosition="bottom" >
	<t:tab href="stateController.do?execute&turn=dataSummary"   title="数据信息导入概述" id="datasummary"></t:tab>
	<t:tab href="stateController.do?execute&turn=accSummary"  title="外汇账号信息概述" id="accsummary"></t:tab>
	<t:tab href="stateController.do?execute&turn=bopSummary"   title="国际收支信息概述" id="bopsummary"></t:tab>
	<t:tab href="stateController.do?execute&turn=jshSummary"  title="账号内结售汇概述" id="jshsummary"></t:tab>
</t:tabs>

