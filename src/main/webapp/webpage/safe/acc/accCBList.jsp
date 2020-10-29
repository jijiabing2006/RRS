<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="accCBList" checkbox="true" fitColumns="false" title="账户收支余信息" actionUrl="accCBController.do?datagrid" 
  idField="id" fit="true" queryMode="group" pageSize="30" sortName="importdate" sortOrder="desc" autoLoadData="true">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="r.actiontype"  field="actiontype"    queryMode="group" dictionary="actiontype"></t:dgCol>
   <t:dgCol title="r.account.no"  field="accountno"   query="true" queryMode="single"  ></t:dgCol>
   <t:dgCol title="r.business.date"  field="dealdate" formatter="yyyy-MM-dd"  query="true" queryMode="group" ></t:dgCol>
   <t:dgCol title="r.currency.code"  field="currencycode"   query="true" queryMode="single" dictionary="normal_currency,currency,currency" ></t:dgCol>
      <t:dgCol title="r.lastbalance"  field="lastbalance"  hidden="false"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="r.credit.amt"  field="credit"    queryMode="group"  ></t:dgCol>
   <t:dgCol title="r.debit.amt"  field="debit"    queryMode="group"  ></t:dgCol>
   <t:dgCol title="r.account.balance"  field="balance"    queryMode="group"  ></t:dgCol>
   <t:dgCol title="r.feedback.status"  field="isinsafe"  query="true"  queryMode="single" replace="common.blank_0,r.receiptted_1,r.waitting_2,r.FERF_3" ></t:dgCol>
   <t:dgCol title="r.send.mts"  field="isexport"  exp="isvalidation#eq#1"  queryMode="group" replace="r.sent_1,r.unsend_0" ></t:dgCol>
   <t:dgCol title="r.edit.status"  field="isedit"    queryMode="group" replace="r.completed_1,r.uncompleted_0" ></t:dgCol>
   <t:dgCol title="r.verify.status"  field="isvalidation" replace="r.completed_1,r.uncompleted_0,r.verify.nopass_3"   queryMode="group"  ></t:dgCol>
   <t:dgCol title="r.balance.status"  field="state"   queryMode="group"  replace="r.isbalance_1,r.isunbalance_0,r.notsafebalance_2"></t:dgCol>
   <t:dgCol title="filename"  field="filename"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="common.operation" field="opt" width="100"></t:dgCol>
   <t:dgCol title="common.remark"  field="remark"   hidden="false" queryMode="single"  ></t:dgCol>
   <t:dgCol title="tfilename"  field="tfilename"  hidden="true"  queryMode="group"  ></t:dgCol>

   <t:dgCol title="r.safebalance"  field="safebalance"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgCol title="rptno"  field="rptno"  hidden="true"  queryMode="group"  ></t:dgCol>
   <t:dgFunOpt operationCode="approveCancel" title="r.verify.cancel" exp="isvalidation#eq#1"  funname="approveCancel(id)" />
   <t:dgDelOpt operationCode="del"  title="common.delete"  exp="isvalidation#eq#0" url="accCBController.do?doDel&id={id}" />
   <t:dgToolBar operationCode="add"  title="common.add" icon="icon-add" url="accCBController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar operationCode="edit"  title="common.edit"  icon="icon-edit" url="accCBController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar operationCode="batchDel" title="common.batch.delete"  icon="icon-remove" url="accCBController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="common.view" icon="icon-search" url="accCBController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar operationCode="export" title="common.export" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar  operationCode="batchApprove" title="r.batch.approve" icon="icon-ok" url="accController.do?doBatchApprove&type=CB" funname="approveALLSelect"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/safe/acc/accCBList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#accCBListtb").find("input[name='dealdate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#accCBListtb").find("input[name='dealdate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#accCBListtb").find("input[name='importdate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#accCBListtb").find("input[name='importdate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#currencycode,[name='currencycode']").select2();
 			$("#isinsafe,[name='isinsafe']").select2({  minimumResultsForSearch: Infinity});
 			//	$("#accCBListtb").find("select[name='currencycode']").attr("style","selectwidth:100%;").select2();
 		//	$("#accCBListtb").find("select[name='isinsafe']").attr("style","selectwidth:100%;").select2();
 		
 		
 	//	$("#accCBListtb").find("a[iconcls='icon-search']").click(function(){
 	//		if($("#accCBListtb").find("input[name='dealdate_begin']").val()&&
 	//		    $("#accCBListtb").find("input[name='dealdate_end']").val()){
 				
 	//		}
	//	}
 	//	);
 		
 
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'accCBController.do?upload', "accCBList");
}

//导出
function ExportXls() {
	JeecgExcelExport("accCBController.do?exportXls","accCBList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("accCBController.do?exportXlsByT","accCBList");
}

function approveCancel(id) {
	createwindow('审核重置', 'accController.do?doCancelAuthor&id=' + id+'&type=CB');
}
 </script>