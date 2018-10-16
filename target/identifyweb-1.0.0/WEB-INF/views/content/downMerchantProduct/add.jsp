<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<form method="post" enctype="multipart/form-data" action="<c:url value="/content/downMerchantProduct/add" />">
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">机构编号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchNo" value="${item.mchNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">姓名</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchName" value="${item.mchName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">产品类型</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="productType" value="${item.productType}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">T1账户金额, 单位：分</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="t1BalanceAmt" value="${item.t1BalanceAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">T0账户金额 , 单位：分</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="t0BalanceAmt" value="${item.t0BalanceAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易费率，单位：千分之</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="feeRatio" value="${item.feeRatio}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">单笔交易加收费，单位：元</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="addFeeAmt" value="${item.addFeeAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">代付费率，单位：千分之</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="drawFeeRatio" value="${item.drawFeeRatio}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">单笔代付加收费，单位：元</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="addDrawFeeAmt" value="${item.addDrawFeeAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">单笔最小金额, 单位：元</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="minPerAmt" value="${item.minPerAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">单笔最大金额, 单位：元</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="maxPerAmt" value="${item.maxPerAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">日交易限额, 单位：元</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="maxTotalAmt" value="${item.maxTotalAmt}">
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

