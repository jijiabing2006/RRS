<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<link rel="stylesheet" href="plug-in/easyui/themes/metro/main.css" type="text/css"></link>
<script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.1.4.4.js"></script>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding:1px;">
		<t:datagrid name="fList" title="读取反馈"
			actionUrl="accController.do?findFeedback" idField="id" fit="true"
			queryMode="group" pageSize="30" sortName="fbh.importdate"
			sortOrder="desc" autoLoadData="true" >
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="r.errfield" field="errfield"  query="false"></t:dgCol>
			<t:dgCol title="r.errfield.cn" field="errfieldcn" ></t:dgCol>
			<t:dgCol title="r.errdesc" field="errdesc" ></t:dgCol>
			<t:dgCol title="r.rptno" field="rptno" ></t:dgCol>
			<t:dgCol title="r.filename" field="fbh_filename" ></t:dgCol>
			<t:dgCol title="r.tfilename" hidden="true" field="fbh_tfilename" ></t:dgCol>
			<t:dgCol title="r.feedback.status" field="processed"  query="true"  queryMode="single" replace="r.resolved_1,r.unresolve_0"></t:dgCol>
			<t:dgCol title="r.reportdate" field="fbh_importdate"
				formatter="yyyy-MM-dd" query="true" queryMode="single"></t:dgCol>
			
			<t:dgCol title="common.operation" field="opt"></t:dgCol>
			   <t:dgFunOpt  title="common.view"  funname="viewacc(fbh_filename)" operationCode="view" />
			<t:dgDelOpt url="feedbackerrorinfoController.do?doDel&id={id}" title="common.delete"></t:dgDelOpt>
			
		</t:datagrid>
	</div>

</div>
<script type="text/javascript">
	$(function() {
		//给时间控件加上样式
		$("#fListtb").find("input[name='fbh.importdate']").attr("class", "Wdate")
				.click(function() {
					WdatePicker({
						dateFmt : 'yyyy-MM-dd'
					});
				});
		$("#processed,[name='processed']").select2({  minimumResultsForSearch: Infinity});
		//alert($("#fListtb").find("a[iconcls='icon-search']").text());
		//	$("#fListtb").find("a[iconcls='icon-search']").text("读取反馈");
		//	$("#fListtb").find("a[iconcls='icon-search']").click(function(){
		//		$("#fListtb").find("a[iconcls='icon-reload']").hide();
		//		  });

		//	alert($("#fListtb").find("a[iconcls='icon-search']").text());
		var s = "<a href='#'  class='button' style='background:url(plug-in/easyui/themes/icons/download_16.png) no-repeat; background-position:5px;text-indent:10px;' type='button' onclick='readFeedback();'>读取反馈</a>";
		$("#fListtb").find("a[iconcls='icon-search']").before(s);
		  $(".pagination-page-list").select2({
			    minimumResultsForSearch: Infinity,
			    theme: "bootstrap"

			  }).attr("style","width:auto;");

	});

	//导出
	function readFeedback() {
		var d = $("#fListtb").find("input[name='fbh.importdate']").val();
		if (d == "" || d == undefined) {
			tip("请选择上报时系统日期");
			return false;
		}

		doSubmitNunTable("accController.do?readFeedback&date=" + d, "formobj");
	}
	function viewacc(filename) {
	   var s=filename.substr(0,5);
	   var subtitle="账户开关户信息";
	   if(s=="ACCCB"){
		   subtitle="账户收支余信息";
	   }
//	addOneTab(subtitle, "accController.do?goaccupdate&type="+s+"&");
 //   update(subtitle,"accController.do?goaccupdate&load=detail","fList");
    detail(subtitle,"accController.do?goaccupdate","fList");
	}
</script>