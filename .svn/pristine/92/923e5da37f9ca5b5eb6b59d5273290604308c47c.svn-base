<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>

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
<form action="<c:url value="/admin/downDspInvoice/detail" />" method="post">
	<input type="hidden" name="id" value="${item.id}" />
	<c:if test="${not empty error}">
		<div class="liner-error">
			<div class="liner-box-right">${error}</div>
		</div>
	</c:if>
	<div class="ms-row">
		<div class="ms-col-5 ms-border">
			<div class="ms-header">订单基本信息</div>
			<table class="liner-box">
				<tr>
					<td class="liner-box-left">平台流水号:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="平台流水号" name="transNo" value="${item.transNo}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">平台受理时间:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="平台受理时间" name="createDate" value="${item.createDate}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">机构号:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="机构号" name="mchNo" value="${item.mchNo}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">机构名称:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="机构名称" name="mchName" value="${item.mchName}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">交易通道:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="通道" name="channel" value="${item.channel}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">通道账户号:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="通道账户号" name="channelAccNo" value="${item.channelAccNo}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">通道商户:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="通道商户" name="channelAccName" value="${item.channelAccName}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">单笔加收费:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="单笔加收费" name="mchPayFee" value="${item.mchPayFee}" readonly/>元
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">备注:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="备注" name="remark" value="${item.remark}" readonly/>
					</td>
				</tr>
			</table>
		</div>
		
		<div class="ms-col-5 ms-border">
			<div class="ms-header">机构状态信息</div>
			<table class="liner-box">
				<tr>
					<td class="liner-box-left">交易类型:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="交易类型" value="<utils:enum mode='lbl' key="EnumDspMode" value="${item.dspMode }" />" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">机构流水号:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="机构流水号" name="tradeNo" value="${item.tradeNo}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">机构订单时间:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="订单时间" name="orderDate" value="${item.orderDate}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">交易状态:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="交易状态" name="status" value="${item.status}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">状态描述:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="交易状态描述" name="statusDesc" value="${item.statusDesc}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">状态更新时间:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="状态更新时间" name="successDate" value="${item.statusChgDate}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">交易手续费:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="商户手续费金额" name="mchPayFee" value="${item.mchPayFee}" readonly/>元
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">验证结果:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="验证结果" name="verifyStatus" value="${item.verifyStatus}" readonly/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>