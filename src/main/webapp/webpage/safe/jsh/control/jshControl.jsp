<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,select2"></t:base>
<t:tabs id="tt" iframe="false" tabPosition="top">
	<t:tab href="jshController.do?execute&turn=jshExtract" icon="icon-share" title="r.data.extract" id="jshextract"></t:tab>
	<t:tab href="jshController.do?execute&turn=jshSendMTS" icon="icon-sendout" title="r.send.mts" id="jshreport"></t:tab>
	<t:tab href="jshController.do?execute&turn=jshFeedback" icon="icon-return24" title="r.feedback.get" id="jshfeedback"></t:tab>
	<%--t:tab href="jshController.do?select" icon="icon-search" title="下拉联动" id="autoSelect"></t:tab>--%>
</t:tabs>
<%--<script type="text/javascript" src="plug-in/tools/HoverTabs.js"></script>--%>
<script type="text/javascript">
$(function(){

	$('#tt div .tabs-wrap').addClass('tabs-pill');
});
</script>
