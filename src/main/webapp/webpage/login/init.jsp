<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript" src="plug-in/jquery/jquery-1.12.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$.browser.mozilla = /firefox/.test(navigator.userAgent.toLowerCase());
		$.browser.webkit = /webkit/.test(navigator.userAgent.toLowerCase());
		$.browser.opera = /opera/.test(navigator.userAgent.toLowerCase());
		$.browser.msie = /msie/.test(navigator.userAgent.toLowerCase());

		var browserversion = "";
		//IE8+浏览器
		if ($.browser.msie) {
			browserversion = "IE" + $.browser.version;
		}
		//谷歌浏览器
		if ($.browser.webkit) {
			browserversion = "Chrome" + $.browser.version;
		}
		//火狐浏览器
		if ($.browser.mozilla) {
			browserversion = "Mozilla Firefox" + $.browser.version;
		}
		//欧朋浏览器
		if ($.browser.opera) {
			browserversion = "Opera" + $.browser.version;
		}

		window.location.href = "loginController.do?login";

	});
</script>
</head>
<body>
</body>
</html>