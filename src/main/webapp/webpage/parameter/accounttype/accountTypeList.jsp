<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript" charset="utf-8">
	/*
	 *	excel导出
	 */
	function accountTypeListExportXls() {
		JeecgExcelExport("accountTypeController.do?exportXls","accountTypeList");
	}
	function accountTypeListImportXls() {
		openuploadwin('Excel导入', 'accountTypeController.do?upload', "accountTypeList");
	}
	
</script>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="accountTypeList" title="账户性质" actionUrl="accountTypeController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="账户性质代码" field="code" ></t:dgCol>
   <t:dgCol title="账户性质名称" field="name" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="accountTypeController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="accountTypeController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="accountTypeController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="common.view"  icon="icon-search" url="accountTypeController.do?addorupdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导出Excel" icon="icon-search" onclick="accountTypeListExportXls();"></t:dgToolBar>
   <t:dgToolBar title="导入Excel" icon="icon-search" onclick="accountTypeListImportXls()"></t:dgToolBar>
   
  </t:datagrid>
  </div>
 </div>