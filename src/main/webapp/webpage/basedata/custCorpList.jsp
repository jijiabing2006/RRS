<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="custCorpList" checkbox="true" fitColumns="false" title="t_cust_corp" actionUrl="custCorpController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="客户名称中文"  field="ctnm"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户英文名"  field="ctnmen"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="公司地址"  field="address"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="客户编号"  field="csnm"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="法人标志"  field="mlpr"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="客户类型"  field="cutp"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客户类别"  field="cs"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="国民经济代码"  field="nesc"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="境内外标识"  field="islocal"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="母公司客户名称"  field="pctnm"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="母公司注册国家"  field="pctnt"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="常驻国家"  field="ctnt"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="国内注册地址"  field="regaddress"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="企业出资人经济成分"  field="attcode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="企业规模"  field="custsize"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="企业证件类型"  field="citp"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="证件有效期"  field="idexpdate" formatter="yyyy-MM-dd" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="关系人类型"  field="relatetype"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="关系人姓名"  field="relatename"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="法人代表姓名"  field="crnm"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="法人证件类型"  field="crit"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="法人证件号码"  field="crid"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="组织机构代码"  field="encode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="代码证更新日期"  field="enupdate" formatter="yyyy-MM-dd" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="组织机构类型"  field="entyp"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="营业执照"  field="buslicense"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="营业执照有效期"  field="orgexpdate" formatter="yyyy-MM-dd" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="经营范围"  field="busscope"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="成立日期"  field="regdate" formatter="yyyy-MM-dd" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="所属行业"  field="industrycode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="企业登记注册类型"  field="regtype"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="贷款卡编号"  field="lncardid"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="上市公司标志"  field="listcompanyflag"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否为银行股东"  field="isbanksh"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="股东客户代码"  field="shcustid"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="股东名称"  field="shname"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="持有股份份额"  field="shares"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="投资人国别"  field="invcountrycode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="集团客户标志"  field="pcutp"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="集团客户编号"  field="parn"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="集团客户关联关系类型"  field="prepr"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="外部信用评级"  field="excreditass"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="风险等级"  field="rishrank"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="国税证号"  field="nationaltaxlic"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="地税证号"  field="localtaxlic"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="联系人"  field="contactor"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="单位电话"  field="phone"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="传真"  field="fax"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="手机"  field="sms"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="电子邮件"  field="email"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="通讯地址"  field="caddress"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否特殊经济区内企业"  field="istaxfree"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="特殊经济区类型"  field="taxfreecode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="邮政编码"  field="zipcode"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否为黑名单客户"  field="blcl"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="黑名单原因"  field="cklr"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="关联人名称"  field="afpr"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="注册资本币种"  field="recpccy"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="注册资本金额"  field="rgcp"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="实收资本金额"  field="actcp"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="总资产"  field="totalassets"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="年营业收入"  field="annrevenue"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="员工人数"  field="empnumber"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="上级机构代码"  field="parentbrca"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="本机构代码"  field="brca"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="地区编号"  field="regc"    queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="开户日期"  field="opdt" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="营业日期"  field="importdate" formatter="yyyy-MM-dd"   queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="custCorpController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="custCorpController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="custCorpController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="custCorpController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="common.view"  icon="icon-search" url="custCorpController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/basedata/custCorpList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#custCorpListtb").find("input[name='idexpdate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#custCorpListtb").find("input[name='idexpdate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#custCorpListtb").find("input[name='enupdate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#custCorpListtb").find("input[name='enupdate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#custCorpListtb").find("input[name='orgexpdate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#custCorpListtb").find("input[name='orgexpdate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#custCorpListtb").find("input[name='regdate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#custCorpListtb").find("input[name='regdate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#custCorpListtb").find("input[name='opdt_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#custCorpListtb").find("input[name='opdt_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#custCorpListtb").find("input[name='importdate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#custCorpListtb").find("input[name='importdate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'custCorpController.do?upload', "custCorpList");
}

//导出
function ExportXls() {
	JeecgExcelExport("custCorpController.do?exportXls","custCorpList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("custCorpController.do?exportXlsByT","custCorpList");
}
 </script>