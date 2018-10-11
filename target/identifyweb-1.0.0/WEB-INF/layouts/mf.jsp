<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
	String resourceUrl = com.woodare.framework.utils.SysProperties.getInstance().getProperty(com.woodare.template.constant.SystemPropertiesConstant.CODE_ROOT_RESOURCE_URL, "http://open.mishua.cn");
%>
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
<link rel="stylesheet" href="<%=resourceUrl %>/resources/mf/css/common.css?v=11" />

<!-- load included css files -->
<tiles:importAttribute name="styles" scope="request" ignore="true" />
<c:if test="${not empty styles }">
<c:forEach var="cssStyle" items="${styles}">
	<link rel="stylesheet" type="text/css" href="<%=resourceUrl %>${cssStyle}"></link>
</c:forEach>
</c:if>
<script>
window.CMS_URL = '<%=request.getContextPath() %>/';
window.PLATFORM_CODE = '${wxUser.unionid}';
</script>
<script type="text/javascript" src="<%=resourceUrl %>/resources/js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=resourceUrl %>/resources/mf/js/common/common.js?v=13"></script>
<script type="text/javascript" src="<%=resourceUrl %>/resources/mf/js/common/dao.js?v=12"></script>

<!-- load included js files -->
<tiles:importAttribute name="scripts" scope="request" ignore="true" />
<c:if test="${not empty scripts }">
<c:forEach var="js" items="${scripts}">
	<script type="text/javascript" src="<%=resourceUrl %>${js}"></script>
</c:forEach>
</c:if>
</head>
<body unselectable="on" onselectstart="return false;">
	<tiles:insertAttribute name="body" ignore="true" />
</body>
</html>