<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="custIndvList" checkbox="true" fitColumns="false" title="t_cust_indv" actionUrl="custIndvController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="客户编号"  field="csnm"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="客户名称"  field="ctnm"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否出生在美国"  field="piabi"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="居住地国别代码"  field="resi"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="身份标识"  field="identity"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="居住所在省市"  field="pro"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="居住所在城市/区"  field="city"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="客户地址"  field="address"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="行业"  field="industrycode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="国籍"  field="ctnt"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="客户英文名"  field="ctnmen"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="出生日期"  field="dob" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否为本行员工"  field="isem"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否为股东"  field="shod"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="个人证件号码"  field="encode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="个人证件类型"  field="citp"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="性别"  field="gend"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="婚姻状况"  field="mars"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="职务"  field="pstn"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="职业"  field="job"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="客户类型"  field="cs"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="发证机关代码"  field="issa"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="w9表单信息"  field="w9fb"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="联系方式"  field="ctif"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="邮编"  field="zipcode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="个人年收入"  field="income"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否为黑名单客户"  field="lsbl"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="黑名单原因"  field="blre"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="关联人名称"  field="afpr"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="关联方关系"  field="repr"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="关联人证件类型"  field="apidt"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="关联人证件号"  field="apidn"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="关联人客户编号"  field="apcn"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="备注信息"  field="note"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="居住情况"  field="lvst"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="上一级机构代码"  field="parentbrca"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="分支机构代码"  field="brca"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="地区编号"  field="regc"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="开户日期"  field="opdt" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="民族"  field="nation"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="营业日期"  field="importdate" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="custIndvController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="custIndvController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="custIndvController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="custIndvController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="common.view"  icon="icon-search" url="custIndvController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/basedata/custIndvList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#custIndvListtb").find("input[name='dob_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#custIndvListtb").find("input[name='dob_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#custIndvListtb").find("input[name='opdt_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#custIndvListtb").find("input[name='opdt_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#custIndvListtb").find("input[name='importdate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#custIndvListtb").find("input[name='importdate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'custIndvController.do?upload', "custIndvList");
}

//导出
function ExportXls() {
	JeecgExcelExport("custIndvController.do?exportXls","custIndvList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("custIndvController.do?exportXlsByT","custIndvList");
}
 </script>