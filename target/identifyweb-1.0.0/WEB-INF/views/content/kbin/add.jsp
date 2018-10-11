<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<form method="post" enctype="multipart/form-data" action="<c:url value="/content/kbin/add" />">
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">startNum</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="startNum" value="${item.startNum}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">cardLength</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="cardLength" value="${item.cardLength}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">bankName</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="bankName" value="${item.bankName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">bankCode</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="bankCode" value="${item.bankCode}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">bankNo</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="bankNo" value="${item.bankNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">bankNameAbbr</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="bankNameAbbr" value="${item.bankNameAbbr}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">cardAttr</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="cardAttr" value="${item.cardAttr}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">cardEpayNo</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="cardEpayNo" value="${item.cardEpayNo}">
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>

