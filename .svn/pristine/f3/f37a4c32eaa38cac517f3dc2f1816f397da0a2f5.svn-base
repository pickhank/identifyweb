<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<form method="post" enctype="multipart/form-data" action="<c:url value="/content/downAgent/add" />">
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">agentNo</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="agentNo" value="${item.agentNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">name</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="name" value="${item.name}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">phone</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="phone" value="${item.phone}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">bankName</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="bankName" value="${item.bankName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">cardNo</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="cardNo" value="${item.cardNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">开启资金明细</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="enableBalChgFlg" value="${item.enableBalChgFlg}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">status</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="status" value="${item.status}">
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>

