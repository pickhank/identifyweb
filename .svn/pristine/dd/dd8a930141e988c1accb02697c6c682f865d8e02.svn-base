<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<form method="post" enctype="multipart/form-data" action="<c:url value="/content/downMerchantBalance/add" />">
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">机构号 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchNo" value="${item.mchNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">机构名称 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchName" value="${item.mchName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">收支日期YYYYMMDD</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="balanceDate" value="${item.balanceDate}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">收支流水号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="balanceNo" value="${item.balanceNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">收支序号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="sortNum" value="${item.sortNum}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">资金账户</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="accType" value="${item.accType}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">来源类型</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="sourceType" value="${item.sourceType}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">调整资金（元） *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="diffAmt" value="${item.diffAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">账户余额（元） *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="balAmt" value="${item.balAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">关联流水编号 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="referTransNo" value="${item.referTransNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">关联渠道 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="referChannel" value="${item.referChannel}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">关联渠道商户 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="referChannelAccNo" value="${item.referChannelAccNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">备注 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="remark" value="${item.remark}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">保留字段1 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="balResv1" value="${item.balResv1}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">保留字段2 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="balResv2" value="${item.balResv2}">
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>

