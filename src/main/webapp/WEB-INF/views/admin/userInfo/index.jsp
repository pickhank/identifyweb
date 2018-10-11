<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<form class="search-form" action="<c:url value="/admin/userInfo/index" />" method="post">
	<label for="keywords">过滤条件：</label>
	<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="space"></div>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/admin/userInfo/add" />" class="btn btn-orange pull-right operate-detail">新建</a>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>用户名</td>
		<td>商户名称</td>
		<td>邮箱</td>
		<td>最后登录时间</td>
		<td class="td-width-20">操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td>
				<c:out value="${item.username}" />
			</td>
			<td>
				<c:out value="${item.name}" />
			</td>
			<td>
				<c:out value="${item.email}" />
			</td>
			<td>
				<fmt:formatDate value="${item.lastLoginDate}" pattern="yyyy/MM/dd HH:mm:ss"/>
			</td>
			<td>
				<a href="<c:url value="/admin/userInfo/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<a href="<c:url value="/admin/userInfo/delete/${item.id}" />" class="choose-link operate-delete">删除</a>
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="3">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>