<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="bopRList" checkbox="true" fitColumns="true" title="境内收入申报单-管理信息" actionUrl="bopRController.do?datagrid" 
     idField="id" fit="true" queryMode="group" pageSize="30" sortName="importdate" sortOrder="desc" autoLoadData="true"  >
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  ></t:dgCol>
  <t:dgCol title="r.actiontype"  field="actiontype"    queryMode="group" dictionary="actiontype" ></t:dgCol>   <t:dgCol title="修改删除原因"  field="actiondesc"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="申报号码"  field="rptno"   query="true" queryMode="single"  ></t:dgCol>
   <t:dgCol title="是否保税货物项下"  field="isref"  hidden="true"  queryMode="group" dictionary="sf_YN" ></t:dgCol>
   <t:dgCol title="境内收入类型"  field="payattr"  hidden="true"  queryMode="group" dictionary="payattr" ></t:dgCol>
   <t:dgCol title="付款类型"  field="paytype"  hidden="true"  queryMode="group" dictionary="paytype" ></t:dgCol>
   <t:dgCol title="交易编码1"  field="txcode"  hidden="true"  queryMode="group" dictionary="s_p_transcationcode where dir='1',code,text" ></t:dgCol>
   <t:dgCol title="相应金额1"  field="tc1amt"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="交易附言1"  field="txrem"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="交易编码2"  field="txcode2"  hidden="true"  queryMode="group" dictionary="s_p_transcationcode where dir='1',code,text" ></t:dgCol>
   <t:dgCol title="相应金额2"  field="tc2amt"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="交易附言2"  field="tx2rem"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="填报人"  field="crtuser"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="填报人电话"  field="inptelc"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="申报日期"  field="rptdate" formatter="yyyy-MM-dd"  query="true" queryMode="group"  ></t:dgCol>
   <t:dgCol title="外汇局批件号"  field="regno"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="buscode"  field="buscode"    queryMode="group"  ></t:dgCol>
   <t:dgCol title="资本项下 "  field="cap"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="分行代码"  field="brca"  hidden="true"  queryMode="group"  ></t:dgCol>
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
   <t:dgDelOpt operationCode="del" title="common.delete" exp="isvalidation#eq#0" url="bopRController.do?doDel&id={id}" />
   <t:dgToolBar operationCode="add" title="common.add" icon="icon-add" url="bopRController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar operationCode="edit" title="common.edit" icon="icon-edit" url="bopRController.do?goUpdate"   funname="update"></t:dgToolBar>
   <t:dgToolBar operationCode="detail" title="common.view"  icon="icon-search" url="bopRController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar operationCode="import"  title="common.import" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar operationCode="export" title="common.export" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar  operationCode="batchApprove" title="r.batch.approve" icon="icon-ok" url="bopController.do?doBatchApprove&type=R" funname="approveALLSelect"></t:dgToolBar>
    <t:dgToolBar  operationCode="batchDel" title="common.batch.delete"  icon="icon-remove"  url="bopRController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/safe/bop/bopRList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#bopRListtb").find("input[name='rptdate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#bopRListtb").find("input[name='rptdate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#bopRListtb").find("input[name='importdate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#bopRListtb").find("input[name='importdate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'bopRController.do?upload', "bopRList");
}

//导出
function ExportXls() {
	JeecgExcelExport("bopRController.do?exportXls","bopRList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("bopRController.do?exportXlsByT","bopRList");
}function approveCancel(id) {
	createwindow('审核重置', 'bopController.do?doCancelAuthor&id=' + id+'&type=R');
}
 </script>