<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<div class="page__hd">
	<h1 class="page__title">Merchant</h1>
	<p class="page__desc">商户</p>
</div>
<div class="weui-flex exec-btn-wapper">
	<div class="weui-flex__item">
		<a href="javascript:$('.search-form').toggle('slow');" class="weui-btn weui-btn_mini weui-btn_default">搜索</a>
	</div>
</div>
<form class="search-form" action="<c:url value="/admin/downMerchant/index" />" method="post" style="display:none;">
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
			<div class="weui-cell__hd"><label class="weui-label">商户类别</label></div>
			<div class="weui-cell__bd">
				<utils:merccate name="mercCategory" cssName="weui-select" value="${search.mercCategory}"></utils:merccate>
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
			<label class="weui-form-preview__label"><c:out value="${item.mchNo}" /></label> 
			<em class="weui-form-preview__value"><c:out value="${item.name}" /></em>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">类别</label> 
				<span class="weui-form-preview__value"><utils:merccate mode="lbl" value="${item.mercCategory}"></utils:merccate></span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">信任IP</label> 
				<span class="weui-form-preview__value"><c:out value="${item.limitIps }" /></span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">状态</label> 
				<span class="weui-form-preview__value"><c:out value="${item.status}" /></span>
			</div>
		</div>
		<div class="weui-form-preview__ft">
			<a class="weui-form-preview__btn weui-form-preview__btn_primary operate-detail" href="<c:url value="/admin/downMerchant/add?id=${item.id}" />">编辑</a>
		</div>
	</div>
	<br>
</c:forEach>
