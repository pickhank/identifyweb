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
<form action="<c:url value="/content/downAdvance/add" />" method="post">
	<input type="hidden" name="id" value="${item.id}" />
	<table class="liner-box">
		<c:if test="${not empty error}">
			<tr class="liner-error">
				<td class="liner-box-left"></td>
				<td class="liner-box-right">${error}</td>
			</tr>
		</c:if>
		<tr>
			<td class="liner-box-left required">收款人姓名:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="收款人姓名" name="accName" value="${item.accName}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">收款人电话:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="收款人电话" name="accTel" value="${item.accTel}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">收款卡银行:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="收款卡银行" name="bankName" value="${item.bankName}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">收款卡号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="收款卡号" name="cardNo" value="${item.cardNo}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">联行行号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="联行行号" name="bankCode" value="${item.bankCode}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">代付流水号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="代付流水号" name="advanceNo" value="${item.advanceNo}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">机构编号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="机构编号" name="comNo" value="${item.comNo}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">终端编号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="终端编号" name="trmNo" value="${item.trmNo}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">代付金额:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="交易金额" name="amount" value="${item.amount}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">代付费:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="交易金额" name="dfFee" value="${item.dfFee}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">实际代付金额:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="交易金额" name="realAmount" value="${item.realAmount}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">原下游交易流水号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="下游交易流水号" name="tradeNo" value="${item.tradeNo}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">原交易流水号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="交易流水号" name="transNo" value="${item.transNo}" readonly/>
			</td>
		</tr>
		
		<tr>
			<td class="liner-box-left required">POSP商户编号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="POSP商户编号" name="pospMchNo" value="${item.pospMchNo}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">固定请求编号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="固定请求编号" name="proCod" value="${item.proCod}" readonly/>
			</td>
		</tr>
		

		<tr>
			<td class="liner-box-left required">批次号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="批次号" name="resePriNo" value="${item.resePriNo}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易参考号:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="交易参考号" name="retReNo" value="${item.retReNo}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易状态:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="交易状态" name="status" value="${item.status}" readonly/>
			</td>
		</tr>
		<tr>
			<td class="liner-box-left required">交易状态描述:</td>
			<td class="liner-box-right">
				<input type="text" class="input-box" placeholder="交易状态描述" name="statusDesc" value="${item.statusDesc}" readonly/>
			</td>
		</tr>
	</table>
</form>