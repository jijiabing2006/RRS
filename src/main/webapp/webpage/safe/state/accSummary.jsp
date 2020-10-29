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
.acc-databk-image{
background-image: -ms-linear-gradient(left top,  #f3fad9,#fff);
    background-image: -moz-linear-gradient(left top,  #f3fad9,#fff);
    background-image: -webkit-gradient(linear, left top, left bottom, from(#f3fad9), to(#fff));
    background-image: -webkit-linear-gradient(left top,  #f3fad9,#fff);
    background-image: -o-linear-gradient(left top,  #f3fad9,#fff);
    background-image: linear-gradient(left top,  #f3fad9,#fff);
}
</style>
<h6>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;营业日期:${accSummary['ACCCA'].importdate}</h6>


<div class="blankheight "></div>


<div class=" warning content acc-databk-image" style="margin:2px  0 0 0;width: 750px">
	<h5>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账户开关户信息 <b> 总数 </b>:${accSummary['ACCCA'].counts}笔。
	</h5>
	<ul style="text-align:left;size:100px;">
		<b> 编辑 </b>:
		<li>完成: <b>${accSummary['ACCCA'].editnum}</b>笔。
		</li>
		<li>未完成: <strong>${accSummary['ACCCA'].uneditnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b> 审核 </b>:
		<li>已审核: <b> ${accSummary['ACCCA'].vnum}</b>笔。
		</li>
		<li>未审核: <strong> ${accSummary['ACCCA'].unvnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b>反馈状态 </b>:
		<li>等待: <b>${accSummary['ACCCA'].waitnum}</b>笔。
		</li>
		<li>接收成功: <b>${accSummary['ACCCA'].vinnum}</b>笔。
		</li>
		<li>接收失败: <strong>${accSummary['ACCCA'].failnum}</strong>笔。
		</li>
	</ul>
</div>
<div class="blankheight " style="margin:2px  0 0 0"></div>

<div class=" warning content acc-databk-image"  style="margin:2px  0 0 0;width: 750px">
<h5>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账户收支余信息</i><b> 总数 </b>:${accSummary['ACCCB'].counts}笔。
</h5>
	<ul style="text-align:left;size:100px;">
		<b> 编辑 </b>:
		<li>完成: <b> ${accSummary['ACCCB'].editnum}</b>笔。
		</li>
		<li>未完成: <strong>${accSummary['ACCCB'].uneditnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b>审核 </b>:
		<li>已审核:<b>${accSummary['ACCCB'].vnum}</b>笔。
		</li>
		<li>未审核:<strong>${accSummary['ACCCB'].unvnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:10px;">
		<b> 反馈状态 </b>:
		<li>等待:<b>${accSummary['ACCCB'].waitnum}</b>笔。
		</li>
		<li>接收成功:<b>${accSummary['ACCCB'].vinnum}</b>笔。
		</li>
		<li>接收失败:<strong>${accSummary['ACCCB'].failnum}</strong>笔。
		</li>
	</ul>
</div>
<div class="blankheight " style="margin:2px  0 0 0"></div>
