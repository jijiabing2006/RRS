<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center" style="padding: 1px;">
	<t:datagrid name="timeTaskList" title="schedule.task.manage" actionUrl="jobTaskController.do?datagrid" 
 idField="id" fit="true" queryMode="group" pageSize="30" sortName="createTime" sortOrder="desc" autoLoadData="true"  >
	<t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="common.jobName" field="jobName" query="true" queryMode="single"></t:dgCol>
	<t:dgCol title="common.jobGroup" field="jobGroup" query ="true" queryMode="single"></t:dgCol>
	<t:dgCol title="running.state" field="jobStatus" replace="停止_0,运行_1"></t:dgCol>
	<t:dgCol title="cron.expression" field="cronExpression"  ></t:dgCol>
	<t:dgCol title="common.task.desc" field="description"></t:dgCol>
	<t:dgCol title="running.isConcurrent" field="isConcurrent" replace="否_0,是_1"></t:dgCol>
	<t:dgCol title="common.beanClass" field="beanClass"></t:dgCol>
	<t:dgCol title="common.springId" field="springId"></t:dgCol>
	<t:dgCol title="common.methodName" field="methodName"></t:dgCol>
	<t:dgCol title="common.createtime" field="createTime" formatter="yyyy-MM-dd" hidden="true"></t:dgCol>
	<t:dgCol title="common.updatetime" field="updateTime" formatter="yyyy-MM-dd" hidden="true"></t:dgCol>
	<t:dgCol title="common.operation" field="opt" width="100"></t:dgCol>
	<t:dgConfOpt title="common.start" url="jobTaskController.do?startOrStopTask&id={id}&jobStatus=1" message="确认运行任务" exp="jobStatus#eq#0"/>
	<t:dgConfOpt title="common.stop" url="jobTaskController.do?startOrStopTask&id={id}&jobStatus=0" message="确认停止" exp="jobStatus#eq#1"/>
	<t:dgConfOpt title="effective.immediately" url="jobTaskController.do?updateTime&id={id}" message="确认更新任务时间" exp="isEffect#eq#0"/>
	<t:dgDelOpt title="common.delete" url="jobTaskController.do?del&id={id}" />
	<t:dgToolBar title="common.add" icon="icon-add" url="jobTaskController.do?addorupdate" funname="add"></t:dgToolBar>
	<t:dgToolBar title="common.edit" icon="icon-edit" url="jobTaskController.do?addorupdate" funname="update"></t:dgToolBar>
	<t:dgToolBar title="common.view" icon="icon-search" url="jobTaskController.do?addorupdate" funname="detail"></t:dgToolBar>
</t:datagrid></div>
</div>
