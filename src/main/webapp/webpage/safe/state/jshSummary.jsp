<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.css"
	type="text/css"></link>
<style>
.content {
	border: 1px solid #D5D5D5;
	-moz-columns: 400px;
	-webkit-columns: 400px;
	columns: 400px;
	height: 300px;
	width: 250px;
}

.blankheight {
	height: 1px;
	padding: 0px;
	background-color: #D5D5D5;
	overflow: hidden;
}

.warning   strong {
	color: #ff0000;
}
.jsh-databk-image{
background-image: -ms-linear-gradient(left,  #f1f9fb,#fff);
    background-image: -moz-linear-gradient(left,  #f1f9fb,#fff);
    background-image: -webkit-gradient(linear, left top, left bottom, from(#f1f9fb), to(#fff));
    background-image: -webkit-linear-gradient(left,  #f1f9fb,#fff);
    background-image: -o-linear-gradient(left,  #f1f9fb,#fff);
    background-image: linear-gradient(left,  #f1f9fb,#fff);
}
</style>
<h6>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;营业日期:${jshSummary['JSHD'].importdate}</h6>

<div class="blankheight "></div>

<div class=" warning content jsh-databk-image" style="margin:2px  0 0 0;width: 750px">
	<h5>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;外汇账户内结汇信息 <b> 总数 </b>:${jshSummary['JSHD'].counts}笔。
	</h5>
	<ul style="text-align:left;size:100px;">
		<b> 编辑 </b>:
		<li>完成: <b>${jshSummary['JSHD'].editnum}</b>笔。
		</li>
		<li>未完成: <strong>${jshSummary['JSHD'].uneditnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b> 审核 </b>:
		<li>已审核: <b> ${jshSummary['JSHD'].vnum}</b>笔。
		</li>
		<li>未审核: <strong> ${jshSummary['JSHD'].unvnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b>反馈状态 </b>:
		<li>等待: <b>${jshSummary['JSHD'].waitnum}</b>笔。
		</li>
		<li>接收成功: <b>${jshSummary['JSHD'].vinnum}</b>笔。
		</li>
		<li>接收失败: <strong>${jshSummary['JSHD'].failnum}</strong>笔。
		</li>
	</ul>
</div>
<div class="blankheight " style="margin:2px  0 0 0"></div>

<div class=" warning content jsh-databk-image"  style="margin:2px  0 0 0;width: 750px">
	<h5>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;外汇账户内售汇信息</i><b> 总数 </b>:${jshSummary['JSHE'].counts}笔。
	</h5>
	<ul style="text-align:left;size:100px;">
		<b> 编辑 </b>:
		<li>完成: <b> ${jshSummary['JSHE'].editnum}</b>笔。
		</li>
		<li>未完成: <strong>${jshSummary['JSHE'].uneditnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b>审核 </b>:
		<li>已审核:<b>${jshSummary['JSHE'].vnum}</b>笔。
		</li>
		<li>未审核:<strong>${jshSummary['JSHE'].unvnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:10px;">
		<b> 反馈状态 </b>:
		<li>等待:<b>${jshSummary['JSHE'].waitnum}</b>笔。
		</li>
		<li>接收成功:<b>${jshSummary['JSHE'].vinnum}</b>笔。
		</li>
		<li>接收失败:<strong>${jshSummary['JSHE'].failnum}</strong>笔。
		</li>
	</ul>
</div>
<div class="blankheight " style="margin:2px  0 0 0"></div>
