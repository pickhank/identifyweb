<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">上游商户号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="userId" value="${item.userId}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易流水号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="transNo" value="${item.transNo}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">订单时间</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="orderDate" value="${item.orderDate} " readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算卡户名</td>
			<td class="liner-box-right"> 
				<input class="input-box" type="text" name="settleCardName" value="${item.settleCardName}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算卡卡号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="settleCardNo" value="${item.settleCardNo}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算卡手机号码</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="settleCardMobile" value="${item.settleCardMobile}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算卡身份证号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="settleCardCertId" value="${item.settleCardCertId}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算卡银行名称</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="settleCardBankNm" value="${item.settleCardBankNm}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算卡联行号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="settleCardBankUnionNo" value="${item.settleCardBankUnionNo}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算卡联行名称</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="settleCardBankUnionNm" value="${item.settleCardBankUnionNm}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">审核状态</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="status" value="${item.status}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">提交时间</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="checkDate" value="${item.checkDate}" readonly>
			</td>
		</tr>
	</table>


