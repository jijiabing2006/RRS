<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="mmFixDepositList" title="存放同业定期存款信息" actionUrl="mmFixDepositController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="交易序号" field="dlno" ></t:dgCol>
   <t:dgCol title="上一级机构代码" field="parentbrca" ></t:dgCol>
   <t:dgCol title="分支机构代码" field="brcaCo" ></t:dgCol>
   <t:dgCol title="交易对手" field="csnm" ></t:dgCol>
   <t:dgCol title="交易币种" field="ccy" ></t:dgCol>
   <t:dgCol title="本金" field="pcpl" ></t:dgCol>
   <t:dgCol title="交易日期" field="deld" formatter="yyyy-MM-dd"></t:dgCol>
   <t:dgCol title="生效日期" field="stad" formatter="yyyy-MM-dd"></t:dgCol>
   <t:dgCol title="到期日" field="matd" formatter="yyyy-MM-dd"></t:dgCol>
   <t:dgCol title="账户类型" field="category" ></t:dgCol>
   <t:dgCol title="利率类型" field="intratp" ></t:dgCol>
   <t:dgCol title="计息方式" field="intbas" ></t:dgCol>
   <t:dgCol title="利率" field="intrate" ></t:dgCol>
   <t:dgCol title="利率基准" field="intkey" ></t:dgCol>
   <t:dgCol title="利率调整" field="intspr" ></t:dgCol>
   <t:dgCol title="利息计提方式" field="accpar" ></t:dgCol>
   <t:dgCol title="应计利息" field="totintamt" ></t:dgCol>
   <t:dgCol title="利率重新定价期限" field="inddued" ></t:dgCol>
   <t:dgCol title="备注" field="remarks" ></t:dgCol>
   <t:dgCol title="提款账户" field="drawacc" ></t:dgCol>
   <t:dgCol title="本金清算账户" field="priliqacct" ></t:dgCol>
   <t:dgCol title="利息清算账户" field="intliqacct" ></t:dgCol>
   <t:dgCol title="交易对手银行" field="benbk" ></t:dgCol>
   <t:dgCol title="交易对手账户" field="benacc" ></t:dgCol>
   <t:dgCol title="授信编号" field="limitref" ></t:dgCol>
   <t:dgCol title="客户编号" field="cusref" ></t:dgCol>
   <t:dgCol title="报文编号" field="advsent" ></t:dgCol>
   <t:dgCol title="SWIFTCODE" field="swiftco" ></t:dgCol>
   <t:dgCol title="利息支付币种" field="intccy" ></t:dgCol>
   <t:dgCol title="转存标识" field="aurol" ></t:dgCol>
   <t:dgCol title="续存方式" field="aucap" ></t:dgCol>
   <t:dgCol title="续存期限" field="aurolte" ></t:dgCol>
   <t:dgCol title="续存到期日" field="finmat" formatter="yyyy-MM-dd"></t:dgCol>
   <t:dgCol title="续存利率" field="rolrate" ></t:dgCol>
   <t:dgCol title="上一到期日" field="predate" formatter="yyyy-MM-dd"></t:dgCol>
   <t:dgCol title="系统日期" field="sysDate" formatter="yyyy-MM-dd"></t:dgCol>
   <t:dgCol title="营业日期" field="importdate" formatter="yyyy-MM-dd"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="mmFixDepositController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="mmFixDepositController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="mmFixDepositController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="mmFixDepositController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>