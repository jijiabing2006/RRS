<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>定时任务管理</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="div" action="jobTaskController.do?save" beforeSubmit="check(curform);">
		<input id="id" name="id" type="hidden" value="${jobTaskPage.id }">
		<input id="jobStatus" name="jobStatus" type="hidden" value="${(empty jobTaskPage.jobStatus)?0:jobTaskPage.jobStatus }">
		<fieldset class="step">
			<div class="form">
				<label class="Validform_label"><t:mutiLang
						langKey="common.jobName" />:</label> <input class="inputxt" id="jobName"
					name="jobName" value="${jobTaskPage.jobName}" datatype="*">
				<span class="Validform_checktip"></span>
			</div>
			<div class="form">
				<label class="Validform_label"><t:mutiLang
						langKey="common.jobGroup" />:</label> <input class="inputxt" id="jobGroup"
					name="jobGroup" value="${jobTaskPage.jobGroup}" datatype="*">
				<span class="Validform_checktip"></span>
			</div>
			<div class="form">
				<label class="Validform_label"><t:mutiLang
						langKey="common.task.desc" />:</label> <input class="inputxt"
					id="description" name="description"
					value="${jobTaskPage.description}"  > <span
					class="Validform_checktip"></span>
			</div>
			<div class="form">
				<label class="Validform_label"><t:mutiLang
						langKey="cron.expression" />:</label> <input class="inputxt"
					id="cronExpression" name="cronExpression" onclick=""
					value="${jobTaskPage.cronExpression}" datatype="*"> <span
					class="Validform_checktip"></span>
			</div>
			<div class="form">
				<label class="Validform_label"><t:mutiLang
						langKey="running.isConcurrent" />:</label> 
					
					<t:dictSelect field="isConcurrent" type="list"
						typeGroupCode="sf_10" defaultVal="${jobTaskPage.isConcurrent}"
						hasLabel="false" title="是否有状态" datatype="*"></t:dictSelect> <span
					class="Validform_checktip">
					</span>
					
					
					
			</div>
			<div class="form">
				<label class="Validform_label"><t:mutiLang
						langKey="common.beanClass" />:</label> <input class="inputxt"
					id="beanClass" name="beanClass"
					value="${jobTaskPage.beanClass}"  datatype="notblank"  > <span
					class="Validform_checktip"></span>
			</div>
			<div class="form">
				<label class="Validform_label"><t:mutiLang
						langKey="common.springId" />:</label> <input class="inputxt"
					id="springId" name="springId"
					value="${jobTaskPage.springId}"  ignore="unignore"   datatype="unbothnull" with="beanClass"  nullmsg="类全名与SpirngId至少填写一项" > <span
					class="Validform_checktip"></span>
			</div>
			<div class="form">
				<label class="Validform_label"><t:mutiLang
						langKey="common.methodName" />:</label> <input class="inputxt"
					id="springId" name="methodName"
					value="${jobTaskPage.methodName}" datatype="*"> <span
					class="Validform_checktip"></span>
			</div>
		</fieldset>
	</t:formvalid>
	<script>
	
			function check(curform) {
				var flag = true;
				$.ajax({
					type : "POST",
					async : false,
					dataType : "JSON",
					cache : false,
					url : "jobTaskController.do?updateTime",
					data : $("#formobj").serialize(),
					  success:function(data){
				            if(!data.success){
				                $.Showmsg(data.msg);
				        //     alert($("#"+data.obj+""));
								$("#"+data.obj+"").focus();
				                flag = false;
				            }
				        }

					//end-callback
				});//end-ajax
				return flag;

		}
			</script>
</body>