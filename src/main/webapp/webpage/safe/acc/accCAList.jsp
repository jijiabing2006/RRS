<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<div class="easyui-layout" fit="true" >
  <div region="center" style="padding:1px;"  >
  <t:datagrid name="accCAList" checkbox="true" fitColumns="false" title="账户开关户信息" actionUrl="accCAController.do?datagrid" 
  idField="id" fit="true" queryMode="group" pageSize="30" sortName="importdate" sortOrder="desc" autoLoadData="true"  >
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group" ></t:dgCol>
   <t:dgCol title="r.actiontype"  field="actiontype"    queryMode="group" dictionary="actiontype"></t:dgCol>
   <t:dgCol title="r.encode"  field="encode"  hidden="true"  queryMode="group" ></t:dgCol>
   <t:dgCol title="r.enname"  field="enname"    queryMode="group" ></t:dgCol>
   <t:dgCol title="r.account.no"  field="accountno"   query="true" queryMode="single" ></t:dgCol>
      <t:dgCol title="r.currency.code"  field="currencycode"   query="true" queryMode="single" dictionary="normal_currency,currency,currency" ></t:dgCol>
   <t:dgCol title="r.business.date"  field="businessdate" formatter="yyyy-MM-dd"  query="true" queryMode="group" ></t:dgCol>
   <t:dgCol title="r.importdate"  field="importdate" hidden="true" formatter="yyyy-MM-dd"   queryMode="group" ></t:dgCol>
   <t:dgCol title="r.feedback.status"  field="isinsafe" query="true"   queryMode="single" replace="common.blank_0,r.receiptted_1,r.waitting_2,r.FERF_3,r.precheck.error_4" ></t:dgCol>
   <t:dgCol title="r.send.mts"  field="isexport" exp="isvalidation#eq#1"   queryMode="group" replace="r.sent_1,r.unsend_0"></t:dgCol>
   <t:dgCol title="r.edit.status"  field="isedit"    queryMode="group" replace="r.completed_1,r.uncompleted_0"></t:dgCol>
   <t:dgCol title="r.verify.status"  field="isvalidation"    queryMode="group" replace="r.completed_1,r.uncompleted_0,r.verify.nopass_3"></t:dgCol>
   <t:dgCol title="filename"  field="filename"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="common.operation" field="opt"  ></t:dgCol>
   <t:dgCol title="common.remark"  field="remark"   hidden="false" queryMode="single"  ></t:dgCol>
   <t:dgCol title="tfilename"  field="tfilename"  hidden="true"  queryMode="group"  ></t:dgCol>
 
    <t:dgFunOpt operationCode="approveCancel" title="r.verify.cancel" exp="isedit#eq#1&&isvalidation#eq#1"  funname="approveCancel(id)"  />
   <t:dgDelOpt operationCode="del" title="common.delete" exp="isvalidation#eq#0" url="accCAController.do?doDel&id={id}" />
   <t:dgToolBar operationCode="add" title="common.add" icon="icon-add" url="accCAController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar operationCode="edit" title="common.edit" icon="icon-edit" url="accCAController.do?goUpdate" width="810"  funname="update"></t:dgToolBar>
   <t:dgToolBar operationCode="detail" title="common.view"  icon="icon-search" url="accCAController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar operationCode="export" title="common.export" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar  operationCode="batchApprove" title="r.batch.approve" icon="icon-ok" url="accController.do?doBatchApprove&type=CA" funname="approveALLSelect"></t:dgToolBar>
    <t:dgToolBar  operationCode="batchDel" title="common.batch.delete"  icon="icon-remove"  url="accCAController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/safe/acc/accCAList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#accCAListtb").find("input[name='businessdate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#accCAListtb").find("input[name='businessdate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#accCAListtb").find("input[name='importdate_begin']").attr("class","Wdate") .click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#accCAListtb").find("input[name='importdate_end']").attr("class","Wdate") .click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#currencycode,[name='currencycode']").select2();
 			$("#isinsafe,[name='isinsafe']").select2({  minimumResultsForSearch: Infinity});
 
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'accCAController.do?upload', "accCAList");
}

//导出
function ExportXls() {
	JeecgExcelExport("accCAController.do?exportXls","accCAList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("accCAController.do?exportXlsByT","accCAList");
}
function approveCancel(id) {
	createwindow('审核重置', 'accController.do?doCancelAuthor&id=' + id+'&type=CA');
}

</script>