<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.input-box-short {
	width: 43%;
}
</style>
<form action="<c:url value="/user/index" />" method="post">
	<input type="hidden" name="id" value="${item.id}" />
	<input type="hidden" name="isAdminFlag" value="${item.isAdminFlag}" />
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<c:if test="${not empty success}">
			<tr class="liner-info">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${success}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">用户名:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="用户名" name="username" value="${item.username}" readonly />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">邮箱:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="邮箱" name="email" value="${item.email}" />
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>
