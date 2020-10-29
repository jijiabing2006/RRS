<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>t_cust_indv</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="custIndvController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${custIndvPage.id }">
		<table  cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								客户编号:
							</label>
						</td>
						<td class="value">
						     	 <input id="csnm" name="csnm" type="text"  class="inputxt"  
									               datatype="*"
									                 value='${custIndvPage.csnm}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户编号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								客户名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="ctnm" name="ctnm" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.ctnm}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否出生在美国:
							</label>
						</td>
						<td class="value">
						     	 <input id="piabi" name="piabi" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.piabi}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否出生在美国</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								居住地国别代码:
							</label>
						</td>
						<td class="value">
						     	 <input id="resi" name="resi" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.resi}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">居住地国别代码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								身份标识:
							</label>
						</td>
						<td class="value">
						     	 <input id="islocal" name="islocal" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.islocal}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">身份标识</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								居住所在省市:
							</label>
						</td>
						<td class="value">
						     	 <input id="pro" name="pro" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.pro}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">居住所在省市</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								居住所在城市/区:
							</label>
						</td>
						<td class="value">
						     	 <input id="city" name="city" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.city}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">居住所在城市/区</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								客户地址:
							</label>
						</td>
						<td class="value">
						     	 <input id="address" name="address" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.address}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户地址</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								行业:
							</label>
						</td>
						<td class="value">
						     	 <input id="industrycode" name="industrycode" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.industrycode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">行业</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								国籍:
							</label>
						</td>
						<td class="value">
						     	 <input id="ctnt" name="ctnt" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.ctnt}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">国籍</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								客户英文名:
							</label>
						</td>
						<td class="value">
						     	 <input id="ctnmen" name="ctnmen" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.ctnmen}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户英文名</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								出生日期:
							</label>
						</td>
						<td class="value">
									  <input id="dob" name="dob" type="text"  
						      						class="Wdate" onClick="WdatePicker()"
									                
						      						 value='<fmt:formatDate value='${custIndvPage.dob}' type="date" pattern="yyyy-MM-dd"/>'>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出生日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否为本行员工:
							</label>
						</td>
						<td class="value">
						     	 <input id="isem" name="isem" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.isem}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否为本行员工</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								是否为股东:
							</label>
						</td>
						<td class="value">
						     	 <input id="shod" name="shod" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.shod}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否为股东</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								个人证件号码:
							</label>
						</td>
						<td class="value">
						     	 <input id="encode" name="encode" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.encode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">个人证件号码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								个人证件类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="citp" name="citp" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.citp}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">个人证件类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								性别:
							</label>
						</td>
						<td class="value">
						     	 <input id="gend" name="gend" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.gend}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">性别</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								婚姻状况:
							</label>
						</td>
						<td class="value">
						     	 <input id="mars" name="mars" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.mars}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">婚姻状况</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								职务:
							</label>
						</td>
						<td class="value">
						     	 <input id="pstn" name="pstn" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.pstn}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">职务</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								职业:
							</label>
						</td>
						<td class="value">
						     	 <input id="job" name="job" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.job}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">职业</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								客户类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="cs" name="cs" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.cs}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">客户类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								发证机关代码:
							</label>
						</td>
						<td class="value">
						     	 <input id="issa" name="issa" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.issa}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发证机关代码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								w9表单信息:
							</label>
						</td>
						<td class="value">
						     	 <input id="w9fb" name="w9fb" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.w9fb}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">w9表单信息</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								联系方式:
							</label>
						</td>
						<td class="value">
						     	 <input id="ctif" name="ctif" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.ctif}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系方式</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								邮编:
							</label>
						</td>
						<td class="value">
						     	 <input id="zipcode" name="zipcode" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.zipcode}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮编</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								个人年收入:
							</label>
						</td>
						<td class="value">
						     	 <input id="income" name="income" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.income}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">个人年收入</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否为黑名单客户:
							</label>
						</td>
						<td class="value">
						     	 <input id="lsbl" name="lsbl" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.lsbl}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否为黑名单客户</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								黑名单原因:
							</label>
						</td>
						<td class="value">
						     	 <input id="blre" name="blre" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.blre}'>
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
									               
									                 value='${custIndvPage.afpr}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关联人名称</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								关联方关系:
							</label>
						</td>
						<td class="value">
						     	 <input id="repr" name="repr" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.repr}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关联方关系</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								关联人证件类型:
							</label>
						</td>
						<td class="value">
						     	 <input id="apidt" name="apidt" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.apidt}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关联人证件类型</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								关联人证件号:
							</label>
						</td>
						<td class="value">
						     	 <input id="apidn" name="apidn" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.apidn}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关联人证件号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								关联人客户编号:
							</label>
						</td>
						<td class="value">
						     	 <input id="apcn" name="apcn" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.apcn}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">关联人客户编号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								备注信息:
							</label>
						</td>
						<td class="value">
						     	 <input id="note" name="note" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.note}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注信息</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								居住情况:
							</label>
						</td>
						<td class="value">
						     	 <input id="lvst" name="lvst" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.lvst}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">居住情况</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								上一级机构代码:
							</label>
						</td>
						<td class="value">
						     	 <input id="parentbrca" name="parentbrca" type="text"  class="inputxt"  
									               datatype="*"
									                 value='${custIndvPage.parentbrca}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上一级机构代码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								分支机构代码:
							</label>
						</td>
						<td class="value">
						     	 <input id="brca" name="brca" type="text"  class="inputxt"  
									               datatype="*"
									                 value='${custIndvPage.brca}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">分支机构代码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								地区编号:
							</label>
						</td>
						<td class="value">
						     	 <input id="regc" name="regc" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.regc}'>
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
									                
						      						 value='<fmt:formatDate value='${custIndvPage.opdt}' type="date" pattern="yyyy-MM-dd"/>'>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">开户日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								民族:
							</label>
						</td>
						<td class="value">
						     	 <input id="nation" name="nation" type="text"  class="inputxt"  
									               
									                 value='${custIndvPage.nation}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">民族</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								营业日期:
							</label>
						</td>
						<td class="value">
									  <input id="importdate" name="importdate" type="text"  
						      						class="Wdate" onClick="WdatePicker()"
									               datatype="*" 
						      						 value='<fmt:formatDate value='${custIndvPage.importdate}' type="date" pattern="yyyy-MM-dd"/>'>    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">营业日期</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/bastdata/custIndv.js"></script>		