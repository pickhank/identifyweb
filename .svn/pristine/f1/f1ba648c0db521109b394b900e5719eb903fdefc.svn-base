<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page__hd">
	<h1 class="page__title">Recon</h1>
	<p class="page__desc">对账单</p>
</div>
<div class="weui-flex exec-btn-wapper">
	<div class="weui-flex__item"><a href="<c:url value="/admin/recon/index" />" class="weui-btn weui-btn_mini weui-btn_warn operate-detail">返回列表</a></div>
</div>
<form class="search-form" action="<c:url value="/admin/recon/export" />" method="post">
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">商户号</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="商户号" name="mchNo" value="${search.mchNo}">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label for="" class="weui-label">开始日期</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="date" name="startDate" value="<fmt:formatDate value="${search.startDate}" pattern="yyyy-MM-dd"/>">
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
		<input type="submit" value="导出" class="weui-btn weui-btn_mini weui-btn_primary"></input>
	</div>
</form>