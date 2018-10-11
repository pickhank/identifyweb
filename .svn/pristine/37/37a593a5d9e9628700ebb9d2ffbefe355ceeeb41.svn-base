<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<form method="post" enctype="multipart/form-data" action="<c:url value="/content/downDspInvoice/add" />">
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">接口版本号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="versionNo" value="${item.versionNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">鉴权类型</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="dspMode" value="${item.dspMode}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户号 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchNo" value="${item.mchNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户名 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchName" value="${item.mchName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">平台流水号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="transNo" value="${item.transNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">平台日期yyyyMMdd</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="transDate" value="${item.transDate}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">下游交易流水号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="tradeNo" value="${item.tradeNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">订单时间, 格式：yyyyMMddHHmmss</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="orderDate" value="${item.orderDate}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">异步通知地址</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="notifyUrl" value="${item.notifyUrl}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">代理商 *</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="agentNo" value="${item.agentNo}">
			</td>
		</tr>
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
			<td class="liner-box-left required">单笔费，单位：分</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="addFeeAmt" value="${item.addFeeAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">卡姓名</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="holderName" value="${item.holderName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">手机号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mobile" value="${item.mobile}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">证件号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="idCard" value="${item.idCard}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">卡号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="cardNo" value="${item.cardNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">卡CVV</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="cardCvn" value="${item.cardCvn}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">付款卡-卡有效期-MMYY</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="cardExpDate" value="${item.cardExpDate}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">商户交易手续费</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchPayFee" value="${item.mchPayFee}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">渠道交易手续费</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="channelPayFee" value="${item.channelPayFee}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">代理商利润</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="agentProfit" value="${item.agentProfit}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">平台利润</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="profit" value="${item.profit}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">平台利润-额外</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="xtraProfit" value="${item.xtraProfit}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">已提交上游</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="sentUp" value="${item.sentUp}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">上游流水号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="upTransNo" value="${item.upTransNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易状态, 00=处理成功，计费、01=处理中、02=处理失败，不计费</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="status" value="${item.status}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易状态描述</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="statusDesc" value="${item.statusDesc}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">状态更新时间</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="statusChgDate" value="${item.statusChgDate}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">验证结果, 00=验证成功、02=验证失败</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="verifyStatus" value="${item.verifyStatus}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">验证结果, 00=验证成功、02=验证失败</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="verifyStatusDesc" value="${item.verifyStatusDesc}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">保留缺省域1</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="resv1" value="${item.resv1}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">保留缺省域2</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="resv2" value="${item.resv2}">
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>

