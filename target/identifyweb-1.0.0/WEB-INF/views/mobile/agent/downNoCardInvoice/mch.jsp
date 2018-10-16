<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page__hd">
	<h1 class="page__title">Merchant</h1>
	<p class="page__desc">商户</p>
</div>
<div class="weui-flex exec-btn-wapper">
	<div class="weui-flex__item">
		<a href="javascript:$('.search-form').toggle('slow');" class="weui-btn weui-btn_mini weui-btn_default">搜索</a>
	</div>
</div>
<form class="search-form" action="<c:url value="/agent/downNoCardInvoice/mch" />" method="post" style="display:none;">
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="weui-cells__title">搜索条件</div>
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">关键字</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="关键字" name="keywords" value="${search.keywords}">
			</div>
		</div>
	</div>
	<div style="text-align: center; background: #FFF; padding: 8px; border-bottom: 1px solid #267ad2;">
		<input type="submit" value="查询" class="weui-btn weui-btn_mini weui-btn_primary"></input>
	</div>
</form>
<utils:mobilepager />
	<c:forEach var="item" items="${res.items}" varStatus="status">
	<div class="weui-form-preview">
		<div class="weui-form-preview__hd">
			<label class="weui-form-preview__label">商户编号</label> 
			<em class="weui-form-preview__value"><c:out value="${item.mchNo}" /></em>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">姓名</label> 
				<span class="weui-form-preview__value"><c:out value="${item.name}" /></span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">电话</label> 
				<span class="weui-form-preview__value"><c:out value="${item.phone}" /></span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">结算卡银行</label> 
				<span class="weui-form-preview__value"><c:out value="${item.bankName}" /></span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">状态</label> 
				<span class="weui-form-preview__value"><c:out value="${item.status}" /></span>
			</div>
		</div>
	</div>
	<br>
	</c:forEach>
