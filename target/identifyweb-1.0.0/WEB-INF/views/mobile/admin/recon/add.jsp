<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="page__hd">
	<h1 class="page__title">Recon</h1>
	<p class="page__desc">对账单</p>
</div>
<c:if test="${not empty status}">
	<article class="weui-article" style="background:#FFF;">
		<c:if test="${status}">
		<div><i class="weui-icon-success"></i>操作成功</div>
		</c:if>
		<c:if test="${not status}">
		<div><i class="weui-icon-warn"></i>${error}</div>
		</c:if>
	</article>
</c:if>
<form action="<c:url value="/admin/recon/add" />" method="post">
	<input type="hidden" name="id" value="${item.id}" ></input>
	<div class="weui-cells__title">对账</div>
	<div class="weui-cells weui-cells_form">
	<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">对账日期</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="YYYYMMDD" name="orderDate" value="${item.orderDate}" >
			</div>
		</div>
		</div>
		<div style="text-align: center; background: #FFF; padding: 8px; border-bottom: 1px solid #267ad2;">
		<input type="submit" value="提交" class="weui-btn weui-btn_mini weui-btn_primary"></input>
	</div>
</form>