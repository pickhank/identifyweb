<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %><%
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
<link href="<%=resourceUrl%>/resources/js/easyui/themes/gray/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=resourceUrl%>/resources/js/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="<%=resourceUrl%>/resources/css/common/box.css" rel="stylesheet" type="text/css" />
<style>
* {
	padding: 0;
	margin: 0;
}

a {
	text-decoration: none;
	cursor: pointer;
}

html,body {
	width: 100%;
	position: relative;
	font-family: "microsoft yahei";
}

html {
	width: 990px;
	margin: 0 auto;
	background: #fdfdfd;
	background-image: url('<c:url value='/resources/images/whiteline_pattern.png'/>'), url('<c:url value='/resources/images/white_noise.jpg'/>');
	background-repeat: repeat;
}

.clear:after {
	content: '';
	display: block;
	height: 0;
	clear: both;
	visibility: hidden;
}

.pull-left {
	float: left;
}

.pull-right {
	float: right;
}
/* top header style*/
.header-top-wapper {
	border-top: 3px solid #2B8995;
	height: 150px;
}

.error-message {
	height: auto;
	color: red;
	padding: 15px 5% 0;
}

.login-btn {
	color: #FFFFFF;
	font-weight: 900;
	background: #2B8995;
	font-size: 1.1rem;
	padding: 5px 30px;
	border: none;
}

.login-btn:hover {
	background: #3a485d;
}

.nav,.nav li {
	float: left;
}

.nav {
	line-height: 30px;
	width: 100%;
	background: #2B8995;
	list-style: none;
	margin-bottom: 5px;
}

.nav li {
	width: 13%;
	text-align: center;
	color: #333333;
	margin-right: 1px;
}
.nav li.nav-item-right {
	width: 6%;
	float:right;
}
.nav li.nav-item-right-name {
	width: auto;
}

.nav li a {
	color: #FFFFFF;
	display: block;
}

.nav li:not(.active):not(.nav-item-right-name):hover a {
	background: #3a485d;
}

.nav li.active a {
	background: #3a485d;
}
.register-link {
	float:right;
	color:#2B8995;
}
.register-link:hover {
	text-decoration: underline;
}
.portal-footer {
	border-top: 1px solid #DEDEDE;
	margin-top: 5px;
	padding-top: 5px;
	color: #a0a0a0;
	font-size: 0.8rem;
}
</style>

<!-- load included css files -->
<tiles:importAttribute name="styles" scope="request" ignore="true" />
<c:if test="${not empty styles }">
<c:forEach var="cssStyle" items="${styles}">
	<c:url value="${cssStyle}" var="url"/>
	<link rel="stylesheet" type="text/css" href="<c:out value="${url}"/>"></link>
</c:forEach>
</c:if>
<script type="text/javascript" src="<%=resourceUrl%>/resources/js/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/js/easyui/easyui-lang-zh_CN.js"></script>

<!-- load included js files -->
<tiles:importAttribute name="scripts" scope="request" ignore="true" />
<c:if test="${not empty scripts }">
<c:forEach var="js" items="${scripts}">
	<c:url value="${js}" var="url"/>
	<script type="text/javascript" src="<c:out value="${url}"/>?v=2"></script>
</c:forEach>
</c:if>
</head>
<body>
	<div class="header-top-wapper clear">
		
	</div>
	<div class="clear">
		<ul class="top-nav-wapper nav">
			<li class="nav-item"></li>
			<sec:authorize ifAnyGranted="ROLE_USER">
			<li class="nav-item-right"><a href="<c:url value="/system/user/logout" />" >退出</a></li>
			<li class="nav-item-right nav-item-right-name"><a onclick="return false;">你好：<sec:authentication property="principal.username" />!</a></li>
			</sec:authorize>
		</ul>
	</div>
	<tiles:insertAttribute name="body" />
	<div class="portal-footer clear">
		版权所有© (2014-2024) 
	</div>
</body>
</html>