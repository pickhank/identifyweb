<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty status}">
	<script>
		$(function() {
		<c:if test="${status}">
			_WOO.c.alertInfo("操作成功");
		</c:if>
		<c:if test="${not status}">
			_WOO.c.alertError("${error}");
		</c:if>
		});
	</script>
</c:if>