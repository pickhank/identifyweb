<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="page__hd">
	<h1 class="page__title">Agent</h1>
	<p class="page__desc">代理商管理</p>
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
<form action="<c:url value="/admin/agent/add"/>" method="post">
	<input type="hidden" name="id" value="${item.id}" /></input>
	<c:if test="${empty item.id}">
	<div class="weui-cells__title">新建代理商</div>
	</c:if>
	<c:if test="${not empty item.id}">
	<div class="weui-cells__title">编辑代理商</div>
	</c:if>
		<div class="weui-cells weui-cells_form">
		<c:if test="${empty item.id}">
		<div class="weui-cell weui-cell_select weui-cell_select-after">
			<div class="weui-cell__hd"><label class="weui-label">登录名</label></div>
			<div class="weui-cell__bd">
				<select class="weui-select" name="username">
					<option value="">请选择--
					<c:forEach var="item" items="${downagents}" varStatus="status">
					<option value="${item.orderNo}">${item.orderNo}&nbsp;&nbsp;${item.name}&nbsp;&nbsp;${item.phone}
					</c:forEach>
				</select>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty item.id}">
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">登录名</label></div>
			<div class="weui-cell__bd">
				<input type="text" class="weui-input" placeholder="登录名" name="username" value="${item.username}" readonly/>
			</div>
		</div>
		</c:if>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">邮箱</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="邮箱" name="email" value="${item.email}">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">密码</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" type="text" placeholder="密码" name="password" value="">
			</div>
		</div>
	</div>
	<div style="text-align: center; background: #FFF; padding: 8px; border-bottom: 1px solid #267ad2;">
			<input type="submit" value="提交" class="weui-btn weui-btn_mini weui-btn_primary"></input>
	</div>
</form>