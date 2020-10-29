<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding:1px;">
		<t:datagrid name="fRReportList" checkbox="true" fitColumns="true"
			title="${syskind=='PBOC'?'人民银行':syskind=='1104'?'银监会':''}-${statfreq=='monthly'?'月报':statfreq=='queraterly'?'季报':statfreq=='yealy'?'年报':statfreq=='daily'?'日报':statfreq=='ten-day'?'旬报':''}报表"
			actionUrl="frController.do?datagrid&syskind=${syskind}&statfreq=${statfreq}"
			idField="id" fit="true" queryMode="group" sortName="reportname">
			<t:dgCol title="id" field="id" hidden="true" queryMode="group"
				width="120"></t:dgCol>
			<t:dgCol title="报表表" field="reportname" query="true"
				queryMode="single" width="120"></t:dgCol>
			<t:dgCol title="全称" field="fullname" queryMode="group" width="120"></t:dgCol>
			<t:dgCol title="频度" field="statfreq"
				replace="日报_daily,旬报_ten-day,月报_monthly,季报_quarterly,半年报_semiyearly,年报_yearly"
				queryMode="group" width="120"></t:dgCol>
			<t:dgCol title="模板名称" field="templatename" hidden="true"
				queryMode="group" width="120"></t:dgCol>
			<t:dgCol title="监管机构" field="syskind"
				replace="人民银行_PBOC,银监会_CBRC,外管_SAFE,内部_inner" queryMode="group"
				width="120"></t:dgCol>
			<t:dgCol title=" 批次" field="batch" query="true" queryMode="single"
				dictionary="rp_batch" width="120"></t:dgCol>
			<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
			<t:dgFunOpt  title="查看"
				funname="executebytab(reportname)" />
		</t:datagrid>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(
			function() {
				//给时间控件加上样式
				$("select[name='batch']").attr("onchange",
						"fRReportListsearch()").select2({  minimumResultsForSearch: Infinity});
			});

	function executebytab(reportname) {
		addOneTab(
				"${syskind}-" + reportname,
				"ReportServer?reportlet=com.finereport.service.impl.ReporletFromDB&isIframe&op=write&rpname="
						+ reportname
						+ "&clickFunctionId=8a00e49c50c5d2340150c6fc2da80121");
	}
</script>