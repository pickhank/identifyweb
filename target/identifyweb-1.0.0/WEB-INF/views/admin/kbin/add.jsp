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
<form action="<c:url value="/admin/kbin/add" />" method="post">
	<input type="hidden" name="id" value="${item.id}" />
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">首数字:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="首数字" name="startNum" value="${item.startNum}" />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">长度:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="长度" name="cardLength" value="${item.cardLength}" />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">卡类型:</td>
			<td class="liner-box-right">
				<select name="cardAttr" class="input-box search-input-box">
					<option value="">请选择
					<option value="1" <c:if test="${item.cardAttr eq '1'}">selected</c:if>>借记卡
					<option value="2" <c:if test="${item.cardAttr eq '2'}">selected</c:if>>贷记卡
					<option value="3" <c:if test="${item.cardAttr eq '3'}">selected</c:if>>准贷记卡
					<option value="4" <c:if test="${item.cardAttr eq '4'}">selected</c:if>>预付卡
					<option value="5" <c:if test="${item.cardAttr eq '5'}">selected</c:if>>其他
				</select>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">银行名称:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="银行名称" name="bankName" value="${item.bankName}" />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">银行编码:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="银行编码" name="bankCode" value="${item.bankCode}" />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">银行联行号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="银行联行号" name="bankNo" value="${item.bankNo}" />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">银行缩写:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="银行缩写" name="bankNameAbbr" value="${item.bankNameAbbr}" />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left ">Epay银行编码:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" maxlength="10" placeholder="Epay银行编码" name="cardEpayNo" value="${item.cardEpayNo}" />
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>