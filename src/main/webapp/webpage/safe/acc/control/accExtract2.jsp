<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:formvalid formid="formobj" dialog="false" layout="div" btnsub="btn"
	action="accController.do?accExtract">
	<input type="hidden" id="id" />
	<fieldset class="step">
		<div class="form">
			<div class="text_success">
				<label class="Validform_label"> 日期： </label> <input name="date"
					datatype="*" id="date"> <span class="Validform_checktip"></span>
			</div>

			<div class="button-row">
				<a href="#" class="easyui-linkbutton" iconCls="icon-share" id="btn">提取数据</a>
			</div>
		</div>

	</fieldset>

</t:formvalid>
<script type="text/javascript">
 $(function(){
 		//给时间控件加上样式
 			$("#date").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 
 });
//导出
//function accExtract() {
//	alert("11");
//	var d=$("#date").val();
//	alert(d);
//	doSubmit("accController.do?accExtract&date="+d,"formobj","formobj");
//	alert("22");
//}

 </script>