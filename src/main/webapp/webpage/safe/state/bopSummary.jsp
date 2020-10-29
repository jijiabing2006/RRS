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
.bop-databk-image{
background-image: -ms-linear-gradient(top,  #fff9e6,#fff);
    background-image: -moz-linear-gradient(top,  #fff9e6,#fff);
    background-image: -webkit-gradient(linear, left top, left bottom, from(#fff9e6), to(#fff));
    background-image: -webkit-linear-gradient(top,  #fff9e6,#fff);
    background-image: -o-linear-gradient(top,  #fff9e6,#fff);
    background-image: linear-gradient(top,  #fff9e6,#fff);
}
</style>
<h6>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;营业日期:${bopSummary['BOPA'].importdate}</h6>


<div class="blankheight "></div>


<div class=" warning content bop-databk-image" style="margin:2px  0 0 0">
	<h5>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;涉外收入申报单 <b> 总数 </b>:${bopSummary['BOPA'].counts}笔。
	</h5>
	<ul style="text-align:left;size:100px;">
		<b> 编辑 </b>:
		<li>完成: <b>${bopSummary['BOPA'].editnum}</b>笔。
		</li>
		<li>未完成: <strong>${bopSummary['BOPA'].uneditnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b> 审核 </b>:
		<li>已审核: <b> ${bopSummary['BOPA'].vnum}</b>笔。
		</li>
		<li>未审核: <strong> ${bopSummary['BOPA'].unvnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b>反馈状态 </b>:
		<li>等待: <b>${bopSummary['BOPA'].waitnum}</b>笔。
		</li>
		<li>接收成功: <b>${bopSummary['BOPA'].vinnum}</b>笔。
		</li>
		<li>接收失败: <strong>${bopSummary['BOPA'].failnum}</strong>笔。
		</li>
	</ul>
</div>
<div class="warning content bop-databk-image" style="margin:-302px  0 0 255px">
	<h5>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;境外汇款申请书 <b> 总数 </b>:${bopSummary['BOPB'].counts}笔。
	</h5>
	<ul style="text-align:left;size:100px;">
		<b> 编辑 </b>:
		<li>完成: <b>${bopSummary['BOPB'].editnum}</b>笔。
		</li>
		<li>未完成: <strong>${bopSummary['BOPB'].uneditnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b> 审核 </b>:
		<li>已审核: <b> ${bopSummary['BOPB'].vnum}</b>笔。
		</li>
		<li>未审核: <strong> ${bopSummary['BOPB'].unvnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b>反馈状态 </b>:
		<li>等待: <b>${bopSummary['BOPB'].waitnum}</b>笔。
		</li>
		<li>接收成功: <b>${bopSummary['BOPB'].vinnum}</b>笔。
		</li>
		<li>接收失败: <strong>${bopSummary['BOPB'].failnum}</strong>笔。
		</li>
	</ul>
</div>
<div class="warning content bop-databk-image" style="margin:-302px 0 0 510px">
	<h5>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对外付款/承兑通知书 <b> 总数 </b>:${bopSummary['BOPC'].counts}笔。
	</h5>
	<ul style="text-align:left;size:100px;">
		<b> 编辑 </b>:
		<li>完成: <b>${bopSummary['BOPC'].editnum}</b>笔。
		</li>
		<li>未完成: <strong>${bopSummary['BOPC'].uneditnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b> 审核 </b>:
		<li>已审核: <b> ${bopSummary['BOPC'].vnum}</b>笔。
		</li>
		<li>未审核: <strong> ${bopSummary['BOPC'].unvnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b>反馈状态 </b>:
		<li>等待: <b>${bopSummary['BOPC'].waitnum}</b>笔。
		</li>
		<li>接收成功: <b>${bopSummary['BOPC'].vinnum}</b>笔。
		</li>
		<li>接收失败: <strong>${bopSummary['BOPC'].failnum}</strong>笔。
		</li>
	</ul>
</div>
<div class="blankheight " style="margin:2px  0 0 0"></div>

<div class="warning content bop-databk-image" style="margin:2px  0 0 0">
	<h5>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;境内收入申报单 <b> 总数 </b>:${bopSummary['BOPD'].counts}笔。
	</h5>
	<ul style="text-align:left;size:100px;">
		<b> 编辑 </b>:
		<li>完成: <b>${bopSummary['BOPD'].editnum}</b>笔。
		</li>
		<li>未完成: <strong>${bopSummary['BOPD'].uneditnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b> 审核 </b>:
		<li>已审核: <b> ${bopSummary['BOPD'].vnum}</b>笔。
		</li>
		<li>未审核: <strong> ${bopSummary['BOPD'].unvnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b>反馈状态 </b>:
		<li>等待: <b>${bopSummary['BOPD'].waitnum}</b>笔。
		</li>
		<li>接收成功: <b>${bopSummary['BOPD'].vinnum}</b>笔。
		</li>
		<li>接收失败: <strong>${bopSummary['BOPD'].failnum}</strong>笔。
		</li>
	</ul>
</div>
<div class="warning content bop-databk-image" style="margin:-302px  0 0 255px">
	<h5>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;境内汇款申请书 <b> 总数 </b>:${bopSummary['BOPE'].counts}笔。
	</h5>
	<ul style="text-align:left;size:100px;">
		<b> 编辑 </b>:
		<li>完成: <b>${bopSummary['BOPE'].editnum}</b>笔。
		</li>
		<li>未完成: <strong>${bopSummary['BOPE'].uneditnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b> 审核 </b>:
		<li>已审核: <b> ${bopSummary['BOPE'].vnum}</b>笔。
		</li>
		<li>未审核: <strong> ${bopSummary['BOPE'].unvnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b>反馈状态 </b>:
		<li>等待: <b>${bopSummary['BOPE'].waitnum}</b>笔。
		</li>
		<li>接收成功: <b>${bopSummary['BOPE'].vinnum}</b>笔。
		</li>
		<li>接收失败: <strong>${bopSummary['BOPE'].failnum}</strong>笔。
		</li>
	</ul>
</div>
<div class="warning content bop-databk-image" style="margin:-302px 0 0 510px">
	<h5>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;境内付款/承兑通知书 <b> 总数 </b>:${bopSummary['BOPF'].counts}笔。
	</h5>
	<ul style="text-align:left;size:100px;">
		<b> 编辑 </b>:
		<li>完成: <b>${bopSummary['BOPF'].editnum}</b>笔。
		</li>
		<li>未完成: <strong>${bopSummary['BOPF'].uneditnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b> 审核 </b>:
		<li>已审核: <b> ${bopSummary['BOPF'].vnum}</b>笔。
		</li>
		<li>未审核: <strong> ${bopSummary['BOPF'].unvnum}</strong>笔。
		</li>
	</ul>
	<ul style="text-align:left;size:100px;">
		<b>反馈状态 </b>:
		<li>等待: <b>${bopSummary['BOPF'].waitnum}</b>笔。
		</li>
		<li>接收成功: <b>${bopSummary['BOPF'].vinnum}</b>笔。
		</li>
		<li>接收失败: <strong>${bopSummary['BOPF'].failnum}</strong>笔。
		</li>
	</ul>
</div>
<div class="blankheight " style="margin:2px  0 0 0"></div>

