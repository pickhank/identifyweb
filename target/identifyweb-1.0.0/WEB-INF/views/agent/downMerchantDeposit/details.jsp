<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<style>
.input-box-short {
	width:43%;
}
.liner-box-left {
	padding: 2px 10px;
}
.liner-box-right {
	padding: 0;
}
</style>
	<input type="hidden" name="id" value="${item.id}" />
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">支付方式:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" value="<c:if test="${item.payType eq '01' }">网银</c:if><c:if test="${item.payType eq '02' }">银联在线</c:if>" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商品名称:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="商品名称" name="subject" value="${item.subject}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易金额:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="交易金额" value="${item.price}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算额:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="结算额" value="${item.downRealT1Price}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">手续费:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="手续费" value="${item.downPayFee}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">订单描述:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="交易备注" name="description" value="${item.description}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">机构流水号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="机构流水号" name="tradeNo" value="${item.tradeNo}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">平台流水号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="交易流水号" name="transNo" value="${item.transNo}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">订单时间:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="订单时间" name="orderDate" value="${item.orderDate}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易状态:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="交易状态" name="status" value="${item.status}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易状态描述:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="交易状态描述" name="statusDesc" value="${item.statusDesc}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">通知地址:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="通知地址" name="notifyUrl" value="${item.notifyUrl}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">回调地址:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="回调地址" name="callbackUrl" value="${item.callbackUrl}" readonly/>
			</td>
		</tr>
	</table>