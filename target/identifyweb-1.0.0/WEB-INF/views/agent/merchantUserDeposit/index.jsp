<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${not empty error}">
<script>
$(function(){
	$.messager.alert('错误','${error}');
});
</script>
</c:if>
<form class="search-form" action="<c:url value="/agent/merchantUserDeposit/index" />" method="post">
	<div class="ms-col-fix-100 clear">
		<label for="keywords">过滤条件：</label>
		<input type="text" class="input-box search-input-box" placeholder="关键字" name="keywords" value="${search.keywords}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="userNo">用户标识：</label>
		<input type="text" class="input-box search-input-box" placeholder="用户标识" name="userNo" value="${search.userNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="mchNo">下游商户号：</label>
		<input type="text" class="input-box search-input-box" placeholder="下游商户号" name="mchNo" value="${search.mchNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="depositNo">提现流水号：</label>
		<input type="text" class="input-box search-input-box" placeholder="提现流水号" name="depositNo" value="${search.depositNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="depositDate">提现日期：</label>
		<input type="text" class="input-box search-input-box" placeholder="提现日期" name="depositDate" value="${search.depositDate}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="orderNo">交易流水号：</label>
		<input type="text" class="input-box search-input-box" placeholder="交易流水号" name="orderNo" value="${search.orderNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="upNo">上游流水号：</label>
		<input type="text" class="input-box search-input-box" placeholder="上游流水号" name="upNo" value="${search.upNo}"></input>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="status">交易状态：</label>
		<select name="status" class="input-box search-input-box">
			<option value="">全部
			<option value="PAYING" <c:if test="${search.status eq 'PAYING'}">selected</c:if>>支付中
			<option value="APPLYING" <c:if test="${search.status eq 'APPLYING'}">selected</c:if>>提现中
			<option value="PENDING" <c:if test="${search.status eq 'PENDING'}">selected</c:if>>待查证
			<option value="SUCCESS" <c:if test="${search.status eq 'SUCCESS'}">selected</c:if>>处理成功
			<option value="FAIL" <c:if test="${search.status eq 'FAIL'}">selected</c:if>>失败
		</select>
	</div>
	<div class="ms-col-fix-100 clear">
		<label for="settleCardNo">卡号：</label>
		<input type="text" class="input-box search-input-box" placeholder="卡号" name="settleCardNo" value="${search.settleCardNo}"></input>
	</div>
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<input type="submit" value="搜索" class="btn"></input>
</form>
<table class="list-wapper">
	<tr class="list-header">
		<td>平台用户标识</td>
		<td>下游商户号</td>
		<td>机构名称</td>
		<td>平台提现流水号</td>
		<td>订单时间</td>
		<td>平台提现日期</td>
		<td>姓名</td>
		<td>银行帐号</td>
		<td>提现金额(元)</td>
		<td>交易流水号</td>
		<td>交易状态</td>
		<td>交易状态描述</td>
		<td>上游流水号</td>
		<td>上游状态</td>
		<td>操作</td>
	</tr>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<tr class="list-item">
			<td>
				<c:out value="${item.userNo}" />
			</td>
			<td>
				<c:out value="${item.mchNo}" />
			</td>
			<td>
				<c:out value="${item.mchName}" />
			</td>
			<td>
				<c:out value="${item.depositNo}" />
			</td>
			<td>
				<c:out value="${item.orderTime}" />
			</td>
			<td>
				<c:out value="${item.depositDate}" />
			</td>
			<td>
				<c:out value="${item.settleName}" />
			</td>
			<td>
				<c:out value="${item.settleCardNo}" />
			</td>
			<td>
				<fmt:formatNumber type='number' value='${item.depositAmt / 100.0}' pattern='0.00' maxFractionDigits='2'/>
			</td>
			<td>
				<c:out value="${item.orderNo}" /> 
			</td>
			<td>
				<c:if test="${item.status eq 'PAYING'}">支付中</c:if>
				<c:if test="${item.status eq 'APPLYING'}">提现中</c:if>
				<c:if test="${item.status eq 'PENDING'}">待查证</c:if>
				<c:if test="${item.status eq 'SUCCESS'}">处理成功</c:if>
				<c:if test="${item.status eq 'FAIL'}">失败</c:if>
			</td>
			<td>
				<c:out value="${item.statusDesc}" />
			</td>
			<td>
				<c:out value="${item.upNo}" />
			</td>
			<td>
				<c:if test="${not empty item.upRetCode }">
					<c:out value="${item.upRetCode}" />: <c:out value="${item.upRetDesc}" />
				</c:if>
			</td>
			<td>
				<a href="<c:url value="/agent/merchantUserDeposit/details?id=${item.id}" />" class="choose-link operate-detail">详细</a>
				<!--<a href="<c:url value="/admin/merchantUserDeposit/add?id=${item.id}" />" class="choose-link operate-detail">编辑</a>
				<a href="<c:url value="/admin/merchantUserDeposit/delete/${item.id}" />" class="choose-link operate-delete" >删除</a> -->
		</tr>
	</c:forEach>
	<tr class="list-footer">
		<td colspan="15">
			<utils:pager></utils:pager>
		</td>
	</tr>
</table>