<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<form class="search-form" action="<c:url value="/content/downAdvance/index" />" method="post">
	<label for="keywords">过滤条件：</label>
	<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	<label for="transNo">原流水号：</label>
	<input type="text" class="input-box search-input-box" placeholder="原流水号" name="transNo" value="${search.transNo}"></input>
	<label for="tradeNo">原下游流水号：</label>
	<input type="text" class="input-box search-input-box" placeholder="原下游流水号" name="tradeNo" value="${search.tradeNo}"></input>
	<label for="advanceNo">代付流水号：</label>
	<input type="text" class="input-box search-input-box" placeholder="代付流水号" name="advanceNo" value="${search.advanceNo}"></input>
	<label for="status">代付状态：</label>
	<select name="status" class="input-box search-input-box">
		<option value="">全部
		<option value="00" <c:if test="${search.status eq '00'}">selected</c:if>>代付成功
		<option value="01" <c:if test="${search.status eq '01'}">selected</c:if>>代付中
		<option value="02" <c:if test="${search.status eq '02'}">selected</c:if>>代付失败
	</select>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="space"></div>
	<input type="submit" value="搜索" class="btn"></input>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>收款人姓名</td>
		<td>收款卡号</td>
		<td>金额</td>
		<td>代付费</td>
		<td>实际打款金额</td>
		<td>原下游交易流水号</td>
		<td>原交易流水号</td>
		<td>代付流水号</td>
		<td>代付批次</td>
		<td>交易参考号</td>
		<td>交易状态</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td>
				<c:out value="${item.accName}" />
			</td>
			<td>
				<c:out value="${item.cardNo}" />
			</td>
			<td>
				<c:out value="${item.amount}" />
			</td>
			<td>
				<c:out value="${item.dfFee}" />
			</td>
			<td>
				<c:out value="${item.realAmount}" />
			</td>
			<td>
				<c:out value="${item.tradeNo}" />
			</td>
			<td>
				<c:out value="${item.transNo}" />
			</td>
			<td>
				<c:out value="${item.advanceNo}" />
			</td>
			<td>
				<c:out value="${item.resePriNo}" /> - <c:out value="${item.proCod}" />
			</td>
			<td>
				<c:out value="${item.retReNo}" />
			</td>
			<td>
				<c:out value="${item.status}" /> - <c:out value="${item.statusDesc}" />
			</td>
			<td>
				<a href="<c:url value="/content/downAdvance/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
			</td>
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="11">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>