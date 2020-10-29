<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
		<t:datagrid name="fList" title="文件下载"
			actionUrl="frController.do?frtemplateList" idField="id" fit="true">
			<t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="模板名称" field="cptname" query="true"></t:dgCol>
			<t:dgCol title="文件后缀名" field="extend" query="false"></t:dgCol>
			<t:dgCol title="创建日期" field="createDate"
				formatter="yyyy-MM-dd hh:mm:ss" query="false" queryMode="group"></t:dgCol>
			<t:dgCol title="创建人名称" field="createName" query="false"
				queryMode="group"></t:dgCol>
			<t:dgCol title="更新日期" field="updateDate"
				formatter="yyyy-MM-dd hh:mm:ss" query="false" queryMode="group"></t:dgCol>
			<t:dgCol title="更新人名称" field="updateName" query="false"
				queryMode="group"></t:dgCol>
				<t:dgCol title="所属机构" field="regorg" dictionary="regorg" query="true" queryMode="single"></t:dgCol>
			<t:dgCol title="操作" field="opt"></t:dgCol>
			   <t:dgFunOpt operationCode="goDdata" title="编辑"   funname="goUpdate(id)"  />
			<t:dgDefOpt url="frController.do?viewFile&fileid={id} " title="下载"></t:dgDefOpt>
			<t:dgDelOpt operationCode="del" title="common.delete"
				url="frController.do?doDel&id={id}" />
			<t:dgToolBar title="模板文件录入" icon="icon-add" funname="add"
				url="frController.do?addFiles"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>
 <script type="text/javascript">
function goUpdate(id) {
	createwindow('编辑', 'frController.do?goUpdate&id=' + id+'&type=B',800,300);
} 
 </script>