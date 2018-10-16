<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<c:if test="${not empty errorMsg}">
<script>
$(function(){
	$.messager.alert('错误','${errorMsg}');
});
</script>
</c:if>
<table class="list-wapper">
	<tr class="list-header">
		<td>角色</td>
		<td>系统保留</td>
	</tr>
	<c:forEach var="item" items="${items}" varStatus="itemStatus" >
		<tr class="list-item">
			<td>
				<c:out value="${item.roleName}" />
			</td>
			<td>
				<c:out value="${item.systemMenuFlag}" />
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="8">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>
