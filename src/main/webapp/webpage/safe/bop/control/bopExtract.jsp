<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<link rel="stylesheet" href="plug-in/easyui/themes/metro/main.css" type="text/css"></link>
<script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.1.4.4.js"></script>
<t:formvalid formid="formobj" dialog="false" layout="div" btnsub="btn" 
	action="bopController.do?bopExtract">
	<input type="hidden" id="id" />
	<fieldset class="step">
		<div class="form">
			<div class="text_success">
				<label class="Validform_label"> 营业日期： </label>
				 <input name="extractdate" type="text"
					id="extractdate"> <span class="Validform_checktip"></span>
			</div>

			<div class="button-row">
				<a href="#" class="button" style="background:url('plug-in/easyui/themes/icons/datafilter_16.png') no-repeat; background-position:5px;text-indent:10px;" type="button"
					onclick="bopExtract();">提取数据</a>
			</div>
		</div>

	</fieldset>

</t:formvalid>
<script type="text/javascript">
 $(function(){
 		//给时间控件加上样式
 			$("#extractdate").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 
 });
 
 
//导出
function bopExtract() {
	var d=$("#extractdate").val();
	if (d == ""||d== undefined) {
		tip("请选择营业日期");
		return false;
	}
	

	doSubmitNunTable("bopController.do?bopExtract&date="+d,"formobj");
}

 </script>