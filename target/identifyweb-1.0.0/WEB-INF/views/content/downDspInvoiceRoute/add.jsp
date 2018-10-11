<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<form method="post" enctype="multipart/form-data" action="<c:url value="/content/downNoCardRoute/add" />">
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">downMchNo</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="downMchNo" value="${item.downMchNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">downAgentNo</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="downAgentNo" value="${item.downAgentNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">channel</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="channel" value="${item.channel}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">channelMercNo</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="channelMercNo" value="${item.channelMercNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">channelMercName</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="channelMercName" value="${item.channelMercName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">transferMercNo</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="transferMercNo" value="${item.transferMercNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">transferMercName</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="transferMercName" value="${item.transferMercName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">limitAmt</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="limitAmt" value="${item.limitAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">topChannel</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="topChannel" value="${item.topChannel}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">mercCategory</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mercCategory" value="${item.mercCategory}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">priority</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="priority" value="${item.priority}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">expDate</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="expDate" value="${item.expDate}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">expTime</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="expTime" value="${item.expTime}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">routeCategory</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="routeCategory" value="${item.routeCategory}">
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>

