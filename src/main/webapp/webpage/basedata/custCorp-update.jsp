<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>t_cust_corp</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="custCorpController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${custCorpPage.id }">
		<table  cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								客户名称中文:
							</label>
						</td>
						<td class="value">
						     	 <input id="ctnm" name="ctnm" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.ctnm}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户名称中文</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								客户英文名:
							</label>
						</td>
						<td class="value">
						     	 <input id="ctnmen" name="ctnmen" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.ctnmen}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户英文名</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								公司地址:
							</label>
						</td>
						<td class="value">
						     	 <input id="address" name="address" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.address}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">公司地址</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								客户编号:
							</label>
						</td>
						<td class="value">
						     	 <input id="csnm" name="csnm" type="text"  class="inputxt"  
									               datatype="*"
									                 value='${custCorpPage.csnm}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户编号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								法人标志:
							</label>
						</td>
						<td class="value">
						     	 <input id="mlpr" name="mlpr" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.mlpr}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">法人标志</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								客户类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="cutp" name="cutp" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.cutp}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								客户类别:
							</label>
						</td>
						<td class="value">
						     	 <input id="cs" name="cs" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.cs}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户类别</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								国民经济代码:
							</label>
						</td>
						<td class="value">
						     	 <input id="nesc" name="nesc" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.nesc}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">国民经济代码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								境内外标识:
							</label>
						</td>
						<td class="value">
						     	 <input id="islocal" name="islocal" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.islocal}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">境内外标识</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								母公司客户名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="pctnm" name="pctnm" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.pctnm}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">母公司客户名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								母公司注册国家:
							</label>
						</td>
						<td class="value">
						     	 <input id="pctnt" name="pctnt" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.pctnt}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">母公司注册国家</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								常驻国家:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="ctnt" type="list"
										typeGroupCode="" defaultVal="${custCorpPage.ctnt}" hasLabel="false"  title="常驻国家"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">常驻国家</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								国内注册地址:
							</label>
						</td>
						<td class="value">
						     	 <input id="regaddress" name="regaddress" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.regaddress}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">国内注册地址</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								企业出资人经济成分:
							</label>
						</td>
						<td class="value">
						     	 <input id="attcode" name="attcode" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.attcode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">企业出资人经济成分</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								企业规模:
							</label>
						</td>
						<td class="value">
						     	 <input id="custsize" name="custsize" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.custsize}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">企业规模</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								企业证件类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="citp" name="citp" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.citp}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">企业证件类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								证件有效期:
							</label>
						</td>
						<td class="value">
									  <input id="idexpdate" name="idexpdate" type="text"  
						      						class="Wdate" onClick="WdatePicker()"
									                
						      						 value='<fmt:formatDate value='${custCorpPage.idexpdate}' type="date" pattern="yyyy-MM-dd"/>'>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">证件有效期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								关系人类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="relatetype" name="relatetype" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.relatetype}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关系人类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								关系人姓名:
							</label>
						</td>
						<td class="value">
						     	 <input id="relatename" name="relatename" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.relatename}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关系人姓名</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								法人代表姓名:
							</label>
						</td>
						<td class="value">
						     	 <input id="crnm" name="crnm" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.crnm}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">法人代表姓名</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								法人证件类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="crit" name="crit" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.crit}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">法人证件类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								法人证件号码:
							</label>
						</td>
						<td class="value">
						     	 <input id="crid" name="crid" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.crid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">法人证件号码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								组织机构代码:
							</label>
						</td>
						<td class="value">
						     	 <input id="encode" name="encode" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.encode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">组织机构代码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								代码证更新日期:
							</label>
						</td>
						<td class="value">
									  <input id="enupdate" name="enupdate" type="text"  
						      						class="Wdate" onClick="WdatePicker()"
									                
						      						 value='<fmt:formatDate value='${custCorpPage.enupdate}' type="date" pattern="yyyy-MM-dd"/>'>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">代码证更新日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								组织机构类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="entyp" name="entyp" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.entyp}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">组织机构类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								营业执照:
							</label>
						</td>
						<td class="value">
						     	 <input id="buslicense" name="buslicense" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.buslicense}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">营业执照</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								营业执照有效期:
							</label>
						</td>
						<td class="value">
									  <input id="orgexpdate" name="orgexpdate" type="text"  
						      						class="Wdate" onClick="WdatePicker()"
									                
						      						 value='<fmt:formatDate value='${custCorpPage.orgexpdate}' type="date" pattern="yyyy-MM-dd"/>'>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">营业执照有效期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								经营范围:
							</label>
						</td>
						<td class="value">
						     	 <input id="busscope" name="busscope" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.busscope}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">经营范围</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								成立日期:
							</label>
						</td>
						<td class="value">
									  <input id="regdate" name="regdate" type="text"  
						      						class="Wdate" onClick="WdatePicker()"
									                
						      						 value='<fmt:formatDate value='${custCorpPage.regdate}' type="date" pattern="yyyy-MM-dd"/>'>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">成立日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								所属行业:
							</label>
						</td>
						<td class="value">
						     	 <input id="industrycode" name="industrycode" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.industrycode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">所属行业</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								企业登记注册类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="regtype" name="regtype" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.regtype}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">企业登记注册类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								贷款卡编号:
							</label>
						</td>
						<td class="value">
						     	 <input id="lncardid" name="lncardid" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.lncardid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">贷款卡编号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								上市公司标志:
							</label>
						</td>
						<td class="value">
						     	 <input id="listcompanyflag" name="listcompanyflag" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.listcompanyflag}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上市公司标志</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								是否为银行股东:
							</label>
						</td>
						<td class="value">
						     	 <input id="isbanksh" name="isbanksh" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.isbanksh}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否为银行股东</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								股东客户代码:
							</label>
						</td>
						<td class="value">
						     	 <input id="shcustid" name="shcustid" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.shcustid}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">股东客户代码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								股东名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="shname" name="shname" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.shname}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">股东名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								持有股份份额:
							</label>
						</td>
						<td class="value">
						     	 <input id="shares" name="shares" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.shares}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">持有股份份额</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								投资人国别:
							</label>
						</td>
						<td class="value">
						     	 <input id="invcountrycode" name="invcountrycode" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.invcountrycode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">投资人国别</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								集团客户标志:
							</label>
						</td>
						<td class="value">
						     	 <input id="pcutp" name="pcutp" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.pcutp}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">集团客户标志</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								集团客户编号:
							</label>
						</td>
						<td class="value">
						     	 <input id="parn" name="parn" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.parn}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">集团客户编号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								集团客户关联关系类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="prepr" name="prepr" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.prepr}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">集团客户关联关系类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								外部信用评级:
							</label>
						</td>
						<td class="value">
						     	 <input id="excreditass" name="excreditass" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.excreditass}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">外部信用评级</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								风险等级:
							</label>
						</td>
						<td class="value">
						     	 <input id="rishrank" name="rishrank" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.rishrank}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">风险等级</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								国税证号:
							</label>
						</td>
						<td class="value">
						     	 <input id="nationaltaxlic" name="nationaltaxlic" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.nationaltaxlic}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">国税证号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								地税证号:
							</label>
						</td>
						<td class="value">
						     	 <input id="localtaxlic" name="localtaxlic" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.localtaxlic}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地税证号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								联系人:
							</label>
						</td>
						<td class="value">
						     	 <input id="contactor" name="contactor" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.contactor}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								单位电话:
							</label>
						</td>
						<td class="value">
						     	 <input id="phone" name="phone" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.phone}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">单位电话</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								传真:
							</label>
						</td>
						<td class="value">
						     	 <input id="fax" name="fax" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.fax}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">传真</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								手机:
							</label>
						</td>
						<td class="value">
						     	 <input id="sms" name="sms" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.sms}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">手机</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								电子邮件:
							</label>
						</td>
						<td class="value">
						     	 <input id="email" name="email" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.email}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">电子邮件</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								通讯地址:
							</label>
						</td>
						<td class="value">
						     	 <input id="caddress" name="caddress" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.caddress}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">通讯地址</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								是否特殊经济区内企业:
							</label>
						</td>
						<td class="value">
						     	 <input id="istaxfree" name="istaxfree" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.istaxfree}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否特殊经济区内企业</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								特殊经济区类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="taxfreecode" name="taxfreecode" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.taxfreecode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">特殊经济区类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								邮政编码:
							</label>
						</td>
						<td class="value">
						     	 <input id="zipcode" name="zipcode" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.zipcode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮政编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否为黑名单客户:
							</label>
						</td>
						<td class="value">
						     	 <input id="blcl" name="blcl" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.blcl}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否为黑名单客户</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								黑名单原因:
							</label>
						</td>
						<td class="value">
						     	 <input id="cklr" name="cklr" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.cklr}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">黑名单原因</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								关联人名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="afpr" name="afpr" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.afpr}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关联人名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								注册资本币种:
							</label>
						</td>
						<td class="value">
						     	 <input id="recpccy" name="recpccy" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.recpccy}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">注册资本币种</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								注册资本金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="rgcp" name="rgcp" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.rgcp}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">注册资本金额</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								实收资本金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="actcp" name="actcp" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.actcp}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">实收资本金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								总资产:
							</label>
						</td>
						<td class="value">
						     	 <input id="totalassets" name="totalassets" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.totalassets}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">总资产</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								年营业收入:
							</label>
						</td>
						<td class="value">
						     	 <input id="annrevenue" name="annrevenue" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.annrevenue}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">年营业收入</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								员工人数:
							</label>
						</td>
						<td class="value">
						     	 <input id="empnumber" name="empnumber" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.empnumber}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">员工人数</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								上级机构代码:
							</label>
						</td>
						<td class="value">
						     	 <input id="parentbrca" name="parentbrca" type="text"  class="inputxt"  
									               datatype="*"
									                 value='${custCorpPage.parentbrca}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上级机构代码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								本机构代码:
							</label>
						</td>
						<td class="value">
						     	 <input id="brca" name="brca" type="text"  class="inputxt"  
									               datatype="*"
									                 value='${custCorpPage.brca}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">本机构代码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								地区编号:
							</label>
						</td>
						<td class="value">
						     	 <input id="regc" name="regc" type="text"  class="inputxt"  
									               
									                 value='${custCorpPage.regc}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地区编号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								开户日期:
							</label>
						</td>
						<td class="value">
									  <input id="opdt" name="opdt" type="text"  
						      						class="Wdate" onClick="WdatePicker()"
									                
						      						 value='<fmt:formatDate value='${custCorpPage.opdt}' type="date" pattern="yyyy-MM-dd"/>'>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">开户日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								营业日期:
							</label>
						</td>
						<td class="value">
									  <input id="importdate" name="importdate" type="text"  
						      						class="Wdate" onClick="WdatePicker()"
									               datatype="*" 
						      						 value='<fmt:formatDate value='${custCorpPage.importdate}' type="date" pattern="yyyy-MM-dd"/>'>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">营业日期</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/basedata/custCorp.js"></script>		