<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="<c:url value="/content/systemUser/resetPassword" />" method="post">
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
			<td class="liner-box-left">旧密码:</td>
			<td class="liner-box-right">
				<input type="password" class="input-box" name="password"/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left">新密码:</td>
			<td class="liner-box-right">
				<input type="password" class="input-box" name="newPassword"/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left">重复新密码:</td>
			<td class="liner-box-right">
				<input type="password" class="input-box" name="newPasswordConfirm" />
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>