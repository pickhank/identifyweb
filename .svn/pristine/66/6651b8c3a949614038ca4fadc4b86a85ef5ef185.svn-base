<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<form method="post" enctype="multipart/form-data" action="<c:url value="/content/notifyRecord/add" />">
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">异步通知类型</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="notifyType" value="${item.notifyType}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">机构交易主键</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="tradeNo" value="${item.tradeNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">内部流水号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="transNo" value="${item.transNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">通知地址</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="notifyUrl" value="${item.notifyUrl}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">异步通知是否成功</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="notifySuccess" value="${item.notifySuccess}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">异步通知次数</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="notifyTimes" value="${item.notifyTimes}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">末次异步通知时间</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="notifyLastDate" value="${item.notifyLastDate}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">下次异步通知时间</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="notifyNextDate" value="${item.notifyNextDate}">
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>

