<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="<c:url value="/admin/system/index" />" method="post">
	<input type="hidden" name="id" value="${item.id}"/>
	<table class="liner-box">
		<c:if test="${not empty error}">
		<tr class="liner-error">
			<td class="liner-box-left"></td>
			<td class="liner-box-right">
				${error}
			</td>
		</tr>
		</c:if>
		<c:forEach var="item" items="${items }">
		<tr>
			<td class="liner-box-left">${item.pname }:</td>
			<td class="liner-box-right">
				<input type="hidden" name="pcodes" value="${item.pcode }">
				<input type="text" class="input-box" <c:if test="${empty item.isEditFlag or not item.isEditFlag }">readonly="readonly"</c:if> name="pvalues" value="<c:out value='${item.pvalue }' />"/>
				<c:if test="${not empty item.pdesc }">
				<div class="liner-box-tips">${item.pdesc }</div>
				</c:if>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="2" class="liner-box-one-line"><input type="submit" value="提交" class="btn"/></td>
		</tr>
	</table>
</form>