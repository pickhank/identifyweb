<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
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
form .input-box[readonly] {
    background-color: #ccc;
}
</style>
<form action="<c:url value="/admin/passwayMerchant/updateAcct" />" method="post">
	<input type="hidden" name="id" value="${item.id}" />
	<input type="hidden" name="channel" value="${item.channel}" />
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right" colspan="3">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">归属通道:</td>
			<td class="liner-box-right" colspan="3">
				<utils:combo mode="lbl" value="${item.channel}" />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户编号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="商户编号" name="mercNo" value="${item.mercNo}" readonly/>
			</td>
			<td class="liner-box-left required">通道商户号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="通道商户号" name="passwayMercNo" value="${item.passwayMercNo}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">开户姓名:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="开户姓名" name="settleCardName" value="${item.settleCardName}" />
			</td>
			<td class="liner-box-left required">身份证号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="身份证号" name="settleCardCertId" value="${item.settleCardCertId}" />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">开户账户:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="开户账户" name="settleCardNo" value="${item.settleCardNo}" />
			</td>
			<td class="liner-box-left required">预留手机号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="预留手机号" name="settleCardMobile" value="${item.settleCardMobile}" />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">银行名称:</td>
			<td class="liner-box-right" colspan="3" >
				<input type="text" class="input-box" placeholder="银行名称" name="settleCardBankNm" value="${item.settleCardBankNm}" />
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">联行号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="联行号" name="settleCardBankUnionNo" value="${item.settleCardBankUnionNo}" />
			</td>
			<td class="liner-box-left required">联行名称:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="联行名称" name="settleCardBankUnionNm" value="${item.settleCardBankUnionNm}" />
			</td>
		</tr>
		<c:if test="${item.channelMode eq '1' }">
			<tr>
				<td class="liner-box-left required">费率（千分之）:</td>
				<td class="liner-box-right">
					<input type="text" class="input-box" readonly name="feeRatio" value="${item.feeRatio}" />
				</td>
				<td class="liner-box-left required">单笔手续费（元）:</td>
				<td class="liner-box-right">
					<input type="text" class="input-box" readonly name="feeAmt" value="${item.feeAmt}" />
				</td>
			</tr>
		</c:if>
		<c:if test="${item.channelMode eq '2' }">
			<tr>
				<td class="liner-box-left required">费率（千分之）:</td>
				<td class="liner-box-right">
					<input type="text" class="input-box" required name="feeRatio" value="${item.feeRatio}" />
				</td>
				<td class="liner-box-left required">单笔手续费（元）:</td>
				<td class="liner-box-right">
					<input type="text" class="input-box" required name="feeAmt" value="${item.feeAmt}" />
				</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="4" class="liner-box-one-line" style="text-align:center; padding: 5px;">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
	<c:if test="${not empty inv }">
	<div class="ms-col-12 " style="    overflow: hidden;">
		<div class="ms-header">最近一笔交易信息</div>
		<table class="liner-box">
			<tr>
				<td class="liner-box-left">流水号:</td>
				<td class="liner-box-right">
					<input type="text" class="input-box" placeholder="交易流水号" value="${inv.transNo}" />
				</td>
			</tr>
			<tr>
				<td class="liner-box-left">交易金额:</td>
				<td class="liner-box-right">
					<input type="text" class="input-box" placeholder="交易金额" value="${inv.price}" />
				</td>
			</tr>
			<tr>	
				<td class="liner-box-left">商户手续费:</td>
				<td class="liner-box-right">
					<input type="text" class="input-box" placeholder="商户金额" value="${inv.downFeePrice}" />
				</td>
			</tr>
			<tr>
				<td class="liner-box-left">交易费率:</td>
				<td class="liner-box-right">
					<input type="text" class="input-box" placeholder="交易费率" value="${inv.downPayFee}<c:if test="${not empty inv.fixedDownPayFee }">(${inv.fixedDownPayFee })</c:if>" />
				</td>
			</tr>
			<tr>
				<td class="liner-box-left">单笔手续费:</td>
				<td class="liner-box-right">
					<input type="text" class="input-box" placeholder="单笔手续费" value="${inv.downDrawFee}<c:if test="${not empty inv.fixedDownDrawFee }">(${inv.fixedDownDrawFee })</c:if>" />
				</td>
			</tr>
			<tr>
				<td class="liner-box-left">支付卡号:</td>
				<td class="liner-box-right">
					<input type="text" class="input-box" placeholder="支付卡号" value="${inv.payCardNo}" />
				</td>
			</tr>
			<tr>
				<td class="liner-box-left">结算卡号:</td>
				<td class="liner-box-right">
					<input type="text" class="input-box" placeholder="结算卡号" value="${inv.cardNo}" />
				</td>
			</tr>
			<tr>
				<td class="liner-box-left">机构号:</td>
				<td class="liner-box-right">
					<input type="text" class="input-box" placeholder="机构号" value="${inv.mchNo}" />
				</td>
			</tr>
			<tr>
				<td class="liner-box-left">机构名:</td>
				<td class="liner-box-right">
					<input type="text" class="input-box" placeholder="机构名" value="${inv.mchName}" />
				</td>
			</tr>
		</table>
		</div>
	</div>
	</c:if>
</form>