<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String resourceUrl = com.woodare.framework.utils.SysProperties.getInstance().getProperty(com.woodare.template.constant.SystemPropertiesConstant.CODE_ROOT_RESOURCE_URL, "http://open.mishua.cn");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8" />
<title>业务管理平台</title>
<%-- 
<link href="<%=resourceUrl%>/resources/js/easyui/themes/metro/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=resourceUrl%>/resources/js/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
 --%>
<link rel="stylesheet" type="text/css" href="<%=resourceUrl%>/resources/jquery-easyui-1.5.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=resourceUrl%>/resources/jquery-easyui-1.5.5/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=resourceUrl%>/resources/css/common/box.css?v=14" />

<%-- --%><link rel="stylesheet" type="text/css" href="<%=resourceUrl%>/resources/jquery-ui-1.12.1.custom/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="<%=resourceUrl%>/resources/jquery-ui-1.12.1.custom/jquery-ui.theme.min.css"> 
<%-- <link rel="stylesheet" type="text/css" href="<%=resourceUrl%>/resources/css/ui-lightness/jquery-ui-1.9.1.custom.min.css" > --%>
<link rel="stylesheet" type="text/css" href="<%=resourceUrl%>/resources/js/jquery/jquery.datetimepicker.css" >
<!-- <add-attribute value="/resources/js/jquery/jquery-ui-1.9.1.custom.min.js" />
			<add-attribute value="/resources/js/jquery/jquery.ui.datepicker-zh-CN.js" />
			<add-attribute value="/resources/js/jquery/jquery.datetimepicker.js" />
			<add-attribute value="/resources/css/ui-lightness/jquery-ui-1.9.1.custom.min.css" />
			<add-attribute value="/resources/js/jquery/jquery.datetimepicker.css" /> -->

<script type="text/javascript" src="<%=resourceUrl%>/resources/jquery-easyui-1.5.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/jquery-easyui-1.5.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/jquery-easyui-1.5.5/locale/easyui-lang-zh_CN.js"></script>

<%-- <script type="text/javascript" src="<%=resourceUrl%>/resources/js/jquery/jquery-ui-1.9.1.custom.min.js"></script> --%>
<%-- --%><script type="text/javascript" src="<%=resourceUrl%>/resources/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script> 
<script type="text/javascript" src="<%=resourceUrl%>/resources/js/jquery/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/js/jquery/jquery.ui.datepicker-zh-CN.js"></script>
<%-- 
<script type="text/javascript" src="<%=resourceUrl%>/resources/js/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=resourceUrl%>/resources/js/easyui/easyui-lang-zh_CN.js"></script> --%>

<script type="text/javascript" src="<%=resourceUrl%>/resources/js/cms/common.js?v=14"></script>
<style>
body .ui-datepicker td {
    padding: 2px;
}
html, body {
    font-size: 14px;
}
</style>
<script>
$(function() {
	$(".item-delete-link").click(function() {
		if (parent.Popup) parent.Popup.show($(this).attr("href"), true).always(function() {
			$.messager.alert('系统提示','删除成功!','info',function() {
				$(".search-form").submit();
			});
		});
	});
	$(".export-form").submit(function() {
		var params = $(".search-form").serializeArray();
		$(".export-form input[type='hidden']").remove();
		var $form = $(".export-form");
		$form.append('<input type="hidden" name="doExportFlag" value="true"> ');
		$(params).each(function(){
			$form.append('<input type="hidden" name="' + this.name + '" value="' + this.value + '"> ');
		});
	});
});
window.CMS_URL = '<%=request.getContextPath() %>/';
</script>
<!-- load included css files -->
<tiles:importAttribute name="styles" scope="request" ignore="true" />
<c:if test="${not empty styles }">
<c:forEach var="cssStyle" items="${styles}">
	<link rel="stylesheet" type="text/css" href="<%=resourceUrl%>${cssStyle}"></link>
</c:forEach>
</c:if>

<!-- load included js files -->
<tiles:importAttribute name="scripts" scope="request" ignore="true" />
<c:if test="${not empty scripts }">
<c:forEach var="js" items="${scripts}">
	<script type="text/javascript" src="<%=resourceUrl%>${js}"></script>
</c:forEach>
</c:if>
</head>

<body id='cbid'>
	<div class="box-body"><tiles:insertAttribute name="body" ignore="true" /></div>
	<div class="box-fotter"></div>
</body>
</html>