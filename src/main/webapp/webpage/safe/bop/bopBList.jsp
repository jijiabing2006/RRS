<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="bopBList" checkbox="true" fitColumns="true" title="境外汇款申请书基础信息" actionUrl="bopBController.do?datagrid" 
      idField="id" fit="true" queryMode="group" pageSize="30" sortName="importdate" sortOrder="desc" autoLoadData="true"  >
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  ></t:dgCol>
    <t:dgCol title="r.actiontype"  field="actiontype"    queryMode="group" dictionary="actiontype" ></t:dgCol>
   <t:dgCol title="r.rptno"  field="rptno"   query="true" queryMode="single"  ></t:dgCol>
      <t:dgCol title="r.buscode"  field="buscode"    queryMode="group"  ></t:dgCol>
      <t:dgCol title="r.account.no"  field="fcyacc"   query="true" queryMode="single"  ></t:dgCol>
   <t:dgCol title="汇款人类型"  field="custype"  hidden="true"  queryMode="group" dictionary="custype" ></t:dgCol>
   <t:dgCol title="个人身份证件号码"  field="idcode"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="r.encode"  field="custcod"  hidden="true" query="true" queryMode="single"  ></t:dgCol>
   <t:dgCol title="r.enname"  field="custnm"  hidden="true"  queryMode="single"  ></t:dgCol>
   <t:dgCol title="收款人名称"  field="oppuser"  hidden="true"  queryMode="group"  ></t:dgCol>
  <t:dgCol title="r.currency.code"  field="txccy"   ></t:dgCol>
   <t:dgCol title="收入款金额"  field="txamt"    queryMode="group"  ></t:dgCol>
   <t:dgCol title="结算方式"  field="method"  hidden="true"  queryMode="group" dictionary="s_p_bopmethod where dir='1',code,name"   ></t:dgCol>
   
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
   <t:dgFunOpt operationCode="goDdata" title="申报信息" exp="isedit#eq#1&&isvalidation#eq#1"  funname="goDeclaredata(id)"  />
   <t:dgFunOpt operationCode="goMdata" title="管理信息" exp="isedit#eq#1&&isvalidation#eq#1"  funname="goControldata(id)"  />
   <t:dgDelOpt operationCode="del" title="common.delete" exp="isvalidation#eq#0" url="bopBController.do?doDel&id={id}" />
   <t:dgToolBar operationCode="add" title="common.add" icon="icon-add" url="bopBController.do?goAdd" width="1036" funname="add"></t:dgToolBar>
   <t:dgToolBar operationCode="edit" title="common.edit" icon="icon-edit" url="bopBController.do?goUpdate" width="1036"  funname="update"></t:dgToolBar>
   <t:dgToolBar operationCode="detail" title="common.view"  icon="icon-search" url="bopBController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar operationCode="export" title="common.export" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar  operationCode="batchApprove" title="r.batch.approve" icon="icon-ok" url="bopController.do?doBatchApprove&type=B" funname="approveALLSelect"></t:dgToolBar>
    <t:dgToolBar  operationCode="batchDel" title="common.batch.delete"  icon="icon-remove"  url="bopBController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/safe/bop//bopBList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#bopBListtb").find("input[name='importdate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#bopBListtb").find("input[name='importdate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'bopBController.do?upload', "bopBList");
}

//导出
function ExportXls() {  
	JeecgExcelExport("bopBController.do?exportXls","bopBList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("bopBController.do?exportXlsByT","bopBList");
}
function approveCancel(id) {
	createwindow('审核重置', 'bopController.do?doCancelAuthor&id=' + id+'&type=B');
}
function goDeclaredata(id) {
	createwindow('申报信息', 'bopController.do?goDeclaredata&id=' + id+'&type=B',1036,300);
} 
function goControldata(id) {
	createwindow('管理信息', 'bopController.do?goControldata&id=' + id+'&type=B',1036,200);
}
 </script>