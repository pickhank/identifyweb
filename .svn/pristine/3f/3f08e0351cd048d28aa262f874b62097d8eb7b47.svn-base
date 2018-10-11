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
<form class="search-form" action="<c:url value="/admin/user/index" />" method="post">
	<div class="search-group">
		<label for="keywords">关键字：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
		<label for="userRole">用户角色：</label>
		<select name="userRole" class="input-box search-input-box">
			<option value="">全部
			<option value="SUPERVISOR" <c:if test="${search.userRole eq 'SUPERVISOR'}">selected</c:if>>超级管理员
			<option value="ADMIN" <c:if test="${search.userRole eq 'ADMIN'}">selected</c:if>>运维
			<option value="LIQUIDATING" <c:if test="${search.userRole eq 'LIQUIDATING'}">selected</c:if>>清算员
			<option value="OPERATION" <c:if test="${search.userRole eq 'OPERATION'}">selected</c:if>>系统管理员
		</select>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<input type="submit" value="搜索" class="btn"></input>
	<c:if test="${empty accRole or accRole eq 'SUPERVISOR' or accRole eq 'OPERATION' }">
	<a href="<c:url value="/admin/user/add"/>" class="btn btn-orange pull-right operate-detail" >新建</a>
	</c:if>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>用户名</td>
		<td>角色</td>
		<td>邮箱</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="itemStatus" >
		<tr class="list-item">
			<td>
				<c:out value="${item.username }" />
			</td>
			<td>
				<c:if test="${empty item.userRole or item.userRole eq 'SUPERVISOR'}">超级管理员</c:if>
				<c:if test="${item.userRole eq 'ADMIN'}">运维</c:if>
				<c:if test="${item.userRole eq 'LIQUIDATING'}">清算员</c:if>
				<c:if test="${item.userRole eq 'OPERATION'}">系统管理员</c:if>
			</td>
			<td>
				<c:out value="${item.email }" />
			</td>
			<td class="text-right">
			<c:if test="${empty accRole or accRole eq 'SUPERVISOR' or accRole eq 'OPERATION' }">
				<c:if test="${item.isAdminFlag }">
					系统保留帐户
				</c:if>
				<c:if test="${not item.isAdminFlag }">
					<a href="<c:url value="/admin/user/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
					<a href="<c:url value="/admin/user/delete/${item.id}" />" class="operate-delete">删除</a>
				</c:if>
			</c:if>
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="8">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>
