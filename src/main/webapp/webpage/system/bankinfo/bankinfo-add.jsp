<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>r_core_bankinfo</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="bankinfoController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${bankinfoPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							金融机构代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="branchcode" name="branchcode" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">金融机构代码</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							14位金融机构代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="bankcodeC14" name="bankcodeC14" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">14位金融机构代码</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							15位AML代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="amlbankcode" name="amlbankcode" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">15位AML代码</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							中文简称:
						</label>
					</td>
					<td class="value">
					     	 <input id="shortcnname" name="shortcnname" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">中文简称</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							英文简称:
						</label>
					</td>
					<td class="value">
					     	 <input id="shortenname" name="shortenname" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">英文简称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							中文全称:
						</label>
					</td>
					<td class="value">
					     	 <input id="fullcnname" name="fullcnname" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">中文全称</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							英文全称:
						</label>
					</td>
					<td class="value">
					     	 <input id="fullenname" name="fullenname" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">英文全称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							机构代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="brca" name="brca" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">机构代码</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							上一级机构代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="parentbrca" name="parentbrca" type="text" style="width: 150px" class="inputxt"  
								               datatype="*"
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上一级机构代码</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							行政区划代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="areacode" name="areacode" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">行政区划代码</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							市级代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="citycode" name="citycode" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">市级代码</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							市级名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="cityname" name="cityname" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">市级名称</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							省级代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="provincecode" name="provincecode" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">省级代码</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							省级名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="provincename" name="provincename" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">省级名称</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							地址:
						</label>
					</td>
					<td class="value">
					     	 <input id="address" name="address" type="text" style="width: 150px" class="inputxt"  
								               
								               >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">地址</label>
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
  <script src = "webpage/basedata/bankinfo.js"></script>		