<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page__hd">
	<h1 class="page__title">NoCardInvoice</h1>
	<p class="page__desc">无卡交易</p>
</div>
<div class="weui-flex exec-btn-wapper">
	<div class="weui-flex__item"><a href="javascript:$('.search-form').toggle('slow');" class="weui-btn weui-btn_mini weui-btn_default">搜索</a></div>
	<div class="weui-flex__item">
		<form action="<c:url value="/agent/downNoCardInvoice/export" />" target="_blank" method="post" class="export-form" style="display: inline;">
			<input type="submit" value="导出" class="weui-btn weui-btn_mini weui-btn_primary export-btn"></input>
		</form>
	</div>
</div>
<form class="search-form" action="<c:url value="/agent/downNoCardInvoice/index" />" method="post" style="display:none;">
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="weui-cells__title">搜索条件</div>
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">关键字</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="关键字" name="keywords" value="${search.keywords}">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">商户号</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="商户号" name="mchNo" value="${search.mchNo}">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">流水号</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="流水号" name="transNo" value="${search.transNo}">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">下游流水号</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="下游流水号" name="tradeNo" value="${search.tradeNo}">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">手机号</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="手机号" name="userToken" value="${search.userToken}">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">支付卡号</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="支付卡号" name="payCardNo" value="${search.payCardNo}">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">收款卡号</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="收款卡号" name="cardNo" value="${search.cardNo}">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">身份证号</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="身份证号" name="accIdCard" value="${search.accIdCard}">
			</div>
		</div>
		<div class="weui-cell weui-cell_select weui-cell_select-after">
			<div class="weui-cell__hd">
			<label class="weui-label">交易状态</label></div>
			<div class="weui-cell__bd">
				<select name="status" class="weui-select">
					<option value="" >全部
					<option value="00" <c:if test="${search.status eq '00'}">selected</c:if>>成功
					<option value="01" <c:if test="${search.status eq '01'}">selected</c:if>>交易中
					<option value="09" <c:if test="${search.status eq '09'}">selected</c:if>>发起交易中
					<option value="02" <c:if test="${search.status eq '02'}">selected</c:if>>失败
				</select>
			</div>
		</div>
		<div class="weui-cell weui-cell_select weui-cell_select-after">
			<div class="weui-cell__hd">
			<label class="weui-label">代付状态</label></div>
			<div class="weui-cell__bd">
				<select name="qfStatus" class="weui-select">
					<option value="" >全部
					<option value="NOT_QF" <c:if test="${search.qfStatus eq 'NOT_QF'}">selected</c:if>>未代付
					<option value="IN_PROCESS" <c:if test="${search.qfStatus eq 'IN_PROCESS'}">selected</c:if>>代付中
					<option value="SUCCESS" <c:if test="${search.qfStatus eq 'SUCCESS'}">selected</c:if>>代付成功
					<option value="FAILED" <c:if test="${search.qfStatus eq 'FAILED'}">selected</c:if>>代付失败
				</select>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label for="" class="weui-label">开始日期</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="date"  name="startDate" value="<fmt:formatDate value="${search.startDate}" pattern="yyyy-MM-dd"/>">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label for="" class="weui-label">结束日期</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="date" name="endDate" value="<fmt:formatDate value="${search.endDate}" pattern="yyyy-MM-dd"/>">
			</div>
		</div>
	</div>
	<div style="text-align: center; background: #FFF; padding: 8px; border-bottom: 1px solid #267ad2;">
		<input type="submit" value="查询" class="weui-btn weui-btn_mini weui-btn_primary"></input>
	</div>
</form>
<utils:mobilepager/>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<div class="weui-form-preview">
			<div class="weui-form-preview__hd">
				<label class="weui-form-preview__label">商户号</label>
				<em class="weui-form-preview__value"><c:out value="${item.mchNo}" /></em>
			</div>
			<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">下游交易流水号</label>
				<span class="weui-form-preview__value"><c:out value="${item.tradeNo}" /></span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">交易流水号</label>
				<span class="weui-form-preview__value"><c:out value="${item.transNo}" /></span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">交易金额</label>
				<span class="weui-form-preview__value"><c:out value="${item.price}" /></span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">用户结算额</label>
				<span class="weui-form-preview__value"><c:out value="${item.downRealPrice}" /></span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">手续费</label>
				<span class="weui-form-preview__value"><c:out value="${item.downFeePrice}" /></span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">下游利润</label>
				<span class="weui-form-preview__value"><c:out value="${item.profit}" /></span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">订单时间</label>
				<span class="weui-form-preview__value"><c:out value="${item.orderDate}" /></span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">订单完成时间</label>
				<span class="weui-form-preview__value"><fmt:formatDate value="${item.successDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">交易状态</label>
				<span class="weui-form-preview__value"><c:out value="${item.statusDesc}" /></span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">代付状态</label>
				<span class="weui-form-preview__value">
					<c:if test="${item.qfStatus eq 'NOT_QF'}">未代付</c:if>
					<c:if test="${item.qfStatus eq 'IN_PROCESS'}">代付中</c:if>
					<c:if test="${item.qfStatus eq 'SUCCESS'}">代付成功</c:if>
					<c:if test="${item.qfStatus eq 'FAILED'}">代付失败</c:if>
				</span>
			</div>
			</div>
			<div class="weui-form-preview__ft">
				<a class="weui-form-preview__btn weui-form-preview__btn_primary operate-detail" href="<c:url value="/agent/downNoCardInvoice/add?id=${item.id}" />">详情</a>
			</div>
		</div>
		<br>
	</c:forEach>
