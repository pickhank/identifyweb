<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<div class="page__hd">
	<h1 class="page__title">Recon</h1>
	<p class="page__desc">对账单</p>
</div>
<div class="weui-flex exec-btn-wapper">
	<div class="weui-flex__item"><a href="javascript:$('.search-form').toggle('slow');" class="weui-btn weui-btn_mini weui-btn_default">搜索</a></div>
	<div class="weui-flex__item">
			<a href="<c:url value="/admin/recon/export" />" class="weui-btn weui-btn_mini weui-btn_default">下载对账单</a>
	</div>
	<div class="weui-flex__item"><a href="<c:url value="/admin/recon/add" />" class="weui-btn weui-btn_mini weui-btn_warn operate-detail">对账</a></div>
</div>
<form class="search-form" action="<c:url value="/admin/recon/index" />" method="post" style="display:none;">
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
			<div class="weui-cell__hd"><label class="weui-label">日期</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="YYYYMMDD" name="orderDate" value="${search.orderDate}">
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
				<label class="weui-form-preview__label">交易日期</label>
				<span class="weui-form-preview__value"><c:out value="${item.orderDate}" /></span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">总交易额</label>
				<span class="weui-form-preview__value"><c:out value="${item.price}" /></span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">总清算额</label>
				<span class="weui-form-preview__value"><c:out value="${item.realPrice}" /></span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">总成功笔数</label>
				<span class="weui-form-preview__value"><c:out value="${item.successCount}" /></span>
			</div>
		</div>
		<div class="weui-form-preview__ft">
			<a class="weui-form-preview__btn weui-form-preview__btn_primary operate-detail" href="http://pay.mishua.cn/zhonlinepay/uploads/${item.filePath}" target="_blank" >下载</a>
		</div>
	</div>
	<br>
	</c:forEach>