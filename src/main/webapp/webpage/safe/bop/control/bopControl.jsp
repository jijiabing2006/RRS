<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,select2"></t:base>
<t:tabs id="tt" iframe="false" tabPosition="top">
	<t:tab href="bopController.do?execute&turn=bopExtract" icon="icon-share" title="r.data.extract" id="bopextract"></t:tab>
	<t:tab href="bopController.do?execute&turn=bopSendMTS" icon="icon-sendout" title="r.send.mts" id="bopreport"></t:tab>
	<t:tab href="bopController.do?execute&turn=bopFeedback" icon="icon-return24" title="r.feedback.get" id="bopfeedback"></t:tab>
	<%--t:tab href="bopController.do?select" icon="icon-search" title="下拉联动" id="autoSelect"></t:tab>--%>
</t:tabs>
<script type="text/javascript" >
$(function(){

	$('#tt div .tabs-wrap').addClass('tabs-pill');
});
</script>
