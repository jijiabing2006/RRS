<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>FR模板列表</title>
<t:base type="jquery,easyui,tools,select2"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" layout="div" dialog="true"
		beforeSubmit="upload">
		<fieldset class="step">
			<div class="form">
				<label class="Validform_label"> 模板名称: </label> <input
					class="inputxt" name="documentTitle" id="documentTitle"
					datatype="s3-50"> <span class="Validform_checktip">名称在3~50位字符,且不为空</span>
			</div>
			<div class="form">
				<t:upload name="files" buttonText="上传文件"
					uploader="frController.do?saveFiles" extend="office"
					id="file_upload" formData="documentTitle,regorg"></t:upload>
			</div>
			<div class="form" id="filediv" style="height: 50px"></div>
			<div class="form">
				<label class="Validform_label" style="width: 200px">
					所属机构 </label>
					<t:dictSelect field="regorg" type="list"
						typeGroupCode="regorg" defaultVal=" "
						hasLabel="false" title="所属机构" datatype="*"></t:dictSelect> 
			</div>

		</fieldset>


	</t:formvalid>
	<Script>
		$(function() {

			$("#regorg").select2({
				minimumResultsForSearch : Infinity,
				theme : "bootstrap"
			});
		});
	</Script>
</body>
</html>
