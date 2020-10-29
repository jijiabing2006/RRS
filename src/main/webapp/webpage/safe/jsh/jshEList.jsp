<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="jshEList" checkbox="true" fitColumns="true" title="购汇基础信息" actionUrl="jshEController.do?datagrid" 
 idField="id" fit="true" queryMode="group" pageSize="30" sortName="importdate" sortOrder="desc" autoLoadData="true"  >
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="r.actiontype"  field="actiontype"    queryMode="group" dictionary="actiontype" ></t:dgCol>
   <t:dgCol title="r.rptno"  field="rptno"   query="true" queryMode="single"  ></t:dgCol>
   <t:dgCol title="r.buscode"  field="buscode"    queryMode="group"  ></t:dgCol>
   <t:dgCol title="购汇申请人名称"  field="custnm"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="人民币账户账号"  field="lcyacc"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="外汇账号"  field="fcyacc"  query="true" queryMode="single"   ></t:dgCol>
   <t:dgCol title="购汇金额"  field="lcyamt"    queryMode="group"  ></t:dgCol>
   <t:dgCol title="购汇币别"  field="lcyccy"    queryMode="group" dictionary="normal_currency,currency,text" ></t:dgCol>
   <t:dgCol title="汇率"  field="exrate"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="r.importdate"  field="importdate" hidden="false" formatter="yyyy-MM-dd" query="true" queryMode="group" ></t:dgCol>
   <t:dgCol title="r.feedback.status"  field="isinsafe"    queryMode="group" replace="common.blank_0,r.receiptted_1,r.waitting_2,r.FERF_3,r.precheck.error_4" ></t:dgCol>
   <t:dgCol title="r.send.mts"  field="isexport" exp="isvalidation#eq#1"   queryMode="group" replace="r.sent_1,r.unsend_0"></t:dgCol>
   <t:dgCol title="r.edit.status"  field="isedit"    queryMode="group" replace="r.completed_1,r.uncompleted_0"></t:dgCol>
   <t:dgCol title="r.verify.status"  field="isvalidation"    queryMode="group" replace="r.completed_1,r.uncompleted_0,r.verify.nopass_3"></t:dgCol>
   <t:dgCol title="filename"  field="filename"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="common.operation" field="opt" width="100"></t:dgCol>
   <t:dgCol title="common.remark"  field="remark"   hidden="false" queryMode="single"  ></t:dgCol>
   <t:dgCol title="tfilename"  field="tfilename"  hidden="true"  queryMode="group"  ></t:dgCol>
 
    <t:dgFunOpt operationCode="approveCancel" title="r.verify.cancel" exp="isedit#eq#1&&isvalidation#eq#1"  funname="approveCancel(id)" />
     <t:dgFunOpt operationCode="goMdata" title="管理信息" exp="isedit#eq#1&&isvalidation#eq#1"  funname="goMdata(id)"  />
   <t:dgDelOpt operationCode="del" title="common.delete" exp="isvalidation#eq#0" url="jshEController.do?doDel&id={id}" />
   <t:dgToolBar operationCode="add" title="common.add" icon="icon-add" url="jshEController.do?goAdd"  width="810"  funname="add"></t:dgToolBar>
   <t:dgToolBar operationCode="edit" title="common.edit" icon="icon-edit" url="jshEController.do?goUpdate" width="810"  funname="update"></t:dgToolBar>
   <t:dgToolBar operationCode="detail" title="common.view"  icon="icon-search" url="jshEController.do?goUpdate"   width="810"  funname="detail"></t:dgToolBar>
   <t:dgToolBar operationCode="import"  title="common.import" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar operationCode="export" title="common.export" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar  operationCode="batchApprove" title="r.batch.approve" icon="icon-ok" url="jshController.do?doBatchApprove&type=E" funname="approveALLSelect"></t:dgToolBar>
    <t:dgToolBar  operationCode="batchDel" title="common.batch.delete"  icon="icon-remove"  url="jshEController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/safe/jsh/jshEList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#jshEListtb").find("input[name='importdate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#jshEListtb").find("input[name='importdate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'jshEController.do?upload', "jshEList");
}

//导出
function ExportXls() {
	JeecgExcelExport("jshEController.do?exportXls","jshEList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("jshEController.do?exportXlsByT","jshEList");
}
function approveCancel(id) {
	createwindow('审核重置', 'jshController.do?doCancelAuthor&id=' + id+'&type=E');
}
function goMdata(id) {
	createwindow('管理信息', 'jshController.do?goMdata&id=' + id+'&type=E');
}
 </script>