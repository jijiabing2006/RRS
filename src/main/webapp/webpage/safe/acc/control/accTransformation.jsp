<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<link rel="stylesheet" href="plug-in/easyui/themes/metro/main.css" type="text/css"></link>
<t:formvalid formid="formobj" dialog="false" layout="div" btnsub="btn" 
	action="accController.do?accExtract">
	<input type="hidden" id="id" />
	<fieldset class="step">
		<div class="form">
			<div class="text_success">
				<label class="Validform_label"> 转换日期： </label>
				 <input name="transdate"  type="text"
					id="transdate"> <span class="Validform_checktip"></span>
			</div>

			<div class="button-row">
			<span style="float:right">
			<a href="#" class="button" style="background:url('plug-in/easyui/themes/icons/datafilter_16.png') no-repeat; background-position:5px;text-indent:10px;"  type="button"
					onclick="accTransforme();">生成账户变更记录</a>
			</span>
				
			</div>
		</div>

	</fieldset>

</t:formvalid>
<script type="text/javascript">
 $(function(){
 		//给时间控件加上样式
 			$("#transdate").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 
 });
 
 
//导出
function accTransforme() {
	var d=$("#transdate").val();
	if (d == ""||d== undefined) {
		tip("请选择营业日期");
		return false;
	}
	

	doSubmit("accController.do?accTransforme&date="+d,"formobj");
}

 </script>