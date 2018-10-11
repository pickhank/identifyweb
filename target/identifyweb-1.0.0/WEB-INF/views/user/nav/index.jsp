<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3 class="box-header-title">账户设置</h3>
<ul class="box-nav">
	<li class="box-nav-active">
		<a>账户信息</a>
	</li>
	<li>
		<a href="<c:url value="/user/resetPassword" />">重设密码</a>
	</li>
</ul>