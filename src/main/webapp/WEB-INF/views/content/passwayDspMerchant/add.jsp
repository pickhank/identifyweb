<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<form method="post" enctype="multipart/form-data" action="<c:url value="/content/passwayDspMerchant/add" />">
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">通道标识</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="channel" value="${item.channel}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">通道账户编号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="channelAccNo" value="${item.channelAccNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">通道账户名称</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="channelAccName" value="${item.channelAccName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">渠道签名KEY密文</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="signKey" value="${item.signKey}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">渠道加密KEY密文</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="encKey" value="${item.encKey}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">额外参数 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="extra" value="${item.extra}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">额外参数 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="remark" value="${item.remark}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">费率配置信息 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="feeText" value="${item.feeText}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">状态</td>
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

