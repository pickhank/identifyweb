<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<div class="page__hd">
	<h1 class="page__title">NoCardRoute</h1>
	<p class="page__desc">机构交易路由</p>
</div>
<div class="weui-flex exec-btn-wapper">
	<div class="weui-flex__item"><a href="javascript:$('.search-form').toggle('slow');" class="weui-btn weui-btn_mini weui-btn_default">搜索</a></div>
	<div class="weui-flex__item"><a href="<c:url value="/admin/downNoCardRoute/add" />" class="weui-btn weui-btn_mini weui-btn_warn operate-detail">新建</a></div>
</div>
<form class="search-form" action="<c:url value="/admin/downNoCardRoute/index" />" method="post" style="display:none;">
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
			<div class="weui-cell__hd"><label class="weui-label">机构号</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" name="downMchNo" value="${search.downMchNo}" />
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">商户类别</label></div>
			<div class="weui-cell__bd">
				<utils:merccate name="mercCategory" cssName="weui-select" value="${search.mercCategory}"></utils:merccate>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">路由类型</label></div>
			<div class="weui-cell__bd">
				<select name="routeCategory" class="weui-select">
					<option value="">全部
					<option value="MERCHANT" <c:if test="${search.routeCategory eq 'MERCHANT'}">selected</c:if>>按商户号
					<option value="CATEGORY" <c:if test="${search.routeCategory eq 'CATEGORY'}">selected</c:if>>按类别
				</select>
			</div>
		</div>
		<!-- div class="weui-cell">
			<div class="weui-cell__hd"><label for="" class="weui-label">日期</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="date" value="">
			</div>
		</div> -->
	</div>
	<div style="text-align: center; background: #FFF; padding: 8px; border-bottom: 1px solid #267ad2;">
		<input type="submit" value="查询" class="weui-btn weui-btn_mini weui-btn_primary"></input>
	</div>
</form>
<utils:mobilepager/>
	<c:forEach var="item" items="${res.items}" varStatus="status">
		<div class="weui-form-preview">
			<div class="weui-form-preview__bd">
				<div class="weui-form-preview__item">
					<label class="weui-form-preview__label">优先级</label>
					<span class="weui-form-preview__value"><c:out value="${item.priority}" /></span>
				</div>
				<c:if test="${item.routeCategory eq 'MERCHANT'}">
					<div class="weui-form-preview__item">
						<label class="weui-form-preview__label">按商户号</label>
						<span class="weui-form-preview__value">
							<c:if test="${empty item.downMchNo}">-</c:if>
							<c:if test="${not empty item.downMchNo}"><c:out value="${item.downMchName}" />(<c:out value="${item.downMchNo}" />)</c:if>
						</span>
					</div>
				</c:if>
				<c:if test="${item.routeCategory eq 'CATEGORY'}">
					<div class="weui-form-preview__item">
						<label class="weui-form-preview__label">按类别</label>
						<span class="weui-form-preview__value"><utils:merccate mode="lbl" value="${item.mercCategory}" /></span>
					</div>
				</c:if>
				<div class="weui-form-preview__item">
					<label class="weui-form-preview__label">通道</label>
					<span class="weui-form-preview__value"><utils:combo mode="lbl" value="${item.channel}" /></span>
				</div>
				<div class="weui-form-preview__item">
					<label class="weui-form-preview__label">通道商户</label>
					<span class="weui-form-preview__value"><c:out value="${item.channelMercName}" /></span>
				</div>
				<div class="weui-form-preview__item">
					<label class="weui-form-preview__label">失效日期</label>
					<span class="weui-form-preview__value"><utils:dateFormat value="${item.expDate}" /></span>
				</div>
				<div class="weui-form-preview__item">
					<label class="weui-form-preview__label">失效时间</label>
					<span class="weui-form-preview__value"><utils:dateFormat value="${item.expTime}" /></span>
				</div>
			</div>
			<div class="weui-form-preview__ft">
				<a class="weui-form-preview__btn weui-form-preview__btn_primary operate-detail" href="<c:url value="/admin/downNoCardRoute/add?id=${item.id}" />">编辑</a>
				<button type="submit" class="weui-form-preview__btn weui-form-preview__btn_default operate-delete" href="<c:url value="/admin/downNoCardRoute/delete/${item.id}" />">删除</button>
			</div>
		</div>
		<br>
	</c:forEach>
