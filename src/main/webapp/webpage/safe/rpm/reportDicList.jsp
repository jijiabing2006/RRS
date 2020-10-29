<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;" >
  <t:datagrid name="reportDicList"  checkbox="true" fitColumns="true"  title="报表字典" actionUrl="reportDicController.do?datagrid" 
       idField="id" fit="true" autoLoadData="true" queryMode="group" pageSize="30" sortOrder="desc">
   <t:dgCol title="编号" field="id" hidden="true" ></t:dgCol>
   <t:dgCol title="报表名称" field="reportName" query="true" queryMode="single" ></t:dgCol>
   <t:dgCol title="报表类型" field="reportType" query="true" queryMode="single" dictionary="reporttype"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="reportDicController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="reportDicController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="reportDicController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="reportDicController.do?addorupdate" funname="detail"></t:dgToolBar>
 <!--  <t:dgToolBar operationCode="import"  title="common.import" icon="icon-put" funname="ImportXls"></t:dgToolBar> --> 
   <t:dgToolBar operationCode="export" title="common.export" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
  <script type="text/javascript">
  //导入
function ImportXls() {
	openuploadwin('Excel导入', 'reportDicController.do?upload', "reportDicList");
}

//导出
function ExportXls() {
	JeecgExcelExport("reportDicController.do?exportXls","reportDicList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("reportDicController.do?exportXlsByT","reportDicList");
}
</script>