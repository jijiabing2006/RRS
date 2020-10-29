<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
  <head>
   <script>
    function complete(id){
    	 $.ajax({
 			async : false,
 			cache : false,
 			type : 'POST',
 			contentType : 'application/json', 
 			dataType:"json",
 			url:"reportMonitorController.do?complete&id="+id,
 			success : function(data) {
 				alert(data.msg);
 				window.location.reload();
 			}
 		});
    } 
    $(document).ready(function(){
		//给时间控件加上样式
			$("#reportMonitorListtb").find("input[name='warnTime']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
			$("#reportMonitorListtb").find("input[name='lastWarnTime']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
   });
   </script>
  </head>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="reportMonitorList" checkbox="true" fitColumns="true" title="报表监控" actionUrl="reportMonitorController.do?datagrid" 
  idField="id" fit="true" autoLoadData="true" queryMode="group" pageSize="30" sortOrder="desc">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="负责人" field="userId" query="true" queryMode="single"></t:dgCol>
   <t:dgCol title="负责人姓名" field="userName" query="true" queryMode="single"></t:dgCol>
   <t:dgCol title="报表类型" field="reportType" query="true" queryMode="single" dictionary="reporttype"></t:dgCol>
   <t:dgCol title="报表名称" field="reportName" query="true" queryMode="single"></t:dgCol>
   <t:dgCol title="告警时间" field="warnTime" query="true" queryMode="single" formatter="yyyy-MM-dd"></t:dgCol>
   <t:dgCol title="上一次告警时间"  field="lastWarnTime"  query="true" formatter="yyyy-MM-dd"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt title="完成" funname="complete(id)" />
   <t:dgDelOpt title="删除" url="reportMonitorController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="reportMonitorController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit"  url="reportMonitorController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="reportMonitorController.do?addorupdate" funname="detail" ></t:dgToolBar>
   <t:dgToolBar operationCode="export" title="common.export" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
  //导入
function ImportXls() {
	openuploadwin('Excel导入', 'reportMonitorController.do?upload', "reportMonitorList");
}

//导出
function ExportXls() {
	JeecgExcelExport("reportMonitorController.do?exportXls","reportMonitorList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("reportMonitorController.do?exportXlsByT","reportMonitorList");
}
</script>