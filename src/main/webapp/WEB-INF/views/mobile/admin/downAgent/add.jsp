<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="page__hd">
	<h1 class="page__title">Agent</h1>
	<p class="page__desc">代理商</p>
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

<form action="<c:url value="/admin/downAgent/add" />" method="post">
	<input type="hidden" name="id" value="${item.id}"></input>
	<c:if test="${empty item.id}">
	<div class="weui-cells__title">新建代理商</div>
	</c:if>
	<c:if test="${not empty item.id}">
	<div class="weui-cells__title">编辑代理商</div>
	</c:if>
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">编号</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="代理商编号-自动生成" name="orderNo" value="${item.orderNo}" readonly>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="姓名" name="name" value="${item.name}">
			</div>
		</div>
		
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">电话</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="电话" name="phone" value="${item.phone}">
			</div>
		</div>
		
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">结算卡银行</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="结算卡银行" name="bankName" value="${item.bankName}">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">结算卡卡号</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="结算卡卡号" name="cardNo" value="${item.cardNo}">
			</div>
		</div>
		<div class="weui-cell weui-cell_select weui-cell_select-after">
			<div class="weui-cell__hd"><label class="weui-label">状态</label></div>
			<div class="weui-cell__bd">
				<select class="weui-select" name="status">
					<option value="PENDING" <c:if test="${item.status eq 'PENDING'}">selected</c:if>>未使用
					<option value="ACTIVE" <c:if test="${item.status eq 'ACTIVE'}">selected</c:if>>使用中
					<option value="HOLD" <c:if test="${item.status eq 'HOLD'}">selected</c:if>>暂停使用
				</select>
			</div>
		</div>
	</div>
	<div style="text-align: center; background: #FFF; padding: 8px; border-bottom: 1px solid #267ad2;">
		<input type="submit" value="提交" class="weui-btn weui-btn_mini weui-btn_primary"></input>
	</div>
</form>
