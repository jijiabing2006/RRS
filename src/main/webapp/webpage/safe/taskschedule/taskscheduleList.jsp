<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="taskscheduleList" checkbox="true" fitColumns="false" title="提取任务控制表" actionUrl="taskscheduleController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="分行代码"  field="brca"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="可执行"  field="executable"  hidden="true"  queryMode="group" dictionary="sf_tf" replace="common.yes_true,common.no_false" width="120"></t:dgCol>
   <t:dgCol title="营业日期"  field="importdate" formatter="yyyy-MM-dd"  query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="上级分行"  field="parentbrca"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="任务描述"  field="taskdesc"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="任务名称"  field="taskname"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="计数"  field="counts"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="taskscheduleController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="taskscheduleController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="taskscheduleController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="taskscheduleController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="taskscheduleController.do?goUpdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#taskscheduleListtb").find("input[name='importdate_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#taskscheduleListtb").find("input[name='importdate_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'taskscheduleController.do?upload', "taskscheduleList");
}

//导出
function ExportXls() {
	JeecgExcelExport("taskscheduleController.do?exportXls","taskscheduleList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("taskscheduleController.do?exportXlsByT","taskscheduleList");
}
 </script>