<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="mailReceipterList" checkbox="true" fitColumns="false" title="r_c_mail_receipter" actionUrl="mailReceipterController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="username"  field="username"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="type"  field="type"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="state"  field="state"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="brca"  field="brca"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="msend"  field="msend"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="userid"  field="userid"    queryMode="group" dictionary="t_s_base_user,id,realname" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="mailReceipterController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="mailReceipterController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="mailReceipterController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="mailReceipterController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="common.view"  icon="icon-search" url="mailReceipterController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/basedata/mailReceipterList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'mailReceipterController.do?upload', "mailReceipterList");
}

//导出
function ExportXls() {
	JeecgExcelExport("mailReceipterController.do?exportXls","mailReceipterList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("mailReceipterController.do?exportXlsByT","mailReceipterList");
}
 </script>