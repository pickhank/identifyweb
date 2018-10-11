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
<form action="<c:url value="/admin/downNoCardInvoice/add" />" method="post">
	<input type="hidden" name="id" value="${item.id}" />
	<c:if test="${not empty error}">
		<div class="liner-error">
			<div class="liner-box-right">${error}</div>
		</div>
	</c:if>
	<div class="ms-row">
	
	<%-- <td><utils:combo mode="lbl" value="${item.channel}" /></td>
			<td><utils:combo mode="lbl" value="${item.channelAccName}" /></td>
			<td><c:out value="${item.transNo}" /></td>
			<td><c:out value="${item.mchNo}" /></td>
			<td><c:out value="${item.mchName}" /></td>
			<td>
				
			</td>
			
		 
			
			 --%>
		<div class="ms-col-5 ms-border">
			<div class="ms-header">平台基本信息</div>
			<table class="liner-box">
				<tr>
					<td class="liner-box-left">清算账户:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="清算账户" name="mode" 
							value="<c:choose><c:when test="${item.mode eq 'S1' }">T1</c:when><c:when test="${item.mode eq 'S0' }">T0</c:when><c:otherwise>${item.mode }</c:otherwise></c:choose>" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">平台流水号:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="平台流水号" name="transNo" value="${item.transNo}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">受理时间:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="受理时间" name="createDate" value="${item.createDate}" readonly/>
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
					<td class="liner-box-left">代付费率:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="代付费率" name="drawFeeRatio" value="${item.drawFeeRatio}" readonly/>%。
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">单笔代付费:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="单笔加收费" name="addDrawFeeAmt" value="${item.addDrawFeeAmt}" readonly/>元
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">用途:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="用途" name="purpose" value="${item.purpose}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">异步通知地址:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="通知地址" name="notifyUrl" value="${item.notifyUrl}" readonly/>
					</td>
				</tr>
			</table>
		</div>
		
		<div class="ms-col-5 ms-border">
			<div class="ms-header">结算信息</div>
			<table class="liner-box">
				<tr>
					<td class="liner-box-left">机构流水号:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="机构流水号" name="tradeNo" value="${item.tradeNo}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">机构订单时间:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="机构订单时间" name="orderDate" value="${item.orderDate}" readonly/>
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
					<td class="liner-box-left">代付金额:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="代付金额" name="price" value="${item.price}" readonly/>元
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">手续费:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="手续费" name="downDrawFee" value="${item.downDrawFee}" readonly/>元
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">收款人:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="收款人" name="accName" value="${item.accName}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">收款银行:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="收款卡所属银行" name="accBankName" value="${item.accBankName}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">收款卡号:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="收款卡号" name="accCardNo" value="${item.accCardNo}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">异步通知是否成功:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="异步通知是否成功" name="notifySuccess" value="${item.notifySuccess}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">异步通知次数:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="异步通知次数" name="notifyTimes" value="${item.notifyTimes}" readonly/>
					</td>
				</tr>
				<tr>
					<td class="liner-box-left">末次异步通知时间:</td>
					<td class="liner-box-right">
						<input type="text" class="input-box" placeholder="末次异步通知时间" name="notifyLastDate" value="${item.notifyLastDate}" readonly/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>