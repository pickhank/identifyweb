<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
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
<form method="post" enctype="multipart/form-data" action="<c:url value="/admin/user/add" />">
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">用户名</td>
			<td class="liner-box-right">
				<c:if test="${empty item.id}">
					<input class="input-box" type="text" name="username"  value="${item.username}">
				</c:if>
				<c:if test="${not empty item.id}">
					<input class="input-box" type="text" name="username"  value="${item.username}" readonly>
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">邮箱</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="email" value="${item.email}" >
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">密码</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="password" value="">
				<div>不填写为保持原密码，新建时不填则和用户名相同</div>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">用户角色</td>
			<td class="liner-box-right">
				<select name="userRole" class="input-box">
					<option value="">全部
					<option value="SUPERVISOR" <c:if test="${search.userRole eq 'SUPERVISOR'}">selected</c:if>>超级管理员
					<option value="ADMIN" <c:if test="${search.userRole eq 'ADMIN'}">selected</c:if>>运维
					<option value="LIQUIDATING" <c:if test="${search.userRole eq 'LIQUIDATING'}">selected</c:if>>清算员
					<option value="OPERATION" <c:if test="${search.userRole eq 'OPERATION'}">selected</c:if>>系统管理员
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>

