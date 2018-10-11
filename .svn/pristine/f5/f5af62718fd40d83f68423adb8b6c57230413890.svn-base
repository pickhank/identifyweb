<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<form method="post" enctype="multipart/form-data" action="<c:url value="/content/downCreditMerchant/add" />">
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">通道</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="channel" value="${item.channel}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">通道账户名称</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="channelAccName" value="${item.channelAccName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">通道账户编号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="channelAccNo" value="${item.channelAccNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">本地商户唯一标识</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="localeUsrNo" value="${item.localeUsrNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">渠道下发的用户编号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="passwayUsrNo" value="${item.passwayUsrNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">渠道下发的用户相关</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="passwayUsrRefer" value="${item.passwayUsrRefer}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户简称</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="usrShortName" value="${item.usrShortName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户名称</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="usrName" value="${item.usrName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户地址</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="usrAddr" value="${item.usrAddr}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户手机号码</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="usrMobile" value="${item.usrMobile}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算卡户名</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="cardHolder" value="${item.cardHolder}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算卡卡号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="cardNo" value="${item.cardNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算卡手机号码</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="cardMobile" value="${item.cardMobile}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算卡身份证号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="certId" value="${item.certId}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算卡银行名称</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="cardBankName" value="${item.cardBankName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算卡联行号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="bankUnionNo" value="${item.bankUnionNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算卡联行名称</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="bankUnionName" value="${item.bankUnionName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户交易费率</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="feeRatio" value="${item.feeRatio}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户交易单笔手续费</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="feeAmt" value="${item.feeAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户状态，0未提交、1报备成功、2待开通</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="status" value="${item.status}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">保留缺省域1</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="merResv1" value="${item.merResv1}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">保留缺省域2</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="merResv2" value="${item.merResv2}">
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>

