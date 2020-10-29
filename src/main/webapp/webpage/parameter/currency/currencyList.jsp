<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript" charset="utf-8">
	/*
	 *	excel导出
	 */
	function currencyListExportXls() {
		JeecgExcelExport("currencyController.do?exportXls","currencyList");
	}
	function currencyListImportXls() {
		openuploadwin('Excel导入', 'currencyController.do?upload', "currencyList");
	}
	
</script>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="currencyList" title="币种代码表" actionUrl="currencyController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="common.code"   langArg="r.currency.code"  field="currency" ></t:dgCol>
   <t:dgCol title="币种名称" field="name" ></t:dgCol>
   <t:dgCol title="数字代码" field="number" ></t:dgCol>
   <t:dgCol title="common.operation" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="common.delete" url="currencyController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="currencyController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="currencyController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="common.view"  icon="icon-search" url="currencyController.do?addorupdate" funname="detail"></t:dgToolBar>
   	<t:dgToolBar title="导出Excel" icon="icon-search" onclick="currencyListExportXls();"></t:dgToolBar>
   	<t:dgToolBar title="导入Excel" icon="icon-search" onclick="currencyListImportXls()"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>