<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.liner-box-left {
	padding: 2px 10px;
	width:25%; 
}
.liner-box-right {
	padding: 0;
}
</style>
<div style="padding:50px 10px;margin: 0 auto; width: 600px;">
<div class="ms-col-10 ms-border">
<div class="ms-header">修改密码</div>
<form action="<c:url value="/agent/agentUser/resetPassword" />" method="post">
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
			<td class="liner-box-left">确认密码:</td>
			<td class="liner-box-right">
				<input type="password" class="input-box" name="newPasswordConfirm" />
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<br>
				<input type="submit" value="提交" class="btn" />
				<br>
			</td>
		</tr>
	</table>
</form>
</div>
</div>