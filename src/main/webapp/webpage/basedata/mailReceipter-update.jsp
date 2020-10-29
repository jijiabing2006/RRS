<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>r_c_mail_receipter</title>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
  <script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
  <script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="mailReceipterController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${mailReceipterPage.id }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			 <tr>
					<td align="right">
						<label class="Validform_label">
							收件人:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect id="userid" field="userid" type="list"
									dictTable="t_s_base_user" dictField="id" dictText="realname" defaultVal="${mailReceipterPage.userid}" hasLabel="false"  title="userid" datatype="*"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">收件人</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							发送类型:
						</label>
					</td>
					<td class="value">
					     	 <input id="type" name="type" type="text"  class="inputxt"  
								               datatype="*"  value="${mailReceipterPage.type}">
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发送类型</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							启用状态:
						</label>
					</td>
					<td class="value">
							 <t:dictSelect field="state" type="list"
									typeGroupCode="sf_YN" defaultVal="${mailReceipterPage.state}" hasLabel="false"  title="启用状态" datatype="*"></t:dictSelect>     
							<span class="Validform_checktip"></span>           
							<label class="Validform_label" style="display: none;">启用状态</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否必发:
						</label>
					</td>
					<td class="value">
							 <t:dictSelect field="msend" type="list"
									typeGroupCode="sf_YN" defaultVal="${mailReceipterPage.msend}" hasLabel="false"  title="是否必发" datatype="*"></t:dictSelect>              
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否必发</label>
						</td>
				</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 分行代码:
				</label></td>
				
				 <td class="value" nowrap>
                <input name="brcaid" name="brcaid" type="hidden" value="${id}" id="brcaid">
                <input name="brcaName" class="inputxt" value="${brcaName }" id="shortcnname" style="width:300px" readonly="readonly" datatype="*" />
                <t:choose hiddenName="brcaid" hiddenid="id" url="mailReceipterController.do?brcas" name="brcaList"
                          icon="icon-search" title="common.brca.list" textname="shortcnname" isclear="true" isInit="true"></t:choose>
                <span class="Validform_checktip">>分行代码可多选</span>
                <label class="Validform_label" style="display: none;">分行代码</label>
                
            </td>
			</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/basedata/mailReceipter.js"></script>		