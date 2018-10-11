<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<form method="post" enctype="multipart/form-data" action="<c:url value="/content/downTradeLog/add" />">
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">recordDt</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="recordDt" value="${item.recordDt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">receiveDtime</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="receiveDtime" value="${item.receiveDtime}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">replyDtime</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="replyDtime" value="${item.replyDtime}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">errCode</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="errCode" value="${item.errCode}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">errMsg</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="errMsg" value="${item.errMsg}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">mchNo</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchNo" value="${item.mchNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">mchName</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchName" value="${item.mchName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">tradeNo</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="tradeNo" value="${item.tradeNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">accIdCard</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="accIdCard" value="${item.accIdCard}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">accCardNo</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="accCardNo" value="${item.accCardNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">accBankName</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="accBankName" value="${item.accBankName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">payCardNo</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="payCardNo" value="${item.payCardNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">payBankName</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="payBankName" value="${item.payBankName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">channel</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="channel" value="${item.channel}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">extra1</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="extra1" value="${item.extra1}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">extra2</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="extra2" value="${item.extra2}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易金额</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="price" value="${item.price}">
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>

