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
<form class="search-form" action="<c:url value="/content/downNoCardRoute/index" />" method="post">
	<div class="search-group">
		<label for="keywords">关键字：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input>
	<input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/content/downNoCardRoute/add" />" class="btn btn-orange pull-right operate-detail" >新建</a>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>downMchNo</td>
		<td>downAgentNo</td>
		<td>channel</td>
		<td>channelMercNo</td>
		<td>channelMercName</td>
		<td>transferMercNo</td>
		<td>transferMercName</td>
		<td>limitAmt</td>
		<td>topChannel</td>
		<td>mercCategory</td>
		<td>priority</td>
		<td>expDate</td>
		<td>expTime</td>
		<td>routeCategory</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="itemStatus" >
		<tr class="list-item">
			<td>
				<c:out value="${item.downMchNo}" />
			</td>
			<td>
				<c:out value="${item.downAgentNo}" />
			</td>
			<td>
				<c:out value="${item.channel}" />
			</td>
			<td>
				<c:out value="${item.channelMercNo}" />
			</td>
			<td>
				<c:out value="${item.channelMercName}" />
			</td>
			<td>
				<c:out value="${item.transferMercNo}" />
			</td>
			<td>
				<c:out value="${item.transferMercName}" />
			</td>
			<td>
				<c:out value="${item.limitAmt}" />
			</td>
			<td>
				<c:out value="${item.topChannel}" />
			</td>
			<td>
				<c:out value="${item.mercCategory}" />
			</td>
			<td>
				<c:out value="${item.priority}" />
			</td>
			<td>
				<c:out value="${item.expDate}" />
			</td>
			<td>
				<c:out value="${item.expTime}" />
			</td>
			<td>
				<c:out value="${item.routeCategory}" />
			</td>
			<td>
				<a href="<c:url value="/content/downNoCardRoute/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<!-- a href="<c:url value="/content/downNoCardRoute/delete/${item.id}" />" class="operate-delete">删除</a-->
			</td>
		</tr>
	</c:forEach>
</table>
<utils:pager/>
