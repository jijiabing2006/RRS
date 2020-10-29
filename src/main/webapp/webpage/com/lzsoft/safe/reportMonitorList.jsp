<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:base type="jquery,easyui,tools,DatePicker,select2,prohibit"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="reportMonitorList" title="报表监控" actionUrl="reportMonitorController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="报表名称" field="reportName" ></t:dgCol>
   <t:dgCol title="报表类型" field="reportType" ></t:dgCol>
   <t:dgCol title="负责人" field="userId" ></t:dgCol>
   <t:dgCol title="告警时间" field="warnTime" ></t:dgCol>
   <t:dgCol title="是否完成" field="isDone" ></t:dgCol>
   <t:dgCol title="上一次告警时间" field="lastWarnTime" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="reportMonitorController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="reportMonitorController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="reportMonitorController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="reportMonitorController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>