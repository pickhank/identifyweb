<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="utils" tagdir="/WEB-INF/tags/utils"%>
<div class="page__hd">
	<h1 class="page__title">Agent</h1>
	<p class="page__desc">代理商</p>
</div>
<div class="weui-flex exec-btn-wapper">
	<div class="weui-flex__item"><a href="javascript:$('.search-form').toggle('slow');" class="weui-btn weui-btn_mini weui-btn_default">搜索</a></div>
	<div class="weui-flex__item">
		<form action="<c:url value="/admin/downAgent/export" />" target="_blank" method="post" class="export-form" style="display:inline;">
			<input type="submit" value="导出" class="weui-btn weui-btn_mini weui-btn_primary export-btn" ></input>
		</form>
	</div>
	<div class="weui-flex__item"><a href="<c:url value="/admin/downAgent/add" />" class="weui-btn weui-btn_mini weui-btn_warn operate-detail">新建</a></div>
</div>
<form class="search-form" action="<c:url value="/admin/downAgent/index" />" method="post" style="display:none;">
	<input type="hidden" name="pageIndex" value="${search.pageIndex}"></input><input type="hidden" name="pageSize" value="${search.pageSize}"></input>
	<div class="weui-cells__title">搜索条件</div>
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">关键字</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="关键字" name="keywords" value="${search.keywords}">
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
	<div class="weui-form-preview__hd">
		<label class="weui-form-preview__label">编号</label>
		<em class="weui-form-preview__value"><c:out value="${item.orderNo}" /></em>
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
			<label class="weui-form-preview__label">结算卡卡号</label>
			<span class="weui-form-preview__value"><c:out value="${item.cardNo}" /></span>
		</div>
		<div class="weui-form-preview__item">
			<label class="weui-form-preview__label">状态</label>
			<span class="weui-form-preview__value"><c:out value="${item.status}" /></span>
		</div>
	</div>
	<div class="weui-form-preview__ft">
		<a class="weui-form-preview__btn weui-form-preview__btn_primary operate-detail" href="<c:url value="/admin/downAgent/add?id=${item.id}" />">编辑</a>
		<button type="submit" class="weui-form-preview__btn weui-form-preview__btn_default operate-delete" href="<c:url value="/admin/downAgent/delete/${item.id}" />">删除</button>
	</div>
</div>
<br>
</c:forEach>