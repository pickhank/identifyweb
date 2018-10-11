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
<form class="search-form" action="<c:url value="/content/passwayDspMerchant/index" />" method="post">
	<div class="search-group">
		<label for="keywords">关键字：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input>
	<input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/content/passwayDspMerchant/add" />" class="btn btn-orange pull-right operate-detail" >新建</a>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>通道标识</td>
		<td>通道账户编号</td>
		<td>通道账户名称</td>
		<td>渠道签名KEY密文</td>
		<td>渠道加密KEY密文</td>
		<td>额外参数 *</td>
		<td>额外参数 *</td>
		<td>费率配置信息 *</td>
		<td>状态</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="itemStatus" >
		<tr class="list-item">
			<td>
				<c:out value="${item.channel}" />
			</td>
			<td>
				<c:out value="${item.channelAccNo}" />
			</td>
			<td>
				<c:out value="${item.channelAccName}" />
			</td>
			<td>
				<c:out value="${item.signKey}" />
			</td>
			<td>
				<c:out value="${item.encKey}" />
			</td>
			<td>
				<c:out value="${item.extra}" />
			</td>
			<td>
				<c:out value="${item.remark}" />
			</td>
			<td>
				<c:out value="${item.feeText}" />
			</td>
			<td>
				<c:out value="${item.status}" />
			</td>
			<td>
				<a href="<c:url value="/content/passwayDspMerchant/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<!-- a href="<c:url value="/content/passwayDspMerchant/delete/${item.id}" />" class="operate-delete">删除</a-->
			</td>
		</tr>
	</c:forEach>
</table>
<utils:pager/>
