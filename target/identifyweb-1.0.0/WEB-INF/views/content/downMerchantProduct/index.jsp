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
<form class="search-form" action="<c:url value="/content/downMerchantProduct/index" />" method="post">
	<div class="search-group">
		<label for="keywords">关键字：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input>
	<input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<input type="submit" value="搜索" class="btn"></input>
	<a href="<c:url value="/content/downMerchantProduct/add" />" class="btn btn-orange pull-right operate-detail" >新建</a>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>机构编号</td>
		<td>姓名</td>
		<td>产品类型</td>
		<td>T1账户金额, 单位：分</td>
		<td>T0账户金额 , 单位：分</td>
		<td>交易费率，单位：千分之</td>
		<td>单笔交易加收费，单位：元</td>
		<td>代付费率，单位：千分之</td>
		<td>单笔代付加收费，单位：元</td>
		<td>单笔最小金额, 单位：元</td>
		<td>单笔最大金额, 单位：元</td>
		<td>日交易限额, 单位：元</td>
		<td>状态</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="itemStatus" >
		<tr class="list-item">
			<td>
				<c:out value="${item.mchNo}" />
			</td>
			<td>
				<c:out value="${item.mchName}" />
			</td>
			<td>
				<c:out value="${item.productType}" />
			</td>
			<td>
				<c:out value="${item.t1BalanceAmt}" />
			</td>
			<td>
				<c:out value="${item.t0BalanceAmt}" />
			</td>
			<td>
				<c:out value="${item.feeRatio}" />
			</td>
			<td>
				<c:out value="${item.addFeeAmt}" />
			</td>
			<td>
				<c:out value="${item.drawFeeRatio}" />
			</td>
			<td>
				<c:out value="${item.addDrawFeeAmt}" />
			</td>
			<td>
				<c:out value="${item.minPerAmt}" />
			</td>
			<td>
				<c:out value="${item.maxPerAmt}" />
			</td>
			<td>
				<c:out value="${item.maxTotalAmt}" />
			</td>
			<td>
				<c:out value="${item.status}" />
			</td>
			<td>
				<a href="<c:url value="/content/downMerchantProduct/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<!-- a href="<c:url value="/content/downMerchantProduct/delete/${item.id}" />" class="operate-delete">删除</a-->
			</td>
		</tr>
	</c:forEach>
</table>
<utils:pager/>
