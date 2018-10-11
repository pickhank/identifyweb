<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
String resourceUrl = com.woodare.framework.utils.SysProperties.getInstance().getProperty(com.woodare.template.constant.SystemPropertiesConstant.CODE_ROOT_RESOURCE_URL, "http://open.mishua.cn");%>

<!DOCTYPE html>
<html lang="en-US">
<head>
<script>
	if (parent != window) {
		parent.location.reload()
	}
</script>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta charset="utf-8" />
<title>业务管理平台</title>
<link rel="stylesheet" type="text/css" href="<%=resourceUrl%>/resources/login/style_log.css">
<link rel="stylesheet" type="text/css" href="<%=resourceUrl%>/resources/login/style.css">
<script type="text/javascript" src="<%=resourceUrl%>/resources/js/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/login/particles.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/js/common/jquery.md5.js?1"></script>

<style>
.login_boder input.txt_input.input-box-code {
	width: 160px;
}

.input-box-image {
	height: 32px;
	float: right;
	border: 1px solid #cad2db;
	border-radius: 5px;
	cursor: pointer;
}

.error-message {
	text-align: center;
	color: red;
	text-shadow: 0 0 11px #FFF;
	font-size: 20px;
	font-weight: 700;
}

#particles-js {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background: url('<%=resourceUrl%>/resources/images/dark-grey-tile.png');
}
.login_logo h2 {
    font-size: 36px;
    color: #FFF;
}
</style>
</head>
<body class="login" mycollectionplug="bind">
	<div id="particles-js"></div>
	<div class="login_m">
		<div class="login_logo">
			<h2>运营管理平台</h2>
		</div>
		<c:if test="${not empty errorMsg}">
			<div class="login-line error-message">${errorMsg}</div>
		</c:if>
		<div class="login_boder">
			<div class="login_padding" id="login_model">
				<c:url value="/login" var="loginurl" />
				<form method="post" name="login" action="${loginurl }">
					<input type="hidden" name="password" >
					<h2>用户名</h2>
					<label>
						<input type="text" id="username" class="txt_input" placeholder="用户名" name="username" autocomplete="off">
					</label>
					<h2>密码</h2>
					<label>
						<input type="password" placeholder="密码" id="j_password" autocomplete="off" class="txt_input">
					</label>
					<%-- <h2>验证码</h2>
					<label>
						<input type="text" class="txt_input input-box-code" placeholder="验证码" name="regcode" autocomplete="off" />
						<img src="<c:url value='/regcode/renew' />?<%=(new java.util.Date().getTime())%>" class="input-box-image" onclick="this.src+=1" />
					</label> --%>
					<div class="rem_sub">
						<div class="rem_sub_l">
							<input type="checkbox" id="remember_me" name="_spring_security_remember_me">
							<label for="checkbox">下次自动登录</label>
						</div>
						<label>
							<input type="submit" class="sub_button" name="button" id="button" value="登录" style="opacity: 0.7;" onclick="doLogin();">
						</label>
					</div>
				</form>
			</div>
			<!--login_padding  Sign up end-->
		</div>
		<!--login_boder end-->
	</div>
	<!--login_m end-->
	<br>
	<br>
	<script>
		function doLogin() {
			$('input[name="password"]').val($.dmd5($('#j_password').val()));
			return true;
		}
	</script>
</body>
</html>