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
<form class="search-form" action="<c:url value="/content/kbin/index" />" method="post">
	<div class="search-group">
		<label for="keywords">关键字：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input>
	<input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/content/kbin/add" />" class="btn btn-orange pull-right operate-detail" >新建</a>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>startNum</td>
		<td>cardLength</td>
		<td>bankName</td>
		<td>bankCode</td>
		<td>bankNo</td>
		<td>bankNameAbbr</td>
		<td>cardAttr</td>
		<td>cardEpayNo</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="itemStatus" >
		<tr class="list-item">
			<td>
				<c:out value="${item.startNum}" />
			</td>
			<td>
				<c:out value="${item.cardLength}" />
			</td>
			<td>
				<c:out value="${item.bankName}" />
			</td>
			<td>
				<c:out value="${item.bankCode}" />
			</td>
			<td>
				<c:out value="${item.bankNo}" />
			</td>
			<td>
				<c:out value="${item.bankNameAbbr}" />
			</td>
			<td>
				<c:out value="${item.cardAttr}" />
			</td>
			<td>
				<c:out value="${item.cardEpayNo}" />
			</td>
			<td>
				<a href="<c:url value="/content/kbin/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<!-- a href="<c:url value="/content/kbin/delete/${item.id}" />" class="operate-delete">删除</a-->
			</td>
		</tr>
	</c:forEach>
</table>
<utils:pager/>
