<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="bopPList" checkbox="true" fitColumns="true" title="对外付款/承兑通知书-管理信息" actionUrl="bopPController.do?datagrid" 
     idField="id" fit="true" queryMode="group" pageSize="30" sortName="importdate" sortOrder="desc" autoLoadData="true"  >
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  ></t:dgCol>
    <t:dgCol title="r.actiontype"  field="actiontype"    queryMode="group" dictionary="actiontype" ></t:dgCol>   <t:dgCol title="修改删除原因"  field="actiondesc"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="申报号码"  field="rptno"    queryMode="group"  ></t:dgCol>
   <t:dgCol title="合同号"  field="contrno"    queryMode="group"  ></t:dgCol>
   <t:dgCol title="发票号"  field="invoino"    queryMode="group"  ></t:dgCol>
   <t:dgCol title="提运单号"  field="billno"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="合同金额"  field="contamt"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="联系人"  field="crtuser"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="联系人电话"  field="inptelc"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="申报日期"  field="rptdate" formatter="yyyy-MM-dd"   queryMode="group"  ></t:dgCol>
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
   <t:dgDelOpt operationCode="del" title="common.delete" exp="isvalidation#eq#0" url="bopPController.do?doDel&id={id}" />
   <t:dgToolBar operationCode="add" title="common.add" icon="icon-add" url="bopPController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar operationCode="edit" title="common.edit" icon="icon-edit" url="bopPController.do?goUpdate" width="1036"  funname="update"></t:dgToolBar>
   <t:dgToolBar operationCode="detail" title="common.view"  icon="icon-search" url="bopPController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar operationCode="import"  title="common.import" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar operationCode="export" title="common.export" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar  operationCode="batchApprove" title="r.batch.approve" icon="icon-ok" url="bopController.do?doBatchApprove&type=P" funname="approveALLSelect"></t:dgToolBar>
    <t:dgToolBar  operationCode="batchDel" title="common.batch.delete"  icon="icon-remove"  url="bopPController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/safe/bop/bopPList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#bopPListtb").find("input[name='rptdate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#bopPListtb").find("input[name='rptdate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#bopPListtb").find("input[name='importdate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#bopPListtb").find("input[name='importdate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'bopPController.do?upload', "bopPList");
}

//导出
function ExportXls() {
	JeecgExcelExport("bopPController.do?exportXls","bopPList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("bopPController.do?exportXlsByT","bopPList");
}function approveCancel(id) {
	createwindow('审核重置', 'bopController.do?doCancelAuthor&id=' + id+'&type=P');
}
 </script>