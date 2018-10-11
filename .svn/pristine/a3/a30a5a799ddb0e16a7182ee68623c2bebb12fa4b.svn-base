<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<form method="post" enctype="multipart/form-data" action="<c:url value="/content/downMerchant/add" />">
	<input type="hidden" name="id" value="${item.id}">
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">类别</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mercCategory" value="${item.mercCategory}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">机构编号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="mchNo" value="${item.mchNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">姓名</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="name" value="${item.name}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">电话</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="phone" value="${item.phone}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算卡卡号</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="accCardNo" value="${item.accCardNo}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算卡户名</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="accCardHolder" value="${item.accCardHolder}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">结算银行名称</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="accBankName" value="${item.accBankName}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">代理商ID</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="agentId" value="${item.agentId}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">加密Key</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="encKey" value="${item.encKey}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">签名Key</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="signKey" value="${item.signKey}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">代付KEY</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="payKey" value="${item.payKey}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">信任IP</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="limitIps" value="${item.limitIps}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">默认代付费率，单位：千分之</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="drawFeeRatio" value="${item.drawFeeRatio}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">默认单笔代付加收费，单位：元</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="addDrawFeeAmt" value="${item.addDrawFeeAmt}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">通知消息</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="enableNotify" value="${item.enableNotify}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">开启资金明细</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="enableBalChgFlg" value="${item.enableBalChgFlg}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">状态</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="status" value="${item.status}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">T0授信资金比，单位：百分之</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="creditRatio" value="${item.creditRatio}">
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">下一笔结算日期(由系统自动维护)</td>
			<td class="liner-box-right">
				<input class="input-box" type="text" name="settleDate" value="${item.settleDate}">
			</td>
		</tr>
		<tr>
			<td colspan="2" class="liner-box-one-line">
				<input type="submit" value="提交" class="btn" />
			</td>
		</tr>
	</table>
</form>

