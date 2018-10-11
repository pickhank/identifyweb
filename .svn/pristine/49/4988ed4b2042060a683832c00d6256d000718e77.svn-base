<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page__hd">
	<h1 class="page__title">NoCardInvoice</h1>
	<p class="page__desc">快捷交易明细</p>
</div>
<div class="weui-flex exec-btn-wapper">
	<div class="weui-flex__item"><a href="javascript:$('.search-form').toggle('slow');" class="weui-btn weui-btn_mini weui-btn_default">搜索</a></div>
	<div class="weui-flex__item">&nbsp;</div>
</div>
<form class="search-form" action="<c:url value="/admin/downNoCardInvoice/index" />" method="post" style="display:none;">
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="weui-cells__title">搜索条件</div>
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">机构号</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" name="mchNo" value="${search.mchNo}" />
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">平台流水号</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" name="transNo" value="${search.transNo}" />
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">通道</label></div>
			<div class="weui-cell__bd">
				<utils:combo name="channel" cssName="weui-select" value="${search.channel}"></utils:combo>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">交易状态</label></div>
			<div class="weui-cell__bd">
				<select name="status" class="weui-select">
					<option value="">全部
					<option value="00" <c:if test="${search.status eq '00'}">selected</c:if>>成功
					<option value="01" <c:if test="${search.status eq '01'}">selected</c:if>>交易中
					<option value="09" <c:if test="${search.status eq '09'}">selected</c:if>>发起交易中
					<option value="02" <c:if test="${search.status eq '02'}">selected</c:if>>失败
				</select>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">开始时间</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="date" name="startDate" value="<fmt:formatDate value="${search.startDate}" pattern="yyyy-MM-dd"/>" pattern="yyyy-MM-dd"/>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">结束时间</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="date" name="endDate" value="<fmt:formatDate value="${search.endDate}" pattern="yyyy-MM-dd"/>" pattern="yyyy-MM-dd" />
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
			<label class="weui-form-preview__label">机构</label>
			<em class="weui-form-preview__value"><c:out value="${item.mchName}" />(<c:out value="${item.mchNo}" />)</em>
		</div>
		<div class="weui-form-preview__bd">
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">渠道</label>
			<span class="weui-form-preview__value"><utils:combo mode="lbl" value="${item.channel}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">渠道商户</label>
			<span class="weui-form-preview__value">${item.channelAccName}(${item.channelAccNo})</span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">交易金额</label>
			<span class="weui-form-preview__value"><fmt:formatNumber value="${item.price}" pattern="#,##0.00#"/>&nbsp;</span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">交易流水号</label>
			<span class="weui-form-preview__value"><c:out value="${item.transNo}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">受理时间</label>
			<span class="weui-form-preview__value"><fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd HH:mm:ss"/></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">交易状态</label>
			<span class="weui-form-preview__value " id="trade_detail_status_${status.index }"><c:out value="${item.status}" /></label> - <c:out value="${item.statusDesc}" /></span>
		</div>
		</div>
		<div class="weui-form-preview__ft">
			<a class="weui-form-preview__btn weui-form-preview__btn_primary operate-detail" href="<c:url value="/admin/downNoCardInvoice/detail?id=${item.id}" />">详情</a>
			<button type="submit" class="weui-form-preview__btn weui-form-preview__btn_default operate-check" ref="#trade_detail_status_${status.index }" href="<c:url value="/admin/downNoCardInvoice/check/${item.id}" />">同步</button>
		</div>
	</div>
	<br>
</c:forEach>
