<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="plug-in/bootstrap/css/bootstrap.css"
	type="text/css"></link>
<style>
.border {
	border: 2px solid #DDDDDD;
}

.content {
	border: 1px solid #eee;
	-moz-columns: 400px;
	-webkit-columns: 400px;
	columns: 400px;
}

.auto {
	table-layout: auto;
	width: 75%;
}

.fixed {
	table-layout: fixed;
	width: 75%;
}


.blankline {
	height: 1px;
	margin: 5px auto 5px auto;
	padding: 0px;
	background-color: #D5D5D5;
	overflow: hidden;
}

.warning   strong {
	color: #ff0000;
}
.databg-image{
}

.databk-image{
background-image: -ms-linear-gradient(left top,  #f8f8f8,#fff);
    background-image: -moz-linear-gradient(left top,  #f8f8f8,#fff);
    background-image: -webkit-gradient(linear, left top, left bottom, from(#f8f8f8), to(#fff));
    background-image: -webkit-linear-gradient(left top,  #f8f8f8,#fff);
    background-image: -o-linear-gradient(left top,  #f8f8f8,#fff);
    background-image: linear-gradient(left top,  #f8f8f8,#fff);
}

</style>
<div class="blankline"></div>
<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="plug-in/login/images/data_72.png" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数据导入概述&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h5>
<div class="blankline"></div>
<div style="margin-top: 14px;" >
	<div class="border databk-image">
		<ul style="text-align:left;size:100px;margin: 30px ;">
			<li><b>最后导入T24数据的营业日期</b>:<span id="t24">${dataSummary.lastt24importdate}</span>
			</li>
			<div class="blankline"></div>
			<li><b>是否有需要导入的源文件</b>:${dataSummary.importmessage}</li>
			<div class="blankline"></div>

			<li><b>最后导入Summit数据的营业日期</b>:<span id="summit">${dataSummary.lastsummitimportdate}</span>
			</li>
			<div class="blankline"></div>

			<li><b>Summit导入状态</b>:<span>${dataSummary.t24summitdatediff}</span>
			</li>
		</ul>
	</div>
</div>
	<div class="blankline"></div>
<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数据提取概述</h5>
<div style="margin-top: 14px;">
	<div class="border databk-image">
		<ul style="text-align:left;size:100px;margin: 30px;">
			<li><img src="plug-in/login/images/ACC_36.png" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>外汇账号提取情况</b>:${dataSummary.accextractstate}</li>
			<div class="blankline"></div>
			<li><img src="plug-in/login/images/BOP_36.png" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>国际收支提取情况</b>:${dataSummary.bopextractstate}</li>
			<div class="blankline"></div>
			<li><img src="plug-in/login/images/JSH_36.png" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>账户内结售汇提取情况</b>:${dataSummary.jshextractstate}</li>
			<div class="blankline"></div>
		</ul>
		</ul>
	</div>
</div>
</div>
