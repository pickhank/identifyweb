<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form class="search-form" action="<c:url value="/agent/downNoCardInvoice/mch" />" method="post">
	<label for="keywords">过滤条件：</label>
	<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="space"></div>
	<input type="submit" value="搜索" class="btn"></input>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>商户编号</td>
		<td>姓名</td>
		<td>电话</td>
		<td>结算卡银行</td>
		<td>状态</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td>
				<c:out value="${item.mchNo}" />
			</td>	
			<td>
				<c:out value="${item.name}" />
			</td>
			<td>
				<c:out value="${item.phone}" />
			</td>
			<td>
				<c:out value="${item.bankName}" />
			</td>
			<td>
				<c:out value="${item.status}" />
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="12">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>