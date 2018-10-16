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
<form class="search-form" action="<c:url value="/content/downTradeLog/index" />" method="post">
	<div class="search-group">
		<label for="keywords">关键字：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input>
	<input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/content/downTradeLog/add" />" class="btn btn-orange pull-right operate-detail" >新建</a>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>recordDt</td>
		<td>receiveDtime</td>
		<td>replyDtime</td>
		<td>errCode</td>
		<td>errMsg</td>
		<td>mchNo</td>
		<td>mchName</td>
		<td>tradeNo</td>
		<td>accIdCard</td>
		<td>accCardNo</td>
		<td>accBankName</td>
		<td>payCardNo</td>
		<td>payBankName</td>
		<td>channel</td>
		<td>extra1</td>
		<td>extra2</td>
		<td>交易金额</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="itemStatus" >
		<tr class="list-item">
			<td>
				<c:out value="${item.recordDt}" />
			</td>
			<td>
				<c:out value="${item.receiveDtime}" />
			</td>
			<td>
				<c:out value="${item.replyDtime}" />
			</td>
			<td>
				<c:out value="${item.errCode}" />
			</td>
			<td>
				<c:out value="${item.errMsg}" />
			</td>
			<td>
				<c:out value="${item.mchNo}" />
			</td>
			<td>
				<c:out value="${item.mchName}" />
			</td>
			<td>
				<c:out value="${item.tradeNo}" />
			</td>
			<td>
				<c:out value="${item.accIdCard}" />
			</td>
			<td>
				<c:out value="${item.accCardNo}" />
			</td>
			<td>
				<c:out value="${item.accBankName}" />
			</td>
			<td>
				<c:out value="${item.payCardNo}" />
			</td>
			<td>
				<c:out value="${item.payBankName}" />
			</td>
			<td>
				<c:out value="${item.channel}" />
			</td>
			<td>
				<c:out value="${item.extra1}" />
			</td>
			<td>
				<c:out value="${item.extra2}" />
			</td>
			<td>
				<c:out value="${item.price}" />
			</td>
			<td>
				<a href="<c:url value="/content/downTradeLog/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<!-- a href="<c:url value="/content/downTradeLog/delete/${item.id}" />" class="operate-delete">删除</a-->
			</td>
		</tr>
	</c:forEach>
</table>
<utils:pager/>
