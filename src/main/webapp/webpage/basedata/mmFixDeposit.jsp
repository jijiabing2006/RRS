<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>存放同业定期存款信息</title>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="mmFixDepositController.do?save">
		<input id="id" name="id" type="hidden" value="${mmFixDepositPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">交易序号:</label>
		      <input class="inputxt" id="dlno" name="dlno" ignore="ignore"
					   value="${mmFixDepositPage.dlno}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">上一级机构代码:</label>
		      <input class="inputxt" id="parentbrca" name="parentbrca" ignore="ignore"
					   value="${mmFixDepositPage.parentbrca}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">分支机构代码:</label>
		      <input class="inputxt" id="brcaCo" name="brcaCo" ignore="ignore"
					   value="${mmFixDepositPage.brcaCo}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">交易对手:</label>
		      <input class="inputxt" id="csnm" name="csnm" ignore="ignore"
					   value="${mmFixDepositPage.csnm}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">交易币种:</label>
		      <input class="inputxt" id="ccy" name="ccy" ignore="ignore"
					   value="${mmFixDepositPage.ccy}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">本金:</label>
		      <input class="inputxt" id="pcpl" name="pcpl" ignore="ignore"
					   value="${mmFixDepositPage.pcpl}" datatype="d">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">交易日期:</label>
		      <input class="Wdate" onClick="WdatePicker()"  style="width: 150px" id="deld" name="deld" ignore="ignore"
					   value="<fmt:formatDate value='${mmFixDepositPage.deld}' type="date" pattern="yyyy-MM-dd"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">生效日期:</label>
		      <input class="Wdate" onClick="WdatePicker()"  style="width: 150px" id="stad" name="stad" ignore="ignore"
					   value="<fmt:formatDate value='${mmFixDepositPage.stad}' type="date" pattern="yyyy-MM-dd"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">到期日:</label>
		      <input class="Wdate" onClick="WdatePicker()"  style="width: 150px" id="matd" name="matd" ignore="ignore"
					   value="<fmt:formatDate value='${mmFixDepositPage.matd}' type="date" pattern="yyyy-MM-dd"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">账户类型:</label>
		      <input class="inputxt" id="category" name="category" ignore="ignore"
					   value="${mmFixDepositPage.category}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">利率类型:</label>
		      <input class="inputxt" id="intratp" name="intratp" ignore="ignore"
					   value="${mmFixDepositPage.intratp}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">计息方式:</label>
		      <input class="inputxt" id="intbas" name="intbas" ignore="ignore"
					   value="${mmFixDepositPage.intbas}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">利率:</label>
		      <input class="inputxt" id="intrate" name="intrate" ignore="ignore"
					   value="${mmFixDepositPage.intrate}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">利率基准:</label>
		      <input class="inputxt" id="intkey" name="intkey" ignore="ignore"
					   value="${mmFixDepositPage.intkey}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">利率调整:</label>
		      <input class="inputxt" id="intspr" name="intspr" ignore="ignore"
					   value="${mmFixDepositPage.intspr}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">利息计提方式:</label>
		      <input class="inputxt" id="accpar" name="accpar" ignore="ignore"
					   value="${mmFixDepositPage.accpar}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">应计利息:</label>
		      <input class="inputxt" id="totintamt" name="totintamt" ignore="ignore"
					   value="${mmFixDepositPage.totintamt}" datatype="d">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">利率重新定价期限:</label>
		      <input class="inputxt" id="inddued" name="inddued" ignore="ignore"
					   value="${mmFixDepositPage.inddued}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">备注:</label>
		      <input class="inputxt" id="remarks" name="remarks" ignore="ignore"
					   value="${mmFixDepositPage.remarks}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">提款账户:</label>
		      <input class="inputxt" id="drawacc" name="drawacc" ignore="ignore"
					   value="${mmFixDepositPage.drawacc}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">本金清算账户:</label>
		      <input class="inputxt" id="priliqacct" name="priliqacct" ignore="ignore"
					   value="${mmFixDepositPage.priliqacct}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">利息清算账户:</label>
		      <input class="inputxt" id="intliqacct" name="intliqacct" ignore="ignore"
					   value="${mmFixDepositPage.intliqacct}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">交易对手银行:</label>
		      <input class="inputxt" id="benbk" name="benbk" ignore="ignore"
					   value="${mmFixDepositPage.benbk}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">交易对手账户:</label>
		      <input class="inputxt" id="benacc" name="benacc" ignore="ignore"
					   value="${mmFixDepositPage.benacc}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">授信编号:</label>
		      <input class="inputxt" id="limitref" name="limitref" ignore="ignore"
					   value="${mmFixDepositPage.limitref}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">客户编号:</label>
		      <input class="inputxt" id="cusref" name="cusref" ignore="ignore"
					   value="${mmFixDepositPage.cusref}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">报文编号:</label>
		      <input class="inputxt" id="advsent" name="advsent" ignore="ignore"
					   value="${mmFixDepositPage.advsent}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">SWIFTCODE:</label>
		      <input class="inputxt" id="swiftco" name="swiftco" ignore="ignore"
					   value="${mmFixDepositPage.swiftco}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">利息支付币种:</label>
		      <input class="inputxt" id="intccy" name="intccy" ignore="ignore"
					   value="${mmFixDepositPage.intccy}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">转存标识:</label>
		      <input class="inputxt" id="aurol" name="aurol" ignore="ignore"
					   value="${mmFixDepositPage.aurol}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">续存方式:</label>
		      <input class="inputxt" id="aucap" name="aucap" ignore="ignore"
					   value="${mmFixDepositPage.aucap}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">续存期限:</label>
		      <input class="inputxt" id="aurolte" name="aurolte" ignore="ignore"
					   value="${mmFixDepositPage.aurolte}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">续存到期日:</label>
		      <input class="Wdate" onClick="WdatePicker()"  style="width: 150px" id="finmat" name="finmat" ignore="ignore"
					   value="<fmt:formatDate value='${mmFixDepositPage.finmat}' type="date" pattern="yyyy-MM-dd"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">续存利率:</label>
		      <input class="inputxt" id="rolrate" name="rolrate" ignore="ignore"
					   value="${mmFixDepositPage.rolrate}" datatype="d">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">上一到期日:</label>
		      <input class="Wdate" onClick="WdatePicker()"  style="width: 150px" id="predate" name="predate" ignore="ignore"
					   value="<fmt:formatDate value='${mmFixDepositPage.predate}' type="date" pattern="yyyy-MM-dd"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">系统日期:</label>
		      <input class="Wdate" onClick="WdatePicker()"  style="width: 150px" id="sysDate" name="sysDate" ignore="ignore"
					   value="<fmt:formatDate value='${mmFixDepositPage.sysDate}' type="date" pattern="yyyy-MM-dd"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">营业日期:</label>
		      <input class="Wdate" onClick="WdatePicker()"  style="width: 150px" id="importdate" name="importdate" ignore="ignore"
					   value="<fmt:formatDate value='${mmFixDepositPage.importdate}' type="date" pattern="yyyy-MM-dd"/>">
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>