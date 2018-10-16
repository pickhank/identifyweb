<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%String resourceUrl = com.woodare.framework.utils.SysProperties.getInstance().getProperty(com.woodare.template.constant.SystemPropertiesConstant.CODE_ROOT_RESOURCE_URL, "http://open.mishua.cn");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="msapplication-tap-highlight" content="no" />
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Page-Enter" content="blendTrans(duration=1)" />
<meta http-equiv="Page-Exit" content="blendTrans(duration=1)" />
<meta name="format-detection" content="telephone=no" />
<meta name="format-detection" content="email=no" />
<meta name="format-detection" content="address=no" />
<meta charset="utf-8" />
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<!-- link rel="shortcut icon" href="img/favicon.png" type="image/x-icon" / -->
<link rel="stylesheet" href="<%=resourceUrl%>/resources/m/css/weui.min.css?v=11" />
<link rel="stylesheet" href="<%=resourceUrl%>/resources/m/css/common.css?v=11" />
<style>
</style>
<!-- load included css files -->
<tiles:importAttribute name="styles" scope="request" ignore="true" />
<c:if test="${not empty styles }">
<c:forEach var="cssStyle" items="${styles}">
	<link rel="stylesheet" type="text/css" href="<%=resourceUrl%>${cssStyle}"></link>
</c:forEach>
</c:if>
<script>
window.CMS_URL = '<%=request.getContextPath() %>/';
window.PLATFORM_CODE = '${wxUser.unionid}';
</script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/m/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/m/js/jquery-browser.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/m/js/gotoTop.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/m/js/common.js"></script>
<!-- load included js files -->
<tiles:importAttribute name="scripts" scope="request" ignore="true" />
<c:if test="${not empty scripts }">
<c:forEach var="js" items="${scripts}">
	<script type="text/javascript" src="<%=resourceUrl%>${js}"></script>
</c:forEach>
</c:if>
</head>
<body unselectable="on" onselectstart="return false;">
	<header>
		<div style="height: 40px; background: #50ad00; color: #FFF; font-size: 1.6rem; text-align: center; line-height: 40px;position:relative;">
			平台运营
			<a id="menu-link" style="position: absolute; right: 10px; line-height: normal; height: 40px;" href="javascript:$('#menu-details').toggle('slow')" >
				<img src="<%=resourceUrl%>/resources/m/img/menu.png" style="height:32px; margin-top: 4px;">
			</a>
		</div>
		<div id="menu-details" style="border-bottom: 2px solid #267ad2;">
			<div class="weui-cells" style="margin-top: 0;">
				<a class="weui-cell weui-cell_access" href="<c:url value="/"/>">
					<div class="weui-cell__bd">
						<p>首页</p>
					</div>
					<div class="weui-cell__ft">
					</div>
				</a>
			</div>
			<div class="weui-cells__title">快捷</div>
			<div class="weui-cells">
				<a class="weui-cell weui-cell_access" href="<c:url value="/agent/downNoCardInvoice/mch"/>">
					<div class="weui-cell__bd">
						<p>商户</p>
					</div>
					<div class="weui-cell__ft">
					</div>
				</a>
				<a class="weui-cell weui-cell_access" href="<c:url value="/agent/recon/index"/>">
					<div class="weui-cell__bd">
						<p>对账单</p>
					</div>
					<div class="weui-cell__ft">
					</div>
				</a>
				<a class="weui-cell weui-cell_access" href="<c:url value="/agent/downNoCardInvoice/index"/>">
					<div class="weui-cell__bd">
						<p>无卡交易</p>
					</div>
					<div class="weui-cell__ft">
					</div>
				</a>
				<a class="weui-cell weui-cell_access" href="<c:url value="/agent/downNoCardInvoice/sum"/>">
					<div class="weui-cell__bd">
						<p>无卡交易汇总</p>
					</div>
					<div class="weui-cell__ft">
					</div>
				</a>
			</div>
			<div class="weui-cells__title">系统设置</div>
			<div class="weui-cells">
				<a class="weui-cell weui-cell_access" href="<c:url value="/agent/agentUser/resetPassword"/>">
					<div class="weui-cell__bd">
						<p>修改密码</p>
					</div>
					<div class="weui-cell__ft">
					</div>
				</a>
			</div>
			<div class="button-sp-area" style="padding: 30px;">
				<a href="<c:url value='/system/user/logout'/>" class="weui-btn weui-btn_warn">退出系统</a>
			</div>
		</div>
		<script>
			$("#menu-details").hide();
		</script>
	</header>
	<tiles:insertAttribute name="body" ignore="true" />
	<footer class="page__ft">
		Copyright © 2017 运营平台
	</footer>
</body>
</html>