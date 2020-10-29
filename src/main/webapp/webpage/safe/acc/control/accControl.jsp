<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,select2"></t:base>
<t:tabs id="tt" iframe="false" tabPosition="top" >
	<%--t:tab href="accController.do?execute&turn=accTransformation" icon="icon-share" title="生成所有IFX账户变更上报信息" id="transform"></t:tab>--%>
	<t:tab href="accController.do?execute&turn=accExtract" icon="icon-share" title="r.data.extract" id="accextract"></t:tab>
	<t:tab href="accController.do?execute&turn=accSendMTS" icon="icon-sendout" title="r.send.mts" id="accreport"></t:tab>
	<t:tab href="accController.do?execute&turn=accFeedback" icon="icon-return24" title="r.feedback.get" id="accfeedback"></t:tab>
	<%--t:tab href="accController.do?select" icon="icon-search" title="下拉联动" id="autoSelect"></t:tab>--%>
</t:tabs>
<%-- <script type="text/javascript" src="plug-in/tools/HoverTabs.js"></script> --%>
<script type="text/javascript">
$(function(){

	$('#tt div .tabs-wrap').addClass('tabs-pill');
});
</script>