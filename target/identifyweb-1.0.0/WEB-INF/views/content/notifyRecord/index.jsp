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
<form class="search-form" action="<c:url value="/content/notifyRecord/index" />" method="post">
	<div class="search-group">
		<label for="keywords">关键字：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input>
	<input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/content/notifyRecord/add" />" class="btn btn-orange pull-right operate-detail" >新建</a>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>异步通知类型</td>
		<td>机构交易主键</td>
		<td>内部流水号</td>
		<td>通知地址</td>
		<td>异步通知是否成功</td>
		<td>异步通知次数</td>
		<td>末次异步通知时间</td>
		<td>下次异步通知时间</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="itemStatus" >
		<tr class="list-item">
			<td>
				<c:out value="${item.notifyType}" />
			</td>
			<td>
				<c:out value="${item.tradeNo}" />
			</td>
			<td>
				<c:out value="${item.transNo}" />
			</td>
			<td>
				<c:out value="${item.notifyUrl}" />
			</td>
			<td>
				<c:out value="${item.notifySuccess}" />
			</td>
			<td>
				<c:out value="${item.notifyTimes}" />
			</td>
			<td>
				<c:out value="${item.notifyLastDate}" />
			</td>
			<td>
				<c:out value="${item.notifyNextDate}" />
			</td>
			<td>
				<a href="<c:url value="/content/notifyRecord/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<!-- a href="<c:url value="/content/notifyRecord/delete/${item.id}" />" class="operate-delete">删除</a-->
			</td>
		</tr>
	</c:forEach>
</table>
<utils:pager/>
