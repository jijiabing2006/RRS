<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="bankinfoList" checkbox="true" fitColumns="false" title="r_core_bankinfo" actionUrl="bankinfoController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="金融机构代码"  field="branchcode"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="14位金融机构代码"  field="bankcodeC14"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="15位AML代码"  field="amlbankcode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="中文简称"  field="shortcnname"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="英文简称"  field="shortenname"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="中文全称"  field="fullcnname"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="英文全称"  field="fullenname"  hidden="true" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="机构代码"  field="brca"   query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="上一级机构代码"  field="parentbrca"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="行政区划代码"  field="areacode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="市级代码"  field="citycode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="市级名称"  field="cityname"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="省级代码"  field="provincecode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="省级名称"  field="provincename"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="地址"  field="address"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="bankinfoController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="bankinfoController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="bankinfoController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="bankinfoController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="common.view"  icon="icon-search" url="bankinfoController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/basedata/bankinfoList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'bankinfoController.do?upload', "bankinfoList");
}

//导出
function ExportXls() {
	JeecgExcelExport("bankinfoController.do?exportXls","bankinfoList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("bankinfoController.do?exportXlsByT","bankinfoList");
}
 </script>