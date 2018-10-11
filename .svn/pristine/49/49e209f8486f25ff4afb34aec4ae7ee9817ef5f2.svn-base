<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page__hd">
	<h1 class="page__title">System Setting</h1>
	<p class="page__desc">系统设置</p>
</div>

<c:if test="${not empty error}">
	${error}
</c:if>
<form action="<c:url value="/admin/system/index" />" method="post">
	<input type="hidden" name="id" value="${item.id}"/>
	<c:forEach var="item" items="${items }">
	<div class="weui-cells__title">${item.pname }</div>
	<div class="weui-cells">
		<div class="weui-cell">
			<div class="weui-cell__bd">
				<input type="hidden" name="pcodes" value="${item.pcode }">
				<input class="weui-input" type="text" <c:if test="${empty item.isEditFlag or not item.isEditFlag }">readonly="readonly"</c:if> name="pvalues" value="<c:out value='${item.pvalue }' />">
			</div>
		</div>
	</div>
	</c:forEach>
	<div class="weui-btn-area">
		<input type="submit" value="提交" class="weui-btn weui-btn_primary"/>
	</div>
</form>