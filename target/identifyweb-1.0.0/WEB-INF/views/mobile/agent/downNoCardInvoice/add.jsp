<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/iframe-status.jsp"/>
<div class="page__hd">
	<h1 class="page__title">NoCardInvoice</h1>
	<p class="page__desc">无卡交易详情</p>
</div>
<div class="weui-form-preview">
	<div class="weui-form-preview__bd">
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">接口版本号</label>
			<span class="weui-form-preview__value"><c:out value="${item.versionNo}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">交易金额</label>
			<span class="weui-form-preview__value"><c:out value="${item.price}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">结算额</label>
			<span class="weui-form-preview__value"><c:out value="${item.realPrice}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">手续费</label>
			<span class="weui-form-preview__value"><c:out value="${item.feePrice}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">交易备注</label>
			<span class="weui-form-preview__value"><c:out value="${item.description}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">下游交易流水号</label>
			<span class="weui-form-preview__value"><c:out value="${item.tradeNo}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">交易流水号</label>
			<span class="weui-form-preview__value"><c:out value="${item.transNo}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">交易返回值</label>
			<span class="weui-form-preview__value"><c:out value="${item.tranStr}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">订单时间</label>
			<span class="weui-form-preview__value"><c:out value="${item.orderDate}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">交易状态</label>
			<span class="weui-form-preview__value"><c:out value="${item.status}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">交易状态描述</label>
			<span class="weui-form-preview__value"><c:out value="${item.statusDesc}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">通知地址</label>
			<span class="weui-form-preview__value"><c:out value="${item.notifyUrl}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">前端回调地址</label>
			<span class="weui-form-preview__value"><c:out value="${item.callbackUrl}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">异步通知是否成功</label>
			<span class="weui-form-preview__value"><c:out value="${item.notifySuccess}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">异步通知次数</label>
			<span class="weui-form-preview__value"><c:out value="${item.notifyTimes}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">末次异步通知时间</label>
			<span class="weui-form-preview__value"><c:out value="${item.notifyLastDate}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">费率(千分之X)</label>
			<span class="weui-form-preview__value"><c:out value="${item.payFee}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">代付状态</label>
			<span class="weui-form-preview__value"><c:out value="${item.qfStatus}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">商户费率(千分之X)</label>
			<span class="weui-form-preview__value"><c:out value="${item.downPayFee}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">商户清算金额</label>
			<span class="weui-form-preview__value"><c:out value="${item.downRealPrice}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">商户手续费金额</label>
			<span class="weui-form-preview__value"><c:out value="${item.downFeePrice}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">下游机构利润</label>
			<span class="weui-form-preview__value"><c:out value="${item.profit}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">收款人姓名</label>
			<span class="weui-form-preview__value"><c:out value="${item.accName}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">收款人身份证</label>
			<span class="weui-form-preview__value"><c:out value="${item.accIdCard}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">收款人电话</label>
			<span class="weui-form-preview__value"><c:out value="${item.accTel}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">收款卡银行</label>
			<span class="weui-form-preview__value"><c:out value="${item.bankName}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">收款卡号</label>
			<span class="weui-form-preview__value"><c:out value="${item.cardNo}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">支付卡号</label>
			<span class="weui-form-preview__value"><c:out value="${item.payCardNo}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">联行行号</label>
			<span class="weui-form-preview__value"><c:out value="${item.bankCode}" /></span>
		</div>
	</div>
</div>