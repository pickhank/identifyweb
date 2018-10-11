<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">平台用户标识</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="userNo" value="${item.userNo}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">渠道标识码</td>
			<td class="liner-box-right">
				<c:if test="${item.channel eq 'HLB_CREDIT'}"><input class="input-box" type="text" name="channel" value="01-合利宝" readonly></c:if>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">渠道标账户号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="channelNo" value="${item.channelNo}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">渠道用户标识</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="channelUserNo" value="${item.channelUserNo}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">下游商户号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchNo" value="${item.mchNo}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">平台商户名</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchName" value="${item.mchName}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">下游用户标识</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mercUserNo" value="${item.mercUserNo} " readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">平台提现流水号</td>
			<td class="liner-box-right"> 
				<input class="input-box" type="text" name="depositNo" value="${item.depositNo}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">平台提现日期</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="depositDate" value="${item.depositDate}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">提现通知地址</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="notifyUrl" value="${item.notifyUrl}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易流水号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="orderNo" value="${item.orderNo}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">订单时间</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="orderTime" value="${item.orderTime}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">提现金额(元)</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="depositAmt" value="<fmt:formatNumber type="number" value="${item.depositAmt / 100.0}" pattern="0.00" maxFractionDigits="2"/>" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">提现卡-签约ID</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="settleBindId" value="${item.settleBindId}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">提现卡-渠道签约ID</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="settleUpBindId" value="${item.settleUpBindId}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">提现卡-姓名</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="settleName" value="${item.settleName}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">提现卡-预留手机号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="settlePhone" value="${item.settlePhone}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">提现卡-银行帐号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="settleCardNo" value="${item.settleCardNo}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">提现费率（千分位）</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="feeRatio" value="${item.feeRatio}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">提现单笔手续费（元）</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="feeAmt" value="<fmt:formatNumber type="number" value="${item.feeAmt / 100.0}" pattern="0.00" maxFractionDigits="2"/>" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">用户提现手续费（元）</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="depositFeeAmt" value="<fmt:formatNumber type="number" value="${item.depositFeeAmt / 100.0}" pattern="0.00" maxFractionDigits="2"/>" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required"> 商户提现分润（元）</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mercProfitAmt" value="<fmt:formatNumber type="number" value="${item.mercProfitAmt / 100.0}" pattern="0.00" maxFractionDigits="2"/>" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易状态</td>
			<td class="liner-box-right">
				<c:if test="${item.status eq 'PAYING'}"><input class="input-box" type="text" name="status" value="支付中" readonly></c:if>
				<c:if test="${item.status eq 'APPLYING'}"><input class="input-box" type="text" name="status" value="提现中" readonly></c:if>
				<c:if test="${item.status eq 'PENDING'}"><input class="input-box" type="text" name="status" value="待查证" readonly></c:if>
				<c:if test="${item.status eq 'SUCCESS'}"><input class="input-box" type="text" name="status" value="处理成功" readonly></c:if>
				<c:if test="${item.status eq 'FAIL'}"><input class="input-box" type="text" name="status" value="失败" readonly></c:if>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易状态描述</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="statusDesc" value="${item.statusDesc}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">状态变更时间 </td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="statusUpdTime" value="${item.statusUpdTime}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易完成日期 </td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="finishedDate" value="${item.finishedDate}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">上游流水号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="upNo" value="${item.upNo}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">上游返回码</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="upRetCode" value="${item.upRetCode}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">上游返回描述</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="upRetDesc" value="${item.upRetDesc}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">异步通知状态</td>
			<td class="liner-box-right">
				<c:if test="${item.notifyStatus eq 'CREATED'}"><input class="input-box" type="text" name="notifyStatus" value="创建" readonly></c:if>
				<c:if test="${item.notifyStatus eq 'PENDING'}"><input class="input-box" type="text" name="notifyStatus" value="等待" readonly></c:if>
				<c:if test="${item.notifyStatus eq 'SUCCESS'}"><input class="input-box" type="text" name="notifyStatus" value="成功" readonly></c:if>
				<c:if test="${item.notifyStatus eq 'FAILED'}"><input class="input-box" type="text" name="notifyStatus" value="失败" readonly></c:if>
				<c:if test="${item.notifyStatus eq 'NONEED'}"><input class="input-box" type="text" name="notifyStatus" value="不需要通知" readonly></c:if>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">异步通知返回内容</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="notifyResultDesc" value='${item.notifyResultDesc}' readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">备用字段1</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="extra1" value="${item.extra1}" readonly>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">备用字段2</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="extra2" value="${item.extra2}" readonly>
			</td>
		</tr>
	</table>


