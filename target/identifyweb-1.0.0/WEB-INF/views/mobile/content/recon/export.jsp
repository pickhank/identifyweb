<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.input-box-short {
	width:43%;
}
.liner-box-left {
	padding: 2px 10px;
}
.liner-box-right {
	padding: 0;
}
</style>
<form class="search-form" style="padding-right: 88px; box-sizing: border-box;" target="_blank" action="<c:url value="/content/recon/export" />" method="post">
	<label for="dateCate">时间：</label>
	<select name="dateCate" class="input-box search-input-box">
		<option value="CUSTOM" <c:if test="${'CUSTOM' eq search.dateCate}">selected</c:if>>自定义
		<option value="TODAY" <c:if test="${'TODAY' eq search.dateCate}">selected</c:if>>今天
		<option value="YESTERDAY" <c:if test="${'YESTERDAY' eq search.dateCate}">selected</c:if>>昨天
		<option value="LAST_7_DAYS" <c:if test="${'LAST_7_DAYS' eq search.dateCate}">selected</c:if>>过去七天
		<option value="LAST_30_DAYS" <c:if test="${'LAST_30_DAYS' eq search.dateCate}">selected</c:if>>过去30天
	</select>
	<label for="startDate" class="custom-d-cate">From：</label>
	<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="开始时间" name="startDate" value="<fmt:formatDate value="${search.startDate}" pattern="yyyy/MM/dd"/>"></input>
	<label for="endDate" class="custom-d-cate">To：</label>
	<input type="text" class="input-box search-input-box date-picker custom-d-cate" placeholder="结束时间" name="endDate" value="<fmt:formatDate value="${search.endDate}" pattern="yyyy/MM/dd"/>"></input>
	<script>
		$(".date-picker").datepicker($.datepicker.regional[ "zh-CN" ]);
		$("select[name='dateCate']").change(function(){
			$(".custom-d-cate")[$(this).val() == 'CUSTOM' ? "show" : "hide"]();
		}).change();
	</script>
	<input type="submit" value="导出" class="btn"></input>
	<a href="<c:url value="/content/recon/index" />" class="btn btn-orange pull-right">返回列表</a>
</form>