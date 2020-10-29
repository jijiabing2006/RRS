<%@ page language="java" import="java.util.*,org.jeecgframework.core.util.ResourceUtil" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script src="plug-in/jquery/jquery-1.12.0.min.js" type="text/javascript"></script>
<!DOCTYPE html>
<html>
 <head>
  <title>报表监控</title>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
  <script>
  var  selectAllOption;
  $(function(){
	  selectAllOption=$("#reportName").clone();  
	  $("#reportName option").each(function(i){
		 $(this).html($(this).val());
	  });
	  var  val=$("#reportName").val();
	  if(val!=""){
	  	changeValue($("#reportType").get(0));
	  	$("#reportName").val(val);
	  }
  })
  function changeValue(obj){
      var  reportType=obj.value;
      $("#reportName option").remove();
      selectAllOption.find("option").each(function(i){
    	   if($(this).html().indexOf(reportType)>=0){
    	      var  optionObj=$(this).clone();
    	      optionObj.html(optionObj.val());
    		  $("#reportName").append(optionObj);
    	   }
     });
  }

  </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="reportMonitorController.do?save">
			<input id="id" name="id" type="hidden" value="${reportMonitorPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							报表类型
						</label>
					</td>
					<td class="value">
					 		<t:dictSelect field="reportType" type="list"  datatype="*"
									typeGroupCode="reportType" defaultVal="${reportMonitorPage.reportType}" 
									hasLabel="false"  title="报表类型"   extendJson="{onchange:'changeValue(this)',id:'reportType'}" ></t:dictSelect> 
						<span class="Validform_checktip"></span>
					</td>			
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							报表名称:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="reportName" type="list"  extendJson="{id:'reportName'}"
						dictTable="t_report_dic"
						dictField="report_name" dictText="report_type" defaultVal="${reportMonitorPage.reportName}" hasLabel="false"
						title="报表名称" datatype="*" ></t:dictSelect>
					   <span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							负责人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="userId" name="userId" 
							   value="${reportMonitorPage.userId}" >  (以顿号隔开)
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							负责人姓名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="userName" name="userName" 
							   value="${reportMonitorPage.userName}" datatype="*"> (以顿号隔开)
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							告警时间:
						</label>
					</td>
					<td class="value">
						<input class="easyui-datebox" id="warnTime" name="warnTime"  style="width:200px"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
							   value="<fmt:formatDate value="${reportMonitorPage.warnTime}"  type="date" pattern="yyyy-MM-dd"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr style="display:none">
					<td align="right">
						<label class="Validform_label">
							是否完成:
						</label>
					</td>
					<td class="value">
					    <t:dictSelect field="isDone" type="list"  datatype="*"
									typeGroupCode="sf_yn" defaultVal="N" 
									hasLabel="false"  title="是否完成"></t:dictSelect> 
						<span class="Validform_checktip"></span>
					</td>
				</tr> 
			 	<tr>
					<td align="right" hidden="hidden">
						<label class="Validform_label">
							上一次告警时间:
						</label>
					</td>
					<td class="value" hidden="hidden">
						<input class="inputxt" id="lastWarnTime" name="lastWarnTime" ignore="ignore"
							   value="${reportMonitorPage.lastWarnTime}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right" hidden="hidden">
						<label class="Validform_label">
							本次告警时间:
						</label>
					</td>
					<td class="value" hidden="hidden">
						<input class="inputxt" id="currentWarnTime" name="currentWarnTime" ignore="ignore"
							   value="${reportMonitorPage.currentWarnTime}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right" hidden="hidden">
						<label class="Validform_label">
							下次告警时间:
						</label>
					</td>
					<td class="value" hidden="hidden">
						<input class="inputxt" id="nextWarnTime" name="nextWarnTime" ignore="ignore"
							   value="${reportMonitorPage.nextWarnTime}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script type="text/javascript">
  
  </script>