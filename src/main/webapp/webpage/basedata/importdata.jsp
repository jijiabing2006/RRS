<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>手工导数</title>
<t:base type="jquery,easyui,tools,select2,prohibit"></t:base>
<link rel="stylesheet" href="plug-in/easyui/themes/metro/main.css" type="text/css"></link>
<script type="text/javascript" src="plug-in/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="plug-in/ckfinder/ckfinder.js"></script>

</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" layout="div" dialog="false" action="importDataController.do?doImportdata" btnsub="btn" beforeSubmit="check(curform);" >
	<input type="hidden" name="id" id="id" value="  ">
	<fieldset class="step">
	<tr>
		<td align="right"><label class="Validform_label"> 是否手工导入: </label></td>
		<td class="value"><t:dictSelect id="yn" field="yn" typeGroupCode="sf_10" hasLabel="false" defaultVal="0"  ></t:dictSelect>  </td>
	</tr>
	<a href="#" class="easyui-linkbutton" id="btn" iconCls="icon-ok" >手工导数</a> 
	</fieldset>
</t:formvalid>
<t:formvalid formid="formsummitimport" layout="div" dialog="false" action="importDataController.do?doImportSummitdata" btnsub="btn">
	<input type="hidden" name="id" id="id" value="  ">
	<fieldset class="step">
		<a href="#" class="easyui-linkbutton" id="btn" iconCls="icon-ok" >手工导入Summit数据</a> 
	</fieldset>
</t:formvalid>
<t:formvalid formid="formsummitupdate" layout="div" dialog="false" action="importDataController.do?doUpdateSummitdata" btnsub="btn">
	<input type="hidden" name="id" id="id" value="  ">
	<fieldset class="step">
		<a href="#" class="easyui-linkbutton" id="btn" iconCls="icon-ok" >手工更新Summit数据</a> 
	</fieldset>
</t:formvalid>
<t:formvalid formid="formftz" layout="div" dialog="false" action="ftzmisDataController.do?doFtzmisdata" btnsub="btn">
	<fieldset class="step">
		<a href="#" class="easyui-linkbutton" id="btn" iconCls="icon-ok" >导出FTZMIS数据</a> 
	</fieldset>
</t:formvalid>
<script type="text/javascript">
$("#yn").select2({
    minimumResultsForSearch: Infinity,
    theme: "bootstrap"
});
function check(curform) {
	var yn=$("#yn").val();
	if (yn == ""||yn== undefined) {
		tip("请选择是否要手工导入数据");
		return false;
	}
	if(yn=="0"){
		tip("选择否时，不会导入数据");
		return false;
	}
}
 </script>
</body>
</html>